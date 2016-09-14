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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;


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
		addFilterBreweriesListeners();
		addFilterStylesListeners();
		addSearchMenuListeners();
		addOptionsMenuListeners();
		addSaveMenuAndButtonListeners();
		addRefreshButtonListener();
		addOperationOnClose();
		addTablePopupMenu();

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
//		model.beersFilteredByBrewery(b);
		model.setBeerFilteringCurrentValue(b);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_BREWERY));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByBreweryCountry(String country){
//		model.beersFilteredByBreweryCountry(country);
		model.setBeerFilteringCurrentValue(country);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_COUNTRY));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByExactABV(double abv){
//		model.beersFilteredByExatcAlcool(abv);
		model.setBeerFilteringCurrentValue((Double)abv);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_MINIMUM_ABV));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByMinimumABV(double abv){
//		model.beersFilteredByMinimumAlcool(abv);
		model.setBeerFilteringCurrentValue((Double)abv);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_MINIMUM_ABV));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByMinimumMark(int mark){
//		model.beersFilteredByMiminumMark(mark);
		model.setBeerFilteringCurrentValue((Integer)mark);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_MINIMUM_MARK));
		model.applyFilteringToBeers();
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByExactMark(int mark){
//		model.beersFilteredByExactMark(mark);
		model.setBeerFilteringCurrentValue((Integer)mark);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_EXACT_MARK));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByMinimumNumberOfStars(int number){
//		model.beersFilteredByMinimumNumberOfStars(number);
		model.setBeerFilteringCurrentValue((Integer)number);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_MINIMUM_STARS));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByExactNumberOfStars(int number){
//		model.beersFilteredByExactNumberOfStars(number);
		model.setBeerFilteringCurrentValue((Integer)number);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_EXACT_STAR));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByFermentation(Fermentation f){
//		model.beersFilteredByFermentation(f);
		model.setBeerFilteringCurrentValue(f);
		if(f==Fermentation.HIGH){
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_FERMENTATION_HIGH));
		}
		else if(f==Fermentation.LOW){
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_FERMENTATION_LOW));
		}else{
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_FERMENTATION_SPONTANEOUS));
		}
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByStyle(Style s){
//		model.beersFilteredByStyle(s);
		model.setBeerFilteringCurrentValue(s);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_STYLE));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByMainStyle(Style s){
//		model.beersFilteredByMainStyle(s);
		model.setBeerFilteringCurrentValue(s);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_MAIN_STYLE));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByStyleProvenience(String s){
//		model.beersFilteredByStyleProvenience(s);
		model.setBeerFilteringCurrentValue(s);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_STYLE_PROVENIENCE));
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByTrappist(boolean trappist){
//		model.beersFilteredByTrappist(trappist);
		model.setBeerFilteringCurrentValue((Boolean)trappist);
		if(trappist){
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_TRAPPIST_YES));
		}else{
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_TRAPPIST_NO));
		}
		model.applyFilteringToBeers();
		showBeers();
	}
	
	public void beersFilteredByIsTried(boolean tried){
//		model.beersFilteredByIsTried(tried);
		model.setBeerFilteringCurrentValue(tried);
		if(tried){
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_IS_TRIED_YES));
		}else{
			model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_IS_TRIED_NO));
		}
		model.applyFilteringToBeers();
		showBeers();
	}
	
	
	public void beersFilteredByPlaceTried(String place){
//		model.beersFilteredByPlaceTried(place);
		model.setBeerFilteringCurrentValue(place);
		model.setBeersFilteringCurrentAlgorithm(Utils.getBeerFilteringAlgorithm(QueryRunner.BeerFilterAlgorithm.BY_PLACE_TRIED));
		model.applyFilteringToBeers();
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
					
					
					beersFilteredByBreweryCountry(string);
				}
				
				
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByExatcAlcool(0.0);
//				showBeers();
				String string = askExactABVBeersFilteredByExactABV();
				if(string!=null && string!=""){
					double value = Utils.parseDouble(string);
					beersFilteredByExactABV(value);
				}
				
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByExactMark(0);
//				showBeers();
				String string = askExactMarkBeersFilteredByExactMark();
				if(string!=null && string!=""){
					int value = Integer.parseInt(string);
					beersFilteredByExactMark(value);
				}
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
				String string = askStyleBeersFilteredByStyleMain();
				if(string!=null && string!=""){
					Style s = Utils.getMainStyleFromString(string); //!!!!!!!!!!!!!!!!1
					beersFilteredByMainStyle(s);
				}
				
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMinimumAlcool(0.0);
//				showBeers();
				String string = askMinimumABVBeersFilteredByMinimumABV();
				if(string!=null && string!=""){
					double value = Utils.parseDouble(string);
					beersFilteredByMinimumABV(value);
				}
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMiminumMark(0);
//				showBeers();
				String string = askMinimumMarkBeersFilteredByMinimumMark();
				if(string!=null && string!=""){
					int value = Integer.parseInt(string);
					beersFilteredByMinimumMark(value);
				}
			}
			
		});
		
		gui.addActionMenuBeersFilteredByStyleProvenience(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByStyleProvenience(null);
//				showBeers();
				String country = askCountryBeersFiltererdByStyleProvenience();
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
				String string = askExactStarsBeersFilteredByExactStars();
				if(string!=null && string!=""){
					int value = Integer.parseInt(string);
					beersFilteredByExactNumberOfStars(value);
				}
			}
			
		});
		
		gui.addActionMenuBeersFilteredMinimumNumberOfStars(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				model.beersFilteredByMinimumNumberOfStars(0);
//				showBeers();
				String string = askMinimumStarsBeersFilteredByMinimumStars();
				if(string!=null && string!=""){
					int value = Integer.parseInt(string);
					beersFilteredByMinimumNumberOfStars(value);
				}
			}
			
		});
		
		gui.addActionMenuBeersFilteredByStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String string = askStyleBeersFilteredByStyle();
				if(string!=null && string!=""){
					Style s= Utils.getStyleFromString(string);
					beersFilteredByStyle(s);
				}
				
			}
			
		});
		
		
		gui.addActionMenuBeersFilteredByPlaceTried(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
							
				String string = askPlaceBeersFilteredByPlaceTried();
				if(string!=null){
					beersFilteredByPlaceTried(string);
				}
				
			}
			
		});
		
	}
	
	
	public void breweriesFilteredByTrappistYes(){
		model.setBreweryFilteringCurrentAlgorithm(Utils.getBreweryAverageFilteringAlgorithm(QueryRunner.BreweryFilterAlgorithm.TRAPPIST_YES));
		model.applyFilteringToBreweries();
		showBreweries();
	}
	

	public void breweriesFilteredByTrappistNo(){
		model.setBreweryFilteringCurrentAlgorithm(Utils.getBreweryAverageFilteringAlgorithm(QueryRunner.BreweryFilterAlgorithm.TRAPPIST_NO));
		model.applyFilteringToBreweries();
		showBreweries();
	}
	
	public void breweriesFilteredByCountry(String country){
		model.setBreweryFilteringCurrentValue(country);
		model.setBreweryFilteringCurrentAlgorithm(Utils.getBreweryAverageFilteringAlgorithm(QueryRunner.BreweryFilterAlgorithm.COUNTRY));
		model.applyFilteringToBreweries();
		showBreweries();
	}
	
	private void addFilterBreweriesListeners(){
		gui.addActionMenuBreweriesFilteredByNation(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String country = askCountryBreweriesFilteredByCountry();
				if(country!=null){
					breweriesFilteredByCountry(country);
				}
				
			}
			
		});
		
		
		gui.addActionMenuBreweriesFilteredByTrappistNo(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				breweriesFilteredByTrappistNo();
				
			}
			
		});
		
		
		gui.addActionMenuBreweriesFilteredByTrappistYes(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				breweriesFilteredByTrappistYes();
				
			}
			
		});
	}
	
	
	public void stylesFilteredByFermentationHigh(){
		model.setStyleFilteringCurrentAlgorithm(Utils.getStyleFilteringAlgorithm(QueryRunner.StyleFilterAlgorithm.BY_FERMENTATION_HIGH));
		model.applyFilteringTostyles();
		showStyles();
	}
	
	public void stylesFilteredByFermentationSpontan(){
		model.setStyleFilteringCurrentAlgorithm(Utils.getStyleFilteringAlgorithm(QueryRunner.StyleFilterAlgorithm.BY_FERMENTATION_SPONTANEOUS));
		model.applyFilteringTostyles();
		showStyles();
	}
	
	public void stylesFilteredByFermentationLow(){
		model.setStyleFilteringCurrentAlgorithm(Utils.getStyleFilteringAlgorithm(QueryRunner.StyleFilterAlgorithm.BY_FERMENTATION_LOW));
		model.applyFilteringTostyles();
		showStyles();
	}
	
	public void stylesFilteredByCountry(String country){
		model.setStyleFilteringCurrentValue(country);
		model.setStyleFilteringCurrentAlgorithm(Utils.getStyleFilteringAlgorithm(QueryRunner.StyleFilterAlgorithm.BY_COUNTRY));
		model.applyFilteringTostyles();
		showStyles();
	}
	
	public void stylesFilteredByMainStyle(Style main){
		model.setStyleFilteringCurrentValue(main);
		model.setStyleFilteringCurrentAlgorithm(Utils.getStyleFilteringAlgorithm(QueryRunner.StyleFilterAlgorithm.BY_MAIN_STYLE));
		model.applyFilteringTostyles();
		showStyles();
	}
	
	private void addFilterStylesListeners(){
		gui.addActionMenuStylesFilteredByCountryOrigin(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String country = askCountryStylesFilteredByOriginCountry();
				if(country!=null){
					stylesFilteredByCountry(country);
				}
			}
			
		});
		
		
		gui.addActionMenuStylesFilteredByFermentationHigh(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stylesFilteredByFermentationHigh();
				
			}
			
		});
		
		gui.addActionMenuStylesFilteredByFermentationLow(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stylesFilteredByFermentationLow();
				
			}
			
		});
		
		gui.addActionMenuStylesFilteredByFermentationSpontaneous(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stylesFilteredByFermentationSpontan();
				
			}
			
		});
		
		
		gui.addActionMenuStylesFilteredByMainStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Style s = new Style();
				String main= askMainStyleStylesFilteredByMainStyle();
				if(main!=null){
					s.setStyleMainName(main);
					stylesFilteredByMainStyle(s);
				}
				
				
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
				
				model.resetBeerFilter();
				
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
				
				model.resetBreweryFilter();
				
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
				
				model.resetStyleFilter();
				
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
	
	public void stylesSortedByFermentationCategorySubcategory(){
		model.setStyleSortingCurrentAlgorithm(Utils.getStylesSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_CATEGORY));
		showStyles();
	}
	
	public void stylesSortedByFermentationThenCountry(){
//		model.styleSortedByFermentationThenCountry();
		model.setStyleSortingCurrentAlgorithm(Utils.getStylesSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_COUNTRY));
		showStyles();
	}
	
	public void stylesSortedByCountryThenFermentation(){
//		model.styleSortedByCountryThenFermentationy();
		model.setStyleSortingCurrentAlgorithm(Utils.getStylesSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.COUNTRY_FERMENTATION));
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
		
		gui.addActionMenuStylesSortedByFermentationCategorySubcategory(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				stylesSortedByFermentationCategorySubcategory();
				
			}
			
		});
	}
	
	
	public void breweriesSortedByCountryThenName(){
//		model.breweriesSortedByCountryThenName();
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.COUNTRY_NAME));
		showBreweries();
	}
	
	public void breweriesSortedByName(){
//		model.breweriesSortedBynName(null);
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.NAME));
		showBreweries();
	}
	
	public void breweriesSortedByCountryThenAverageAscending(){
//		model.breweriesSortedByCountryThenAverageAscending();
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.COUNTRY_AVERAGE_ASCENDING));
		showBreweries();
	}
	
	public void breweriesSortedByAverageAscending(){
//		model.breweriesSortedByAverageAscending();
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.AVERAGE_ASCENDING));
		showBreweries();
	}
	
	public void breweriesSortedByCountryThenAverageDescending(){
//		model.breweriesSortedByCountryThenAverageDescending();
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.COUNTRY_AVERAGE_DESCENDING));
		showBreweries();
	}
	
	public void breweriesSortedByAverageDescending(){
//		model.breweriesSortedByAverageDescending();
		model.setBrewerySortingCurrentAlgorithm(Utils.getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.COUNTRY_AVERAGE_ASCENDING));
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
			model.exportBeers(type, new FileOutputStream(f), false);
			boolean price = false;
			if(type.isPriceExportable()){
				price = askPrintAlsoPriceWhenExportBeers();				
			}
			model.exportBeers(type, new FileOutputStream(f), price);
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
		dialog.setPrice(Double.toString(b.getPrice()));
		
	
//		addBeerDialogPriceMarkEditable(dialog, b.isTried());
		
	}
	
	private void setStyleInDialog(StyleDialog dialog){
		Style s=model.getStyleShown();
		dialog.setStyleMainName(s.getStyleMainName());
		dialog.setStyleSubcategory(s.getStyleSubCategory());
		dialog.setFermentation(Utils.getFermentationItalianString(s.getFermentation()));
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
		dialog.setBreweryTrappist(b.isAuthenticTrappist());
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
		
		double alcool = 0.0;
		int mark = 0;
		int stars = 0;
		double price = 0.0;
		
		if(addBeerDialog.getABV().equals("")){
			b.setAlcool(alcool);
		}
		else{
			b.setAlcool(Utils.parseDouble(addBeerDialog.getABV()));
		}
		
		
		if(addBeerDialog.getMark().equals("")){
			b.setMark(mark);
		}
		else{
			b.setMark(Integer.parseInt(addBeerDialog.getMark()));
		}
		
		
		if(addBeerDialog.getStars().equals("")){
			b.setNumberOfStars(stars);
		}
		else{
			b.setNumberOfStars(Integer.parseInt(addBeerDialog.getStars()));
		}
		
		
		
		if(addBeerDialog.getPrice().equals("")){
			b.setPrice(price);
		}
		else{
			b.setPrice(Utils.parseDouble(addBeerDialog.getPrice()));
		}
		
		
		
//		b.setAlcool(Utils.parseDouble(addBeerDialog.getABV()));
//		b.setMark(Integer.parseInt(addBeerDialog.getMark()));
//		b.setNumberOfStars(Integer.parseInt(addBeerDialog.getStars()));
		b.setTried(addBeerDialog.isTried());
		b.setPlaceTried(addBeerDialog.getPlace());
//		b.setPrice(Utils.parseDouble(addBeerDialog.getPrice()));
		b.setDescription(addBeerDialog.getDescription());
		
//		brewery.setName(addBeerDialog.getName());
//		brewery.setCountry(addBeerDialog.get);
		
//		Utils.printStyle(b.getStyle(), System.out);
		
		return b;
	}
	
	private Brewery getBreweryInAddNewBreweryDialog(){
		Brewery b = new Brewery();
		b.setName(addBreweryDialog.getBreweryName());
		b.setCountry(addBreweryDialog.getBreweryCountry());
		b.setTown(addBreweryDialog.getBreweryTown());
		b.setDescription(addBreweryDialog.getDescription());
		b.setWebsite(addBreweryDialog.getBreweryWebsite());	
		b.setAuthenticTrappist(addBreweryDialog.isTrappist());  
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
	
	
	private void deleteBeersLogic(List<Beer> beers) throws UpdateSavingException{
		model.deleteBeers(beers);
		refreshData();
	}
	
	private void deleteBreweriesLogic(List<BreweryAverage> breweries) throws UpdateSavingException{
		model.deleteBreweries(breweries);
		refreshData();
	}
	
	private void deleteStylesLogic(List<Style> styles) throws UpdateSavingException{
		model.deleteStyles(styles);
		refreshData();
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
	
	private void addBeerDialogPriceMarkEditable( boolean editable){
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
	
	public void showAddOrModifyBeer(boolean addNewBeerOrModify, boolean fromDialog){
		if(addNewBeerOrModify){
			model.setAddNewBeerOrModifyBeer(true);
			showAddBeerDialog();
			addBeerDialogPriceMarkEditable(true);
		}
		else{
			model.setAddNewBeerOrModifyBeer(false);
			showModifyBeerDialog(fromDialog);
		}
	}
	
	public void showAddOrModifyBrewery(boolean addNewBreweryOrModify, boolean fromDialog){
		if(addNewBreweryOrModify){
			model.setAddNewBreweryOrModifyBrewery(true);
			showAddBreweryDialog();
		}else{
			model.setAddNewBreweryOrModifyBrewery(false);
			showModifyBreweryDialog(fromDialog);
		}
	}
	
	public void showAddOrModifyStyle(boolean addNewStyleOrModify, boolean fromDialog){
		if(addNewStyleOrModify){
			model.setAddNewStyleOrModifyStyle(true);
			showAddStyleDialog();
		}else{
			model.setAddNewStyleOrModifyStyle(false);
			showModifyStyleDialog(fromDialog);
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
		List<String> styles = new LinkedList<String>(model.getOnlyMainStylesString());
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
				
				showAddOrModifyBeer(true, true);
				
			}
			
		});
		
		gui.addActionMenuAddNewBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAddOrModifyBrewery(true, true);
			}
			
		});
		
		gui.addActionMenuAddNewStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showAddOrModifyStyle(true, true);
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
				
				viewBeerDialog.setVisible(false);
				viewBeerDialog.dispose();
			}
			
		});
		
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
				
				viewBreweryDialog.setVisible(false);
				viewBreweryDialog.dispose();
			}
			
		});
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
				
				
				viewStyleDialog.setVisible(false);
				viewStyleDialog.dispose();
				
			}
			
		});
		

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
	
	private void showModifyBeerDialog(boolean fromDialog){
		if(fromDialog){
			viewBeerDialog.setVisible(false);
			viewBeerDialog.dispose();
		}

		
		addBeerDialog = new ViewAddNewBeer();
		addBeerDialog.fillThings(Utils.getBreweriesString(model.getBreweryData()), Utils.getStylesString(model.getStyleData()));
		setBeerInDialog(addBeerDialog);
		
		setAddNewBeerOkButton();
		setAddNewBeerCancelButton();
		setAddNewBeerRadioButton();
		
//		addBeerDialog.setTried(true);
//		addBeerDialogPriceMarkEditable(true);
		
//		model.setAddNewBeerOrModifyBeer(false);  already done.
		addBeerDialogPriceMarkEditable(model.getBeerShown().isTried());
		addBeerDialog.setVisible(true);
	}
	
	private void showModifyBreweryDialog(boolean fromDialog){
		if(fromDialog){
			viewBreweryDialog.setVisible(false);
			viewBreweryDialog.dispose();
		}

		
		addBreweryDialog=new ViewAddNewBrewery();
		addBreweryDialog.fillThings(model.getCountries());
		setBreweryInDialog(addBreweryDialog);
		
		setAddNewBreweryOkButton();
		setAddNewBreweryCancelButton();;
		
//		model.setAddNewBreweryOrModifyBrewery(false); already done.
		
		addBreweryDialog.setVisible(true);
	}
	
	private void showModifyStyleDialog(boolean fromDialog){
		if(fromDialog){
			viewStyleDialog.setVisible(false);
			viewStyleDialog.dispose();
		}

		
		addStyleDialog=new ViewAddNewStyle();
		addStyleDialog.fillThings(Utils.getMainStyleString(model.getOnlyMainStyles()), Utils.getFermentationsItalianString(), model.getCountries());
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
							
				showAddOrModifyStyle(false, true);
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
							
				showAddOrModifyBrewery(false, true);
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
				
				showAddOrModifyBeer(false, true);
				
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
	
	
	private void addTablePopupMenu(){
		
		addPopupMenuEventListener();
		addPopupMenuViewThingsListener();
		addPopupMenuModifyThingsListener();
		addPopupMenuDeleteThingsListener();
	}
	
	
	private void addPopupMenuEventListener(){
		gui.addPopupListener(new PopupMenuListener(){

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				
				
				//select the row when the popup is select.
//				int p = gui.getPointForPopup();
//				System.out.println("The row is : "+p);
//				gui.setSelectedRow(p, p);
				int p = gui.getNewPointForPopoup();
				gui.setSelectedRow(p, p);
			}
			
		});
	}
	
	private void addPopupMenuViewThingsListener(){
		gui.addActionPopupMenuViewThings(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = gui.getTableSelectedRow();
				openDialogShowThing(row);
			}
			
		});
	}
	
	private void addPopupMenuModifyThingsListener(){
		gui.addActionPopupMenuModifyThings(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(model.getDataShownNow()==DataShownNow.BEER){
					model.setBeerShown(model.getSelectedBeer(gui.getTableSelectedRow()));
					showAddOrModifyBeer(false, false);
				}
				else if(model.getDataShownNow()==DataShownNow.STYLE){
					model.setStyleShown(model.getSelectedStyle(gui.getTableSelectedRow()));
					showAddOrModifyStyle(false, false);
				}else{
					model.setBreweryShown(model.getSelectedBrewery(gui.getTableSelectedRow()));
					showAddOrModifyBrewery(false, false);
				}
			}
			
		});
	}
	
	private void addPopupMenuDeleteThingsListener(){
		gui.addActionPopupMenuDeleteThings(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					
				System.out.println(Arrays.toString(gui.getTableSelectedRows()));
				
				if(askSureToDelete()){
					try{
						if(model.getDataShownNow()==DataShownNow.BEER){
							List<Beer> toDelete = Utils.subListBeer(model.getBeerData(), gui.getTableSelectedRows());
							
							
//							System.out.println("Beer at pos "+gui.getTableSelectedRows()[0]);
//							Utils.printBeer(model.getSelectedBeer(gui.getTableSelectedRows()[0]), System.out);
							
							
							deleteBeersLogic(toDelete);
						}
						else if(model.getDataShownNow()==DataShownNow.STYLE){
							List<BreweryAverage> toDelete = Utils.subListBrewery(model.getBreweryAverageData(), gui.getTableSelectedRows());
							deleteBreweriesLogic(toDelete);
						}else{
							List<Style> toDelete = Utils.subListStyle(model.getStyleData(), gui.getTableSelectedRows());
							deleteStylesLogic(toDelete);
						}
					}catch(UpdateSavingException e){
						showExceptionDialog(e);
					}
				}
			}
			
		});
	}
	
	
	private void popupMenuViewEnabled(boolean enabled){
		gui.setPopupMenuViewThingsTableEnabled(enabled);
	}
	
	
	private void popupMenuModifyEnabled(boolean enabled){
		gui.setPopoupMenuModifyThingsTableEnabled(enabled);
	}
	
	
	private void openDialogShowThing(int row){
		if(model.getDataShownNow()==DataShownNow.BEER){
			openBeerDialog(row);

		}else if(model.getDataShownNow()==DataShownNow.STYLE){
			openStyleDialog(row);
		}else{
			openBreweryDialog(row);
		}
	}

	
	private void addListSelectionListener(){
		gui.addMouseListener(new MouseListener(){
			
			
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					JTable t =(JTable)arg0.getSource();
					int row=t.getSelectedRow();
//					if(model.getDataShownNow()==DataShownNow.BEER){
//							openBeerDialog(row);
//				
//					}else if(model.getDataShownNow()==DataShownNow.STYLE){
//						openStyleDialog(row);
//					}else{
//						openBreweryDialog(row);
//					}
					openDialogShowThing(row);
				}
//				else if(arg0.isPopupTrigger()){
//					gui.showPopupMenu(true);
//				}
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
		
		
		gui.addTableListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
//				gui.showPopupMenu(true);
								
				int [] rows = gui.getTableSelectedRows();
				
				if(rows.length>1){
					popupMenuModifyEnabled(false);
					popupMenuViewEnabled(false);
				}
				else{
					popupMenuModifyEnabled(true);
					popupMenuViewEnabled(true);
				}
				
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
				
				newConf.setDefaultView(Utils.getViewDefaultFromDescription(preferencesDialog.getComboBoxDeafultViewSelectedItem()));
				
				newConf = ConfigurationFactory.setupPath(newConf);
				
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
//		System.out.println("Preferences");
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
		
		exceptionDialog.init();
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
		String ret = null;
		if(countries.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_NATIONS);
		}
		else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_ORIGIN_STYLE, countries);
		}
		return ret;
	}
	
	
	private String askBreweryBeersFilteredByBrewery(){
		optionPane.setParent(gui);
		String ret = null;
		String[] breweries = Utils.getBreweryStringArray(model.getBreweryData());
		if(breweries.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_BREWERY);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_BREWERY, breweries);
		}	
		return ret;
	}
	
	private String askStyleBeersFilteredByStyleMain(){
		optionPane.setParent(gui);
		String ret = null;
		String[] styles = Utils.getMainStyleStringArray(model.getOnlyMainStyles());
		if(styles.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_STYLES);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_STYLE, styles);
		}		
		return ret;
	}
	
	private String askStyleBeersFilteredByStyle(){
		optionPane.setParent(gui);
		String ret = null;
		String[] styles = Utils.getStyleStringArray(model.getStyleData());
		if(styles.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_STYLES);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEER_FILTER_BY_STYLE_AND_SUB, styles);
		}	
		return ret;
	}
	
	private String askCountryBeersFilteredByCountry(){
		optionPane.setParent(gui);
		String ret = null;
		String[] countries = Utils.toArray(model.getCountriesWithBrewery());
		if(countries.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_NATIONS);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_ORIGIN_STYLE, countries);
		}		
		return ret;
	}
	
	private String askExactMarkBeersFilteredByExactMark(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_EXACT_MARK, Utils.Constants.DEFAULT_MARK);
	}
	
	private String askExactStarsBeersFilteredByExactStars(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_EXACT_STAR, Utils.Constants.DEFAULT_STAR);
	}
	
	private String askMinimumMarkBeersFilteredByMinimumMark(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_MINIMUM_MARK, Utils.Constants.DEFAULT_MARK);
	}
	
	private String askMinimumStarsBeersFilteredByMinimumStars(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_MINIMUM_STAR, Utils.Constants.DEFAULT_STAR);
	}
	
	private String askMinimumABVBeersFilteredByMinimumABV(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_MINIMUM_ABV, Utils.Constants.DEFAULT_ABV);
	}
	
	private String askExactABVBeersFilteredByExactABV(){
		optionPane.setParent(gui);
		return optionPane.showBlankTextInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_EXACT_ABV, Utils.Constants.DEFAULT_ABV);
	}
	
	private String askCountryBreweriesFilteredByCountry(){
		optionPane.setParent(gui);
		String ret=null;
		String[] countries = Utils.toArray(model.getCountriesWithBrewery());
		if(countries.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_NATIONS);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BREWERIES_FILTER_BY_COUNTRY, countries);
		}	
		return ret;
	}
	
	private String askMainStyleStylesFilteredByMainStyle(){
		optionPane.setParent(gui);
		String ret=null;
		String[] styles = Utils.toArray(model.getOnlyMainStylesString());
		if(styles.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_STYLES);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.STYLES_FILTER_BY_MAIN_STYLE, styles);
		}	
		return ret;
	}
	
	private String askCountryStylesFilteredByOriginCountry(){
		optionPane.setParent(gui);
		String ret=null;
		String[] countries = Utils.toArray(model.getCountriesWithStyle());
		if(countries.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_NATIONS);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.STYLES_FILTER_BY_ORIGIN_COUNTRY, countries);
		}	
		return ret;
	}
	
	private String askPlaceBeersFilteredByPlaceTried(){
		optionPane.setParent(gui);
		String ret = null;
		String [] places = Utils.toArray(model.getAllPlaces());
		if(places.length==0){
			optionPane.showErrorMessageDialog(Utils.Constants.ERROR, Utils.Constants.NO_PLACES);
		}else{
			ret = optionPane.showComboBoxInput(Utils.Constants.FILTER_BY_TITLE, Utils.Constants.BEERS_FILTER_BY_PLACE_TRIED, places);
		}
		
		return ret;
	}
	
	private boolean askPrintAlsoPriceWhenExportBeers(){
		optionPane.setParent(gui);
		int res;
		res = optionPane.showYesNo(Utils.Constants.QUESTION, Utils.Constants.WRITE_ALSO_TOTALE_PRICE);
		return ViewJOptionPane.isYesOption(res);
	}
	
	
	public boolean askSureToDelete(){
		optionPane.setParent(gui);
		boolean ret = false;
		int res = optionPane.showOkCancel(Utils.Constants.QUESTION, Utils.Constants.CONFIRMATION_BEFORE_DELETE);
		if(ViewJOptionPane.isOkOption(res)){
			ret = true;
		}
		return ret;
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
