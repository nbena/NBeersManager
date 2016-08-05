package org.nbena.beersmanager.xml;
//
//import java.io.File;
//import java.io.OutputStream;
//import java.io.StringWriter;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import org.nbena.beersmanager.coreclasses.Beer;
//import org.nbena.beersmanager.coreclasses.Brewery;
//import org.nbena.beersmanager.coreclasses.Style;
//import org.nbena.beersmanager.export.Exporter;
//
public class XMLCoreClassHelper{
//
//	
//	@XmlRootElement(name="styles")
//	static class StylesXMList{
//		
//		public StylesXMList(){}
//		
//		@XmlElement(name="style")
//		private LinkedList<Style> list=null;
//		
//		/**
//		 * @return the list
//		 */
//		private LinkedList<Style> getStyles(){
//			return list;
//		}
//		
//		/**
//		 * @param list the list to set
//		 */
//		private void setStyles(List<Style> list){
//			//this.list=list;
//			list=new LinkedList<Style>();
//			list.addAll(list);
//		}
//	}
//	
//	
//	@XmlRootElement(name="brewery")
//	static class BreweriesXMList{
//		
//		public BreweriesXMList(){}
//		
//		@XmlElement(name="brewery")
//		private LinkedList<Brewery> list=null;
//
//		/**
//		 * @return the list
//		 */
//		private LinkedList<Brewery> getBreweries() {
//			return list;
//		}
//
//		/**
//		 * @param list the list to set
//		 */
//		private void setBreweries(List<Brewery> list) {
//			//this.list = list;
//			list=new LinkedList<Brewery>();
//			list.addAll(list);
//		}
//		
//	}
//	
//	
//	
//	@XmlRootElement(name="beers")
//	static class BeersXMLSpecialClassList{
//		
//		public BeersXMLSpecialClassList(){}
//		
//		@XmlElement(name="beer")
//		private LinkedList<BeerXMLSpecialClass> list=null;
//
//		/**
//		 * @return the list
//		 */
//		private LinkedList<BeerXMLSpecialClass> getBeers() {
//			return list;
//		}
//
//		/**
//		 * @param list the list to set
//		 */
//		private void setBeers(LinkedList<BeerXMLSpecialClass> list) {
//			//this.list = list;
//			list=new LinkedList<BeerXMLSpecialClass>();
//			list.addAll(list);
//		}
//		
//	}
//	
//	
//	@XmlRootElement(name="beers")
//	static class BeersXMList{
//		
//		public BeersXMList(){}
//		
//		@XmlElement(name="beer")
//		private LinkedList<Beer> list=null;
//
//		/**
//		 * @return the list
//		 */
//		private LinkedList<Beer> getBeers() {
//			return list;
//		}
//
//		/**
//		 * @param list the list to set
//		 */
//		private void setBeers(List<Beer> list) {
//			//this.list = list;
//			list=new LinkedList<Beer>();
//			list.addAll(list);
//		}
//		
//	}
//	
//	
//	
//	public static LinkedList<Brewery> getAllBreweries(LinkedList<File> files) throws JAXBException{
//		LinkedList<Brewery> breweries = new LinkedList<Brewery>();
//		BreweriesXMList breweriesXML=null;
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXMList.class);	
//		for(File file: files){
//			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
//			breweriesXML=(BreweriesXMList) unMarshaller.unmarshal(file);
//			breweries.addAll(breweriesXML.getBreweries());
//		}
//		
//		return breweries;
//	}
//	
//	public static LinkedList<Style> getAllStyles(LinkedList<File> files) throws JAXBException{
//		LinkedList<Style> styles = new LinkedList<Style>();
//		StylesXMList stylesXML=null;
//		JAXBContext jaxbContext=JAXBContext.newInstance(StylesXMList.class);	
//		for(File file: files){
//			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
//			//unMarshaller.set
//			stylesXML=(StylesXMList) unMarshaller.unmarshal(file);
//			styles.addAll(stylesXML.getStyles());
//		}
//		
//		return styles;
//	}
//	
//	public static LinkedList<BeerXMLSpecialClass> getAllBeersSpecialClass(LinkedList<File> files) throws JAXBException{
//		LinkedList<BeerXMLSpecialClass> beers = new LinkedList<BeerXMLSpecialClass>();
//		BeersXMLSpecialClassList beersXML=null;
//		JAXBContext jaxbContext=JAXBContext.newInstance(BeersXMLSpecialClassList.class);	
//		for(File file: files){
//			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
//			beersXML=(BeersXMLSpecialClassList) unMarshaller.unmarshal(file);
//			beers.addAll(beersXML.getBeers());
//		}
//		
//		return beers;
//	}
//	
//	
//	public static LinkedList<Beer> getAllBeersNormalClass(LinkedList<File> files) throws JAXBException{
//		LinkedList<Beer> beers = new LinkedList<Beer>();
//		BeersXMList beersXML=null;
//		JAXBContext jaxbContext=JAXBContext.newInstance(BeersXMList.class);	
//		for(File file: files){
//			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();	
//			beersXML=(BeersXMList) unMarshaller.unmarshal(file);
//			beers.addAll(beersXML.getBeers());
//		}
//		
//		return beers;
//	}
//	
//	
//	public void writeBrewery(List<Brewery> breweries, OutputStream out) throws JAXBException{
//		//split the breweries list into multiple list
//		//breweries.subList(fromIndex, toIndex)
//		/*
//		int foreach, j;
//		j=0;
//		if(breweries.size()%files.size()==0){
//			foreach=breweries.size()/files.size();
//		}
//		
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXMList.class);
//		
//		
//		for(int i=0;i<foreach;i++){
//			Marshaller marshaller=jaxbContext.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.marshal(breweries, files.get((i*foreach)/foreach));
//		}
//		
//		*/
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BreweriesXMList xmlBreweries=new BreweriesXMList();
//		xmlBreweries.setBreweries(breweries);
//		marshaller.marshal(xmlBreweries, out);
//
//	}
//	
//	
//	public void writeBeerSpecialClass(List<Beer> beers, OutputStream out) throws JAXBException{
//		JAXBContext jaxbContext = JAXBContext.newInstance(BeersXMLSpecialClassList.class);
//		LinkedList<BeerXMLSpecialClass> specialClassBeers=Convertitor.toXMLSpecialClassList(beers);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BeersXMLSpecialClassList xmlBeers=new BeersXMLSpecialClassList();
//		xmlBeers.setBeers(specialClassBeers);
//		marshaller.marshal(xmlBeers, out);	
//	}
//	
//	public void writeBeer(List<Beer> beers, OutputStream out) throws JAXBException{
//		JAXBContext jaxbContext = JAXBContext.newInstance(BeersXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BeersXMList xmlBeers=new BeersXMList();
//		xmlBeers.setBeers(beers);
//		marshaller.marshal(xmlBeers, out);	
//	}
//	
//	public void writeStyle(List<Style> styles, OutputStream out) throws JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(StylesXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		StylesXMList xmlStyles=new StylesXMList();
//		xmlStyles.setStyles(styles);
//		marshaller.marshal(xmlStyles, out);		
//	}
//	
//	
//	public static String writeBreweries(LinkedList<Brewery> breweries) throws JAXBException{
//		StringWriter w=new StringWriter();
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BreweriesXMList xmlBreweries=new BreweriesXMList();
//		xmlBreweries.setBreweries(breweries);
//		marshaller.marshal(xmlBreweries, w);
//		return w.toString();
//
//	}
//	
//	public static String writeBeersSpecialClass(LinkedList<Beer> beers) throws JAXBException{
//		StringWriter w=new StringWriter();
//		JAXBContext jaxbContext = JAXBContext.newInstance(BeersXMLSpecialClassList.class);
//		LinkedList<BeerXMLSpecialClass> specialClassBeers=Convertitor.toXMLSpecialClassList(beers);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BeersXMLSpecialClassList xmlBeers=new BeersXMLSpecialClassList();
//		xmlBeers.setBeers(specialClassBeers);
//		marshaller.marshal(xmlBeers, w);	
//		return w.toString();
//	}
//	
//	public static String writeBeersNormalClass(LinkedList<Beer> beers) throws JAXBException{
//		StringWriter w=new StringWriter();
//		JAXBContext jaxbContext = JAXBContext.newInstance(BeersXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		BeersXMList xmlBeers=new BeersXMList();
//		xmlBeers.setBeers(beers);
//		marshaller.marshal(xmlBeers, w);	
//		return w.toString();
//	}
//	
//	public static String writeStyles(LinkedList<Style> styles) throws JAXBException{
//		StringWriter w=new StringWriter();
//		JAXBContext jaxbContext=JAXBContext.newInstance(StylesXMList.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		StylesXMList xmlStyles=new StylesXMList();
//		xmlStyles.setStyles(styles);
//		marshaller.marshal(xmlStyles, w);	
//		return w.toString();
//	}
//	
//	/*
//	public static void appendBeer(Beer beer, File file) throws IOException{
//		BufferedWriter bw=Files.newBufferedWriter(Paths.get(file.toURI()), StandardOpenOption.APPEND);
//		PrintStream ps=new PrintStream(Files.newOutputStream(Paths.get(file.toURI()), StandardOpenOption.APPEND));
//		ps.
//	}
//	*/
}
