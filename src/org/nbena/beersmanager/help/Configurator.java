package org.nbena.beersmanager.help;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nbena.beersmanager.help.Configuration;

public class Configurator {

	private File file;
	private Configuration configuration;
	
	private static final String STARTUP_OPT = "startupOperation";
	private static final String ORDERING_BEER = "orderingBeer";
	private static final String ORDERING_BREWERY = "orderingBrewery";
	private static final String ORDERING_STYLE = "orderingStyle";
	
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
		/*if(file.exists()==false){
			//Logger.
			System.out.println("");
		}*/
	}


	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}


	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}


	public Configurator(File file, Configuration configuration) {
		this.file = file;
		this.configuration = configuration;
	}
	
	public Configurator() {
	}


	public void putConfigurationToFile() throws JSONException, IOException{
		
		JSONObject obj=new JSONObject();
		obj.put(STARTUP_OPT, configuration.getStartupOpt().toString());
		obj.put(ORDERING_BEER, configuration.getOrderingBeer());
		obj.put(ORDERING_BREWERY, configuration.getOrderingBrewery());
		obj.put(ORDERING_STYLE, configuration.getOrderingStyle());
		obj.write(new FileWriter(file));
	}
	
	public void laodConfigurationFromFile() throws IOException{

		JSONObject obj=new JSONObject(new JSONTokener(new FileInputStream(file)));
		configuration.setOrderingBeer(Configuration.ORDERING_BEER.valueOf(obj.getString(ORDERING_BEER)));
		configuration.setOrderingBrewery(Configuration.ORDERING_BREWERY.valueOf(obj.getString(ORDERING_BREWERY)));
		configuration.setOrderingStyle(Configuration.ORDERING_STYLE.valueOf(obj.getString(ORDERING_STYLE)));
		configuration.setStartupOpt(Configuration.STARTUP_OPT.valueOf(obj.getString(STARTUP_OPT)));
		

	}
	

}
