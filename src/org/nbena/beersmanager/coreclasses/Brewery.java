package org.nbena.beersmanager.coreclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="brewery")
@XmlAccessorType(XmlAccessType.FIELD)
public class Brewery implements Comparable<Brewery> {
	private String name;
	private String town;
	private String country;
	private String description;
	private String website;
	private boolean isAuthenticTrappist;
	

	public Brewery() {
	}

	public Brewery(String name, String town, String country, String description, String website) {
		this.name = name;
		this.town = town;
		this.country = country;
		this.description = description;
		this.website = website;
	}
	
	public Brewery(String name, String town, String country, String description, String website, boolean isAuthenticTrappist) {
		this.name = name;
		this.town = town;
		this.country = country;
		this.description = description;
		this.website = website;
		this.isAuthenticTrappist=isAuthenticTrappist;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
		
		return getName().compareTo(o.getName());
	}
	
	

}
