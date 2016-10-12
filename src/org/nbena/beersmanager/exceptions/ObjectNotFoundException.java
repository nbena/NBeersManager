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

import org.nbena.beersmanager.exe.Utils;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends Exception {

//	public ObjectNotFoundException() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public ObjectNotFoundException(String arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
	
	private static String createMessage(Object o){
		StringBuilder builder = new StringBuilder();
		builder.append("Oggetto non trovato ");
		builder.append(Utils.shortDesc(o));
		return builder.toString();
	}
	
	public ObjectNotFoundException(Object o){	
		super(createMessage(o));
	}

//	public ObjectNotFoundException(Throwable arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public ObjectNotFoundException(String arg0, Throwable arg1) {
//		super(arg0, arg1);
//		// TODO Auto-generated constructor stub
//	}
//
//	public ObjectNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}

}
