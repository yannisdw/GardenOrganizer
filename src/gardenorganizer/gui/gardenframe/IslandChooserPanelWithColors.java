/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.gui.gardenframe;

import gardenorganizer.model.Archipelago;
import gardenorganizer.model.Island;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * 
 * @author yannis
 */
@SuppressWarnings("serial")
public class IslandChooserPanelWithColors extends JPanel implements Observer {

    private final Archipelago archipelago;
    private List<ColorDescriptionPanel> panels;

    public IslandChooserPanelWithColors(Archipelago archipelago) {
	this.archipelago = archipelago;
	archipelago.addObserver(this);
	panels = new ArrayList<ColorDescriptionPanel>();
	setPreferredSize(new Dimension(200, 0));
	setLayout(new GridLayout(0, 1));
    }

    @Override
    public void update(Observable o, Object arg) {
	if ((String) arg == Archipelago.ISLANDS_CHANGED) {
	    panels.clear();
	    removeAll();
	    paintContent();
	    Iterator<Island> islandIterator = archipelago.getIslandIterator();
	    while (islandIterator.hasNext()) {
		Island island = islandIterator.next();
		Color islandColor = island.getColor();
		String islandName = island.getIslandName();
		panels.add(new ColorDescriptionPanel(islandColor, islandName, this));

	    }
	    paintContent();
	}

    }

    public void setSelectedIsland(String selectedIslandName) {
	archipelago.setCurrentIsland(selectedIslandName);
	for (ColorDescriptionPanel p : panels) {
	    if (p.getText().equalsIgnoreCase(selectedIslandName)) {
		p.markAsSelectected();
	    } else {
		p.markAsUnSelected();
	    }
	}

    }

    private void paintContent() {
	for (ColorDescriptionPanel p : panels) {
	    add(p);
	    revalidate();
	    repaint();
	}
    }

}
