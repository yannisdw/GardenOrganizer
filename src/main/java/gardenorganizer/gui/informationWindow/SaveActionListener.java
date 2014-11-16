package gardenorganizer.gui.informationWindow;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.informationWindow.colordescription.LoadSavePanel;
import gardenorganizer.model.persistence.Exporter;
import gardenorganizer.model.persistence.SourceDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SaveActionListener implements ActionListener {

    private final SourceDirectory sourceDirectory;
    private LoadSavePanel panel;

    public SaveActionListener(LoadSavePanel panel, SourceDirectory sourceDirectory) {
	this.panel = panel;
	this.sourceDirectory = sourceDirectory;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

	Model model = panel.getModel();
	Exporter exporter = new Exporter(model, sourceDirectory);

	String fileName = (String) JOptionPane.showInputDialog("Naam: ", "");
	if (fileName == null || fileName.trim().length() <= 0) {
	    return;
	}

	if (!sourceDirectory.fileNameExists(fileName)) {
	    exporter.writeToFile(fileName);

	    sourceDirectory.itHasChanged();
	} else {
	    int overwrite = JOptionPane.showConfirmDialog(null, "Bestandsnaam bestaat al. Overschrijven?",
		    "Overschrijven", JOptionPane.YES_NO_OPTION);

	    if (overwrite == JOptionPane.YES_OPTION) {
		exporter.writeToFile(fileName);
	    } else {
		JOptionPane.showMessageDialog(null, "Bestand niet bewaard. \nKies een andere naam om te bewaren.",
			"Bestand niet bewaard!!", JOptionPane.WARNING_MESSAGE);
	    }

	}
    }

}
