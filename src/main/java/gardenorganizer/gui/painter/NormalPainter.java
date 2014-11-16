package gardenorganizer.gui.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class NormalPainter extends Painter {

    public NormalPainter(Point tlPoint, int width, int height, Color c) {
	super(tlPoint, width, height, c);
    }

    @Override
    public void paint(Graphics g) {
	g.setColor(getColor());
	g.drawRect(getTlPoint().x, getTlPoint().y, getWidth(), getHeight());

    }

}
