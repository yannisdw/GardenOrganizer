/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gardenorganizer.model;

import gardenorganizer.util.Calculator;

import java.awt.Graphics;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author yannis
 */
public class BoxGrid {

    private Set<Tile> tiles;
    private Iterator<Tile> boxIterator;
    private Tile lastBox;
    private int amountOfRows, amountOfColumns, rowHeight, columnWidth;
    private Map<Integer, Tile> boxMap;
    private int gridWidth, gridHeight;
    private Calculator c;

    private Set<Integer> unpaintableTiles = new HashSet<Integer>();
    private int[] unpaintables = { 0, 1, 2, 3, 18, 19, 20, 21, 36, 37, 54, 55, 72, 73, 90, 91, 108, 109,//
	    126, 127, 128, 129, 130, 131, 132,//
	    144, 145, 146, 147, 148, 149, 150,//
	    162, 163, 164, 165, 166, 167, 168 };

    public BoxGrid(Calculator c) {
	this.c = c;

	this.amountOfColumns = c.getAmountOfColumns();
	this.amountOfRows = c.getAmountOfRows();
	this.rowHeight = c.getRowHeight();
	this.columnWidth = c.getColumnWidth();
	gridWidth = amountOfColumns * columnWidth;
	gridHeight = amountOfRows * rowHeight;

	tiles = new TreeSet<Tile>(new Comparator<Tile>() {

	    @Override
	    public int compare(Tile o1, Tile o2) {
		return o1.getTileNumber() - o2.getTileNumber();
	    }
	});
	boxIterator = null;

	initializeTiles();
    }

    public int getGridHeight() {
	return gridHeight;
    }

    public int getGridWidth() {
	return gridWidth;
    }

    public Tile getTileWithNumber(int number) {
	return boxMap.get(number);
    }

    public Neighbors getNeighborsOfTile(int boxNumber) {
	Neighbors nb = new Neighbors();
	Calculator c = new Calculator(amountOfColumns, amountOfRows, 0, 0);
	if (c.hasBottomNeighbor(boxNumber)) {
	    nb.setBottomNeighbor(boxMap.get(boxNumber + amountOfColumns));
	}
	if (c.hasTopNeighbor(boxNumber)) {
	    nb.setTopNeighbor(boxMap.get(boxNumber - amountOfColumns));
	}
	if (c.hasLeftNeighbor(boxNumber)) {
	    nb.setLeftNeighbor(boxMap.get(boxNumber - 1));
	}
	if (c.hasRightNeighbor(boxNumber)) {
	    nb.setRightNeighbor(boxMap.get(boxNumber + 1));
	}
	return nb;
    }

    public Tile getNextBox() {

	if (boxIterator == null) {
	    boxIterator = tiles.iterator();
	}

	if (boxIterator.hasNext()) {
	    lastBox = boxIterator.next();
	    return lastBox;
	}

	return null;
    }

    public Tile getLastBoxTouched() {
	return lastBox;
    }

    private void initializeTiles() {
	boxMap = new HashMap<Integer, Tile>();
	int amountOfTiles = amountOfColumns * amountOfRows;
	for (int i = 0; i < amountOfTiles; i++) {
	    Tile t = new Tile(i, columnWidth, rowHeight);
	    t.setTopLeftPoint(c.calculateTLForBoxNumber(i));
	    tiles.add(t);
	    boxMap.put(i, t);
	}

	initializeUnpaintableTiles();

    }

    private void initializeUnpaintableTiles() {
	// TODO Auto-generated method stub
	for (int i = 0; i < unpaintables.length; i++) {
	    unpaintableTiles.add(unpaintables[i]);
	}

    }

    public void paintTiles(Graphics g) {

	for (Tile t : tiles) {
	    if (!unpaintableTiles.contains(t.getTileNumber())) {
		t.paint(g);
	    }
	}
    }

    public Set<Tile> getTiles() {
	return tiles;
    }

    public Iterator<Tile> getTileIterator() {
	return tiles.iterator();
    }
}
