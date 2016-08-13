package org.nbena.beersmanager.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.nbena.beersmanager.query.QueryRunner;

@XmlRootElement(name="configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
	
	public static enum BeerSortingAlgorithm{
		COUNTRY_OF_BREWERY_STYLE,
		FERMENTATION_COUNTRY_OF_STYLE_BREWERY,
		FERMENTATIOM_STYLE_COUNTRY_OF_BREWERY,
		MARK_STAR,
		STAR_MARK
	}
	
	public static enum StyleSortingAlgorithm{
		FERMENTATION_COUNTRY,
		COUNTRY_FERMENTATION
	}
	
	public static enum BrewerySortingAlgorithm{
		COUNTRY_NAME,
		NAME,
		AVERAGE,
		COUNTRY_AVERAGE
	}
	

//	public static enum ViewAllDefault{
//		COUNTRY_BREWERY_FERMENTATION_STYLE,
//		FERMENTATION_STYLE_COUNTRY_BREWERY,
//		JUST_ALPHA_ORDER
//	}
//	
//	public static enum BeersDefault{
//		ALL_TRIED_BEERS,
//		ALL_STAR_BEERS,
//		ALL_NOT_TRIED_BEERS,
//		ALL_AFTER_LIMIT_BEERS
//	}
//	
//	public static enum StyleDefault{
//		ALL_STYLES_GROUP_BY_COUNTRY_FERMENTATION,
//		STYLES_ONLY_CERTAIN_COUNTRIES,
//		STYLES_ONLY_CERTAIN_FERMENTATION
//	}
//	
//	public static enum BreweriesDefault{
//		ALL_BREWERIES_GROUP_COUNTRY,
//		BREWERIES_WITH_MORE_STARRED_BEERS,
//		BREWERIES_WITH_AVERAGE_AFTER_LIMIT
//	}
	
	
	public static  enum HighlightStarBeer{
		YES,
		NO
	}
	
	public static enum WriteToFileWhen{
		EVERY_UPDATE,
		BEFORE_CLOSE
	}
	
	public static enum ShowAlsoBreweriesAverage{
		YES,
		NO
	}
	
//	private ViewAllDefault viewAllDefaultOption;
//	private BeersDefault beersDefaultOption;
//	private StyleDefault styleDeafultOption;
//	private BreweriesDefault breweriesDeafultOption;
	private WriteToFileWhen writeToFileWhenOption;
	private BeerSortingAlgorithm beerSortingAlgorithm;
	private BrewerySortingAlgorithm brewerySortingAlgorithm;
	private StyleSortingAlgorithm styleSortingAlgorithm;
	
	private int starMarkValue;
	
	//not write, program will search for them automatically
	@XmlTransient
	private String styleFilePath;
	@XmlTransient
	private String breweryFilePath;
	@XmlTransient
	private String beerFilePath;
	
	private String styleFilePathBackup;
	private String beweryFilePathBackup;
	private String beerFilePathBackup;
	
	
	
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
	public BrewerySortingAlgorithm getBrewerySortingAlgorithm() {
		return brewerySortingAlgorithm;
	}

	/**
	 * @param brewerySortingAlgorithm the brewerySortingAlgorithm to set
	 */
	public void setBrewerySortingAlgorithm(BrewerySortingAlgorithm brewerySortingAlgorithm) {
		this.brewerySortingAlgorithm = brewerySortingAlgorithm;
	}

	/**
	 * @return the styleSortingAlgorithm
	 */
	public StyleSortingAlgorithm getStyleSortingAlgorithm() {
		return styleSortingAlgorithm;
	}

	/**
	 * @param styleSortingAlgorithm the styleSortingAlgorithm to set
	 */
	public void setStyleSortingAlgorithm(StyleSortingAlgorithm styleSortingAlgorithm) {
		this.styleSortingAlgorithm = styleSortingAlgorithm;
	}

	public int getStarMarkValue() {
		return starMarkValue;
	}

	public void setStarMarkValue(int starMarkValue) {
		this.starMarkValue = starMarkValue;
	}

	public String getStyleFilePath() {
		return styleFilePath;
	}

	public void setStyleFilePath(String styleFilePath) {
		this.styleFilePath = styleFilePath;
	}

	public String getBreweryFilePath() {
		return breweryFilePath;
	}

	public void setBreweryFilePath(String breweryFilePath) {
		this.breweryFilePath = breweryFilePath;
	}

	public String getBeerFilePath() {
		return beerFilePath;
	}

	public void setBeerFilePath(String beerFilePath) {
		this.beerFilePath = beerFilePath;
	}

	public String getStyleFilePathBackup() {
		return styleFilePathBackup;
	}

	public WriteToFileWhen getWriteToFileWhenOption() {
		return writeToFileWhenOption;
	}

	public void setWriteToFileWhenOption(WriteToFileWhen writeToFileWhenOption) {
		this.writeToFileWhenOption = writeToFileWhenOption;
	}

	public void setStyleFilePathBackup(String styleFilePathBackup) {
		this.styleFilePathBackup = styleFilePathBackup;
	}

	public String getBeweryFilePathBackup() {
		return beweryFilePathBackup;
	}

	/**
	 * @return the beerSortingAlgorithm
	 */
	public BeerSortingAlgorithm getBeerSortingAlgorithm() {
		return beerSortingAlgorithm;
	}

	/**
	 * @param beerSortingAlgorithm the beerSortingAlgorithm to set
	 */
	public void setBeerSortingAlgorithm(BeerSortingAlgorithm beerSortingAlgorithm) {
		this.beerSortingAlgorithm = beerSortingAlgorithm;
	}

	public void setBeweryFilePathBackup(String beweryFilePathBackup) {
		this.beweryFilePathBackup = beweryFilePathBackup;
	}

	public String getBeerFilePathBackup() {
		return beerFilePathBackup;
	}

	public void setBeerFilePathBackup(String beerFilePathBackup) {
		this.beerFilePathBackup = beerFilePathBackup;
	}
	
	
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


}
