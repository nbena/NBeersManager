package org.nbena.beersmanager.exe.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.nbena.beersmanager.exe.models.Model;
import org.nbena.beersmanager.exe.views.ViewMainGUI;
import org.nbena.beersmanager.export.Exporter;
import org.nbena.beersmanager.export.JSONExporter;
import org.nbena.beersmanager.export.MSExcelNewExporter;
import org.nbena.beersmanager.export.MSExcelOldExporter;

public class ControllerMainGUI {
	
	private ViewMainGUI gui;
	private Model model;
	
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

}
