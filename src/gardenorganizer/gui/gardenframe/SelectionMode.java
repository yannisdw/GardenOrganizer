package gardenorganizer.gui.gardenframe;

public enum SelectionMode {
    ADD_TO_ISLAND("Voeg toe"), REMOVE_FROM_ISLAND("Verwijder");

    private String description;

    private SelectionMode(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

}
