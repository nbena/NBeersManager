package org.nbena.beersmanager.export;

import org.nbena.beersmanager.coreclasses.Brewery;

import org.nbena.beersmanager.coreclasses.Fermentation;
import org.nbena.beersmanager.json.coreclasses.StyleJSONSpecialClass;

public class BeerJSONExportSpecialClass {

	private String beerName;
	private Brewery brewery;
	private StyleJSONSpecialClass style;
	private Boolean isTried;
	private String placeTried;
	private Double price;
	private Double alcool;
	private Integer mark;
	private Integer numberOfStars; /*primitive types declared os object so I can check if they are null*/
	private String beerDescription;
//	private String color;
	private byte[] image; //tell to xml to ignore
	private String imageFilePath;

	public BeerJSONExportSpecialClass(String name, Brewery brewery, StyleJSONSpecialClass style) {
		this.beerName = name;
		this.brewery = brewery;
		this.style = style;
	}
	
	public BeerJSONExportSpecialClass(String name, Brewery brewery, StyleJSONSpecialClass style, int numberOfStar) {
		this.beerName = name;
		this.brewery = brewery;
		this.style = style;
		this.numberOfStars=numberOfStar;
	}
	
	
	

	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description) {
		this.placeTried = placeTried;
		this.price = price;
		this.alcool = alcool;
		this.mark = mark;
		this.numberOfStars = numberOfStars;
		this.beerDescription = description;
	}
	
//	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description, String color) {
//		this.placeTried = placeTried;
//		this.price = price;
//		this.alcool = alcool;
//		this.mark = mark;
//		this.numberOfStars = numberOfStars;
//		this.description = description;
//		this.color=color;
//	}
	
	public void setAssage(String placeTried, double price, double alcool, int mark, int numberOfStars, String description, byte[] image) {
		this.placeTried = placeTried;
		this.price = price;
		this.alcool = alcool;
		this.mark = mark;
		this.numberOfStars = numberOfStars;
		this.beerDescription = description;
	}

	public BeerJSONExportSpecialClass() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return beerName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.beerName = name;
	}

	/**
	 * @return the brewery
	 */
	public Brewery getBrewery() {
		return brewery;
	}

	/**
	 * @param brewery the brewery to set
	 */
	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}

	/**
	 * @return the style
	 */
	public StyleJSONSpecialClass getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(StyleJSONSpecialClass style) {
		this.style = style;
	}

	/**
	 * @return the fermentation
	 */
	public Fermentation getFermentation() {
		return Fermentation.valueOf(style.getFermentation());
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
		
		//-----
		/*
		if(mark>=84)
			numberOfStar=true;
			*/
		//----
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return beerDescription;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.beerDescription = description;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getImageFilePath() {
		return imageFilePath;
	}

	public Integer getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(Integer numberOfStar) {
		this.numberOfStars = numberOfStar;
	}

	public void setImageFilePath(String imagePath) {
		this.imageFilePath = imagePath;
	}

}
