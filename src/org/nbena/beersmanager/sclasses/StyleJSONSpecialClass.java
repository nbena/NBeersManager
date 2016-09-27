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
package org.nbena.beersmanager.sclasses;



/**
 * A special version of {@link #org.nbena.beersmanager.coreclasses.Style}. It contains the fermentation as string value, because the JSON exporter
 * does not know enums.
 * @author nbena
 * @see org.nbena.beersmanager.coreclasses.Style
 *
 */
public class StyleJSONSpecialClass {
	
	
		
		private String styleMainName;
		private String styleSubCategory;
		private String styleDescription;
		private String styleCountryOrigin;
		private String fermentation;
		
		
		public StyleJSONSpecialClass(String styleMainName, String styleSubCategory, String description, String styleCountryOrigin, String fermentation) {
			this.styleMainName = styleMainName;
			this.styleSubCategory = styleSubCategory;
			this.styleDescription = description;
			this.styleCountryOrigin = styleCountryOrigin;
			this.fermentation = fermentation;
		}
		

		
		/**
		 * @return the fermentation
		 */
		public String getFermentation() {
			return fermentation;
		}


		/**
		 * @param fermentation the fermentation to set
		 */
		
		//@XmlElement
		public void setFermentation(String fermentation) {
			this.fermentation = fermentation;
		}





		public StyleJSONSpecialClass() {
			// TODO Auto-generated constructor stub
		}



		

		
		/**
		 * @return the description
		 */
		public String getStyleDescription() {
			return styleDescription;
		}
		/**
		 * @param styleDescription the description to set
		 */
		//@XmlElement
		public void setStyleDescription(String styleDescription) {
			this.styleDescription = styleDescription;
		}


		/**
		 * @return the styleMainName
		 */
		public String getStyleMainName() {
			return styleMainName;
		}


		/**
		 * @param styleMainName the styleMainName to set
		 */
		public void setStyleMainName(String styleMainName) {
			this.styleMainName = styleMainName;
		}


		/**
		 * @return the styleSubCategory
		 */
		public String getStyleSubCategory() {
			return styleSubCategory;
		}


		/**
		 * @param styleSubCategory the styleSubCategory to set
		 */
		public void setStyleSubCategory(String styleSubCategory) {
			this.styleSubCategory = styleSubCategory;
		}


		/**
		 * @return the styleCountryOrigin
		 */
		public String getStyleCountryOrigin() {
			return styleCountryOrigin;
		}


		/**
		 * @return the styleCountryOrigin
		 */
		public void setStyleCountryOrigin(String countryOrigin) {
			this.styleCountryOrigin = countryOrigin;
		}

/*

		@Override
		public int compareTo(Style o) {
			int ret;
			if(o.getStyleMainName().equals(styleMainName)){
				if(o.getStyleSubCategory().equals(o.styleSubCategory)){
					ret=0;
				}
				else{
					ret=getStyleSubCategory().compareTo(o.styleSubCategory);
				}
			}
			else{
				ret=getStyleMainName().compareTo(o.styleMainName);
			}
			return ret;
		}


*/



		
		

	



	

}
