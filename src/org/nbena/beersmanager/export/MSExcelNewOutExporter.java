/*   NBeersManager: manages what you drink.

    Copyright (C) 2016  Nicola Bena

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    */
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
import org.nbena.beersmanager.json.coreclasses.BeerJSONSaveSpecialClass;

public class MSExcelNewOutExporter extends OutExporter{


	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Cell[] cellIntestation;
	private Cell[] cells;
	
	public MSExcelNewOutExporter() {
		super();
	}

	@Override
	public void writeBeer(List<Beer> beers, OutputStream out, boolean writeTotalPrice) throws Exception {
		if(out instanceof FileOutputStream == false)
			throw new Exception("OutputStream is not a valid instace of FileOutputStream");
		
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Beers");
		
		//intestation
		XSSFRow rowIntestation=sheet.createRow(0);
//		cellIntestation=new Cell[15];
//		for(int j=0;j<15;j++){
//			cellIntestation[j]=rowIntestation.createCell(j);
//		}
//		
//		
//		
//		cellIntestation[0].setCellValue(OutExporter.BEER_BREWERY_NAME);
//		cellIntestation[1].setCellValue(OutExporter.BEER_BREWERY_COUNTRY);
//		cellIntestation[2].setCellValue(OutExporter.BEER_BREWERY_TOWN);		
//		cellIntestation[3].setCellValue(OutExporter.BEER_NAME);
//		cellIntestation[4].setCellValue(OutExporter.BEER_FERMENTATION);
//		cellIntestation[5].setCellValue(OutExporter.BEER_STYLE_MAIN);
//		cellIntestation[6].setCellValue(OutExporter.BEER_STYLE_SUBCATEGORY);
//		cellIntestation[7].setCellValue(OutExporter.BEER_STYLE_COUNTRY);
//		cellIntestation[8].setCellValue(OutExporter.BEER_BREWERY_TRAPPIST);	
//		cellIntestation[9].setCellValue(OutExporter.BEER_COLOR);
//		cellIntestation[10].setCellValue(OutExporter.BEER_MARK);
//		cellIntestation[11].setCellValue(OutExporter.BEER_ALCOOL);
//		cellIntestation[12].setCellValue(OutExporter.BEER_PLACE_TRIED);
//		cellIntestation[13].setCellValue(OutExporter.BEER_STARS);
//		cellIntestation[14].setCellValue(OutExporter.BEER_DESCRIPTION);
//		
//		
//		
//		//content
//		cells=new Cell[15];
//		int i=1;
//		//for(int i=0/*, j=0*/;i<=beers.size();i++){
//		for(Beer beer: beers){
//			XSSFRow row=sheet.createRow(i);
//			
//			for(int f=0;f<15;f++){
//				cells[f]=row.createCell(f);
//			}
//			
//			cells[0].setCellValue(beer.getBrewery().getName());
//			cells[1].setCellValue(beer.getBrewery().getCountry());
//			cells[2].setCellValue(beer.getBrewery().getTown());
//			cells[3].setCellValue(beer.getName());
//			cells[4].setCellValue(beer.getFermentation().toFirstUpperCase());
//			cells[5].setCellValue(beer.getStyle().getStyleMainName());
//			cells[6].setCellValue(beer.getStyle().getStyleSubCategory());
//			cells[7].setCellValue(beer.getStyle().getStyleCountryOrigin());
//			cells[8].setCellValue(beer.getBrewery().isAuthenticTrappist());
////			cells[9].setCellValue(beer.getColor());
//			cells[10].setCellValue(beer.getMark());
//			cells[11].setCellValue(beer.getAlcool());
//			cells[12].setCellValue(beer.getPlaceTried());
//			cells[13].setCellValue(beer.getNumberOfStars());
//			cells[14].setCellValue(beer.getDescription());
//			
//			i++;
//		}
		
		
		cellIntestation=new Cell[15];
		for(int j=0;j<15;j++){
			cellIntestation[j]=rowIntestation.createCell(j);
		}
		
		
		
		cellIntestation[0].setCellValue(OutExporter.BEER_BREWERY_NAME);
		cellIntestation[1].setCellValue(OutExporter.BEER_BREWERY_COUNTRY);
		cellIntestation[2].setCellValue(OutExporter.BEER_BREWERY_TOWN);		
		cellIntestation[3].setCellValue(OutExporter.BEER_NAME);
		cellIntestation[4].setCellValue(OutExporter.BEER_FERMENTATION);
		cellIntestation[5].setCellValue(OutExporter.BEER_STYLE_MAIN);
		cellIntestation[6].setCellValue(OutExporter.BEER_STYLE_SUBCATEGORY);
		cellIntestation[7].setCellValue(OutExporter.BEER_STYLE_COUNTRY);
		cellIntestation[8].setCellValue(OutExporter.BEER_BREWERY_TRAPPIST);	
//		cellIntestation[9].setCellValue(OutExporter.BEER_COLOR);
		cellIntestation[9].setCellValue(OutExporter.BEER_MARK);
		cellIntestation[10].setCellValue(OutExporter.BEER_ALCOOL);
		cellIntestation[11].setCellValue(OutExporter.BEER_PLACE_TRIED);
		cellIntestation[12].setCellValue(OutExporter.BEER_STARS);
		cellIntestation[13].setCellValue(OutExporter.BEER_DESCRIPTION);
		
		
		
		//content
		cells=new Cell[14];
		int i=1;
		//for(int i=0/*, j=0*/;i<=beers.size();i++){
		for(Beer beer: beers){
			XSSFRow row=sheet.createRow(i);
			
			for(int f=0;f<14;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(beer.getBrewery().getBreweryName());
			cells[1].setCellValue(beer.getBrewery().getCountry());
			cells[2].setCellValue(beer.getBrewery().getTown());
			cells[3].setCellValue(beer.getName());
			cells[4].setCellValue(beer.getFermentation().toFirstUpperCase());
			cells[5].setCellValue(beer.getStyle().getStyleMainName());
			cells[6].setCellValue(beer.getStyle().getStyleSubCategory());
			cells[7].setCellValue(beer.getStyle().getStyleCountryOrigin());
			cells[8].setCellValue(beer.getBrewery().isAuthenticTrappist());
//			cells[9].setCellValue(beer.getColor());
			cells[9].setCellValue(beer.getMark());
			cells[10].setCellValue(beer.getAlcool());
			cells[11].setCellValue(beer.getPlaceTried());
			cells[12].setCellValue(beer.getNumberOfStars());
			cells[13].setCellValue(beer.getDescription());
			
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
	
	cellIntestation[0].setCellValue(OutExporter.STYLE_NAME);
	cellIntestation[1].setCellValue(OutExporter.STYLE_SUB);
	cellIntestation[2].setCellValue(OutExporter.STYLE_FERMENTATION);
	cellIntestation[3].setCellValue(OutExporter.STYLE_COUNTRY);
	cellIntestation[4].setCellValue(OutExporter.STYLE_DESCRIPTION);
	
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

		cellIntestation[0].setCellValue(OutExporter.BREWERY_NAME);
		cellIntestation[1].setCellValue(OutExporter.BREWERY_TOWN);
		cellIntestation[2].setCellValue(OutExporter.BREWERY_COUNTRY);
		cellIntestation[3].setCellValue(OutExporter.BREWERY_WEBSITE);
		cellIntestation[4].setCellValue(OutExporter.BREWERY_DESCRIPTION);
		cells=new Cell[5];
		int i=1;
		for(Brewery brewery: breweries){
			Row row=sheet.createRow(i);
			
			for(int f=0;f<5;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(brewery.getBreweryName());
			cells[1].setCellValue(brewery.getTown());
			cells[2].setCellValue(brewery.getCountry());
			cells[3].setCellValue(brewery.getWebsite());
			cells[4].setCellValue(brewery.getBreweryDescription());
			
			i++;
		}
		workbook.write(out);
		out.close();
		
	}

	@Override
	public void writeBeerSpecialClass(List<BeerJSONSaveSpecialClass> beers, OutputStream out) throws Exception {
		if(out instanceof FileOutputStream == false)
			throw new Exception("OutputStream is not a valid instace of FileOutputStream");
		
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Beers");
		
		//intestation
		Row rowIntestation=sheet.createRow(0);
//		cellIntestation=new Cell[15];
		cellIntestation=new Cell[9];
//		for(int j=0;j<15;j++){
//			cellIntestation[j]=rowIntestation.createCell(j);
//		}
//		
//		cellIntestation[0].setCellValue(OutExporter.BEER_BREWERY_NAME);			
//		cellIntestation[1].setCellValue(OutExporter.BEER_NAME);
//		cellIntestation[2].setCellValue(OutExporter.BEER_STYLE_MAIN);
//		cellIntestation[3].setCellValue(OutExporter.BEER_STYLE_SUBCATEGORY);
//		cellIntestation[4].setCellValue(OutExporter.BEER_COLOR);
//		cellIntestation[5].setCellValue(OutExporter.BEER_MARK);
//		cellIntestation[6].setCellValue(OutExporter.BEER_ALCOOL);
//		cellIntestation[7].setCellValue(OutExporter.BEER_PLACE_TRIED);
//		cellIntestation[8].setCellValue(OutExporter.BEER_STARS);
//		cellIntestation[9].setCellValue(OutExporter.BEER_DESCRIPTION);	
//
//		
//		//content
//		cells=new Cell[15];
//		int i=1;
//		for(BeerJSONSpecialClass beer: beers){
//			Row row=sheet.createRow(i);
//			
//			for(int f=0;f<15;f++){
//				cells[f]=row.createCell(f);
//			}
//			
//			cells[0].setCellValue(beer.getBreweryName());
//			cells[1].setCellValue(beer.getName());
//			cells[2].setCellValue(beer.getStyleMainName());
//			cells[3].setCellValue(beer.getStyleSubcategory());
//			cells[4].setCellValue(beer.getColor());
//			cells[5].setCellValue(beer.getMark());
//			cells[6].setCellValue(beer.getAlcool());
//			cells[7].setCellValue(beer.getPlaceTried());
//			cells[8].setCellValue(beer.getNumberOfStars());
//			cells[9].setCellValue(beer.getDescription());
//			
//			
//			i++;
//		}
		
		for(int j=0;j<9;j++){
			cellIntestation[j]=rowIntestation.createCell(j);
		}
		
		cellIntestation[0].setCellValue(OutExporter.BEER_BREWERY_NAME);			
		cellIntestation[1].setCellValue(OutExporter.BEER_NAME);
		cellIntestation[2].setCellValue(OutExporter.BEER_STYLE_MAIN);
		cellIntestation[3].setCellValue(OutExporter.BEER_STYLE_SUBCATEGORY);
		cellIntestation[4].setCellValue(OutExporter.BEER_MARK);
		cellIntestation[5].setCellValue(OutExporter.BEER_ALCOOL);
		cellIntestation[6].setCellValue(OutExporter.BEER_PLACE_TRIED);
		cellIntestation[7].setCellValue(OutExporter.BEER_STARS);
		cellIntestation[8].setCellValue(OutExporter.BEER_DESCRIPTION);	

		
		//content
		cells=new Cell[9];
		int i=1;
		for(BeerJSONSaveSpecialClass beer: beers){
			Row row=sheet.createRow(i);
			
			for(int f=0;f<14;f++){
				cells[f]=row.createCell(f);
			}
			
			cells[0].setCellValue(beer.getBreweryName());
			cells[1].setCellValue(beer.getBeerName());
			cells[2].setCellValue(beer.getStyleMainName());
			cells[3].setCellValue(beer.getStyleSubcategory());
			cells[4].setCellValue(beer.getMark());
			cells[5].setCellValue(beer.getAlcool());
			cells[6].setCellValue(beer.getPlaceTried());
			cells[7].setCellValue(beer.getNumberOfStars());
			cells[8].setCellValue(beer.getBeerDescription());
			
			
			i++;
		}
		
		workbook.write(out);
		out.close();
	}

}
