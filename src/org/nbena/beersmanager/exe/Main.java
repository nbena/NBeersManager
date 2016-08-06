package org.nbena.beersmanager.exe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.JSONExporter;
import org.nbena.beersmanager.query.QueryRunner;

public class Main {
	
	private static final String GUI = "gui";
	private static final String CMD = "cmd";
	private LinkedList<String> errors;
	private File[] jsonFiles;
	private String directory;
	
	private List<Beer> beers;
	private List<Brewery> breweries;
	private List<Style> styles;
	

	public Main() throws FileNotFoundException, Exception {
		directory=Utils.currentDirectory();
		errors=new LinkedList<String>();
		loadFiles();
		System.out.println("Starting reading...");
		

	}
	
	/**
	 * @return the beers
	 */
	public List<Beer> getBeers() {
		return beers;
	}

	/**
	 * @param beers the beers to set
	 */
	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}

	/**
	 * @return the breweries
	 */
	public List<Brewery> getBreweries() {
		return breweries;
	}

	/**
	 * @param breweries the breweries to set
	 */
	public void setBreweries(List<Brewery> breweries) {
		this.breweries = breweries;
	}

	/**
	 * @return the styles
	 */
	public List<Style> getStyles() {
		return styles;
	}

	/**
	 * @param styles the styles to set
	 */
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	private void loadFiles(){
		jsonFiles=new File[3];
		jsonFiles[0]=new File(Utils.jsonBeers(directory));
		jsonFiles[1]=new File(Utils.jsonBreweries(directory));
		jsonFiles[2]=new File(Utils.jsonStyle(directory));
		
		System.out.println("Files...");
		System.out.println(jsonFiles[0].getAbsolutePath()+"\n");
		System.out.println(jsonFiles[1].getAbsolutePath()+"\n");
		System.out.println(jsonFiles[2].getAbsolutePath()+"\n");
	}
	
	private void loadBreweriesStyles() throws FileNotFoundException, Exception{
		
		styles=Utils.readStyles(jsonFiles[2]);
		breweries=Utils.readBreweries(jsonFiles[1]);
	}
	
	private void loadListsComplete() throws FileNotFoundException, Exception{
		
		styles=Utils.readStyles(jsonFiles[2]);
		breweries=Utils.readBreweries(jsonFiles[1]);
		beers=Utils.readBeers(jsonFiles[0], breweries, styles);
	}
	
	private void saveStyles() throws FileNotFoundException, Exception	{
		System.out.println("Now adding styles...\n");
		Utils.saveStyles(StupidClass.someStyle(), jsonFiles[2]);
	}
	
	private void saveBreweries() throws FileNotFoundException, Exception	{
		System.out.println("Now adding  breweries...\n");
		Utils.saveBreweries(StupidClass.someBreweries(), jsonFiles[1]);
		
	}
	
	private void saveBeers() throws FileNotFoundException, Exception	{
		System.out.println("Now adding beers...\n");
		Utils.saveBeers(StupidClass.someBeers(breweries, styles), jsonFiles[0]);		
	}

	
	public static void main(String[] args) {
		try {
			Main m = new Main();

			
			m.saveStyles();
			m.saveBreweries();
			
			m.loadBreweriesStyles();
			
			m.saveBeers();
			
			m.loadListsComplete();
			
			Utils.printStylesComplete(m.getStyles(),System.out);
			Utils.printBreweriesComplete(m.getBreweries(), System.out);
			Utils.printBeersComplete(m.getBeers(), System.out);


			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
		if(args.length>0){
			if(args[0].equals(GUI)){
				
			}
			else{
				System.out.println(Utils.currentDirectory());
			}
		}
		*/


	}

}