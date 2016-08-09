package org.nbena.beersmanager.exe.ui.views;

import java.awt.event.ActionListener;

import javax.swing.JDialog;

public abstract class ViewAbstractDialog extends JDialog {
	
	public abstract void addActionListenerOkButton(ActionListener listener);
	
	public abstract void addActionListenerCancelButton(ActionListener listener);
	
	public abstract void addActionListenerModifyButton(ActionListener listener);
	

}
