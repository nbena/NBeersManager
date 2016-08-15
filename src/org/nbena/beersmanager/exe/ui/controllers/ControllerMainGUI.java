package org.nbena.beersmanager.exe.ui.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.conf.ConfigurationFactory;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.ui.models.Model;
import org.nbena.beersmanager.exe.ui.models.Model.DataShownNow;
import org.nbena.beersmanager.exe.ui.models.Model.ExportType;
import org.nbena.beersmanager.exe.ui.models.ModelBeerTable;
import org.nbena.beersmanager.exe.ui.models.ModelBreweryAverage;
import org.nbena.beersmanager.exe.ui.models.ModelBreweryTable;
import org.nbena.beersmanager.exe.ui.models.ModelStyleTable;
import org.nbena.beersmanager.exe.ui.views.BeerDialog;
import org.nbena.beersmanager.exe.ui.views.BreweryDialog;
import org.nbena.beersmanager.exe.ui.views.StyleDialog;
import org.nbena.beersmanager.exe.ui.views.ViewAbstractDialog;
import org.nbena.beersmanager.exe.ui.views.ViewAddNewBeer;
import org.nbena.beersmanager.exe.ui.views.ViewAddNewBrewery;
import org.nbena.beersmanager.exe.ui.views.ViewAddNewStyle;
import org.nbena.beersmanager.exe.ui.views.ViewMainGUI;
import org.nbena.beersmanager.exe.ui.views.ViewPreferences;
import org.nbena.beersmanager.exe.ui.views.ViewViewBeer;
import org.nbena.beersmanager.exe.ui.views.ViewViewBrewery;
import org.nbena.beersmanager.exe.ui.views.ViewViewStyle;
import org.nbena.beersmanager.query.BreweryAverage;
import org.nbena.beersmanager.query.QueryRunner;

public class ControllerMainGUI {
	
	private ViewMainGUI gui;
	private Model model;
	
	private ViewAddNewBeer addBeerDialog;
	private ViewAddNewBrewery addBreweryDialog;
	private ViewAddNewStyle addStyleDialog;
	
	private ViewViewBeer viewBeerDialog;
	private ViewViewBrewery viewBreweryDialog;
	private ViewViewStyle viewStyleDialog;
	
	private ViewPreferences preferencesDialog;

	

	public ControllerMainGUI(ViewMainGUI gui, Model model) {
		this.gui=gui;
		this.model=model;
		
		addListeners();
		
		gui.setVisible(true);
	}
	
	private void addListeners(){
		addFileExporterListeners();
		addAddNewThingsListeners();
		addListSelectionListener();
		addViewListeners();
		addSortBeersListeners();
		addSortStylesListeners();
		addSortBreweriesListeners();
		addFilterBeersListeners();
		addSearchMenuListeners();
		addOptionsMenuListeners();
		addActionMenuPreferencesListener();

	}
	
	public void showBeers(){
		enableShowBeersItems();
		
		model.setTableModel(new ModelBeerTable());
		model.showBeerData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void showBreweries(){
		if(model.isShowAlsoAverage()){
			showBreweriesAverage();
		}else{
			showBreweriesNormal();
		}
	}
	
	public void showBreweriesNormal(){
		enableShowBreweriesItems();
		
		model.setTableModel(new ModelBreweryTable());
		model.showBreweryData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void showBreweriesAverage(){
		enableShowBreweriesItems();
		
		model.setTableModel(new ModelBreweryAverage());
		model.showBreweryData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void showStyles(){
		enableShowStylesItems();
		
		model.setTableModel(new ModelStyleTable());
		model.showStyleData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void setShowBreweriesAverages(boolean show){
		model.setShowAlsoAverage(show);
	}
	
	private void enableShowBeersItems(){
		gui.setQueryEnabledItemsBeer(true);
		gui.setQueryEnabledItemsBrewerie(false);
		gui.setQueryEnabledItemsStyle(false);
	}
	
	private void enableShowBreweriesItems(){
		gui.setQueryEnabledItemsBeer(false);
		gui.setQueryEnabledItemsBrewerie(true);
		gui.setQueryEnabledItemsStyle(false);
	}
	
	private void enableShowStylesItems(){
		gui.setQueryEnabledItemsBeer(false);
		gui.setQueryEnabledItemsBrewerie(false);
		gui.setQueryEnabledItemsStyle(true);
	}
	
	public void beersFilteredByBrewery(Brewery b){
		model.beersFilteredByBrewery(b);
		showBeers();
	}
	
	public void beersFilteredByBreweryCountry(String country){
		model.beersFilteredByBreweryCountry(null);
		showBeers();
	}
	
	public void beersFilteredByExactABV(double abv){
		model.beersFilteredByExatcAlcool(abv);
		showBeers();
	}
	
	public void beersFilteredByMinimumABV(double abv){
		model.beersFilteredByMinimumAlcool(abv);
		showBeers();
	}
	
	public void beersFilteredByMinimumMaek(int mark){
		model.beersFilteredByMiminumMark(mark);
		showBeers();
	}
	
	public void beersFilteredByExactMark(int mark){
		model.beersFilteredByExactMark(mark);
		showBeers();
	}
	
	public void beersFilteredByMinimumNumberOfStars(int number){
		model.beersFilteredByMinimumNumberOfStars(number);
		showBeers();
	}
	
	public void beersFilteredByExactNumberOfStars(int number){
		model.beersFilteredByExactNumberOfStars(number);
		showBeers();
	}
	
	public void beersFilteredByFermentation(Fermentation f){
		model.beersFilteredByFermentation(f);
		showBeers();
	}
	
	public void beersFilteredByStyle(Style s){
		model.beersFilteredByStyle(s);
		showBeers();
	}
	
	public void beersFilteredByMainStyle(Style s){
		model.beersFilteredByMainStyle(s);
		showBeers();
	}
	
	public void beeersFilteredByStyleProvenience(String s){
		model.beersFilteredByStyleProvenience(s);
		showBeers();
	}
	
	public void beersFilteredByTrappist(boolean trappist){
		model.beersFilteredByTrappist(trappist);
		showBeers();
	}
	
	public void beersFilteredByIsTried(boolean tried){
		model.beersFilteredByIsTried(tried);
		showBeers();
	}
	
	
	private void addFilterBeersListeners(){
		
		gui.addActionMenuBeersFilteredByBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByBrewery(null);
//				showBeers();
				beersFilteredByBrewery(null);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByBreweryCountry(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByBreweryCountry(null);
//				showBeers();
				beersFilteredByBrewery(null);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByExatcAlcool(0.0);
//				showBeers();
				beersFilteredByExactABV(0.0);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByExactMark(0);
//				showBeers();
				beersFilteredByExactMark(0);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationHigh(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beerFilteredByFermentation(Fermentation.HIGH);
//				showBeers();
				beersFilteredByFermentation(Fermentation.HIGH);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationLow(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beerFilteredByFermentation(Fermentation.LOW);
//				showBeers();
				beersFilteredByFermentation(Fermentation.LOW);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationSpontaneous(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beerFilteredByFermentation(Fermentation.SPONTANEOUS);
//				showBeers();
				beersFilteredByFermentation(Fermentation.SPONTANEOUS);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTrappistNo(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByTrappist(false);
//				showBeers();
				beersFilteredByTrappist(false);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTrappistYes(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByTrappist(true);
//				showBeers();
				beersFilteredByTrappist(true);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTriedYes(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByIsTried(true);
//				showBeers();
				beersFilteredByIsTried(true);
			}
			
		});
		
		gui.addActionMenuBeersFilteredIsTriedNo(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByIsTried(false);
//				showBeers();
				beersFilteredByIsTried(false);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMainStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMainStyle(null);
//				showBeers();
				beersFilteredByMainStyle(null);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMinimumAlcool(0.0);
//				showBeers();
				beersFilteredByMinimumABV(0);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMiminumMark(0);
//				showBeers();
				beersFilteredByMinimumMaek(0);
			}
			
		});
		
		gui.addActionMenuBeersFilteredByStyleProvenience(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByStyleProvenience(null);
//				showBeers();
				beeersFilteredByStyleProvenience(null);
			}
			
		});
		
		gui.addActionMenuBeersFilteredExactNumberOfStars(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByExactNumberOfStars(0);
//				showBeers();
				beersFilteredByExactNumberOfStars(0);
			}
			
		});
		
		gui.addActionMenuBeersFilteredMinimumNumberOfStars(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMinimumNumberOfStars(0);
//				showBeers();
				beersFilteredByMinimumNumberOfStars(0);
			}
			
		});
		
		
	}
	
	
	private void addViewListeners(){
		gui.addActionListenerViewBeer(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				enableShowBeersItems();
//				
//				model.setTableModel(new ModelBeerTable());
//				model.showBeerData();
//				
//				gui.setTableModel(model.getTableModel());
				
				showBeers();
			
			}
			
		});
		
		gui.addActionListenerViewBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
//				enableShowBreweriesItems();
//				
//				model.setTableModel(new ModelBreweryTable());
//				model.showBreweryData();
//				
//				gui.setTableModel(model.getTableModel());
				
				showBreweries();
				
			}
			
		});
		
		gui.addActionListenerViewStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
//				enableShowStylesItems();
//				
//				model.setTableModel(new ModelStyleTable());
//				model.showStyleData();
//				
//				gui.setTableModel(model.getTableModel());
				
				showStyles();
			}
			
		});
	}
	
	
	//order function are public because I need to call them at the start of the program.
	
	public void beersSortedByCountryOfBreweryStyle(){
//		model.beersSortedByCountryOfBreweryStyle();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.COUNTRY_OF_BREWERY_STYLE));
		showBeers();
	}
	
	public void beersSortedByFermentationCountryOfStyleBrewery(){
//		model.beersSortedByFermentationCountryOfStyleBrewery();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.FERMENTATION_COUNTRY_OF_STYLE_BREWERY));
		showBeers();	
	}
	
	public void beersSortedByFermentationStyleCountryOfBrewery(){
//		model.beersSortedByFermentationStyleCountryOfBrewery();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.FERMENTATIOM_STYLE_COUNTRY_OF_BREWERY));
		showBeers();
	}
	
	public void beersSortedByMarkStarAscending(){
//		model.beersSortedByMarkStarAscending();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_ASCENDING));
		showBeers();
	}
	
	public void beersSortedByMarkStarDescending(){
//		model.beersSortedByMarkStarDescending();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_DESCENDING));
		showBeers();
	}
	
	public void beersSortedByStarMarkAscending(){
//		model.beersSortedByStarMarkAsending();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.STAR_MARK_ASCENDING));
		showBeers();
	}
	
	public void beersSortedByStarMarkDescending(){
//		model.beersSortedByStarMarkDesending();
		model.setBeerSortingCurrentAlgorithm(Utils.getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.STAR_MARK_DESCENDING));
		showBeers();
	}
	
		
	private void addSortBeersListeners(){
		gui.addActionMenuBeersSortedByCountryOfBreweryStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				model.beersSortedByCountryOfBreweryStyle();
//				showBeers();
				beersSortedByCountryOfBreweryStyle();
			}
			
		});
		
		gui.addActionMenuBeersSortedByFermentationCountryOfStyleBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
//				model.beersSortedByFermentationCountryOfStyleBrewery();
//				showBeers();
				beersSortedByFermentationCountryOfStyleBrewery();
			}
			
		});
		
		gui.addActionMenuBeersSortedByFermentationStyleCountryOfBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
//				model.beersSortedByFermentationStyleCountryOfBrewery();
//				showBeers();
				beersSortedByFermentationStyleCountryOfBrewery();	
			}
			
		});
		
		gui.addActionMenuBeersSortedByMarkStarAscending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				beersSortedByMarkStarAscending();
			}
			
		});
		
		gui.addActionMenuBeersSortedByMarkStarDescending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				beersSortedByMarkStarDescending();
			}
			
		});
		
		gui.addActionMenuBeersSortedByStarMarkAscending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				beersSortedByStarMarkAscending();
			}
			
		});
		
		gui.addActionMenuBeersSortedByStarMarkDescending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				beersSortedByStarMarkDescending();
			}
			
		});
	}
	
	public void stylesSortedByFermentationThenCountry(){
		model.styleSortedByFermentationThenCountry();
		showStyles();
	}
	
	public void stylesSortedByCountryThenFermentation(){
		model.styleSortedByCountryThenFermentationy();
		showStyles();
	}
	
	private void addSortStylesListeners(){
		gui.addActionMenuStylesSortedByCountryThenFermentation(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				stylesSortedByCountryThenFermentation();
			}
			
		});
		
		gui.addActionMenuStylesSortedByFermentationThenCountry(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				stylesSortedByFermentationThenCountry();
			}
			
		});
	}
	
	
	public void breweriesSortedByCountryThenName(){
		model.breweriesSortedByCountryThenName();
		showBreweries();
	}
	
	public void breweriesSortedByName(){
		model.breweriesSortedBynName(null);
		showBreweries();
	}
	
	public void breweriesSortedByCountryThenAverageAscending(){
		model.breweriesSortedByCountryThenAverageAscending();
		showBreweries();
	}
	
	public void breweriesSortedByAverageAscending(){
		model.breweriesSortedByAverageAscending();
		showBreweries();
	}
	
	public void breweriesSortedByCountryThenAverageDescending(){
		model.breweriesSortedByCountryThenAverageDescending();
		showBreweries();
	}
	
	public void breweriesSortedByAverageDescending(){
		model.breweriesSortedByAverageDescending();
		showBreweries();
	}
	
	private void addSortBreweriesListeners(){
		gui.addActionMenuBreweriesSortedByAverageAscending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				breweriesSortedByAverageAscending();
			}
			
		});
		
		gui.addActionMenuBreweriesSortedByCountryThenAverageAscending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				breweriesSortedByCountryThenAverageAscending();
			}
		});
		
		gui.addActionMenuBreweriesSortedByAverageDescending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				breweriesSortedByAverageDescending();
			}
			
		});
		
		gui.addActionMenuBreweriesSortedByCountryThenAverageDescending(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				breweriesSortedByCountryThenAverageDescending();
			}
		});
		
		gui.addActionMenuBreweriesSortedByCountryThenName(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				breweriesSortedByCountryThenName();
			}
		});
		
		gui.addActionMenuBreweriesSortedByName(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				breweriesSortedByName();
			}
		});
		
		
	}
	

	
	
	
	
	private File askFileToExport() throws FileNotFoundException{
		File returned=null;
		gui.initJFileChooser(Utils.getAllFileFilters(), new File(model.getLastDirectory()));
		JFileChooser guiChooser=gui.getJFileChooser();
		if(guiChooser.showSaveDialog(gui)==JFileChooser.APPROVE_OPTION){
			returned=guiChooser.getSelectedFile();
			
			if(Utils.checkIfExtensionIsPresent(returned)==false){
				returned=new File(returned.getAbsolutePath()+"."+Utils.getJFileChooserSelectedExtension(guiChooser));
			}
		}
		return returned;
	}
	
	private void exportBeers() throws Exception{
		File f=askFileToExport();
		if(f!=null){
			ExportType type=Utils.getExportType(f);
			model.exportBeers(type, new FileOutputStream(f));
		}
	}
	
	private void exportBreweries() throws Exception{
		File f=askFileToExport();
		if(f!=null){
			ExportType type=Utils.getExportType(f);
			model.exportBreweries(type, new FileOutputStream(f));
		}
	}
	
	private void exportStyles() throws Exception{
		File f=askFileToExport();
		if(f!=null){
			ExportType type=Utils.getExportType(f);
			model.exportStyles(type, new FileOutputStream(f));
		}
	}
	
	public void export()throws Exception{
			if(model.getDataShownNow()==DataShownNow.BEER){
				exportBeers();
			}
			else if(model.getDataShownNow()==DataShownNow.BREWERY){
				exportBreweries();
			}
			else{
				exportStyles();
			}
	}
	
	private void addFileExporterListeners(){
		
		gui.addActionMenuExport(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					export();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
	
	private void addAddNewThingsListeners(){
		gui.addActionMenuAddNewBeer(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
		
		gui.addActionMenuAddNewBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.addActionMenuAddNewStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.addActionMenuNewBeerFromFile(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
		
		gui.addActionMenuNewBreweryFromFile(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.addActionMenuNewStyleFromFile(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void setOkCancelViewDialog(ViewAbstractDialog dialog){
		dialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
			
		});
		
		dialog.addActionListenerCancelButton(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
	}
	
	/**
	 * 
	 * @param row, the selected row in the table. If -1, the current style is already in the model.
	 */
	private void openStyleDialog(int row){
		if(row!=-1){
			Style s=model.getSelectedStyle(row);
			model.setStyleShown(s);
		}
		viewStyleDialog=new ViewViewStyle();
		setStyleInDialog(viewStyleDialog);
			
		setOkCancelViewDialog(viewStyleDialog);
		setStyleDialogModifyButtonListener();
		setStyleViewBeersButtonListener();
		
		viewStyleDialog.setVisible(true);
	}
	
	/**
	 * 
	 * @param row, the selected row in the table. If -1, the current brewery is already in the model.
	 */
	private void openBreweryDialog(int row){
		if(row!=-1){
			BreweryAverage b=model.getSelectedBrewery(row);
			model.setBreweryShown(b); //call then
		}
		viewBreweryDialog=new ViewViewBrewery();
		setBreweryInDialog(viewBreweryDialog);
		
		setOkCancelViewDialog(viewBreweryDialog);
		setBreweryModifyButtonListener();
		setBreweryViewBeersButtonListener();
		
		viewBreweryDialog.setVisible(true);	
	}
	
	private void openBeerDialog(int row){
		Beer b =model.getSelectedBeer(row);
		model.setBeerShown(b);
		viewBeerDialog=new ViewViewBeer();
		setBeerInDialog(viewBeerDialog);
				
		setOkCancelViewDialog(viewBeerDialog);
		setBeerModifyButtonListener();
		
		setBeerViewStyleBreweryListener();
		
		viewBeerDialog.setVisible(true);
	}
	
	//this stupid method, we can make it static
	private void setBeerInDialog(BeerDialog dialog){
		Beer b=model.getBeerShown();
		dialog.setBeerName(b.getName());
		dialog.setBreweryName(Utils.getBreweryString(b.getBrewery()));
		dialog.setStyle(Utils.getStyleString(b.getStyle()));
		dialog.setABV(Double.toString(b.getAlcool()));
		dialog.setStars(Integer.toString(b.getNumberOfStars()));
		dialog.setMark(Integer.toString(b.getMark()));
		dialog.setTried(Utils.getBooleanItalian(b.isTried()));
		dialog.setDescription(b.getDescription());
	}
	
	private void setStyleInDialog(StyleDialog dialog){
		Style s=model.getStyleShown();
		dialog.setStyleMainName(s.getStyleMainName());
		dialog.setStyleSubcategory(s.getStyleSubCategory());
		dialog.setFermentation(Utils.getFermentationString(s.getFermentation()));
		dialog.setStyleCountry(s.getStyleCountryOrigin());
		dialog.setDescription(s.getDescription());
	}
	
	public void setBreweryInDialog(BreweryDialog dialog){
		BreweryAverage b=model.getBreweryShown();
		dialog.setBreweryName(b.getName());
		dialog.setBreweryTown(b.getTown());
		dialog.setBreweryCountry(b.getCountry());
		dialog.setBreweryDescription(b.getDescription());
		dialog.setBreweryWebsite(b.getWebsite());
		dialog.setBreweryAverage(Double.toString(b.getAverage()));
	}
	
	
	private void setStyleDialogModifyButtonListener(){
		viewStyleDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewStyleDialog.setVisible(false);
				viewStyleDialog.dispose();
				
				addStyleDialog=new ViewAddNewStyle();
				addStyleDialog.fillThings(Utils.getMainStyleString(model.getOnlyMainStyle()), model.getCountries());
//				addStyleDialog.setStyle(model.getStyleDialog());
				setStyleInDialog(addStyleDialog);
				addStyleDialog.setVisible(true);
			}
			
		});
	}
	
	private void setBreweryModifyButtonListener(){
		viewBreweryDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				viewBreweryDialog.setVisible(false);
				viewBreweryDialog.dispose();
				
				addBreweryDialog=new ViewAddNewBrewery();
				addBreweryDialog.fillThings(model.getCountries());
				setBreweryInDialog(addBreweryDialog);
				addBreweryDialog.setVisible(true);
				
			}
			
		});
	}
	
	private void setBeerModifyButtonListener(){
		viewBeerDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewBeerDialog.setVisible(false);
				viewBeerDialog.dispose();
				
				addBeerDialog = new ViewAddNewBeer();
				addBeerDialog.fillThings(Utils.getBreweriesString(model.getBreweryData()), Utils.getStylesString(model.getStyleData()));
				setBeerInDialog(addBeerDialog);
				addBeerDialog.setVisible(true);
			}
			
		});
	}
	
	private void setBreweryViewBeersButtonListener(){
		viewBreweryDialog.addActionListenerViewBeersButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				viewBreweryDialog.setVisible(false);
				viewBreweryDialog.dispose();
				
				BreweryAverage b = model.getBreweryShown();
				
				
				
				beersFilteredByBrewery(b);
//				gui.noRowSelected();
			}
			
		});
	}
	
	private void setStyleViewBeersButtonListener(){
		viewStyleDialog.addActionListenerViewBeersButton(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				
				viewStyleDialog.setVisible(false);
				viewStyleDialog.dispose();
				
				Style s = model.getStyleShown();
				
				beersFilteredByStyle(s);
			}
			
		});
	}
	
	private void setBeerViewStyleBreweryListener(){
		setBeerViewStyleButtonListener();
		setBeerViewBreweryButtonListener();
	}
	
	private void setBeerViewStyleButtonListener(){
		viewBeerDialog.addActionListenerViewStyleButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				viewBeerDialog.setVisible(false);
				viewBeerDialog.dispose();
				
				Style s = model.getBeerShown().getStyle();
				
				model.setStyleShown(s);
				openStyleDialog(-1);
			}
			
		});
	}
	
	
	private void setBeerViewBreweryButtonListener(){
		viewBeerDialog.addActionListenerViewBreweryButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				viewBeerDialog.setVisible(false);
				viewBeerDialog.dispose();
				
				Brewery b =  model.getBeerShown().getBrewery();
				
				model.setBreweryShown(b);
				openBreweryDialog(-1);
			}
			
		});
	}
	

	
	private void addListSelectionListener(){
		gui.addMouseListener(new MouseListener(){
			
			
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					JTable t =(JTable)arg0.getSource();
					int row=t.getSelectedRow();
					if(model.getDataShownNow()==DataShownNow.BEER){
							openBeerDialog(row);
				
					}else if(model.getDataShownNow()==DataShownNow.BREWERY || model.getDataShownNow()==DataShownNow.BREWERY_AVERAGE){
							openBreweryDialog(row);
					}
					else{
						openStyleDialog(row);
					}
				}
			}
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
//					JTable t =(JTable)arg0.getSource();
//					int row=t.getSelectedRow();
//					if(model.getDataShownNow()==DataShownNow.BEER){
//							openBeerDialog(row);
//				
//					}else if(model.getDataShownNow()==DataShownNow.BREWERY || model.getDataShownNow()==DataShownNow.BREWERY_AVERAGE){
//							openBreweryDialog(row);
//					}
//					else{
//						openStyleDialog(row);
//					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
	}
	
	
	private void addSearchMenuListeners(){
		gui.addActionMenuSearchBeer(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.addActionMenuSearchBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.addActionMenuSearchStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	private void addActionMenuPreferencesListener(){
		gui.addActionMenuPreferences(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				showPreferencesDialog();
			}
			
		});
	}
	
	
	private void addPreferencesOkButtonListener(){
		preferencesDialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				preferencesDialog.setVisible(false);
				
				
				Configuration newConf = new Configuration();
				
				newConf.setBeerSortingAlgorithm(Utils.getBeerSortingAlgorithmFromDescription(preferencesDialog.getComboBoxSortingBeerSelectedItem()));
				newConf.setBrewerySortingAlgorithm(Utils.getBrewerySortingAlgorithmFromDescription(preferencesDialog.getComboBoxSortingBrewerySelectedItem()));
				newConf.setStyleSortingAlgorithm(Utils.getStyleSortingAlgorithmFromDescription(preferencesDialog.getComboBoxSortingStyleSelectedItem()));
				
//				newConf.setBeerFilterAlgorithm(QueryRunner.BeerFilterAlgorithm.NONE);
//				newConf.setBeerFilterValue("");
//				newConf.setBreweryFilterAlgorithm(QueryRunner.BreweryFilterAlgorithm.NONE);
//				newConf.setBreweryFilterValue("");
//				newConf.setStyleFilterAlgorithm(QueryRunner.StyleFilterAlgorithm.NONE);
//				newConf.setStyleFilterValue("");
				
				newConf = ConfigurationFactory.getDefaultFilteringConfiguration(newConf);
				
				if(!model.getConfiguration().equals(newConf)){
					System.out.println("Configuration has changed");
					model.setConfiguration(newConf);
				}
			}
			
		});
	}
	
	private void addPreferencesCancelButtonListener(){
		preferencesDialog.addActionListenerCancelButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				preferencesDialog.setVisible(false);
				preferencesDialog.dispose();
			}
			
		});
	}
	
	private void addPreferencesDefaultSortingButtonListener(){
		preferencesDialog.addActionListenerDefaultSortingButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Configuration newConf = model.getConfiguration();			
				newConf = ConfigurationFactory.getDefaultSortingConfiguration(newConf);
				
				fillPreferencesSortingAlgorithm(newConf);
			}
			
		});
	}
	
	private void addPreferencesDefaultFilteringButtonListener(){
		preferencesDialog.addActionListenerDefaultFilteringButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Configuration newConf = model.getConfiguration();			
				newConf = ConfigurationFactory.getDefaultFilteringConfiguration(newConf);
				
				fillPreferencesFilteringAlgorithm(newConf);
				
			}
			
		});
	}
	
	private void addPreferencesDeafultButtonListener(){
		preferencesDialog.addActionListenerDefaultButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				fillPreferences(ConfigurationFactory.getDefaultConfiguration());
				
			}
			
		});
	}
	
	private void fillPreferencesSortingAlgorithm(Configuration conf){
		preferencesDialog.fillComboBoxSortingBeer(Utils.getBeerSortingAlgorithmDescriptionList());
		preferencesDialog.setComboBoxSortingBeerSelectedItem(Utils.getBeerSortingAlgorithmDescription(conf.getBeerSortingAlgorithm()));
		
		preferencesDialog.fillComboBoxSortingBrewery(Utils.getBrewerySortingAlgorithmDescriptionList());
		preferencesDialog.setComboBoxSortingBrewerySelectedItem(Utils.getBrewerySortingAlgorithmDescription(conf.getBrewerySortingAlgorithm()));
		
		preferencesDialog.fillComboBoxSortingStyle(Utils.getStyleSortingAlgorithmDescriptionList());
		preferencesDialog.setComboBoxSortingStyleSelectedItem(Utils.getStyleSortingAlgorithmDescription(conf.getStyleSortingAlgorithm()));
	}
	
	private void fillPreferencesFilteringAlgorithm(Configuration conf){
		preferencesDialog.fillComboBoxFilteringBeer(Utils.getBeerFilterAlgorithmDescriptionList());
		preferencesDialog.setComboBoxFilteringBeerSelectedItem(Utils.getBeerFilterAlgorithmDescription(conf.getBeerFilterAlgorithm()));
		
		preferencesDialog.fillComboBoxFilteringBrewery(Utils.getBreweryFilterAlgorithmDescriptionList());
		preferencesDialog.setComboBoxFilteringBrewerySelectedItem(Utils.getBreweryFilterAlgorithmDescription(conf.getBreweryFilterAlgorithm()));
		
		preferencesDialog.fillComboBoxFilteringStyle(Utils.getStyleFilterAlgorithmDescriptionList());
		preferencesDialog.setComboBoxFilteringStyleSelectedItem(Utils.getStyleFilterAlgorithmDescription(conf.getStyleFilterAlgorithm()));
	}
	
	private void fillPreferences(Configuration conf){
		fillPreferencesSortingAlgorithm(conf);
		fillPreferencesFilteringAlgorithm(conf);
	}
	
	public void showPreferencesDialog(){
		
		preferencesDialog = new ViewPreferences();
		fillPreferences(model.getConfiguration());
		
		addPreferencesOkButtonListener();
		addPreferencesCancelButtonListener();
		addPreferencesDefaultSortingButtonListener();
		addPreferencesDefaultFilteringButtonListener();
		addPreferencesDeafultButtonListener();
		
		preferencesDialog.setVisible(true);
	}
	
	public void showAboutDialog(){
		
	}
	
	private void addOptionsMenuListeners(){
		showPreferencesDialog();
		showAboutDialog();
	}
	
	

}
