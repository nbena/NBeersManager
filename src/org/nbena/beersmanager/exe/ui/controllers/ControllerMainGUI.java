package org.nbena.beersmanager.exe.ui.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.ui.models.Model;
import org.nbena.beersmanager.exe.ui.models.Model.DataShownNow;
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
		viewStyleDialog.setStyle(s);
		viewStyleDialog.setVisible(true);
		
		setOkCancelViewDialog(viewStyleDialog);
		setStyleDialogModifyButtonListener();
	}
	
	private void openBreweryDialog(int row){
		Brewery b=model.getSelectedBrewery(row);
		model.setBreweryDialog(b); //call then
		viewBreweryDialog=new ViewViewBrewery();
		viewBreweryDialog.setBrewery(b);
		viewBreweryDialog.setVisible(true);
		
		setOkCancelViewDialog(viewBreweryDialog);
		setBreweryModifyButtonListener();
	}
	
	private void setStyleDialogModifyButtonListener(){
		viewStyleDialog.addActionListenerModifyButton(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewStyleDialog.setVisible(false);
				viewStyleDialog.dispose();
				
				addStyleDialog=new ViewAddNewStyle();
				addStyleDialog.fillThings(model.getOnlyMainStyle(), model.getCountries());
				addStyleDialog.setStyle(model.getStyleDialog());
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
				addBreweryDialog.setBrewery(model.getBreweryDialog());
				addBreweryDialog.setVisible(true);
				
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
						Beer b=model.getSelectedBeer(row);
						//Utils.printBeer(b, System.out);
						
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
