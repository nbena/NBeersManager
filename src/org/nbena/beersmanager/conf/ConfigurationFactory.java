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
package org.nbena.beersmanager.conf;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.nbena.beersmanager.exe.Utils;
//import org.nbena.beersmanager.json.conf.JSONExporterConfiguration;
import org.nbena.beersmanager.json.conf.JSONExporterConfigurationNew;
import org.nbena.beersmanager.query.QueryRunner;

public class ConfigurationFactory {
	
	
	public ConfigurationFactory(){
	}
	
	public static ConfigurationNew getDefaultConfiguration(){
		ConfigurationNew conf=new ConfigurationNew();
		conf = getDefaultView(conf);
		conf = getDefaultSortingConfiguration(conf);
//		conf = getDefaultFilteringConfiguration(conf);
		conf.setPaths(/*Utils.currentDirectory()*/);
		return conf;
	}
	
	public static ConfigurationNew getDefaultSortingConfiguration(ConfigurationNew conf){
		conf.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_DESCENDING);
		conf.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.AVERAGE_DESCENDING);
		conf.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_CATEGORY);
		return conf;
	}
	
//	public static Configuration getDefaultFilteringConfiguration(Configuration conf){
//		conf.setBeerFilterAlgorithm(BeerFilterAlgorithm.NONE);
//		conf.setBeerFilterValue("");
//		conf.setBreweryFilterAlgorithm(BreweryFilterAlgorithm.NONE);
//		conf.setBreweryFilterValue("");		
//		conf.setStyleFilterAlgorithm(StyleFilterAlgorithm.NONE);
//		conf.setStyleFilterValue("");
//		return conf;
//	}
	
	public static ConfigurationNew getDefaultView(ConfigurationNew conf){
		conf.setDefaultView(ConfigurationNew.ShowDefault.BEER);
		return conf;
	}
	
//	public static String setupXMLPaths(Configuration conf) {
//		String path=new File(".").getAbsolutePath();
//		path+=File.pathSeparator+"data"+File.pathSeparator;
//		conf.setBeerFilePath(path+"beers.xml");	
//		conf.setBreweryFilePath(path+"breweries.xml");
//		conf.setStyleFilePath(path+"styles.xml");
//		return path;
//	}
//	
//	public static Configuration getCurrentConfiguration(File file) throws JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(Configuration.class);
//		Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
//		Configuration conf=(Configuration) unMarshaller.unmarshal(file);	
//		setupXMLPaths(conf);
//		return conf;
//	}
//	
//	public static void writeConfiguration(Configuration conf, File file) throws JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(Configuration.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.marshal(conf, file);
//	}
	
	public static String getDefaultConfigurationPath(){
		return Utils.getConfigurationPath(/*Utils.getOSIndipendentFolder()*/);
	}
	
	public static ConfigurationNew setupPath(ConfigurationNew c){
		c.setPaths(/*Utils.currentDirectory()*/);
		return c;
	}
	
//	public static Configuration readConfiguration(String configurationPath) throws JSONException, FileNotFoundException{
//		JSONExporterConfiguration exporter=new JSONExporterConfiguration();
//		exporter.setIn(new FileInputStream(new File(configurationPath)));
//		return exporter.readConfiguration();
//	}
//	
////	public static Configuration readConfiguration() throws JSONException, FileNotFoundException{
////		return readConfiguration(getDefaultConfigurationPath());
////	}
//	
//	public static void writeConfiguration(Configuration c, String configurationPath) throws FileNotFoundException{
//		JSONExporterConfiguration exporter=new JSONExporterConfiguration();
//		exporter.setOut(new FileOutputStream(new File(configurationPath)));
//		exporter.writeConfiguration(c);
//	}
	
//	public static void writeConfiguration(Configuration c) throws FileNotFoundException{
//		writeConfiguration(c, getDefaultConfigurationPath());
//	}
	
	public static ConfigurationNew readConfiguration(/*String configurationPath*/) throws JSONException, FileNotFoundException{
		String configurationPath = Utils.getConfigurationPath();
		JSONExporterConfigurationNew exporter=new JSONExporterConfigurationNew();
		exporter.setIn(new FileInputStream(new File(configurationPath)));
		return exporter.readConfiguration();
	}
	
//	public static Configuration readConfiguration() throws JSONException, FileNotFoundException{
//		return readConfiguration(getDefaultConfigurationPath());
//	}
	
	public static void writeConfiguration(ConfigurationNew c/*, String configurationPath*/) throws FileNotFoundException{
		String configurationPath = Utils.getConfigurationPath();
		JSONExporterConfigurationNew exporter=new JSONExporterConfigurationNew();
		exporter.setOut(new FileOutputStream(new File(configurationPath)));
		exporter.writeConfiguration(c);
	}

}
