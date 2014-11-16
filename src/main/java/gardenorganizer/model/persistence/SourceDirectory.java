package gardenorganizer.model.persistence;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceDirectory extends Observable {

    private static final String PATH_TO_SOURCE_FILES = System.getProperty("user.home") + "/GardenOrganizer";
    private final File f;

    private Logger logger = LoggerFactory.getLogger(SourceDirectory.class);

    public SourceDirectory() {

	f = new File(PATH_TO_SOURCE_FILES);
	if (!f.exists()) {
	    logger.info("make directory " + f);
	    f.mkdir();
	}
    }

    public static String getPathToSourceFiles() {
	return PATH_TO_SOURCE_FILES;
    }

    public List<String> existingSourceFileNames() {
	return Arrays.asList(f.list());
    }

    public boolean fileNameExists(String fileName) {

	if (!fileName.endsWith(".xml")) {
	    fileName = fileName.concat(".xml");
	}

	if (existingSourceFileNames().contains(fileName)) {
	    return true;
	}
	return false;
    }

    public String createPathForFileName(String fileName) {

	String concat = PATH_TO_SOURCE_FILES.concat("/").concat(fileName);
	if (concat.endsWith(".xml"))
	    return concat;
	return concat.concat(".xml");
    }

    public void itHasChanged() {
	setChanged();
	notifyObservers();

    }

}
