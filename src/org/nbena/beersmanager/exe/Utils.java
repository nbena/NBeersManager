package org.nbena.beersmanager.exe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.BeerJSONSpecialClass;
import org.nbena.beersmanager.json.Converter;
import org.nbena.beersmanager.json.JSONExporter;

public class Utils {

	public Utils() {
	}
	
	
	public static void printBeersComplete(List<Beer> beers, OutputStream output){
		PrintStream out=new PrintStream(output);
		out.println("-------------Beers:--------------");
		for(Beer beer: beers){
			out.println("-------");
			out.println(" "+beer.getBrewery().getName()+": "+beer.getName());
			out.println(" "+beer.getStyle().getStyleMainName()+"  "+beer.getStyle().getStyleSubCategory());
			out.println(" "+beer.getColor()+" - Fermentation: "+beer.getFermentation());
			out.println(" Alcool: "+beer.getAlcool()+"% ");
			out.println(" Description: "+beer.getDescription());
			out.println(" Mark: "+beer.getMark());
			out.println(" Star: "+beer.getNumberOfStars());
			if(beer.isTried()){
				out.println(" Tried: yes");
				out.println(" Place tried: "+beer.getPlaceTried()+ " Price: "+beer.getPrice());
			}
			else{
				out.println(" Tried: no"+System.lineSeparator());
			}
		}
		out.println();
	}
	
	public static void printBreweriesComplete(List<Brewery> breweries, OutputStream output){
		PrintStream out=new PrintStream(output);
		out.println("-------------Breweries:--------------");
		for(Brewery brewery: breweries){
			out.println("-------");
			out.println(" "+brewery.getName()+" - "+brewery.getTown()+" - "+brewery.getCountry()+" - "+brewery.getWebsite());
			out.println(" "+brewery.getDescription()+"\n");
		}
	}
	
	public static void printStylesComplete(List<Style> styles, OutputStream output){
		PrintStream out=new PrintStream(output);
		out.println("-------------Style:--------------");
		for(Style style: styles){
			out.println(" "+style.getStyleSubCategory()+" "+style.getStyleMainName()+", "+style.getFermentation()+" fermentation");
			out.println(" "+style.getDescription()+"\n");
		}
	}
	
	public static String currentDirectory(){
		String path=new File(".").getAbsolutePath();
		return path.substring(0, path.length()-1);
	}
	
	public static String jsonStyle(String directory){
		return directory.concat("styles.json");
	}
	
	public static String jsonBreweries(String directory){
		return directory.concat("breweries.json");
	}
	
	public static String jsonBeers(String directory){
		return directory.concat("beers.json");
	}
	
	
	public static List<Style> readStyles(File file) throws FileNotFoundException, Exception{
		 return Converter.toNormalStyleList(JSONExporter.readStylesSpecial(new FileInputStream(file)));  
	}
	
	public static List<Brewery> readBreweries(File file) throws FileNotFoundException, Exception{
		return JSONExporter.readBreweries(new FileInputStream(file));
	}
	
	public static List<Beer> readBeers(File file, List<Brewery> breweries, List<Style> styles) throws FileNotFoundException, Exception{
		List<Beer> beersRead=new LinkedList<Beer>();
		List<BeerJSONSpecialClass> beersSpecial;	  
		beersSpecial = JSONExporter.readBeersSpecial( new FileInputStream(file));
		beersRead = Converter.recompose(beersSpecial, breweries, styles);
		return beersRead;
	}
	
	public static void saveStyles(List<Style> styles, File file) throws FileNotFoundException, Exception{
		JSONExporter.writeStyleSpecial(styles, new FileOutputStream(file));
	}
	
	public static void saveBreweries(List<Brewery> breweries, File file) throws FileNotFoundException, Exception{
		JSONExporter.writeBrewery(breweries, new FileOutputStream(file));
	}
	
	public static void saveBeers(List<Beer> beers, File file) throws FileNotFoundException, Exception{
		//List<BeerJSONSpecialClass> beersSpecial = Converter.toSpecialBeerList(beers);
		JSONExporter.writeBeerSpecial(beers, new FileOutputStream(file)); //already done by json
	}
	
	public static Object[] fromStyle(Style s){
		Object[] array=new Object[5];
		array[0]=s.getStyleMainName();
		array[1]=s.getStyleSubCategory();
		array[2]=s.getFermentation();
		array[3]=s.getStyleCountryOrigin();
		array[4]=s.getDescription();
		return array;
	}
	
	public static Object[][] fromStyles(List<Style> styles){
		Object[][] array=new Object[styles.size()][5];
		for(int i=0;i<styles.size();i++){
			Style s=styles.get(i);
			array[i]=fromStyle(s);
		}
		return array;
	}
	
	public static class Constants{
		public static final Object[] TABLE_HEADER_BEERS = {
				"Nome",
				"Birrificio",
				"Stile",
				"ABV %",
				"Voto",
				"Stelle",
				"Provata",
				"Luogo",
				"Prezzo",
				"Descrizione"
		};
		
		public static final Object[] TABLE_HEADER_BREWERY = {
				"Nome",
				"Nazione",
				"Città",
				"Descizione",
				"Web"
		};
		
		public static final Object[] TABLE_HEADER_STYLE = {
				"Stile",
				"Sottostile",
				"Fermentazione",
				"Paese d'origine",
				"Descrizione"
		};
	}

}
