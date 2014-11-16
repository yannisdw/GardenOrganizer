package gardenorganizer.gui.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Painter {

    private final Point tlPoint;
    private final int width;
    private final int height;
    private final Color color;

    protected Painter(Point tlPoint, int width, int height, Color c) {
	this.tlPoint = tlPoint;
	this.width = width;
	this.height = height;
	this.color = c;
    }

    public Color getColor() {
	return color;
    }

    public Point getTlPoint() {
	return tlPoint;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public abstract void paint(Graphics g);

}
