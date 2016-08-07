package org.nbena.beersmanager.exe.models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.Utils.Constants;
import org.nbena.beersmanager.coreclasses.Style;

public class ModelStyleTable extends DefaultTableModel{
	
	private List<Style> data;
	
	public ModelStyleTable(){
		super(new Object[][]{}, Constants.TABLE_HEADER_STYLE);
	}
	
	public ModelStyleTable(Object[][] data){
		super(data, Utils.Constants.TABLE_HEADER_STYLE);
	}
	
	public void setData(List<Style> styles){
		this.data=styles;
		for(Style s: data){
			this.addRow(Utils.fromStyle(s));
		}
	}
	
	

}
