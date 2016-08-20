package org.nbena.beersmanager.json.coreclasses;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nbena.beersmanager.coreclasses.*;





public class JSONExporterCoreClasses {
	
	private static final String JSON_BEER_STYLE_MAIN_NAME = "styleMainName";
	private static final String JSON_BEER_ALCOOL = "alcool";
	private static final String JSON_BEER_TRIED = "tried";
	private static final String JSON_BEER_STYLE_SUBCATEGORY = "styleSubcategory";
	private static final String JSON_BEER_NUMBER_STARS = "numberOfStars";
	private static final String JSON_BEER_COLOR = "color";
	private static final String JSON_BEER_PRICE = "price";
	private static final String JSON_BEER_NAME = "name";
	private static final String JSON_BEER_PLACE_TRIED = "placeTried";
	private static final String JSON_BEER_BREWERY_NAME = "breweryName";
	private static final String JSON_BEER_MARK = "mark";
	private static final String JSON_BEER_DESCRIPTION = "description";
	
	private static final String JSON_BREWERY_NAME = "name";
	private static final String JSON_BREWERY_TOWN = "town";
	private static final String JSON_BREWERY_COUNTRY = "country";
	private static final String JSON_BREWERY_DESCRIPTION = "description";
	private static final String JSON_BREWERY_WEBSITE = "website";
	private static final String JSON_BREWERY_TRAPPIST = "authenticTrappist";
	
	private static final String JSON_STYLE_MAIN_NAME = "styleMainName";
	private static final String JSON_STYLE_SUBCATEGORY = "styleSubCategory";
	private static final String JSON_STYLE_DESCRIPTION = "description";
	private static final String JSON_STYLE_COUNTRY_ORIIGIN = "styleCountryOrigin";
	private static final String JSON_STYLE_FERMENTATION = "fermentation";
	
	

	
	public static void writeBrewery(List<Brewery> breweries, OutputStream out) throws JSONException{
		JSONArray array=new JSONArray(breweries);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	

	
//	public static void writeStyle(List<Style> styles, OutputStream out) throws Exception{
//		/*
//		JSONArray array=new JSONArray();
//		array.put(styles);
//		PrintStream ps=new PrintStream(out);
//		ps.print(array.toString());
//		*/
//		throw new Exception("Invalid method");
//	}
	
	/*
	public  void writeBeerSpecialClass(List<Beer> beers, OutputStream out) throws Exception{
		JSONArray array=new JSONArray();
		array.put(beers);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	*/
	
	public static void writeBeer(List<Beer> beers, OutputStream out) throws JSONException{
		JSONArray array=new JSONArray(beers);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
		
		
	}
	
	public static void writeBeerSpecial(List<Beer> beers, OutputStream out)throws JSONException{
		List<BeerJSONSpecialClass> beerSpecial = Converter.toSpecialBeerList(beers);
		JSONArray array= new JSONArray(beerSpecial);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	
	
	public static void writeStyleSpecial(List<Style> styles, OutputStream out)throws JSONException{
		List<StyleJSONSpecialClass> styleSpecial = Converter.toSpecialStyleList(styles);
		JSONArray array= new JSONArray(styleSpecial);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	
	
	public static String writeBeers(List<Beer> beers){
		return new JSONArray(beers).toString();
	}
	
	public static String writeBeersSpecial(List<Beer> beers){
		List<BeerJSONSpecialClass> beerSpecial = Converter.toSpecialBeerList(beers);
		return new JSONArray(beerSpecial).toString();
	}
		
	public static String writeBreweries(List<Brewery> breweries){
		return new JSONArray(breweries).toString();
	}
	
	public static String writeStyles(List<Style> styles){
		return new JSONArray(styles).toString();
	}
	
	public static String writeStylesSpecial(List<Style> styles){
		List<StyleJSONSpecialClass> styleSpecial = Converter.toSpecialStyleList(styles);
		return new JSONArray(styleSpecial).toString();
	}
	
	public static String writeBeer(Beer b){
		return new JSONObject(b).toString();
	}
	
	public static String writeBrewery(Brewery b){
		return new JSONObject(b).toString();
	}
	
	public static String writeStyle(Style s){
		return new JSONObject(s).toString();
	}
	
	public static String writeStyleSpecial(Style s){
		return new JSONObject(Converter.toStyleSpecialClass(s)).toString();
	}
	
	
	public static BeerJSONSpecialClass toBeerSpecialClass(JSONObject obj){
		BeerJSONSpecialClass beer=new BeerJSONSpecialClass();
		beer.setAlcool(obj.getDouble(JSON_BEER_ALCOOL));
		beer.setBreweryName(obj.getString(JSON_BEER_BREWERY_NAME));
//		beer.setColor(obj.getString(JSON_BEER_COLOR));
		beer.setDescription(obj.getString(JSON_BEER_DESCRIPTION));
		beer.setMark(obj.getInt(JSON_BEER_MARK));
		beer.setName(obj.getString(JSON_BEER_NAME));
		beer.setNumberOfStars(obj.getInt(JSON_BEER_NUMBER_STARS));
		beer.setPlaceTried(obj.getString(JSON_BEER_PLACE_TRIED));
		beer.setPrice(obj.getDouble(JSON_BEER_PRICE));
		beer.setStyleMainName(obj.getString(JSON_BEER_STYLE_MAIN_NAME));
		beer.setStyleSubcategory(obj.getString(JSON_BEER_STYLE_SUBCATEGORY));
		beer.setTried(obj.getBoolean(JSON_BEER_TRIED));
		return beer;
	}
	
	public static StyleJSONSpecialClass toStyleSpecialClass(JSONObject obj){
		StyleJSONSpecialClass style=new StyleJSONSpecialClass();
		style.setStyleMainName(obj.getString(JSON_STYLE_MAIN_NAME));
		style.setStyleSubCategory(obj.getString(JSON_STYLE_SUBCATEGORY));
		style.setStyleCountryOrigin(obj.getString(JSON_STYLE_COUNTRY_ORIIGIN));
		style.setFermentation(obj.getString(JSON_STYLE_FERMENTATION));
		style.setDescription(obj.getString(JSON_STYLE_DESCRIPTION));
		return style;
	}
	
	public static Brewery toBrewery(JSONObject obj){
		Brewery brewery = new Brewery();
		brewery.setAuthenticTrappist(obj.getBoolean(JSON_BREWERY_TRAPPIST));
		brewery.setCountry(obj.getString(JSON_BREWERY_COUNTRY));
		brewery.setDescription(obj.getString(JSON_BREWERY_DESCRIPTION));
		brewery.setName(obj.getString(JSON_BREWERY_NAME));
		brewery.setTown(obj.getString(JSON_BREWERY_TOWN));
		brewery.setWebsite(obj.getString(JSON_BREWERY_WEBSITE));
		return brewery;
	}
	
	public static List<BeerJSONSpecialClass> readBeersSpecial(InputStream in)throws JSONException{
		JSONArray array =new JSONArray(new JSONTokener(in));
		LinkedList<BeerJSONSpecialClass> beers=new LinkedList<BeerJSONSpecialClass>();
		for (int i=0;i<array.length();i++){
			BeerJSONSpecialClass b=toBeerSpecialClass(array.getJSONObject(i));
			beers.add(b);
		}
		return beers;
	}
	
	
	public static Brewery readBrewery(String in){
		JSONObject json = new JSONObject(new JSONTokener(in));
		return toBrewery(json);
	}
	
	public static Style readStyle(String in){
		JSONObject json = new JSONObject(new JSONTokener(in));
		return Converter.toStyleNormalClass(toStyleSpecialClass(json));
	}
	
	public static List<StyleJSONSpecialClass> readStylesSpecial(InputStream in)throws JSONException{
		JSONArray array =new JSONArray(new JSONTokener(in));
		LinkedList<StyleJSONSpecialClass> styles=new LinkedList<StyleJSONSpecialClass>();
		for (int i=0;i<array.length();i++){
			StyleJSONSpecialClass b=toStyleSpecialClass(array.getJSONObject(i));
			styles.add(b);
		}
		return styles;
	}
	
	public static List<Brewery> readBreweries(InputStream in)throws JSONException{
		JSONArray array =new JSONArray(new JSONTokener(in));
		LinkedList<Brewery> breweries=new LinkedList<Brewery>();
		for (int i=0;i<array.length();i++){
			Brewery b=toBrewery(array.getJSONObject(i));
			breweries.add(b);
		}
		return breweries;
	}
	
	

}
