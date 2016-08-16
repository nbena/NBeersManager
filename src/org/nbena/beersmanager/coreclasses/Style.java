package org.nbena.beersmanager.coreclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="style")
@XmlAccessorType(XmlAccessType.FIELD)
public class Style implements Comparable<Style>{
	
	private String styleMainName;
	private String styleSubCategory;
	private String description;
	private String styleCountryOrigin;
	private Fermentation fermentation;
	
	public Style(String styleMainName, String styleSubCategory, String description, String styleCountryOrigin, Fermentation fermentation) {
		this.styleMainName = styleMainName;
		this.styleSubCategory = styleSubCategory;
		this.description = description;
		this.styleCountryOrigin = styleCountryOrigin;
		this.fermentation = fermentation;
	}
	

	
	/**
	 * @return the fermentation
	 */
	public Fermentation getFermentation() {
		return fermentation;
	}


	/**
	 * @param fermentation the fermentation to set
	 */
	
	//@XmlElement
	public void setFermentation(Fermentation fermentation) {
		this.fermentation = fermentation;
	}





	public Style() {
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



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fermentation == null) ? 0 : fermentation.hashCode());
		result = prime * result + ((styleCountryOrigin == null) ? 0 : styleCountryOrigin.hashCode());
		result = prime * result + ((styleMainName == null) ? 0 : styleMainName.hashCode());
		result = prime * result + ((styleSubCategory == null) ? 0 : styleSubCategory.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Style other = (Style) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fermentation != other.fermentation)
			return false;
		if (styleCountryOrigin == null) {
			if (other.styleCountryOrigin != null)
				return false;
		} else if (!styleCountryOrigin.equals(other.styleCountryOrigin))
			return false;
		if (styleMainName == null) {
			if (other.styleMainName != null)
				return false;
		} else if (!styleMainName.equals(other.styleMainName))
			return false;
		if (styleSubCategory == null) {
			if (other.styleSubCategory != null)
				return false;
		} else if (!styleSubCategory.equals(other.styleSubCategory))
			return false;
		return true;
	}






	
	

}
