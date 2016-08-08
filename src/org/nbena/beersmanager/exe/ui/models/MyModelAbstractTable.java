package org.nbena.beersmanager.exe.ui.models;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public abstract class MyModelAbstractTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7689613139253801207L;

	public MyModelAbstractTable() {
		// TODO Auto-generated constructor stub
	}

	public MyModelAbstractTable(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public MyModelAbstractTable(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public MyModelAbstractTable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public MyModelAbstractTable(Vector data, Vector columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MyModelAbstractTable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	
	public  MyModelAbstractTable(Object [][]data){}
	
	public abstract void clear();
	
	public abstract void setData(Object o);
	
	public abstract void setData(List data);
	
	public abstract Object getSelectedObject(int row);

}
