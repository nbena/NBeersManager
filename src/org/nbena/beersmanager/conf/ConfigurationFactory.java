package org.nbena.beersmanager.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.json.conf.JSONExporter;
import org.nbena.beersmanager.query.QueryRunner;
import org.nbena.beersmanager.query.QueryRunner.BeerFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.BreweryFilterAlgorithm;
import org.nbena.beersmanager.query.QueryRunner.StyleFilterAlgorithm;

public class ConfigurationFactory {
	
	
	public ConfigurationFactory(){
	}
	
	public static Configuration getDefaultConfiguration(){
		Configuration conf=new Configuration();
//		conf.setBeerFilterAlgorithm(BeerFilterAlgorithm.NONE);
//		conf.setBeerFilterValue("");
//		conf.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_DESCENDING);
//		
//		conf.setBreweryFilterAlgorithm(BreweryFilterAlgorithm.NONE);
//		conf.setBreweryFilterValue("");
//		conf.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.AVERAGE_DESCENDING);
//		
//		conf.setStyleFilterAlgorithm(StyleFilterAlgorithm.NONE);
//		conf.setStyleFilterValue("");
//		conf.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_COUNTRY);
		conf = getDefaultSortingConfiguration(conf);
		conf = getDefaultFilteringConfiguration(conf);
		conf.setPaths(Utils.jsonConfiguration(Utils.currentDirectory()));
		return conf;
	}
	
	public static Configuration getDefaultSortingConfiguration(Configuration conf){
		conf.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_DESCENDING);
		conf.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.AVERAGE_DESCENDING);
		conf.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_COUNTRY);
		return conf;
	}
	
	public static Configuration getDefaultFilteringConfiguration(Configuration conf){
		conf.setBeerFilterAlgorithm(BeerFilterAlgorithm.NONE);
		conf.setBeerFilterValue("");
		conf.setBreweryFilterAlgorithm(BreweryFilterAlgorithm.NONE);
		conf.setBreweryFilterValue("");		
		conf.setStyleFilterAlgorithm(StyleFilterAlgorithm.NONE);
		conf.setStyleFilterValue("");
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
	
	public static String getConfigurationPath(){
		return Utils.jsonConfiguration(Utils.currentDirectory());
	}
	
	public static Configuration setupPath(Configuration c){
		c.setPaths(Utils.currentDirectory());
		return c;
	}
	
	public static Configuration readConfiguration(String configurationPath) throws JSONException, FileNotFoundException{
		JSONExporter exporter=new JSONExporter();
		exporter.setIn(new FileInputStream(new File(configurationPath)));
		return exporter.readConfiguration();
	}
	
	public static void writeConfiguration(Configuration c, String configurationPath) throws FileNotFoundException{
		JSONExporter exporter=new JSONExporter();
		exporter.setOut(new FileOutputStream(new File(configurationPath)));
		exporter.writeConfiguration(c);
	}

}