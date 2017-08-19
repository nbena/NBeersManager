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




import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.Utils.Constants;
import org.nbena.beersmanager.coreclasses.Style;

@SuppressWarnings("serial")
public class ModelStyleTable extends /*DefaultTableModel*/MyModelAbstractTable{
	

	private List<Style> styleData;
	
	public ModelStyleTable(){
		super(new Object[][]{}, Constants.TABLE_HEADER_STYLE);
	}
	
	public ModelStyleTable(Object[][] data){
		super(data, Utils.Constants.TABLE_HEADER_STYLE);
	}
	
	/*
	public void setData(List<Style> styles){
		this.data=styles;
		for(Style s: data){
			this.addRow(Utils.fromStyle(s));
		}
	}
	*/
	public void clear(){
//		int max=this.getRowCount();
//		System.out.println("Row count is: "+max);
//		for(int i=0;i<max;i++){
//			this.removeRow(i);
//		}
		for(int i=0;i<getRowCount();i++){
			removeRow(i);
		}
	}

	@Override
	public void setData(Object o) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(@SuppressWarnings("rawtypes") List data) {
		styleData=(List<Style>)data;
		for(Style s: styleData){
			this.addRow(Utils.fromStyleToObjectArray(s));
		}
//		this.fireTableDataChanged();
//		System.out.println("Fired");
		
	}

	@Override
	public Object getSelectedObject(int row) {
		return null;
		//return styleData.get(this.);
	}


	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

		
	}
	
	


