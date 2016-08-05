package org.nbena.beersmanager.json;




public class StyleJSONSpecialClass {
	
	
		
		private String styleMainName;
		private String styleSubCategory;
		private String description;
		private String styleCountryOrigin;
		private String fermentation;
		
		
		public StyleJSONSpecialClass(String styleMainName, String styleSubCategory, String description, String styleCountryOrigin, String fermentation) {
			this.styleMainName = styleMainName;
			this.styleSubCategory = styleSubCategory;
			this.description = description;
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
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		//@XmlElement
		public void setDescription(String description) {
			this.description = description;
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
