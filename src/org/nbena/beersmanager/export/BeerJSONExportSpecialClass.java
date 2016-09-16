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

	

	public BeerJSONExportSpecialClass() {
	}

	/**
	 * @return the name
	 */
	public String getBeerName() {
		return beerName;
	}

	/**
	 * @param beerName the name to set
	 */
	public void setBeerName(String beerName) {
		this.beerName = beerName;
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
	public String getBeerDescription() {
		return beerDescription;
	}

	/**
	 * @param beerDescription the description to set
	 */
	public void setBeerDescription(String beerDescription) {
		this.beerDescription = beerDescription;
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