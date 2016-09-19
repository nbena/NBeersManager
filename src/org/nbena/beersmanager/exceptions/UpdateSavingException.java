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


	
	public static enum ErrorWhile{
		SAVING,
		UPDATING,
		ADDING,
		DELETING,
		DELETING_TRAVERSAL,
		UPDATING_TRAVERSAL
	}
	
//	public static String createMessage(Object o, ErrorWhile error){
//		
//		String up = "Error while ";
//		
//		switch(error){
//		case ADDING:
//			up += ErrorWhile.ADDING.toString().toLowerCase();
//			break;
//		case SAVING:
//			up += ErrorWhile.SAVING.toString().toLowerCase();
//			break;
//		case UPDATING:
//			up += ErrorWhile.UPDATING.toString().toLowerCase();
//			break;
//		case DELETING:
//			up += ErrorWhile.DELETING.toString().toLowerCase();
//			break;
//		case DELETING_TRAVERSAL:
//			up+= "Cancellando elementi relativi a questo oggetto.";
//		case UPDATING_TRAVERSAL: 
//			up += "Modificando elementi relativi a questo oggetto: ";
//			break;
//		}
//		
//	
//		System.out.println(o.getClass());
//		
//		if (o instanceof Beer) {
//			Beer cause = (Beer)o;
//			up.concat("Beer "+cause.getBrewery().getBreweryName() +" "+cause.getName());
//		}
//		else if(o instanceof Brewery){
//			Brewery cause = (Brewery)o;
//			up.concat("Brewery "+cause.getBreweryName());
//		}
//		else if(o instanceof BreweryAverage){
//			BreweryAverage cause = (BreweryAverage)o;
//			up.concat("Brewery "+cause.getBreweryName());
//		}else if(o instanceof Style){
//			Style cause =(Style)o;
//			up.concat("Style "+Utils.getStyleStringSubMain(cause));
//		}
//		System.out.println(up);
//		return up;
//	}
//	
	private static String getErrorMessageFromCause(ErrorWhile error){
		String up = "Errore mentre ";
		
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
		case DELETING:
			up += ErrorWhile.DELETING.toString().toLowerCase();
			break;
		case DELETING_TRAVERSAL:
			up+= "Cancellando elementi relativi a questo oggetto.";
		case UPDATING_TRAVERSAL: 
			up += "Modificando elementi relativi a questo oggetto: ";
			break;
		}
		
		return up;
	}
	
	private static String appendCause(String s, ErrorWhile error){
		String ret = s;
		if(error == ErrorWhile.ADDING){
			ret+= " E' probabile che l'oggetto sia già presente.";
		}else if(error == ErrorWhile.DELETING_TRAVERSAL || error == ErrorWhile.UPDATING_TRAVERSAL){
			ret += "E' dovuto ad un errore interno.";
		}else if(error == ErrorWhile.SAVING){
			ret += " Controllare che il file non sia già in uso";
		}else {
			//delte, update
			ret += "La ricerca dell'oggetto non ha dato risultati. Chiudere e riavviare.";
		}
		return ret;
	}
	
	public static String createMessage(Beer cause, ErrorWhile error){
		String s = getErrorMessageFromCause(error);
		s+= "birra: '"+cause.getName()+"'";
		return s.concat(appendCause(s, error));
	}
	
	public static String createMessage(Brewery cause, ErrorWhile error){
		String s = getErrorMessageFromCause(error);
		s+="birrificio: '"+cause.getBreweryName()+"'";
		return s.concat(appendCause(s, error));
	}
	
	public static String createMessage(Style cause, ErrorWhile error){
		String s = getErrorMessageFromCause(error);
		s+= "stile: '"+cause.getStyleMainName()+" "+cause.getStyleSubCategory()+"'";
		return s.concat(appendCause(s, error));
	}
	public static String createMessage(BreweryAverage cause, ErrorWhile error){
		String s = getErrorMessageFromCause(error);
		s+="birrificio: '"+cause.getBreweryName()+"'";
		return s.concat(appendCause(s, error));
	}
	
	@Deprecated
	public UpdateSavingException(Object o, ErrorWhile error){	
//		super(createMessage(o, error));
		super();
	}

//	public UpdateSavingException(Throwable arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public UpdateSavingException(String arg0, Throwable arg1) {
//		super(arg0, arg1);
//		// TODO Auto-generated constructor stub
//	}
//
//	public UpdateSavingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}
	
	public UpdateSavingException(String message){
		super(message);
	}

}
