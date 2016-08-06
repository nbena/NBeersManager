package org.nbena.beersmanager.query;

import java.util.Collections;

import java.util.Comparator;
import java.util.LinkedList;

import org.nbena.beersmanager.coreclasses.*;
import org.nbena.beersmanager.exe.Utils;
/**
 * Class that contains a series of static class that implements comparator of various kind, that
 * should be used in sort and/or search methods.
 * @author nb
 * @see java.lang.Comparator
 *
 */
public class Comparators {
	
	/**
	 * Comparator class that is used to compare breweries.
	 * The algorithm check in order:<ol>
	 * <li> Brewery country</li>
	 * <li> Brewery town </li>
	 * <li> Brewery name</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBreweryGeographycally implements Comparator<Brewery>{


		@Override
		public int compare(Brewery arg0, Brewery arg1) {
			int ret;
			if(arg0.getCountry().equals(arg1.getCountry())){
				if(arg0.getTown().equals(arg1.getTown())){
					ret=arg0.getName().compareTo(arg1.getName());
				}
				else{
					ret=arg0.getTown().compareTo(arg1.getTown());
				}
			}
			else{
				ret=arg0.getCountry().compareTo(arg1.getCountry());
			}
			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare styles.
	 * The algorithm check in order:<ol>
	 * <li> Style name</li>
	 * <li> Style subcategory</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorStyleMainStyleSub implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {
			int ret;
			if(arg0.getStyleMainName().equals(arg1.getStyleMainName())){
				ret=0;
			}
			else{
				ret=arg0.getStyleSubCategory().compareTo(arg1.getStyleSubCategory());
			}
			return ret;	
		}
		
	}
	
	/*
	/**
	 * Comparator class that is used to compare styles.
	 * The algorithm check in order:<ol>
	 * <li> Style fermentation</li>
	 * <li> Style country origin</li>
	 * </ol>
	 * @author nb
	 *
	 *
	public static class ComparatorStyleByFermentationThenCountry implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {
			int ret;
			if(arg0.getFermentation()==arg1.getFermentation()){
				if(arg0.getStyleCountryOrigin().equals(arg1.getStyleCountryOrigin())){
					ret=arg0.compareTo(arg1);
				}
				else{
					ret=arg0.getStyleCountryOrigin().compareTo(arg1.getStyleCountryOrigin());
				}
			}
			else{
				ret=arg0.getFermentation().toString().compareTo(arg1.getFermentation().toString());
			}
			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare styles.
	 * The algorithm check in order:<ol>
	 * <li> Style country origin</li>
	 * <li> Style fermentation</li>
	 * </ol>
	 * @author nb
	 *
	 *
	public static class ComparatorStyleByCountryThenFermentation implements Comparator<Style>{

		@Override
		public int compare(Style o1, Style o2) {
			int ret;
			if(o1.getStyleCountryOrigin().equals(o1.getStyleCountryOrigin())){
				if(o1.getFermentation()==o2.getFermentation()){					
					ret=o1.compareTo(o2);
				}
				else{
					ret=o1.getFermentation().toString().compareTo(o2.getFermentation().toString());
				}
			}
			else{
				ret=o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
			}
			return ret;
		}
		
	}
	*/
	
	
	/**
	 * Comparator class that is used to compare styles.
	 * The algorithm check in order:<ol>
	 * <li> Style fermentation</li>
	 * <li> Style country origin</li>
	 * <li> Style name</li>
	 * <li> Style subcategory</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorStyleFermentationComplete implements Comparator<Style>{

		@Override
		public int compare(Style o1, Style o2) {
			int ret;
			if(o1.getFermentation()==o2.getFermentation()){
				if(o1.getStyleCountryOrigin().equalsIgnoreCase(o2.getStyleCountryOrigin())){
					if(o1.getStyleMainName().equalsIgnoreCase(o2.getStyleMainName())){
							ret=o1.getStyleSubCategory().compareTo(o2.getStyleSubCategory());				
					}
					else{
						ret=o1.getStyleMainName().compareTo(o2.getStyleMainName());
					}
				}
				else{
					ret=o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
				}
			}
			else{
				ret=o1.getFermentation().toString().compareTo(o2.getFermentation().toString());
			}
			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare styles.
	 * The algorithm check in order:<ol>
	 * <li> Style country origin</li>
	 * <li> Style fermentation</li>
	 * <li> Style name</li>
	 * <li> Style subcategory</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorStyleCountryComplete implements Comparator<Style>{

		@Override
		public int compare(Style o1, Style o2) {
			int ret;
				if(o1.getStyleCountryOrigin().equalsIgnoreCase(o2.getStyleCountryOrigin())){
					if(o1.getFermentation()==o2.getFermentation()){
						if(o1.getStyleMainName().equalsIgnoreCase(o2.getStyleMainName())){
							ret=o1.getStyleSubCategory().compareTo(o2.getStyleSubCategory());				
					    }
					    else{
						   ret=o1.getStyleMainName().compareTo(o2.getStyleMainName());
					    }
					}
					else{
						ret=o1.getFermentation().toString().compareTo(o2.getFermentation().toString());
					}
				}
				else{
					ret=o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
				}

			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare beers.
	 * The algorithm check in order:<ol>
	 * <li> Beer's brewery town</li>
	 * <li> Beer's brewery name</li>
	 * <li> Beer's style fermentation </li>
	 * <li> Beer's style name</li>
	 * <li> Beer's style subcategory</li>
	 * <li> Beer name </li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBeerByCountryBreweryStyleName implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			if(arg0.getBrewery().getCountry().equalsIgnoreCase(arg1.getBrewery().getCountry())){
				if(arg0.getBrewery().getTown().equalsIgnoreCase(arg1.getBrewery().getTown())){
					if(arg0.getBrewery().getName().equalsIgnoreCase(arg1.getBrewery().getName())){
						if(arg0.getStyle().getFermentation()==arg1.getStyle().getFermentation()){
							if(arg0.getStyle().getStyleMainName().equalsIgnoreCase(arg1.getStyle().getStyleMainName())){
								if(arg0.getStyle().getStyleSubCategory().equalsIgnoreCase(arg1.getStyle().getStyleSubCategory())){
									ret=arg0.getName().compareTo(arg1.getName());
								}
								else{
									ret=arg0.getStyle().getStyleSubCategory().compareTo(arg1.getStyle().getStyleSubCategory());
								}
							}
							else{
								ret=arg0.getStyle().getStyleMainName().compareTo(arg1.getStyle().getStyleMainName());
							}
						}
						else{
							ret=arg0.getStyle().getFermentation().toString().compareTo(arg1.getStyle().getFermentation().toString());
						}
					}
					else{
						ret=arg0.getBrewery().getName().compareTo(arg1.getBrewery().getName());
					}
				}
				else{
					ret=arg0.getBrewery().getTown().compareTo(arg1.getBrewery().getTown());
				}
			}
			else{
				ret=arg0.getBrewery().getCountry().compareTo(arg1.getBrewery().getCountry());
			}
			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare beers.
	 * The algorithm check in order:<ol>
	 * <li> Beer's style fermentation </li>
	 * <li> Beer's style country origin</li>
	 * <li> Beer's style name</li>
	 * <li> Beer's style subcategory</li>
	 * <li> Beer's brewery town</li>
	 * <li> Beer's brewery name</li>
	 * <li> Beer name </li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBeerByFermentationCountryStyleBreweryName implements Comparator<Beer>{

		@Override
		public int compare(Beer o1, Beer o2) {
			int ret;
			if(o1.getStyle().getFermentation().toString().equalsIgnoreCase(o2.getStyle().getFermentation().toString())){
				if(o1.getStyle().getStyleCountryOrigin().equalsIgnoreCase(o2.getStyle().getStyleCountryOrigin())){
					if(o1.getStyle().getStyleMainName().equalsIgnoreCase(o2.getStyle().getStyleMainName())){
						if(o1.getStyle().getStyleSubCategory().equalsIgnoreCase(o2.getStyle().getStyleSubCategory())){
							if(o1.getBrewery().getTown().equalsIgnoreCase(o2.getBrewery().getTown())){
								if(o1.getBrewery().getName().equalsIgnoreCase(o2.getBrewery().getName())){
									ret=o1.getName().compareTo(o2.getName());
								}
								else{
									ret=o1.getBrewery().getName().compareTo(o2.getBrewery().getName());
								}
							}
							else{
								ret=o1.getBrewery().getTown().compareTo(o2.getBrewery().getTown());
							}
						}
						else{
							ret=o1.getStyle().getStyleSubCategory().compareTo(o2.getStyle().getStyleSubCategory());
						}
					}
					else{
						ret=o1.getStyle().getStyleMainName().compareTo(o2.getStyle().getStyleMainName());
					}
				}
				else{
					ret=o1.getStyle().getStyleCountryOrigin().compareTo(o2.getStyle().getStyleCountryOrigin());
				}
			}
			else{
				ret=o1.getStyle().getFermentation().toString().compareTo(o2.getStyle().getFermentation().toString());
			}
			return ret;
		}
		
	}
	
	/**
	 * Comparator class that is used to compare beers.
	 * The algorithm check in order:<ol>
	 * <li> Beer's style fermentation </li>
	 * <li> Beer's style name</li>
	 * <li> Beer's style subcategory</li>
	 * <li> Beer's brewery country </li>
	 * <li> Beer's brewery town</li>
	 * <li> Beer's brewery name</li>
	 * <li> Beer name </li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBeerByFermentationStyleCountryBreweryName implements Comparator<Beer>{

		@Override
		public int compare(Beer o1, Beer o2) {
			int ret;
			if(o1.getStyle().getFermentation().toString().equalsIgnoreCase(o2.getStyle().getFermentation().toString())){
				if(o1.getStyle().getStyleMainName().equalsIgnoreCase(o2.getStyle().getStyleMainName())){
					if(o1.getStyle().getStyleSubCategory().equalsIgnoreCase(o2.getStyle().getStyleSubCategory())){
						if(o1.getBrewery().getCountry().equalsIgnoreCase(o2.getBrewery().getCountry())){
							if(o1.getBrewery().getTown().equalsIgnoreCase(o2.getBrewery().getTown())){
								if(o1.getBrewery().getName().equalsIgnoreCase(o2.getBrewery().getName())){
									ret=o1.getName().compareTo(o2.getName());
								}
								else{
									ret=o1.getBrewery().getName().compareTo(o2.getBrewery().getName());
								}
							}
							else{
								ret=o1.getBrewery().getTown().compareTo(o2.getBrewery().getTown());
							}
						}
						else{
							ret=o1.getBrewery().getCountry().compareTo(o2.getBrewery().getCountry());
						}
					}
					else{
						ret=o1.getStyle().getStyleSubCategory().compareTo(o2.getStyle().getStyleSubCategory());
					}
				}
				else{
					ret=o1.getStyle().getStyleMainName().compareTo(o2.getStyle().getStyleMainName());
				}
			}
			else{
				ret=o1.getStyle().getFermentation().toString().compareTo(o2.getStyle().getFermentation().toString());
			}
			return ret;
		}
		
	}
	
	
	//Comparator for binary search of beers.
	public static class ComparatorBeerByNameStyleBrewery implements Comparator<Beer>{

		@Override
		public int compare(Beer o1, Beer o2) {
			int rBrewery = o1.getBrewery().compareTo(o2.getBrewery());
			int ret, rStyle, rName;
			if (rBrewery !=0){
				ret=rBrewery;
			}else{
				rStyle= o1.getStyle().compareTo(o2.getStyle());
				if(rStyle!=0){
					ret=rStyle;
				}
				else{
					rName=o1.getName().compareToIgnoreCase(o2.getName());
					ret=rName;
				}
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBeerByCountryBreweryAverage implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int rCountry, rBrewery, ret;
			rCountry=arg0.getBrewery().getCountry().compareTo(arg1.getBrewery().getCountry());
			if (rCountry==0){
				rBrewery = arg0.getMark() > arg1.getMark() ? 1 : 0;
				ret=rBrewery;
			}else{
				ret=rCountry;
			}
			return ret;
		}
		
	}
	
	public static void main(String args[]){
		LinkedList<Brewery> breweries=new LinkedList<Brewery>();
		Brewery b=new Brewery("Carlow Brewing", "Carlow", "Ireland", "a", "b");
		Brewery b2=new Brewery("Guinness", "Dublin", "Ireland", "a", "b");
		Brewery b3=new Brewery("Porterhouse", "Dublin", "Ireland", "a", "b");
		Brewery b4=new Brewery("Elav", "Bergamo", "Italy", "a", "b");
		Brewery b5=new Brewery("Valcavallina", "Bergamo", "Italy", "a", "b");
		breweries.add(b);
		breweries.add(b2);
		breweries.add(b3);
		breweries.add(b4);
		breweries.add(b5);
		Collections.sort(breweries, new ComparatorBreweryGeographycally());
		Utils.printBreweriesComplete(breweries, System.out);
		
	}

}
