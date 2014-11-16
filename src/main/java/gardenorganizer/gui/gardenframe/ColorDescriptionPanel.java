package gardenorganizer.gui.gardenframe;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorDescriptionPanel extends JPanel implements MouseListener {

    private Color c;
    private String text;

    private JLabel textLabel, colorLabel;
    private IslandChooserPanelWithColors parent;

    public ColorDescriptionPanel(Color islandColor, String islandName, IslandChooserPanelWithColors parent) {
	this.c = islandColor;
	this.text = islandName;
	this.parent = parent;
	addMouseListener(this);
	buildUI();

    }

    public String getText() {
	return text;
    }

    public void buildUI() {
	setOpaque(true);
	markAsUnSelected();
	textLabel = new JLabel(text);
	colorLabel = new JLabel();
	colorLabel.setOpaque(true);
	colorLabel.setBackground(c);

	setLayout(new GridLayout(1, 1));
	add(colorLabel);
	add(textLabel);
    }

    public void markAsUnSelected() {
	this.setBackground(Color.LIGHT_GRAY);
    }

    public void markAsSelectected() {
	this.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	parent.setSelectedIsland(text);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}
