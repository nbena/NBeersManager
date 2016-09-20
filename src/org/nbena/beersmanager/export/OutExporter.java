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

import java.io.OutputStream;

import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSaveSpecialClass;

public abstract class OutExporter {
	
	//public static enum 
	/*
	protected HashMap<String, String> beerIntestationMap;
	protected HashMap<String, String> breweryIntestationMap;
	protected HashMap<String, String> styleIntestationMap;
	*/
	
	public static final String BEER_BREWERY_NAME = "Brewery name";
	public static final String BEER_BREWERY_TOWN = "Brewery town";
	public static final String BEER_BREWERY_COUNTRY = "Brewery country";
	public static final String BEER_NAME = "Name";
	public static final String BEER_BREWERY_TRAPPIST = "Trappist beer?";
	public static final String BEER_FERMENTATION = "Fermentation";
	public static final String BEER_STYLE_MAIN = "Style";
	public static final String BEER_STYLE_SUBCATEGORY = "Style subcategory";
	public static final String BEER_STYLE_COUNTRY = "Style origin";
	public static final String BEER_COLOR = "Color";
	public static final String BEER_MARK = "Mark";
	public static final String BEER_ALCOOL = "Alcool";
	public static final String BEER_PLACE_TRIED = "Location";
	public static final String BEER_STARS = "Stars";
	public static final String BEER_DESCRIPTION = "Description";
	
	public static final String BREWERY_NAME = "Name";
	public static final String BREWERY_TOWN = "Town";
	public static final String BREWERY_COUNTRY = "Country";
	public static final String BREWERY_WEBSITE = "Website";
	public static final String BREWERY_DESCRIPTION = "Description";
	public static final String BREWERY_IS_TRAPPIST = "Trappist brewery?";
	
	public static final String STYLE_NAME = "Name";
	public static final String STYLE_SUB = "Subcategory";
	public static final String STYLE_COUNTRY = "Country style origin";
	public static final String STYLE_FERMENTATION = "Fermentation";
	public static final String STYLE_DESCRIPTION = "Description";
	
	protected OutExporter(){
		
		/*
		beerIntestationMap=new HashMap<String, String>();
		breweryIntestationMap=new HashMap<String, String>();
		styleIntestationMap=new HashMap<String, String>();
		
		beerIntestationMap.put("Brewery name", "Brewery name");
		beerIntestationMap.put("Brewery town", "Brewery town");
		beerIntestationMap.put("Brewery country", "Brewery country");
		beerIntestationMap.put("Beer name", "Beer name");
		beerIntestationMap.put("Trappist", "Authentic Trappist Product");
		beerIntestationMap.put("Fermentation", "Fermentation");
		beerIntestationMap.put("Style", "Style");
		beerIntestationMap.put("Style sub category", "Style subcategory");
		beerIntestationMap.put("Style country", "Style origin country");
		beerIntestationMap.put("Color", "Color");
		beerIntestationMap.put("Mark", "Mark");
		beerIntestationMap.put("Alcool", "Alcool");
		beerIntestationMap.put("Place tried", "Place tried");
		beerIntestationMap.put("Stars", "Stars");
		beerIntestationMap.put("Description", "Description");
		
		breweryIntestationMap.put("Brewery name", "Name");
		breweryIntestationMap.put("Brewery town", "Town");
		breweryIntestationMap.put("Brewery country", "Country");
		breweryIntestationMap.put("Brewery website", "Website");
		breweryIntestationMap.put("Brewery description", "Description");
		breweryIntestationMap.put("Brewery trappist", "Trappist brewery");
		
		styleIntestationMap.put("Style name", "Name");
		styleIntestationMap.put("Style subcategory", "Sub style");
		styleIntestationMap.put("Style country", "Country style origin");
		styleIntestationMap.put("Fermentation", "Fermentation");
		styleIntestationMap.put("Description", "Description");
		*/
	}
	
	public abstract void writeBeer(List<Beer> beers, OutputStream out, boolean writeTotalPrice) throws Exception;
	
	public abstract void writeBeerSpecialClass(List<BeerJSONSaveSpecialClass> beers, OutputStream out) throws Exception;
	
	public abstract void writeStyle(List<Style> styles, OutputStream out) throws Exception;
	
	public abstract void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception;
	

}
