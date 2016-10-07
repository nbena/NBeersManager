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
import org.nbena.beersmanager.sclasses.BreweryAverage;
/**
 * This class contains a set of static subclasses. Each subclass implements the Comparator interface for Beer, Brewery, Style class.
 * This classes are used by the QueryRunner class to perform filtering, sorting and searching on lists of these objects.
 * @author nbena
 * @see java.lang.Comparator
 * @see org.nbena.beersmanager.coreclasses.Beer
 * @see org.nbena.beersmanager.coreclasses.Brewery
 * @see org.nbena.beersmanager.coreclasses.Style
 * @see org.nbena.beersamanager.query.QueryRunner
 *
 */
public class Comparators {
	
	/**
	 * This class contains comparator for the {@link org.nbena.beersmanager.coreclasses.Style} class.
	 * @author nbena
	 *
	 */
	static class StyleComparator{

		/**
		 * Compares two styles by looking only to the main style name. 
		 * Comparison is lexically, made using the string comparators.
		 * @author nbena
		 *
		 */
		public static class ComparatorStyleOnlyMainCategory implements Comparator<Style>{
		
			@Override
			public int compare(Style arg0, Style arg1) {
				return arg0.getStyleMainName().compareToIgnoreCase(arg1.getStyleMainName());
			}
			
		}

		/**
		 * Comparator class that is used to compare styles.
		 * The algorithm checks in order:<ol>
		 * <li> Style name</li>
		 * <li> Style subcategory</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorStyleMainCategorySubCategory implements Comparator<Style>{ //not work on binary search.
		
			@Override
			public int compare(Style arg0, Style arg1) {
				return styleCompareToWithoutFermentation(arg0, arg1);
			}
		}

		/**
		 * Comparator class that is used to compare styles.
		 * The algorithm checks in order:<ol>
		 * <li> Style origin country </li>
		 * <li> Fermentation type </li>
		 * <li> Style name</li>
		 * <li> Style subcategory</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorStyleCountryFermentation implements Comparator<Style>{
		
			@Override
			public int compare(Style o1, Style o2) {
				return styleCompareToCountryFermentation(o1, o2);
			}
			
		}

		/**
		 * Comparator class that is used to compare styles.
		 * The algorithm checks in order:<ol>
		 * <li> Fermentation type </li>
		 * <li> Style origin country </li>
		 * <li> Style name</li>
		 * <li> Style subcategory</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorStyleFermentationCountry implements Comparator<Style>{
		
			@Override
			public int compare(Style o1, Style o2) {
				return styleCompareToFermentationCountry(o1, o2);
			}
			
		}
		
		/**
		 * Style comparators are keeped in the easistes way, because nested calls are not frequent.
		 */

		/**
		 * Comparator class that is used to compare styles.
		 * The algorithm checks in order:<ol>
		 * <li> Fermentation type </li>
		 * <li> Style name</li>
		 * <li> Style subcategory</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorStyleByFermentationCategorySubcategory implements Comparator<Style>{
		
				@Override
				public int compare(Style arg0, Style arg1) {
					return styleCompareToStartingFromFermentation(arg0, arg1);
				}
				
			}
		


		 
		private static int styleCompareToWithFermentation(Style o1, Style o2){
			int ret;
			ret = StyleComparator.styleCompareToWithoutFermentation(o1, o2);
			if(ret==0){
				ret = o1.getFermentation().compareTo(o2.getFermentation());
			}
			return ret;
		}

		private static int styleCompareToWithoutFermentation(Style o1, Style o2){
			int ret;
			ret = o1.getStyleMainName().compareToIgnoreCase(o2.getStyleMainName());
			if(ret==0){
				ret = o1.getStyleSubCategory().compareToIgnoreCase(o2.getStyleSubCategory());
			}
			return ret;
			
			
		}

		/**
		 * It changes the order, the first thing we check is fermentation insted of the name
		 * @param o1
		 * @param o2
		 * @return
		 */
		private static int styleCompareToStartingFromFermentation(Style o1, Style o2){
			int ret;
			ret = o1.getFermentation().compareTo(o2.getFermentation());
			if(ret == 0){
				ret = styleCompareToWithoutFermentation(o1, o2);
			}
			return ret;
		}

		private static int styleCompareToCountryFermentation(Style o1, Style o2){
			int ret = o1.getStyleCountryOrigin().compareToIgnoreCase(o2.getStyleCountryOrigin());
			if(ret ==0){
				ret = styleCompareToStartingFromFermentation(o1, o2);
			}
			return ret;
		}

		private static int styleCompareToFermentationCountry(Style o1, Style o2){
			int ret = o1.getFermentation().compareTo(o2.getFermentation());
			if(ret ==0){
				ret = o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
			}
			if(ret == 0){
				ret = styleCompareToWithoutFermentation(o1, o2);
			}
			return ret;
		}
		
		static boolean styleEqual(Style o1, Style o2){
			return styleCompareToCountryFermentation(o1, o2)==0 ? true : false;
		}
		
		static boolean fastEqual(Style o1, Style o2){
			return styleCompareToWithoutFermentation(o1, o2) == 0 ? true : false;
		}

//		private static int styleCompareToWithCountry(Style o1, Style o2){
//			int ret;
//			ret = styleCompareToWithFermentation(o1, o2);
//			if(ret==0){
//				ret = o1.getStyleCountryOrigin().compareTo(o2.getStyleCountryOrigin());
//			}
//			return ret;
//		}
		
		static int styleCompareOnlyMain(Style o1, Style o2){
			return o1.getStyleMainName().compareToIgnoreCase(o2.getStyleMainName());
		}
		
		static boolean styleOnlyMainEqual(Style o1, Style o2){
			return styleCompareOnlyMain(o1, o2)==0? true : false;
		}
		
	}
	
	
	
	
	
	
//	/**
//	 * Comparator class that is used to compare styles.
//	 * The algorithm check in order:<ol>
//	 * <li> Style fermentation</li>
//	 * <li> Style country origin</li>
//	 * <li> Style name</li>
//	 * <li> Style subcategory</li>
//	 * </ol>
//	 * @author nb
//	 *
//	 */
//	public static class ComparatorStyleFermentationComplete implements Comparator<Style>{
//
//		@Override
//		public int compare(Style o1, Style o2) {
//			return styleCompareToWithFermentation(o1, o2);
//		}
//		
//	}
	
	/**
	 * This class contains comparator for the {@link org.nbena.beersmanager.coreclasses.Beer} class.
	 * Each beer comparison, if after all the comparison they are still the same, a comparsion between the
	 * name is performed.
	 * @author nbena
	 *
	 */
	static class BeerComparator{
		
		private static int beerCompareByName(Beer o1, Beer o2){
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
		
		private static int beerCompareByBreweryStyle(Beer o1, Beer o2){
			int ret;
			ret = BreweryComparator.breweryCompareByCountryName(o1.getBrewery(), o2.getBrewery());
			if(ret == 0){
				ret = StyleComparator.styleCompareToWithoutFermentation(o1.getStyle(), o2.getStyle());
			}
			return ret;
		}
		
		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks in order:<ol>
		 * <li> Beer's brewery country </li>
		 * <li> Beer's brewery name</li>
		 * <li> Beer's style name</li>
		 * <li> Beer's style subcategory</li>
		 * <li> Beer name </li>
		 * </ol>
		 * @author nbena
		 *
		 */
		private static int beerCompareByBreweryStyleFast(Beer o1, Beer o2){
			int ret;
			ret = BreweryComparator.breweryCompareByCountryName(o1.getBrewery(), o2.getBrewery());
			if(ret == 0){
				ret = StyleComparator.styleCompareToWithoutFermentation(o1.getStyle(), o2.getStyle());
			}
			return ret;
		}
		
		private static int beerCompareByFermentationCountryOfStyleBrewery(Beer o1, Beer o2){
			int ret;
			ret = StyleComparator.styleCompareToFermentationCountry(o1.getStyle(), o2.getStyle());
			if(ret == 0){
				ret = BreweryComparator.breweryCompareByName(o1.getBrewery(), o2.getBrewery());
			}
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByFermentationCountryOfBrewery(Beer o1, Beer o2){
			int ret;
			ret = StyleComparator.styleCompareToWithFermentation(o1.getStyle(), o2.getStyle());
			if(ret == 0){
				ret = BreweryComparator.breweryCompareByCountryName(o1.getBrewery(), o2.getBrewery());
			}
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByABVAscending(Beer o1, Beer o2){
			int ret;
			ret = (o1.getAlcool()>o2.getAlcool()? 1: (o1.getAlcool()==o2.getAlcool())? 0 : -1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret; 
		}
		
		private static int beerCompareByABVDescending(Beer o1, Beer o2){
			int ret;
			ret = (o1.getAlcool()>o2.getAlcool()? -1: (o1.getAlcool()==o2.getAlcool())? 0 : 1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByPriceAscending(Beer o1, Beer o2){
			int ret;
			ret = (o1.getPrice()>o2.getPrice()? 1: (o1.getPrice()==o2.getPrice())? 0 : -1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByPriceDescending(Beer o1, Beer o2){
			int ret;
			ret =(o1.getPrice()>o2.getPrice()? -1: (o1.getPrice()==o2.getPrice())? 0 : 1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByMarkAscending(Beer o1, Beer o2){
			int ret;;
			ret = (o1.getMark()>o2.getMark()? 1 : (o1.getMark()==o2.getMark())? 0 : -1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByMarkDescending(Beer o1, Beer o2){
			int ret;
			ret = (o1.getMark()>o2.getMark()? -1 : (o1.getMark()==o2.getMark())? 0 : 1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByStarAscending(Beer o1, Beer o2){
			int ret;
			ret =  (o1.getNumberOfStars()>o2.getNumberOfStars()? 1 : (o1.getNumberOfStars()==o2.getNumberOfStars())? 0 : -1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByStarDescending(Beer o1, Beer o2){
			int ret;
			ret = (o1.getNumberOfStars()>o2.getNumberOfStars()? -1 : (o1.getNumberOfStars()==o2.getNumberOfStars())? 0 : 1);
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
			return ret;
		}
		
		
		private static int beerCompareByCountryMark(Beer o1, Beer o2){
			int ret;
			ret = o1.getBrewery().getCountry().compareTo(o2.getBrewery().getCountry());
//			if( ret == 0){
//				ret = compareBeerByMarkAscending(o1, o2);
//			}
			return ret;
		}
		
		private static int beerCompareByMarkStarAscending(Beer o1, Beer o2){
			int ret;
			ret = beerCompareByMarkAscending(o1, o2);
			if(ret == 0){
				ret = beerCompareByStarAscending(o1, o2);
			}
			return ret;
		}
		
		private static int beerCompareByMarkStarDescending(Beer o1, Beer o2){
			int ret;
			ret = beerCompareByMarkDescending(o1, o2);
			if(ret == 0){
				ret = beerCompareByStarDescending(o1, o2);
			}
			return ret;
		}
		
		private static int beerCompareByStarMarkAscending(Beer o1, Beer o2){
			int ret;
			ret = beerCompareByStarAscending(o1, o2);
			if(ret == 0){
				ret = beerCompareByMarkAscending(o1, o2);
			}
			return ret;
		}
		
		private static int beerCompareByStarMarkDescending(Beer o1, Beer o2){
			int ret;
			ret = beerCompareByStarDescending(o1, o2);
			if(ret == 0){
				ret = beerCompareByMarkDescending(o1, o2);
			}
			return ret;
		}
		
		private static int beerCompareByCountryBreweryName(Beer o1, Beer o2){
			int ret;
			ret = BreweryComparator.breweryCompareByCountryName(o1.getBrewery(), o2.getBrewery());
			if(ret==0){
				ret = beerCompareByName(o1, o2);
			}
			return ret;
		}
		
//		private static int  beerCompareByNameStyleBrewery(Beer o1, Beer o2){
////			int rBrewery = BreweryComparator.breweryCompareTo(o1.getBrewery(), o2.getBrewery());
////			int ret, rStyle, rName;
////			if (rBrewery !=0){
////				ret=rBrewery;
////			}else{
////				rStyle= StyleComparator.styleCompareToWithFermentation(o1.getStyle(), o2.getStyle());
////				if(rStyle!=0){
////					ret=rStyle;
////				}
////				else{
////					rName=o1.getName().compareToIgnoreCase(o2.getName());
////					ret=rName;
////				}
////			}
////			return ret;
//			int ret;
//			ret = BreweryComparator.breweryCompareTo(o1.getBrewery(), o2.getBrewery());
//			if(ret == 0){
//				ret = StyleComparator.styleCompareToWithoutFermentation(o1.getStyle(), o2.getStyle());
//			}
//			if(ret == 0){
//				ret = beerCompareByName(o1, o2);
//			}
//			return ret;
//		}


		/**
			 * Comparator class that is used to compare beers.
			 * The algorithm checks in order:<ol>
			 * <li> Beer's brewery country </li>
			 * <li> Beer's brewery name</li>
			 * <li> Beer's style fermentation </li>
			 * <li> Beer's style name</li>
			 * <li> Beer's style subcategory</li>
			 * <li> Beer name </li>
			 * </ol>
			 * @author nbena
			 *
			 */
			public static class ComparatorBeerByCountryBreweryStyleName implements Comparator<Beer>{
		
				@Override
				public int compare(Beer arg0, Beer arg1) {
		//			int ret;
		//			if(arg0.getBrewery().getCountry().equalsIgnoreCase(arg1.getBrewery().getCountry())){
		//				if(arg0.getBrewery().getTown().equalsIgnoreCase(arg1.getBrewery().getTown())){
		//					if(arg0.getBrewery().getBreweryName().equalsIgnoreCase(arg1.getBrewery().getBreweryName())){
		//						if(arg0.getStyle().getFermentation()==arg1.getStyle().getFermentation()){
		//							if(arg0.getStyle().getStyleMainName().equalsIgnoreCase(arg1.getStyle().getStyleMainName())){
		//								if(arg0.getStyle().getStyleSubCategory().equalsIgnoreCase(arg1.getStyle().getStyleSubCategory())){
		//									ret=arg0.getName().compareToIgnoreCase(arg1.getName());
		//								}
		//								else{
		//									ret=arg0.getStyle().getStyleSubCategory().compareToIgnoreCase(arg1.getStyle().getStyleSubCategory());
		//								}
		//							}
		//							else{
		//								ret=arg0.getStyle().getStyleMainName().compareToIgnoreCase(arg1.getStyle().getStyleMainName());
		//							}
		//						}
		//						else{
		//							ret=arg0.getStyle().getFermentation().toString().compareToIgnoreCase(arg1.getStyle().getFermentation().toString());
		//						}
		//					}
		//					else{
		//						ret=arg0.getBrewery().getBreweryName().compareToIgnoreCase(arg1.getBrewery().getBreweryName());
		//					}
		//				}
		//				else{
		//					ret=arg0.getBrewery().getTown().compareToIgnoreCase(arg1.getBrewery().getTown());
		//				}
		//			}
		//			else{
		//				ret=arg0.getBrewery().getCountry().compareToIgnoreCase(arg1.getBrewery().getCountry());
		//			}
					
		//			ret = BreweryComparator.breweryCompareTo(arg0.getBrewery(), arg1.getBrewery());
		//			if(ret == 0){
		//				ret = StyleComparator.styleCompareToCountryFermentationName(arg0.getStyle(), arg1.getStyle());
		//			}
		//			if(ret == 0){
		//				ret = compareBeerByName(arg0, arg1);
		//			}
		//			return ret;
					int ret;
					ret= beerCompareByBreweryStyle(arg0, arg1);
					if(ret==0){
						ret=beerCompareByName(arg0, arg1);
					}
					return ret;
				}
				
			}

		/**
			 * Comparator class that is used to compare beers.
			 * The algorithm checks in order:<ol>
			 * <li> Beer's style fermentation </li>
			 * <li> Beer's style country origin</li>
			 * <li> Beer's style name</li>
			 * <li> Beer's style subcategory</li>
			 * <li> Beer's brewery name</li>
			 * <li> Beer name </li>
			 * </ol>
			 * @author nbena
			 *
			 */
			public static class ComparatorBeerByFermentationCountryOfStyleBreweryName implements Comparator<Beer>{
		
				@Override
				public int compare(Beer o1, Beer o2) {
		//			int ret;
		//			if(o1.getStyle().getFermentation().toString().equalsIgnoreCase(o2.getStyle().getFermentation().toString())){
		//				if(o1.getStyle().getStyleCountryOrigin().equalsIgnoreCase(o2.getStyle().getStyleCountryOrigin())){
		//					if(o1.getStyle().getStyleMainName().equalsIgnoreCase(o2.getStyle().getStyleMainName())){
		//						if(o1.getStyle().getStyleSubCategory().equalsIgnoreCase(o2.getStyle().getStyleSubCategory())){
		//							if(o1.getBrewery().getTown().equalsIgnoreCase(o2.getBrewery().getTown())){
		//								if(o1.getBrewery().getBreweryName().equalsIgnoreCase(o2.getBrewery().getBreweryName())){
		//									ret=o1.getName().compareToIgnoreCase(o2.getName());
		//								}
		//								else{
		//									ret=o1.getBrewery().getBreweryName().compareToIgnoreCase(o2.getBrewery().getBreweryName());
		//								}
		//							}
		//							else{
		//								ret=o1.getBrewery().getTown().compareToIgnoreCase(o2.getBrewery().getTown());
		//							}
		//						}
		//						else{
		//							ret=o1.getStyle().getStyleSubCategory().compareToIgnoreCase(o2.getStyle().getStyleSubCategory());
		//						}
		//					}
		//					else{
		//						ret=o1.getStyle().getStyleMainName().compareToIgnoreCase(o2.getStyle().getStyleMainName());
		//					}
		//				}
		//				else{
		//					ret=o1.getStyle().getStyleCountryOrigin().compareToIgnoreCase(o2.getStyle().getStyleCountryOrigin());
		//				}
		//			}
		//			else{
		//				ret=o1.getStyle().getFermentation().toString().compareToIgnoreCase(o2.getStyle().getFermentation().toString());
		//			}
					
		//			ret = StyleComparator.styleCompareToFermentationCountryName(o1.getStyle(), o2.getStyle());
		//			if(ret == 0){
		//				ret = BreweryComparator.breweryCompareTo(o1.getBrewery(), o2.getBrewery());
		//			}
		//			if(ret == 0){
		//				ret = compareBeerByName(o1, o2);
		//			}
		//			return ret;
					int ret;
					ret= beerCompareByFermentationCountryOfStyleBrewery(o1, o2);
					if(ret==0){
						ret=beerCompareByName(o1, o2);
					}
					return ret;
				}
				
			}

		/**
			 * Comparator class that is used to compare beers.
			 * The algorithm checks in order:<ol>
			 * <li> Beer's style fermentation </li>
			 * <li> Beer's style name</li>
			 * <li> Beer's style subcategory</li>
			 * <li> Beer's brewery country </li>
			 * <li> Beer's brewery name</li>
			 * <li> Beer name </li>
			 * </ol>
			 * @author nbena
			 *
			 */
			public static class ComparatorBeerByFermentationStyleCountryBreweryName implements Comparator<Beer>{
		
				@Override
				public int compare(Beer o1, Beer o2) {
		//			int ret;
		//			if(o1.getStyle().getFermentation().toString().equalsIgnoreCase(o2.getStyle().getFermentation().toString())){
		//				if(o1.getStyle().getStyleMainName().equalsIgnoreCase(o2.getStyle().getStyleMainName())){
		//					if(o1.getStyle().getStyleSubCategory().equalsIgnoreCase(o2.getStyle().getStyleSubCategory())){
		//						if(o1.getBrewery().getCountry().equalsIgnoreCase(o2.getBrewery().getCountry())){
		//							if(o1.getBrewery().getTown().equalsIgnoreCase(o2.getBrewery().getTown())){
		//								if(o1.getBrewery().getBreweryName().equalsIgnoreCase(o2.getBrewery().getBreweryName())){
		//									ret=o1.getName().compareToIgnoreCase(o2.getName());
		//								}
		//								else{
		//									ret=o1.getBrewery().getBreweryName().compareToIgnoreCase(o2.getBrewery().getBreweryName());
		//								}
		//							}
		//							else{
		//								ret=o1.getBrewery().getTown().compareToIgnoreCase(o2.getBrewery().getTown());
		//							}
		//						}
		//						else{
		//							ret=o1.getBrewery().getCountry().compareToIgnoreCase(o2.getBrewery().getCountry());
		//						}
		//					}
		//					else{
		//						ret=o1.getStyle().getStyleSubCategory().compareToIgnoreCase(o2.getStyle().getStyleSubCategory());
		//					}
		//				}
		//				else{
		//					ret=o1.getStyle().getStyleMainName().compareToIgnoreCase(o2.getStyle().getStyleMainName());
		//				}
		//			}
		//			else{
		//				ret=o1.getStyle().getFermentation().toString().compareToIgnoreCase(o2.getStyle().getFermentation().toString());
		//			}
					
					
		//			ret = StyleComparator.styleCompareToWithFermentation(o1.getStyle(), o2.getStyle());
		//			if(ret == 0){
		//				ret = BreweryComparator.breweryCompareTo(o1.getBrewery(), o2.getBrewery());
		//			}
		//			if(ret == 0){
		//				ret = compareBeerByName(o1, o2);
		//			}
		//			return ret;
					int ret;
					ret= beerCompareByFermentationCountryOfBrewery(o1, o2);
					if(ret==0){
						ret=beerCompareByName(o1, o2);
					}
					return ret;
				}
				
			}

//			/**
//			 * Comparator class that is used to compare beers.
//			 * The algorithm checks in order:<ol>
//			 * <li> Beer's brewery country
//			 * <li> Beer's brewery town</li>
//			 * <li> Beer's brewery name</li>
//			 * <li> Beer's style name</li>
//			 * <li> Beer's style subcategory</li>
//			 * <li> Beer name </li>
//			 * </ol>
//			 * Please note that this search is considered enough to perform a binary search.
//			 * @author nbena
//			 *
//			 */
//		public static class ComparatorBeerByNameStyleBrewery implements Comparator<Beer>{
//		
//			@Override
//			public int compare(Beer o1, Beer o2) {
//				return beerCompareByNameStyleBrewery(o1, o2);
//			}
//			
//		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks the ABV in ascending order, so it is basically a number comparison.
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerABVAscending implements Comparator<Beer> {
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByABVAscending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}
		
		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks the ABV in decending order, so it is basically a number comparison.
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerABVDescending implements Comparator<Beer> {
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret= beerCompareByABVDescending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks the price in ascending order, so it is basically a number comparison.
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerPriceAscending implements Comparator<Beer> {
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByPriceAscending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks the ABV in decending order, so it is basically a number comparison.
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerPriceDescending implements Comparator<Beer> {
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByPriceDescending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
				}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm lexically checks the name of the beer.
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByName implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByName(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class, that checks: <ol>
		 * <li>beer's brewery country</li>
		 * <li>beer mark </li>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByCountryMark implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByCountryMark(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks in ascending order:<ol>
		 * <li>Beer mark</li>
		 * <li>Beer stars</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByMarkStarAscending implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByMarkStarAscending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks in ascending order:<ol>
		 * <li>Beer stars</li>
		 * <li>Beer mark</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByStarMarkAscending implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByStarMarkAscending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks in descending order:<ol>
		 * <li>Beer mark</li>
		 * <li>Beer stars</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByMarkStarDescending implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByMarkStarDescending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator class that is used to compare beers.
		 * The algorithm checks in descending order:<ol>
		 * <li>Beer stars</li>
		 * <li>Beer mark</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByStarMarkDescending implements Comparator<Beer>{
		
			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret=beerCompareByStarMarkDescending(arg0, arg1);
				if(ret==0){
					ret=beerCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}
		
		/**
		 * Comparator class that is used to compare beers
		 * This algorithm checks:<ol>
		 * <li>Brewery country</li>
		 * <li>Brewery name</li>
		 * <li>Beer name</li>
		 * @author nbena
		 *
		 */
		public static class ComparatorBeerByCountryBreweryName implements Comparator<Beer>{

			@Override
			public int compare(Beer arg0, Beer arg1) {
				int ret;
				ret = beerCompareByCountryBreweryName(arg0, arg1);
				//no need to check the name because is a must of the function
				return ret;
			}
			
		}
		
	}
	
	
//	private static int compareStyleByStyleMainStyleSub(Style arg0, Style arg1){
//		int ret;
//		if(arg0.getStyleMainName().equalsIgnoreCase(arg1.getStyleMainName())){
//			ret=0;
//		}
//		else{
//			ret=arg0.getStyleSubCategory().compareToIgnoreCase(arg1.getStyleSubCategory());
//		}
//		return ret;	
//	}
	

	
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
	

	
	
	
//	private static int compareStyleByFermentationComplete(Style o1, Style o2){
//		int ret;
//		if(o1.getFermentation()==o2.getFermentation()){
//			if(o1.getStyleCountryOrigin().equalsIgnoreCase(o2.getStyleCountryOrigin())){
//				if(o1.getStyleMainName().equalsIgnoreCase(o2.getStyleMainName())){
//						ret=o1.getStyleSubCategory().compareToIgnoreCase(o2.getStyleSubCategory());				
//				}
//				else{
//					ret=o1.getStyleMainName().compareToIgnoreCase(o2.getStyleMainName());
//				}
//			}
//			else{
//				ret=o1.getStyleCountryOrigin().compareToIgnoreCase(o2.getStyleCountryOrigin());
//			}
//		}
//		else{
//			ret=o1.getFermentation().toString().compareToIgnoreCase(o2.getFermentation().toString());
//		}
//		return ret;
//	}
	

	
	
	
//	/**
//	 * Comparator class that is used to compare styles.
//	 * The algorithm check in order:<ol>
//	 * <li> Style country origin</li>
//	 * <li> Style fermentation</li>
//	 * <li> Style name</li>
//	 * <li> Style subcategory</li>
//	 * </ol>
//	 * @author nb
//	 *
//	 */
//	public static class ComparatorStyleCountryComplete implements Comparator<Style>{
//
//		@Override
//		public int compare(Style o1, Style o2) {
//			return styleCompareToWithCountry(o1, o2);
//		}
//		
//	}
	

	
	/**
	 * This class contains comparator for the {@link org.nbena.beersmanager.coreclasses.Brewery} class.
	 * @author nbena
	 *
	 */
	static class BreweryComparator{

/*
 * As beer comparator, every last comparisom check the name.
 */
		
		static int breweryCompareByName(Brewery o1, Brewery o2){
			return o1.getBreweryName().compareToIgnoreCase(o2.getBreweryName());
		}
		
		/**
		 * Compares lexically two breweries, based on the brewery name, the town, and
		 * the country. The comparison stops when one of that fields are different.
		 * @param o1	the first brewery.
		 * @param o2	the second brewery.
		 * @return	0 if equal, a number more than 0 if o1 is more then o2, a number less than 0 otherwhise.
		 */
		private static int breweryCompareTo(Brewery o1, Brewery o2){
			int ret;
			ret = o1.getCountry().compareTo(o2.getCountry());
			if(ret==0){
				ret = o1.getTown().compareTo(o2.getTown());
			}
			if(ret==0){
				ret = o1.getBreweryName().compareTo(o2.getBreweryName());
			}
			return ret;
		}

		static int breweryCompareByCountryName(Brewery o1, Brewery o2){
			int ret;
			ret = o1.getCountry().compareTo(o2.getCountry());
			//here we left the name comparison because it is a part of.
			if(ret == 0){
				ret = breweryCompareByName(o1, o2);
			}
			return ret;
		}
		
		
		private static int breweryCompareByCountryAverageAscending(BreweryAverage o1, BreweryAverage o2){
			int ret;
			ret = o1.getCountry().compareTo(o2.getCountry());
			if(ret == 0){
//				if(o1.getAverage()>o2.getAverage()){
//					ret=1;
//				}else if(o1.getAverage()==o2.getAverage()){
//					ret=0;
//				}else{
//					ret=-1;
//				}
				ret = breweryCompareByAverageAscending(o1, o2);
			}


			return ret;
		}
		
		private static int breweryCompareByCountryAverageDescending(BreweryAverage o1, BreweryAverage o2){
			int ret;
			ret = o1.getCountry().compareTo(o2.getCountry());
			if(ret == 0){
//				if(o1.getAverage()>o2.getAverage()){
//					ret=-1;
//				}else if(o1.getAverage()==o2.getAverage()){
//					ret=0;
//				}else{
//					ret=1;
//				}
				ret = breweryCompareByAverageDescending(o1, o2);
			}

			return ret;
		}
		
		private static int breweryCompareByAverageAscending(BreweryAverage o1, BreweryAverage o2){
			int ret;
//			if(o1.getAverage()>o2.getAverage()){
//				ret=1;
//			}else if(o1.getAverage()==o2.getAverage()){
//				ret=0;
//			}else{
//				ret=-1;
//			}
			ret = (o1.getAverage()>o2.getAverage() ? 1 : (o1.getAverage()==o2.getAverage()? 0 : -1));
//			if(ret == 0){
//				ret = breweryCompareByName(o1, o2);
//			}
			return ret;
		}
		
		private static int breweryCompareByAverageDescending(BreweryAverage o1, BreweryAverage o2){
			int ret;
//			if(o2.getAverage()>o1.getAverage()){
//				ret = 1;
//			}else if(o2.getAverage()==o1.getAverage()){
//				ret = 0;
//			}else{
//				ret = -1;
//			}
			
			ret = (o1.getAverage()>o2.getAverage() ? -1 : (o1.getAverage()==o2.getAverage()? 0 : 1));
			
//			if(ret == 0){
//				ret = breweryCompareByName(o1, o2);
//			}
			
			return ret;
		}
		
		/**
		 * Comparator that compares Brewery, in order:<ol>
		 * <li>Brewery country</li>
		 * <li>Brewery name</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByCountryName implements Comparator<Brewery>{
		
		@Override
		public int compare(Brewery arg0, Brewery arg1) {
			int ret;
			ret=breweryCompareByCountryName(arg0, arg1);
//			if(ret==0){
//				ret=breweryCompareByName(arg0, arg1);
//			}
			return ret;
			}
			
		}

		/**
		 * Comparator that compares Brewery by lexically compares the name.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByName implements Comparator<Brewery>{
		
			@Override
			public int compare(Brewery arg0, Brewery arg1) {
				return breweryCompareByName(arg0, arg1);
			}
			
		}

		/**
		 * Comparator that compares Brewery by its average in ascending order.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByAverageAscending implements Comparator<BreweryAverage>{
		
			@Override
			public int compare(BreweryAverage arg0, BreweryAverage arg1) {
				int ret;
				ret=breweryCompareByAverageAscending(arg0, arg1);
				if(ret==0){
					ret=breweryCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator that compares Brewery by its average in descending order.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByAverageDescending implements Comparator<BreweryAverage>{
		
				@Override
				public int compare(BreweryAverage arg0, BreweryAverage arg1) {
					int ret;
					ret=breweryCompareByAverageDescending(arg0, arg1);
					if(ret==0){
						ret=breweryCompareByName(arg0, arg1);
					}
					return ret;
				}
				
			}

		/**
		 * Comparator that compares Brewery. It checks in ascending order:<ol>
		 * <li> Brewery country</li>
		 * <li>Brewery average</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByCountryThenAverageAscending implements Comparator<BreweryAverage>{
		
			@Override
			public int compare(BreweryAverage arg0, BreweryAverage arg1) {
				int ret;
				ret=breweryCompareByCountryAverageAscending(arg0, arg1);
				if(ret==0){
					ret=breweryCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}

		/**
		 * Comparator that compares Brewery. It checks in descending order:<ol>
		 * <li> Brewery country</li>
		 * <li>Brewery average</li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryByCountryThenAverageDescending implements Comparator<BreweryAverage>{
		
			@Override
			public int compare(BreweryAverage arg0, BreweryAverage arg1) {
				int ret;
				ret=breweryCompareByCountryAverageDescending(arg0, arg1);
				if(ret==0){
					ret=breweryCompareByName(arg0, arg1);
				}
				return ret;
			}
			
		}
		
		static boolean breweryEqual(Brewery o1, Brewery o2){
			return breweryCompareTo(o1, o2) ==0 ? true : false;
		}

		
//		/**
//		 * Comparator class that is used to compare breweries.
//		 * The algorithm check in order:<ol>
//		 * <li> Brewery country</li>
//		 * <li> Brewery town </li>
//		 * <li> Brewery name</li>
//		 * </ol>
//		 * @author nb
//		 *
//		 */
//		static class ComparatorBreweryGeographycally implements Comparator<Brewery>{
//
//			
//
//			@Override
//			public int compare(Brewery arg0, Brewery arg1) {
//				return breweryCompareTo(arg0, arg1);
//			}
//			
//		}
//
//
//		
	}
	
	/**
	 * This class contains comparators for binary search. Basically, they are just wrapper for other, 
	 * already used, method that you can find here.
	 * @author nbena
	 *
	 */
	static class Binary{

		/**
		 * Compares two breweries. It checks:<ol>
		 * <li> Brewery country </li>
		 * <li> Brewery name </li>
		 * </ol>
		 * It assumes that it's impossible to find two breweries with the same name in a town, so
		 * it do not check the town.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryForBinarySearch implements Comparator<Brewery>{
		
				@Override
				public int compare(Brewery arg0, Brewery arg1) {
		//			int ret;
		//			if (arg0.getBreweryName().equals(arg1.getBreweryName())){
		//				ret = 0;
		//			}
		//			else{
		//				return BreweryComparator.compareBreweryByCountryName(arg0, arg1);
		//			}
		//			return ret;
					return Comparators.BreweryComparator.breweryCompareByCountryName(arg0, arg1);	//no need to check also the town, just country and namae
				}
				
			}
		

		/**
		 * Compares two breweries by only checking the name. This is used by the importing and exporting class, where
		 * the name is enough.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryForBinarySearchConverter implements Comparator<Brewery>{
		
				@Override
				public int compare(Brewery arg0, Brewery arg1) {
		//			int ret;
		//			if (arg0.equals(arg1)){	
		//				ret = 0;
		//			}
		//			else{
		//				return BreweryComparator.compareBreweryByName(arg0, arg1);
		//			}
		//			return ret;
					//use this because the converter knows just the name
					return Comparators.BreweryComparator.breweryCompareByName(arg0, arg1);
				}
				
			}

		/**
		 * Compares two breweries. It checks:<ol>
		 * <li> Brewery country </li>
		 * <li> Brewery name </li>
		 * </ol>
		 * It assumes that it's impossible to find two breweries with the same name in a town, so
		 * it do not check the town.
		 * @author nbena
		 *
		 */
		static class ComparatorBreweryAverageForBinarySearch implements Comparator<BreweryAverage>{
		
				@Override
				public int compare(BreweryAverage arg0, BreweryAverage arg1) {
		//			int ret;
		//			if (arg0.equals(arg1)){
		//				ret = 0;
		//			}
		//			else{
		//				return BreweryComparator.compareBreweryByCountryName(arg0, arg1);
		//			}
		//			return ret;
					//sounds useless to check also the country, but it can happen to have two breweries with the same name
					return Comparators.BreweryComparator.breweryCompareByCountryName(arg0, arg1);
				}
				
			}
		

		/**
		 * Compares two beers. It checks:<ol>
		 * <li> Beer's brewery country </li>
		 * <li> Beer's brewery name</li>
		 * <li> Beer's style name</li>
		 * <li> Beer's style subcategory</li>
		 * <li> Beer name </li>
		 * </ol>
		 * @author nbena
		 *
		 */
		static class ComparatorBeerForBinarySearch implements Comparator<Beer>{
		 
		
			@Override
			public int compare(Beer o1, Beer o2) {
//				int ret;
//				if(o1.equals(o2)){
//					ret = 0;
//				}else{
					
//				}
//				return ret;
				return BeerComparator.beerCompareByBreweryStyleFast(o1, o2);
			}
			
		}
		

		
		
		

		/**
		 * Compares two styles. The algorithm checks:<ol>
		 * <li>Style name</li>
		 * <li>Style subcategory</li>
		 * </ol>
		 * @author nbena
		 * @see StyleComparator.ComparatorStyleMainCategorySubCategory
		 *
		 */
		static class ComparatorStyleForBinarySearch implements Comparator<Style>{
		
				@Override
				public int compare(Style o1, Style o2) {
//					int ret;
//					if(o1.equals(o2)){
//						ret = 0;
//					}else{
//		//				ret = compareStyleByFermentationComplete(o1, o2);
//						ret = Comparators.StyleComparator.styleCompareToWithCountry(o1, o2);
//					}
//					return ret;
					return StyleComparator.styleCompareToWithoutFermentation(o1, o2);
				}
				
			}
		
		/**
		 * A boolean comparison, things can be equal or not.
		 * The comparison is done using the same fields as a binary search.
		 * @param o1	the first beer
		 * @param o2	the second object
		 * @return	true if they are equal.
		 * @see ComparatorBeerForBinarySearch
		 */
		static boolean beerBooleanBinarySearch(Beer o1, Beer o2){
			return BeerComparator.beerCompareByBreweryStyleFast(o1, o2) == 0 ? true : false;
		}
		
		/**
		 * A boolean comparison, things can be equal or not.
		 * The comparison is done using the same fields as a binary search.
		 * @param o1	the first brewery
		 * @param o2	the second object
		 * @return	true if they are equal.
		 * @see ComparatorBreweryForBinarySearch
		 */
		static boolean breweryBooleanBinarySearch(Brewery o1, Brewery o2){
			int ret;
			boolean booleanRet = true;
			ret = Comparators.BreweryComparator.breweryCompareByCountryName(o1, o2);
			if(ret==1){
				booleanRet=false;
				//nested if so I avoid a not-necessary call.
			}
			else{
				ret = Comparators.BreweryComparator.breweryCompareByName(o1, o2);
				booleanRet= (ret==0) ? true : false;
				
			}
			return booleanRet;
		}
		
		/**
		 * A boolean comparison, things can be equal or not.
		 * The comparison is done using the same fields as a binary search.
		 * @param o1	the first brewery
		 * @param o2	the second object
		 * @return	true if they are equal.
		 * @see ComparatorBreweryAvergeForBinarySearch
		 */
		static boolean breweryAverageBooleanBinarySearch(BreweryAverage o1, BreweryAverage o2){
			int ret;
			boolean booleanRet = true;
			ret = Comparators.BreweryComparator.breweryCompareByCountryName(o1, o2);
			if(ret==1){
				booleanRet=false;
				//nested if so I avoid a not-necessary call.
			}
			else{
				ret = Comparators.BreweryComparator.breweryCompareByName(o1, o2);
				booleanRet= (ret==0) ? true : false;
				
			}
			return booleanRet;
		}
		
		/**
		 * A boolean comparison, things can be equal or not.
		 * The comparison is done using the same fields as a binary search.
		 * @param o1	the first style
		 * @param o2	the second object
		 * @return	true if they are equal.
		 * @see ComparatorStyleForBinarySearch
		 */
		static boolean styleBooleanBinarySearch(Style o1, Style o2){
			return StyleComparator.styleCompareToWithoutFermentation(o1, o2) == 0 ? true : false;
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
