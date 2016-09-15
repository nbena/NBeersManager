package org.nbena.beersmanager.coreclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="brewery")
@XmlAccessorType(XmlAccessType.FIELD)
public class Brewery implements Comparable<Brewery> {


	private String breweryName;
	private String town;
	private String country;
	private String breweryDescription;
	private String website;
	private boolean isAuthenticTrappist;
	

	
	public Brewery(String breweryName, String town, String country, String breweryDescription, String website,
			boolean isAuthenticTrappist) {
		this.breweryName = breweryName;
		this.town = town;
		this.country = country;
		this.breweryDescription = breweryDescription;
		this.website = website;
		this.isAuthenticTrappist = isAuthenticTrappist;
	}
	
	
	
	public Brewery(String breweryName, String town, String country, String breweryDescription, String website) {
		this.breweryName = breweryName;
		this.town = town;
		this.country = country;
		this.breweryDescription = breweryDescription;
		this.website = website;
	}



	public Brewery() {
	}


	/**
	 * @return the name
	 */
	public String getBreweryName() {
		return breweryName;
	}

	/**
	 * @param breweryName the name to set
	 */
	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the description
	 */
	public String getBreweryDescription() {
		return breweryDescription;
	}

	/**
	 * @param breweryDescription the description to set
	 */
	public void setBreweryDescription(String breweryDescription) {
		this.breweryDescription = breweryDescription;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public boolean isAuthenticTrappist() {
		return isAuthenticTrappist;
	}

	public void setAuthenticTrappist(boolean isAuthenticTrappist) {
		this.isAuthenticTrappist = isAuthenticTrappist;
	}


	@Override
	//basic comparation algorithm based just on the name.
	//assuming that don't exist two breweries with the same name.
	public int compareTo(Brewery o) {
		/*
		int ret;
		if(o.getName().equals(getName())){
			ret=0;
		}
		else{
			if(getCountry().equals(o.getCountry())){
				if(getTown().equals(o.getTown())){
					ret=getName().codePointAt(o.getName());
				}
			}*/
//		System.out.println(name+"\n");
//		System.out.println(o.getName()+"\n");
		return getBreweryName().compareTo(o.getBreweryName());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((breweryDescription == null) ? 0 : breweryDescription.hashCode());
		result = prime * result + (isAuthenticTrappist ? 1231 : 1237);
		result = prime * result + ((breweryName == null) ? 0 : breweryName.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Brewery other = (Brewery) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (breweryDescription == null) {
			if (other.breweryDescription != null)
				return false;
		} else if (!breweryDescription.equals(other.breweryDescription))
			return false;
		if (isAuthenticTrappist != other.isAuthenticTrappist)
			return false;
		if (breweryName == null) {
			if (other.breweryName != null)
				return false;
		} else if (!breweryName.equals(other.breweryName))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
	

}
