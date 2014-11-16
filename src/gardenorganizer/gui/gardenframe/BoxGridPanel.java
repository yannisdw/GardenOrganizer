/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.gui.gardenframe;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.exception.NoIslandSelectedException;
import gardenorganizer.gui.painter.NormalPainter;
import gardenorganizer.gui.painter.SelectedPainter;
import gardenorganizer.model.Archipelago;
import gardenorganizer.model.BoxGrid;
import gardenorganizer.model.Island;
import gardenorganizer.model.IslandTile;
import gardenorganizer.model.Tile;
import gardenorganizer.model.exception.TileBelongsToIslandException;
import gardenorganizer.util.Calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoxGridPanel extends JPanel implements MouseListener, Observer, SelectionModeObserver {

    private final BoxGrid boxGrid;
    private final Calculator c;
    private final Archipelago archipelago;
    private SelectionMode mode = SelectionMode.ADD_TO_ISLAND;
    private final Model model;

    public BoxGridPanel(Model model, SelectionModeObservable selectionModeObservable) {
	this.model = model;
	selectionModeObservable.addObserver(this);
	this.c = model.getCalculator();
	boxGrid = new BoxGrid(c);
	addMouseListener(this);
	Dimension dimension = new Dimension(boxGrid.getGridWidth() + 10, boxGrid.getGridHeight() + 10);
	setPreferredSize(dimension);
	setMinimumSize(dimension);
	this.archipelago = model.getArchipelago();
	archipelago.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	paintIslands(g);
    }

    public void mouseDragged(MouseEvent e) {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Iterator<Tile> allTiles = boxGrid.getTileIterator();

	while (allTiles.hasNext()) {
	    Tile nextTile = allTiles.next();
	    int boxNumberOfHoveredPoint = c.getBoxNumberForCoordinates(e.getX(), e.getY());
	    if (boxNumberOfHoveredPoint == nextTile.getTileNumber()) {

		try {
		    if (mode == SelectionMode.ADD_TO_ISLAND) {
			model.addIslandTileWithNumberToSelectedIsland(boxNumberOfHoveredPoint);
		    } else if (mode == SelectionMode.REMOVE_FROM_ISLAND) {
			archipelago.removeTile(boxNumberOfHoveredPoint);
		    }
		    break;
		} catch (NoIslandSelectedException e1) {
		    JOptionPane.showMessageDialog(this, e1.getMessage(), "No island found", JOptionPane.ERROR_MESSAGE);
		    break;
		} catch (TileBelongsToIslandException e2) {
		    JOptionPane.showMessageDialog(this, e2.getMessage());
		}

	    }
	    nextTile = boxGrid.getNextBox();
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    private void paintIslands(Graphics g) {
	boxGrid.paintTiles(g);
    }

    @Override
    public void update(Observable arg0, Object arg1) {

	resetTiles();

	Iterator<Island> islandIterator = archipelago.getIslandIterator();

	while (islandIterator.hasNext()) {
	    Island next = islandIterator.next();
	    Color color = next.getColor();

	    Collection<IslandTile> islandTiles = next.getIslandTiles();
	    for (IslandTile islandTile : islandTiles) {
		Tile tileInBoxGrid = boxGrid.getTileWithNumber(islandTile.getTileNumber());

		boxGrid.getTileWithNumber(islandTile.getTileNumber()).setPainter(
			new SelectedPainter(tileInBoxGrid.getPointRelativeTo(), islandTile.getWidth(), islandTile
				.getHeight(), color));
	    }
	}

	this.repaint();

    }

    private void resetTiles() {
	Iterator<Tile> allTiles = boxGrid.getTileIterator();
	while (allTiles.hasNext()) {
	    Tile t = allTiles.next();
	    t.setPainter(new NormalPainter(t.getPointRelativeTo(), t.getWidth(), t.getHeight(), Color.BLACK));
	}

    }

    @Override
    public void update(SelectionMode mode) {
	this.mode = mode;
    }

}
