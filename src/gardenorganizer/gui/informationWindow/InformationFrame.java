package gardenorganizer.gui.informationWindow;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.informationWindow.bootstrap.BootstrapPanel;
import gardenorganizer.gui.informationWindow.colordescription.LoadSavePanel;
import gardenorganizer.model.Archipelago;
import gardenorganizer.util.Calculator;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class InformationFrame extends JFrame {

    private Container contentPane;
    private Model model;
    private BootstrapPanel bootstrapPanel;
    private LoadSavePanel description;

    public InformationFrame() {
	contentPane = getContentPane();
	createFrame();
    }

    public void createFrame() {
	Archipelago archipelago = new Archipelago();
	model = new Model();
	model.setArchipelago(archipelago);
	model.setCalculator(new Calculator(18, 10, 50, 50));
	JTabbedPane tabbedPane = new JTabbedPane();
	bootstrapPanel = new BootstrapPanel(model);
	bootstrapPanel.buildUI();
	tabbedPane.addTab("Initialisatie", bootstrapPanel);
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	description = new LoadSavePanel(model, this);

	tabbedPane.addTab("Laden / Bewaren", description);

	contentPane.add(tabbedPane);

	setBounds(32, 32, 250, 250);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    public void setModel(Model model) {
	this.model = model;
	bootstrapPanel.setModel(model);
	description.setModel(model);
    }

    public void setContentPane(Container contentPane) {
	this.contentPane = contentPane;
    }

}
