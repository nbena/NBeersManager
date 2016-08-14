package org.nbena.beersmanager.export;

import java.io.FileOutputStream;



import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSpecialClass;

public class MSExcelNewExporter extends Exporter{


	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Cell[] cellIntestation;
	private Cell[] cells;
	
	public MSExcelNewExporter() {
		super();
	}

	@Override
	public void writeBeer(List<Beer> beers, OutputStream out) throws Exception {
		if(out instanceof FileOutputStream == false)
			throw new Exception("OutputStream is not a valid instace of FileOutputStream");
		
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Beers");
		
		//intestation
		XSSFRow rowIntestation=sheet.createRow(0);
		cellIntestation=new Cell[15];
		for(int j=0;j<15;j++){
			cellIntestation[j]=rowIntestation.createCell(j);
		}
		
//		cellIntestation[0].setCellValue(beerIntestationMap.get("Brewery name"));
//		cellIntestation[1].setCellValue(beerIntestationMap.get("Brewery country"));
//		cellIntestation[2].setCellValue(beerIntestationMap.get("Brewery town"));		
//		cellIntestation[3].setCellValue(beerIntestationMap.get("Beer name"));
//		cellIntestation[4].setCellValue(beerIntestationMap.get("Fermentation"));
//		cellIntestation[5].setCellValue(beerIntestationMap.get("Style"));
//		cellIntestation[6].setCellValue(beerIntestationMap.get("Style sub category"));
//		cellIntestation[7].setCellValue(beerIntestationMap.get("Style country"));
//		cellIntestation[8].setCellValue(beerIntestationMap.get("Trappist"));	
//		cellIntestation[9].setCellValue(beerIntestationMap.get("Color"));
//		cellIntestation[10].setCellValue(beerIntestationMap.get("Mark"));
//		cellIntestation[11].setCellValue(beerIntestationMap.get("Alcool"));
//		cellIntestation[12].setCellValue(beerIntestationMap.get("Place tried"));
//		cellIntestation[13].setCellValue(beerIntestationMap.get("Stars"));
//		cellIntestation[14].setCellValue(beerIntestationMap.get("Description"));
		
		
		cellIntestation[0].setCellValue(Exporter.BEER_BREWERY_NAME);
		cellIntestation[1].setCellValue(Exporter.BEER_BREWERY_COUNTRY);
		cellIntestation[2].setCellValue(Exporter.BEER_BREWERY_TOWN);		
		cellIntestation[3].setCellValue(Exporter.BEER_NAME);
		cellIntestation[4].setCellValue(Exporter.BEER_FERMENTATION);
		cellIntestation[5].setCellValue(Exporter.BEER_STYLE_MAIN);
		cellIntestation[6].setCellValue(Exporter.BEER_STYLE_SUBCATEGORY);
		cellIntestation[7].setCellValue(Exporter.BEER_STYLE_COUNTRY);
		cellIntestation[8].setCellValue(Exporter.BEER_BREWERY_TRAPPIST);	
		cellIntestation[9].setCellValue(Exporter.BEER_COLOR);
		cellIntestation[10].setCellValue(Exporter.BEER_MARK);
		cellIntestation[11].setCellValue(Exporter.BEER_ALCOOL);
		cellIntestation[12].setCellValue(Exporter.BEER_PLACE_TRIED);
		cellIntestation[13].setCellValue(Exporter.BEER_STARS);
		cellIntestation[14].setCellValue(Exporter.BEER_DESCRIPTION);
		
		
		
		//content
		cells=new Cell[15];
		int i=1;
		//for(int i=0/*, j=0*/;i<=beers.size();i++){
		for(Beer beer: beers){
			XSSFRow row=sheet.createRow(i);
			
			for(int f=0;f<15;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(beer.getBrewery().getName());
			cells[1].setCellValue(beer.getBrewery().getCountry());
			cells[2].setCellValue(beer.getBrewery().getTown());
			cells[3].setCellValue(beer.getName());
			cells[4].setCellValue(beer.getFermentation().toFirstUpperCase());
			cells[5].setCellValue(beer.getStyle().getStyleMainName());
			cells[6].setCellValue(beer.getStyle().getStyleSubCategory());
			cells[7].setCellValue(beer.getStyle().getStyleCountryOrigin());
			cells[8].setCellValue(beer.getBrewery().isAuthenticTrappist());
			cells[9].setCellValue(beer.getColor());
			cells[10].setCellValue(beer.getMark());
			cells[11].setCellValue(beer.getAlcool());
			cells[12].setCellValue(beer.getPlaceTried());
			cells[13].setCellValue(beer.getNumberOfStars());
			cells[14].setCellValue(beer.getDescription());
			
			i++;
		}
		
		workbook.write(out);
		out.close();
	}



	@Override
	public void writeStyle(List<Style> styles, OutputStream out) throws Exception {		if(out instanceof FileOutputStream == false)
		throw new Exception("OutputStream is not a valid instace of FileOutputStream");
	
	workbook=new XSSFWorkbook();
	sheet=workbook.createSheet("Style");
	
	//intestation
	Row rowIntestation=sheet.createRow(0);
	cellIntestation=new Cell[5];
	for(int j=0;j<5;j++){
		cellIntestation[j]=rowIntestation.createCell(j);
	}
	
//	cellIntestation[0].setCellValue(styleIntestationMap.get("Style name"));
//	cellIntestation[1].setCellValue(styleIntestationMap.get("Style subcategory"));
//	cellIntestation[2].setCellValue(styleIntestationMap.get("Fermentation"));
//	cellIntestation[3].setCellValue(styleIntestationMap.get("Style country"));
//	cellIntestation[4].setCellValue(styleIntestationMap.get("Description"));
	
	cellIntestation[0].setCellValue(Exporter.STYLE_NAME);
	cellIntestation[1].setCellValue(Exporter.STYLE_SUB);
	cellIntestation[2].setCellValue(Exporter.STYLE_FERMENTATION);
	cellIntestation[3].setCellValue(Exporter.STYLE_COUNTRY);
	cellIntestation[4].setCellValue(Exporter.STYLE_DESCRIPTION);
	
	cells=new Cell[5];
	int i=1;
	for(Style s: styles){
		Row row=sheet.createRow(i);
		
		for(int f=0;f<5;f++){
			cells[f]=row.createCell(f);
		}
		
		cells[0].setCellValue(s.getStyleMainName()/*==null ? "Unknown" : s.getStyleMainName()*/);
		cells[1].setCellValue(s.getStyleSubCategory()/*==? ""*/);
		cells[2].setCellValue(s.getFermentation().toFirstUpperCase());
		cells[3].setCellValue(s.getStyleCountryOrigin()/*==null ? "Unknown" : s.getStyleCountryOrigin()*/);
		cells[4].setCellValue(s.getDescription());
		
		i++;
	}
	workbook.write(out);
	out.close();
		
	}

	@Override
	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception {
		if(out instanceof FileOutputStream == false)
			throw new Exception("OutputStream is not a valid instace of FileOutputStream");
		
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Brewery");
		
		//intestation
		Row rowIntestation=sheet.createRow(0);
		cellIntestation=new Cell[5];
		for(int j=0;j<5;j++){
			cellIntestation[j]=rowIntestation.createCell(j);
		}
		
//		cellIntestation[0].setCellValue(breweryIntestationMap.get("Brewery name"));
//		cellIntestation[1].setCellValue(breweryIntestationMap.get("Brewery town"));
//		cellIntestation[2].setCellValue(breweryIntestationMap.get("Brewery country"));
//		cellIntestation[3].setCellValue(breweryIntestationMap.get("Brewery website"));
//		cellIntestation[4].setCellValue(breweryIntestationMap.get("Brewery description"));

		cellIntestation[0].setCellValue(Exporter.BREWERY_NAME);
		cellIntestation[1].setCellValue(Exporter.BREWERY_TOWN);
		cellIntestation[2].setCellValue(Exporter.BREWERY_COUNTRY);
		cellIntestation[3].setCellValue(Exporter.BREWERY_WEBSITE);
		cellIntestation[4].setCellValue(Exporter.BREWERY_DESCRIPTION);
		cells=new Cell[5];
		int i=1;
		for(Brewery brewery: breweries){
			Row row=sheet.createRow(i);
			
			for(int f=0;f<5;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(brewery.getName());
			cells[1].setCellValue(brewery.getTown());
			cells[2].setCellValue(brewery.getCountry());
			cells[3].setCellValue(brewery.getWebsite());
			cells[4].setCellValue(brewery.getDescription());
			
			i++;
		}
		workbook.write(out);
		out.close();
		
	}

	@Override
	public void writeBeerSpecialClass(List<BeerJSONSpecialClass> beers, OutputStream out) throws Exception {
		if(out instanceof FileOutputStream == false)
			throw new Exception("OutputStream is not a valid instace of FileOutputStream");
		
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Beers");
		
		//intestation
		Row rowIntestation=sheet.createRow(0);
		cellIntestation=new Cell[15];
		for(int j=0;j<15;j++){
			cellIntestation[j]=rowIntestation.createCell(j);
		}
		
		cellIntestation[0].setCellValue(Exporter.BEER_BREWERY_NAME);			
		cellIntestation[1].setCellValue(Exporter.BEER_NAME);
		cellIntestation[2].setCellValue(Exporter.BEER_STYLE_MAIN);
		cellIntestation[3].setCellValue(Exporter.BEER_STYLE_SUBCATEGORY);
		cellIntestation[4].setCellValue(Exporter.BEER_COLOR);
		cellIntestation[5].setCellValue(Exporter.BEER_MARK);
		cellIntestation[6].setCellValue(Exporter.BEER_ALCOOL);
		cellIntestation[7].setCellValue(Exporter.BEER_PLACE_TRIED);
		cellIntestation[8].setCellValue(Exporter.BEER_STARS);
		cellIntestation[9].setCellValue(Exporter.BEER_DESCRIPTION);	

		
		//content
		cells=new Cell[15];
		int i=1;
		for(BeerJSONSpecialClass beer: beers){
			Row row=sheet.createRow(i);
			
			for(int f=0;f<15;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(beer.getBreweryName());
			cells[1].setCellValue(beer.getName());
			cells[2].setCellValue(beer.getStyleMainName());
			cells[3].setCellValue(beer.getStyleSubcategory());
			cells[4].setCellValue(beer.getColor());
			cells[5].setCellValue(beer.getMark());
			cells[6].setCellValue(beer.getAlcool());
			cells[7].setCellValue(beer.getPlaceTried());
			cells[8].setCellValue(beer.getNumberOfStars());
			cells[9].setCellValue(beer.getDescription());
			
			
			i++;
		}
		
		workbook.write(out);
		out.close();
	}

}
