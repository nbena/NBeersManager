package org.nbena.beersmanager.help;
//
//import java.io.File;
//import java.sql.SQLException;
//import java.util.LinkedList;
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
//import org.nbena.beersmanager.coreclasses.Style;;
//
public class SynchronizatorXML {
//	
//	
//	
//	@XmlRootElement(name="styles")
//	static class StylesXML{
//		
//		public StylesXML(){}
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
//		private void setStyles(LinkedList<Style> list){
//			this.list=list;
//		}
//	}
//	
//	@XmlRootElement(name="beers")
//	static class BeersXML{
//		
//		public BeersXML(){}
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
//		private void setBeers(LinkedList<Beer> list) {
//			this.list = list;
//		}
//		
//	}
//	
//	
//	@XmlRootElement(name="brewery")
//	static class BreweriesXML{
//		
//		public BreweriesXML(){}
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
//		private void setBreweries(LinkedList<Brewery> list) {
//			this.list = list;
//		}
//		
//	}
//	
//	
//	private DerbyInterface db;
//
//	public SynchronizatorXML() {
//	}
//
//	public SynchronizatorXML(DerbyInterface db) {
//		this.db = db;
//	}
//	
//	
//	public void synchronizeDbFromXMLBrewery(File file) throws SQLException, JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXML.class);
//		Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
//		BreweriesXML breweries=(BreweriesXML) unMarshaller.unmarshal(file);
//		LinkedList<Brewery> listFromFile=breweries.getBreweries();
//		LinkedList<Brewery> listFromDb=new LinkedList<Brewery>();
//		LinkedList<Brewery> listToAddEventually=new LinkedList<Brewery>();
//		listFromDb=db.getAllBreweries();
//		
//			if(listFromDb.containsAll(listFromFile))
//				return;
//			else{
//				for(Brewery b: listFromFile){
//					if(listFromDb.indexOf(b)==-1){
//						listToAddEventually.add(b);
//					}
//				}
//			}
//			
//			if(listToAddEventually.size()>0){
//				db.insertNewBreweries(listToAddEventually);
//			}
//	}
//	
//	
//	
//	public void synchronizeDbFromXMLBeer(File file) throws SQLException, JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(BeersXML.class);
//		Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
//		BeersXML beers=(BeersXML) unMarshaller.unmarshal(file);
//		LinkedList<Beer> listFromFile=beers.getBeers();
//		LinkedList<Beer> listFromDb=new LinkedList<Beer>();
//		LinkedList<Beer> listToAddEventually=new LinkedList<Beer>();
//		listFromDb=db.queryAllBeers(DerbyInterface.GROUP_BY_BEER_CLAUSE.STYLE_FIRST);
//		
//			if(listFromDb.containsAll(listFromFile))
//				return;
//			else{
//				for(Beer b: listFromFile){
//					if(listFromDb.indexOf(b)==-1){
//						listToAddEventually.add(b);
//					}
//				}
//			}
//			
//			if(listToAddEventually.size()>0){
//				db.insertNewBeersComplete(listToAddEventually);
//			}
//	}
//	
//	
//	public void synchronizeDbFromXMLStyle(File file) throws SQLException, JAXBException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(StylesXML.class);
//		Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
//		StylesXML style=(StylesXML) unMarshaller.unmarshal(file);
//		LinkedList<Style> listFromFile=style.getStyles();
//		LinkedList<Style> listFromDb=new LinkedList<Style>();
//		LinkedList<Style> listToAddEventually=new LinkedList<Style>();
//		listFromDb=db.getAllStyles();
//			if(listFromDb.containsAll(listFromFile))
//				return;
//			else{
//				for(Style s: listFromFile){
//					if(listFromDb.indexOf(s)==-1){
//						listToAddEventually.add(s);
//					}
//				}
//			}
//			
//			if(listToAddEventually.size()>0){
//				db.insertNewStyles(listToAddEventually);
//			}
//	}
//	
//	public void synchronizeXMLBreweryiesFromDb(File file) throws JAXBException, SQLException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(BreweriesXML.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		BreweriesXML breweries=new BreweriesXML();
//		breweries.setBreweries(db.getAllBreweries());
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.marshal(breweries, file);
//	}
//	
//	
//	public void synchronizeXMLBeersFromDb(File file, DerbyInterface.GROUP_BY_BEER_CLAUSE clause) throws JAXBException, SQLException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(BeersXML.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		BeersXML beers=new BeersXML();
//		beers.setBeers(db.queryAllBeers(clause));
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.marshal(beers, file);
//	}
//	
//	public void synchronizeXMLStylesFromDb(File file) throws JAXBException, SQLException{
//		JAXBContext jaxbContext=JAXBContext.newInstance(StylesXML.class);
//		Marshaller marshaller=jaxbContext.createMarshaller();
//		StylesXML styles=new StylesXML();
//		styles.setStyles(db.getAllStyles());
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.marshal(styles, file);
//	}
//
}
