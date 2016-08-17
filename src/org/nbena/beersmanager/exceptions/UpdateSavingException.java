package org.nbena.beersmanager.exceptions;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.BreweryAverage;

public class UpdateSavingException extends Exception {

	public UpdateSavingException() {
		// TODO Auto-generated constructor stub
	}

	public UpdateSavingException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public static enum ErrorWhile{
		SAVING,
		UPDATING,
		ADDING
	}
	
	public static String createMessage(Object o, ErrorWhile error){
		
		String up = "Error while ";
		
		switch(error){
		case ADDING:
			up += ErrorWhile.ADDING.toString().toLowerCase();
			break;
		case SAVING:
			up += ErrorWhile.SAVING.toString().toLowerCase();
			break;
		case UPDATING:
			up += ErrorWhile.UPDATING.toString().toLowerCase();
			break;
		
		}
		
		

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
	
	public UpdateSavingException(Object o, ErrorWhile error){	
		super(createMessage(o, error));
	}

	public UpdateSavingException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UpdateSavingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UpdateSavingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
