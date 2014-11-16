package gardenorganizer.export;

import gardenorganizer.model.Archipelago;
import gardenorganizer.model.Island;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFPrinter {

    private static final String homeDir = System.getProperty("user.home");

    private static final String FILE = homeDir + "/TuinPrint.pdf";

    private Archipelago archipelago;

    public PDFPrinter() {
	this(null);
    }

    public PDFPrinter(Archipelago archipelago) {
	this.archipelago = archipelago;
    }

    public void createPDF() {
	Document document = new Document(PageSize.A4.rotate());
	try {
	    PdfWriter.getInstance(document, new FileOutputStream(FILE));
	    document.open();
	    addTable(document);
	    addLegend(document);
	    document.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    private void addLegend(Document document) {
	document.newPage();
	Paragraph legend = new Paragraph();

	PdfPTable table = new PdfPTable(2);
	int widths[] = { 10, 50 };
	try {
	    table.setWidths(widths);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
	Iterator<Island> islandIterator = archipelago.getIslandIterator();
	while (islandIterator.hasNext()) {
	    Island i = islandIterator.next();
	    Color color = i.getColor();
	    String name = i.getIslandName();
	    PdfPCell cell = new PdfPCell();
	    cell.setBackgroundColor(new BaseColor(color));
	    table.addCell(cell);
	    cell = new PdfPCell(new Phrase(name));
	    table.addCell(cell);
	}
	legend.add(table);
	try {
	    document.add(new Paragraph("Legende " + archipelago.getName()));
	    document.add(new Paragraph(" "));
	    document.add(legend);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public void addTable(Document document) {
	Paragraph title = new Paragraph(archipelago.getName());

	Paragraph preface = new Paragraph();
	PdfPTable table = new PdfPTable(18);
	int[] widths = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	try {
	    table.setWidths(widths);
	} catch (DocumentException e1) {
	    e1.printStackTrace();
	}
	int currentCellNumber = 0;
	for (int row = 0; row < 10; row++) {
	    for (int col = 0; col < 18; col++) {
		Island island = archipelago.getIslandWhichContainsTileNumber(currentCellNumber);
		PdfPCell cell = new PdfPCell(new Phrase(""));
		if (island != null) {
		    cell = new PdfPCell(new Phrase("" + currentCellNumber));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    Color colorToPaintBoxIn = island.getColor();
		    cell.setBackgroundColor(new BaseColor(colorToPaintBoxIn));
		}
		cell.setFixedHeight(40);
		table.addCell(cell);
		currentCellNumber++;
	    }
	}

	preface.add(table);
	try {
	    document.add(title);
	    document.add(new Paragraph(" "));
	    document.add(preface);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String args[]) {
	new PDFPrinter().createPDF();
    }
}
