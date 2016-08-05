package org.nbena.beersmanager.export;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.json.JSONArray;
import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.BeerJSONSpecialClass;

public class JSONExporter extends Exporter{
	
	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception{
		JSONArray array=new JSONArray(breweries);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	
	public void writeStyle(List<Style> styles, OutputStream out) throws Exception{
		JSONArray array=new JSONArray(styles);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	
	public  void writeBeerSpecialClass(List<BeerJSONSpecialClass> beers, OutputStream out) throws Exception{
		JSONArray array=new JSONArray(beers);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}
	
	public void writeBeer(List<Beer> beers, OutputStream out) throws Exception{
		JSONArray array=new JSONArray(beers);
		PrintStream ps=new PrintStream(out);
		ps.print(array.toString());
	}

}
