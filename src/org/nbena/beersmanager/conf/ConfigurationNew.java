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
package org.nbena.beersmanager.conf;


import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.QueryRunner;
import org.nbena.beersmanager.query.QueryRunner.BeerSortingAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BrewerySortingAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.StyleSortingAlgorithm;

/**
 * A new version of the configuration class, now the filters are not present.
 * @author nicola
 *
 */
public class ConfigurationNew {
		
	
//	public static enum ShowAlsoBreweriesAverage{
//		YES,
//		NO
//	}
	
	public static enum ShowDefault{
		BEER,
		BREWERY,
		STYLE;
	}
	
	private BeerSortingAlgorithm beerSortingAlgorithm;
	private BrewerySortingAlgorithm brewerySortingAlgorithm;
	private StyleSortingAlgorithm styleSortingAlgorithm;
	
	
	private ShowDefault defaultView;
	
	
//	private Object beerFilterValue;
//	private Object breweryFilterValue;
//	private Object styleFilterValue;
	
	private String styleFilePath;
	private String breweryFilePath;
	private String beerFilePath;
	private String countriesFilePath;
	
	
//	private String pwd;
	
	
	


	public ConfigurationNew() {
	}



	/**
	 * @return the brewerySortingAlgorithm
	 */
	public QueryRunner.BrewerySortingAlgorithm getBrewerySortingAlgorithm() {
		return brewerySortingAlgorithm;
	}

	/**
	 * @param brewerySortingAlgorithm the brewerySortingAlgorithm to set
	 */
	public void setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm brewerySortingAlgorithm) {
		this.brewerySortingAlgorithm = brewerySortingAlgorithm;
	}

	/**
	 * @return the styleSortingAlgorithm
	 */
	public QueryRunner.StyleSortingAlgorithm getStyleSortingAlgorithm() {
		return styleSortingAlgorithm;
	}

	/**
	 * @param styleSortingAlgorithm the styleSortingAlgorithm to set
	 */
	public void setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm styleSortingAlgorithm) {
		this.styleSortingAlgorithm = styleSortingAlgorithm;
	}

	
	public void setPaths(/*String pwd*/){
//		this.pwd=pwd;
//		beerFilePath=Utils.jsonBeers(pwd);
//		breweryFilePath=Utils.jsonBreweries(pwd);
//		styleFilePath=Utils.jsonStyle(pwd);
//		countriesFilePath = Utils.jsonCountries(pwd);
//		String base = Utils.getOSIndipendentFolder();
		beerFilePath = Utils.getBeersPath();
		breweryFilePath = Utils.getBreweriesPath();
		styleFilePath = Utils.getStylesPath();
		countriesFilePath = Utils.getCountriesPath();
	}


	public String getStyleFilePath() {
		return styleFilePath;
	}


	public String getBreweryFilePath() {
		return breweryFilePath;
	}


	/**
	 * @return the countriesFilePath
	 */
	public String getCountriesFilePath() {
		return countriesFilePath;
	}

	/**
	 * @param countriesFilePath the countriesFilePath to set
	 */
	public void setCountriesFilePath(String countriesFilePath) {
		this.countriesFilePath = countriesFilePath;
	}

	public String getBeerFilePath() {
		return beerFilePath;
	}



	/**
	 * @return the beerSortingAlgorithm
	 */
	public QueryRunner.BeerSortingAlgorithm getBeerSortingAlgorithm() {
		return beerSortingAlgorithm;
	}

	/**
	 * @param beerSortingAlgorithm the beerSortingAlgorithm to set
	 */
	public void setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm beerSortingAlgorithm) {
		this.beerSortingAlgorithm = beerSortingAlgorithm;
	}





	
	/**
	 * @return the pwd
	 */
//	public String getPwd() {
//		return pwd;
//	}


	/**
	 * @return the defaultView
	 */
	public ShowDefault getDefaultView() {
		return defaultView;
	}

	/**
	 * @param defaultView the defaultView to set
	 */
	public void setDefaultView(ShowDefault defaultView) {
		this.defaultView = defaultView;
	}

//	/**
//	 * @return the beerFilterValue
//	 */
//	public Object getBeerFilterValue() {
//		return beerFilterValue;
//	}
//
//	/**
//	 * @param beerFilterValue the beerFilterValue to set
//	 */
//	public void setBeerFilterValue(Object beerFilterValue) {
//		this.beerFilterValue = beerFilterValue;
//	}
//
//	/**
//	 * @return the breweryFilterValue
//	 */
//	public Object getBreweryFilterValue() {
//		return breweryFilterValue;
//	}
//
//	/**
//	 * @param breweryFilterValue the breweryFilterValue to set
//	 */
//	public void setBreweryFilterValue(Object breweryFilterValue) {
//		this.breweryFilterValue = breweryFilterValue;
//	}
//
//	/**
//	 * @return the styleFilterValue
//	 */
//	public Object getStyleFilterValue() {
//		return styleFilterValue;
//	}
//
//	/**
//	 * @param styleFilterValue the styleFilterValue to set
//	 */
//	public void setStyleFilterValue(Object styleFilterValue) {
//		this.styleFilterValue = styleFilterValue;
//	}





	
	


}
