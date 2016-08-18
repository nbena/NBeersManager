package org.nbena.beersmanager.exe.ui.models;

import java.util.List;


import javax.swing.table.DefaultTableModel;

import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.Utils.Constants;
import org.nbena.beersmanager.coreclasses.Style;

public class ModelStyleTable extends /*DefaultTableModel*/MyModelAbstractTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7295474355055930967L;
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

	@Override
	public void setData(List data) {
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




		
	}
	
	


