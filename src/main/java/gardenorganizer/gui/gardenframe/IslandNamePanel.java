/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gardenorganizer.gui.gardenframe;

import gardenorganizer.model.Archipelago;
import gardenorganizer.model.Island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author yannis
 */
@SuppressWarnings("serial")
class IslandNamePanel extends JPanel implements ActionListener {

    private final JButton addButton;
    private final JTextField nameField;
    private final Archipelago archipelago;

    public IslandNamePanel(Archipelago archipelago) {
	addButton = new JButton("add");
	addButton.addActionListener(this);
	nameField = new JTextField(30);
	this.archipelago = archipelago;
	add(nameField);
	add(addButton);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	archipelago.addIsland(new Island(nameField.getText()));
	nameField.setText("");
    }

}
