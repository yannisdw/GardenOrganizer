/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Yannis
 */
public class IslandTile extends Tile {

    private final List<IslandTile> islandNeighbors;

    public IslandTile(int tileNumber, int width, int height) {
	super(tileNumber, width, height);
	islandNeighbors = new ArrayList<IslandTile>();
    }

    public void addIslandNeighbor(IslandTile tile) {
	islandNeighbors.add(tile);
    }

    public List<IslandTile> getIslandNeighbors() {
	return islandNeighbors;
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("IslandTile ").append(getTileNumber());
	return builder.toString();
    }

}
