package gardenorganizer.model.exception;

import gardenorganizer.model.Island;
import gardenorganizer.model.IslandTile;

@SuppressWarnings("serial")
public class TileBelongsToIslandException extends Exception {

    public TileBelongsToIslandException(IslandTile createdIslandTile, Island islandWhichContainsTile) {
	super("Tile " + createdIslandTile.getTileNumber() + " already belongs to island "
		+ islandWhichContainsTile.getIslandName());
    }

}
