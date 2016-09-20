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
package org.nbena.beersmanager.coreclasses;

public enum Fermentation { HIGH, LOW, SPONTANEOUS;
	
	
	public static Fermentation toFermentation(String arg0){
		if(arg0.toLowerCase().equals("high"))
			return HIGH;
		else if(arg0.toLowerCase().equals("low"))
			return LOW;
		return SPONTANEOUS;
	}
	
	public static String toFirstUpperCase(Fermentation fermentation){
		if(fermentation==HIGH)
		   return fermentation.toString().toLowerCase().replaceFirst("h", "H");
		else if(fermentation==LOW)
			return fermentation.toString().toLowerCase().replaceFirst("l", "L");
		else
			return fermentation.toString().toLowerCase().replaceFirst("s", "S");
	}
	
	public String toFirstUpperCase(){
		return Fermentation.toFirstUpperCase(this);
	}
	
	

}

