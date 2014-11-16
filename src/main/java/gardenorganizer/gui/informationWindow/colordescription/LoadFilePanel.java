package gardenorganizer.gui.informationWindow.colordescription;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.gardenframe.GardenOrganizerFrame;
import gardenorganizer.gui.informationWindow.InformationFrame;
import gardenorganizer.model.persistence.Importer;
import gardenorganizer.model.persistence.SourceDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoadFilePanel extends JPanel implements Observer {

    private SourceDirectory sourceDirectory;
    private JComboBox existingFilesCombo;
    private InformationFrame informationFrame;

    public LoadFilePanel(SourceDirectory sourceDirectory, InformationFrame informationFrame) {
	this.sourceDirectory = sourceDirectory;
	this.informationFrame = informationFrame;
	sourceDirectory.addObserver(this);
	createUI();
    }

    private void createUI() {

	existingFilesCombo = new JComboBox();

	for (String existingFileName : sourceDirectory.existingSourceFileNames()) {
	    existingFilesCombo.addItem(existingFileName);
	}

	JButton btnLoad = new JButton("LOAD");
	btnLoad.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		String selectedFileName = (String) existingFilesCombo.getSelectedItem();

		Importer importer = new Importer(sourceDirectory.createPathForFileName(selectedFileName));

		Model model = importer.importFile();
		GardenOrganizerFrame gardenOrganizerFrame = new GardenOrganizerFrame(model);
		gardenOrganizerFrame.initAndDisplay();
		model.addIslandsToArchipelago(importer.getArchipelago().getIslandIterator());

		informationFrame.setModel(model);
	    }

	});
	add(existingFilesCombo);
	add(btnLoad);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	existingFilesCombo.removeAllItems();
	for (String existingFileName : sourceDirectory.existingSourceFileNames()) {
	    existingFilesCombo.addItem(existingFileName);
	}

    }
}
