package gardenorganizer.gui.informationWindow.colordescription;

import gardenorganizer.export.PDFPrinter;
import gardenorganizer.gui.Model;
import gardenorganizer.gui.colorChangerWindow.ColorChangeFrame;
import gardenorganizer.gui.informationWindow.InformationFrame;
import gardenorganizer.gui.informationWindow.SaveActionListener;
import gardenorganizer.model.persistence.SourceDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoadSavePanel extends JPanel {

    private Model model;
    private InformationFrame informationFrame;

    public LoadSavePanel(Model model, InformationFrame informationFrame) {
	this.model = model;
	this.informationFrame = informationFrame;
	buildUI();
    }

    public void buildUI() {
	JButton btnPrint = new JButton("Print");
	btnPrint.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new PDFPrinter(model.getArchipelago()).createPDF();

		JOptionPane.showMessageDialog(null,
			"document TuinPrint.pdf gecreëerd in " + System.getProperty("user.home"));

	    }

	});
	add(btnPrint);

	JButton btnSave = new JButton("SAVE");
	SourceDirectory sourceDirectory = new SourceDirectory();
	btnSave.addActionListener(new SaveActionListener(this, sourceDirectory));

	add(btnSave);

	JButton btnChangeColor = new JButton("Wijzig kleuren");
	btnChangeColor.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		new ColorChangeFrame(model).initAndDisplay();

	    }
	});
	add(btnChangeColor);
	add(new LoadFilePanel(sourceDirectory, informationFrame));

    }

    public void setModel(Model model) {
	this.model = model;
    }

    public Model getModel() {
	return model;
    }

}
