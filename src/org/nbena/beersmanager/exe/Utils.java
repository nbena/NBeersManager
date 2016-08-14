package org.nbena.beersmanager.exe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.ui.models.Model.ExportType;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSpecialClass;
import org.nbena.beersmanager.json.coreclasses.Converter;
import org.nbena.beersmanager.json.coreclasses.JSONExporter;
import org.nbena.beersmanager.query.BreweryAverage;
import org.nbena.beersmanager.query.QueryRunner;

public class Utils {
	
	//here things not strictly connected with business logic.

	public Utils() {
	}
	
	public static void printStyle(Style style, OutputStream output){
		PrintStream out=new PrintStream(output);
		out.println("-------");
		out.println(" "+style.getStyleSubCategory()+" "+style.getStyleMainName()+", "+style.getFermentation()+" fermentation");
		out.println(" "+style.getDescription()+"\n");
	}
	
	public static void printBeer(Beer beer, OutputStream output){
		PrintStream out=new PrintStream(output);
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
			out.println(" Tried: no");
		}
		out.println();
	}
	
	public static void printBrewery(Brewery brewery, OutputStream output){
		PrintStream out=new PrintStream(output);
		out.println("-------");
		out.println(" "+brewery.getName()+" - "+brewery.getTown()+" - "+brewery.getCountry()+" - "+brewery.getWebsite());
		out.println(" "+brewery.getDescription()+"\n");
	}
	
	public static void printBeers(List<Beer> beers, OutputStream output){
		/*
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
				out.println(" Tried: no"+"\n");
			}
		}
		out.println();
		*/
		for(Beer b: beers){
			printBeer(b, output);
		}
	}
	
	public static void printBreweries(List<Brewery> breweries, OutputStream output){
		/*
		PrintStream out=new PrintStream(output);
		out.println("-------------Breweries:--------------");
		for(Brewery brewery: breweries){
			out.println("-------");
			out.println(" "+brewery.getName()+" - "+brewery.getTown()+" - "+brewery.getCountry()+" - "+brewery.getWebsite());
			out.println(" "+brewery.getDescription()+"\n");
		}
		*/
		for(Brewery b: breweries){
			printBrewery(b, output);
		}
	}
	
	public static void printStyles(List<Style> styles, OutputStream output){
		/*
		PrintStream out=new PrintStream(output);
		out.println("-------------Style:--------------");
		for(Style style: styles){
			out.println(" "+style.getStyleSubCategory()+" "+style.getStyleMainName()+", "+style.getFermentation()+" fermentation");
			out.println(" "+style.getDescription()+"\n");
		}
		*/
		for(Style s: styles){
			printStyle(s, output);
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
	
	public static String jsonConfiguration(String directory){
		return directory.concat("config.json");
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
	
	public static Object[] fromStyleToObjectArray(Style s){
		Object[] array=new Object[5];
		array[0]=s.getStyleMainName();
		array[1]=s.getStyleSubCategory();
		array[2]=s.getFermentation();
		array[3]=s.getStyleCountryOrigin();
		array[4]=s.getDescription();
		return array;
	}
	
	public static Object[] fromBeerToObjectArray(Beer b){
//		Object[] array=new Object[10];
		Object[] array=new Object[9];
		array[0]=b.getName();
		array[1]=b.getBrewery().getName();
		array[2]=b.getStyle().getStyleSubCategory()+" "+b.getStyle().getStyleMainName();
		array[3]=b.getAlcool();
		array[4]=b.getMark();
		array[5]=b.getNumberOfStars();
		array[6]=Utils.getBooleanItalian(b.isTried());
		array[7]=b.getPlaceTried();
		array[8]=b.getPrice();
//		array[9]=b.getDescription();
		return array;
	}
	
	public static Object[] fromBreweryToObjectArray(Brewery b){
		Object [] array=new Object[5];
		array[0]=b.getName();
		array[1]=b.getCountry();
		array[2]=b.getTown();
		array[3]=b.getDescription();
		array[4]=b.getWebsite();
		return array;
	}
	
	public static Object[] fromBreweryAverageToObjectArray(BreweryAverage b){
		Object [] array=new Object[6];
		array[0]=b.getName();
		array[1]=b.getCountry();
		array[2]=b.getTown();
		array[3]=b.getDescription();
		array[4]=b.getWebsite();
		array[5]=b.getAverage();
		return array;
	}
	
	public static Object[][] fromStylesToObjectMatrix(List<Style> styles){
		Object[][] array=new Object[styles.size()][5];
		for(int i=0;i<styles.size();i++){
			Style s=styles.get(i);
			array[i]=fromStyleToObjectArray(s);
		}
		return array;
	}
	
	public static Object[][] fromBeersToObjectMatrix(List<Beer> beers){
//		Object [][] array=new Object[beers.size()][10];
		Object [][] array=new Object[beers.size()][9];
		for(int i=0;i<beers.size();i++){
			Beer b=beers.get(i);
			array[i]=fromBeerToObjectArray(b);
		}
		return array;
	}
	
	public static Object[][] fromBreweriesToObjectMatrix(List<Brewery> breweries){
		Object [][] array=new Object[breweries.size()][5];
		for(int i=0;i<breweries.size();i++){
			Brewery b=breweries.get(i);
			array[i]=fromBreweryToObjectArray(b);
		}
		return array;
	}
	
	
	public static Object[][] fromBreweriesAverageToObjectMatrix(List<BreweryAverage> breweries){
		Object [][] array=new Object[breweries.size()][6];
		for(int i=0;i<breweries.size();i++){
			BreweryAverage b=breweries.get(i);
			array[i]=fromBreweryAverageToObjectArray(b);
		}
		return array;
	}
	
	public static Style getNakedStyle(Style s){
		Style naked=new Style();
		naked.setStyleMainName(s.getStyleMainName());
		return naked;
	}
	
	public static String getStyleString(Style s){
		return s.getStyleSubCategory()+" "+s.getStyleMainName();
	}
	
	public static String getBreweryString(Brewery b){
		return b.getName()+", "+b.getTown()+" ("+b.getCountry()+")";
	}
	
	public static List<String> getBreweriesString(List<Brewery> breweries){
		List<String> strings=new LinkedList<String>();
		for(Brewery b: breweries){
			strings.add(getBreweryString(b));
		}
		return strings;
	}
	
	public static List<String> getStylesString(List<Style> styles){
		List<String> strings=new LinkedList<String>();
		for(Style s: styles){
			strings.add(getStyleString(s));
		}
		return strings;
	}
	
	public static String getBooleanItalian(boolean b){
		return b==true ? "S�" : "No";
	}
	
	public static List<String> getMainStyleString(List<Style> styles){
		List<String> strings=new LinkedList<String>();
		for(Style s: styles){
			strings.add(s.getStyleMainName());
		}
		return strings;
	}
	
	public static String getFermentationString(Fermentation f){
		String s=null;
		if(f==Fermentation.HIGH)
			s="Alta";
		else if(f==Fermentation.LOW)
			s="Bassa";
		else
			s="Spontanea";
		return s;
	}
	
	public static BreweryAverage fromBreweryToBreweryAverage(Brewery b, List<Beer> itsBeers){
		BreweryAverage av=new BreweryAverage();
		av.setAuthenticTrappist(b.isAuthenticTrappist());
		av.setCountry(b.getCountry());
		av.setDescription(b.getDescription());
		av.setName(b.getName());
		av.setTown(b.getTown());
		av.setWebsite(b.getWebsite());
		av.setAverage(itsBeers);
		return av;
	}
	
	public static BreweryAverage fromBreweryToBreweryAverage(Brewery b){
		BreweryAverage av=new BreweryAverage();
		av.setAuthenticTrappist(b.isAuthenticTrappist());
		av.setCountry(b.getCountry());
		av.setDescription(b.getDescription());
		av.setName(b.getName());
		av.setTown(b.getTown());
		av.setWebsite(b.getWebsite());
		av.setAverage(0.0);
		return av;
	}
	
	public static List<BreweryAverage> fromBreweriesToBreweriesAverage(List<Brewery> breweries, List<Beer> beers){
		List<BreweryAverage> breweriesAv=new LinkedList<BreweryAverage>();
		for(Brewery b: breweries){
			List<Beer> itsBeers=QueryRunner.beersFilteredByBrewery(beers, b);
			BreweryAverage av=fromBreweryToBreweryAverage(b, itsBeers);
			breweriesAv.add(av);
		}
		return breweriesAv;
	}
	
	public static List<BreweryAverage> fromBreweriesToBreweriesAverage(List<Brewery> breweries){
		List<BreweryAverage> breweriesAv=new LinkedList<BreweryAverage>();
		for(Brewery b: breweries){
			BreweryAverage av=fromBreweryToBreweryAverage(b);
			breweriesAv.add(av);
		}
		return breweriesAv;
	}
	
	public static Brewery fromBreweryAverageToBrewery(BreweryAverage brewery){
		Brewery b=new Brewery();
		b.setAuthenticTrappist(brewery.isAuthenticTrappist());
		b.setCountry(brewery.getCountry());
		b.setDescription(brewery.getDescription());
		b.setName(brewery.getName());
		b.setTown(brewery.getTown());
		b.setWebsite(brewery.getWebsite());
		return b;
	}
	
	public static List<Brewery> fromBreweriesAverageToBrewery(List<BreweryAverage> breweries){
		List<Brewery> normals=new LinkedList<Brewery>();
		for(BreweryAverage b: breweries){
			Brewery normal=fromBreweryAverageToBrewery(b);
			normals.add(normal);
		}
		return normals;
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
				"Prezzo"/*,*/
				/*"Descrizione"*/
		};
		
		public static final Object[] TABLE_HEADER_BREWERY = {
				"Nome",
				"Nazione",
				"Citt�",
				"Descizione",
				"Web"
		};
		
		public static final Object[] TABLE_HEADER_BREWERY_AVERAGE = {
				"Nome",
				"Nazione",
				"Citt�",
				"Descizione",
				"Web",
				"Media"
		};
		
		public static final Object[] TABLE_HEADER_STYLE = {
				"Stile",
				"Sottostile",
				"Fermentazione",
				"Paese d'origine",
				"Descrizione"
		};
		
		public static final String[] EXCEL_OLD ={
				"Microsoft excel 2003-2007 (xls)",
				"xls"
		};
		
		public static final String[] EXCEL_NEW ={
				"Microsoft excel 2010-2016 (xlsx)",
				"xlss"
		};
		
		public static final String[] JSON ={
				"JavaScript Object Notation (json)",
				"json"
		};
		
		public static final String[] PDF ={
				"Portable Document Format (pdf)",
				"pdf"
		};
		
		public static final String[] TXT ={
				"Text file (txt)",
				"txt"
		};
	}
	
	public static String getFileExtension(File f){
		String filePath=f.getAbsolutePath();
		return filePath.substring(filePath.lastIndexOf("."), filePath.length()).replace(".", "").toLowerCase();
	}
	
	public static ExportType getExportType(File f){
		ExportType export;
		String ext=getFileExtension(f);
		if(ext.equals("xls")){
			export=ExportType.EXCEL_OLD;
		}
		else if(ext.equals("xlsx")){
			export=ExportType.EXCEL_NEW;
		}
		else if(ext.equals("json")){
			export=ExportType.JSON;
		}
		else if(ext.equals("pdf")){
			export=ExportType.PDF;
		}else{
			export=ExportType.TXT;
		}
		return export;
	}
	
	public static FileNameExtensionFilter[] getAllFileFilters(){
		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[5];
		filters[0]=new FileNameExtensionFilter(Constants.EXCEL_NEW[0], Constants.EXCEL_NEW[1]);
		filters[1]=new FileNameExtensionFilter(Constants.EXCEL_OLD[0], Constants.EXCEL_OLD[1]);
		filters[2]=new FileNameExtensionFilter(Constants.JSON[0], Constants.JSON[1]);
		filters[3]=new FileNameExtensionFilter(Constants.PDF[0], Constants.PDF[1]);
		filters[4]=new FileNameExtensionFilter(Constants.TXT[0], Constants.TXT[1]);
		return filters;
	}
	
	public static boolean checkIfExtensionIsPresent(File f){
		boolean returned=true;
		try{
			getFileExtension(f);
		}catch(StringIndexOutOfBoundsException e){
			returned=false;
		}
		return returned;
	}
	
	public static String getJFileChooserSelectedExtension(JFileChooser chooser){
		FileNameExtensionFilter filter=(FileNameExtensionFilter)chooser.getFileFilter();
		return filter.getExtensions()[0];
	}
	
	public static Function<List<Beer>, List<Beer>> getBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm algorithm){
		Function<List<Beer>, List<Beer>> function=null;
		switch(algorithm){
		case COUNTRY_OF_BREWERY_STYLE:
			function=QueryRunner::beersSortedByCountryOfBreweryStyle;
			break;
		case FERMENTATIOM_STYLE_COUNTRY_OF_BREWERY:
			function=QueryRunner::beersSortedByFermentationStyleCountryOfBrewery;
			break;
		case FERMENTATION_COUNTRY_OF_STYLE_BREWERY:
			function=QueryRunner::beersSortedByFermentationCountryOfStyleBrewery;
			break;
		case MARK_STAR_ASCENDING:
			function=QueryRunner::beersSortedByMarkStarAscending;
			break;
		case STAR_MARK_ASCENDING:
			function=QueryRunner::beersSortedByStarMarkAscending;
			break;
		case MARK_STAR_DESCENDING:
			function=QueryRunner::beersSortedByMarkStarDescending;
			break;
		case STAR_MARK_DESCENDING:
			function=QueryRunner::beersSortedByStarMarkDescending;
			break;
		}
		return function;
	}
	
	
	public static Function<List<BreweryAverage>, List<BreweryAverage>> getBreweriesSortingAlgorithm(QueryRunner.BrewerySortingAlgorithm algorithm){
		Function<List<BreweryAverage>, List<BreweryAverage>> function=null;
		switch(algorithm){
		case AVERAGE_ASCENDING:
			function=QueryRunner::breweriesSortedByAverageAscending;
			break;
		case COUNTRY_AVERAGE_ASCENDING:
			function=QueryRunner::breweriesSortedByCountryThenAverageAscending;
			break;
		case AVERAGE_DESCENDING:
			function=QueryRunner::breweriesSortedByAverageDescending;
			break;
		case COUNTRY_AVERAGE_DESCENDING:
			function=QueryRunner::breweriesSortedByCountryThenAverageDescending;
			break;
		case COUNTRY_NAME:
			function=QueryRunner::breweriesSortedByCountryThenNameWithAverage;
			break;
		case NAME:
			function=QueryRunner::breweriesSortedByNameWithAverage;
			break;
		}
		return function;
	}
	
	public static Function<List<Style>, List<Style>> getStylesSortingAlgorithm(QueryRunner.StyleSortingAlgorithm algorithm){
		Function<List<Style>, List<Style>> function=null;
		switch(algorithm){
		case COUNTRY_FERMENTATION:
			function=QueryRunner::styleSortedByCountryThenFermentationy;
			break;
		case FERMENTATION_COUNTRY:
			function=QueryRunner::styleSortedByFermentationThenCountry;
			break;
		}
		return function;
	}
	

}
