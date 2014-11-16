/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gardenorganizer.gui.gardenframe;

import gardenorganizer.gui.Model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GardenOrganizerFrame extends JFrame {
    private BoxGridPanel boxGridPanel;
    private Model model;

    public GardenOrganizerFrame(Model model) {
	this.model = model;
    }

    public void initAndDisplay() {

	this.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		model.getArchipelago().reset();
		super.windowClosing(e);
	    }

	});
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	buildUI();

	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	int screenHeight = screenSize.height;
	int screenWidth = screenSize.width;
	pack();
	setLocation(screenWidth / 2 - getWidth() / 2, screenHeight / 2 - getHeight() / 2);
	setVisible(true);
    }

    private void buildUI() {
	setTitle(model.getArchipelago().getName());
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(new IslandNamePanel(model.getArchipelago()), BorderLayout.NORTH);
	SelectionModeObservable observable = new SelectionModeObservable();

	boxGridPanel = new BoxGridPanel(model, observable);
	JScrollPane scrollPane = new JScrollPane(boxGridPanel);

	SelectionModePanel selectionModePanel = new SelectionModePanel(observable);
	getContentPane().add(selectionModePanel, BorderLayout.SOUTH);
	getContentPane().add(scrollPane, BorderLayout.CENTER);
	getContentPane().add(new IslandChooserPanelWithColors(model.getArchipelago()), BorderLayout.EAST);

    }
}
