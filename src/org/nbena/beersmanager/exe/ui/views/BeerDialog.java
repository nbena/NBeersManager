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
package org.nbena.beersmanager.exe.ui.views;

public interface BeerDialog {
	
	public abstract void setBeerName(String t);
	
	
	public abstract void setBreweryName(String t);
	
	
	public abstract void setStyle(String t);
	
	
	public abstract void setABV(double abv);
	
	
	public abstract void setStars(int star);
	
	
	public abstract void setMark(int mark);
	
	
	public abstract void setTried(boolean tried);
	
	
	public abstract void setDescription(String t);
	
	
	public abstract void setPlace(String t);
	
	
	public abstract void setPrice(double price);
	

}
