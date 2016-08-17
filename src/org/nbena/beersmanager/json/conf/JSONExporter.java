package org.nbena.beersmanager.json.conf;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.query.QueryRunner;

public class JSONExporter {
	
	private JSONObject json;
	private OutputStream out;
	private InputStream in;
	
	
	private static final String JSON_BEER_SORTING_ALGORITHM = "beerSorting";
	private static final String JSON_BREWERY_SORTING_ALGORITHM = "brewerySorting";
	private static final String JSON_STYLE_SORTING_ALGORITHM = "styleSorting";
	private static final String JSON_BEER_FILTER_ALGORITHM = "beerFilter";
	private static final String JSON_BREWERY_FILTER_ALGORITHM = "breweryFilter";
	private static final String JSON_STYLE_FILTER_ALGORITHM = "styleFilter";
	private static final String JSON_BEER_FILTER_VALUE = "beerFilterValue";
	private static final String JSON_BREWERY_FILTER_VALUE = "breweryFilterValue";
	private static final String JSON_STYLE_FILTER_VALUE = "styleFilterValue";
	
	
	public JSONExporter(OutputStream out, InputStream in) {
//		json=new JSONObject();
		this.out = out;
		this.in = in;
	}
	
	public JSONExporter(){}



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
	
	
	
	
	public void writeConfiguration(Configuration c) throws JSONException{
		json=new JSONObject();
		json.put(JSON_BEER_FILTER_ALGORITHM, c.getBeerFilterAlgorithm().toString());
		json.put(JSON_BEER_FILTER_VALUE, c.getBeerFilterValue());
		json.put(JSON_BEER_SORTING_ALGORITHM, c.getBeerSortingAlgorithm().toString());
		
		json.put(JSON_BREWERY_FILTER_ALGORITHM, c.getBreweryFilterAlgorithm());
		json.put(JSON_BREWERY_FILTER_VALUE, c.getBreweryFilterValue());
		json.put(JSON_BREWERY_SORTING_ALGORITHM, c.getBrewerySortingAlgorithm());
		
		json.put(JSON_STYLE_FILTER_ALGORITHM, c.getStyleFilterAlgorithm());
		json.put(JSON_STYLE_FILTER_VALUE, c.getStyleFilterValue());
		json.put(JSON_STYLE_SORTING_ALGORITHM, c.getStyleSortingAlgorithm());
		
		PrintStream ps = new PrintStream(out);
		ps.print(json.toString());
		
	}
	
	public Configuration readConfiguration(){
		Configuration c=new Configuration();
		json=new JSONObject(new JSONTokener(in));
		c.setBeerFilterAlgorithm(QueryRunner.BeerFilterAlgorithm.valueOf(json.getString(JSON_BEER_FILTER_ALGORITHM)));
		c.setBeerFilterValue(json.getString(JSON_BEER_FILTER_VALUE));
		c.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.valueOf(json.getString(JSON_BEER_SORTING_ALGORITHM)));
		
		c.setBreweryFilterAlgorithm(QueryRunner.BreweryFilterAlgorithm.valueOf(json.getString(JSON_BREWERY_FILTER_ALGORITHM)));
		c.setBreweryFilterValue(json.getString(JSON_BREWERY_FILTER_VALUE));
		c.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.valueOf(json.getString(JSON_BREWERY_SORTING_ALGORITHM)));
		
		c.setStyleFilterAlgorithm(QueryRunner.StyleFilterAlgorithm.valueOf(json.getString(JSON_STYLE_FILTER_ALGORITHM)));
		c.setStyleFilterValue(json.getString(JSON_STYLE_FILTER_VALUE));
		c.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.valueOf(json.getString(JSON_STYLE_SORTING_ALGORITHM)));
		return c;
	}
	

}
