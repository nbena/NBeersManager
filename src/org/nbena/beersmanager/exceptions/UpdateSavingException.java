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
public class UpdateSavingException extends Exception {

	public UpdateSavingException() {
		super();
	}


	
	public static enum ErrorWhile{
		SAVING,
		UPDATING,
		ADDING,
		DELETING,
		DELETING_TRAVERSAL,
		UPDATING_TRAVERSAL
	}
	
	private static final String ADDING_IT = "aggiunta ";
	private static final String DELETING_IT = "cancellazione ";
	private static final String DELETING_TRAVERSAL_IT = "Cancellazione dei dati relativi a questo oggetto ";
	private static final String SAVING_IT = "salvataggio ";
	private static final String UPDATING_IT = "aggiornamento ";
	private static final String UPDATING_TRAVERSAL_IT = "modifica dati relativi a questo oggetto ";
	private static final String ADDING_FINAL_IT = ", l'oggetto potrebbe essere già presente";
	private static final String DEL_UPD_TRAV_FINAL_IT = ", un errore interno è successo";
	private static final String SAVING_FINAL_IT = ", il file potrebbe già essere aperto";
	private static final String OTHER_FINAL_IT = ", ricerca fallita, due oggetti simili o oggetto non trovato";
	
	
//	public static String createMessage(Beer cause, ErrorWhile error){
//		String s = Utils.errorWhileToItalianString(error);
//		s+= "birra: '"+cause.getName()+"'";
//		return s.concat(Utils.errorWhileAppendCause(s, error));
//	}
//	
//	public static String createMessage(Brewery cause, ErrorWhile error){
//		String s = Utils.errorWhileToItalianString(error);
//		s+="birrificio: '"+cause.getBreweryName()+"'";
//		return s.concat(Utils.errorWhileAppendCause(s, error));
//	}
//	
//	public static String createMessage(Style cause, ErrorWhile error){
//		String s = Utils.errorWhileToItalianString(error);
//		s+= "stile: '"+cause.getStyleMainName()+" "+cause.getStyleSubCategory()+"'";
//		return s.concat(Utils.errorWhileAppendCause(s, error));
//	}
//	public static String createMessage(BreweryAverage cause, ErrorWhile error){
//		String s = Utils.errorWhileToItalianString(error);
//		s+="birrificio: '"+cause.getBreweryName()+"'";
//		return s.concat(Utils.errorWhileAppendCause(s, error));
//	}
	
	
	public UpdateSavingException(Object o, ErrorWhile error){	
//		super(createMessage(o, error));
		super(createMessage(o, error));
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
	@Deprecated
	public UpdateSavingException(String message){
		super(message);
	}
	
	
	public static String createMessage(Object o, ErrorWhile error){
		StringBuilder builder = new StringBuilder();
		builder.append("Errore durante  ");
		String desc = Utils.shortDesc(o);
		switch(error){
		case ADDING:
			builder.append(ADDING_IT);
			builder.append(desc);
			builder.append(ADDING_FINAL_IT);
			break;
		case DELETING:
			builder.append(DELETING_IT);
			builder.append(desc);
			builder.append(OTHER_FINAL_IT);
			break;
		case DELETING_TRAVERSAL:
			builder.append(DELETING_TRAVERSAL_IT);
			builder.append(desc);
			builder.append(DEL_UPD_TRAV_FINAL_IT);
			break;
		case SAVING:
			builder.append(SAVING_IT);
			builder.append(desc);
			builder.append(SAVING_FINAL_IT);
			break;
		case UPDATING:
			builder.append(UPDATING_IT);
			builder.append(desc);
			builder.append(OTHER_FINAL_IT);
			break;
		case UPDATING_TRAVERSAL:
			builder.append(UPDATING_TRAVERSAL_IT);
			builder.append(desc);
			builder.append(DEL_UPD_TRAV_FINAL_IT);
			break;
		
		}
		return builder.toString();
	}
	

}
