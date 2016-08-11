package org.nbena.beersmanager.exe.ui.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.ui.models.Model;
import org.nbena.beersmanager.exe.ui.models.Model.DataShownNow;
import org.nbena.beersmanager.exe.ui.models.ModelBeerTable;
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
import org.nbena.beersmanager.exe.ui.views.ViewViewBeer;
import org.nbena.beersmanager.exe.ui.views.ViewViewBrewery;
import org.nbena.beersmanager.exe.ui.views.ViewViewStyle;
import org.nbena.beersmanager.export.Exporter;
import org.nbena.beersmanager.export.JSONExporter;
import org.nbena.beersmanager.export.MSExcelNewExporter;
import org.nbena.beersmanager.export.MSExcelOldExporter;

public class ControllerMainGUI {
	
	private ViewMainGUI gui;
	private Model model;
	
	private ViewAddNewBeer addBeerDialog;
	private ViewAddNewBrewery addBreweryDialog;
	private ViewAddNewStyle addStyleDialog;
	
	private ViewViewBeer viewBeerDialog;
	private ViewViewBrewery viewBreweryDialog;
	private ViewViewStyle viewStyleDialog;
	//
	private Exporter exporter;

	public ControllerMainGUI(ViewMainGUI gui, Model model) {
		this.gui=gui;
		this.model=model;
		gui.setVisible(true);
		
		addListeners();
	}
	
	private void addListeners(){
		addFileExporterListeners();
		addAddNewThingsListeners();
		addListSelectionListener();
		addViewListeners();
		addOrderBeersListeners();

	}
	
	public void showBeers(){
		enableShowBeersItems();
		
		model.setTableModel(new ModelBeerTable());
		model.showBeerData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void showBreweries(){
		enableShowBreweriesItems();
		
		model.setTableModel(new ModelBreweryTable());
		model.showBreweryData();
		
		gui.setTableModel(model.getTableModel());
	}
	
	public void showStyles(){
		enableShowStylesItems();
		
		model.setTableModel(new ModelStyleTable());
		model.showStyleData();
		
		gui.setTableModel(model.getTableModel());
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
	
	
	private void addFilterBeersListeners(){
		
		gui.addActionMenuBeersFilteredByBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByBrewery(null);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByBreweryCountry(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByBreweryCountry(null);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByExatcAlcool(0.0);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByExactMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByExactMark(0);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationHigh(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beerFilteredByFermentation(Fermentation.HIGH);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationLow(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beerFilteredByFermentation(Fermentation.LOW);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByFermentationSpontaneous(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beerFilteredByFermentation(Fermentation.SPONTANEOUS);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTrappistNo(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByTrappist(false);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTrappistYes(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByTrappist(true);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByIsTriedYes(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByIsTried(true);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredIsTriedNo(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByIsTried(false);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMainStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByMainStyle(null);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumABV(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByMinimumAlcool(0.0);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByMinimumMark(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByMiminumMark(0);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredByStyleProvenience(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByStyleProvenience(null);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredExactNumberOfStars(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByExactNumberOfStars(0);
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersFilteredMinimumNumberOfStars(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.beersFilteredByMinimumNumberOfStars(0);
				showBeers();
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
	
	private void addOrderBeersListeners(){
		gui.addActionMenuBeersSortedByCountryOfBreweryStyle(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				model.sortBeerByCountryOfBreweryStyle();
				showBeers();
			}
			
		});
		
		gui.addActionMenuBeersSortedByFermentationCountryOfStyleBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				model.sortBeerByFermentationCountryOfStyleBrewery();
				showBeers();			
			}
			
		});
		
		gui.addActionMenuBeersSortedByFermentationStyleCountryOfBrewery(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				model.sortBeerByFermentationStyleCountryOfBrewery();
				showBeers();
			}
			
		});
	}
	
	
	private void addFileExporterListeners(){
		gui.addActionExportAsExcelNew(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exporter=new MSExcelNewExporter();
				//exporter.writeBeer(beers, out);
			}
			
		});
		
		gui.addActionExportAsExcelOld(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exporter=new MSExcelOldExporter();
				
			}
			
		});
		
		gui.addActionExportAsJSON(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exporter=new JSONExporter();
				
			}
			
		});
		
		gui.addActionExportAsPdf(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//exporter=new PDFExporter();
				
			}
			
		});
		
		gui.addActionExportAsXML(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//exporter=new XMLExporter();
				
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
	
	private void openStyleDialog(int row){
		Style s=model.getSelectedStyle(row);
		model.setStyleDialog(s);
		viewStyleDialog=new ViewViewStyle();
		setStyleInDialog(viewStyleDialog);
			
		setOkCancelViewDialog(viewStyleDialog);
		setStyleDialogModifyButtonListener();
		
		viewStyleDialog.setVisible(true);
	}
	
	private void openBreweryDialog(int row){
		Brewery b=model.getSelectedBrewery(row);
		model.setBreweryDialog(b); //call then
		viewBreweryDialog=new ViewViewBrewery();
		setBreweryInDialog(viewBreweryDialog);
		
		setOkCancelViewDialog(viewBreweryDialog);
		setBreweryModifyButtonListener();
		
		viewBreweryDialog.setVisible(true);	
	}
	
	private void openBeerDialog(int row){
		Beer b =model.getSelectedBeer(row);
		model.setBeerDialog(b);
		viewBeerDialog=new ViewViewBeer();
		setBeerInDialog(viewBeerDialog);
				
		setOkCancelViewDialog(viewBeerDialog);
		setBeerModifyButtonListener();
		
		viewBeerDialog.setVisible(true);
	}
	
	//this stupid method, we can make it static
	private void setBeerInDialog(BeerDialog dialog){
		Beer b=model.getBeerDialog();
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
		Style s=model.getStyleDialog();
		dialog.setStyleMainName(s.getStyleMainName());
		dialog.setStyleSubcategory(s.getStyleSubCategory());
		dialog.setFermentation(Utils.getFermentationString(s.getFermentation()));
		dialog.setStyleCountry(s.getStyleCountryOrigin());
		dialog.setDescription(s.getDescription());
	}
	
	public void setBreweryInDialog(BreweryDialog dialog){
		Brewery b=model.getBreweryDialog();
		dialog.setBreweryName(b.getName());
		dialog.setBreweryTown(b.getTown());
		dialog.setBreweryCountry(b.getCountry());
		dialog.setBreweryDescription(b.getDescription());
		dialog.setBreweryWebsite(b.getWebsite());
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
	
	private void addListSelectionListener(){
		gui.addTableListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent event){
				
				if(event.getValueIsAdjusting()){ //if not do this, the event is fired twice.
					
					int row=gui.getTableSelectedRow();
					if(model.getDataShownNow()==DataShownNow.BEER){
//						Beer b=model.getSelectedBeer(row);
						//Utils.printBeer(b, System.out);
						openBeerDialog(row);
						
					}else if(model.getDataShownNow()==DataShownNow.BREWERY){
//						Brewery b=model.getSelectedBrewery(row);
//						model.setBreweryDialog(b); //call then
//						viewBreweryDialog=new ViewViewBrewery();
//						viewBreweryDialog.setBrewery(b);
//						viewBreweryDialog.setVisible(true);
						openBreweryDialog(row);
					}
					else{
//						Style s=model.getSelectedStyle(row);
//						model.setStyleDialog(s);
//						viewStyleDialog=new ViewViewStyle();
//						viewStyleDialog.setStyle(s);
//						viewStyleDialog.setVisible(true);
						openStyleDialog(row);
					}
				}
				
			}
			
		});
	}
	
	
	

}
