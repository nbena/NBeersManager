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
package org.nbena.beersmanager.exe.ui.models;

import java.util.List;


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

//	public MyModelAbstractTable(Vector columnNames, int rowCount) {
//		super(columnNames, rowCount);
//		// TODO Auto-generated constructor stub
//	}
//
//	public MyModelAbstractTable(Object[] columnNames, int rowCount) {
//		super(columnNames, rowCount);
//		// TODO Auto-generated constructor stub
//	}
//
//	public MyModelAbstractTable(Vector data, Vector columnNames) {
//		super(data, columnNames);
//		// TODO Auto-generated constructor stub
//	}

	public MyModelAbstractTable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	
	public MyModelAbstractTable(Object [] columnNames){
		super(new Object[][]{}, columnNames);
	}
	
	public  MyModelAbstractTable(Object [][]data){}
	
	public abstract void clear();
	
	public abstract void setData(Object o);
	
	public abstract void setData(@SuppressWarnings("rawtypes") List data);
	
	public abstract Object getSelectedObject(int row);

}
