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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.QueryRunner;
import org.nbena.beersmanager.query.QueryRunner.BeerFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BeerSortingAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BreweryFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BrewerySortingAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.StyleFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.StyleSortingAlgorithm;

@XmlRootElement(name="configuration")
@XmlAccessorType(XmlAccessType.FIELD)
@Deprecated
public class Configuration {
		
	
	public static enum ShowAlsoBreweriesAverage{
		YES,
		NO
	}
	
	public static enum ShowDefault{
		BEER,
		BREWERY,
		STYLE;
	}
	
	private BeerSortingAlgorithm beerSortingAlgorithm;
	private BrewerySortingAlgorithm brewerySortingAlgorithm;
	private StyleSortingAlgorithm styleSortingAlgorithm;
	
	private BeerFilterAlgorithm beerFilterAlgorithm;
	private BreweryFilterAlgorithm breweryFilterAlgorithm;
	private StyleFilterAlgorithm styleFilterAlgorithm;
	
	private ShowDefault defaultView;
	
	
	private Object beerFilterValue;
	private Object breweryFilterValue;
	private Object styleFilterValue;
	
	private String styleFilePath;
	private String breweryFilePath;
	private String beerFilePath;
	private String countriesFilePath;
	
	
	private String pwd;
	
	
	


	public Configuration() {
	}

//	public ViewAllDefault getViewAllDefaultOption() {
//		return viewAllDefaultOption;
//	}
//
//	public void setViewAllDefaultOption(ViewAllDefault viewAllDefaultOption) {
//		this.viewAllDefaultOption = viewAllDefaultOption;
//	}
//
//	public BeersDefault getBeersDefaultOption() {
//		return beersDefaultOption;
//	}
//
//	public void setBeersDefaultOption(BeersDefault beersDefaultOption) {
//		this.beersDefaultOption = beersDefaultOption;
//	}
//
//	public StyleDefault getStyleDeafultOption() {
//		return styleDeafultOption;
//	}
//
//	public void setStyleDeafultOption(StyleDefault styleDeafultOption) {
//		this.styleDeafultOption = styleDeafultOption;
//	}
//
//	public BreweriesDefault getBreweriesDeafultOption() {
//		return breweriesDeafultOption;
//	}
//
//	public void setBreweriesDeafultOption(BreweriesDefault breweriesDeafultOption) {
//		this.breweriesDeafultOption = breweriesDeafultOption;
//	}


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

	
	public void setPaths(String pwd){
		this.pwd=pwd;
		beerFilePath=Utils.jsonBeers(pwd);
		breweryFilePath=Utils.jsonBreweries(pwd);
		styleFilePath=Utils.jsonStyle(pwd);
		countriesFilePath = Utils.jsonCountries(pwd);
	}


	public String getStyleFilePath() {
		return styleFilePath;
	}

//	public void setStyleFilePath(String styleFilePath) {
//		this.styleFilePath = styleFilePath;
//	}

	public String getBreweryFilePath() {
		return breweryFilePath;
	}

//	public void setBreweryFilePath(String breweryFilePath) {
//		this.breweryFilePath = breweryFilePath;
//	}

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

//	public void setBeerFilePath(String beerFilePath) {
//		this.beerFilePath = beerFilePath;
//	}
//
//	public String getStyleFilePathBackup() {
//		return styleFilePathBackup;
//	}

//	public WriteToFileWhen getWriteToFileWhenOption() {
//		return writeToFileWhenOption;
//	}
//
//	public void setWriteToFileWhenOption(WriteToFileWhen writeToFileWhenOption) {
//		this.writeToFileWhenOption = writeToFileWhenOption;
//	}

//	public void setStyleFilePathBackup(String styleFilePathBackup) {
//		this.styleFilePathBackup = styleFilePathBackup;
//	}
//
//	public String getBeweryFilePathBackup() {
//		return beweryFilePathBackup;
//	}

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
	 * @return the beerFilterAlgorithm
	 */
	public BeerFilterAlgorithm getBeerFilterAlgorithm() {
		return beerFilterAlgorithm;
	}

	/**
	 * @param beerFilterAlgorithm the beerFilterAlgorithm to set
	 */
	public void setBeerFilterAlgorithm(BeerFilterAlgorithm beerFilterAlgorithm) {
		this.beerFilterAlgorithm = beerFilterAlgorithm;
	}

	/**
	 * @return the breweryFilterAlgorithm
	 */
	public BreweryFilterAlgorithm getBreweryFilterAlgorithm() {
		return breweryFilterAlgorithm;
	}

	/**
	 * @param breweryFilterAlgorithm the breweryFilterAlgorithm to set
	 */
	public void setBreweryFilterAlgorithm(BreweryFilterAlgorithm breweryFilterAlgorithm) {
		this.breweryFilterAlgorithm = breweryFilterAlgorithm;
	}

	/**
	 * @return the styleFilterAlgorithm
	 */
	public StyleFilterAlgorithm getStyleFilterAlgorithm() {
		return styleFilterAlgorithm;
	}

	/**
	 * @param styleFilterAlgorithm the styleFilterAlgorithm to set
	 */
	public void setStyleFilterAlgorithm(StyleFilterAlgorithm styleFilterAlgorithm) {
		this.styleFilterAlgorithm = styleFilterAlgorithm;
	}



//	public void setBeweryFilePathBackup(String beweryFilePathBackup) {
//		this.beweryFilePathBackup = beweryFilePathBackup;
//	}
//
//	public String getBeerFilePathBackup() {
//		return beerFilePathBackup;
//	}
//
//	public void setBeerFilePathBackup(String beerFilePathBackup) {
//		this.beerFilePathBackup = beerFilePathBackup;
//	}
	
	
//	public void beersSortedByCountryOfBreweryStyle(){
////		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(beerData);
//		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(beerData);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByFermentationCountryOfStyleBrewery(){
//		beerData=QueryRunner.beersSortedByFermentationCountryOfStyleBrewery(beerData);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByFermentationStyleCountryOfBrewery(){
//		beerData=QueryRunner.beersSortedByFermentationStyleCountryOfBrewery(beerData);
//		filteredBeers=beerData;
//	}
	
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @deprecated use insted setPaths.
	 * @param pwd the pwd to set
	 */
	@Deprecated
	public void setPwd(String pwd) {
		this.pwd = pwd;
//		beerFilePath = Utils.jsonBeers(pwd);
//		breweryFilePath = Utils.jsonBreweries(pwd);
//		styleFilePath = Utils.jsonStyle(pwd);
//		countriesFilePath = Utils.jsonCountries(pwd);
	}

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

	/**
	 * @return the beerFilterValue
	 */
	public Object getBeerFilterValue() {
		return beerFilterValue;
	}

	/**
	 * @param beerFilterValue the beerFilterValue to set
	 */
	public void setBeerFilterValue(Object beerFilterValue) {
		this.beerFilterValue = beerFilterValue;
	}

	/**
	 * @return the breweryFilterValue
	 */
	public Object getBreweryFilterValue() {
		return breweryFilterValue;
	}

	/**
	 * @param breweryFilterValue the breweryFilterValue to set
	 */
	public void setBreweryFilterValue(Object breweryFilterValue) {
		this.breweryFilterValue = breweryFilterValue;
	}

	/**
	 * @return the styleFilterValue
	 */
	public Object getStyleFilterValue() {
		return styleFilterValue;
	}

	/**
	 * @param styleFilterValue the styleFilterValue to set
	 */
	public void setStyleFilterValue(Object styleFilterValue) {
		this.styleFilterValue = styleFilterValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beerFilePath == null) ? 0 : beerFilePath.hashCode());
		result = prime * result + ((beerFilterAlgorithm == null) ? 0 : beerFilterAlgorithm.hashCode());
		result = prime * result + ((beerFilterValue == null) ? 0 : beerFilterValue.hashCode());
		result = prime * result + ((beerSortingAlgorithm == null) ? 0 : beerSortingAlgorithm.hashCode());
		result = prime * result + ((breweryFilePath == null) ? 0 : breweryFilePath.hashCode());
		result = prime * result + ((breweryFilterAlgorithm == null) ? 0 : breweryFilterAlgorithm.hashCode());
		result = prime * result + ((breweryFilterValue == null) ? 0 : breweryFilterValue.hashCode());
		result = prime * result + ((brewerySortingAlgorithm == null) ? 0 : brewerySortingAlgorithm.hashCode());
		result = prime * result + ((countriesFilePath == null) ? 0 : countriesFilePath.hashCode());
		result = prime * result + ((defaultView == null) ? 0 : defaultView.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((styleFilePath == null) ? 0 : styleFilePath.hashCode());
		result = prime * result + ((styleFilterAlgorithm == null) ? 0 : styleFilterAlgorithm.hashCode());
		result = prime * result + ((styleFilterValue == null) ? 0 : styleFilterValue.hashCode());
		result = prime * result + ((styleSortingAlgorithm == null) ? 0 : styleSortingAlgorithm.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (beerFilePath == null) {
			if (other.beerFilePath != null)
				return false;
		} else if (!beerFilePath.equals(other.beerFilePath))
			return false;
		if (beerFilterAlgorithm != other.beerFilterAlgorithm)
			return false;
		if (beerFilterValue == null) {
			if (other.beerFilterValue != null)
				return false;
		} else if (!beerFilterValue.equals(other.beerFilterValue))
			return false;
		if (beerSortingAlgorithm != other.beerSortingAlgorithm)
			return false;
		if (breweryFilePath == null) {
			if (other.breweryFilePath != null)
				return false;
		} else if (!breweryFilePath.equals(other.breweryFilePath))
			return false;
		if (breweryFilterAlgorithm != other.breweryFilterAlgorithm)
			return false;
		if (breweryFilterValue == null) {
			if (other.breweryFilterValue != null)
				return false;
		} else if (!breweryFilterValue.equals(other.breweryFilterValue))
			return false;
		if (brewerySortingAlgorithm != other.brewerySortingAlgorithm)
			return false;
		if (countriesFilePath == null) {
			if (other.countriesFilePath != null)
				return false;
		} else if (!countriesFilePath.equals(other.countriesFilePath))
			return false;
		if (defaultView != other.defaultView)
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (styleFilePath == null) {
			if (other.styleFilePath != null)
				return false;
		} else if (!styleFilePath.equals(other.styleFilePath))
			return false;
		if (styleFilterAlgorithm != other.styleFilterAlgorithm)
			return false;
		if (styleFilterValue == null) {
			if (other.styleFilterValue != null)
				return false;
		} else if (!styleFilterValue.equals(other.styleFilterValue))
			return false;
		if (styleSortingAlgorithm != other.styleSortingAlgorithm)
			return false;
		return true;
	}




	
	


}
