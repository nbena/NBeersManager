package org.nbena.beersmanager.exe.ui.models;

import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.util.stream.Collectors;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.export.Exporter;
import org.nbena.beersmanager.export.JSONExporter;
import org.nbena.beersmanager.export.PDFExporter;
import org.nbena.beersmanager.export.TXTExporter;
import org.nbena.beersmanager.export.MSExcelOldExporter;
import org.nbena.beersmanager.export.MSExcelNewExporter;
import org.nbena.beersmanager.query.Comparators;
import org.nbena.beersmanager.query.QueryRunner;

public class Model {
	

	public static enum DataShownNow{
		BEER,
		BREWERY,
		STYLE
	}
	
	public static enum DialogShownNow{
		BEER,
		BREWERY,
		STYLE
	}
	
	private MyModelAbstractTable tableModel;
	
	private MyModelAbstractDialog dialogModel;
	
	private List<Style> styleData;
	private List<Beer> beerData;
	private List<Brewery> breweryData;
	
	private List<String> countries;
	
	private Beer beerDialog;
	private Brewery breweryDialog;
	private Style styleDialog;
	
	private List<Beer> filteredBeers;
	private List<Brewery> filteredBreweries;
	private List<Style> filteredstyles;
	
	private DataShownNow dataShownNow;
	private DialogShownNow dialogShown;
	
	private Exporter exporter;
	
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
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(MyModelAbstractTable tableModel) {
		this.tableModel = tableModel;
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
		filteredstyles=styleData;
	}
	
//	public void setStyleDataAndShow(List<Style> styleData){
//		this.styleData = styleData;	
//		dataShownNow=DataShownNow.STYLE;
//		tableModel.clear();
//		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
//		tableModel=tableModelOld;
//		tableModel.setData(styleData); //it work||
//	}
	
	public void showStyleData(){
		dataShownNow=DataShownNow.STYLE;
		tableModel.clear();
		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		tableModel.setData(styleData); //it work||
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
		ModelBeerTable tableModelOld=(ModelBeerTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		tableModel.setData(beerData); //it work||
	}

	/**
	 * @return the breweryData
	 */
	public List<Brewery> getBreweryData() {
		return breweryData;
	}

	/**
	 * @param breweryData the breweryData to set
	 */
	public void setBreweryData(List<Brewery> breweryData) {
		this.breweryData = breweryData;
		filteredBreweries=breweryData;
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
		dataShownNow=DataShownNow.BREWERY;
		tableModel.clear();
		ModelBreweryTable tableModelOld=(ModelBreweryTable)tableModel;
		tableModel=tableModelOld;
//		tableModel.fireTableStructureChanged();
		tableModel.setData(breweryData); //it work||
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
			return beerData.get(index);
		}
		else
			throw new RuntimeException("Beer is not in the table");
		//return null;
	}
	
	public Style getSelectedStyle(int index){
		if (dataShownNow==DataShownNow.STYLE){
			//Style s = (Style)tableModel.getSelectedObject(index);
			//return s;
			return styleData.get(index);
		}
		else
			throw new RuntimeException("Style is not in the table");
		//return null;
	}
	
	public Brewery getSelectedBrewery(int index){
		if (dataShownNow==DataShownNow.BREWERY){
			//Brewery b = (Brewery)tableModel.getSelectedObject(index);
			//return b;
			return breweryData.get(index);
		}
		else
			throw new RuntimeException("Brewery is not in the table");
		//return null;
	}

	@Deprecated
	/**
	 * @return the beerDialog
	 */
	public Beer getBeerDialog() {
		return beerDialog;
	}

	@Deprecated
	/**
	 * @param beerDialog the beerDialog to set
	 */
	public void setBeerDialog(Beer beerDialog) {
		this.beerDialog = beerDialog;
	}

	@Deprecated
	/**
	 * @return the breweryDialog
	 */
	public Brewery getBreweryDialog() {
		return breweryDialog;
	}

	@Deprecated
	/**
	 * @param breweryDialog the breweryDialog to set
	 */
	public void setBreweryDialog(Brewery breweryDialog) {
		this.breweryDialog = breweryDialog;
	}

	@Deprecated
	/**
	 * @return the styleDialog
	 */
	public Style getStyleDialog() {
		return styleDialog;
	}

	@Deprecated
	/**
	 * @param styleDialog the styleDialog to set
	 */
	public void setStyleDialog(Style styleDialog) {
		this.styleDialog = styleDialog;
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

	
	
	
	public void beersFilteredByStyle(Style style){
		filteredBeers=QueryRunner.beersFilteredByStyle(filteredBeers, style);
	}
	
	public void beersFilteredByMainStyle(Style style){
		filteredBeers=QueryRunner.beersFilteredByMainStyle(filteredBeers, style);
	}
	

	
	public void beersFilteredByBrewery(Brewery brewery){
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
		exporter = new JSONExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportJSONBreweries(OutputStream out) throws Exception{
		exporter = new JSONExporter();
		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportJSONStyles(OutputStream out) throws Exception{
		exporter = new JSONExporter();
		exporter.writeStyle(styleData, out);
	}
	
	
	private void exportMsExcelNewBeers(OutputStream out) throws Exception{
		exporter = new MSExcelNewExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportMsExcelNewBreweries(OutputStream out) throws Exception{
		exporter = new MSExcelNewExporter();
		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportMsExcelNewStyles(OutputStream out) throws Exception{
		exporter = new MSExcelNewExporter();
		exporter.writeStyle(styleData, out);
	}
	
	
	private void exportMsExcelOldBeers(OutputStream out) throws Exception{
		exporter = new  MSExcelOldExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportMsExcelOldBreweries(OutputStream out) throws Exception{
		exporter = new MSExcelOldExporter();
		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportMsExcelOldStyles(OutputStream out) throws Exception{
		exporter = new MSExcelOldExporter();
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
		exporter = new TXTExporter();
		exporter.writeBeer(filteredBeers, out);
	}
	
	private void exportTXTBreweries(OutputStream out) throws Exception{
		exporter = new TXTExporter();
		exporter.writeBrewery(filteredBreweries, out);
	}
	
	private void exportTXTStyles(OutputStream out) throws Exception{
		exporter = new TXTExporter();
		exporter.writeStyle(styleData, out);
	}
	
	public String getLastDirectory(){
		return System.getProperty("user.home");
	}
	
	

}
