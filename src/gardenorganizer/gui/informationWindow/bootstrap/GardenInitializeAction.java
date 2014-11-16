package gardenorganizer.gui.informationWindow.bootstrap;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.gardenframe.GardenOrganizerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GardenInitializeAction implements ActionListener {

    private Model model;
    private BootstrapPanel bootstrapPanel;

    public GardenInitializeAction(Model model, BootstrapPanel panel) {
	this.bootstrapPanel = panel;
	this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	model.setCalculator(bootstrapPanel.getCalculator());
	new GardenOrganizerFrame(model).initAndDisplay();
    }

}
