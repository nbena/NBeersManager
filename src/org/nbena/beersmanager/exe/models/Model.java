package org.nbena.beersmanager.exe.models;

import javax.swing.table.DefaultTableModel;

public class Model {
	
	private DefaultTableModel tableModel;
	
	public Model(){
		tableModel=new DefaultTableModel();
	}

	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	



	

}
