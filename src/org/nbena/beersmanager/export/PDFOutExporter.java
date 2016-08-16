package org.nbena.beersmanager.export;
//
//import java.awt.Color;
//import java.io.OutputStream;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.nbena.beersmanager.coreclasses.Beer;
//import org.nbena.beersmanager.coreclasses.Brewery;
//import org.nbena.beersmanager.coreclasses.Style;
//
//import be.quodlibet.boxable.BaseTable;
//import be.quodlibet.boxable.Cell;
//import be.quodlibet.boxable.Row;
//
public class PDFOutExporter{
//	
//	private PDDocument doc;
//	private float margin;
//	private Cell<PDPage> cellIntestation[];
//	private Cell<PDPage> cells[];
//	
//	public PDFExporter() {
//		super();
//		doc=new PDDocument();
//		margin=10;
//	}
//
//	
//	
//	
//
//	@Override
//	public void writeBeer(List<Beer> beers, OutputStream out) throws Exception {
//		
//		PDPage page=new PDPage();
//		doc.addPage(page);
//		float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
//		
//		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
//		boolean drawContent = true;
//		float yStart = yStartNewPage;
//		float bottomMargin = 70;
//		BaseTable table = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
//				drawContent);
//		
//		Row<PDPage> intestation=table.createRow(15f);
//		cellIntestation=new Cell[15];
//		for(int j=0;j<15;j++){
//			cellIntestation[j]=intestation.createCell(100, "temp value that will be changed");
//			cellIntestation[j].setFont(PDType1Font.HELVETICA_BOLD);
//			cellIntestation[j].setFillColor(Color.ORANGE);
//			cellIntestation[j].setTextColor(Color.WHITE);
//		}
//		
//		cellIntestation[0].setText(beerIntestationMap.get("Brewery name"));
//		cellIntestation[1].setText(beerIntestationMap.get("Brewery country"));
//		cellIntestation[2].setText(beerIntestationMap.get("Brewery town"));		
//		cellIntestation[3].setText(beerIntestationMap.get("Beer name"));
//		cellIntestation[4].setText(beerIntestationMap.get("Fermentation"));
//		cellIntestation[5].setText(beerIntestationMap.get("Style"));
//		cellIntestation[6].setText(beerIntestationMap.get("Style sub category"));
//		cellIntestation[7].setText(beerIntestationMap.get("Style country"));
//		cellIntestation[8].setText(beerIntestationMap.get("Trappist"));	
//		cellIntestation[9].setText(beerIntestationMap.get("Color"));
//		cellIntestation[10].setText(beerIntestationMap.get("Mark"));
//		cellIntestation[11].setText(beerIntestationMap.get("Alcool"));
//		cellIntestation[12].setText(beerIntestationMap.get("Place tried"));
//		cellIntestation[13].setText(beerIntestationMap.get("Stars"));
//		cellIntestation[14].setText(beerIntestationMap.get("Description"));
//		
//		table.addHeaderRow(intestation);
//		
//	}
//
//	@Override
//	public void writeBeerSpecialClass(List<Beer> beers, OutputStream out) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void writeStyle(List<Style> styles, OutputStream out) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
}
