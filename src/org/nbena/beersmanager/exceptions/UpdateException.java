package org.nbena.beersmanager.exceptions;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.BreweryAverage;

public class UpdateException extends Exception {

	public UpdateException() {
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public static String createMessage(Object o){
		
		String up = "Error while updating ";
		if (o instanceof Beer) {
			Beer cause = (Beer)o;
			up.concat("Beer "+cause.getBrewery().getName() +" "+cause.getName());
		}
		else if(o instanceof Brewery){
			Brewery cause = (Brewery)o;
			up.concat("Brewery "+cause.getName());
		}
		else if(o instanceof BreweryAverage){
			BreweryAverage cause = (BreweryAverage)o;
			up.concat("Brewery "+cause.getName());
		}else if(o instanceof Style){
			Style cause =(Style)o;
			up.concat("Style "+Utils.getStyleString(cause));
		}
		return up;
	}
	
	public UpdateException(Object o){	
		super(createMessage(o));
	}

	public UpdateException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
