package org.nbena.beersmanager.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.json.coreclasses.BeerJSONSpecialClass;
import org.nbena.beersmanager.json.coreclasses.Converter;

public class MainExporter {
	
	public static void main(String args[]) throws FileNotFoundException, Exception{
		Beer b=new Beer();
		Beer b1=new Beer();
		Brewery br=new Brewery();
		Style s=new Style();
		
		s.setStyleMainName("Stout");
		s.setStyleSubCategory("Dry");
		s.setStyleCountryOrigin("Ireland");
		s.setDescription("Good");
		s.setFermentation(Fermentation.HIGH);
		
		br.setAuthenticTrappist(false);
		br.setCountry("Ireland");
		br.setDescription("Best");
		br.setName("Carlow Brewing");
		br.setTown("Carlow");
		br.setWebsite("www.carlowbrewing.com");
		
		b.setName("O'Hara's Irish Stout");
		b.setAlcool(5.6);
		b.setBrewery(br);
		b.setColor("Black");
		b.setMark(90);
		b.setNumberOfStars(5);
		b.setPlaceTried("The Duke - Dublin");
		b.setPrice(4.65);
		b.setStyle(s);
		b.setTried(true);
		b.setDescription("kjhiukhki");
		
		b1.setName("O'Hara's Leann Follain");
		b1.setAlcool(5.6);
		b1.setBrewery(br);
		b1.setColor("Black");
		b1.setMark(90);
		b1.setNumberOfStars(5);
		b1.setPlaceTried("The Duke - Dublin");
		b1.setPrice(4.65);
		b1.setStyle(s);
		b1.setTried(true);
		b1.setDescription("siki pula");
		//System.out.println(b.getBrewery().getCountry());
		
		LinkedList<Beer> beer=new LinkedList<Beer>();
		LinkedList<Brewery> brewery=new LinkedList<Brewery>();
		LinkedList<Style> style=new LinkedList<Style>();
		beer.add(b);
		beer.add(b1);
		brewery.add(br);
		style.add(s);
		
//		MSExcelOldExporter o=new MSExcelOldExporter();
//		MSExcelNewExporter oo=new MSExcelNewExporter();
		org.nbena.beersmanager.json.coreclasses.JSONExporter j=new org.nbena.beersmanager.json.coreclasses.JSONExporter();
		/*
		oo.writeBrewery(brewery, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\breweryx.xlss")));
		oo.writeStyle(style, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\stylex.xlsx")));
		oo.writeBeer(beer, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\beerx.xlsx")));
		
		o.writeBrewery(brewery, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\brewery.xls")));
		o.writeStyle(style, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\style.xls")));
		o.writeBeer(beer, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\beer.xls")));
		*/
		j.writeBeerSpecial(beer, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\beer.json")));
		j.writeBrewery(brewery, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\brewery.json")));
		j.writeStyleSpecial(style, new FileOutputStream(new File("C:\\Users\\nicola\\Desktop\\style.json")));
		
		
		System.out.println("Reading time");
		List<Beer> beerRead=new LinkedList<Beer>();
		List<BeerJSONSpecialClass> beerSpecial = new LinkedList<BeerJSONSpecialClass>();
		List<Brewery> breweryRead=new LinkedList<Brewery>();
		List<Style> styleRead=new LinkedList<Style>();
		
		styleRead =  Converter.toNormalStyleList(j.readStylesSpecial( new FileInputStream(new File("C:\\Users\\nicola\\Desktop\\style.json"))));  
		beerSpecial = j.readBeersSpecial( new FileInputStream(new File("C:\\Users\\nicola\\Desktop\\beer.json")));
		breweryRead = j.readBreweries( new FileInputStream(new File("C:\\Users\\nicola\\Desktop\\brewery.json")));
		
		beerRead = Converter.recompose(beerSpecial, breweryRead, styleRead);
		
		for (Beer x : beerRead){
			System.out.println(x.toString());
		}
		
	}

}
