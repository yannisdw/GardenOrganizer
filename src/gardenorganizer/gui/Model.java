/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.gui;

import gardenorganizer.gui.exception.NoIslandSelectedException;
import gardenorganizer.model.Archipelago;
import gardenorganizer.model.Island;
import gardenorganizer.model.IslandTile;
import gardenorganizer.model.exception.TileBelongsToIslandException;
import gardenorganizer.util.Calculator;

import java.util.Iterator;

/**
 * 
 * @author yannis
 */
public class Model {

    private Archipelago archipelago;
    private Calculator calculator;

    public Archipelago getArchipelago() {
	return archipelago;
    }

    public void setArchipelago(Archipelago archipelago) {
	this.archipelago = archipelago;
    }

    public Calculator getCalculator() {
	return calculator;
    }

    public void setCalculator(Calculator calculator) {
	this.calculator = calculator;
    }

    public void addIslandsToArchipelago(Iterator<Island> islandIterator) {
	while (islandIterator.hasNext()) {
	    Island i = islandIterator.next();
	    archipelago.addIsland(i);
	}

    }

    public void addIslandTileWithNumberToSelectedIsland(int tileNumber) throws NoIslandSelectedException,
	    TileBelongsToIslandException {
	IslandTile islandTile = new IslandTile(tileNumber, calculator.getColumnWidth(), calculator.getRowHeight());
	archipelago.addIslandTileWithNumberToSelectedIsland(islandTile);

    }

}
