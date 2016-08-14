package org.nbena.beersmanager.conf;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.QueryRunner;

@XmlRootElement(name="configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
	
	public static enum BeerFilterAlgorithm{
		NONE,
		BY_FERMENTATION_HIGH,
		BY_FERMENTATION_LOW,
		BY_FERMENTATION_SPONTANEOUS,
		BY_COUNTRY,
		BY_STYLE_PROVENIENCE,
		BY_STARS,
		BY_MARK,
		BY_ABV,
		IS_TRIED,
		STYLE,
		MAIN_STYLE
	}
	
	public static enum BreweryFilterAlgorithm{
		NONE,
		COUNTRY,
		BEST_AVERAGES,
		TRAPPIST_YES
	}
	
	public static enum StyleFilterAlgorithm{
		
		NONE,
		BY_FERMENTATION_HIGH,
		BY_FERMENTATION_LOW,
		BY_FERMENTATION_SPONTANEOUS,
		BY_COUNTRY,
		BY_MAIN_STYLE
	}

	
	
	public static enum ShowAlsoBreweriesAverage{
		YES,
		NO
	}
	
	private QueryRunner.BeerSortingAlgorithm beerSortingAlgorithm;
	private QueryRunner.BrewerySortingAlgorithm brewerySortingAlgorithm;
	private QueryRunner.StyleSortingAlgorithm styleSortingAlgorithm;
	
	private BeerFilterAlgorithm beerFilterAlgorithm;
	private BreweryFilterAlgorithm breweryFilterAlgorithm;
	private StyleFilterAlgorithm styleFilterAlgorithm;
	
	private String beerFilterValue;
	private String breweryFilterValue;
	private String styleFilterValue;
	
	
	
	private String styleFilePath;
	private String breweryFilePath;
	private String beerFilePath;
	
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

	/**
	 * @return the beerFilterValue
	 */
	public String getBeerFilterValue() {
		return beerFilterValue;
	}

	/**
	 * @param beerFilterValue the beerFilterValue to set
	 */
	public void setBeerFilterValue(String beerFilterValue) {
		this.beerFilterValue = beerFilterValue;
	}

	/**
	 * @return the breweryFilterValue
	 */
	public String getBreweryFilterValue() {
		return breweryFilterValue;
	}

	/**
	 * @param breweryFilterValue the breweryFilterValue to set
	 */
	public void setBreweryFilterValue(String breweryFilterValue) {
		this.breweryFilterValue = breweryFilterValue;
	}

	/**
	 * @return the styleFilterValue
	 */
	public String getStyleFilterValue() {
		return styleFilterValue;
	}

	/**
	 * @param styleFilterValue the styleFilterValue to set
	 */
	public void setStyleFilterValue(String styleFilterValue) {
		this.styleFilterValue = styleFilterValue;
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
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


}
