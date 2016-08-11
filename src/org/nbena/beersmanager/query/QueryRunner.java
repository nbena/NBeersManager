package org.nbena.beersmanager.query;

import java.util.List;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.nbena.beersmanager.coreclasses.*;
import org.nbena.beersmanager.exe.Utils;
public class QueryRunner {
	
	public static enum ORDER_BY_BEER{}
	
	//************************************************************************************************************************************
	//LIST-RETURNED QUERY
	//***********************************************************************************************************************************
	
	//*******************************************************************
	// BEERS QUERY
	//*****************************************************************
	
	public static List<Beer> beersFilteredByStyle(List<Beer> beers, Style style){
		return beers.stream().filter(b -> b.getStyle().compareTo(style)==0)
				.collect(Collectors.toList());
	}
	
	public static List<Beer> beersFilteredByMainStyle(List<Beer> beers, Style style){
		return beers.stream().filter(b -> b.getStyle().getStyleMainName().equals(style.getStyleMainName()))
				.collect(Collectors.toList());
	}
	

	
	public static List<Beer> beersFilteredByBrewery(List<Beer> beers, Brewery brewery){
		return beers.stream().filter(b -> b.getBrewery().compareTo(brewery)==0)
		.collect(Collectors.toList());
	}
	
	
	
	public static List<Beer> beersFilteredByMiminumMark(List<Beer> beers, int mark){
		return beers.stream().filter(b -> b.getMark()>=mark)
				.collect(Collectors.toList());
	}

	public static List<Beer> beersFilteredByExactMark(List<Beer> beers, int mark){
		return beers.stream().filter(b -> b.getMark()==mark)
				.collect(Collectors.toList());
	}
	
	
	
	public static List<Beer> beersFilteredByIsTried(List<Beer> beers, boolean isTried){
		return beers.stream().filter(b -> b.isTried()==isTried)
				.collect(Collectors.toList());
	}
	
	
	
	
	public static List<Beer> beersFilteredByMinimumNumberOfStars(List<Beer> beers, int numberOfStar){
		return beers.stream().filter(b -> b.getNumberOfStars()>=numberOfStar)
				.collect(Collectors.toList());
	}
	
	public static List<Beer> beersFilteredByExactNumberOfStars(List<Beer> beers, int numberOfStar){
		return beers.stream().filter(b -> b.getNumberOfStars()==numberOfStar)
				.collect(Collectors.toList());
	}
	
	
	
	
	public static List<Beer> beersFilteredByMinimumAlcool(List<Beer> beers, double alcool){
		return beers.stream().filter(b -> b.getAlcool()>=alcool)
				.collect(Collectors.toList());
	}
	
	public static List<Beer> beersFilteredByExatcAlcool(List<Beer> beers, double alcool){
		return beers.stream().filter(b -> b.getAlcool()==alcool)
				.collect(Collectors.toList());
	}
	
	

	
	//this can be done by using multiple QueryRunner function, but I think it's not efficient
	
	public static List<Beer> beersFilteredByTrappist(List<Beer> beers, boolean trappist){
		return beers.stream().filter(b -> b.getBrewery().isAuthenticTrappist()==trappist)
				.collect(Collectors.toList());
	}
	
	
	
	public static List<Beer> beersFilteredByColour(List<Beer> beers, String color){
		return beers.stream().filter(b -> b.getColor().equalsIgnoreCase(color))
				.collect(Collectors.toList());
	}
	
	
	
	
	public static List<Beer> beerFilteredByFermentation(List<Beer> beers, Fermentation fermentation){
		return beers.stream().filter(b -> b.getStyle().getFermentation()==fermentation)
				.collect(Collectors.toList());
	}
	
	
	
	
	public static List<Beer> beersFilteredByBreweryCountry(List<Beer> beers, String country){	
		return beers.stream().filter(b -> b.getBrewery().getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
			
	}
	
	
	
	public static List<Beer> beersFilteredByStyleProvenience(List<Beer> beers, String provenience){
		return beers.stream().filter(b -> b.getStyle().getStyleCountryOrigin().equalsIgnoreCase(provenience))
				.collect(Collectors.toList());
	}
	
	///******************************************************************
	// EXTRACTION QUERY
	//*****************************************************************
	public static List<Brewery> getAllBreweries(List<Beer> beers){
		List<Brewery> breweries=new LinkedList<Brewery>();
		for(Beer b: beers){
			if(breweries.contains(b.getBrewery())==false){
				breweries.add(b.getBrewery());
			}
		}
		return breweries;
	}
	
	public static List<Style> getAllStyles(List<Beer> beers){
		List<Style> styles=new LinkedList<Style>();
		for(Beer b: beers){
			if(styles.contains(b.getStyle())==false){
				styles.add(b.getStyle());
			}
		}
		return styles;
	}
	
	
	//*******************************************************************
	// BREWERIES QUERY
	//*****************************************************************
	
	public static List<Brewery> breweriesFilteredByCountry(List<Brewery> breweries, String country){
		return breweries.stream().filter(br -> br.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
	}
	
	public static List<Brewery> breweriesFilteredByTrappist(List<Brewery> breweries, boolean trappist){
		return breweries.stream().filter(b -> b.isAuthenticTrappist()==trappist)
				.collect(Collectors.toList());
	}

	
	//*******************************************************************
	// STYLES QUERY
	//*****************************************************************
	
	
	public static List<Style> stylesFilteredByFermentation(List<Style> styles, Fermentation fermentation){
		return styles.stream().filter(s -> s.getFermentation()==fermentation)
				.collect(Collectors.toList());
	}
	
	public static List<Style> stylesFilteredByCountryOrigin(List<Style> styles, String country){
		return styles.stream().filter(s -> s.getStyleCountryOrigin().equalsIgnoreCase(country))
				.collect(Collectors.toList());
	}
	
	public static List<Style> onlyMainStyles(List<Style> styles){
		List<Style> filteredStyles=new LinkedList<Style>();
		//Style nakedStyle;
		for(Style s: styles){
			//nakedStyle=Utils.getNakedStyle(s);
			//if(Collections.binarySearch(filteredStyles, nakedStyle, new Comparators.ComparatorStyleOnlyMain())<0){
			if(!searchForMainStyle(filteredStyles, s)){
//				filteredStyles.add(nakedStyle);
				filteredStyles.add(s);
			}
		}
		return filteredStyles;
	}
	
	private static boolean searchForMainStyle(List<Style> styles, Style style){
		boolean ret=false;
		for(int i=0;i<styles.size();i++){
			Style s=styles.get(i);
			if (s.getStyleMainName().equals(style.getStyleMainName())){
				i=styles.size();
				ret=true;
			}
		}
		return ret;
	}
	
	public static List<String> onlyMainStylesAsString(List<Style> styles){
		List<Style> filtered=onlyMainStyles(styles);
		List<String> strings=new LinkedList<String>();
		for(Style s: filtered){
			strings.add(s.getStyleMainName());
		}
		return strings;
	}
	
	
	public static List<Beer> theBestBeersBasedOnMark(List<Beer> beers){
		int i, j;
		List<Beer> bestBeers=new LinkedList<Beer>();
		bestBeers.add(beers.get(0));
		j=0;
		for(i=1;i<beers.size();i++){
			if(beers.get(i).getMark()>=bestBeers.get(j).getMark()){
				if(beers.get(i).getMark()==bestBeers.get(j).getMark()){
					//need to check if empty because of the remove 
					if(bestBeers.isEmpty()){
						bestBeers.add(j, beers.get(i));
					}
					else{
						bestBeers.add(j, beers.get(i));
						j++;
					}
				}
				else{
					//if more remove the precedent
					Beer beerToCheck=beers.get(i); //necessary because the removeIf doesn't want beers.get(i);
					bestBeers.removeIf(b -> b.getMark()<= beerToCheck.getMark());
					if(bestBeers.isEmpty()){
						bestBeers.add(j, beers.get(i));
					}
					else{
						bestBeers.add(j, beers.get(i));
						j++;					
					}
				}
			}
		}
		return bestBeers;
	}
	
	
	
	//************************************************************************************************************************************
	//SIGNLE OBJECT-RETURNED QUERY
	//***********************************************************************************************************************************
	
	/**
	 * Method that returns the best beer in the list basing only on the beer's mark.
	 * If two or more beers have the same mark, the first of them is chosen.
	 * @param beers
	 * @return the beer with the highest mark
	 */
	public static Beer theBestBeerBasedOnMark(List<Beer> beers){
		Beer b=beers.get(0);
		for(int i=1;i<beers.size();i++){
			Beer beerGet=beers.get(i);
			if(beerGet.getMark()>b.getMark()){
				b=beerGet;
			}
		}
		return b;
	}
	

	
	public static HashMap<Brewery, Double> breweryWithAvrerageMoreThan(LinkedList<Beer> beers, double average, boolean isTried){
		HashMap<Brewery, Double> bestBreweriesMap=new HashMap<Brewery, Double>();
		List<Brewery> breweries=new LinkedList<Brewery>();
		double forEachAverage=0;
		breweries=getAllBreweries(beers);
		HashMap<Brewery, Double> breweryAverage=new HashMap<Brewery, Double>();
		for(Brewery brewery: breweries){
			forEachAverage=0;
			List<Beer> beerBrewery=beersFilteredByBrewery(beers, brewery);
			for(Beer b: beerBrewery){
				if(b.isTried()==isTried){
					forEachAverage+=b.getMark();
				}
			}
			forEachAverage/=beerBrewery.size();
			breweryAverage.put(brewery, forEachAverage);
		}
		breweryAverage.forEach((k, v) -> {
			if(v>=average){
				bestBreweriesMap.put(k, v);
			}
		});
		return bestBreweriesMap;
	}
	
	public static double breweryAverage(List<Beer> beers, Brewery b){
		double average=0;
		List<Beer> beersFiltered = beersFilteredByBrewery(beers, b);
		for (Beer beer: beersFiltered){
			average+=beer.getMark();
		}
		return average/beersFiltered.size();
	}
	
	
	public static HashMap<Brewery, Double> breweriesAverage(List<Beer> beers){
		HashMap<Brewery, Double> map=new HashMap<Brewery, Double>();
		List<Brewery> breweries = getAllBreweries(beers);
		for(Brewery b: breweries){
			map.put(b, breweryAverage(beers, b));
		}
		return map;
	}
	
	/**
	 * Sort beers by their brewery (country, town, name), and then by their style (fermentation, name, subcategory)
	 * @param beers
	 * @return
	 */
	public static List<Beer> sortBeerByCountryOfBreweryStyle(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByCountryBreweryStyleName());
		return sortedBeers;
	}
	
	/**
	 * Sort beers by fermentation, then style (origin country, name, subcategory), and then brewery (town, name).
	 * @param beers
	 * @return
	 */
	public static List<Beer> sortBeerByFermentationCountryOfStyleBrewery(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByFermentationCountryStyleBreweryName());
		return sortedBeers;
	}
	
	/**
	 * Sort beers by fermentation, style (name and subcategory) then brewery (country, town, name).
	 * @param beers
	 * @return
	 */
	public static List<Beer> sortBeerByFermentationStyleCountryOfBrewery(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByFermentationStyleCountryBreweryName());
		return sortedBeers;
	}
	
	@Deprecated 
	/**
	 * Only internal use for binary search.
	 * @param beers
	 * @return
	 */
	public static List<Beer> sortBeerByNameStyleBrewery(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByNameStyleBrewery());
		return sortedBeers;
	}
	
	@Deprecated //wait
	public static List<Beer> sortBeerByCountryBreweryAverage(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByCountryBreweryAverage());
		return sortedBeers;
	}
	
	
	//SORT FUNCTIONS
	public static void sortStyleByFermentationThenCountry(List<Style> styles){
		Collections.sort(styles , new Comparators.ComparatorStyleFermentationComplete());
	}
	
	public static void sortStyleByCountryThenFermentationy(List<Style> styles){
		Collections.sort(styles , new Comparators.ComparatorStyleCountryComplete());
	}
	
	
	
	
	
	//exist function
	public static boolean isStyleExists(List<Style> styles, Style style){
		return Collections.binarySearch(styles, style) >= 0 ? true : false;
	}
	
	public static boolean isBreweryExists(List<Brewery> breweries, Brewery brewery){
		return Collections.binarySearch(breweries, brewery) >= 0 ? true : false;
	}
	
	public static boolean isBeerExists(List<Beer> beers, Beer beer){
		return Collections.binarySearch(beers, beer, new Comparators.ComparatorBeerByNameStyleBrewery()) >=0 ? true: false;
	}
	

}
