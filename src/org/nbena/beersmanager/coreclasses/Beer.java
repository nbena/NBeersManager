package org.nbena.beersmanager.coreclasses;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="beer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Beer {
	private String name;
	private Brewery brewery;
	private Style style;
	private Boolean isTried;
	private String placeTried;
	private Double price;
	private Double alcool;
	private Integer mark;
	private Integer numberOfStars; /*primitive types declared os object so I can check if they are null*/
	private String description;
	private String color;
	private byte[] image; //tell to xml to ignore
	private String imageFilePath;

	public Beer(String name, Brewery brewery, Style style) {
		this.name = name;
		this.brewery = brewery;
		this.style = style;
	}
	
	public Beer(String name, Brewery brewery, Style style, int numberOfStar) {
		this.name = name;
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

	public Beer() {
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
	public Style getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @return the fermentation
	 */
	public Fermentation getFermentation() {
		return style.getFermentation();
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
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/*
	public boolean isComplete(){
		return name!=null && brewery!=null && style!=null && placeTried!=null && isTried!=null && price!=null && alcool!=null && mark!=null && numberOfStars!=null && description!=null; 
	}
	
	public boolean isVeryComplete(){
		return name!=null && brewery!=null && style!=null && placeTried!=null && isTried!=null && price!=null && alcool!=null && mark!=null && numberOfStars!=null && description!=null && image!=null; 
	}
	
	public boolean isMinimal(){
		return name!=null && brewery!=null && style!=null  && numberOfStars!=null; 
	}
	
	public boolean isVeryMinimal(){
		return name!=null && brewery!=null && style!=null; 
	}
	*/
	
	public String toString(){
		StringBuilder b =new StringBuilder();
		b.append(brewery.getName()+" "+name+"\n");
		b.append(style.getStyleMainName()+" "+style.getStyleSubCategory()+", "+alcool+" %\n");
		b.append("Stars: "+numberOfStars+", mark: "+mark+"\n");
		b.append("Color: "+color+"\n");
		if(isTried){
			b.append("Tried at: "+placeTried+", paied: "+price+"\n");
		}
		else{
			b.append("Not yet tried\n");
		}
		
		b.append("Color: "+color+"\n");
		return b.toString();
	}

}
