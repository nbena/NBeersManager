package org.nbena.beersmanager.exe.ui.models;




import java.util.List;

import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.exe.Utils;

public class ModelBreweryTable extends /*DefaultTableModel*/MyModelAbstractTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6446731013288090993L;

	public ModelBreweryTable() {
		super(new Object[][]{}, Utils.Constants.TABLE_HEADER_BREWERY);
	}



	public ModelBreweryTable(Object[][] data) {
		super(data, Utils.Constants.TABLE_HEADER_BREWERY);
	}



	@Override
	public void clear() {
		int max=this.getRowCount();
		for(int i=0;i<max;i++){
			this.removeRow(i);
		}
	}



	@Override
	public void setData(Object o) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setData(List data) {
		List<Brewery> breweryData=(List<Brewery>)data;
		for(Brewery b: breweryData){
			this.addRow(Utils.fromBreweryToObjectArray(b));
		}
		
	}



	@Override
	public Object getSelectedObject(int row) {
		// TODO Auto-generated method stub
		return null;
	}

}
