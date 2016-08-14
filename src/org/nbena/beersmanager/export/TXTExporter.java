package org.nbena.beersmanager.export;

import java.io.OutputStream;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSpecialClass;

public class TXTExporter extends Exporter {

	public TXTExporter() {
	}

	@Override
	public void writeBeer(List<Beer> beers, OutputStream out) throws Exception {
		Utils.printBeers(beers, out);
	}

	@Override
	public void writeBeerSpecialClass(List<BeerJSONSpecialClass> beers, OutputStream out) throws Exception {
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

}
