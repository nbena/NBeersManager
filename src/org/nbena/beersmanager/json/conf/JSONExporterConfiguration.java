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
package org.nbena.beersmanager.json.conf;

import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintStream;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nbena.beersmanager.conf.Configuration;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.coreclasses.Converter;
import org.nbena.beersmanager.query.QueryRunner;

public class JSONExporterConfiguration {
	
	private JSONObject json;
	private OutputStream out;
	private InputStream in;
	
	
	private static final String JSON_BEER_SORTING_ALGORITHM = "beerSorting";
	private static final String JSON_BREWERY_SORTING_ALGORITHM = "brewerySorting";
	private static final String JSON_STYLE_SORTING_ALGORITHM = "styleSorting";
	private static final String JSON_BEER_FILTER_ALGORITHM = "beerFilter";
	private static final String JSON_BREWERY_FILTER_ALGORITHM = "breweryFilter";
	private static final String JSON_STYLE_FILTER_ALGORITHM = "styleFilter";
	
	private static final String JSON_BEER_FILTER_VALUE_TYPE = "beerFilterValueType";
	private static final String JSON_BREWERY_FILTER_VALUE_TYPE = "breweryFilterValueType";
	private static final String JSON_STYLE_FILTER_VALUE_TYPE = "styleFilterValueType";

	
	private static final String JSON_BEER_FILTER_VALUE = "beerFilterValue";
	private static final String JSON_BREWERY_FILTER_VALUE = "breweryFilterValue";
	private static final String JSON_STYLE_FILTER_VALUE = "styleFilterValue";
	private static final String JSON_DEFAULT_VIEW = "defaultView";
	
	private static final String JSON_STYLE_FILTER_VALUE_TYPE_STYLE = "style";
	private static final String JSON_STYLE_FILTER_VALUE_TYPE_STRING = "string";
	private static final String JSON_STYLE_FILTER_VALUE_TYPE_FERMENTATION = "fermentation";
	
	private static final String JSON_BREWERY_FILTER_VALUE_TYPE_STRING = "string"; //even if it is the same as beer, keep them separate.
	
	private static final String JSON_BEER_FILTER_VALUE_TYPE_DOUBLE = "double";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_INT = "int";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_BOOLEAN = "boolean";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_STYLE = "style";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_BREWERY = "brewery";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_STRING = "string";
	private static final String JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION = "fermentation";
	
	 static class WriteObject{
		
		String type;
		Object value;
		
		
		public WriteObject(String type, Object value) {
			this.type = type;
			this.value = value;
		}
		
		public WriteObject() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		/**
		 * @return the value
		 */
		public Object getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(Object value) {
			this.value = value;
		}
		
		
		
	}
	
	
	
//	private static String[] getBeerFilterValueAndType(Object o){
//		String[] value = new String[2];
//		value[0]="";
//		value[1]="";
//		if(o instanceof Double){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_DOUBLE;
//			value[1] = Double.toString((Double)o);
//		}
//		else if(o instanceof Integer){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_INT;
//			value[1] = Integer.toString((Integer)o);
//		}
//		else if(o instanceof Boolean){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_BOOLEAN;
//			value[1] = Boolean.toString((Boolean)o);
//		}
//		else if(o instanceof Style){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_STYLE;
////			value[1] = JSONExporterCoreClasses.writeStyleSpecial((Style)o);
//			value[1] = Utils.getStyleStringForExport((Style)o);
//		}
//		else if(o instanceof Brewery){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_BREWERY;
////			value[1] = JSONExporterCoreClasses.writeBrewery((Brewery)o);
//			value[1] = Utils.getBreweryStringForExport((Brewery)o);
//		}
//		else if(o instanceof String){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_STRING;
//			value[1] = (String)o;
//		}
//		else if(o instanceof Fermentation){
//			value[0] = JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION;
//			value[1] = Fermentation.toFirstUpperCase((Fermentation)o).toString();
//		}
//		return value;
//	}
	
	
	private static WriteObject getBeerFilterValueAndTypeObject(Object o){
		WriteObject obj = new WriteObject();
		if(o instanceof Double){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_DOUBLE);
			obj.setValue((Double)o);
		}
		else if(o instanceof Integer){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_INT);
			obj.setValue((Integer)o);
		}
		else if(o instanceof Boolean){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_BOOLEAN);
			obj.setValue((Boolean)o);
		}
		else if(o instanceof Style){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_STYLE);
//			obj.setValue(Converter.toStyleSpecialClass((Style)o));
			obj.setValue(new JSONObject(Converter.toStyleSpecialClass((Style)o)));
		}
		else if(o instanceof Brewery){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_BREWERY);
//			obj.setValue((Brewery)o);
			obj.setValue(new JSONObject((Brewery)o));
		}
		else if(o instanceof String){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_STRING);
			obj.setValue((String)o);
		}
		else if(o instanceof Fermentation){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION);
			obj.setValue(((Fermentation)o).toString());
		}
		return obj;
	}
	

	
//	private static String[] getBreweryFilterValueAndTypeType(Object o){
//		String[] value = new String[2];
//		value[0]="";
//		value[1]="";
//		if(o instanceof String){
//			value[0] = JSON_BREWERY_FILTER_VALUE_TYPE_STRING;
//			value[1] = (String)o;
//		}
//		return value;
//	}
	
	private static WriteObject getBreweryFilterValueAndTypeTypeObject(Object o){
		WriteObject obj = new WriteObject();
		if(o instanceof String){
			obj.setType(JSON_BREWERY_FILTER_VALUE_TYPE_STRING);
			obj.setValue((String)o);
		}
		return obj;
	}
	
//	private static String[] getStyleFilterValueAndType(Object o){
//		System.out.println(o.getClass());
//		String[] value = new String[2];
//		value[0]="";
//		value[1]="";
//		if(o instanceof Style){
//			value[0] = JSON_STYLE_FILTER_VALUE_TYPE_STYLE;
//			value[1] = Utils.getStyleStringForExport((Style)o);
//		}
//		else if(o instanceof String){
//			value[0] = JSON_STYLE_FILTER_VALUE_TYPE_STRING;
//			value[1] = (String)o;
//		}
//		else if(o instanceof Fermentation){
//			value[0] = JSON_STYLE_FILTER_VALUE_TYPE_FERMENTATION;
//			value[1] = ((Fermentation)o).toString();
//		}
//		return value;
//	}
	
	
	private static WriteObject getStyleFilterValueAndTypeObject(Object o){
		WriteObject obj = new WriteObject();
		if(o instanceof Style){
			obj.setType(JSON_STYLE_FILTER_VALUE_TYPE_STYLE);
//			String value = JSONObject.valueToString(Converter.toStyleSpecialClass((Style)o)); !!!!!!
//			obj.setValue(Converter.toStyleSpecialClass((Style)o));
//			obj.setValue(value);
			obj.setValue(new JSONObject(Converter.toStyleSpecialClass((Style)o)));
		}
		else if(o instanceof String){
			obj.setType(JSON_STYLE_FILTER_VALUE_TYPE_STRING);
			obj.setValue((String)o);
		}
		else if(o instanceof Fermentation){
			obj.setType(JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION);
			obj.setValue(((Fermentation)o).toString());
		}
		return obj;
	}
	
	private Configuration readBeerFilter(Configuration c){
		c.setBeerFilterAlgorithm(QueryRunner.BeerFilterAlgorithm.valueOf(json.getString(JSON_BEER_FILTER_ALGORITHM)));
		String value = json.getString(JSON_BEER_FILTER_VALUE_TYPE);
		
//		System.out.println("Beer filter type: "+value);
		
		if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_DOUBLE)){
			c.setBeerFilterValue(json.getDouble(JSON_BEER_FILTER_VALUE));
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_INT)){
			c.setBeerFilterValue(json.getInt(JSON_BEER_FILTER_VALUE));
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_BOOLEAN)){
			c.setBeerFilterValue(json.getBoolean(JSON_BEER_FILTER_VALUE));
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_STYLE)){
//			c.setBeerFilterValue(JSONExporterCoreClasses.readStyle(json.getJSONObject(JSON_BEER_FILTER_VALUE).toString()));	
//			c.setBeerFilterValue(Utils.getStyleFromStringExport(json.getString(JSON_BEER_FILTER_VALUE)));
			
//			String objString = json.getJSONObject(JSON_BEER_FILTER_VALUE).toString();
//			c.setBeerFilterValue(JSONExporterCoreClasses.readStyle(objString));
			
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_BREWERY)){
//			c.setBeerFilterValue(JSONExporterCoreClasses.readBrewery(json.getJSONObject(JSON_BEER_FILTER_VALUE).toString()));
//			c.setBeerFilterValue(Utils.getBreweryFromStringExport(json.getString(JSON_BEER_FILTER_VALUE)));
			
//			String objString = json.getJSONObject(JSON_BEER_FILTER_VALUE).toString();
//			c.setBeerFilterValue(JSONExporterCoreClasses.readBrewery(objString));	
			
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_STRING)){
			c.setBeerFilterValue(json.getString(JSON_BEER_FILTER_VALUE));
		}else if(value.equals(JSON_BEER_FILTER_VALUE_TYPE_FERMENTATION)){
			c.setBeerFilterValue(Fermentation.valueOf(JSON_BEER_FILTER_VALUE));
		}
		else{
			c.setBeerFilterValue(null);
		}
		return c;
	}
	
	private Configuration readBreweryFilter(Configuration c){
		c.setBreweryFilterAlgorithm(QueryRunner.BreweryFilterAlgorithm.valueOf(json.getString(JSON_BREWERY_FILTER_ALGORITHM)));
		String value = json.getString(JSON_BREWERY_FILTER_VALUE_TYPE);
		
//		System.out.println("Brewery filter type: "+value);
		
		if (value.equals(JSON_BREWERY_FILTER_VALUE_TYPE_STRING)){
			c.setBreweryFilterValue(json.get(JSON_BREWERY_FILTER_VALUE));
		}
		else{
			c.setBreweryFilterValue(null);
		}
		return c;
	}
	

	
	private Configuration readStyleFilter(Configuration c){
		c.setStyleFilterAlgorithm(QueryRunner.StyleFilterAlgorithm.valueOf(json.getString(JSON_STYLE_FILTER_ALGORITHM)));
		String value = json.getString(JSON_STYLE_FILTER_VALUE_TYPE);
		
		
		if(value.equals(JSON_STYLE_FILTER_VALUE_TYPE_STYLE)){
//			String valueR = json.getString(JSON_STYLE_FILTER_VALUE);
//			Object o = JSONObject.stringToValue(valueR);
//			c.setStyleFilterValue(Converter.toStyleNormalClass((StyleJSONSpecialClass)o));
//			c.setStyleFilterValue(JSONExporterCoreClasses.readStyle(json.getJSONObject(JSON_STYLE_FILTER_VALUE).toString()));	
			
			
//			c.setStyleFilterValue(Utils.getStyleFromStringExport(json.getString(JSON_STYLE_FILTER_VALUE)));
			
//			String objString = json.getJSONObject(JSON_STYLE_FILTER_VALUE).toString();
//			c.setStyleFilterValue(JSONExporterCoreClasses.readStyle(objString));
			
			
		}else if(value.equals(JSON_STYLE_FILTER_VALUE_TYPE_STRING)){
			c.setStyleFilterValue(json.get(JSON_STYLE_FILTER_VALUE));
		}else if(value.equals(JSON_STYLE_FILTER_VALUE_TYPE_FERMENTATION)){
			c.setStyleFilterValue(Fermentation.valueOf(JSON_STYLE_FILTER_VALUE));
		}else{
			c.setStyleFilterValue(null);
		}
		return c;
	}
	
	
	public JSONExporterConfiguration(OutputStream out, InputStream in) {
//		json=new JSONObject();
		this.out = out;
		this.in = in;
	}
	
	public JSONExporterConfiguration(){}



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
//		json.put(JSON_BEER_FILTER_ALGORITHM, c.getBeerFilterAlgorithm().toString());
//		json.put(JSON_BEER_FILTER_VALUE, c.getBeerFilterValue());
		json = writeBeerFilter(json, c);
		json.put(JSON_BEER_SORTING_ALGORITHM, c.getBeerSortingAlgorithm().toString());
		
//		json.put(JSON_BREWERY_FILTER_ALGORITHM, c.getBreweryFilterAlgorithm());
//		json.put(JSON_BREWERY_FILTER_VALUE, c.getBreweryFilterValue());
		json = writeBreweryFilter(json, c);
		json.put(JSON_BREWERY_SORTING_ALGORITHM, c.getBrewerySortingAlgorithm());
		
//		json.put(JSON_STYLE_FILTER_ALGORITHM, c.getStyleFilterAlgorithm());
//		json.put(JSON_STYLE_FILTER_VALUE, c.getStyleFilterValue());
		json = writeStyleFilter(json, c);
		json.put(JSON_STYLE_SORTING_ALGORITHM, c.getStyleSortingAlgorithm());
		
		json.put(JSON_DEFAULT_VIEW, c.getDefaultView().toString());
		
		PrintStream ps = new PrintStream(out);
		ps.print(json.toString());
		
	}
	
	private JSONObject writeBeerFilter(JSONObject json, Configuration c){
//		String[] values = getBeerFilterValueAndType(c.getBeerFilterValue());
//		
//		json.put(JSON_BEER_FILTER_ALGORITHM, c.getBeerFilterAlgorithm().toString());
//		json.put(JSON_BEER_FILTER_VALUE_TYPE, values[0]);
//		json.put(JSON_BEER_FILTER_VALUE, values[1]);
		
////		json.put(JSON_BEER_FILTER_VALUE, JSONExporterCoreClasses.readStyle(values[1]));  //Ugly but necessary, then I will create a class
		
		WriteObject obj = getBeerFilterValueAndTypeObject(c.getBeerFilterValue());
		json.put(JSON_BEER_FILTER_ALGORITHM, c.getBeerFilterAlgorithm().toString());
		json.put(JSON_BEER_FILTER_VALUE_TYPE, obj.getType());
		json.put(JSON_BEER_FILTER_VALUE, obj.getValue());
		
	
		
		return json;
	}
	
	private JSONObject writeBreweryFilter(JSONObject json, Configuration c){
//		String[] values = getBreweryFilterValueAndTypeType(c.getBreweryFilterValue());
//		
//		json.put(JSON_BREWERY_FILTER_ALGORITHM, c.getBreweryFilterAlgorithm().toString());
//		json.put(JSON_BREWERY_FILTER_VALUE_TYPE, values[0]);
//		json.put(JSON_BREWERY_FILTER_VALUE, values[1]);
		
		WriteObject obj = getBreweryFilterValueAndTypeTypeObject(c.getBreweryFilterValue());
		json.put(JSON_BREWERY_FILTER_ALGORITHM, c.getBreweryFilterAlgorithm().toString());
		json.put(JSON_BREWERY_FILTER_VALUE_TYPE, obj.getType());
		json.put(JSON_BREWERY_FILTER_VALUE, obj.getValue());
		
		return json;
	}
	
	private JSONObject writeStyleFilter(JSONObject json, Configuration c){
//		String[] values = getStyleFilterValueAndType(c.getStyleFilterValue());
//		
//		json.put(JSON_STYLE_FILTER_ALGORITHM, c.getStyleFilterAlgorithm().toString());
//		json.put(JSON_STYLE_FILTER_VALUE_TYPE, values[0]);
//		json.put(JSON_STYLE_FILTER_VALUE, values[1]);
		
		WriteObject obj = getStyleFilterValueAndTypeObject(c.getStyleFilterValue());
		
		json.put(JSON_STYLE_FILTER_ALGORITHM, c.getStyleFilterAlgorithm().toString());
		json.put(JSON_STYLE_FILTER_VALUE_TYPE, obj.getType());
		json.put(JSON_STYLE_FILTER_VALUE, obj.getValue());
		
		return json;
	}
	
	public Configuration readConfiguration(){
		Configuration c=new Configuration();
		json=new JSONObject(new JSONTokener(in));
//		c.setBeerFilterAlgorithm(QueryRunner.BeerFilterAlgorithm.valueOf(json.getString(JSON_BEER_FILTER_ALGORITHM)));
//		c.setBeerFilterValue(json.getString(JSON_BEER_FILTER_VALUE));
		c = readBeerFilter(c);
		c.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.valueOf(json.getString(JSON_BEER_SORTING_ALGORITHM)));
		
//		c.setBreweryFilterAlgorithm(QueryRunner.BreweryFilterAlgorithm.valueOf(json.getString(JSON_BREWERY_FILTER_ALGORITHM)));
//		c.setBreweryFilterValue(json.getString(JSON_BREWERY_FILTER_VALUE));
		c = readBreweryFilter(c);
		c.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.valueOf(json.getString(JSON_BREWERY_SORTING_ALGORITHM)));
		
//		c.setStyleFilterAlgorithm(QueryRunner.StyleFilterAlgorithm.valueOf(json.getString(JSON_STYLE_FILTER_ALGORITHM)));
//		c.setStyleFilterValue(json.getString(JSON_STYLE_FILTER_VALUE));
		c = readStyleFilter(c);
		c.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.valueOf(json.getString(JSON_STYLE_SORTING_ALGORITHM)));
		
		c.setDefaultView(Configuration.ShowDefault.valueOf(json.getString(JSON_DEFAULT_VIEW)));
		
		return c;
	}
	

}
