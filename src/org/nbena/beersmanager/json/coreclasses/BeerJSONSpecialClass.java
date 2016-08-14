package org.nbena.beersmanager.json.coreclasses;

/*
import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
*/

/*
 * Tought about declare static inside XMLHelper class, but i choose to divide them to better work on two separated (and big) classes
 */
//@XmlRootElement(name="beer")
//@XmlAccessorType(XmlAccessType.FIELD)
public class BeerJSONSpecialClass {
	private String name;
	private String breweryName;
	private String styleMainName;
	private String styleSubcategory;
	private boolean isTried;
	private String placeTried;
	private double price;
	private double alcool;
	private int mark;
	private int numberOfStars;
	private String description;
	private String color;
	//private byte[] image; //tell to xml to ignore
	private String imageFilePath;
	


	
	public String getImageFilePath() {
		return imageFilePath;
	}




	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}




	public BeerJSONSpecialClass(String name, String breweryName, String styleMainName, String styleSubcategory) {
		this.name = name;
		this.breweryName = breweryName;
		this.styleMainName = styleMainName;
		this.styleSubcategory = styleSubcategory;
	}




	public BeerJSONSpecialClass(String name, String breweryName, String styleMainName, String styleSubcategory, int numberOfStars) {
		this.name = name;
		this.breweryName = breweryName;
		this.styleMainName = styleMainName;
		this.styleSubcategory = styleSubcategory;
		this.numberOfStars=numberOfStars;
	}

	
	
	

	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description) {
		this.placeTried = placeTried;
		this.price = price;
		this.alcool = alcool;
		this.mark = mark;
		this.numberOfStars = numberOfStars;
		this.description = description;
	}
	
	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description, String color) {
		this.placeTried = placeTried;
		this.price = price;
		this.alcool = alcool;
		this.mark = mark;
		this.numberOfStars = numberOfStars;
		this.description = description;
		this.color=color;
	}
	
	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description, byte[] image) {
		this.placeTried = placeTried;
		this.price = price;
		this.alcool = alcool;
		this.mark = mark;
		this.numberOfStars = numberOfStars;
		this.description = description;
	}

	public BeerJSONSpecialClass() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the String
	 */
	public String getBreweryName() {
		return breweryName;
	}

	/**
	 * @param String the String to set
	 */
	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}


	public String getStyleMainName() {
		return styleMainName;
	}




	public void setStyleMainName(String styleMainName) {
		this.styleMainName = styleMainName;
	}




	public String getStyleSubcategory() {
		return styleSubcategory;
	}




	public void setStyleSubcategory(String styleSubcategory) {
		this.styleSubcategory = styleSubcategory;
	}




	/**
	 * @return the isTried
	 */
	public boolean isTried() {
		return isTried;
	}

	/**
	 * @param isTried the isTried to set
	 */
	public void setTried(boolean isTried) {
		this.isTried = isTried;
	}

	/**
	 * @return the placeTried
	 */
	public String getPlaceTried() {
		return placeTried;
	}

	/**
	 * @param placeTried the placeTried to set
	 */
	public void setPlaceTried(String placeTried) {
		this.placeTried = placeTried;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the alcool
	 */
	public double getAlcool() {
		return alcool;
	}

	/**
	 * @param alcool the alcool to set
	 */
	public void setAlcool(double alcool) {
		this.alcool = alcool;
	}

	/**
	 * @return the mark
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(int mark) {
		this.mark = mark;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}




	/**
	 * @return the numberOfStars
	 */
	public Integer getNumberOfStars() {
		return numberOfStars;
	}




	/**
	 * @param numberOfStars the numberOfStars to set
	 */
	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	/*
	public boolean isComplete(){
		return name!=null && String!=null && style!=null && placeTried!=null && isTried!=null && price!=null && alcool!=null && mark!=null && numberOfStars!=null && description!=null; 
	}
	
	public boolean isVeryComplete(){
		return name!=null && String!=null && style!=null && placeTried!=null && isTried!=null && price!=null && alcool!=null && mark!=null && numberOfStars!=null && description!=null && image!=null; 
	}
	
	public boolean isMinimal(){
		return name!=null && String!=null && style!=null  && numberOfStars!=null; 
	}
	
	public boolean isVeryMinimal(){
		return name!=null && String!=null && style!=null; 
	}
	
	*/

}
