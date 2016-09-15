package org.nbena.beersmanager.export;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSaveSpecialClass;
import org.nbena.beersmanager.json.coreclasses.JSONExporterCoreClasses;

public class JSONOutExporter extends OutExporter{
	
	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception{
//		JSONArray array=new JSONArray(breweries);
//		PrintStream ps=new PrintStream(out);
//		ps.print(array.toString());
		JSONExporterCoreClasses.writeBrewery(breweries, out);
	}
	
	public void writeStyle(List<Style> styles, OutputStream out) throws Exception{
//		JSONArray array=new JSONArray(styles);
//		PrintStream ps=new PrintStream(out);
//		ps.print(array.toString());
		JSONExporterCoreClasses.writeStyleSpecial(styles, out);
	}
	
	public  void writeBeerSpecialClass(List<BeerJSONSaveSpecialClass> beers, OutputStream out) throws Exception{
//		JSONArray array=new JSONArray(beers);
//		PrintStream ps=new PrintStream(out);
//		ps.print(array.toString());
	}
	
	public void writeBeer(List<Beer> beers, OutputStream out, boolean writeTotalPrice) throws Exception{
//		JSONArray array=new JSONArray();
//		for(Beer b: beers){
//			JSONObject obj = new JSONObject();
//			
//			
//		}
//		PrintStream ps=new PrintStream(out);
//		ps.print(array.toString());
		JSONExporterCoreClasses.writeBeerJSONExportSpecialClass(beers, out);
	}
	

	
	

}
