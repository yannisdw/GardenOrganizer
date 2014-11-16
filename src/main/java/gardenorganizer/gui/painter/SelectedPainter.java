package gardenorganizer.gui.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SelectedPainter extends Painter {

    public SelectedPainter(Point tlPoint, int width, int height, Color c) {
	super(tlPoint, width, height, c);

    }

    @Override
    public void paint(Graphics g) {
	g.setColor(getColor());
	g.fillRect(getTlPoint().x, getTlPoint().y, getWidth(), getHeight());
    }

}
