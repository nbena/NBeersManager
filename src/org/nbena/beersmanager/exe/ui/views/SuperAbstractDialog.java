package org.nbena.beersmanager.exe.ui.views;


import java.awt.event.ActionListener;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public abstract class SuperAbstractDialog extends JDialog {


	
	public abstract void addActionListenerOkButton(ActionListener listener);
	
	public abstract void addActionListenerCancelButton(ActionListener listener);


}
