package org.nbena.beersmanager.conf;

import java.io.File;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.nbena.beersmanager.conf.Configuration.BeersDefault;
import org.nbena.beersmanager.conf.Configuration.BreweriesDefault;
import org.nbena.beersmanager.conf.Configuration.StyleDefault;
import org.nbena.beersmanager.conf.Configuration.ViewAllDefault;
import org.nbena.beersmanager.conf.Configuration.WriteToFileWhen;

public class ConfiguratioFactory {
	
	public static Configuration getDefaultConfiguration(){
		Configuration conf=new Configuration();
//		conf.setBackupEnabledOption(BackupEnabled.YES);
		conf.setBeersDefaultOption(BeersDefault.ALL_STAR_BEERS);
		conf.setBreweriesDeafultOption(BreweriesDefault.ALL_BREWERIES_GROUP_COUNTRY);
//		conf.setExportDefaultoption(ExportDefault.EXPORT_XML);
		conf.setStarMarkValue(80);
		conf.setStyleDeafultOption(StyleDefault.ALL_STYLES_GROUP_BY_COUNTRY_FERMENTATION);
		conf.setViewAllDefaultOption(ViewAllDefault.FERMENTATION_STYLE_COUNTRY_BREWERY);
		conf.setWriteToFileWhenOption(WriteToFileWhen.EVERY_UPDATE);
		String path=setupXMLPaths(conf);
//		conf.setBeweryFilePathBackup(path+"backup-default"+File.pathSeparator+"beers-backup.xml");
//		conf.setBeweryFilePathBackup(path+"backup-default"+File.pathSeparator+"breweries-backup.xml");
//		conf.setStyleFilePathBackup(path+"backup-default"+File.pathSeparator+"styles-backup.xml");
		return conf;
	}
	
	public static String setupXMLPaths(Configuration conf) {
		String path=new File(".").getAbsolutePath();
		path+=File.pathSeparator+"data"+File.pathSeparator;
		conf.setBeerFilePath(path+"beers.xml");	
		conf.setBreweryFilePath(path+"breweries.xml");
		conf.setStyleFilePath(path+"styles.xml");
		return path;
	}
	
	public static Configuration getCurrentConfiguration(File file) throws JAXBException{
		JAXBContext jaxbContext=JAXBContext.newInstance(Configuration.class);
		Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
		Configuration conf=(Configuration) unMarshaller.unmarshal(file);	
		setupXMLPaths(conf);
		return conf;
	}
	
	public static void writeConfiguration(Configuration conf, File file) throws JAXBException{
		JAXBContext jaxbContext=JAXBContext.newInstance(Configuration.class);
		Marshaller marshaller=jaxbContext.createMarshaller();
		marshaller.marshal(conf, file);
	}

}
