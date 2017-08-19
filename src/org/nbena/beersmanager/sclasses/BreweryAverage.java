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

import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.query.QueryRunner.BreweryQuery;

/**
 * A modified version of {@link #org.nbena.beersmanager.coreclasses.Brewery()()}. It contains one more property, which it consists of the average
 * of the mark of each own beer.
 * @author nbena
 * @see org.nbena.beersmanager.coreclasses.Brewery
 *
 */
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
	
	/**
	 * Set the current average for the brewery by calculating it from the given list of BREWERY'S beer.
	 * @param thisBreweryBeers	the beer of this brewery. Note that no controls are made to check if beer are of this brewery,
	 * a filter on this should be done before.
	 */
	public void setAverage(List<Beer> thisBreweryBeers){
		average=BreweryQuery.breweryAverage(thisBreweryBeers, this, true);
//		average=(Double.isNaN(average) ? 0.0 : average); now done inside the calculations function
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
