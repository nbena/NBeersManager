package org.nbena.beersmanager.exe.ui.models;

import java.util.List;


import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.query.BreweryAverage;

public class ModelBreweryAverage extends MyModelAbstractTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478980599280631956L;


	public ModelBreweryAverage() {
		super(new Object[][]{}, Utils.Constants.TABLE_HEADER_BREWERY_AVERAGE);
	}



	public ModelBreweryAverage(Object[][] data) {
		super(data, Utils.Constants.TABLE_HEADER_BREWERY_AVERAGE);
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
	public void setData(List data) {
		List<BreweryAverage> breweryData=(List<BreweryAverage>)data;
		for(BreweryAverage b: breweryData){
			this.addRow(Utils.fromBreweryAverageToObjectArray(b));
		}
//		this.fireTableDataChanged();
//		System.out.println("Data changed");
	}


	@Override
	public void setData(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Object getSelectedObject(int row) {
		// TODO Auto-generated method stub
		return null;
	}




}
