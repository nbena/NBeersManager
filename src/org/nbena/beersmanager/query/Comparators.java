/*   NBeersManager: manages what you drink.

    Copyright (C) 2016  Nicola Bena

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    */
package org.nbena.beersmanager.query;




import java.util.Comparator;

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
					ret=arg0.getBreweryName().compareTo(arg1.getBreweryName());
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
	
	private static int compareStyleByStyleMainStyleSub(Style arg0, Style arg1){
		int ret;
		if(arg0.getStyleMainName().equals(arg1.getStyleMainName())){
			ret=0;
		}
		else{
			ret=arg0.getStyleSubCategory().compareTo(arg1.getStyleSubCategory());
		}
		return ret;	
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
	public static class ComparatorStyleMainStyleSub implements Comparator<Style>{ //not work on binary search.

		@Override
		public int compare(Style arg0, Style arg1) {
//			int ret;
//			if(arg0.getStyleMainName().equals(arg1.getStyleMainName())){
//				ret=0;
//			}
//			else{
//				ret=arg0.getStyleSubCategory().compareTo(arg1.getStyleSubCategory());
//			}
//			return ret;	
			return compareStyleByStyleMainStyleSub(arg0, arg1);
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
	
	private static int compareStyleByCategoryAndSubcategory(Style o1, Style o2){
		int ret;
		if(o1.getStyleMainName().equalsIgnoreCase(o2.getStyleMainName())){
			ret = o1.getStyleSubCategory().compareToIgnoreCase(o2.getStyleSubCategory());
		}
		else {
			ret = o1.getStyleMainName().compareToIgnoreCase(o2.getStyleMainName());
		}
		return ret;
	}
	
	public static class ComparatorStyleByCategoryAndSubcategory implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {

			return compareStyleByCategoryAndSubcategory(arg0, arg1);
		}
		
	}
	
	public static class ComparatorStyleOnlyMainCategory implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {
			return arg0.getStyleMainName().compareToIgnoreCase(arg1.getStyleMainName());
		}
		
	}
	
	
	private static int compareStyleByFermentationComplete(Style o1, Style o2){
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
//			int ret;
//			if(o1.getFermentation()==o2.getFermentation()){
//				if(o1.getStyleCountryOrigin().equalsIgnoreCase(o2.getStyleCountryOrigin())){
//					if(o1.getStyleMainName().equalsIgnoreCase(o2.getStyleMainName())){
//							ret=o1.getStyleSubCategory().compareTo(o2.getStyleSubCategory());				
//					}
//					else{
//						ret=o1.getStyleMainName().compareTo(o2.getStyleMainName());
//					}
//				}
//				else{
//					ret=o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
//				}
//			}
//			else{
//				ret=o1.getFermentation().toString().compareTo(o2.getFermentation().toString());
//			}
//			return ret;
			return compareStyleByFermentationComplete(o1, o2);
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
					if(arg0.getBrewery().getBreweryName().equalsIgnoreCase(arg1.getBrewery().getBreweryName())){
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
						ret=arg0.getBrewery().getBreweryName().compareTo(arg1.getBrewery().getBreweryName());
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
								if(o1.getBrewery().getBreweryName().equalsIgnoreCase(o2.getBrewery().getBreweryName())){
									ret=o1.getName().compareTo(o2.getName());
								}
								else{
									ret=o1.getBrewery().getBreweryName().compareTo(o2.getBrewery().getBreweryName());
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
								if(o1.getBrewery().getBreweryName().equalsIgnoreCase(o2.getBrewery().getBreweryName())){
									ret=o1.getName().compareTo(o2.getName());
								}
								else{
									ret=o1.getBrewery().getBreweryName().compareTo(o2.getBrewery().getBreweryName());
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
	
	private static int  compareBeerByNameStyleBrewery(Beer o1, Beer o2){
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
	
	
	
	public static class ComparatorStyleByFermentationCategorySubcategory implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {
			int ret;
			if(arg0.getFermentation().compareTo(arg1.getFermentation())==0){
				if(arg0.getStyleMainName().equalsIgnoreCase(arg1.getStyleMainName())){
					ret = arg0.getStyleSubCategory().compareToIgnoreCase(arg1.getStyleSubCategory());
				}else{
					ret = arg0.getStyleMainName().compareTo(arg1.getStyleMainName());
				}
			}else{
				ret = arg0.getFermentation().compareTo(arg1.getFermentation());
			}
			return ret;
		}
		
	}
	
	
	//Comparator for binary search of beers.
	public static class ComparatorBeerByNameStyleBrewery implements Comparator<Beer>{

		@Override
		public int compare(Beer o1, Beer o2) {
//			int rBrewery = o1.getBrewery().compareTo(o2.getBrewery());
//			int ret, rStyle, rName;
//			if (rBrewery !=0){
//				ret=rBrewery;
//			}else{
//				rStyle= o1.getStyle().compareTo(o2.getStyle());
//				if(rStyle!=0){
//					ret=rStyle;
//				}
//				else{
//					rName=o1.getName().compareToIgnoreCase(o2.getName());
//					ret=rName;
//				}
//			}
//			return ret;
			return compareBeerByNameStyleBrewery(o1, o2);
		}
		
	}
	
	public static class ComparatorBeerABVAscending implements Comparator<Beer> {

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			//ugly but compact
			ret = (arg0.getAlcool()>arg1.getAlcool()? 1: (arg0.getAlcool()==arg1.getAlcool())? 0 : -1);
			return ret;
		}
		
	}
	
	public static class ComparatorBeerABVDescending implements Comparator<Beer> {

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			//ugly but compact
			ret = (arg0.getAlcool()>arg1.getAlcool()? -1: (arg0.getAlcool()==arg1.getAlcool())? 0 : 1);
			return ret;
		}
		
	}
	
	
	public static class ComparatorBeerPriceAscending implements Comparator<Beer> {

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			//ugly but compact
			ret = (arg0.getPrice()>arg1.getPrice()? 1: (arg0.getPrice()==arg1.getPrice())? 0 : -1);
			return ret;
		}
		
	}
	
	public static class ComparatorBeerPriceDescending implements Comparator<Beer> {

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			//ugly but compact
			ret = (arg0.getPrice()>arg1.getPrice()? -1: (arg0.getPrice()==arg1.getPrice())? 0 : 1);
			return ret;
		}
		
	}
	
	public static class ComparatorBeerByName implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			return arg0.getName().compareToIgnoreCase(arg1.getName());
		}
		
	}
	
	/**
	 * Comparator class, that checks: <ol>
	 * <li>beer's brewery country</li>
	 * <li>beer's average </li>
	 * 
	 * @author nicola
	 *
	 */
	public static class ComparatorBeerByCountryBreweryAverage implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int rCountry, rBrewery, ret;
			rCountry=arg0.getBrewery().getCountry().compareTo(arg1.getBrewery().getCountry());
			if (rCountry==0){
				if(arg0.getMark()>arg1.getMark()){
					rBrewery=1;
				}else if(arg0.getMark()==arg1.getMark()){
					rBrewery=0;
				}
				else{
					rBrewery=-1;
				}
				ret=rBrewery;
			}else{
				ret=rCountry;
			}
			return ret;
		}
		
	}
	
	@Deprecated
	public static class ComparatorStyleOnlyMain implements Comparator<Style>{

		@Override
		public int compare(Style arg0, Style arg1) {
			return Utils.getNakedStyle(arg0).getStyleMainName().compareTo(Utils.getNakedStyle(arg1).getStyleMainName());
		}
		
	}
	
	
	public static class ComparatorBeerByMarkStarAscending implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			if(arg0.getMark()>arg1.getMark()){
				ret=1;
			}
			else if(arg0.getMark()<arg1.getMark()){
				ret=-1;
			}
			else{
				ret=arg0.getNumberOfStars()-arg1.getNumberOfStars();
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBeerByStarMarkAscending implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			if(arg0.getNumberOfStars()>arg1.getNumberOfStars()){
				ret=1;
			}
			else if(arg0.getNumberOfStars()<arg1.getNumberOfStars()){
				ret=-1;
			}
			else{
				ret=arg0.getMark()-arg1.getMark();
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBeerByMarkStarDescending implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			if(arg0.getMark()>arg1.getMark()){
				ret=-1;
			}
			else if(arg0.getMark()<arg1.getMark()){
				ret=1;
			}
			else{
				ret=arg1.getNumberOfStars()-arg0.getNumberOfStars();
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBeerByStarMarkDescending implements Comparator<Beer>{

		@Override
		public int compare(Beer arg0, Beer arg1) {
			int ret;
			if(arg0.getNumberOfStars()>arg1.getNumberOfStars()){
				ret=-1;
			}
			else if(arg0.getNumberOfStars()<arg1.getNumberOfStars()){
				ret=1;
			}
			else{
				ret=arg1.getMark()-arg0.getMark();
			}
			return ret;
		}
		
	}
	
	
	
	
//	COUNTRY_NAME,
//	NAME,
//	AVERAGE,
//	COUNTRY_AVERAGE
	
	private static int compareBreweryByCountryName(Brewery o1, Brewery o2){
		int ret;
		if(o1.getCountry().equals(o2.getCountry())){
			ret=o1.getBreweryName().compareToIgnoreCase(o2.getBreweryName());
		}else{
			ret=o1.getCountry().compareToIgnoreCase(o2.getCountry());
		}
		return ret;
	}
	
	/**
	 * Comparator that compares Brewery, in order:<ol>
	 * <li>Brewery country</li>
	 * <li>Brewery name</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBreweryByCountryName implements Comparator<Brewery>{

	@Override
	public int compare(Brewery arg0, Brewery arg1) {
//		int ret;
//		if(arg0.getCountry().equals(arg1.getCountry())){
//			ret=arg0.getName().compareToIgnoreCase(arg1.getName());
//		}else{
//			ret=arg0.getCountry().compareToIgnoreCase(arg1.getCountry());
//		}
//		return ret;
		return compareBreweryByCountryName(arg0, arg1);
	}
		
	}
	
	/**
	 * Comparator that compares Brewery, in order:<ol>
	 * <li>Brewery name</li>
	 * </ol>
	 * @author nb
	 *
	 */
	public static class ComparatorBreweryByName implements Comparator<Brewery>{

		@Override
		public int compare(Brewery arg0, Brewery arg1) {
			return arg0.getBreweryName().compareToIgnoreCase(arg1.getBreweryName());
		}
		
	}
	
	private static int compareAverageAscending(BreweryAverage arg0, BreweryAverage arg1){
		int ret;
		if(arg0.getAverage()>arg1.getAverage()){
			ret=1;
		}else if(arg0.getAverage()==arg1.getAverage()){
			ret=0;
		}else{
			ret=-1;
		}
		return ret;
	}
	
	public static class ComparatorBreweryByAverageAscending implements Comparator<BreweryAverage>{

		@Override
		public int compare(BreweryAverage arg0, BreweryAverage arg1) {
			return compareAverageAscending(arg0, arg1);
		}
		
	}
	
	private static int compareAverageDescending(BreweryAverage arg0, BreweryAverage arg1){
		int ret;
		if(arg1.getAverage()>arg0.getAverage()){
			ret = 1;
		}else if(arg1.getAverage()==arg0.getAverage()){
			ret = 0;
		}else{
			ret = -1;
		}
		return ret;
	}
	
	public static class ComparatorBreweryByAverageDescending implements Comparator<BreweryAverage>{

		@Override
		public int compare(BreweryAverage arg0, BreweryAverage arg1) {
//			int ret;
//			if(arg0.getAverage()>arg1.getAverage()){
//				ret=-1;
//			}else if(arg0.getAverage()==arg1.getAverage()){
//				ret=0;
//			}else{
//				ret=1;
//			}
			return compareAverageDescending(arg0, arg1);
		}
		
	}
	
	public static class ComparatorBreweryByCountryThenAverageAscending implements Comparator<BreweryAverage>{

		@Override
		public int compare(BreweryAverage arg0, BreweryAverage arg1) {
			int ret;
			if(arg0.getCountry().equals(arg1.getCountry())){
				if(arg0.getAverage()>arg1.getAverage()){
					ret=1;
				}else if(arg0.getAverage()==arg1.getAverage()){
					ret=0;
				}else{
					ret=-1;
				}
			}
			else{
				ret=arg0.getCountry().compareToIgnoreCase(arg1.getCountry());
			}
			return ret;
		}
		
	}
	
	
	public static class ComparatorBreweryByCountryThenAverageDescending implements Comparator<BreweryAverage>{

		@Override
		public int compare(BreweryAverage arg0, BreweryAverage arg1) {
			int ret;
			if(arg0.getCountry().equals(arg1.getCountry())){
				if(arg0.getAverage()>arg1.getAverage()){
					ret=-1;
				}else if(arg0.getAverage()==arg1.getAverage()){
					ret=0;
				}else{
					ret=1;
				}
			}
			else{
				ret=arg0.getCountry().compareToIgnoreCase(arg1.getCountry());
			}
			return ret;
		}
		
	}
	
	private static int compareBreweryByName(Brewery arg0, Brewery arg1){
		return arg0.getBreweryName().compareTo(arg1.getBreweryName());
	}
	
	
	public static class ComparatorBreweryForBinarySearch implements Comparator<Brewery>{

		@Override
		public int compare(Brewery arg0, Brewery arg1) {
			int ret;
			if (arg0.getBreweryName().equals(arg1.getBreweryName())){
				ret = 0;
			}
			else{
//				if (arg0.getCountry().equalsIgnoreCase(arg1.getCountry())){
//					ret = arg0.getName().compareToIgnoreCase(arg1.getName());
//				}
//				else{
//					ret = arg0.getCountry().compareTo(arg1.getCountry());
//				}
				return compareBreweryByCountryName(arg0, arg1);
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBreweryForBinarySearchConverter implements Comparator<Brewery>{

		@Override
		public int compare(Brewery arg0, Brewery arg1) {
			int ret;
			if (arg0.equals(arg1)){
				ret = 0;
			}
			else{
//				if (arg0.getCountry().equalsIgnoreCase(arg1.getCountry())){
//					ret = arg0.getName().compareToIgnoreCase(arg1.getName());
//				}
//				else{
//					ret = arg0.getCountry().compareTo(arg1.getCountry());
//				}
//				return compareBreweryByCountryName(arg0, arg1);
				return compareBreweryByName(arg0, arg1);
			}
			return ret;
		}
		
	}
	
	public static class ComparatorBreweryAverageForBinarySearch implements Comparator<BreweryAverage>{

		@Override
		public int compare(BreweryAverage arg0, BreweryAverage arg1) {
			int ret;
			if (arg0.equals(arg1)){
				ret = 0;
			}
			else{
//				if (arg0.getCountry().equalsIgnoreCase(arg1.getCountry())){
//					ret = arg0.getName().compareToIgnoreCase(arg1.getName());
//				}
//				else{
//					ret = arg0.getCountry().compareTo(arg1.getCountry());
//				}
				return compareBreweryByCountryName(arg0, arg1);
			}
			return ret;
		}
		
	}
	
	
	public static class ComparatorBeerForBinarySearch implements Comparator<Beer>{

		@Override
		public int compare(Beer o1, Beer o2) {
			int ret;
			if(o1.equals(o2)){
				ret = 0;
			}else{
				ret = compareBeerByNameStyleBrewery(o1, o2);
			}
			return ret;
		}
		
	}
	
	
	public static class ComparatorStyleForBinarySearch implements Comparator<Style>{

		@Override
		public int compare(Style o1, Style o2) {
			int ret;
			if(o1.equals(o2)){
				ret = 0;
			}else{
//				ret = compareStyleByFermentationComplete(o1, o2);
				ret = compareStyleByFermentationComplete(o1, o2);
			}
			return ret;
		}
		
	}
	
	
//	public static void main(String args[]){
//		LinkedList<Brewery> breweries=new LinkedList<Brewery>();
//		Brewery b=new Brewery("Carlow Brewing", "Carlow", "Ireland", "a", "b");
//		Brewery b2=new Brewery("Guinness", "Dublin", "Ireland", "a", "b");
//		Brewery b3=new Brewery("Porterhouse", "Dublin", "Ireland", "a", "b");
//		Brewery b4=new Brewery("Elav", "Bergamo", "Italy", "a", "b");
//		Brewery b5=new Brewery("Valcavallina", "Bergamo", "Italy", "a", "b");
//		breweries.add(b);
//		breweries.add(b2);
//		breweries.add(b3);
//		breweries.add(b4);
//		breweries.add(b5);
//		Collections.sort(breweries, new ComparatorBreweryGeographycally());
//		Utils.printBreweries(breweries, System.out);
//		
//	}

}
