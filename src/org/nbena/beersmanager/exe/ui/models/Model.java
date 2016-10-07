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
package org.nbena.beersmanager.exe.ui.models;

import java.io.File;






import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.conf.Configuration.ShowDefault;
import org.nbena.beersmanager.conf.ConfigurationFactory;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exceptions.ObjectNotFoundException;
import org.nbena.beersmanager.exceptions.RecomposingException;
import org.nbena.beersmanager.exceptions.UpdateSavingException;
import org.nbena.beersmanager.exceptions.UpdateSavingException.ErrorWhile;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.export.OutExporter;
import org.nbena.beersmanager.export.JSONOutExporter;
import org.nbena.beersmanager.export.TXTOutExporter;
import org.nbena.beersmanager.json.coreclasses.JSONExporterCoreClasses;
import org.nbena.beersmanager.json.coreclasses.JSONImporter;
import org.nbena.beersmanager.export.MSExcelOldOutExporter;
import org.nbena.beersmanager.export.MSExcelNewOutExporter;
import org.nbena.beersmanager.query.QueryRunner;
import org.nbena.beersmanager.query.QueryRunner.BeerFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BeerQuery;
import org.nbena.beersmanager.query.QueryRunner.BeerQuery.BeerFilter;
import org.nbena.beersmanager.query.QueryRunner.BreweryFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BreweryQuery.BrewerySort;
import org.nbena.beersmanager.query.QueryRunner.StyleFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.StyleQuery;
import org.nbena.beersmanager.sclasses.BreweryAverage;

/**
 * The model class in MVC. Model keeps the business logic, it contains the data, it works on them.
 * It can happen that model act just as a wrapper for other method, because I try to use all-in-one
 * method.
 * @author nbena
 *
 */
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
	
//	private MyModelAbstractDialog dialogModel;
	
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
	
	private BiFunction<List<Beer>, Object, List<Beer>> beersFilteringDefaultAlgorithm;
	private BiFunction<List<BreweryAverage>, Object, List<BreweryAverage>> breweryFilteringDefaultAlgorithm;
	private BiFunction<List<Style>, Object, List<Style>> styleFilteringDefaultAlgorithm;
	
	private Object beerFilteringDefaultValue;
	private Object breweryFilteringDefaultValue;
	private Object styleFilteringDefaultValue;
	
	private BiFunction<List<Beer>, Object, List<Beer>> beersFilteringCurrentAlgorithm;
	private BiFunction<List<BreweryAverage>, Object, List<BreweryAverage>> breweryFilteringCurrentAlgorithm;
	private BiFunction<List<Style>, Object, List<Style>> styleFilteringCurrentAlgorithm;
	
	private Object beerFilteringCurrentValue;
	private Object breweryFilteringCurrentValue;
	private Object styleFilteringCurrentValue;
	
	private boolean isAddNewBeerOrModifyBeer;
	private boolean isAddNewBreweryOrModifyBrewery;
	private boolean isAddNewStyleOrModifyStyle;
	private boolean exportAll;
	
	
	private int[] selctedRows;
	
//	private void setupSortingFunction(){
//		Configuration.
//	}
	
	private boolean somethingToSave = false;
	
	private boolean saveBeer = false;
	private boolean saveBrewery = false;
	private boolean saveStyle = false;
	
	
//	public static final SpinnerNumberModel spinnerMarkModel = new SpinnerNumberModel(Utils.Constants.MARK_SPINNER_DEF_VALUE,
//			Utils.Constants.MARK_SPINNER_MIN_VALUE, Utils.Constants.MARK_SPINNER_MAX_VALUE, Utils.Constants.MARK_SPINNER_STEP_VALUE);
//	
//	public static final SpinnerNumberModel spinnerPriceModel = new SpinnerNumberModel(Utils.Constants.PRICE_SPINNER_DEF_VALUE,
//			Utils.Constants.PRICE_SPINNER_MIN_VALUE, Utils.Constants.PRICE_SPINNER_MAX_VALUE, Utils.Constants.PRICE_SPINNER_STEP_VALUE);
//	
//	public static final SpinnerNumberModel spinnerABVModel = new SpinnerNumberModel(Utils.Constants.ABV_SPINNER_DEF_VALUE,
//			Utils.Constants.ABV_SPINNER_MIN_VALUE, Utils.Constants.ABV_SPINNER_MAX_VALUE, Utils.Constants.ABV_SPINNER_STEP_VALUE);
	
//	private static final MarkSpinnerNumberModel markSpinner = new MarkSpinnerNumberModel();
//	private static final ABVSpinnerNumberModel abvSpinner = new ABVSpinnerNumberModel();
//	private static final PriceSpinnerNumberModel priceSpinner = new PriceSpinnerNumberModel();
	
//	private void initLabelTable(){
////		labelTable = new Hashtable<Integer, JLabel>();
////		labelTable.put(1,  new JLabel("1"));
////		labelTable.put(2, new JLabel("2"));
////		labelTable.put(3,  new JLabel("3"));
////		labelTable.put(4, new JLabel("4"));
////		labelTable.put(5, new JLabel("5"));
//	}
	
	
	public Model(){
		//tableModel=new MyModelAbstractTable();
//		initLabelTable();
//		initSpinner();
	}
	
	public Model(MyModelAbstractTable tableModel){
		this.tableModel=tableModel;
//		initLabelTable();
//		initSpinner();
	}
	
//	public Model(MyModelAbstractDialog dialogModel) {
//		super();
//		this.dialogModel = dialogModel;
////		initLabelTable();
////		initSpinner();
//	}
//
//	public Model(MyModelAbstractTable tableModel, MyModelAbstractDialog dialogModel) {
//		super();
//		this.tableModel = tableModel;
//		this.dialogModel = dialogModel;
////		initLabelTable();
////		initSpinner();
//	}

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
	
	
	
	public List<String> getCountriesWithStyle(){
//		return QueryRunner.getAllCountriesWithAStyle(styleData);
		return QueryRunner.getAllCountriesWithAStyle(filteredStyles);
	}
	
	public List<String> getCountriesWithBrewery(){
//		return QueryRunner.getAllCountriesWithABreweryAverage(breweryData);
		return QueryRunner.getAllCountriesWithABreweryAverage(filteredBreweries); //because is chained...
	}
	
	public List<String> getAllPlaces(){
//		return QueryRunner.getAllPlaces(beerData);
		return QueryRunner.getAllPlaces(filteredBeers);
	}

//	/**
//	 * @return the dialogModel
//	 */
//	public MyModelAbstractDialog getDialogModel() {
//		return dialogModel;
//	}
//
//	/**
//	 * @param dialogModel the dialogModel to set
//	 */
//	public void setDialogModel(MyModelAbstractDialog dialogModel) {
//		this.dialogModel = dialogModel;
//	}

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
//		return styleData;
		return filteredStyles;
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
			beersFilteringCurrentAlgorithm = Utils.getBeerFilteringAlgorithm(BeerFilterAlgorithm.NONE);
		}
		if(brewery){
			filteredBreweries=breweryData;
			breweryFilteringCurrentAlgorithm = Utils.getBreweryAverageFilteringAlgorithm(BreweryFilterAlgorithm.NONE);
		}
		if(style){
			filteredStyles=styleData;
			styleFilteringCurrentAlgorithm = Utils.getStyleFilteringAlgorithm(StyleFilterAlgorithm.NONE);
		}
	}
	
	public void showStyleData(){
		dataShownNow=DataShownNow.STYLE;
		tableModel.clear();
		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		
		applyFilteringToStyles();
		
		applySortingToStyles();
		
//		clearFilter(true, true, false);
		
		tableModel.setData(filteredStyles); //it work||
	}

	/**
	 * @return the beerData
	 */
	public List<Beer> getBeerData() {
//		return beerData;
		return filteredBeers;
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
		
		applyFilteringToBeers();
		
		applySortingToBeers();
		
//		Utils.printBeers(filteredBeers, System.out);
		
		
//		clearFilter(false, true, true);
		
		tableModel.setData(filteredBeers); //it work||
	}


	/**
	 * @return the breweryData
	 */
	public List<Brewery> getBreweryData() {
//		return Utils.fromBreweriesAverageToBrewery(breweryData);
		return Utils.fromBreweriesAverageToBrewery(filteredBreweries);
	}
	
	public List<BreweryAverage> getBreweryAverageData(){
//		return breweryData;
		return filteredBreweries;
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
			List<Beer> itsBeers=BeerFilter.beersFilteredByBrewery(beers, (Brewery)av);
			av.setAverage(itsBeers);
		}
		filteredBreweries=this.breweryData;
	}
	
	public void setAverages(){
//		for(BreweryAverage av: breweryData){
//			List<Beer> itsBeers=BeerFilter.beersFilteredByBrewery(beerData, (Brewery)av);
//			av.setAverage(itsBeers);
//		}
//		
//		filteredBreweries=this.breweryData;
		for(BreweryAverage av: filteredBreweries){
			List<Beer> itsBeers=BeerFilter.beersFilteredByBrewery(beerData, (Brewery)av);
			av.setAverage(itsBeers);
		}
		
//		filteredBreweries=this.breweryData;
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
			
			
			applyFilteringToBreweries();
			
			applySortingToBreweries();
			
			
			tableModel.setData(filteredBreweries); //it work||
		}
		
//		clearFilter(true, false, true);
	}
	
	public void showBreweryAverageData(){
		dataShownNow=DataShownNow.BREWERY_AVERAGE;
		tableModel.clear();
		ModelBreweryAverageTable tableModelOld=(ModelBreweryAverageTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		
		applyFilteringToBreweries();
		
		
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
	
	private void setSorting(){
		beerSortingDefaultAlgorithm = Utils.getBeerSortingAlgorithm(configuration.getBeerSortingAlgorithm());
		brewerySortingDefaultAlgorithm = Utils.getBreweriesSortingAlgorithm(configuration.getBrewerySortingAlgorithm());
		styleSortingDefaultAlgorithm = Utils.getStylesSortingAlgorithm(configuration.getStyleSortingAlgorithm());
		
		beerSortingCurrentAlgorithm=beerSortingDefaultAlgorithm;
		brewerySortingCurrentAlgorithm=brewerySortingDefaultAlgorithm;
		styleSortingCurrentAlgorithm=styleSortingDefaultAlgorithm;
	}
	
	private void setFiltering(){
		beersFilteringDefaultAlgorithm = Utils.getBeerFilteringAlgorithm(configuration.getBeerFilterAlgorithm());
		breweryFilteringDefaultAlgorithm = Utils.getBreweryAverageFilteringAlgorithm(configuration.getBreweryFilterAlgorithm());
		styleFilteringDefaultAlgorithm = Utils.getStyleFilteringAlgorithm(configuration.getStyleFilterAlgorithm());
		
		beerFilteringDefaultValue = configuration.getBeerFilterValue();
		breweryFilteringDefaultValue = configuration.getBreweryFilterValue();
		styleFilteringDefaultValue = configuration.getStyleFilterValue();
		
		beersFilteringCurrentAlgorithm = beersFilteringDefaultAlgorithm;
		breweryFilteringCurrentAlgorithm = breweryFilteringDefaultAlgorithm;
		styleFilteringCurrentAlgorithm = styleFilteringDefaultAlgorithm;
		
		beerFilteringCurrentValue = beerFilteringDefaultValue;
		breweryFilteringCurrentValue = breweryFilteringDefaultValue;
		styleFilteringCurrentValue = styleFilteringDefaultValue;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
//		beerSortingDefaultAlgorithm=Utils.getBeerSortingAlgorithm(configuration.getBeerSortingAlgorithm());
//		brewerySortingDefaultAlgorithm=Utils.getBreweriesSortingAlgorithm(configuration.getBrewerySortingAlgorithm());
//		styleSortingDefaultAlgorithm=Utils.getStylesSortingAlgorithm(configuration.getStyleSortingAlgorithm());
//		
//		beerSortingCurrentAlgorithm=beerSortingDefaultAlgorithm;
//		brewerySortingCurrentAlgorithm=brewerySortingDefaultAlgorithm;
//		styleSortingCurrentAlgorithm=styleSortingDefaultAlgorithm;
		setSorting();
		setFiltering();
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
		breweryShown.setAverage(BeerFilter.beersFilteredByBrewery(beerData, brewery));
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

	
	public List<Style> getOnlyMainStyles(){
		return StyleQuery.onlyMainStyles(styleData);
	}
	
	public List<String> getOnlyMainStylesString(){
		return StyleQuery.onlyMainStylesAsString(styleData);
	}
	
	
//	public void beersSortedByCountryOfBreweryStyle(){
////		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(beerData);
//		beerData=QueryRunner.beersSortedByCountryOfBreweryStyle(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByFermentationCountryOfStyleBrewery(){
//		beerData=QueryRunner.beersSortedByFermentationCountryOfStyleBrewery(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByFermentationStyleCountryOfBrewery(){
//		beerData=QueryRunner.beersSortedByFermentationStyleCountryOfBrewery(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByMarkStarAscending(){
//		beerData=QueryRunner.beersSortedByMarkStarAscending(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByStarMarkAsending(){
//		beerData=QueryRunner.beersSortedByStarMarkAscending(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByMarkStarDescending(){
//		beerData=QueryRunner.beersSortedByMarkStarDescending(filteredBeers);
//		filteredBeers=beerData;
//	}
//	
//	public void beersSortedByStarMarkDesending(){
//		beerData=QueryRunner.beersSortedByStarMarkDescending(filteredBeers);
//		filteredBeers=beerData;
//	}
//
//	public void styleSortedByFermentationThenCountry(){
//		styleData=QueryRunner.styleSortedByFermentationThenCountry(filteredStyles);
//		filteredStyles=styleData;
//	}
//	
//	public void styleSortedByCountryThenFermentationy(){
//		styleData=QueryRunner.styleSortedByCountryThenFermentationy(filteredStyles);
//		filteredStyles=styleData;
//	}
//	
//	
//	
//	
//	public void breweriesSortedByCountryThenName(){
//		breweryData=QueryRunner.breweriesSortedByCountryThenNameWithAverage(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
//	
//	
//	public void breweriesSortedBynName(List<Brewery> breweries){
//		breweryData=QueryRunner.breweriesSortedByNameWithAverage(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
//	
//	
//	public void breweriesSortedByAverageAscending(){
//		breweryData=QueryRunner.breweriesSortedByAverageAscending(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
//	
//	
//	public void breweriesSortedByCountryThenAverageAscending(){
//		breweryData=QueryRunner.breweriesSortedByCountryThenAverageAscending(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
//	
//	public void breweriesSortedByAverageDescending(){
//		breweryData=QueryRunner.breweriesSortedByAverageDescending(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
//	
//	
//	public void breweriesSortedByCountryThenAverageDescending(){
//		breweryData=QueryRunner.breweriesSortedByCountryThenAverageDescending(filteredBreweries);
//		filteredBreweries=breweryData;
//	}
	
	
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

//	public void beersFilteredByStyle(Style style){
//		filteredBeers=QueryRunner.beersFilteredByStyle(filteredBeers, style);
//	}
//	
//	public void beersFilteredByMainStyle(Style style){
//		filteredBeers=QueryRunner.beersFilteredByMainStyle(filteredBeers, style);
//	}
//	
//
//	
//	public void beersFilteredByBrewery(Brewery brewery){
//		filteredBeers=QueryRunner.beersFilteredByBrewery(filteredBeers, brewery);
//	}
//	
//		
//	public void beersFilteredByMiminumMark(int mark){
//		filteredBeers=QueryRunner.beersFilteredByMiminumMark(filteredBeers, mark);
//	}
//
//	public void beersFilteredByExactMark(int mark){
//		filteredBeers=QueryRunner.beersFilteredByExactMark(filteredBeers, mark);
//	}
	
	
	
//	public void beersFilteredByIsTried(boolean isTried){
//		filteredBeers=QueryRunner.beersFilteredByIsTried(filteredBeers, isTried);
//	}
//	
//	
//	
//	public void beersFilteredByMinimumNumberOfStars(int numberOfStar){
//		filteredBeers=QueryRunner.beersFilteredByMinimumNumberOfStars(filteredBeers, numberOfStar);
//	}
//	
//	public void beersFilteredByExactNumberOfStars(int numberOfStar){
//		filteredBeers=QueryRunner.beersFilteredByExactNumberOfStars(filteredBeers, numberOfStar);
//	}
//	
//	
//	
//	public void beersFilteredByMinimumAlcool(double alcool){
//		filteredBeers=QueryRunner.beersFilteredByMinimumAlcool(filteredBeers, alcool);
//	}
//	
//	public void beersFilteredByExatcAlcool(double alcool){
//		filteredBeers=QueryRunner.beersFilteredByExatcAlcool(filteredBeers, alcool);
//	}
//	
//	public void beersFilteredByTrappist(boolean trappist){
//		filteredBeers=QueryRunner.beersFilteredByTrappist(filteredBeers, trappist);
//	}
//	
//	
////	public void beersFilteredByColour(String color){
////		filteredBeers=QueryRunner.beersFilteredByColour(filteredBeers, color);
////	}
////	
//		
//	
//	public void beersFilteredByFermentation(Fermentation fermentation){
//		filteredBeers=QueryRunner.beersFilteredByFermentation(filteredBeers, fermentation);
//	}
//	
//	
//
//	public void beersFilteredByBreweryCountry(String country){	
//		filteredBeers=QueryRunner.beersFilteredByBreweryCountry(filteredBeers, country);
//			
//	}
//	
//	
//	public void beersFilteredByStyleProvenience(String provenience){
//		filteredBeers=QueryRunner.beersFilteredByStyleProvenience(filteredBeers, provenience);
//	}
	
	public static enum ExportType{
		JSON,
		EXCEL_NEW,
		EXCEL_OLD,
		PDF,
		TXT;
		
		public boolean isPriceExportable(){
			return this==EXCEL_OLD || this==EXCEL_NEW || this==TXT;
		}
		
		public static boolean isPriceExportable(ExportType type){
			return type.isPriceExportable();
		}
	}
	
	public void writeTotalPrice(double total, OutputStream out){
		TXTOutExporter exp = (TXTOutExporter)exporter;
		exp.writeTotal(total, out);
	}
	
//	public double getTotalFromSelectedBeers(){
//		return Utils.getSum(filteredBeers);
//	}
	
	public void exportBeers(ExportType export, OutputStream out, boolean writeTotalPrice) throws Exception{
		List<Beer> toExport = setupBeerToExport();
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewBeers(toExport, out, writeTotalPrice);
			break;
		case EXCEL_OLD:
			exportMsExcelOldBeers(toExport, out, writeTotalPrice);
			break;
		case JSON:
			exportJSONBeers(toExport, out);
			break;
		case PDF:
			exportPDFBeers(toExport, out);
			break;
		case TXT:
			exportTXTBeers(toExport, out, writeTotalPrice);
			break;	
		}
	}
	
	public void exportBreweries(ExportType export, OutputStream out) throws Exception{
		List<Brewery> toExport = setupBreweryToExport();
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewBreweries(toExport, out);
			break;
		case EXCEL_OLD:
			exportMsExcelOldBreweries(toExport, out);
			break;
		case JSON:
			exportJSONBreweries(toExport, out);
			break;
		case PDF:
			exportPDFBreweries(toExport, out);
			break;
		case TXT:
			exportTXTBreweries(toExport, out);
			break;	
		}
	}
	
	public void exportStyles(ExportType export, OutputStream out) throws Exception{
		List<Style> toExport = setupStyleToExport();
		switch (export){
		case EXCEL_NEW:
			exportMsExcelNewStyles(toExport, out);
			break;
		case EXCEL_OLD:
			exportMsExcelOldStyles(toExport, out);
			break;
		case JSON:
			exportJSONStyles(toExport, out);
			break;
		case PDF:
			exportPDFStyles(toExport, out);
			break;
		case TXT:
			exportTXTStyles(toExport, out);
			break;	
		}
	}
	
	
	private List<Beer> setupBeerToExport(){
		List<Beer> export = new LinkedList<Beer>(filteredBeers);
		if(!exportAll){
			export = Utils.subListBeer(export, selctedRows);
		}
		return export;
	}
	
	private List<Brewery> setupBreweryToExport(){
		List<Brewery> export = Utils.fromBreweriesAverageToBrewery(filteredBreweries);
		if(!exportAll){
			export = Utils.subListBrewery(export, selctedRows);
		}
		return export;
	}
	
	private List<Style> setupStyleToExport(){
		List<Style> export = new LinkedList<Style>(filteredStyles);
		if(!exportAll){
			export = Utils.subListStyle(export, selctedRows);
		}
		return export;
	}
	
	private void exportJSONBeers(List<Beer> data, OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
		exporter.writeBeer(data, out, false);
	}
	
	private void exportJSONBreweries(List<Brewery> data, OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
//		exporter.writeBrewery(filteredBreweries, out);
		exporter.writeBrewery(data, out);
	}
	
	private void exportJSONStyles(List<Style> data, OutputStream out) throws Exception{
		exporter = new JSONOutExporter();
		exporter.writeStyle(data, out);
	}
	
	
	private void exportMsExcelNewBeers(List<Beer> data,OutputStream out, boolean writeTotalPrice) throws Exception{
		exporter = new MSExcelNewOutExporter();
		exporter.writeBeer(data, out, writeTotalPrice);
	}
	
	private void exportMsExcelNewBreweries(List<Brewery> data, OutputStream out) throws Exception{
		exporter = new MSExcelNewOutExporter();
		exporter.writeBrewery(data, out);
	}
	
	private void exportMsExcelNewStyles(List<Style> data, OutputStream out) throws Exception{
		exporter = new MSExcelNewOutExporter();
		exporter.writeStyle(data, out);
	}
	
	
	private void exportMsExcelOldBeers(List<Beer> data,OutputStream out, boolean writeTotalPrice) throws Exception{
		exporter = new  MSExcelOldOutExporter();
		exporter.writeBeer(data, out, writeTotalPrice);
	}
	
	private void exportMsExcelOldBreweries(List<Brewery> data, OutputStream out) throws Exception{
		exporter = new MSExcelOldOutExporter();
		exporter.writeBrewery(data, out);
	}
	
	private void exportMsExcelOldStyles(List<Style> data, OutputStream out) throws Exception{
		exporter = new MSExcelOldOutExporter();
		exporter.writeStyle(data, out);
	}
	
	private void exportPDFBeers(List<Beer> data,OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportPDFBreweries(List<Brewery> data, OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportPDFStyles(List<Style> data, OutputStream out) throws Exception{
//		exporter = new JSONExporter();
//		exporter.writeStyle(styleData, out);
	}
	
	private void exportTXTBeers(List<Beer> data,OutputStream out, boolean writeTotalPrice) throws Exception{
		exporter = new TXTOutExporter();
		exporter.writeBeer(data, out, writeTotalPrice);
	}
	
	private void exportTXTBreweries(List<Brewery> data, OutputStream out) throws Exception{
		exporter = new TXTOutExporter();
		exporter.writeBrewery(data, out);
	}
	
	private void exportTXTStyles(List<Style> data, OutputStream out) throws Exception{
		exporter = new TXTOutExporter();
		exporter.writeStyle(data, out);
	}
	
	public String getLastDirectory(){
		return System.getProperty("user.home");
	}
	
	
	/**
	 * @return the beersFilteringCurrentAlgorithm
	 */
	public BiFunction<List<Beer>, Object, List<Beer>> getBeersFilteringCurrentAlgorithm() {
		return beersFilteringCurrentAlgorithm;
	}

	/**
	 * @param beersFilteringCurrentAlgorithm the beersFilteringCurrentAlgorithm to set
	 */
	public void setBeersFilteringCurrentAlgorithm(
			BiFunction<List<Beer>, Object, List<Beer>> beersFilteringCurrentAlgorithm) {
		this.beersFilteringCurrentAlgorithm = beersFilteringCurrentAlgorithm;
	}

	/**
	 * @return the breweryFilteringCurrentAlgorithm
	 */
	public BiFunction<List<BreweryAverage>, Object, List<BreweryAverage>> getBreweryFilteringCurrentAlgorithm() {
		return breweryFilteringCurrentAlgorithm;
	}

	/**
	 * @param breweryFilteringCurrentAlgorithm the breweryFilteringCurrentAlgorithm to set
	 */
	public void setBreweryFilteringCurrentAlgorithm(
			BiFunction<List<BreweryAverage>, Object, List<BreweryAverage>> breweryFilteringCurrentAlgorithm) {
		this.breweryFilteringCurrentAlgorithm = breweryFilteringCurrentAlgorithm;
	}

	/**
	 * @return the styleFilteringCurrentAlgorithm
	 */
	public BiFunction<List<Style>, Object, List<Style>> getStyleFilteringCurrentAlgorithm() {
		return styleFilteringCurrentAlgorithm;
	}

	/**
	 * @param styleFilteringCurrentAlgorithm the styleFilteringCurrentAlgorithm to set
	 */
	public void setStyleFilteringCurrentAlgorithm(
			BiFunction<List<Style>, Object, List<Style>> styleFilteringCurrentAlgorithm) {
		this.styleFilteringCurrentAlgorithm = styleFilteringCurrentAlgorithm;
	}

	/**
	 * @return the beerFilteringCurrentValue
	 */
	public Object getBeerFilteringCurrentValue() {
		return beerFilteringCurrentValue;
	}

	/**
	 * @param beerFilteringCurrentValue the beerFilteringCurrentValue to set
	 */
	public void setBeerFilteringCurrentValue(Object beerFilteringCurrentValue) {
		this.beerFilteringCurrentValue = beerFilteringCurrentValue;
	}

	/**
	 * @return the breweryFilteringCurrentValue
	 */
	public Object getBreweryFilteringCurrentValue() {
		return breweryFilteringCurrentValue;
	}

	/**
	 * @param breweryFilteringCurrentValue the breweryFilteringCurrentValue to set
	 */
	public void setBreweryFilteringCurrentValue(Object breweryFilteringCurrentValue) {
		this.breweryFilteringCurrentValue = breweryFilteringCurrentValue;
	}

	/**
	 * @return the styleFilteringCurrentValue
	 */
	public Object getStyleFilteringCurrentValue() {
		return styleFilteringCurrentValue;
	}

	/**
	 * @param styleFilteringCurrentValue the styleFilteringCurrentValue to set
	 */
	public void setStyleFilteringCurrentValue(Object styleFilteringCurrentValue) {
		this.styleFilteringCurrentValue = styleFilteringCurrentValue;
	}

	public void applyFilteringToBeers(){
		filteredBeers = beersFilteringCurrentAlgorithm.apply(filteredBeers, beerFilteringCurrentValue);
	}
	
	
	public void applyFilteringToBreweries(){
		filteredBreweries = breweryFilteringCurrentAlgorithm.apply(filteredBreweries, breweryFilteringCurrentValue);
	}
	
	
	public void applyFilteringToStyles(){
		filteredStyles = styleFilteringCurrentAlgorithm.apply(filteredStyles, styleFilteringCurrentValue);
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
//		System.out.println(":::::::::::::::::The style sorted for binary search:::::::::::");
//		Utils.printStyles(tempStyleSortedList, System.out);
		int pos = QueryRunner.BinarySearch.styleSearch(tempStyleSortedList, key, true);
//		System.out.println("Position is: "+pos);
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
			filteredBeers = beerData; /*when refresh keep the filter*/
			somethingToSave = true;
			saveBeer = true;
		}
		else{
//			throw new UpdateSavingException(beer, UpdateSavingException.ErrorWhile.ADDING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(beer, ErrorWhile.ADDING));
		}
	}
	
	public void addNewBrewery(Brewery brewery) throws UpdateSavingException{
		if(!QueryRunner.BinarySearch.isBreweryExists(Utils.fromBreweriesAverageToBrewery(breweryData), brewery, false)){
			breweryData.add(Utils.fromBreweryToBreweryAverage(brewery));
			filteredBreweries = breweryData;
			somethingToSave = true;
			saveBrewery = true;
		}
		else{
//			throw new UpdateSavingException(brewery, UpdateSavingException.ErrorWhile.ADDING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(brewery, ErrorWhile.ADDING));
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
			somethingToSave = true;
			saveStyle = true;
		}
		else{
//			throw new UpdateSavingException(style, UpdateSavingException.ErrorWhile.ADDING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(style, ErrorWhile.ADDING));
		}

	}
	
	public void updateBeer(Beer newBeer) throws UpdateSavingException{
		if(beerData.remove(beerShown)){
			beerData.add(newBeer);
			filteredBeers = beerData;
			somethingToSave = true;
			saveBeer = true;
		}
		else{
//			throw new UpdateSavingException(newBeer, UpdateSavingException.ErrorWhile.UPDATING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(newBeer, ErrorWhile.UPDATING));
		}

	}
	
	public void updateBrewery(BreweryAverage newBrewery) throws UpdateSavingException{
		if(breweryData.remove(breweryShown)){
			breweryData.add(newBrewery);
			filteredBreweries = breweryData;
			
			List<Beer> beersToUpdate = BeerFilter.beersFilteredByBrewery(beerData, breweryShown);
			if(!beersToUpdate.isEmpty()){
				for(Beer b : beersToUpdate){
					b.setBrewery(newBrewery);
				}
				saveBeer = true;
			}
			
			somethingToSave = true;
			saveBrewery = true;
		}
		else{
//			throw new UpdateSavingException(newBrewery, UpdateSavingException.ErrorWhile.UPDATING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(newBrewery, ErrorWhile.UPDATING));
		}

	}
	
	public void updateStyle(Style newStyle) throws UpdateSavingException{
		if(styleData.remove(styleShown)){
			styleData.add(newStyle);
			filteredStyles = styleData;
			
			List<Beer> beersToUpdate = BeerFilter.beersFilteredByStyle(beerData, styleShown);
			if(!beersToUpdate.isEmpty()){
				for(Beer b : beersToUpdate){
					b.setStyle(newStyle);
				}
				saveBeer = true;
			}
			
			somethingToSave = true;
			saveStyle = true;
		}
		else{
//			throw new UpdateSavingException(newStyle, UpdateSavingException.ErrorWhile.UPDATING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(newStyle, ErrorWhile.UPDATING));
		}

	}
	
	public void deleteBeers(List<Beer> beers) throws UpdateSavingException{
		for(Beer b : beers){
			deleteBeer(b);
		}
	}
	
	public void deleteBreweries(List<BreweryAverage> breweries) throws UpdateSavingException{
		for(BreweryAverage b: breweries){
			deleteBrewery(b);
		}
	}
	
	public void deleteStyles(List<Style> styles) throws UpdateSavingException{
		for(Style s: styles){
			deleteStyle(s);
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
			
			somethingToSave = true;
			saveBeer = true;
		}
		else{
//			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.DELETING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(b, ErrorWhile.DELETING));
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
			
			List<Beer> beersToDelete = BeerFilter.beersFilteredByBrewery(beerData, Utils.fromBreweryAverageToBrewery(toDelete));
			if(!beersToDelete.isEmpty()){
				try{
					deleteBeers(beersToDelete);
				}catch(UpdateSavingException e){
					breweryData.add(toDelete);
//					throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.DELETING);
					throw new UpdateSavingException(UpdateSavingException.createMessage(toDelete, ErrorWhile.DELETING_TRAVERSAL));
				}
			}
			
			somethingToSave = true;
			saveBrewery = true;
			saveBeer = true;
		}
		else{
//			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.UPDATING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(toDelete, ErrorWhile.DELETING));
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
			
			List<Beer> beersToDelete = BeerFilter.beersFilteredByStyle(beerData, toDelete);
			if(!beersToDelete.isEmpty()){
				try{
					deleteBeers(beersToDelete);
				}catch(UpdateSavingException e){
					styleData.add(toDelete);
//					throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.DELETING_TRAVERSAL);
					throw new UpdateSavingException(UpdateSavingException.createMessage(s, ErrorWhile.DELETING_TRAVERSAL));
				}
				
			}
			
			
			somethingToSave = true;
			saveStyle = true;
			saveBeer = true;
		}
		else{
//			throw new UpdateSavingException(toDelete, UpdateSavingException.ErrorWhile.UPDATING);
			throw new UpdateSavingException(UpdateSavingException.createMessage(toDelete, ErrorWhile.DELETING));
		}
	}
	
	
	
	private void saveBeers() throws JSONException, FileNotFoundException{
		JSONExporterCoreClasses.writeBeerSpecial(beerData, new FileOutputStream(configuration.getBeerFilePath()));
	}
	
	private void saveBreweries() throws FileNotFoundException, FileNotFoundException{
		JSONExporterCoreClasses.writeBrewery(Utils.fromBreweriesAverageToBrewery(breweryData), new FileOutputStream(configuration.getBreweryFilePath()));
	}
	
	private void saveStyles() throws JSONException, FileNotFoundException{
		JSONExporterCoreClasses.writeStyleSpecial(styleData, new FileOutputStream(configuration.getStyleFilePath()));
	}
	
	/**
	 * Read the breweries from file.
	 * @return
	 * @throws FileNotFoundException
	 * @throws JSONException
	 */
	private List<Brewery> readBreweries() throws FileNotFoundException, JSONException{
		List<Brewery> breweries = Utils.readBreweries(new File(configuration.getBreweryFilePath()));
		setBreweryData(breweries);
		return breweries;
	}
	
	/**
	 * Read the styles from file.
	 * @throws FileNotFoundException
	 * @throws JSONException
	 */
	private void readStyles() throws FileNotFoundException, JSONException{
		List<Style> styles = Utils.readStyles(new File(configuration.getStyleFilePath()));
		setStyleData(styles);
	}
	
	/**
	 * Read the beers, saved to save space.
	 * Please note that styles must be read first of this.
	 * @param breweries	the breweries to be linked with the beers.
	 * @throws FileNotFoundException	if the file where beers are does not exist
	 * @throws JSONException			if the JSON parsing fails.
	 * @throws RecomposingException		if the linking between beers, styles, breweries fails. 
	 */
	private void readBeers(List<Brewery> breweries) throws FileNotFoundException, JSONException, RecomposingException{
//		List<Brewery> breweries = Utils.readBreweries(new File(configuration.getBreweryFilePath()));
//		List<Style> styles = Utils.readStyles(new File(configuration.getStyleFilePath()));
		if(styleData == null){
			throw new RuntimeException("You must have already read the style before doing this!"); //should never happen
		}
		List<Beer> beers=Utils.readBeersFromSpecial(new File(configuration.getBeerFilePath()), breweries, styleData);
		setBeerData(beers);
//		setBreweryData(breweries);
//		setStyleData(styles);
	}
	
	/**
	 * Read all the data from the files. 
	 * Please note: the program does NOT keeps track of the countries, because no linking is done between breweries and countries.
	 * A modify to the country file can cause horrible problems, so do not touch it please.
	 * @throws FileNotFoundException	if the beers, or the breweries, or the styles files are not present.
	 * @throws JSONException			if during the parsing some errors occur.
	 * @throws RecomposingException		if the linking between beers, breweries and styles fails.
	 */
	public void readThings() throws FileNotFoundException, JSONException, RecomposingException{
		List<Brewery> breweries=readBreweries();
		readStyles();
		readBeers(breweries); //so I do not need to convert.
	}
	
	/**
	 * Save the lists to the file. After some work, this method is smart: this class keeps track of what has been modified
	 * from the last saving, and so it saves only what is not already "committed".
	 * @throws JSONException		if the conversion of the data into JSON  fails.
	 * @throws FileNotFoundException	if the fail where things should be saved does not exist.
	 */
	public void saveThings() throws JSONException, FileNotFoundException {
		if(saveBeer){
			saveBeers();
			saveBeer = false;
		}
		
		if(saveBrewery){
			saveBreweries();
			saveBrewery = false;
		}
		
		
		if(saveStyle){
			saveStyles();
			saveStyle = false;
		}
		
		somethingToSave = false;
	}
	
	/**
	 * Saves the current configuration to file.
	 * @throws FileNotFoundException	if the configuration file is not present.
	 */
	public void saveConfiguration() throws FileNotFoundException{
		ConfigurationFactory.writeConfiguration(configuration, ConfigurationFactory.getDefaultConfigurationPath());

	}
	
	@Deprecated
	/**
	 * Use instead the ControllerMainGUI.showDefault();
	 */
	public void applyDefaultView(){
		switch(configuration.getDefaultView()){
		case BEER:
			showBeerData();
			break;
		case BREWERY:
			showBreweryData();
			break;
		case STYLE:
			showStyleData();
			break;
		
		}
	}
	
	public ShowDefault getDefaultView(){
		return configuration.getDefaultView();
	}
	
	public void resetBeerFilter(){
		clearFilter(true, false, false);
	}
	
	public void resetBreweryFilter(){
		clearFilter(false, true, false);
	}
	
	public void resetStyleFilter(){
		clearFilter(false, false, true);
	}
	
	public List<Brewery> getBreweryDataAlphaOrder(){
		List<Brewery> sorted = BrewerySort.breweriesSortedByName(Utils.fromBreweriesAverageToBrewery(breweryData));
		return sorted;
	}
	
	public List<Style> getStyleDataMainSubOrder(){
		List<Style> sorted = StyleQuery.StyleSort.stylesSortedByMainCategorySubCategory(styleData);
		return sorted;
	}
	
	
	public void setExportSelectedThings(boolean exportAll){
		this.exportAll = exportAll;
		
	}
	
	public void setSelectedRows(int [] selectedRows){
		this.selctedRows = selectedRows;
	}
	
	
	public void importBeers(File f) throws FileNotFoundException, JSONException, RecomposingException{
		List<Beer> beerDiff = JSONImporter.getBeersDifference(beerData,  f);
//		System.out.println("Le birre diiff:");
//		Utils.printBeers(beerDiff, System.out);
		if(!beerDiff.isEmpty()){
			beerData.addAll(beerDiff);
			filteredBeers = beerData;
		}
		
		//now add the breweries if necessary.
		
		List<Brewery> breweryTemp = BeerQuery.getAllBreweries(beerDiff);
//		System.out.println("I birrifici ottenuti:");
//		Utils.printBreweries(breweryTemp, System.out);
//		
//		
//		System.out.println("Il birrificio esiste? ");
//		System.out.println(QueryRunner.BinarySearch.isBreweryExists(Utils.fromBreweriesAverageToBrewery(breweryData), breweryTemp.get(0), false));
		
		wrapperImportBreweries(null, breweryTemp);
		
		List<Style> styleTemp = BeerQuery.getAllStyles(beerDiff);
		
		
//		importStylesBridge(styleTemp);
		wrapperImportStyles(null, styleTemp);
//		beerSortingCurrentAlgorithm.apply(beerData);
//		clearFilter(false, true, true);
	}
	
	private void importBreweriesBridge(List<Brewery> breweriesDiff){
		if(!breweriesDiff.isEmpty()){
			breweryData.addAll(Utils.fromBreweriesToBreweriesAverage(breweriesDiff));
			setAverages();
			filteredBreweries = breweryData;
		}
	}
	
	private List<Brewery> getBreweryDiffFromFile(File f) throws FileNotFoundException, JSONException{
		List<Brewery> breweriesDiff = JSONImporter.getBreweriesDifference(Utils.fromBreweriesAverageToBrewery(breweryData), f);
		return breweriesDiff;
	}
	
	
	public void wrapperImportBreweries(File f, List<Brewery> allBreweries) throws FileNotFoundException, JSONException{
		
//		System.out.println("Il param è: ");
//		Utils.printBreweries(allBreweries, System.out);
//		
//		System.out.println("Il birrificio esiste dentro il param? ");
//		System.out.println(QueryRunner.BinarySearch.isBreweryExists(Utils.fromBreweriesAverageToBrewery(breweryData), allBreweries.get(0), false));
		
		List<Brewery> breweries;
		if(f!=null){
			breweries = getBreweryDiffFromFile(f);
		}
		else{
			breweries =  QueryRunner.Diff.breweryDiff(Utils.fromBreweriesAverageToBrewery(breweryData), allBreweries, false, false);
//			System.out.println("Differenza birrifici::");
//			Utils.printBreweries(breweries, System.out);
		}
		importBreweriesBridge(breweries);
	}
	
//	public void importBreweries(File f) throws FileNotFoundException, JSONException{
//		List<Brewery> breweriesDiff = JSONImporter.getBreweriesDifference(Utils.fromBreweriesAverageToBrewery(breweryData), f);
//		if(!breweriesDiff.isEmpty()){
//			breweryData.addAll(Utils.fromBreweriesToBreweriesAverage(breweriesDiff));
//			setAverages();
//			filteredBreweries = breweryData;
//		}
//		
////		brewerySortingCurrentAlgorithm.apply(breweryData);
////		clearFilter(true, false, true);
//	}
	
	private List<Style> getStyleDiffFromFile(File f) throws FileNotFoundException, JSONException{
		List<Style> stylesDiff = JSONImporter.getStylesDifference(styleData, f);
		return stylesDiff;
	}
	
	private void importStylesBridge(List<Style> stylesDiff){
		if(!stylesDiff.isEmpty()){
			styleData.addAll(stylesDiff);
			filteredStyles = styleData;
			
		}
	}
	
	public void wrapperImportStyles(File f, List<Style> allStyles) throws FileNotFoundException, JSONException{
		List<Style> styles;
		if(f!=null){
			styles = getStyleDiffFromFile(f);
		}
		else{
			styles =  QueryRunner.Diff.styleDiff(styleData, allStyles, false, false);
		}
		
		importStylesBridge(styles);
	}
	
//	public void importStyles(File f) throws FileNotFoundException, JSONException{
//		List<Style> stylesDiff = JSONImporter.getStylesDifference(styleData, f);
//		if(!stylesDiff.isEmpty()){
//			styleData.addAll(stylesDiff);
//			filteredStyles = styleData;
//			
//		}
//	}
		
//		styleSortingCurrentAlgorithm.apply(styleData);
//		clearFilter(true, true, false);
		
//	}
//
//	/**
//	 * @return the labelTable
//	 */
//	public Hashtable<Integer, JLabel> getLabelTable() {
//		return labelTable;
//	}

	/**
	 * @return the spinnerModel
	 */
//	public SpinnerNumberModel getSpinnerModel(ViewAddNewBeer.SpinnerType type) {
//		SpinnerNumberModel ret = null;
////		switch(type){
////		case ABV:
////			ret = spinnerABVModel;
////			break;
////		case MARK:
////			ret = spinnerMarkModel;
////			break;
////		case PRICE:
////			ret = spinnerPriceModel;
////			break;	
////		}
//		switch(type){
//		case ABV:
////			ret = abvSpinner;
//			ret = new CustomSpinnerModel.ABVSpinnerNumberModel();
//			break;
//		case MARK:
////			ret =  markSpinner;
//			ret = new CustomSpinnerModel.MarkSpinnerNumberModel();
//			break;
//		case PRICE:
////			ret = priceSpinner;
//			ret = new CustomSpinnerModel.PriceSpinnerNumberModel();
//			break;	
//		}
//		return ret;
//	}
	
	
	public static SpinnerNumberModel[] getSpinnerModelAsArray(){
		SpinnerNumberModel [] models = new SpinnerNumberModel[3];
//		models[0]=new CustomSpinnerModel.MarkSpinnerNumberModel();
//		models[1]=new CustomSpinnerModel.ABVSpinnerNumberModel();
//		models[2]=new CustomSpinnerModel.PriceSpinnerNumberModel();
		models[0]=new SpinnerNumberModel(Utils.Constants.MARK_SPINNER_DEF_VALUE , Utils.Constants.MARK_SPINNER_MIN_VALUE, 
				Utils.Constants.MARK_SPINNER_MAX_VALUE, Utils.Constants.MARK_SPINNER_STEP_VALUE);
		models[1]=new SpinnerNumberModel(Utils.Constants.ABV_SPINNER_DEF_VALUE, Utils.Constants.ABV_SPINNER_MIN_VALUE, 
				Utils.Constants.ABV_SPINNER_MAX_VALUE, Utils.Constants.ABV_SPINNER_STEP_VALUE);
		models[2]=new SpinnerNumberModel(Utils.Constants.PRICE_SPINNER_DEF_VALUE, Utils.Constants.PRICE_SPINNER_MIN_VALUE, 
				Utils.Constants.PRICE_SPINNER_MAX_VALUE, Utils.Constants.PRICE_SPINNER_STEP_VALUE);
		return models;
	}
	
	public static void saveException(String exception, File f) throws FileNotFoundException{
		PrintStream out = new PrintStream(new FileOutputStream(f));
		out.print(exception);
		out.close();
	}
	
	public List<Brewery> getBreweryDataSortedByName(){
		return BrewerySort.breweriesSortedByName(getBreweryData());
	}
	
	public String getLicense(){
		return Utils.readLicense(Utils.gplPath(configuration.getPwd()));
	}
	
	public boolean enableExportForBeers(){
		return !filteredBeers.isEmpty();
	}
	
	public boolean enableExportForBreweries(){
		return !filteredBreweries.isEmpty();
	}
	
	public boolean enableExportForStyles(){
		return !filteredStyles.isEmpty();
	}

}
