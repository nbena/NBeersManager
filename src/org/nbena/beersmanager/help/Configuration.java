package org.nbena.beersmanager.help;




public class Configuration {
	
	public static enum STARTUP_OPT{
		 NO_OPT,
		 VIEW_BEER_LIST,
		 VIEW_STYLE_LIST,
		 VIEW_BREWERY_LIST
	}
	
	public static enum ORDERING_BEER{
		COUNTRY_BREWERY_STYLE,	//country of the breweries, then brewery, then style. using ComparatorBeerByCountryBreweryStyleName
		STYLE_COUNTRY_BREWERY,	//style of the beer, then brewery, then brewery, using ComparatorBeerByFermentationStyleCountryBreweryName
		STYLE_ORIGIN_BREWERY	//country of the style, then brewery. using ComparatorBeerByFermentationCountryStyleBreweryName
	}
	
	public static enum ORDERING_BREWERY{
		COUNTRY,	//maybe new options will be added. using ComparatorBreweryGeographycally
		
	}
	
	public static enum ORDERING_STYLE{
		FERMENTATION_STYLE_ORIGIN,	//fermentation then country origin style. using ComparatorStyleFermentationComplete
		STYLE_ORIGIN_FERMENTATION	//country origin style then fermentation. using ComparatorStyleCountryComplete 
	}
	
	
	/*
	public static enum BACKUP_SETTING{
		ALWAYS_BACKUP_TO_FILE,
		BACKUP_ONLY_WHEN_USER_ASKS
	}
	*/
	
	STARTUP_OPT startupOpt;
	//private BACKUP_SETTING backupSettings;
	ORDERING_BEER orderingBeer;
	ORDERING_BREWERY orderingBrewery;
	ORDERING_STYLE orderingStyle;
	


	public Configuration() {
	}
	
	/**
	 * @return the startupOpt
	 */
	public STARTUP_OPT getStartupOpt() {
		return startupOpt;
	}

	/**
	 * @param startupOpt the startupOpt to set
	 */
	public void setStartupOpt(STARTUP_OPT startupOpt) {
		this.startupOpt = startupOpt;
	}

	/**
	 * @return the beerQuery
	 *
	public DEFAULT_BEER_QUERY getBeerQuery() {
		return beerQuery;
	}

	/**
	 * @param default_BEER_QUERY the beerQuery to set
	 *
	public void setBeerQuery(DEFAULT_BEER_QUERY default_BEER_QUERY) {
		this.beerQuery = default_BEER_QUERY;
	}
	/*
	 * */
	 

	/**
	 * @return the backupSettings
	 *
	public BACKUP_SETTING getBackupSettings() {
		return backupSettings;
	}

	/**
	 * @param backupSettings the backupSettings to set
	 *
	public void setBackupSettings(BACKUP_SETTING backupSettings) {
		this.backupSettings = backupSettings;
	}
	*/

	/**
	 * @return the groupByClause
	 *
	public GROUP_BY_BEER_CLAUSE getGroupByClause() {
		return groupByClause;
	}

	/**
	 * @param groupByClause the groupByClause to set
	 *
	public void setGroupByClause(GROUP_BY_BEER_CLAUSE groupByClause) {
		this.groupByClause = groupByClause;
	}
	*/
	
	/**
	 * @return the groupByBeer
	 */
	public ORDERING_BEER getOrderingBeer() {
		return orderingBeer;
	}

	/**
	 * @param groupByBeer the groupByBeer to set
	 */
	public void setOrderingBeer(ORDERING_BEER groupByBeer) {
		this.orderingBeer = groupByBeer;
	}

	/**
	 * @return the groupByBrewery
	 */
	public ORDERING_BREWERY getOrderingBrewery() {
		return orderingBrewery;
	}

	/**
	 * @param groupByBrewery the groupByBrewery to set
	 */
	public void setOrderingBrewery(ORDERING_BREWERY groupByBrewery) {
		this.orderingBrewery = groupByBrewery;
	}

	/**
	 * @return the groupByStyle
	 */
	public ORDERING_STYLE getOrderingStyle() {
		return orderingStyle;
	}

	/**
	 * @param groupByStyle the groupByStyle to set
	 */
	public void setOrderingStyle(ORDERING_STYLE groupByStyle) {
		this.orderingStyle = groupByStyle;
	}

	public static Configuration getDefaultConfiguration(){
		Configuration c = new Configuration();
		c.setOrderingBeer(ORDERING_BEER.STYLE_ORIGIN_BREWERY);
		c.setOrderingBrewery(ORDERING_BREWERY.COUNTRY);
		c.setOrderingStyle(ORDERING_STYLE.FERMENTATION_STYLE_ORIGIN);
		c.setStartupOpt(STARTUP_OPT.VIEW_BREWERY_LIST);
		return c;
	}
	


}
