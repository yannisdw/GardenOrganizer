/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.model;

/**
 * 
 * @author yannis
 */
public class Neighbors {

    private Tile leftNeighbor = null, rightNeighbor = null, topNeighbor = null,
	    bottomNeighbor = null;

    public Neighbors() {
    }

    public Neighbors(Tile leftNeighbor, Tile rightNeighbor, Tile topNeighbor,
	    Tile bottomNeighbor) {
	this.leftNeighbor = leftNeighbor;
	this.rightNeighbor = rightNeighbor;
	this.topNeighbor = topNeighbor;
	this.bottomNeighbor = bottomNeighbor;
    }

    public Tile getBottomNeighbor() {
	return bottomNeighbor;
    }

    public void setBottomNeighbor(Tile bottomNeighbor) {
	this.bottomNeighbor = bottomNeighbor;
    }

    public Tile getLeftNeighbor() {
	return leftNeighbor;
    }

    public void setLeftNeighbor(Tile leftNeighbor) {
	this.leftNeighbor = leftNeighbor;
    }

    public Tile getRightNeighbor() {
	return rightNeighbor;
    }

    public void setRightNeighbor(Tile rightNeighbor) {
	this.rightNeighbor = rightNeighbor;
    }

    public Tile getTopNeighbor() {
	return topNeighbor;
    }

    public void setTopNeighbor(Tile topNeighbor) {
	this.topNeighbor = topNeighbor;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("[");
	if (getTopNeighbor() != null) {
	    sb.append("Top: ");
	    sb.append(getTopNeighbor().getTileNumber());
	    sb.append(" - ");
	}
	if (getRightNeighbor() != null) {
	    sb.append("Right: ");
	    sb.append(getRightNeighbor().getTileNumber());
	    sb.append(" - ");
	}
	if (getBottomNeighbor() != null) {
	    sb.append("Bottom: ");
	    sb.append(getBottomNeighbor().getTileNumber());
	    sb.append(" - ");
	}
	if (getLeftNeighbor() != null) {
	    sb.append("Left: ");
	    sb.append(getLeftNeighbor().getTileNumber());

	}
	sb.append("] ");
	return "neighbors: " + sb.toString();
    }

}
