package org.nbena.beersmanager.exe.ui.controllers;


import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JTable;


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
import org.nbena.beersmanager.exe.ui.views.ViewException;
import org.nbena.beersmanager.exe.ui.views.ViewJOptionPane;
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
	
	private ViewException exceptionDialog;
	
	private ViewJOptionPane optionPane;

	

	public ControllerMainGUI(ViewMainGUI gui, Model model) {
		this.gui=gui;
		this.model=model;
		
		optionPane = new ViewJOptionPane(gui);
		
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
		addSaveMenuAndButtonListeners();
		addRefreshButtonListener();
		addOperationOnClose();

	}
	
	
	
	public void showDefault(){
		switch(model.getDefaultView()){
		case BEER:
			showBeers();
			break;
		case BREWERY:
			showBreweriesAverage();
			break;
		case STYLE:
			showStyles();
			break;
		}
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
	
	public void beersFilteredByStyleProvenience(String s){
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
				String breweryString = askBreweryBeersFilteredByBrewery();
				if(breweryString!=null && breweryString!=""){
					
					Brewery b = Utils.getBreweryFromString(breweryString);
					beersFilteredByBrewery(b);
				}
			}
			
		});
		
		gui.addActionMenuBeersFilteredByBreweryCountry(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByBreweryCountry(null);
//				showBeers();
				String string = askCountryBeersFilteredByCountry();
				if(string!=null && string!=""){
					
					
//					beersFilteredByBreweryCountry(b);
					//beersFilteredByCountry(string);
				}
				
				
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
				String country = askBreweryBeersFilteredByBrewery();
				if(country!=null && country!=""){
					
					beersFilteredByStyleProvenience(country);
				}
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
			else if(model.getDataShownNow()==DataShownNow.STYLE){
				exportStyles();
			}
			else{
				exportBreweries();
			}
	}
	
	private void addFileExporterListeners(){
		
		gui.addActionMenuExport(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					export();
				} catch (Exception e) {
					/*e.printStackTrace();*/showExceptionDialog(e);
				}
			}
			
		});
	}
	
	private void setBeerInDialog(BeerDialog dialog){
		Beer b=model.getBeerShown();
		dialog.setBeerName(b.getName());
		dialog.setBreweryName(Utils.getBreweryString(b.getBrewery()));
		dialog.setStyle(Utils.getStyleString(b.getStyle()));
		dialog.setABV(Double.toString(b.getAlcool()));
		dialog.setStars(Integer.toString(b.getNumberOfStars()));
		dialog.setMark(Integer.toString(b.getMark()));
		dialog.setTried(b.isTried());
		dialog.setDescription(b.getDescription());
		dialog.setPlace(b.getPlaceTried());
		
	
		addBeerDialogPriceMarkEditable(b.isTried());
		
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
	
	private Beer getBeerFromAddNewBeerDialog() throws ObjectNotFoundException{
		Beer b = new Beer();
		
		Brewery brewery = Utils.getBreweryFromString(addBeerDialog.getBrewery());
		Style style = Utils.getStyleFromString(addBeerDialog.getStyle());
		
		brewery = model.getBreweryBinarySearch(brewery);	
		style = model.getStyleBinarySearch(style);
		
		b.setName(addBeerDialog.getBeerName());
		b.setStyle(style);
		b.setBrewery(brewery);
		b.setAlcool(Utils.parseDouble(addBeerDialog.getABV()));
		b.setMark(Integer.parseInt(addBeerDialog.getMark()));
		b.setNumberOfStars(Integer.parseInt(addBeerDialog.getStars()));
		b.setTried(addBeerDialog.isTried());
		b.setPlaceTried(addBeerDialog.getPlace());
		b.setPrice(Utils.parseDouble(addBeerDialog.getPrice()));
		b.setDescription(addBeerDialog.getDescription());
		
//		brewery.setName(addBeerDialog.getName());
//		brewery.setCountry(addBeerDialog.get);
		
		Utils.printStyle(b.getStyle(), System.out);
		
		return b;
	}
	
	private Brewery getBreweryInAddNewBreweryDialog(){
		Brewery b = new Brewery();
		b.setName(addBreweryDialog.getBreweryName());
		b.setCountry(addBreweryDialog.getBreweryCountry());
		b.setTown(addBreweryDialog.getBreweryTown());
		b.setDescription(addBreweryDialog.getDescription());
		b.setWebsite(addBreweryDialog.getBreweryWebsite());
		
		b.setAuthenticTrappist(false);  //tomorrow!!!
		return b;
	}
	
	private Style getStyleInAddNewStyleDialog(){
		Style s = new Style();
		s.setStyleMainName(addStyleDialog.getStyleMainName());
		s.setStyleSubCategory(addStyleDialog.getStyleSubcategory());
		s.setStyleCountryOrigin(addStyleDialog.getStyleCountry());
		s.setDescription(addStyleDialog.getDescription());
		s.setFermentation(Utils.getFermentationFromString(addStyleDialog.getFermentation()));
		return s;
	}
	
	/**
	 * Delete the given beer.
	 * @param b  the beer to delete. If null, the beerShown (private field in model) will be deleted.
	 * @throws UpdateSavingException 
	 */
	private void deleteBeerLogic(Beer b) throws UpdateSavingException{
		model.deleteBeer(b);
		refreshData();
	}
	
	/**
	 * Delete the given brewery.
	 * @param b the brewery to delete. If null, the breweryShown (private field in model) will be deleted.
	 * @throws UpdateSavingException 
	 */
	private void deleteBreweryLogic(BreweryAverage b) throws UpdateSavingException{
		model.deleteBrewery(b);
		refreshData();
	}
	
	/**
	 * Delete the given style.
	 * @param s  the style to delete. If null, the styleShown (private field in model) will be deleted.
	 * @throws UpdateSavingException 
	 */
	private void deleteStyleLogic(Style s) throws UpdateSavingException{
		model.deleteStyle(s);
		refreshData();
	}
	
	private void addNewBeerLogic(Beer b) throws UpdateSavingException{
		model.addNewBeer(b);
		refreshData();

	}
	
	private void updateBeerLogic(Beer b) throws UpdateSavingException{
		model.updateBeer(b);
		refreshData();

	}
	
	private void addNewBreweryLogic(Brewery b) throws UpdateSavingException{
		model.addNewBrewery(b);
		refreshData();

	}
	
	private void updateBreweryLogic(Brewery b)throws UpdateSavingException{
		model.updateBrewery(Utils.fromBreweryToBreweryAverage(b));
		refreshData();
	}
	
	private void addNewStyleLogic(Style s) throws UpdateSavingException{
		model.addNewStyle(s);
		refreshData();

	}
	
	private void updateStyleLogic(Style s) throws UpdateSavingException{
		model.updateStyle(s);
		refreshData();

	}
	
	private void addBeerDialogPriceMarkEditable(boolean editable){
		addBeerDialog.setTextFieldMarkEditable(editable);
		addBeerDialog.setTextFieldPriceEditable(editable);
	}
	
	private void setAddNewBeerRadioButton(){
		addBeerDialog.addActionListenerTriedYesRadioButton(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				addBeerDialogPriceMarkEditable(true);
			}
		});
		
		addBeerDialog.addActionListenerTriedNoRadioButton(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				addBeerDialogPriceMarkEditable(false);
			}
		});
	}
	
	private void setAddNewBeerOkButton(){
		addBeerDialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				addBeerDialog.setVisible(false);
				Beer b=null;
				

				
				try {
					b=getBeerFromAddNewBeerDialog();
				} catch (ObjectNotFoundException e1) {
					/*e1.printStackTrace();*/showExceptionDialog(e1);
				}
				
				try{
					if(model.isAddNewBeerOrModifyBeer()){
						addNewBeerLogic(b);
					}else{
						updateBeerLogic(b);
					}
				}catch(UpdateSavingException e1){
					showExceptionDialog(e1);
				}
				addBeerDialog.dispose();
				
			}
			
		});
		
		
	}
	
	private void setAddNewBreweryOkButton(){
		addBreweryDialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				addBreweryDialog.setVisible(false);
				
				Brewery b = getBreweryInAddNewBreweryDialog();
				Utils.printBrewery(b, System.out);
				
				
					try{
						if(model.isAddNewBreweryOrModifyBrewery()){
							addNewBreweryLogic(b);
						}else{
							updateBreweryLogic(b);
						}
					}catch(UpdateSavingException e1){
						showExceptionDialog(e1);
					}

			}
			
		});
		
	}
	
	private void setAddNewStyleOkButton(){
		addStyleDialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				addStyleDialog.setVisible(false);
				
				Style s=getStyleInAddNewStyleDialog();
				
//				System.out.println("Lo stile ottenuto è: \n");
//				Utils.printStyle(s, System.out);
				

				try{
					if(model.isAddNewStyleOrModifyStyle()){
						addNewStyleLogic(s);
					}else{
						updateStyleLogic(s);
					}
				}catch(UpdateSavingException e1){
					showExceptionDialog(e1);
				}
			
				addStyleDialog.dispose();
			}
			
		});
	}
	
	private void setAddNewBeerCancelButton(){
		addBeerDialog.addActionListenerCancelButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addBeerDialog.setVisible(false);
				addBeerDialog.dispose();
			}
			
		});
	}
	
	
	private void setAddNewBreweryCancelButton(){
		addBreweryDialog.addActionListenerCancelButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addBreweryDialog.setVisible(false);
				addBreweryDialog.dispose();
			}
			
		});
	}
	
	
	private void setAddNewStyleCancelButton(){
		addStyleDialog.addActionListenerCancelButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addStyleDialog.setVisible(false);
				addStyleDialog.dispose();
			}
			
		});
	}
	
	public void showAddOrModifyBeer(boolean addNewBeerOrModify){
		if(addNewBeerOrModify){
			model.setAddNewBeerOrModifyBeer(true);
			showAddBeerDialog();
		}
		else{
			model.setAddNewBeerOrModifyBeer(false);
			showModifyBeerDialog();
		}
	}
	
	public void showAddOrModifyBrewery(boolean addNewBreweryOrModify){
		if(addNewBreweryOrModify){
			model.setAddNewBreweryOrModifyBrewery(true);
			showAddBreweryDialog();
		}else{
			model.setAddNewBreweryOrModifyBrewery(false);
			showModifyBreweryDialog();
		}
	}
	
	public void showAddOrModifyStyle(boolean addNewStyleOrModify){
		if(addNewStyleOrModify){
			model.setAddNewStyleOrModifyStyle(true);
			showAddStyleDialog();
		}else{
			model.setAddNewStyleOrModifyStyle(false);
			showModifyStyleDialog();
		}
	}
	

	
	public void showAddBeerDialog(){
		addBeerDialog = new ViewAddNewBeer();
		addBeerDialog.fillThings(Utils.getBreweriesString(model.getBreweryData()), Utils.getStylesString(model.getStyleData()));
		
		setAddNewBeerOkButton();
		setAddNewBeerCancelButton();
		setAddNewBeerRadioButton();
		
		addBeerDialog.setTried(true);
		addBeerDialogPriceMarkEditable(true);
		
		addBeerDialog.setVisible(true);
	}
	
	public void showAddBreweryDialog(){
		addBreweryDialog = new ViewAddNewBrewery();
		addBreweryDialog.fillThings(model.getCountries());
		
		setAddNewBreweryOkButton();
		setAddNewBreweryCancelButton();
		
		addBreweryDialog.setVisible(true);
	}
	
	
	private void addAddStyleComboBoxStyleListener(){
		addStyleDialog.addActionComboBoxSelectedItem(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
						
				String mainStyle = addStyleDialog.getStyleMainName();
				if(mainStyle.equals(Utils.Constants.NEW_STYLE_STRING)){
					
					addStyleDialog.removeComboBoxSelection();
					addStyleDialog.closeComboBox();
					
					mainStyle = askNewMainStyleName();
					
					if(mainStyle!=null && mainStyle.length()>0){
						addStyleDialog.addComboBoxItem(mainStyle);
					}
				}
			}
			
		});
	}
	
	/**
	 * From the model's list of main style as string, it adds a new String 'New style'. (User will select and it will have the possibilility to add a new style).
	 * @return the string list of main styles plus 'New Style'. 
	 */
	private List<String> getStylesListToAddToAddNewStyle(){
		List<String> styles = new LinkedList<String>(model.getOnlyMainStyles());
		styles.add(Utils.Constants.NEW_STYLE_STRING);
		return styles;
	}
	
	public void showAddStyleDialog(){
		addStyleDialog = new ViewAddNewStyle();
		
		addStyleDialog.fillThings(getStylesListToAddToAddNewStyle(), Utils.getFermentationsItalianString(), model.getCountries());
		
		setAddNewStyleOkButton();
		setAddNewStyleCancelButton();
		
		addAddStyleComboBoxStyleListener();
		
		addStyleDialog.setVisible(true);
	}
	
	private void addAddNewThingsListeners(){
		gui.addActionMenuAddNewBeer(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAddOrModifyBeer(true);
				
			}
			
		});
		
		gui.addActionMenuAddNewBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAddOrModifyBrewery(true);
			}
			
		});
		
		gui.addActionMenuAddNewStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAddOrModifyStyle(true);
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
	
	
	private void setDeleteBeerButtonListener(){
		viewBeerDialog.addActionListenerDeleteButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					deleteBeerLogic(null);
				} catch (UpdateSavingException e) {
					showExceptionDialog(e);
				}
			}
			
		});
		
		viewBeerDialog.setVisible(false);
		viewBeerDialog.dispose();
	}
	
	private void setDeleteBreweryButtonListener(){
		viewBreweryDialog.addActionListenerDeleteButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					deleteBreweryLogic(null);
				} catch (UpdateSavingException e1) {
					showExceptionDialog(e1);
				}
			}
			
		});
		
		viewBreweryDialog.setVisible(false);
		viewBreweryDialog.dispose();
	}
	
	private void setDeleteStyleButtonListener(){
		viewStyleDialog.addActionListenerDeleteButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					deleteStyleLogic(null);
				} catch (UpdateSavingException e1) {
					showExceptionDialog(e1);
				}
			}
			
		});
		
		viewStyleDialog.setVisible(false);
		viewStyleDialog.dispose();
	}

	

	
	private void openBeerDialog(int row){
		Beer b =model.getSelectedBeer(row);
		model.setBeerShown(b);
		viewBeerDialog=new ViewViewBeer();
		setBeerInDialog(viewBeerDialog);
				
		setOkCancelViewDialog(viewBeerDialog);
		setBeerModifyButtonListener();	
		setBeerViewStyleBreweryListener();
		setDeleteBeerButtonListener();
		
		viewBeerDialog.setVisible(true);
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
		setDeleteBreweryButtonListener();
		
		viewBreweryDialog.setVisible(true);	
	}
	
	//this stupid method, we can make it static

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
		setDeleteStyleButtonListener();
		
		viewStyleDialog.setVisible(true);
	}
	
	private void showModifyBeerDialog(){
		viewBeerDialog.setVisible(false);
		viewBeerDialog.dispose();
		
		addBeerDialog = new ViewAddNewBeer();
		addBeerDialog.fillThings(Utils.getBreweriesString(model.getBreweryData()), Utils.getStylesString(model.getStyleData()));
		setBeerInDialog(addBeerDialog);
		
		setAddNewBeerOkButton();
		setAddNewBeerCancelButton();
		setAddNewBeerRadioButton();
		
//		addBeerDialog.setTried(true);
//		addBeerDialogPriceMarkEditable(true);
		
//		model.setAddNewBeerOrModifyBeer(false);  already done.
		
		addBeerDialog.setVisible(true);
	}
	
	private void showModifyBreweryDialog(){
		viewBreweryDialog.setVisible(false);
		viewBreweryDialog.dispose();
		
		addBreweryDialog=new ViewAddNewBrewery();
		addBreweryDialog.fillThings(model.getCountries());
		setBreweryInDialog(addBreweryDialog);
		
		setAddNewBreweryOkButton();
		setAddNewBreweryCancelButton();;
		
//		model.setAddNewBreweryOrModifyBrewery(false); already done.
		
		addBreweryDialog.setVisible(true);
	}
	
	private void showModifyStyleDialog(){
		viewStyleDialog.setVisible(false);
		viewStyleDialog.dispose();
		
		addStyleDialog=new ViewAddNewStyle();
		addStyleDialog.fillThings(Utils.getMainStyleString(model.getOnlyMainStyle()), Utils.getFermentationsItalianString(), model.getCountries());
		setStyleInDialog(addStyleDialog);
		
		setAddNewStyleOkButton();
		setAddNewStyleCancelButton();
		
//		model.setAddNewStyleOrModifyStyle(false); already done.
		
		addStyleDialog.setVisible(true);
	}
	
	
	private void setStyleDialogModifyButtonListener(){
		viewStyleDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				viewStyleDialog.setVisible(false);
//				viewStyleDialog.dispose();
//				
//				addStyleDialog=new ViewAddNewStyle();
//				addStyleDialog.fillThings(Utils.getMainStyleString(model.getOnlyMainStyle()), model.getCountries());
//				setStyleInDialog(addStyleDialog);
//				
//				setAddNewStyleOkCancelButton();
//				
//				model.setAddNewStyleOrModifyStyle(false);
//				
//				addStyleDialog.setVisible(true);
							
				showAddOrModifyStyle(false);
			}
			
		});
	}
	
	private void setBreweryModifyButtonListener(){
		viewBreweryDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				viewBreweryDialog.setVisible(false);
//				viewBreweryDialog.dispose();
//				
//				addBreweryDialog=new ViewAddNewBrewery();
//				addBreweryDialog.fillThings(model.getCountries());
//				setBreweryInDialog(addBreweryDialog);
//				setAddNewBreweryOkCancelButton();
//				
//				model.setAddNewBreweryOrModifyBrewery(false);
//				
//				addBreweryDialog.setVisible(true);
							
				showAddOrModifyBrewery(false);
			}
			
		});
	}
	
	private void setBeerModifyButtonListener(){
		viewBeerDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				viewBeerDialog.setVisible(false);
//				viewBeerDialog.dispose();
//				
//				addBeerDialog = new ViewAddNewBeer();
//				addBeerDialog.fillThings(Utils.getBreweriesString(model.getBreweryData()), Utils.getStylesString(model.getStyleData()));
//				setBeerInDialog(addBeerDialog);
//				setAddNewBeerOkCancelButton();
//				
//				model.setAddNewBeerOrModifyBeer(false);
//				
//				addBeerDialog.setVisible(true);
				
				showAddOrModifyBeer(false);
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
				
					}else if(model.getDataShownNow()==DataShownNow.STYLE){
						openStyleDialog(row);
					}else{
						openBreweryDialog(row);
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
				
//				newConf = ConfigurationFactory.getDefaultFilteringConfiguration(newConf);
				
				newConf.setBeerFilterAlgorithm(Utils.getBeerFilterAlgorithmFromDescription(preferencesDialog.getComboBoxFilteringBeerSelectedItem()));
//				newConf.setBeerFilterValue(preferencesDialog.getBeerFilteringValue());
				newConf.setBreweryFilterAlgorithm(Utils.getBreweryFilterAlgorithmFromDescription(preferencesDialog.getComboBoxFilteringBrewerySelectedItem()));
//				newConf.setBeerFilterValue(preferencesDialog.getBreweryFilteringValue());
				newConf.setStyleFilterAlgorithm(Utils.getStyleFilterAlgorithmFromDescription(preferencesDialog.getComboBoxFilteringStyleSelectedItem()));
//				newConf.setBeerFilterValue(preferencesDialog.getStyleFilteringValue());
				
				newConf.setBeerFilterValue("");
				newConf.setBreweryFilterValue("");
				newConf.setStyleFilterValue("");
				
				if(!model.getConfiguration().equals(newConf)){
					System.out.println("Configuration has changed");
					model.setConfiguration(newConf);
					try {
						model.saveConfiguration();
					} catch (FileNotFoundException e1) {
						showExceptionDialog(e1);
					}
				}
				preferencesDialog.dispose();
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
	
	private void addPreferencesDefaultViewButtonListener(){
		preferencesDialog.addActionListenerDefaultViewButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Configuration newConf = model.getConfiguration();
				newConf = ConfigurationFactory.getDefaultView(newConf);
				
				fillPreferencesDefaultView(newConf);
				
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
	

	
	private void fillPreferencesDefaultView(Configuration conf){
		preferencesDialog.fillComboBoxDefaultView(Utils.getDefaultViewDescriptionList());
		preferencesDialog.setComboBoxDefaultViewSelectedItem(Utils.getViewDefaultDescription(conf.getDefaultView()));
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
		fillPreferencesDefaultView(conf);
	}
	
	public void showPreferencesDialog(){
		
		preferencesDialog = new ViewPreferences();
		fillPreferences(model.getConfiguration());
		
		addPreferencesOkButtonListener();
		addPreferencesCancelButtonListener();
		addPreferencesDefaultSortingButtonListener();
		addPreferencesDefaultFilteringButtonListener();
		addPreferencesDefaultViewButtonListener();
		addPreferencesDeafultButtonListener();
		
		preferencesDialog.setVisible(true);
	}
	
	public void showAboutDialog(){
		
	}
	
	private void addOptionsMenuListeners(){
		gui.addActionMenuPreferences(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showPreferencesDialog();
			}
			
		});
		
		gui.addActionMenuAbout(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAboutDialog();
			}
			
		});
		
		
	}
	
	
	
	private void addSaveMenuAndButtonListeners(){
		gui.addActionMenuSaveAndSaveButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					model.saveThings();
				} catch (JSONException | FileNotFoundException e) {
					/*e.printStackTrace();*/showExceptionDialog(e);
				}
				
			}
			
		});
	}
	
	
	
	public void showExceptionDialog(Exception e){
		exceptionDialog = new ViewException();
		
		exceptionDialog.addActionListenerOkButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				exceptionDialog.setVisible(false);
				exceptionDialog.dispose();
			}
			
		});
		
		exceptionDialog.initEditorPane();
		exceptionDialog.setErrorType(e.getClass().getSimpleName());
		exceptionDialog.setErrorMessage(e.getMessage());
		exceptionDialog.setStackTrace("<html><body style=\"color: red;\">", Utils.getStackTrace(e), "</body></html>");
		
		exceptionDialog.setVisible(true);
	}
	
	private void refreshData(){
		switch(model.getDataShownNow()){
		case BEER:
			showBeers();
			break;
		case STYLE:
			showStyles();
			break;
		default:
			showBreweries();
			break;
		}
	}
	
	private void addRefreshButtonListener(){
		gui.addActionRefreshButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				refreshData();
			}
			
		});
	}
	
	private String askNewMainStyleName(){
		optionPane.setParent(addStyleDialog);
		String mainStyleName = optionPane.showBlankTextInput(Utils.Constants.NEW_STYLE_TITLE, Utils.Constants.NEW_STYLE_MESSAGE,Utils.Constants.NEW_STYLE_TITLE);
		return mainStyleName;
	}
	
	private void close(){
		gui.setVisible(false);
		gui.dispose();
		System.exit(0);
	}
	
	private void setAskOnClose(){
		if(model.isSomethingToSave()){
			int res = optionPane.showOkCancel(Utils.Constants.QUESTION, Utils.Constants.CONFIRMATION_BEFORE_EXIT);
			if(ViewJOptionPane.isOkOption(res)){
				close();
			}
		}else{
			close();
		}
	}
	
	
	
	private String askCountryBeersFiltererdByStyleProvenience(){
		optionPane.setParent(gui);
		String[] countries = Utils.toArray(model.getCountriesWithStyle());
		return optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_ORIGIN_STYLE, countries);
	}
	
	
	private String askBreweryBeersFilteredByBrewery(){
		optionPane.setParent(gui);
		String[] breweries = Utils.getBreweryStringArray(model.getBreweryData());
		return optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_BREWERY, breweries);
	}
	
	private String askStyleBeersFilteredByStyleMain(){
		optionPane.setParent(gui);
		String[] styles = Utils.getStyleStringArray(model.getOnlyMainStyle());
		return optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_STYLE, styles);
	}
	
	private String askStyleBeersFilteredByStyle(){
		optionPane.setParent(gui);
		String[] styles = Utils.getStyleStringArray(model.getStyleData());
		return optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_STYLE_AND_SUB, styles);
	}
	
	private String askCountryBeersFilteredByCountry(){
		optionPane.setParent(gui);
		String[] countries = Utils.toArray(model.getCountriesWithBrewery());
		return optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_ORIGIN_STYLE, countries);
	}
	
	private void addOperationOnClose(){
		gui.setDoNothingOnClose();
		gui.addActionListenerOnClosing(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				setAskOnClose();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
