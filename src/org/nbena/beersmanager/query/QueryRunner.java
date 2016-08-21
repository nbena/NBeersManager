package org.nbena.beersmanager.query;

import java.util.List;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.nbena.beersmanager.coreclasses.*;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.Comparators.ComparatorBeerForBinarySearch;
import org.nbena.beersmanager.query.Comparators.ComparatorBreweryByAverageAscending;
import org.nbena.beersmanager.query.Comparators.ComparatorBreweryByCountryThenAverageAscending;
public class QueryRunner {
	
	public static enum ORDER_BY_BEER{}
	
	
	
	//************************************************************************************************************************************
	//LIST-RETURNED QUERY
	//***********************************************************************************************************************************
	
	//*******************************************************************
	// BEERS QUERY
	//*****************************************************************
	
	public static enum BeerSortingAlgorithm{
		COUNTRY_OF_BREWERY_STYLE,
		FERMENTATION_COUNTRY_OF_STYLE_BREWERY,
		FERMENTATIOM_STYLE_COUNTRY_OF_BREWERY,
		MARK_STAR_ASCENDING,
		STAR_MARK_ASCENDING,
		MARK_STAR_DESCENDING,
		STAR_MARK_DESCENDING
	}



	public static enum StyleSortingAlgorithm{
		FERMENTATION_COUNTRY,
		COUNTRY_FERMENTATION
	}



	public static enum BrewerySortingAlgorithm{
		COUNTRY_NAME,
		NAME,
		AVERAGE_ASCENDING,
		COUNTRY_AVERAGE_ASCENDING,
		AVERAGE_DESCENDING,
		COUNTRY_AVERAGE_DESCENDING
	}
	
	
	public static enum BeerFilterAlgorithm{
		NONE,
		BY_FERMENTATION_HIGH,
		BY_FERMENTATION_LOW,
		BY_FERMENTATION_SPONTANEOUS,
		BY_COUNTRY,
		BY_STYLE_PROVENIENCE,
		BY_MINIMUM_STARS,
		BY_MINIMUM_MARK,
		BY_MINIMUM_ABV,
		BY_IS_TRIED_YES,
		BY_IS_TRIED_NO,
		BY_STYLE,
		BY_MAIN_STYLE,
		BY_BREWERY,
		BY_EXACT_MARK,
		BY_EXACT_STAR,
		BY_EXACT_ABV,
		BY_TRAPPIST_YES,
		BY_TRAPPIST_NO
	}
	
	public static enum BreweryFilterAlgorithm{
		NONE,
		COUNTRY,
		TRAPPIST_NO,
		TRAPPIST_YES
	}
	
	public static enum StyleFilterAlgorithm{
		
		NONE,
		BY_FERMENTATION_HIGH,
		BY_FERMENTATION_LOW,
		BY_FERMENTATION_SPONTANEOUS,
		BY_COUNTRY,
		BY_MAIN_STYLE
	}



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
	
	
	
//	public static List<Beer> beersFilteredByColour(List<Beer> beers, String color){
//		return beers.stream().filter(b -> b.getColor().equalsIgnoreCase(color))
//				.collect(Collectors.toList());
//	}
//	
	
	
	
	public static List<Beer> beersFilteredByFermentation(List<Beer> beers, Fermentation fermentation){
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
	
	public static List<Brewery> breweriesFilteredByTrappist(List<Brewery> breweries, Boolean trappist){
		return breweries.stream().filter(b -> b.isAuthenticTrappist()==trappist)
				.collect(Collectors.toList());
	}
	
	
	public static List<BreweryAverage> breweriesAverageFilteredByCountry(List<BreweryAverage> breweries, String country){
		return breweries.stream().filter(br -> br.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
	}
	
	public static List<BreweryAverage> breweriesAverageFilteredByTrappist(List<BreweryAverage> breweries, Boolean trappist){
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
	
	public static final List<Style> stylesFilteredByMainStyle(List<Style> styles, Style style){
		return styles.stream().filter(s -> s.getStyleMainName().equals(style.getStyleMainName()))
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
	

	
	
	/**
	 * Sort beers by their brewery (country, town, name), and then by their style (fermentation, name, subcategory)
	 * @param beers
	 * @return
	 */
	public static List<Beer> beersSortedByCountryOfBreweryStyle(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByCountryBreweryStyleName());
		return sortedBeers;
	}
	
	/**
	 * Sort beers by fermentation, then style (origin country, name, subcategory), and then brewery (town, name).
	 * @param beers
	 * @return
	 */
	public static List<Beer> beersSortedByFermentationCountryOfStyleBrewery(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByFermentationCountryStyleBreweryName());
		return sortedBeers;
	}
	
	/**
	 * Sort beers by fermentation, style (name and subcategory) then brewery (country, town, name).
	 * @param beers
	 * @return
	 */
	public static List<Beer> beersSortedByFermentationStyleCountryOfBrewery(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByFermentationStyleCountryBreweryName());
		return sortedBeers;
	}
	
	public static List<Beer> beersSortedByMarkStarAscending(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByMarkStarAscending());
		return sortedBeers;
	}
	
	public static List<Beer> beersSortedByStarMarkAscending(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByStarMarkAscending());
		return sortedBeers;
	}
	
	public static List<Beer> beersSortedByMarkStarDescending(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByMarkStarDescending());
		return sortedBeers;
	}
	
	public static List<Beer> beersSortedByStarMarkDescending(List<Beer> beers){
		List<Beer> sortedBeers= new LinkedList<Beer>(beers);
		Collections.sort(sortedBeers, new Comparators.ComparatorBeerByStarMarkDescending());
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
	public static List<Style> styleSortedByFermentationThenCountry(List<Style> styles){
		List<Style> sortedStyles=new LinkedList<Style>(styles);
		Collections.sort(sortedStyles , new Comparators.ComparatorStyleFermentationComplete());
		return sortedStyles;
	}
	
	public static List<Style> styleSortedByCountryThenFermentationy(List<Style> styles){
		List<Style> sortedStyles=new LinkedList<Style>(styles);
		Collections.sort(sortedStyles , new Comparators.ComparatorStyleCountryComplete());
		return sortedStyles;
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
	
	
	public static double breweryAverage(List<Beer> beers, Brewery b, boolean beersAlreadyFilteredByBrewery){
		double average=0.0;
		List<Beer> beersFiltered;
		if(beersAlreadyFilteredByBrewery){
			beersFiltered=new LinkedList<Beer>(beers);
		}
		else{
			beersFiltered = beersFilteredByBrewery(beers, b);
		}
		for (Beer beer: beersFiltered){
			average+=beer.getMark();
		}
		return average/beersFiltered.size();
	}
	
	
	
	
	public static HashMap<Brewery, Double> breweriesAverage(List<Beer> beers, List<Brewery> breweries, boolean beersAlreadyFiltered){
		HashMap<Brewery, Double> map=new HashMap<Brewery, Double>();
		for(Brewery b: breweries){
			map.put(b, breweryAverage(beers, b, beersAlreadyFiltered));
		}
		return map;
	}
	
	
	public static List<Brewery> breweriesSortedByCountryThenName(List<Brewery> breweries){
		List<Brewery>  sortedBreweries=new LinkedList<Brewery>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByCountryName());
		return sortedBreweries;
	}
	
	public static List<BreweryAverage> breweriesSortedByCountryThenNameWithAverage(List<BreweryAverage> breweries){
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByCountryName());
		return sortedBreweries;
	}
	
	
	
	public static List<Brewery> breweriesSortedByName(List<Brewery> breweries){
		List<Brewery>  sortedBreweries=new LinkedList<Brewery>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByName());
		return sortedBreweries;
	}
	
	public static List<BreweryAverage> breweriesSortedByNameWithAverage(List<BreweryAverage> breweries){
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByName());
		return sortedBreweries;
	}
	
	
	public static List<BreweryAverage> breweriesSortedByAverageAscending(List<BreweryAverage> breweries) {	
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByAverageAscending());
		return sortedBreweries;
	}
	
	
	public static List<BreweryAverage> breweriesSortedByCountryThenAverageAscending(List<BreweryAverage> breweries){
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByCountryThenAverageAscending());
		return sortedBreweries;
	}
	
	public static List<BreweryAverage> breweriesSortedByAverageDescending(List<BreweryAverage> breweries) {	
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByAverageDescending());
		return sortedBreweries;
	}
	
	
	public static List<BreweryAverage> breweriesSortedByCountryThenAverageDescending(List<BreweryAverage> breweries){
		List<BreweryAverage>  sortedBreweries=new LinkedList<BreweryAverage>(breweries);
		Collections.sort(sortedBreweries, new Comparators.ComparatorBreweryByCountryThenAverageDescending());
		return sortedBreweries;
	}
	
	public static class Sorting{
		
	}
	
	
	public static class BinarySearch{
		
		
		public static List<Beer> beersSortedForBinarySearch(List<Beer> beers){
			List<Beer> orderedBeers = new LinkedList<Beer>(beers);
			Collections.sort(orderedBeers, new Comparators.ComparatorBeerForBinarySearch());
			return orderedBeers;
		}
		
		public static int beerSearch(List<Beer> beers, Beer beer, boolean sorted){
			int ret;
			if(!sorted){
				beers = beersSortedForBinarySearch(beers);		
			}
			ret = Collections.binarySearch(beers, beer, new ComparatorBeerForBinarySearch());
			return ret;
		}
		
		public static boolean isBeerExists(List<Beer> beers, Beer beer, boolean sorted){
			return beerSearch(beers, beer, sorted) > 0 ? true : false;
		}
		
		
		
		public static List<Brewery> breweriesSortedForBinarySearch(List<Brewery> breweries){
			List<Brewery> orderedBreweries = new LinkedList<Brewery>(breweries);
			Collections.sort(orderedBreweries, new Comparators.ComparatorBreweryForBinarySearch());
			return orderedBreweries;
		}
		
		public static int brewerySearch(List<Brewery> breweries, Brewery brewery, boolean sorted){
			int ret;
			if(!sorted){
				breweries = breweriesSortedForBinarySearch(breweries);		
			}
			ret = Collections.binarySearch(breweries, brewery, new Comparators.ComparatorBreweryForBinarySearch());
			return ret;
		}
		
		public static boolean isBreweryExists(List<Brewery> beers, Brewery beer, boolean sorted){
			return brewerySearch(beers, beer, sorted) >=0 ? true : false;
		}
		
		
		public static List<BreweryAverage> breweriesAverageSortedForBinarySearch(List<BreweryAverage> breweries){
			List<BreweryAverage> orderedBreweries = new LinkedList<BreweryAverage>(breweries);
			Collections.sort(orderedBreweries, new Comparators.ComparatorBreweryAverageForBinarySearch());
			return orderedBreweries;
		}
		
		public static int breweryAverageSearch(List<BreweryAverage> breweries, BreweryAverage brewery, boolean sorted){
			int ret;
			if(!sorted){
				breweries = breweriesAverageSortedForBinarySearch(breweries);		
			}
			ret = Collections.binarySearch(breweries, brewery, new Comparators.ComparatorBreweryAverageForBinarySearch());
			return ret;
		}
		
		public static boolean isBreweryAverageExists(List<BreweryAverage> beers, BreweryAverage beer, boolean sorted){
			return breweryAverageSearch(beers, beer, sorted) >=0 ? true : false;
		}
		
		
		public static List<Style> stylesSortedForBinarySearch(List<Style> styles){
			List<Style> orderedStyles = new LinkedList<Style>(styles);
//			Collections.sort(orderedStyles, new Comparators.ComparatorStyleForBinarySearch());
			Collections.sort(orderedStyles, new Comparators.ComparatorStyleByCategoryAndSubcategory());
			return orderedStyles;
		}
		
		
		public static int styleSearch(List<Style> styles, Style style, boolean sorted){
			int ret;
			if(!sorted){
				styles = stylesSortedForBinarySearch(styles);		
			}
//			ret = Collections.binarySearch(styles, style, new Comparators.ComparatorStyleForBinarySearch());
			ret = Collections.binarySearch(styles, style, new Comparators.ComparatorStyleByCategoryAndSubcategory());
			return ret;
		}

		public static boolean isStyleExists(List<Style> styles, Style style, boolean sorted){
			return styleSearch(styles, style, sorted) >=0 ? true : false;
		}
		
	}
	
	
		public static List<String> getAllCountriesWithAStyle(List<Style> styles){
			List<String>  countries = new LinkedList<String>();
			styles.stream().filter(s -> countries.contains(s.getStyleCountryOrigin()) ? countries.add(s.getStyleCountryOrigin()) : null);
			return countries;
		}
	
		public static List<String> getAllCountriesWithABrewery(List<Brewery> breweries){
			List<String>  countries = new LinkedList<String>();
			breweries.stream().filter(b -> countries.contains(b.getCountry()) ? countries.add(b.getCountry()) : null);
			return countries;
		}
	
	
	

}
