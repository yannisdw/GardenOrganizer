/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.model;

import gardenorganizer.gui.exception.NoIslandSelectedException;
import gardenorganizer.model.comparator.IslandComparator;
import gardenorganizer.model.exception.TileBelongsToIslandException;

import java.awt.Color;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author yannis
 */
public class Archipelago extends Observable {

    public static final String ISLANDS_CHANGED = "islandAdded";

    public static final String TILES_CHANGED = "tileAdded";

    private Island currentIsland;

    private final Set<Island> islands;

    private String name;

    public Archipelago() {
	this.islands = new TreeSet<Island>(new IslandComparator());
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public static Color getRandomColor() {
	Random r = new Random();
	return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public void addIsland(Island i) {

	setChanged();
	currentIsland = i;
	islands.add(i);
	notifyObservers(ISLANDS_CHANGED);
    }

    public void removeTile(int tileNumber) {
	for (Island i : islands) {
	    if (i.containsIslandNumber(tileNumber)) {
		i.removeIslandTile(tileNumber);
	    }
	}
	setChanged();
	notifyObservers(TILES_CHANGED);
    }

    public void setCurrentIsland(String currentIsland) {
	this.currentIsland = getIslandWithName(currentIsland);
    }

    public Iterator<Island> getIslandIterator() {
	return islands.iterator();
    }

    public void changeColorsOfIsland(Island i, Color c) {
	i.setColor(c);
	setChanged();
	notifyObservers(ISLANDS_CHANGED);
	notifyObservers(TILES_CHANGED);
    }

    private Island getIslandWithName(String name) {
	if (name == null || name.trim().equalsIgnoreCase("")) {
	    return null;
	}

	for (Island i : islands) {
	    if (name.equalsIgnoreCase(i.getIslandName())) {
		return i;
	    }
	}
	return null;
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Archipelago contains following islands: ").append("\n");
	for (Island i : islands) {
	    builder.append(i.toString()).append("\n");
	}
	return builder.toString();

    }

    public void reset() {
	currentIsland = null;
	islands.clear();

    }

    public void addIslandTileWithNumberToSelectedIsland(IslandTile createdIslandTile) throws NoIslandSelectedException,
	    TileBelongsToIslandException {
	if (currentIsland == null) {
	    throw new NoIslandSelectedException();
	}

	Island islandWhichContainsTile = getIslandWhichContainsTile(createdIslandTile);
	if (islandWhichContainsTile != null) {
	    throw new TileBelongsToIslandException(createdIslandTile, islandWhichContainsTile);
	}

	currentIsland.addIslandTile(createdIslandTile);
	setChanged();
	notifyObservers(TILES_CHANGED);
    }

    public Island getIslandWhichContainsTileNumber(int tileNumber) {
	for (Island i : islands) {
	    if (i.containsIslandNumber(tileNumber)) {
		return i;
	    }
	}
	return null;
    }

    private Island getIslandWhichContainsTile(IslandTile createdIslandTile) {
	for (Island i : islands) {
	    if (i.containsIslandNumber(createdIslandTile.getTileNumber())) {
		return i;
	    }
	}
	return null;
    }

    public Island getCurrentIsland() {
	return currentIsland;
    }

}
