package org.nbena.beersmanager.exe.ui.models;





import java.util.List;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.exe.Utils;

public class ModelBeerTable extends /*DefaultTableModel*/MyModelAbstractTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6465689985503079210L;
	
	

	public ModelBeerTable() {
		super(new Object[][]{}, Utils.Constants.TABLE_HEADER_BEERS);
	}
	
	public ModelBeerTable(Object [][] data){
		super(data, Utils.Constants.TABLE_HEADER_BEERS);
		
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
		List<Beer> beerData=(List<Beer>)data;
		for(Beer b: beerData){
			this.addRow(Utils.fromBeerToObjectArray(b));
		}
	}

	@Override
	public Object getSelectedObject(int row) {
		return null;
	}

	

}
