package org.nbena.beersmanager.coreclasses;

public enum Fermentation { HIGH, LOW, SPONTANEOUS;
	
	
	public static Fermentation toFermentation(String arg0){
		if(arg0.toLowerCase().equals("high"))
			return HIGH;
		else if(arg0.toLowerCase().equals("low"))
			return LOW;
		return SPONTANEOUS;
	}
	
	public static String toFirstUpperCase(Fermentation fermentation){
		if(fermentation==HIGH)
		   return fermentation.toString().toLowerCase().replaceFirst("h", "H");
		else if(fermentation==LOW)
			return fermentation.toString().toLowerCase().replaceFirst("l", "L");
		else
			return fermentation.toString().toLowerCase().replaceFirst("s", "S");
	}
	
	public String toFirstUpperCase(){
		return Fermentation.toFirstUpperCase(this);
	}
	
	

}

