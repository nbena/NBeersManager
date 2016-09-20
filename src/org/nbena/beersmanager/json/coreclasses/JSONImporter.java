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

import java.io.File;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.QueryRunner;

public class JSONImporter {
	
//	private List<Beer> baseBeers;
//	private List<Brewery> baseBrewery;
//	private List<Style> baseStyle;
//	
//	private List<Beer> beersDiff;
//	private List<Brewery> breweriesDiff;
//	private List<Style> stylesDiff;
//	
//	boolean initStyle = false;
//	boolean initBrewery = false;
	
	public JSONImporter(){
		
	}
	
//	public JSONImporter(File beerFile, File breweryFile, File styleFile) throws FileNotFoundException, JSONException{
////		beersDiff = new LinkedList<Beer>();
////		breweriesDiff = new LinkedList<Brewery>();
////		stylesDiff = new LinkedList<Style>();
//		
//		initRead(beerFile, breweryFile, styleFile);
//	}
//	
//	public void initRead(File beerFile, File breweryFile, File styleFile) throws FileNotFoundException, JSONException{
//		baseBrewery = Utils.readBreweries(breweryFile);
//		baseStyle = Utils.readStyles(styleFile);
//		baseBeers = Utils.readBeers(beerFile, baseBrewery, baseStyle);
//	}
	
//	
//	
//	private void initStyle(List<Style> base, File file){
//		List<Style> readStyle = Utils.readStyles(file);
//		stylesDiff = QueryRunner.Diff.styleDiff(base, readStyle, false, false);
//		initStyle = true;
//	}
//	
//	private void initBrewery(List<Brewery> base, File file){
//		List<Brewery> readBrewery = Utils.readBreweries(file);
//		breweriesDiff = QueryRunner.Diff.breweryDiff(base, readBrewery, false, false);
//		initBrewery = true;
//	}
	
//	private void initBeer(File file){
//		
//	}
	
	public static List<Beer> getBeersDifference(List<Beer> currentBeers, File beerFile) throws FileNotFoundException, JSONException {
		List<Beer> beersRead = Utils.readBeersFromBeerExportClass(beerFile);
		return getBeersDifference(currentBeers, beersRead);
	}
	
	

	public static List<Brewery> getBreweriesDifference(List<Brewery> currentBreweries, File breweryFile) throws FileNotFoundException, JSONException{
		List<Brewery> breweriesRead = Utils.readBreweries(breweryFile);
		return getBreweriesDifference(currentBreweries, breweriesRead);
	}
	
	public static List<Style> getStylesDifference(List<Style> currentStyles, File styleFile) throws FileNotFoundException, JSONException{
		List<Style> stylesRead = Utils.readStyles(styleFile);
		return getStylesDifference(currentStyles, stylesRead);
	}
	
	
	
	private static List<Beer> getBeersDifference(List<Beer> currentBeers, List<Beer> otherList){
		List<Beer> newList = new LinkedList<Beer>();
		List<Beer> currentBeersCopy = new LinkedList<Beer>(currentBeers);
		List<Beer> beersToAdd = QueryRunner.Diff.beerDiff(currentBeersCopy, otherList, false, false);
		if(!beersToAdd.isEmpty()){
			newList.addAll(beersToAdd);
		}		
		return newList;
	}
	
	
	
	private static List<Brewery> getBreweriesDifference(List<Brewery> currentBreweries, List<Brewery> otherList){
		List<Brewery> newList = new LinkedList<Brewery>();
		List<Brewery> currentBreweriesCopy = new LinkedList<Brewery>(currentBreweries);
		List<Brewery> breweriesToAdd = QueryRunner.Diff.breweryDiff(currentBreweriesCopy, otherList, false, false);
		if(!breweriesToAdd.isEmpty()){
			newList.addAll(breweriesToAdd);
		}		
		return newList;
	}
	
	
	private static List<Style> getStylesDifference(List<Style> currentStyles, List<Style> otherList){
		List<Style> newList = new LinkedList<Style>();
		List<Style> currentStylesCopy = new LinkedList<Style>(currentStyles);
		List<Style> stylesToAdd = QueryRunner.Diff.styleDiff(currentStylesCopy, otherList, false, false);
		if(!stylesToAdd.isEmpty()){
			newList.addAll(stylesToAdd);
		}		
		return newList;
	}

}
