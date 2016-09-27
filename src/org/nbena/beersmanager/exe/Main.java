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
package org.nbena.beersmanager.exe;


import java.io.FileNotFoundException;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONException;
import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.conf.ConfigurationFactory;
import org.nbena.beersmanager.exceptions.RecomposingException;
import org.nbena.beersmanager.exe.ui.Controller;
import org.nbena.beersmanager.exe.ui.models.Model;
import org.nbena.beersmanager.exe.ui.views.ViewMainGUI;

/**
 * The main class. It creates instance of View, Model and Controller.
 * @author nbena
 *
 */
public class Main {
	
//	private static final String GUI = "gui";
//	private static final String CMD = "cmd";
//	private LinkedList<String> errors;
//	private File[] jsonFiles;
//	private String directory;
//	
//	private List<Beer> beers;
//	private List<Brewery> breweries;
//	private List<Style> styles;
//	
//
//	public Main() throws FileNotFoundException, Exception {
//		directory=Utils.currentDirectory();
//		errors=new LinkedList<String>();
//		loadFiles();
//		System.out.println("Starting reading...");
//		
//
//	}
//	
//	/**
//	 * @return the beers
//	 */
//	public List<Beer> getBeers() {
//		return beers;
//	}
//
//	/**
//	 * @param beers the beers to set
//	 */
//	public void setBeers(List<Beer> beers) {
//		this.beers = beers;
//	}
//
//	/**
//	 * @return the breweries
//	 */
//	public List<Brewery> getBreweries() {
//		return breweries;
//	}
//
//	/**
//	 * @param breweries the breweries to set
//	 */
//	public void setBreweries(List<Brewery> breweries) {
//		this.breweries = breweries;
//	}
//
//	/**
//	 * @return the styles
//	 */
//	public List<Style> getStyles() {
//		return styles;
//	}
//
//	/**
//	 * @param styles the styles to set
//	 */
//	public void setStyles(List<Style> styles) {
//		this.styles = styles;
//	}
//
//	private void loadFiles(){
//		jsonFiles=new File[3];
//		jsonFiles[0]=new File(Utils.jsonBeers(directory));
//		jsonFiles[1]=new File(Utils.jsonBreweries(directory));
//		jsonFiles[2]=new File(Utils.jsonStyle(directory));
//		
//		System.out.println("Files...");
//		System.out.println(jsonFiles[0].getAbsolutePath()+"\n");
//		System.out.println(jsonFiles[1].getAbsolutePath()+"\n");
//		System.out.println(jsonFiles[2].getAbsolutePath()+"\n");
//	}
//	
//	private void loadBreweriesStyles() throws FileNotFoundException, Exception{
//		
//		styles=Utils.readStyles(jsonFiles[2]);
//		breweries=Utils.readBreweries(jsonFiles[1]);
//	}
//	
//	private void loadListsComplete() throws FileNotFoundException, Exception{
//		
//		styles=Utils.readStyles(jsonFiles[2]);
//		breweries=Utils.readBreweries(jsonFiles[1]);
//		beers=Utils.readBeersFromSpecial(jsonFiles[0], breweries, styles);
//	}
//	
//	private void saveStyles() throws FileNotFoundException, Exception	{
//		System.out.println("Now adding styles...\n");
//		Utils.saveStyles(StupidClass.someStyle(), jsonFiles[2]);
//	}
//	
//	private void saveBreweries() throws FileNotFoundException, Exception	{
//		System.out.println("Now adding  breweries...\n");
//		Utils.saveBreweries(StupidClass.someBreweries(), jsonFiles[1]);
//		
//	}
//	
//	private void saveBeers() throws FileNotFoundException, Exception	{
//		System.out.println("Now adding beers...\n");
//		Utils.saveBeers(StupidClass.someBeers(breweries, styles), jsonFiles[0]);		
//	}

	
	public static void main(String[] args) {
//		try {
//
//		
//			
//			
//			try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//					| UnsupportedLookAndFeelException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Model model =new Model();
//			
//			
//			model.setConfiguration(ConfigurationFactory.getDefaultConfiguration());
//			
//			model.setCountries(StupidClass.someCountries());
//			
//			ViewMainGUI gui = new ViewMainGUI(model);
//			ControllerMainGUI controller=new ControllerMainGUI(gui, model);
//			
//			
//			controller.setShowBreweriesAverages(true);
//			
//			
//			model.setStyleData(StupidClass.someStyle());
//			model.setBreweryData(StupidClass.someBreweries());
//			model.setBeerData(StupidClass.someBeers(StupidClass.someBreweries(), StupidClass.someStyle()));
//			
//			model.setAverages(StupidClass.someBeers(StupidClass.someBreweries(), StupidClass.someStyle()));
//			
//			
//			controller.showBeers();
////			controller.showBreweries();
////			controller.showStyles();
			
			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		

//		Configuration config = ConfigurationFactory.getDefaultConfiguration();
//		
//		config.setBeerFilterAlgorithm(BeerFilterAlgorithm.BY_MINIMUM_MARK);
//		config.setBeerFilterValue(100);
//		
//		config.setBreweryFilterAlgorithm(BreweryFilterAlgorithm.COUNTRY);
//		config.setBreweryFilterValue("Belgio");
//		
//		config.setStyleFilterAlgorithm(StyleFilterAlgorithm.BY_MAIN_STYLE);
//		config.setStyleFilterValue(new Style("Stout", "Imperial", "ciao", "Eire", Fermentation.HIGH));
//		try {
//			ConfigurationFactory.writeConfiguration(config, "C:\\Users\\nicola\\Documents\\BeerDb\\config.json");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			Configuration read = ConfigurationFactory.readConfiguration("C:\\Users\\nicola\\Documents\\BeerDb\\config.json");
//			Utils.printConfiguration(read, System.out);
//		} catch (JSONException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//		System.out.println(Utils.currentDirectory());
		
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				
				Controller.showExceptionDialog(e);
				System.exit(1);
			}
			
			Model model =new Model();
			Configuration conf = null;
			
			
			try {
				conf = ConfigurationFactory.readConfiguration(ConfigurationFactory.getDefaultConfigurationPath());
				conf = ConfigurationFactory.setupPath(conf);
				model.setConfiguration(conf);
			} catch (JSONException | FileNotFoundException e) {
				
				Controller.showExceptionDialog(e);
				Controller.tellToUserDefaultOptionWillBeUsed();
				
				conf = ConfigurationFactory.getDefaultConfiguration();
				model.setConfiguration(conf);
				try {
					model.saveConfiguration();
				} catch (FileNotFoundException e1) {
					
					Controller.tellUserErrorSaveDefaultConfig();
					//it happens only if we are very unlucky!!
				}
			}
			
			try {
				model.setCountries(Utils.getCountries(model.getConfiguration().getCountriesFilePath()));
			} catch (JSONException | FileNotFoundException e) {
				
				Controller.showExceptionDialog(e);
				Controller.tellUserErrorNoCountry();
				System.exit(1);
			}
			
			
			ViewMainGUI gui = new ViewMainGUI(model);
			Controller controller=new Controller(gui, model);
			
			
			controller.setShowBreweriesAverages(true);
			
			
			try {
				model.readThings();
			} catch (FileNotFoundException | JSONException | RecomposingException e) {
				
				Controller.showExceptionDialog(e);
				System.exit(1);
			}
			
			model.setAverages();
			
			
//			controller.showBeers();
//			controller.showBreweries();
//			controller.showStyles();
			
			controller.showDefault();

//			Utils.printBeers(model.getBeerData(), System.out);


	}

}
