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
package org.nbena.beersmanager.json.coreclasses;




import java.util.LinkedList;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exceptions.RecomposingException;
import org.nbena.beersmanager.export.BeerJSONExportSpecialClass;
import org.nbena.beersmanager.query.QueryRunner;

public class Converter {
	
	
	public static BeerJSONSaveSpecialClass toBeerJSONSaveSpecialClassSpecialClass(Beer beer){
		/*
		String styleMainName, styleSubcategory, breweryname=null;
		styleMainName=beer.getStyle().getStyleMainName();
		styleSubcategory=beer.getStyle().getStyleSubCategory();
		*/
		BeerJSONSaveSpecialClass beerSpecial=new BeerJSONSaveSpecialClass(beer.getName(), beer.getBrewery().getBreweryName(), beer.getStyle().getStyleMainName(), beer.getStyle().getStyleSubCategory(), beer.getNumberOfStars());
		beerSpecial.setAlcool(beer.getAlcool());
//		beerSpecial.setColor(beer.getColor());
		beerSpecial.setBeerDescription(beer.getDescription());
		beerSpecial.setImageFilePath(beer.getImageFilePath()); //absolute path is get by working directory
		beerSpecial.setMark(beer.getMark());
		beerSpecial.setPlaceTried(beer.getPlaceTried());
		beerSpecial.setPrice(beer.getPrice());
		//beerSpecial.setStar(beer.isStar());
		beerSpecial.setTried(beer.isTried());
		return beerSpecial;
	}
	

	
	
	public static Beer toNormalBeerFromJSONSaveSpecialClass(BeerJSONSaveSpecialClass specialClassBeer){
		/*
		 * Information about style and brewrey are filled then, because knowing name, category and subcategory
		 * then I can look for which style and brewery match them.
		 */
		Style style=new Style();
		style.setStyleMainName(specialClassBeer.getStyleMainName());
		style.setStyleSubCategory(specialClassBeer.getStyleSubcategory());
		
		Brewery brewery=new Brewery();
		brewery.setBreweryName(specialClassBeer.getBreweryName());
		
		Beer beer=new Beer(specialClassBeer.getBeerName(), brewery, style);
		beer.setAlcool(specialClassBeer.getAlcool());
//		beer.setColor(specialClassBeer.getColor());
		beer.setDescription(specialClassBeer.getBeerDescription());
		beer.setImageFilePath(specialClassBeer.getImageFilePath()); //absolute path is get by working directory
		beer.setMark(specialClassBeer.getMark());
		beer.setPlaceTried(specialClassBeer.getPlaceTried());
		beer.setPrice(specialClassBeer.getPrice());
		beer.setNumberOfStars(specialClassBeer.getNumberOfStars());
		beer.setTried(specialClassBeer.isTried());
		return beer;
	}
	
	
	public static Style toNormalStyle(StyleJSONSpecialClass specialClassStyle){
		Style style = new Style();
		style.setStyleMainName(specialClassStyle.getStyleMainName());
		style.setStyleSubCategory(specialClassStyle.getStyleSubCategory());
		style.setDescription(specialClassStyle.getStyleDescription());
		style.setFermentation(Fermentation.toFermentation(specialClassStyle.getFermentation()));
		style.setStyleCountryOrigin(specialClassStyle.getStyleCountryOrigin());
		return style;
	}
	
	public static StyleJSONSpecialClass toStyleSpecialClass(Style style){
		StyleJSONSpecialClass specialClassStyle=new StyleJSONSpecialClass();
		specialClassStyle.setStyleMainName(style.getStyleMainName());
		specialClassStyle.setStyleSubCategory(style.getStyleSubCategory());
		specialClassStyle.setStyleDescription(style.getDescription());
		specialClassStyle.setFermentation(style.getFermentation().toFirstUpperCase());
		specialClassStyle.setStyleCountryOrigin(style.getStyleCountryOrigin());
		return specialClassStyle;

	}
	
	
	public static LinkedList<Beer> recompose(List<BeerJSONSaveSpecialClass> specialClassBeers, List<Brewery> breweries, List<Style> styles) throws RecomposingException{
		LinkedList<Beer> beers=new LinkedList<Beer>();
		Beer beer;
		int index1, index2;
		//not use a dbms-like approach in making a cartesian product.
		//using instead a binary seacrh into breweries and style.
		
		
//		Collections.sort(breweries);	
//		Collections.sort(styles);
		breweries = QueryRunner.BinarySearch.breweriesSortedForBinaySearchConverter(breweries);
		styles = QueryRunner.BinarySearch.stylesSortedForBinarySearch(styles);
		
		for(int i=0, j=0;i<specialClassBeers.size();i++){
			beer=toNormalBeerFromJSONSaveSpecialClass(specialClassBeers.get(i));
//			index1=Collections.binarySearch(styles, beer.getStyle());
			index1 = QueryRunner.BinarySearch.styleSearch(styles, beer.getStyle(), true);
			if(index1>=0){
				beer.setStyle(styles.get(index1));
			}
			else{
				throw new RecomposingException(beer.getStyle());
//				System.err.print("Style not found, insertion point: "+index1);
			}
			
			index2=QueryRunner.BinarySearch.brewerySearchConverter(breweries, beer.getBrewery(), true);
			if(index2>=0){
				beer.setBrewery(breweries.get(index2));
			}
			else{
//				System.err.print("Brewery not found, insertion point: "+index2);
				throw new RecomposingException(beer.getBrewery());
			}
			
			if(index1>=0 && index2>=0){
				beers.add(j, beer);
			}
		}
		
		return beers;
	}
	
	public static List<Style> toNormalStyleList(List<StyleJSONSpecialClass> styles){
		LinkedList<Style> normalStyles = new LinkedList<Style>();
		for(StyleJSONSpecialClass style: styles){
			normalStyles.add(toNormalStyle(style));
		}
		return normalStyles;
	}
	
	public static List<StyleJSONSpecialClass> toSpecialStyleList(List<Style> styles){
		LinkedList<StyleJSONSpecialClass> specialStyles = new LinkedList<StyleJSONSpecialClass>();
		for(Style style: styles){
			specialStyles.add(toStyleSpecialClass(style));
		}
		return specialStyles;
	}
	
	public static List<BeerJSONSaveSpecialClass> toBeerJSONSaveSpecialClassList(List<Beer> beers){
		LinkedList<BeerJSONSaveSpecialClass> beersSpecial=new LinkedList<BeerJSONSaveSpecialClass>();
		for(Beer beer: beers){
			beersSpecial.add(toBeerJSONSaveSpecialClassSpecialClass(beer));
		}
		return beersSpecial;
	}
	

	public static List<Beer> toNormalBeerFromJSONSaveSpecialClassList(List<BeerJSONSaveSpecialClass> beers){
		LinkedList<Beer> normalBeers=new LinkedList<Beer>();
		for(BeerJSONSaveSpecialClass beer: beers){
			normalBeers.add(toNormalBeerFromJSONSaveSpecialClass(beer));
		}
		return normalBeers;
	}
	
	
	
	public static Beer toNormalBeerFromJSONExportSpecialClass(BeerJSONExportSpecialClass specialClassBeer){
		Beer beer = new Beer();
		beer.setBrewery(specialClassBeer.getBrewery());
		beer.setAlcool(specialClassBeer.getAlcool());
//		beer.setColor(specialClassBeer.getColor());
		beer.setDescription(specialClassBeer.getBeerDescription());
		beer.setImageFilePath(specialClassBeer.getImageFilePath()); //absolute path is get by working directory
		beer.setMark(specialClassBeer.getMark());
		beer.setPlaceTried(specialClassBeer.getPlaceTried());
		beer.setPrice(specialClassBeer.getPrice());
		beer.setNumberOfStars(specialClassBeer.getNumberOfStars());
		beer.setTried(specialClassBeer.isTried());
		beer.setStyle(toNormalStyle(specialClassBeer.getStyle()));
		beer.setName(specialClassBeer.getBeerName());
		return beer;
	}
	
	
	public static BeerJSONExportSpecialClass toBeerJSONExportSpecialClass(Beer b){
		BeerJSONExportSpecialClass beer = new BeerJSONExportSpecialClass();
		beer.setBrewery(b.getBrewery());
		beer.setAlcool(b.getAlcool());
//		beer.setColor(specialClassBeer.getColor());
		beer.setBeerDescription(b.getDescription());
		beer.setImageFilePath(b.getImageFilePath()); //absolute path is get by working directory
		beer.setMark(b.getMark());
		beer.setPlaceTried(b.getPlaceTried());
		beer.setPrice(b.getPrice());
		beer.setNumberOfStars(b.getNumberOfStars());
		beer.setTried(b.isTried());
		beer.setStyle(toStyleSpecialClass(b.getStyle()));
		beer.setBeerName(b.getName());
		return beer;
	}
	
	public static List<Beer> toNormalBeerFromJSONExportSpecialClassList(List<BeerJSONExportSpecialClass> beers){
		LinkedList<Beer> normalBeers=new LinkedList<Beer>();
		for(BeerJSONExportSpecialClass beer: beers){
			normalBeers.add(toNormalBeerFromJSONExportSpecialClass(beer));
		}
		return normalBeers;
	}

	public static List<BeerJSONExportSpecialClass> toBeerJSONExportSpecialClassList(List<Beer> beers){
		LinkedList<BeerJSONExportSpecialClass> beersSpecial=new LinkedList<BeerJSONExportSpecialClass>();
		for(Beer beer: beers){
			beersSpecial.add(toBeerJSONExportSpecialClass(beer));
		}
		return beersSpecial;
	}
	


}
