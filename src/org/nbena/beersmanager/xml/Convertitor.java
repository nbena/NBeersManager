package org.nbena.beersmanager.xml;
//
//import java.util.Collections;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import org.nbena.beersmanager.coreclasses.Beer;
//import org.nbena.beersmanager.coreclasses.Brewery;
//import org.nbena.beersmanager.coreclasses.Style;
//
public class Convertitor {
//	
//	
//	public static BeerXMLSpecialClass toXMLSpecialClass(Beer beer){
//		/*
//		String styleMainName, styleSubcategory, breweryname=null;
//		styleMainName=beer.getStyle().getStyleMainName();
//		styleSubcategory=beer.getStyle().getStyleSubCategory();
//		*/
//		BeerXMLSpecialClass beerSpecial=new BeerXMLSpecialClass(beer.getName(), beer.getBrewery().getName(), beer.getStyle().getStyleMainName(), beer.getStyle().getStyleSubCategory(), beer.getNumberOfStars());
//		beerSpecial.setAlcool(beer.getAlcool());
//		beerSpecial.setColor(beer.getColor());
//		beerSpecial.setDescription(beer.getDescription());
//		beerSpecial.setImageFilePath(beer.getImageFilePath()); //absolute path is get by working directory
//		beerSpecial.setMark(beer.getMark());
//		beerSpecial.setPlaceTried(beer.getPlaceTried());
//		beerSpecial.setPrice(beer.getPrice());
//		//beerSpecial.setStar(beer.isStar());
//		beerSpecial.setTried(beer.isTried());
//		return beerSpecial;
//	}
//	
//	public static LinkedList<BeerXMLSpecialClass> toXMLSpecialClassList(List<Beer> beers){
//		LinkedList<BeerXMLSpecialClass> beersSpecial=new LinkedList<BeerXMLSpecialClass>();
//		for(Beer beer: beers){
//			beersSpecial.add(toXMLSpecialClass(beer));
//		}
//		return beersSpecial;
//	}
//	
//	
//	public static Beer toBeerNormalClass(BeerXMLSpecialClass specialClassBeer){
//		/*
//		 * Information about style and brewrey are filled then, because knowing name, category and subcategory
//		 * then I can look for which style and brewery match them.
//		 */
//		Style style=new Style();
//		style.setStyleMainName(specialClassBeer.getStyleMainName());
//		style.setStyleSubCategory(specialClassBeer.getStyleSubcategory());
//		Brewery brewery=new Brewery();
//		brewery.setName(specialClassBeer.getBreweryName());
//		Beer beer=new Beer(specialClassBeer.getName(), brewery, style);
//		beer.setAlcool(specialClassBeer.getAlcool());
//		beer.setColor(beer.getColor());
//		beer.setDescription(specialClassBeer.getDescription());
//		beer.setImageFilePath(specialClassBeer.getImageFilePath()); //absolute path is get by working directory
//		beer.setMark(specialClassBeer.getMark());
//		beer.setPlaceTried(specialClassBeer.getPlaceTried());
//		beer.setPrice(specialClassBeer.getPrice());
//		beer.setNumberOfStars(specialClassBeer.getNumberOfStars());
//		beer.setTried(specialClassBeer.isTried());
//		return null;
//	}
//	
//	public static LinkedList<Beer> recompose(List<BeerXMLSpecialClass> specialClassBeers, List<Brewery> breweries, List<Style> styles){
//		LinkedList<Beer> beers=new LinkedList<Beer>();
//		Beer beer;
//		int index1, index2;
//		//not use a dbms-like approach in making a cartesian product.
//		//using instead a binary seacrh into breweries and style.
//		Collections.sort(breweries);
//		Collections.sort(styles);
//		for(int i=0, j=0;i<specialClassBeers.size();i++){
//			beer=toBeerNormalClass(specialClassBeers.get(i));
//			index1=Collections.binarySearch(styles, beer.getStyle());
//			if(index1>=0){
//				beer.setStyle(styles.get(index1));
//			}
//			else{
//				System.err.print("Style not found, insertion point: "+index1);
//			}
//			
//			index2=Collections.binarySearch(breweries, beer.getBrewery());
//			if(index2>=0){
//				beer.setBrewery(breweries.get(index2));
//			}
//			else{
//				System.err.print("Brewery not found, insertion point: "+index2);
//			}
//			
//			if(index1>=0 && index2>=0){
//				beers.add(j, beer);
//			}
//		}
//		
//		return beers;
//	}
//	
//
//	
//
//
}
