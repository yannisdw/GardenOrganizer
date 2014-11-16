package gardenorganizer.gui.gardenframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SelectionModePanel extends JPanel implements ActionListener {

    private SelectionModeObservable observable;
    JRadioButton selectButton;
    JRadioButton unselectButton;

    public SelectionModePanel(SelectionModeObservable observable) {
	this.observable = observable;
	selectButton = new JRadioButton(SelectionMode.ADD_TO_ISLAND.getDescription());
	selectButton.addActionListener(this);
	selectButton.setSelected(true);
	unselectButton = new JRadioButton(SelectionMode.REMOVE_FROM_ISLAND.getDescription());
	unselectButton.addActionListener(this);
	ButtonGroup group = new ButtonGroup();
	group.add(selectButton);
	group.add(unselectButton);
	add(selectButton);
	add(unselectButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == selectButton) {
	    observable.setState(SelectionMode.ADD_TO_ISLAND);
	} else {
	    observable.setState(SelectionMode.REMOVE_FROM_ISLAND);
	}
    }
}
