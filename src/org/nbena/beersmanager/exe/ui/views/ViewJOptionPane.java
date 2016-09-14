package org.nbena.beersmanager.exe.ui.views;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ViewJOptionPane {
	
	private Component parent;
	
	public ViewJOptionPane() {
	}

	public ViewJOptionPane(Component parent) {
		this.parent = parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	/**
	 * Show a JOptioPane that show a text field to user, when he can insert what he wants.
	 * @param title the title of the dialog.
	 * @param message the message to show.
	 * @param defaultPossibility the default shown text.
	 * @return the text insterted by the user. (Null if cancel is pressed)
	 */
	public String showBlankTextInput(String title, String message, String defaultPossibility){
		return (String) JOptionPane.showInputDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, null, null, defaultPossibility);
	}
	
	/**
	 * Show a JOptionPane with a combo box.
	 * @param title the title of the dialog.
	 * @param message the message to show.
	 * @param items the array of the options. Default option is item[0].
	 * @return the selected item. (Null if cancel is pressed).
	 */
	public String showComboBoxInput(String title, String message, String[] items){
		return (String) JOptionPane.showInputDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE, null, items, items[0]);
	}
	
	public void showErrorMessageDialog(String title, String message){
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	
	public int showOkCancel(String title, String message){
		return JOptionPane.showConfirmDialog(parent, message, title,JOptionPane.YES_NO_OPTION);
	}
	
	public int showYesNo(String title, String message){
		return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION);
	}
	
	public static boolean isYesOption(int option){
		return option==JOptionPane.YES_OPTION ? true : false;
	}
	
	public static boolean isOkOption(int option){
		return option==JOptionPane.OK_OPTION ? true : false;
	}
	
	public static boolean isNoOption(int option){
		return option==JOptionPane.NO_OPTION ? true : false;
	}
	
	public static boolean isCancelOption(int option){
		return option==JOptionPane.CANCEL_OPTION ? true : false;
	}
	
	public static boolean isClosedOption(int option){
		return option==JOptionPane.CLOSED_OPTION ? true : false;
	}
	


}
