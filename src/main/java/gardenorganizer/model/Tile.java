/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gardenorganizer.model;

import gardenorganizer.gui.painter.NormalPainter;
import gardenorganizer.gui.painter.Painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 
 * @author Yannis
 */
public class Tile {

    private final int tileNumber, width, height;

    private Point pointRelativeTo;

    private Painter normalPainter;

    public Tile(int tileNumber, int width, int height) {
	pointRelativeTo = new Point(0, 0);
	this.tileNumber = tileNumber;
	this.width = width;
	this.height = height;

    }

    public void setPainter(Painter normalPainter) {
	this.normalPainter = normalPainter;
    }

    /**
     * 
     * if <i>anotherBox</i> is right of this box, it will return <i>LEFT</i>
     * 
     * @param anotherTile
     * @return
     */
    public NeighborType getNeighborTypeComparedTo(Tile anotherTile) {
	return NeighborType.NONE;
    }

    public int getTileNumber() {
	return tileNumber;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public void setRelativeTo(Point p) {
	pointRelativeTo = p;
    }

    public Point getPointRelativeTo() {
	return pointRelativeTo;
    }

    public void setTopLeftPoint(Point topLeftPoint) {
	normalPainter = new NormalPainter(topLeftPoint, width, height,
		Color.black);
	pointRelativeTo = topLeftPoint;
    }

    public void paint(Graphics g) {
	normalPainter.paint(g);
	g.drawString("" + tileNumber, pointRelativeTo.x + getWidth() / 2,
		pointRelativeTo.y + getHeight() / 2);
    }

}
