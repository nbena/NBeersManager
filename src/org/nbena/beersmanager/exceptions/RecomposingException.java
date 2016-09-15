package org.nbena.beersmanager.exceptions;

import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;

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
			msg.append("brewery: "+b.getName());
		}
		return msg.toString();
	}

}
