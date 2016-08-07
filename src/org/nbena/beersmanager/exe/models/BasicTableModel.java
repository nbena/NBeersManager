package org.nbena.beersmanager.exe.models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public abstract class BasicTableModel extends DefaultTableModel{
	
	public abstract void setDataList(List data);
	
	public abstract void addDataList(List data);

}
