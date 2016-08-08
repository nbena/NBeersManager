package org.nbena.beersmanager.exe.ui.models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.coreclasses.Style;

public class Model {
	

	public static enum DataShownNow{
		BEER,
		BREWERY,
		STYLE
	}
	
	public static enum DialogShownNow{
		BEER,
		BREWERY,
		STYLE
	}
	
	private MyModelAbstractTable tableModel;
	
	private MyModelAbstractDialog dialogModel;
	
	private List<Style> styleData;
	private List<Beer> beerData;
	private List<Brewery> breweryData;
	
	private Beer beerDialog;
	private Brewery breweryDialog;
	private Style styleDialog;
	
	private DataShownNow dataShownNow;
	private DialogShownNow dialogShown;
	
	public Model(){
		//tableModel=new MyModelAbstractTable();
	}
	
	public Model(MyModelAbstractTable tableModel){
		this.tableModel=tableModel;
	}
	
	public Model(MyModelAbstractDialog dialogModel) {
		super();
		this.dialogModel = dialogModel;
	}

	public Model(MyModelAbstractTable tableModel, MyModelAbstractDialog dialogModel) {
		super();
		this.tableModel = tableModel;
		this.dialogModel = dialogModel;
	}

	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(MyModelAbstractTable tableModel) {
		this.tableModel = tableModel;
	}
	
	
	
	
	/**
	 * @return the dialogModel
	 */
	public MyModelAbstractDialog getDialogModel() {
		return dialogModel;
	}

	/**
	 * @param dialogModel the dialogModel to set
	 */
	public void setDialogModel(MyModelAbstractDialog dialogModel) {
		this.dialogModel = dialogModel;
	}

	/**
	 * @return the dialogShown
	 */
	public DialogShownNow getDialogShown() {
		return dialogShown;
	}

	/**
	 * @param dialogShown the dialogShown to set
	 */
	public void setDialogShown(DialogShownNow dialogShown) {
		this.dialogShown = dialogShown;
	}

	/**
	 * @return the styleData
	 */
	public List<Style> getStyleData() {
		return styleData;
	}

	/**
	 * @param styleData the styleData to set
	 */
	public void setStyleData(List<Style> styleData) {
		this.styleData = styleData;
		dataShownNow=DataShownNow.STYLE;
		tableModel.clear();
		ModelStyleTable tableModelOld=(ModelStyleTable)tableModel;
		tableModel=tableModelOld;
		tableModel.setData(styleData); //it work||		
	}

	/**
	 * @return the beerData
	 */
	public List<Beer> getBeerData() {
		return beerData;
	}

	/**
	 * @param beerData the beerData to set
	 */
	public void setBeerData(List<Beer> beerData) {
		this.beerData = beerData;
		dataShownNow=DataShownNow.BEER;
		tableModel.clear();
		ModelBeerTable tableModelOld=(ModelBeerTable)tableModel;
		tableModel=tableModelOld;
		tableModel.setData(beerData); //it work||
	}

	/**
	 * @return the breweryData
	 */
	public List<Brewery> getBreweryData() {
		return breweryData;
	}

	/**
	 * @param breweryData the breweryData to set
	 */
	public void setBreweryData(List<Brewery> breweryData) {
		this.breweryData = breweryData;
		dataShownNow=DataShownNow.BREWERY;
		tableModel.clear();
		ModelBreweryTable tableModelOld=(ModelBreweryTable)tableModel;
		tableModel=tableModelOld;
		tableModel.setData(breweryData); //it work||
	}

	/**
	 * @return the dataShownNow
	 */
	public DataShownNow getDataShownNow() {
		return dataShownNow;
	}

	/**
	 * Set the new Data Type that is shown in the table. If different than the
	 * previous, the table is cleared.
	 * @param dataShownNow the dataShownNow to set
	 */
	public void setDataShownNow(DataShownNow dataShownNow) {
		if(this.dataShownNow!=dataShownNow && this.dataShownNow!=null){
			tableModel.clear();
		}
		this.dataShownNow = dataShownNow;
	}


	public Beer getSelectedBeer(int index){
		if (dataShownNow==DataShownNow.BEER){
//			Beer b = (Beer)tableModel.getSelectedObject(index);
//			return b;
			return beerData.get(index);
		}
		else
			throw new RuntimeException("Beer is not in the table");
		//return null;
	}
	
	public Style getSelectedStyle(int index){
		if (dataShownNow==DataShownNow.STYLE){
			//Style s = (Style)tableModel.getSelectedObject(index);
			//return s;
			return styleData.get(index);
		}
		else
			throw new RuntimeException("Style is not in the table");
		//return null;
	}
	
	public Brewery getSelectedBrewery(int index){
		if (dataShownNow==DataShownNow.BREWERY){
			//Brewery b = (Brewery)tableModel.getSelectedObject(index);
			//return b;
			return breweryData.get(index);
		}
		else
			throw new RuntimeException("Brewery is not in the table");
		//return null;
	}

	/**
	 * @return the beerDialog
	 */
	public Beer getBeerDialog() {
		return beerDialog;
	}

	/**
	 * @param beerDialog the beerDialog to set
	 */
	public void setBeerDialog(Beer beerDialog) {
		this.beerDialog = beerDialog;
	}

	/**
	 * @return the breweryDialog
	 */
	public Brewery getBreweryDialog() {
		return breweryDialog;
	}

	/**
	 * @param breweryDialog the breweryDialog to set
	 */
	public void setBreweryDialog(Brewery breweryDialog) {
		this.breweryDialog = breweryDialog;
	}

	/**
	 * @return the styleDialog
	 */
	public Style getStyleDialog() {
		return styleDialog;
	}

	/**
	 * @param styleDialog the styleDialog to set
	 */
	public void setStyleDialog(Style styleDialog) {
		this.styleDialog = styleDialog;
	}


	

}
