package org.nbena.beersmanager.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
	

	public enum ViewAllDefault{
		COUNTRY_BREWERY_FERMENTATION_STYLE,
		FERMENTATION_STYLE_COUNTRY_BREWERY,
		JUST_ALPHA_ORDER
	}
	
	public enum BeersDefault{
		ALL_TRIED_BEERS,
		ALL_STAR_BEERS,
		ALL_NOT_TRIED_BEERS,
		ALL_AFTER_LIMIT_BEERS
	}
	
	public enum StyleDefault{
		ALL_STYLES_GROUP_BY_COUNTRY_FERMENTATION,
		STYLES_ONLY_CERTAIN_COUNTRIES,
		STYLES_ONLY_CERTAIN_FERMENTATION
	}
	
	public enum BreweriesDefault{
		ALL_BREWERIES_GROUP_COUNTRY,
		BREWERIES_WITH_MORE_STARRED_BEERS,
		BREWERIES_WITH_AVERAGE_AFTER_LIMIT
	}
	
	public enum ExportDefault{
		EXPORT_PDF,
		EXPORT_MS_NEW,
		EXPORT_MS_OLD,
		EXPORT_XML
	}
	
	public enum BackupEnabled{
		YES,
		NO
	}
	
	public enum HighlightStarBeer{
		YES,
		NO
	}
	
	public enum WriteToFileWhen{
		EVERY_UPDATE,
		BEFORE_CLOSE
	}
	
	private ViewAllDefault viewAllDefaultOption;
	private BeersDefault beersDefaultOption;
	private StyleDefault styleDeafultOption;
	private BreweriesDefault breweriesDeafultOption;
	private ExportDefault exportDefaultoption;
	private BackupEnabled backupEnabledOption;
	private WriteToFileWhen writeToFileWhenOption;
	
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

	public ViewAllDefault getViewAllDefaultOption() {
		return viewAllDefaultOption;
	}

	public void setViewAllDefaultOption(ViewAllDefault viewAllDefaultOption) {
		this.viewAllDefaultOption = viewAllDefaultOption;
	}

	public BeersDefault getBeersDefaultOption() {
		return beersDefaultOption;
	}

	public void setBeersDefaultOption(BeersDefault beersDefaultOption) {
		this.beersDefaultOption = beersDefaultOption;
	}

	public StyleDefault getStyleDeafultOption() {
		return styleDeafultOption;
	}

	public void setStyleDeafultOption(StyleDefault styleDeafultOption) {
		this.styleDeafultOption = styleDeafultOption;
	}

	public BreweriesDefault getBreweriesDeafultOption() {
		return breweriesDeafultOption;
	}

	public void setBreweriesDeafultOption(BreweriesDefault breweriesDeafultOption) {
		this.breweriesDeafultOption = breweriesDeafultOption;
	}

	public ExportDefault getExportDefaultoption() {
		return exportDefaultoption;
	}

	public void setExportDefaultoption(ExportDefault exportDefaultoption) {
		this.exportDefaultoption = exportDefaultoption;
	}

	public BackupEnabled getBackupEnabledOption() {
		return backupEnabledOption;
	}

	public void setBackupEnabledOption(BackupEnabled backupEnabledOption) {
		this.backupEnabledOption = backupEnabledOption;
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

	public void setBeweryFilePathBackup(String beweryFilePathBackup) {
		this.beweryFilePathBackup = beweryFilePathBackup;
	}

	public String getBeerFilePathBackup() {
		return beerFilePathBackup;
	}

	public void setBeerFilePathBackup(String beerFilePathBackup) {
		this.beerFilePathBackup = beerFilePathBackup;
	}
	
	


}
