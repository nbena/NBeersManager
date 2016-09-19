package org.nbena.beersmanager.exe.ui.models;


import javax.swing.SpinnerNumberModel;

import org.nbena.beersmanager.exe.Utils;

public class CustomSpinnerModel  {




	
	
	public static class MarkSpinnerNumberModel extends SpinnerNumberModel{
		
		
		public MarkSpinnerNumberModel(){
			super(Utils.Constants.MARK_SPINNER_DEF_VALUE , Utils.Constants.MARK_SPINNER_MIN_VALUE, 
					Utils.Constants.MARK_SPINNER_MAX_VALUE, Utils.Constants.MARK_SPINNER_STEP_VALUE);
			
//			super(Utils.Constants.MARK_SPINNER_DEF_VALUE ,null, 
//					Utils.Constants.MARK_SPINNER_MAX_VALUE, Utils.Constants.MARK_SPINNER_STEP_VALUE);
//			
//			super.setValue(Utils.Constants.MARK_SPINNER_DEF_VALUE);
//			System.out.println("The value of def: "+Utils.Constants.MARK_SPINNER_DEF_VALUE);
//			System.out.println("The current value at inizialization is: "+(Integer)this.getValue());
			
		}
		
		
//		@Override
//		public Integer getValue(){
//			return (Integer)super.getValue();
//		}
		
		
		}
	
	
	public static class ABVSpinnerNumberModel extends SpinnerNumberModel{
		
		public ABVSpinnerNumberModel (){
			super(Utils.Constants.ABV_SPINNER_DEF_VALUE, Utils.Constants.ABV_SPINNER_MIN_VALUE, 
					Utils.Constants.ABV_SPINNER_MAX_VALUE, Utils.Constants.ABV_SPINNER_STEP_VALUE);
			
//			super(Utils.Constants.ABV_SPINNER_DEF_VALUE, null, 
//					Utils.Constants.ABV_SPINNER_MAX_VALUE, Utils.Constants.ABV_SPINNER_STEP_VALUE);
//			
//			super.setValue(Utils.Constants.ABV_SPINNER_DEF_VALUE);
		}
		
//		@Override
//		public Double getValue(){
//			return (Double)super.getValue();
//		}
	}
	
	public static class PriceSpinnerNumberModel extends SpinnerNumberModel{
		
		public PriceSpinnerNumberModel (){
			super(Utils.Constants.PRICE_SPINNER_DEF_VALUE, Utils.Constants.PRICE_SPINNER_MIN_VALUE, 
					Utils.Constants.PRICE_SPINNER_MAX_VALUE, Utils.Constants.PRICE_SPINNER_STEP_VALUE);
			
//			super(Utils.Constants.PRICE_SPINNER_DEF_VALUE, null, 
//					Utils.Constants.PRICE_SPINNER_MAX_VALUE, Utils.Constants.PRICE_SPINNER_STEP_VALUE);
//			
//			super.setValue(Utils.Constants.PRICE_SPINNER_DEF_VALUE);
			
		}
		
//		@Override
//		public Double getValue(){
//			return (Double)super.getValue();
//		}
	
	}
	
	}



	
	


