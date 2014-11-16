package gardenorganizer.gui;

import gardenorganizer.gui.informationWindow.InformationFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String args[]) {
	SwingUtilities.invokeLater(new Runnable() {

	    @Override
	    public void run() {
		new InformationFrame();
	    }
	});
    }

}
