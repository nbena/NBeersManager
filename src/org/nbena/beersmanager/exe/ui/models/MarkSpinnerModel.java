/**
 * 
 */
package org.nbena.beersmanager.exe.ui.models;

import javax.swing.SpinnerNumberModel;

/**
 * @author nicola
 *
 */
public class MarkSpinnerModel extends SpinnerNumberModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7926835778095366182L;
	private static final int min = 0;
	private static final int max = 100;
	private static final int step = 1;
	private static final int defaultValue = 0;

	/**
	 * 
	 */
	public MarkSpinnerModel() {
		super(defaultValue, min, max, step);
	}

//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 * @param arg3
//	 */
//	public MarkSpinnerModel(Number arg0, Comparable arg1, Comparable arg2, Number arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}

//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 * @param arg3
//	 */
//	public MarkSpinnerModel(int arg0, int arg1, int arg2, int arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}

//	/**
//	 * @param arg0
//	 * @param arg1
//	 * @param arg2
//	 * @param arg3
//	 */
//	public MarkSpinnerModel(double arg0, double arg1, double arg2, double arg3) {
//		super(arg0, arg1, arg2, arg3);
//		// TODO Auto-generated constructor stub
//	}
	
	public int getIntValue(){
		return (Integer)super.getValue();
	}
	
	public void setIntValue(int value){
		super.setValue(value);
	}

}
