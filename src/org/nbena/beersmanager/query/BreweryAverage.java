package org.nbena.beersmanager.query;

import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;


public class BreweryAverage extends Brewery {
	
	private double average;

	public BreweryAverage() {
	}

	public BreweryAverage(String name, String town, String country, String description, String website) {
		super(name, town, country, description, website);
	}

	public BreweryAverage(String name, String town, String country, String description, String website,
			boolean isAuthenticTrappist) {
		super(name, town, country, description, website, isAuthenticTrappist);
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}
	
	
	public void setAverage(List<Beer> thisBreweryBeers){
		average=QueryRunner.breweryAverage(thisBreweryBeers, this, true);
	}

}
