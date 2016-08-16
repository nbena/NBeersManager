package org.nbena.beersmanager.query;

import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;


public class BreweryAverage extends Brewery {
	
//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(average);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BreweryAverage other = (BreweryAverage) obj;
//		if (Double.doubleToLongBits(average) != Double.doubleToLongBits(other.average))
//			return false;
//		return true;
//	}


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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(average);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BreweryAverage other = (BreweryAverage) obj;
		if (Double.doubleToLongBits(average) != Double.doubleToLongBits(other.average))
			return false;
		return true;
	}

}
