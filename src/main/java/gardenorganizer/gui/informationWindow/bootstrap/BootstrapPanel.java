package gardenorganizer.gui.informationWindow.bootstrap;

import gardenorganizer.gui.Model;
import gardenorganizer.gui.util.SpringUtilities;
import gardenorganizer.util.Calculator;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class BootstrapPanel extends JPanel {

    final JTextField txtRows = new JTextField(40);
    final JTextField txtCols = new JTextField(40);
    final JTextField txtColWidth = new JTextField(40);
    final JTextField txtRowHeight = new JTextField(40);
    private JPanel lblTextPanel = new JPanel();
    private Model model;

    public BootstrapPanel(Model model) {
	this.model = model;
    }

    public void buildUI() {
	SpringLayout layout = new SpringLayout();

	setLayout(new BorderLayout());

	lblTextPanel.setLayout(layout);
	createAndAddLabel("Rijen", txtRows);
	createAndAddLabel("Kolommen", txtCols);
	createAndAddLabel("Breedte", txtColWidth);
	createAndAddLabel("Hoogte", txtRowHeight);

	SpringUtilities.makeCompactGrid(lblTextPanel, 4, 2, // rows, cols
		6, 6, // initX, initY
		6, 6); // xPad, yPad

	initializeWithDefaultData();

	add(lblTextPanel, BorderLayout.CENTER);

	JButton btnStart = new JButton("Start");
	btnStart.addActionListener(new GardenInitializeAction(model, this));
	add(btnStart, BorderLayout.SOUTH);
    }

    private void createAndAddLabel(String labelText, JTextField textField) {
	JLabel lblRows = new JLabel(labelText, JLabel.TRAILING);
	lblTextPanel.add(lblRows);
	lblRows.setLabelFor(textField);
	lblTextPanel.add(textField);

    }

    private void initializeWithDefaultData() {
	Calculator calculator = model.getCalculator();
	txtRows.setText(calculator.getAmountOfRows() + "");
	txtCols.setText(calculator.getAmountOfColumns() + "");
	txtColWidth.setText(calculator.getColumnWidth() + "");
	txtRowHeight.setText(calculator.getRowHeight() + "");
    }

    public Calculator getCalculator() {
	return new Calculator(getAmountOfCols(txtCols), getAmountOfRows(txtRows), getWidth(txtColWidth),
		getHeight(txtRowHeight));
    }

    private int getHeight(JTextField txtRowHeight) {
	return getIntValue(txtRowHeight.getText());
    }

    private int getIntValue(String text) {
	int intValue;
	try {
	    intValue = Integer.parseInt(text);
	} catch (NumberFormatException ex) {
	    throw new IllegalArgumentException("illegal argument");
	}
	return intValue;
    }

    public void setModel(Model model) {
	this.model = model;
	initializeWithDefaultData();
    }

    private int getWidth(JTextField txtColWidth) {
	return getIntValue(txtColWidth.getText());
    }

    private int getAmountOfRows(JTextField txtRows) {
	return getIntValue(txtRows.getText());

    }

    private int getAmountOfCols(JTextField txtCols) {
	return getIntValue(txtCols.getText());
    }

}
