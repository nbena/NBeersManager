package org.nbena.beersmanager.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.json.conf.JSONExporter;
import org.nbena.beersmanager.query.QueryRunner;

public class ConfigurationFactory {
	
	private String configurationPath;
	
	public ConfigurationFactory(){
		configurationPath=Utils.jsonConfiguration(Utils.currentDirectory());
	}
	
	public static Configuration getDefaultConfiguration(){
		Configuration conf=new Configuration();
		conf.setBeerFilterAlgorithm(Configuration.BeerFilterAlgorithm.NONE);
		conf.setBeerFilterValue("");
		conf.setBeerSortingAlgorithm(QueryRunner.BeerSortingAlgorithm.MARK_STAR_DESCENDING);
		
		conf.setBreweryFilterAlgorithm(Configuration.BreweryFilterAlgorithm.NONE);
		conf.setBreweryFilterValue("");
		conf.setBrewerySortingAlgorithm(QueryRunner.BrewerySortingAlgorithm.AVERAGE_DESCENDING);
		
		conf.setStyleFilterAlgorithm(Configuration.StyleFilterAlgorithm.NONE);
		conf.setStyleFilterValue("");
		conf.setStyleSortingAlgorithm(QueryRunner.StyleSortingAlgorithm.FERMENTATION_COUNTRY);
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
	
	public static Configuration setupPath(Configuration c){
		c.setPwd(Utils.currentDirectory());
		return c;
	}
	
	public Configuration readConfiguration() throws JSONException, FileNotFoundException{
		JSONExporter exporter=new JSONExporter(new FileOutputStream(new File(configurationPath)), new FileInputStream(new File(configurationPath)));
		return exporter.readConfiguration();
	}
	
	public void writeConfiguration(Configuration c) throws FileNotFoundException{
		JSONExporter exporter=new JSONExporter(new FileOutputStream(new File(configurationPath)), new FileInputStream(new File(configurationPath)));
		exporter.writeConfiguration(c);
	}

}
