/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.model;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author yannis
 */
public class Island {

    private HashMap<Integer, IslandTile> islandTilesSet;

    private final String islandName;

    private Color color;

    public Island(String islandName) {
	this(islandName, Archipelago.getRandomColor());
    }

    public Island(String islandName, Color islandColor) {
	this.islandName = islandName;
	this.color = islandColor;
	islandTilesSet = new HashMap<Integer, IslandTile>();
    }

    public void addIslandTile(IslandTile tile) {
	if (!islandTilesSet.containsKey(tile.getTileNumber())) {
	    islandTilesSet.put(tile.getTileNumber(), tile);
	}
    }

    public void removeIslandTile(int tileNumber) {
	if (islandTilesSet.containsKey(tileNumber)) {
	    islandTilesSet.remove(tileNumber);
	}
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public Color getColor() {
	return color;
    }

    public String getIslandName() {
	return islandName;
    }

    public Collection<IslandTile> getIslandTiles() {
	return islandTilesSet.values();
    }

    public List<IslandTile> getIslandNeighborsOfTile(Tile t) {
	throw new UnsupportedOperationException("not ready yet");
    }

    public boolean containsIslandNumber(int islandNumber) {
	return islandTilesSet.containsKey(islandNumber);
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Island ").append(islandName).append(" contains tiles ").append("\n   ");
	for (IslandTile islandTile : islandTilesSet.values()) {
	    builder.append(islandTile.toString()).append(" - ");
	}
	return builder.toString();
    }

}
