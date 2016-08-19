package org.nbena.beersmanager.exe.ui.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.conf.ConfigurationFactory;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exceptions.ObjectNotFoundException;
import org.nbena.beersmanager.exceptions.UpdateSavingException;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.export.OutExporter;
import org.nbena.beersmanager.export.JSONOutExporter;
import org.nbena.beersmanager.export.PDFOutExporter;
import org.nbena.beersmanager.export.TXTOutExporter;
import org.nbena.beersmanager.json.coreclasses.JSONExporterCoreClasses;
import org.nbena.beersmanager.export.MSExcelOldOutExporter;
import org.nbena.beersmanager.export.MSExcelNewOutExporter;
import org.nbena.beersmanager.query.BreweryAverage;
import org.nbena.beersmanager.query.Comparators;
import org.nbena.beersmanager.query.QueryRunner;
import org.nbena.beersmanager.query.Comparators.ComparatorBreweryByAverageAscending;
import org.nbena.beersmanager.query.Comparators.ComparatorBreweryByCountryThenAverageAscending;

public class Model {
	

	public static enum DataShownNow{
		BEER,
		BREWERY,
		BREWERY_AVERAGE,
		STYLE
	}
	
	public static enum DialogShownNow{
		BEER,
		BREWERY,
		STYLE
	}
	
	
	private MyModelAbstractTable tableModel;
	
	private MyModelAbstractDialog dialogModel;
	
	private boolean showAlsoAverage=false;
	
	private List<Style> styleData;
	private List<Beer> beerData;
	private List<BreweryAverage> breweryData;
	
	private List<String> countries;
	
	private Beer beerShown;
	private BreweryAverage breweryShown;
	private Style styleShown;
	
	private List<Beer> filteredBeers;
	private List<BreweryAverage> filteredBreweries;
	private List<Style> filteredStyles;
	
	
	
	private DataShownNow dataShownNow;
	private DialogShownNow dialogShown;
	
	private OutExporter exporter;
	private Configuration configuration;
	
	private Function<List<Beer>, List<Beer>> beerSortingDefaultAlgorithm;
	private Function<List<BreweryAverage>, List<BreweryAverage>> brewerySortingDefaultAlgorithm;
	private Function<List<Style>, List<Style>> styleSortingDefaultAlgorithm;
	
	private Function<List<Beer>, List<Beer>> beerSortingCurrentAlgorithm;
	private Function<List<BreweryAverage>, List<BreweryAverage>> brewerySortingCurrentAlgorithm;
	private Function<List<Style>, List<Style>> styleSortingCurrentAlgorithm;
	
	private boolean isAddNewBeerOrModifyBeer;
	private boolean isAddNewBreweryOrModifyBrewery;
	private boolean isAddNewStyleOrModifyStyle;
	
//	private void setupSortingFunction(){
//		Configuration.
//	}
	
	private boolean somethingToSave = false;
	
	
	public Model(){
		//tableModel=new MyModelAbstractTable();
	}
	
	public Model(MyModelAbstractTable tableModel){
		this.tableModel=tableModel;
	}
	
	public Model(MyModelAbstractDialog dialogModel) {
		super();
		this.dialogModel = dialogModel;
	}

	public Model(MyModelAbstractTable tableModel, MyModelAbstractDialog dialogModel) {
		super();
		this.tableModel = tableModel;
		this.dialogModel = dialogModel;
	}

	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @return the somethingToSave
	 */
	public boolean isSomethingToSave() {
		return somethingToSave;
	}

	/**
	 * @param somethingToSave the somethingToSave to set
	 */
	public void setSomethingToSave(boolean somethingToSave) {
		this.somethingToSave = somethingToSave;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(MyModelAbstractTable tableModel) {
		this.tableModel = tableModel;
	}
	
	
	
	
	/**
	 * @return the styleSortingDefaultAlgorithm
	 */
	public Function<List<Style>, List<Style>> getStyleSortingDefaultAlgorithm() {
		return styleSortingDefaultAlgorithm;
	}

	/**
	 * @param styleSortingDefaultAlgorithm the styleSortingDefaultAlgorithm to set
	 */
	public void setStyleSortingDefaultAlgorithm(Function<List<Style>, List<Style>> styleSortingDefaultAlgorithm) {
		this.styleSortingDefaultAlgorithm = styleSortingDefaultAlgorithm;
	}

	/**
	 * @return the isAddNewBeerOrModifyBeer
	 */
	public boolean isAddNewBeerOrModifyBeer() {
		return isAddNewBeerOrModifyBeer;
	}

	/**
	 * @param isAddNewBeerOrModifyBeer the isAddNewBeerOrModifyBeer to set
	 */
	public void setAddNewBeerOrModifyBeer(boolean isAddNewBeerOrModifyBeer) {
		this.isAddNewBeerOrModifyBeer = isAddNewBeerOrModifyBeer;
	}

	/**
	 * @return the isAddNewBreweryOrModifyBrewery
	 */
	public boolean isAddNewBreweryOrModifyBrewery() {
		return isAddNewBreweryOrModifyBrewery;
	}

	/**
	 * @param isAddNewBreweryOrModifyBrewery the isAddNewBreweryOrModifyBrewery to set
	 */
	public void setAddNewBreweryOrModifyBrewery(boolean isAddNewBreweryOrModifyBrewery) {
		this.isAddNewBreweryOrModifyBrewery = isAddNewBreweryOrModifyBrewery;
	}

	/**
	 * @return the isAddNewStyleOrModifyStyle
	 */
	public boolean isAddNewStyleOrModifyStyle() {
		return isAddNewStyleOrModifyStyle;
	}

	/**
	 * @param isAddNewStyleOrModifyStyle the isAddNewStyleOrModifyStyle to set
	 */
	public void setAddNewStyleOrModifyStyle(boolean isAddNewStyleOrModifyStyle) {
		this.isAddNewStyleOrModifyStyle = isAddNewStyleOrModifyStyle;
	}

	/**
	 * @return the dialogModel
	 */
	public MyModelAbstractDialog getDialogModel() {
		return dialogModel;
	}

	/**
	 * @param dialogModel the dialogModel to set
	 */
	public void setDialogModel(MyModelAbstractDialog dialogModel) {
		this.dialogModel = dialogModel;
	}

	/**
	 * @return the dialogShown
	 */
	public DialogShownNow getDialogShown() {
		return dialogShown;
	}

	/**
	 * @param dialogShown the dialogShown to set
	 */
	public void setDialogShown(DialogShownNow dialogShown) {
		this.dialogShown = dialogShown;
	}

	/**
	 * @return the styleData
	 */
	public List<Style> getStyleData() {
		return styleData;
	}

	/**
	 * @param styleData the styleData to set
	 */
	public void setStyleData(List<Style> styleData) {
		this.styleData = styleData;	
		filteredStyles=styleData;
	}
	
//	public void setStyleDataAndShow(List<Style> styleData){
//		this.styleData = styleData;	
//		dataShownNow=DataShownNow.STYLE;
//		tableModel.clear();
//		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
//		tableModel=tableModelOld;
//		tableModel.setData(styleData); //it work||
//	}
	
	private void clearFilter(boolean beer, boolean brewery, boolean style){
		if(beer){
			filteredBeers=beerData;
		}
		if(brewery){
			filteredBreweries=breweryData;
		}
		if(style){
			filteredStyles=styleData;
		}
	}
	
	public void showStyleData(){
		dataShownNow=DataShownNow.STYLE;
		tableModel.clear();
		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		
		applySortingToStyles();
		
		clearFilter(true, true, false);
		
		tableModel.setData(filteredStyles); //it work||
	}

	/**
	 * @return the beerData
	 */
	public List<Beer> getBeerData() {
		return beerData;
	}

	/**
	 * @param beerData the beerData to set
	 */
	public void setBeerData(List<Beer> beerData) {
		this.beerData = beerData;
		filteredBeers=beerData;

	}
	
//	public void setBeerDataAndShow(List<Beer> beerData){
//		this.beerData = beerData;
//		dataShownNow=DataShownNow.BEER;
//		tableModel.clear();
//		ModelBeerTable tableModelOld=(ModelBeerTable)tableModel;
//		tableModel=tableModelOld;
//		tableModel.setData(beerData); //it work||
//	}
	
	public void showBeerData(){
		dataShownNow=DataShownNow.BEER;
		tableModel.clear();
//		tableModel.set
		ModelBeerTable tableModelOld=(ModelBeerTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		
		applySortingToBeers();
		
//		Utils.printBeers(filteredBeers, System.out);
		
		clearFilter(false, true, true);
		
		tableModel.setData(filteredBeers); //it work||
	}


	/**
	 * @return the breweryData
	 */
	public List<Brewery> getBreweryData() {
		return Utils.fromBreweriesAverageToBrewery(breweryData);
	}
	
	public List<BreweryAverage> getBreweryAverageData(){
		return breweryData;
	}

	/**
	 * @param breweryData the breweryData to set
	 */
	public void setBreweryData(List<Brewery> breweryData) {
		this.breweryData = Utils.fromBreweriesToBreweriesAverage(breweryData);
		filteredBreweries=this.breweryData;
	}
	
	@Deprecated
	public void setAverages(List<Beer> beers){
		for(BreweryAverage av: breweryData){
			List<Beer> itsBeers=QueryRunner.beersFilteredByBrewery(beers, (Brewery)av);
			av.setAverage(itsBeers);
		}
		filteredBreweries=this.breweryData;
	}
	
	public void setAverages(){
		for(BreweryAverage av: breweryData){
			List<Beer> itsBeers=QueryRunner.beersFilteredByBrewery(beerData, (Brewery)av);
			av.setAverage(itsBeers);
		}
		filteredBreweries=this.breweryData;
	}
	
//	public void setBreweryDataAndShow(List<Brewery> breweryData){
//		this.breweryData = breweryData;
//		dataShownNow=DataShownNow.BREWERY;
//		tableModel.clear();
//		ModelBreweryTable tableModelOld=(ModelBreweryTable)tableModel;
//		tableModel=tableModelOld;
//		tableModel.setData(breweryData); //it work||
//	}
	
	public void showBreweryData(){
		if(showAlsoAverage){
			showBreweryAverageData();
		}
		else{
			dataShownNow=DataShownNow.BREWERY;
			tableModel.clear();
			ModelBreweryTable tableModelOld=(ModelBreweryTable)tableModel;
			tableModel=tableModelOld;
//			tableModel.fireTableStructureChanged();
			
			applySortingToBreweries();
			
			
			tableModel.setData(filteredBreweries); //it work||
		}
		
		clearFilter(true, false, true);
	}
	
	public void showBreweryAverageData(){
		dataShownNow=DataShownNow.BREWERY_AVERAGE;
		tableModel.clear();
		ModelBreweryAverage tableModelOld=(ModelBreweryAverage)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		
		applySortingToBreweries();
		
		tableModel.setData(filteredBreweries); //it work||
	}

	/**
	 * @return the dataShownNow
	 */
	public DataShownNow getDataShownNow() {
		return dataShownNow;
	}

	/**
	 * Set the new Data Type that is shown in the table. If different than the
	 * previous, the table is cleared.
	 * @param dataShownNow the dataShownNow to set
	 */
	public void setDataShownNow(DataShownNow dataShownNow) {
		if(this.dataShownNow!=dataShownNow && this.dataShownNow!=null){
			tableModel.clear();
		}
		this.dataShownNow = dataShownNow;
	}


	/**
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public Beer getSelectedBeer(int index){
		if (dataShownNow==DataShownNow.BEER){
//			Beer b = (Beer)tableModel.getSelectedObject(index);
//			return b;
			return filteredBeers.get(index);
		}
		else
			throw new RuntimeException("Beer is not in the table");
		//return null;
	}
	
	public Style getSelectedStyle(int index){
		if (dataShownNow==DataShownNow.STYLE){
			//Style s = (Style)tableModel.getSelectedObject(index);
			//return s;
			return filteredStyles.get(index);
		}
		else
			throw new RuntimeException("Style is not in the table");
		//return null;
	}
	
	
	
	public BreweryAverage getSelectedBrewery(int index){
		if(dataShownNow==DataShownNow.BREWERY || dataShownNow==DataShownNow.BREWERY_AVERAGE){
//			if(dataShownNow==DataShownNow.BREWERY){
//				Brewery b=(Brewery)tableModel.getSelectedObject(index);
//				av=Utils.fromBreweryToBreweryAverage(b);
//				av.setAverage(QueryRunner.beersFilteredByBrewery(beerData, b));
//			}
//			else{
//				av=(BreweryAverage)tableModel.getSelectedObject(index);
//			}
//			Utils.printBrewery(av, System.out);
			return filteredBreweries.get(index);
		}
		else
			throw new RuntimeException("Brewery is not in the table");
	}
	
//	public BreweryAverage getSelectedBrewery(int index){
////		System.out.println(dataShownNow);
//		if (dataShownNow==DataShownNow.BREWERY){
//			//Brewery b = (Brewery)tableModel.getSelectedObject(index);
//			//return b;
//			return breweryData.get(index);
//		}
//		else if(dataShownNow==DataShownNow.BREWERY_AVERAGE){
//			getSelectedBreweryAverage(index);
//		}
//		else{
//			throw new RuntimeException("Brewery is not in the table");
//		}
//			
//		//return null;
//	}
//	
//	public BreweryAverage getSelectedBreweryAverage(int index){
//		if (dataShownNow==DataShownNow.BREWERY_AVERAGE){
//			//Brewery b = (Brewery)tableModel.getSelectedObject(index);
//			//return b;
//			return breweryData.get(index);
//		}
//		else
//			throw new RuntimeException("Brewery is not in the table");
//	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
		beerSortingDefaultAlgorithm=Utils.getBeerSortingAlgorithm(configuration.getBeerSortingAlgorithm());
		brewerySortingDefaultAlgorithm=Utils.getBreweriesSortingAlgorithm(configuration.getBrewerySortingAlgorithm());
		styleSortingDefaultAlgorithm=Utils.getStylesSortingAlgorithm(configuration.getStyleSortingAlgorithm());
		
		beerSortingCurrentAlgorithm=beerSortingDefaultAlgorithm;
		brewerySortingCurrentAlgorithm=brewerySortingDefaultAlgorithm;
		styleSortingCurrentAlgorithm=styleSortingDefaultAlgorithm;
	}

	/**
	 * @return the beerDialog
	 */
	public Beer getBeerShown() {
		return beerShown;
	}

	/**
	 * @param beerDialog the beerDialog to set
	 */
	public void setBeerShown(Beer beerDialog) {
		this.beerShown = beerDialog;
	}

	/**
	 * @return the breweryDialog
	 */
	public BreweryAverage getBreweryShown() {
		return breweryShown;
	}

	/**
	 * @param breweryDialog the breweryDialog to set
	 */
	public void setBreweryShown(BreweryAverage breweryDialog) {
		this.breweryShown = breweryDialog;
	}
	
	public void setBreweryShown(Brewery brewery){
		breweryShown=Utils.fromBreweryToBreweryAverage(brewery);
		breweryShown.setAverage(QueryRunner.beersFilteredByBrewery(beerData, brewery));
	}

	/**
	 * @return the styleDialog
	 */
	public Style getStyleShown() {
		return styleShown;
	}

	/**
	 * @param styleDialog the styleDialog to set
	 */
	public void setStyleShown(Style styleDialog) {
		this.styleShown = styleDialog;
	}

	
	public List<Style> getOnlyMainStyle(){
		return QueryRunner.onlyMainStyles(styleData);
	}
	
	public List<String> getOnlyMainStyles(){
		return QueryRunner.onlyMainStylesAsString(styleData);
	}
	
	
	public void beersSortedByCountryOfBreweryStyle(){
//		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(beerData);
		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByFermentationCountryOfStyleBrewery(){
		beerData=QueryRunner.beersSortedByFermentationCountryOfStyleBrewery(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByFermentationStyleCountryOfBrewery(){
		beerData=QueryRunner.beersSortedByFermentationStyleCountryOfBrewery(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByMarkStarAscending(){
		beerData=QueryRunner.beersSortedByMarkStarAscending(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByStarMarkAsending(){
		beerData=QueryRunner.beersSortedByStarMarkAscending(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByMarkStarDescending(){
		beerData=QueryRunner.beersSortedByMarkStarDescending(filteredBeers);
		filteredBeers=beerData;
	}
	
	public void beersSortedByStarMarkDesending(){
		beerData=QueryRunner.beersSortedByStarMarkDescending(filteredBeers);
		filteredBeers=beerData;
	}

	public void styleSortedByFermentationThenCountry(){
		styleData=QueryRunner.styleSortedByFermentationThenCountry(filteredStyles);
		filteredStyles=styleData;
	}
	
	public void styleSortedByCountryThenFermentationy(){
		styleData=QueryRunner.styleSortedByCountryThenFermentationy(filteredStyles);
		filteredStyles=styleData;
	}
	
	
	
	
	public void breweriesSortedByCountryThenName(){
		breweryData=QueryRunner.breweriesSortedByCountryThenNameWithAverage(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	
	public void breweriesSortedBynName(List<Brewery> breweries){
		breweryData=QueryRunner.breweriesSortedByNameWithAverage(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	
	public void breweriesSortedByAverageAscending(){
		breweryData=QueryRunner.breweriesSortedByAverageAscending(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	
	public void breweriesSortedByCountryThenAverageAscending(){
		breweryData=QueryRunner.breweriesSortedByCountryThenAverageAscending(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	public void breweriesSortedByAverageDescending(){
		breweryData=QueryRunner.breweriesSortedByAverageDescending(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	
	public void breweriesSortedByCountryThenAverageDescending(){
		breweryData=QueryRunner.breweriesSortedByCountryThenAverageDescending(filteredBreweries);
		filteredBreweries=breweryData;
	}
	
	
	/**
	 * @return the showAlsoAverage
	 */
	public boolean isShowAlsoAverage() {
		return showAlsoAverage;
	}

	/**
	 * @param showAlsoAverage the showAlsoAverage to set
	 */
	public void setShowAlsoAverage(boolean showAlsoAverage) {
		this.showAlsoAverage = showAlsoAverage;
	}

	public void beersFilteredByStyle(Style style){
		filteredBeers=QueryRunner.beersFilteredByStyle(filteredBeers, style);
	}
	
	public void beersFilteredByMainStyle(Style style){
		filteredBeers=QueryRunner.beersFilteredByMainStyle(filteredBeers, style);
	}
	

	
	public void beersFilteredByBrewery(Brewery brewery){
//		System.out.println("brewery to work is: \n");
//		Utils.printBrewery(brewery, System.out);
		filteredBeers=QueryRunner.beersFilteredByBrewery(filteredBeers, brewery);
	}
	
		
	public void beersFilteredByMiminumMark(int mark){
		filteredBeers=QueryRunner.beersFilteredByMiminumMark(filteredBeers, mark);
	}

	public void beersFilteredByExactMark(int mark){
		filteredBeers=QueryRunner.beersFilteredByExactMark(filteredBeers, mark);
	}
	
	
	
	public void beersFilteredByIsTried(boolean isTried){
		filteredBeers=QueryRunner.beersFilteredByIsTried(filteredBeers, isTried);
	}
	
	
	
	public void beersFilteredByMinimumNumberOfStars(int numberOfStar){
		filteredBeers=QueryRunner.beersFilteredByMinimumNumberOfStars(filteredBeers, numberOfStar);
	}
	
	public void beersFilteredByExactNumberOfStars(int numberOfStar){
		filteredBeers=QueryRunner.beersFilteredByExactNumberOfStars(filteredBeers, numberOfStar);
	}
	
	
	
	public void beersFilteredByMinimumAlcool(double alcool){
		filteredBeers=QueryRunner.beersFilteredByMinimumAlcool(filteredBeers, alcool);
	}
	
	public void beersFilteredByExatcAlcool(double alcool){
		filteredBeers=QueryRunner.beersFilteredByExatcAlcool(filteredBeers, alcool);
	}
	
	public void beersFilteredByTrappist(boolean trappist){
		filteredBeers=QueryRunner.beersFilteredByTrappist(filteredBeers, trappist);
	}
	
	
	public void beersFilteredByColour(String color){
		filteredBeers=QueryRunner.beersFilteredByColour(filteredBeers, color);
	}
	
		
	
	public void beersFilteredByFermentation(Fermentation fermentation){
		filteredBeers=QueryRunner.beersFilteredByFermentation(filteredBeers, fermentation);
	}
	
	

	public void beersFilteredByBreweryCountry(String country){	
		filteredBeers=QueryRunner.beersFilteredByBreweryCountry(filteredBeers, country);
			
	}
	
	
	public void beersFilteredByStyleProvenience(String provenience){
		filteredBeers=QueryRunner.beersFilteredByStyleProvenience(filteredBeers, provenience);
	}
	
	public static enum ExportType{
		JSON,
		EXCEL_NEW,
		EXCEL_OLD,
		PDF,
		TXT
	}
	
	public void exportBeers(ExportType export, OutputStream out) throws Exception{
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewBeers(out);
			break;
		case EXCEL_OLD:
			exportMsExcelOldBeers(out);
			break;
		case JSON:
			exportJSONBeers(out);
			break;
		case PDF:
			exportPDFBeers(out);
			break;
		case TXT:
			exportTXTBeers(out);
			break;	
		}
	}
	
	public void exportBreweries(ExportType export, OutputStream out) throws Exception{
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewBreweries(out);
			break;
		case EXCEL_OLD:
			exportMsExcelOldBreweries(out);
			break;
		case JSON:
			exportJSONBreweries(out);
			break;
		case PDF:
			exportPDFBreweries(out);
			break;
		case TXT:
			exportTXTBreweries(out);
			break;	
		}
	}
	
	public void exportStyles(ExportType export, OutputStream out) throws Exception{
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewStyles(out);
			break;
		case EXCEL_OLD:
			exportMsExcelOldStyles(out);
			break;
		case JSON:
			exportJSONStyles(out);
			break;
		case PDF:
			exportPDFStyles(out);
			break;
		case TXT:
			exportTXTStyles(out);
			break;	
		}
	}
	
	private void exportJSONBeers(OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportJSONBreweries(OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportJSONStyles(OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
		exporter.writeStyle(styleData, out);
	}
	
	
	private void exportMsExcelNewBeers(OutputStream out) throws Exception{
		exporter = new MSExcelNewOutExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportMsExcelNewBreweries(OutputStream out) throws Exception{
		exporter = new MSExcelNewOutExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportMsExcelNewStyles(OutputStream out) throws Exception{
		exporter = new MSExcelNewOutExporter();
		exporter.writeStyle(styleData, out);
	}
	
	
	private void exportMsExcelOldBeers(OutputStream out) throws Exception{
		exporter = new  MSExcelOldOutExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportMsExcelOldBreweries(OutputStream out) throws Exception{
		exporter = new MSExcelOldOutExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportMsExcelOldStyles(OutputStream out) throws Exception{
		exporter = new MSExcelOldOutExporter();
		exporter.writeStyle(styleData, out);
	}
	
	private void exportPDFBeers(OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportPDFBreweries(OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportPDFStyles(OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeStyle(styleData, out);
	}
	
	private void exportTXTBeers(OutputStream out) throws Exception{
		exporter = new TXTOutExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportTXTBreweries(OutputStream out) throws Exception{
		exporter = new TXTOutExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportTXTStyles(OutputStream out) throws Exception{
		exporter = new TXTOutExporter();
		exporter.writeStyle(styleData, out);
	}
	
	public String getLastDirectory(){
		return System.getProperty("user.home");
	}
	
	
	public void applySortingToBeers(){
		filteredBeers=this.beerSortingCurrentAlgorithm.apply(filteredBeers);
//		filteredBeers=beerData;
	}
	
	public void applySortingToBreweries(){
		filteredBreweries=this.brewerySortingCurrentAlgorithm.apply(filteredBreweries);
//		filteredBreweries=breweryData;
	}
	
	public void applySortingToStyles(){
		filteredStyles=this.styleSortingCurrentAlgorithm.apply(filteredStyles);
//		filteredStyles=styleData;
	}

	/**
	 * @return the beerSortingCurrentAlgorithm
	 */
	public Function<List<Beer>, List<Beer>> getBeerSortingCurrentAlgorithm() {
		return beerSortingCurrentAlgorithm;
	}

	/**
	 * @param beerSortingCurrentAlgorithm the beerSortingCurrentAlgorithm to set
	 */
	public void setBeerSortingCurrentAlgorithm(Function<List<Beer>, List<Beer>> beerSortingCurrentAlgorithm) {
		this.beerSortingCurrentAlgorithm = beerSortingCurrentAlgorithm;
	}

	/**
	 * @return the brewerySortingCurrentAlgorithm
	 */
	public Function<List<BreweryAverage>, List<BreweryAverage>> getBrewerySortingCurrentAlgorithm() {
		return brewerySortingCurrentAlgorithm;
	}

	/**
	 * @param brewerySortingCurrentAlgorithm the brewerySortingCurrentAlgorithm to set
	 */
	public void setBrewerySortingCurrentAlgorithm(
			Function<List<BreweryAverage>, List<BreweryAverage>> brewerySortingCurrentAlgorithm) {
		this.brewerySortingCurrentAlgorithm = brewerySortingCurrentAlgorithm;
	}

	/**
	 * @return the styleSortingCurrentAlgorithm
	 */
	public Function<List<Style>, List<Style>> getStyleSortingCurrentAlgorithm() {
		return styleSortingCurrentAlgorithm;
	}

	/**
	 * @param styleSortingCurrentAlgorithm the styleSortingCurrentAlgorithm to set
	 */
	public void setStyleSortingCurrentAlgorithm(Function<List<Style>, List<Style>> styleSortingCurrentAlgorithm) {
		this.styleSortingCurrentAlgorithm = styleSortingCurrentAlgorithm;
	}
	
	public Brewery getBreweryBinarySearch(Brewery key) throws ObjectNotFoundException{
		List<BreweryAverage> tempBrewerySortedList = QueryRunner.BinarySearch.breweriesAverageSortedForBinarySearch(breweryData);
		int pos = QueryRunner.BinarySearch.breweryAverageSearch(tempBrewerySortedList, Utils.fromBreweryToBreweryAverage(key), true);
		if (pos>=0){
			return Utils.fromBreweryAverageToBrewery(tempBrewerySortedList.get(pos));
		}
		else{
			throw new ObjectNotFoundException(key);
		}
	}
	
	public Style getStyleBinarySearch(Style key) throws ObjectNotFoundException{
		List<Style> tempStyleSortedList = QueryRunner.BinarySearch.stylesSortedForBinarySearch(styleData);
		int pos = QueryRunner.BinarySearch.styleSearch(tempStyleSortedList, key, true);
		if(pos >=0){
			return tempStyleSortedList.get(pos);
		}
		else{
			throw new ObjectNotFoundException(key);
		}
	}
	
	
	
	

	
	public void addNewBeer(Beer beer) throws UpdateSavingException{
		if(!QueryRunner.BinarySearch.isBeerExists(beerData, beer, false)){
			beerData.add(beer);
			filteredBeers = beerData;
		}
		else{
			throw new UpdateSavingException(beer, UpdateSavingException.ErrorWhile.ADDING);
		}
	}
	
	public void addNewBrewery(Brewery brewery) throws UpdateSavingException{
		if(!QueryRunner.BinarySearch.isBreweryExists(Utils.fromBreweriesAverageToBrewery(breweryData), brewery, false)){
			breweryData.add(Utils.fromBreweryToBreweryAverage(brewery));
			filteredBreweries = breweryData;
		}
		else{
			throw new UpdateSavingException(brewery, UpdateSavingException.ErrorWhile.ADDING);
		}

	}
	
	
	public void addNewStyle(Style style) throws UpdateSavingException{
		
//		System.out.println("Lo stile da cercare");
//		Utils.printStyle(style, System.out);
//		System.out.println("Gli stili: ");
//		Utils.printStyles(styleData, System.out);
		boolean res = QueryRunner.BinarySearch.isStyleExists(styleData, style, false);
		if(!res){
			styleData.add(style);
			filteredStyles = styleData;
		}
		else{
			throw new UpdateSavingException(style, UpdateSavingException.ErrorWhile.ADDING);
		}

	}
	
	public void updateBeer(Beer newBeer) throws UpdateSavingException{
		if(beerData.remove(beerShown)){
			beerData.add(newBeer);
			filteredBeers = beerData;
			
			showBeerData();
		}
		else{
			throw new UpdateSavingException(newBeer, UpdateSavingException.ErrorWhile.UPDATING);
		}

	}
	
	public void updateBrewery(BreweryAverage newBrewery) throws UpdateSavingException{
		if(breweryData.remove(breweryShown)){
			breweryData.add(newBrewery);
			filteredBreweries = breweryData;
			
			showBreweryData();
		}
		else{
			throw new UpdateSavingException(newBrewery, UpdateSavingException.ErrorWhile.UPDATING);
		}

	}
	
	public void updateStyle(Style newStyle) throws UpdateSavingException{
		if(styleData.remove(styleShown)){
			styleData.add(newStyle);
			filteredStyles = styleData;
			
//			System.out.println("Gli stili: ");
//			Utils.printStyles(styleData, System.out);
			
			showStyleData();
		}
		else{
			throw new UpdateSavingException(newStyle, UpdateSavingException.ErrorWhile.UPDATING);
		}

	}
	
	public void deleteBeer(Beer b) throws UpdateSavingException{
		Beer toDelete;
		if(b==null){
			toDelete = beerShown;
		}
		else{
			toDelete = b;
		}
		if(beerData.remove(toDelete)){
			filteredBeers = beerData;
			
			showBeerData();
		}
		else{
			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.DELETING);
		}
	}
	
	public void deleteBrewery(BreweryAverage av) throws UpdateSavingException{
		BreweryAverage toDelete;
		if(av==null){
			toDelete = breweryShown;
		}else{
			toDelete = av;
		}
		
		if(breweryData.remove(toDelete)){
			filteredBreweries = breweryData;
			
			showBreweryData();
		}
		else{
			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.UPDATING);
		}
	}
	
	public void deleteStyle(Style s) throws UpdateSavingException{
		Style toDelete;
		if(s==null){
			toDelete = styleShown;
		}else{
			toDelete= s;
		}
		if(styleData.remove(toDelete)){
			filteredStyles = styleData;
			
			showStyleData();
		}
		else{
			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.UPDATING);
		}
	}
	
	
	
	public void saveBeers() throws JSONException, FileNotFoundException{
		JSONExporterCoreClasses.writeBeerSpecial(beerData, new FileOutputStream(configuration.getBeerFilePath()));
	}
	
	public void saveBreweries() throws FileNotFoundException, FileNotFoundException{
		JSONExporterCoreClasses.writeBrewery(Utils.fromBreweriesAverageToBrewery(breweryData), new FileOutputStream(configuration.getBreweryFilePath()));
	}
	
	public void saveStyles() throws JSONException, FileNotFoundException{
		JSONExporterCoreClasses.writeStyleSpecial(styleData, new FileOutputStream(configuration.getStyleFilePath()));
	}
	
	private List<Brewery> readBreweries() throws FileNotFoundException, JSONException{
		List<Brewery> breweries = Utils.readBreweries(new File(configuration.getBreweryFilePath()));
		setBreweryData(breweries);
		return breweries;
	}
	
	private void readStyles() throws FileNotFoundException, JSONException{
		List<Style> styles = Utils.readStyles(new File(configuration.getStyleFilePath()));
		setStyleData(styles);
	}
	
	private void readBeers(List<Brewery> breweries) throws FileNotFoundException, JSONException{
//		List<Brewery> breweries = Utils.readBreweries(new File(configuration.getBreweryFilePath()));
//		List<Style> styles = Utils.readStyles(new File(configuration.getStyleFilePath()));
		List<Beer> beers=Utils.readBeers(new File(configuration.getBeerFilePath()), breweries, styleData);
		setBeerData(beers);
//		setBreweryData(breweries);
//		setStyleData(styles);
	}
	
	public void readThings() throws FileNotFoundException, JSONException{
		List<Brewery> breweries=readBreweries();
		readStyles();
		readBeers(breweries); //so I do not need to convert.
	}
	
	public void saveThings() throws JSONException, FileNotFoundException {
		saveBeers();
		saveBreweries();
		saveStyles();
	}
	
	public void saveConfiguration() throws FileNotFoundException{
		ConfigurationFactory.writeConfiguration(configuration, ConfigurationFactory.getConfigurationPath());
	}

}
