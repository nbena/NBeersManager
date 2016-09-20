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
package org.nbena.beersmanager.export;

import java.io.OutputStream;

import java.util.List;

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
