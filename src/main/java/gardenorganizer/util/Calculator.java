/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gardenorganizer.util;

import java.awt.Point;

/**
 * 
 * @author Yannis
 */
public class Calculator {

    private int amountOfColumns, amountOfRows;
    private int columnWidth, rowHeight;

    public Calculator(int amountOfColumns, int amountOfRows, int columnWidth,
	    int rowHeight) {
	this.amountOfColumns = amountOfColumns;
	this.amountOfRows = amountOfRows;
	this.columnWidth = columnWidth;
	this.rowHeight = rowHeight;
    }

    public int getAmountOfColumns() {
	return amountOfColumns;
    }

    public int getAmountOfRows() {
	return amountOfRows;
    }

    public int getColumnWidth() {
	return columnWidth;
    }

    public int getRowHeight() {
	return rowHeight;
    }

    public Point calculateTRForCoordinates(int x, int y) {

	int pointX = x;
	int pointY = y;

	while (pointX % (columnWidth) != 0) {
	    pointX++;
	}

	while (pointY % rowHeight != 0) {
	    pointY--;
	}

	return new Point(pointX, pointY);
    }

    public Point calculateTLForCoordinates(int x, int y) {
	int pointX = x;
	int pointY = y;

	while (pointX % (columnWidth) != 0) {
	    pointX--;
	}

	while (pointY % rowHeight != 0) {
	    pointY--;
	}

	return new Point(pointX, pointY);
    }

    public Point calculateLRForCoordinates(int x, int y) {

	int pointX = x;
	int pointY = y;

	while (pointX % (columnWidth) != 0) {
	    pointX++;
	}

	while (pointY % rowHeight != 0) {
	    pointY++;
	}

	return new Point(pointX, pointY);
    }

    public Point calculateLLForCoordinates(int x, int y) {

	int pointX = x;
	int pointY = y;

	while (pointX % (columnWidth) != 0) {
	    pointX--;
	}

	while (pointY % rowHeight != 0) {
	    pointY++;
	}

	return new Point(pointX, pointY);
    }

    public int getColumnNumberForCoordinates(int x, int y) {
	int columnNumber = 0;
	int lrX = calculateLRForCoordinates(x, y).x;

	columnNumber = lrX / columnWidth - 1;
	return columnNumber;
    }

    public int getRowNumberForCoordinates(int x, int y) {
	int rowNumber = 0;
	int lrY = calculateLRForCoordinates(x, y).y;

	rowNumber = lrY / (rowHeight) - 1;

	return rowNumber;
    }

    public int getBoxNumberForCoordinates(int x, int y) {
	int boxNumber = 0;
	boxNumber = (getRowNumberForCoordinates(x, y) * amountOfColumns)
		+ getColumnNumberForCoordinates(x, y);
	return boxNumber;
    }

    public boolean hasLeftNeighbor(int boxNumber) {
	if ((boxNumber) % (amountOfColumns) == 0) {
	    return false;
	}

	return true;
    }

    public boolean hasRightNeighbor(int boxNumber) {
	if ((boxNumber + 1) % (amountOfColumns) == 0) {
	    return false;
	}
	return true;
    }

    public boolean hasTopNeighbor(int boxNumber) {
	if (boxNumber >= amountOfColumns) {
	    return true;
	}
	return false;
    }

    public boolean hasBottomNeighbor(int boxNumber) {
	if (boxNumber + amountOfColumns >= ((amountOfRows) * (amountOfColumns))) {
	    return false;
	}
	return true;
    }

    public Point calculateTRForBoxNumber(int boxNumber) {
	int columnNumber = getColumnNumberFromBoxNumber(boxNumber);
	int rowNumber = getRowNumberFromBoxNumber(boxNumber);

	int pointX = (columnNumber + 1) * columnWidth;

	int pointY = rowNumber * rowHeight;

	return new Point(pointX, pointY);
    }

    public Point calculateTLForBoxNumber(int boxNumber) {
	int columnNumber = getColumnNumberFromBoxNumber(boxNumber);
	int rowNumber = getRowNumberFromBoxNumber(boxNumber);

	int pointX = (columnNumber) * columnWidth;

	int pointY = rowNumber * rowHeight;

	return new Point(pointX, pointY);
    }

    public Point calculateLLForBoxNumber(int boxNumber) {
	int columnNumber = getColumnNumberFromBoxNumber(boxNumber);
	int rowNumber = getRowNumberFromBoxNumber(boxNumber);

	int pointX = (columnNumber) * columnWidth;

	int pointY = (rowNumber + 1) * rowHeight;

	return new Point(pointX, pointY);
    }

    public Point calculateLRForBoxNumber(int boxNumber) {
	int columnNumber = getColumnNumberFromBoxNumber(boxNumber);
	int rowNumber = getRowNumberFromBoxNumber(boxNumber);

	int pointX = (columnNumber + 1) * columnWidth;

	int pointY = (rowNumber + 1) * rowHeight;

	return new Point(pointX, pointY);
    }

    public int getColumnNumberFromBoxNumber(int boxNumber) {
	return boxNumber % amountOfColumns;
    }

    public int getRowNumberFromBoxNumber(int boxNumber) {
	return boxNumber / amountOfColumns;
    }

    public Point getPointInBoxNumber(int boxNumber) {
	int columnNumber = getColumnNumberFromBoxNumber(boxNumber);
	int rowNumber = getRowNumberFromBoxNumber(boxNumber);
	int x = columnWidth / 2 + columnNumber * columnWidth;
	int y = rowHeight / 2 + rowNumber * rowHeight;
	return new Point(x, y);
    }
}
