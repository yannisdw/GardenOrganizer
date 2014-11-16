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
import gardenorganizer.model.persistence.generated.ObjectFactory;
import gardenorganizer.model.persistence.generated.TileType;
import gardenorganizer.util.Calculator;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Exporter {

    private BoxGridType boxGridType;
    private SourceDirectory sourceDirectory;
    private Model model;

    public Exporter(Model model, SourceDirectory sourceDirectory) {
	this.sourceDirectory = sourceDirectory;
	this.model = model;
    }

    public void writeToFile(String fileName) {
	String filePath = sourceDirectory.createPathForFileName(fileName);

	Archipelago archipelago = model.getArchipelago();
	archipelago.setName(fileName);
	buildBoxGridType(archipelago, model.getCalculator());
	JAXBContext jc;
	try {
	    jc = JAXBContext.newInstance("gardenorganizer.model.persistence.generated");
	    Marshaller m = jc.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    m.marshal(boxGridType, new FileOutputStream(filePath));
	} catch (JAXBException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }

    private void buildBoxGridType(Archipelago archipelago, Calculator calculator) {
	ObjectFactory factory = new ObjectFactory();
	boxGridType = factory.createBoxGridType();

	createArchipelago(archipelago, factory);
	createDimensions(calculator, factory);
    }

    private void createArchipelago(Archipelago archipelago, ObjectFactory factory) {
	ArchipelagoType archipelagor = factory.createArchipelagoType();
	archipelagor.setName(archipelago.getName());
	buildIslands(archipelago.getIslandIterator(), archipelagor, factory);
	boxGridType.setArchipelago(archipelagor);
    }

    private void createDimensions(Calculator calculator, ObjectFactory factory) {
	DimensionType dimension = factory.createDimensionType();
	dimension.setAmountOfColumns(calculator.getAmountOfColumns());
	dimension.setAmountOfRows(calculator.getAmountOfRows());
	boxGridType.setDimension(dimension);
    }

    private void buildIslands(Iterator<Island> islandIterator, ArchipelagoType archipelago, ObjectFactory factory) {
	while (islandIterator.hasNext()) {
	    Island i = islandIterator.next();
	    IslandType island = factory.createIslandType();
	    island.setName(i.getIslandName());
	    island.setColor(buildColor(i.getColor(), factory));
	    addTiles(i.getIslandTiles(), island, factory);
	    archipelago.getIslandList().add(island);
	}
    }

    private ColorType buildColor(Color color, ObjectFactory factory) {
	ColorType colorType = factory.createColorType();
	colorType.setBlue(color.getBlue());
	colorType.setGreen(color.getGreen());
	colorType.setRed(color.getRed());
	return colorType;
    }

    private void addTiles(Collection<IslandTile> islandTiles, IslandType island, ObjectFactory factory) {
	for (IslandTile tile : islandTiles) {
	    island.getTileList().add(createTileType(tile, factory));
	}
    }

    private TileType createTileType(IslandTile tile, ObjectFactory factory) {
	TileType tileType = factory.createTileType();
	tileType.setNumber(tile.getTileNumber() + "");
	return tileType;
    }

}
