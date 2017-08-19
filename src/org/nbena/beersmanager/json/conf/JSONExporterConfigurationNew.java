package org.nbena.beersmanager.json.conf;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
//import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.conf.ConfigurationNew;
import org.nbena.beersmanager.query.QueryRunner;

public class JSONExporterConfigurationNew {

	private JSONObject json;
	private OutputStream out;
	private InputStream in;
	
	
	private static final String JSON_BEER_SORTING_ALGORITHM = "beerSorting";
	private static final String JSON_BREWERY_SORTING_ALGORITHM = "brewerySorting";
	private static final String JSON_STYLE_SORTING_ALGORITHM = "styleSorting";
//	private static final String JSON_BEER_FILTER_ALGORITHM = "beerFilter";
//	private static final String JSON_BREWERY_FILTER_ALGORITHM = "breweryFilter";
//	private static final String JSON_STYLE_FILTER_ALGORITHM = "styleFilter";
//	
//	private static final String JSON_BEER_FILTER_VALUE_TYPE = "beerFilterValueType";
//	private static final String JSON_BREWERY_FILTER_VALUE_TYPE = "breweryFilterValueType";
//	private static final String JSON_STYLE_FILTER_VALUE_TYPE = "styleFilterValueType";
//
//	
//	private static final String JSON_BEER_FILTER_VALUE = "beerFilterValue";
//	private static final String JSON_BREWERY_FILTER_VALUE = "breweryFilterValue";
//	private static final String JSON_STYLE_FILTER_VALUE = "styleFilterValue";
	private static final String JSON_DEFAULT_VIEW = "defaultView";
	
//	private static final String JSON_STYLE_FILTER_VALUE_TYPE_STYLE = "style";
//	private static final String JSON_STYLE_FILTER_VALUE_TYPE_STRING = "string";
//	private static final String JSON_STYLE_FILTER_VALUE_TYPE_FERMENTATION = "fermentation";
//	
//	private static final String JSON_BREWERY_FILTER_VALUE_TYPE_STRING = "string"; //even if it is the same as beer, keep them separate.
//	
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_DOUBLE = "double";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_INT = "int";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_BOOLEAN = "boolean";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_STYLE = "style";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_BREWERY = "brewery";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_STRING = "string";
//	private static final String JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION = "fermentation";
	
	
	public JSONExporterConfigurationNew(OutputStream out, InputStream in) {
		this.out = out;
		this.in = in;
	}
	
	public JSONExporterConfigurationNew(){}



	/**
	 * @param out the out to set
	 */
	public void setOut(OutputStream out) {
		this.out = out;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(InputStream in) {
		this.in = in;
	}
	
	public void writeConfiguration(ConfigurationNew c) throws JSONException{
		json=new JSONObject();
		json.put(JSON_BEER_SORTING_ALGORITHM, c.getBeerSortingAlgorithm().toString());
		json.put(JSON_BREWERY_SORTING_ALGORITHM, c.getBrewerySortingAlgorithm());
		json.put(JSON_STYLE_SORTING_ALGORITHM, c.getStyleSortingAlgorithm());		
		json.put(JSON_DEFAULT_VIEW, c.getDefaultView().toString());
		
		PrintStream ps = new PrintStream(out);
		ps.print(json.toString());
		
	}
	
	public ConfigurationNew readConfiguration(){
		ConfigurationNew c=new ConfigurationNew();
		json=new JSONObject(new JSONTokener(in));
		c.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.valueOf(json.getString(JSON_BEER_SORTING_ALGORITHM)));
		c.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.valueOf(json.getString(JSON_BREWERY_SORTING_ALGORITHM)));
		c.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.valueOf(json.getString(JSON_STYLE_SORTING_ALGORITHM)));		
		c.setDefaultView(ConfigurationNew.ShowDefault.valueOf(json.getString(JSON_DEFAULT_VIEW)));
		
		return c;
	}

}
