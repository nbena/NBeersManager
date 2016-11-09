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

import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.exe.Utils;

/**
 * Pretty unnecessary, now always use modelbreweryaverage
 * @author nicola
 *
 */
@Deprecated
@SuppressWarnings("serial")
public class ModelBreweryTable extends /*DefaultTableModel*/MyModelAbstractTable {



	public ModelBreweryTable() {
		super(new Object[][]{}, Utils.Constants.TABLE_HEADER_BREWERY);
	}



	public ModelBreweryTable(Object[][] data) {
		super(data, Utils.Constants.TABLE_HEADER_BREWERY);
	}



	@Override
	public void clear() {
//		int max=this.getRowCount();
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



	@Override
	public void setData(@SuppressWarnings("rawtypes") List data) {
		@SuppressWarnings("unchecked")
		List<Brewery> breweryData=(List<Brewery>)data;
		for(Brewery b: breweryData){
			this.addRow(Utils.fromBreweryToObjectArray(b));
		}
//		this.fireTableDataChanged();
//		System.out.println("Data changed");
	}



	@Override
	public Object getSelectedObject(int row) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

}
