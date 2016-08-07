package org.nbena.beersmanager.exe.models;



import javax.swing.table.DefaultTableModel;

import org.nbena.beersmanager.exe.Utils;

public class ModelBeerTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6465689985503079210L;

	public ModelBeerTable() {
		super(new Object[][]{}, Utils.Constants.TABLE_HEADER_BEERS);
	}

	

}
