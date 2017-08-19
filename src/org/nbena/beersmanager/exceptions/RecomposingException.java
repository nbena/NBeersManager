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
package org.nbena.beersmanager.exceptions;

import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;

@SuppressWarnings("serial")
public class RecomposingException extends Exception {

//	public RecomposingException() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public RecomposingException(String arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public RecomposingException(Throwable arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public RecomposingException(String arg0, Throwable arg1) {
//		super(arg0, arg1);
//		// TODO Auto-generated constructor stub
//	}
	
	public RecomposingException(Object o){
		super(createMessage(o));
	}

//	public RecomposingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}
	
	private static String createMessage(Object o){
		StringBuilder msg = new StringBuilder();
		msg.append("Recomposing beer error: not found ");
		if (o instanceof Style){
			Style s =(Style)o;
			msg.append("style:" +s.getStyleMainName()+" "+s.getStyleSubCategory());
		}else if(o instanceof Brewery){
			Brewery b = (Brewery)o;
			msg.append("brewery: "+b.getBreweryName());
		}
		return msg.toString();
	}

}
