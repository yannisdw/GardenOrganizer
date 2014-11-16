package gardenorganizer.gui.colorChangerWindow;

import gardenorganizer.gui.Model;
import gardenorganizer.model.Island;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorChangeFrame extends JFrame {

    private Model model;

    public ColorChangeFrame(Model model) {
	this.model = model;
    }

    public void initAndDisplay() {
	JPanel p = new JPanel(new GridLayout(0, 1));
	Iterator<Island> islandIterator = model.getArchipelago().getIslandIterator();
	while (islandIterator.hasNext()) {
	    final Island i = islandIterator.next();
	    final JPanel islandPanel = new JPanel();
	    islandPanel.setOpaque(true);
	    islandPanel.setBackground(i.getColor());
	    islandPanel.add(new JLabel(i.getIslandName()));
	    islandPanel.addMouseListener(new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
		    Color c = JColorChooser.showDialog(ColorChangeFrame.this, "title", i.getColor());
		    if (c != null && c != i.getColor()) {
			model.getArchipelago().changeColorsOfIsland(i, c);
			islandPanel.setBackground(c);
		    }
		}
	    });
	    p.add(islandPanel);
	}

	add(p);
	pack();
	setVisible(true);
    }
}
