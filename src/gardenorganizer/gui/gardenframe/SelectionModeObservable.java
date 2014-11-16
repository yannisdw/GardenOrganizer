package gardenorganizer.gui.gardenframe;

import java.util.ArrayList;
import java.util.List;

public class SelectionModeObservable {

    private List<SelectionModeObserver> observers = new ArrayList<SelectionModeObserver>();

    private SelectionMode currentSelectionMode;

    public SelectionModeObservable() {
	currentSelectionMode = SelectionMode.ADD_TO_ISLAND;
    }

    public void addObserver(SelectionModeObserver arg0) {
	observers.add(arg0);
    }

    public void setState(SelectionMode mode) {
	currentSelectionMode = mode;
	notifyObservers();
    }

    private void notifyObservers() {
	for (SelectionModeObserver o : observers) {
	    o.update(currentSelectionMode);
	}
    }

}
