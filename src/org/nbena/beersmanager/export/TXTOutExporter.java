package org.nbena.beersmanager.export;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSaveSpecialClass;

public class TXTOutExporter extends OutExporter {

	public TXTOutExporter() {
	}

	@Override
	public void writeBeer(List<Beer> beers, OutputStream out, boolean writeTotalPrice) throws Exception {
		Utils.printBeersTotal(beers, out);
	}

	@Override
	public void writeBeerSpecialClass(List<BeerJSONSaveSpecialClass> beers, OutputStream out) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStyle(List<Style> styles, OutputStream out) throws Exception {
		Utils.printStyles(styles, out);
	}

	@Override
	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws Exception {
		Utils.printBreweries(breweries, out);
	}
	
	public void writeTotal(double total, OutputStream out){
		PrintStream output = new PrintStream(out);
		output.println("Il totale: "+total);
	}

}
