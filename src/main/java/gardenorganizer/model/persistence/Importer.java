package gardenorganizer.model.persistence;

import gardenorganizer.gui.Model;
import gardenorganizer.model.Archipelago;
import gardenorganizer.model.Island;
import gardenorganizer.model.IslandTile;
import gardenorganizer.model.persistence.generated.ArchipelagoType;
import gardenorganizer.model.persistence.generated.BoxGridType;
import gardenorganizer.model.persistence.generated.ColorType;
import gardenorganizer.model.persistence.generated.DimensionType;
import gardenorganizer.model.persistence.generated.IslandType;
import gardenorganizer.model.persistence.generated.TileType;
import gardenorganizer.util.Calculator;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Importer {

    private String pathToImport;
    BoxGridType boxGrid = null;

    public Importer(String pathToImport) {
	this.pathToImport = pathToImport;
    }

    public Model importFile() {

	JAXBContext jc;
	try {
	    jc = JAXBContext.newInstance("gardenorganizer.model.persistence.generated");
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    boxGrid = (BoxGridType) unmarshaller.unmarshal(new FileInputStream(pathToImport));
	} catch (JAXBException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return createModel(boxGrid);

    }

    private Model createModel(BoxGridType boxGrid) {
	Model m = new Model();
	Archipelago archipelago = new Archipelago();
	archipelago.setName(boxGrid.getArchipelago().getName());
	m.setArchipelago(archipelago);
	m.setCalculator(createCalculator(boxGrid));
	return m;
    }

    public Archipelago getArchipelago() {
	return createArchipelago(boxGrid);
    }

    private Calculator createCalculator(BoxGridType boxGrid) {
	DimensionType dimension = boxGrid.getDimension();
	int amountOfCols = Integer.parseInt(dimension.getAmountOfColumns() + "");
	int amountOfRows = Integer.parseInt(dimension.getAmountOfRows() + "");

	Calculator c = new Calculator(amountOfCols, amountOfRows, 50, 50);

	return c;
    }

    private Archipelago createArchipelago(BoxGridType boxGrid) {
	Archipelago arch = new Archipelago();
	ArchipelagoType archipelago = boxGrid.getArchipelago();
	List<IslandType> islandList = archipelago.getIslandList();
	for (IslandType island : islandList) {
	    arch.addIsland(createIsland(island));
	}

	return arch;
    }

    private Island createIsland(IslandType island) {
	Island island2 = new Island(island.getName(), createColor(island.getColor()));
	for (TileType tileType : island.getTileList()) {
	    island2.addIslandTile(createTile(tileType));
	}
	return island2;
    }

    private IslandTile createTile(TileType tileType) {
	return new IslandTile(Integer.parseInt(tileType.getNumber()), 50, 50);
    }

    private Color createColor(ColorType color) {
	return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }
}
