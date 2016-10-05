/*   NBeersManager: manages what you drink.

    Copyright (C) 2016  Nicola Bena

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    */
package org.nbena.beersmanager.exe.ui.views;






import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import org.nbena.beersmanager.exe.Utils;
import org.nbena.beersmanager.exe.ui.models.Model;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ViewMainGUI extends JFrame {

	private JPanel contentPane;
	
	
	private JMenuItem mntmNewStylesFromFile;
	private JMenuItem mntmNewBeersFromFile;
	private JMenuItem mntmNewBreweriesFromFile;

	
	private JMenuItem mntmAddNewBeer;
	private JMenuItem mntmAddNewStyle;
	private JMenuItem mntmAddNewBrewery;
	private JTable table;
	
	private JMenuItem mntmViewBeers;
	private JMenuItem mntmViewBreweries;
	private JMenuItem mntmViewStyles;
	
	private JMenuItem mnOrderBeer;
	private JMenuItem mnOrderBrewery;
	private JMenuItem mnOrderStyle;
	
	private JMenuItem mnFilterBeer;
	private JMenuItem mnFilterBrewery;
	private JMenuItem mnFilterStyle;
	
	private JMenuItem mntmSortBeersByCountryOfBreweryStyle;
	private JMenuItem mntmSortBeersByFermentationCountryOfStyleBrewery;
	private JMenuItem mntmSortBeersByFermentationStyleCountryOfBrewery;
	private JMenuItem mntmBeersFilteredByStyle;
	private JMenuItem mntmBeersFilteredByMainStyle;
	private JMenuItem mntmBeersFilteredByBrewery;
	private JMenu mnProvata;
	private JMenuItem mntmBeersFilteredByIsTriedYes;
	private JMenuItem mntmBeersFilteredByIsTriedNo;
	private JMenu mnVoto;
	private JMenuItem mntmBeersFilteredByMiminumMark;
	private JMenuItem mntmBeersFilteredByExactMark;
	private JMenu mnStelle;
	private JMenuItem mntmBeersFilteredByMinimumNumberOfStars;
	private JMenuItem mntBeersFilteredByExactNumberOfStars;
	private JMenu mnABV;
	private JMenuItem mntmBeersFilteredByMinimumAlcool;
	private JMenuItem mntmBeersFilteredByExactAlcool;
	private JMenu mnTrappista;
	private JMenu mnFermentazione;
	private JMenuItem mntmBeersFilteredByBreweryCountry;
	private JMenuItem mntmBeersFiltedredByStyleProvenience;
	private JMenuItem mntmBeersFilteredByFermentationHigh;
	private JMenuItem mntmBeersFilteredByFermentationLow;
	private JMenuItem mntmBeersFilteredByFermentationSpontaneous;
	private JMenuItem mntmBeersFilteredByTrappistYes;
	private JMenuItem mntmBeersFiltedredByTrappistNo;

	
	
	private JMenu mnSearch;
	private JMenuItem mntmSearchBeer;
	private JMenuItem mntmSearchBrewery;
	private JMenuItem mntmSearchStyle;
	private JMenuItem mntmExport;
	
	private JFileChooser chooser;
	private JMenu mnMarkStar;
	private JMenu mnStarMark;
	
	private JMenuItem mntmBeersSortedByMarkStarDescending;
	private JMenuItem mntmBeersSortedByMarkStarAscending;
	private JMenuItem mntmBeersSortedByStarMarkDescending;
	private JMenuItem mntmBeersSortedByStarMarkAscending;
	private JMenuItem mntmStylesSortedByFermentationThenCountry;
	private JMenuItem mntmStylesSortedByCountryThenFermentation;
	private JMenuItem mntmBreweriesSortedByCountryThenName;
	private JMenuItem mntmBreweriesSortedByName;
	private JMenu mnBreweriesSortedByAverage;
	private JMenu mnBreweriesSortedByCountryThenAverage;
	private JMenuItem mntmBreweriesSortedByAverageDescending;
	private JMenuItem mntmBreweriesSortedByAverageAscending;
	private JMenuItem mntmBreweriesSortedByCountryThenAverageDescending;
	private JMenuItem mntmBreweriesSortedByCountryThenAverageAscending;
	
	private JMenuItem mntmPreferences;
	private JMenuItem mntmAbout;
	private JButton btnSave;
	private JMenuItem mntmSave;
	private JButton btnReferesh;
	private JMenu mnTrappista_1;
	private JMenuItem mntmBreweriesFilteredByTrappistYes;
	private JMenuItem mntmBreweriesFilteredByTrappistNo;
	private JMenuItem mntmNazione;
	private JMenu mnFermentazione_1;
	private JMenuItem mntmStylesFilteredByFermentationHigh;
	private JMenuItem mntmStylesFilteredByFermentationLow;
	private JMenuItem mntmStylesFilteredByFermentationSpontaneous;
	private JMenuItem mntmStylesFilteredByMainStyle;
	private JMenuItem mntmStylesFilteredByCountryOrigin;
	private JMenuItem mntmBeersFilteredByPlaceTried;
	private JPopupMenu popupMenu;
	private JMenuItem mntmViewThingsTable;
	private JMenuItem mntmModifyThingsTable;
	private JMenuItem mntmDeleteThingsTable;
	private JScrollPane scrollPane;
	private JMenuItem mntmStylesSortedByFermentationCategorySubcategory;
	private JMenu mnBeersSortedByABV;
	private JMenuItem mntmBeersSortedByABVAscending;
	private JMenuItem mntmBeersSortedByABVDescending;
	private JMenu mnBeersSortedByPrice;
	private JMenuItem mntmBeersSortedByPriceAscending;
	private JMenuItem mntmBeersSortedByPriceDescending;
	private JMenuItem mntmBeersSortedByName;
	
	
	
	
	public void addPopupListener(PopupMenuListener listener){
		popupMenu.addPopupMenuListener(listener);
	}
	
	
	public void setPopupMenuViewThingsTableEnabled(boolean enabled){
		mntmViewThingsTable.setEnabled(enabled);
	}
	
	public void setPopoupMenuModifyThingsTableEnabled(boolean enabled){
		mntmModifyThingsTable.setEnabled(enabled);
	}
	
	public void addActionPopupMenuViewThings(ActionListener listener){
		mntmViewThingsTable.addActionListener(listener);
	}
	
	public void addActionPopupMenuModifyThings(ActionListener listener){
		mntmModifyThingsTable.addActionListener(listener);
	}
	
	public void addActionPopupMenuDeleteThings(ActionListener listener){
		mntmDeleteThingsTable.addActionListener(listener);
	}
	
	public void showPopupMenu(boolean show){
		popupMenu.setVisible(show);
	}
	
	public int getRowAtPoint(Point p){
		return table.rowAtPoint(p);
	}
	

	
	public void setSelectedRow(int startRow, int endRow){
		table.setRowSelectionInterval(startRow, endRow);
	}
	
	//done here because I don't like the getPopup and getTable
	public int getPointForPopup(){
		return  table.rowAtPoint(Utils.getPointForPopupMenu(popupMenu, table));
	}
	
	public int getNewPointForPopoup(){
		return table.rowAtPoint(Utils.getPointForPopupMenu(popupMenu, MouseInfo.getPointerInfo().getLocation(), table));
	}
	
	public void addActionRefreshButton(ActionListener listener){
		btnReferesh.addActionListener(listener);
	}
	
	public void addActionMenuSaveAndSaveButton(ActionListener listener){
		mntmSave.addActionListener(listener);
		btnSave.addActionListener(listener);
	}
	
	
	public void addActionMenuPreferences(ActionListener listener){
		mntmPreferences.addActionListener(listener);
	}
	
	public void addActionMenuAbout(ActionListener listener){
		mntmAbout.addActionListener(listener);
	}
	
	
	public void addActionMenuSearchBeer(ActionListener listener){
		mntmSearchBeer.addActionListener(listener);
	}
	
	public void addActionMenuSearchBrewery(ActionListener listener){
		mntmSearchBrewery.addActionListener(listener);
	}
	
	public void addActionMenuSearchStyle(ActionListener listener){
		mntmSearchStyle.addActionListener(listener);
	}
	
	
	
	public void addActionMenuBreweriesFilteredByNation(ActionListener listener){
		mntmNazione.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesFilteredByTrappistYes(ActionListener listener){
		mntmBreweriesFilteredByTrappistYes.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesFilteredByTrappistNo(ActionListener listener){
		mntmBreweriesFilteredByTrappistNo.addActionListener(listener);
	}
	
	
	
	public void addActionMenuStylesFilteredByFermentationHigh(ActionListener listener){
		mntmStylesFilteredByFermentationHigh.addActionListener(listener);
	}
	
	public void addActionMenuStylesFilteredByFermentationLow(ActionListener listener){
		mntmStylesFilteredByFermentationLow.addActionListener(listener);
	}
	
	public void addActionMenuStylesFilteredByFermentationSpontaneous(ActionListener listener){
		mntmStylesFilteredByFermentationSpontaneous.addActionListener(listener);
	}
	
	public void addActionMenuStylesFilteredByCountryOrigin(ActionListener listener){
		mntmStylesFilteredByCountryOrigin.addActionListener(listener);
	}
	
	public void addActionMenuStylesFilteredByMainStyle(ActionListener listener){
		mntmStylesFilteredByMainStyle.addActionListener(listener);
	}
	
	
	
	public void addActionMenuBeersFilteredByStyle(ActionListener listener){
		mntmBeersFilteredByStyle.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByMainStyle(ActionListener listener){
		mntmBeersFilteredByMainStyle.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByBrewery(ActionListener listener){
		mntmBeersFilteredByBrewery.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByIsTriedYes(ActionListener listener){
		mntmBeersFilteredByIsTriedYes.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredIsTriedNo(ActionListener listener){
		mntmBeersFilteredByIsTriedNo.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByMinimumMark(ActionListener listener){
		mntmBeersFilteredByMiminumMark.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByExactMark(ActionListener listener){
		mntmBeersFilteredByExactMark.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredExactNumberOfStars(ActionListener listener){
		mntBeersFilteredByExactNumberOfStars.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredMinimumNumberOfStars(ActionListener listener){
		mntmBeersFilteredByMinimumNumberOfStars.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByMinimumABV(ActionListener listener){
		mntmBeersFilteredByMinimumAlcool.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByExactABV(ActionListener listener){
		mntmBeersFilteredByExactAlcool.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByIsTrappistYes(ActionListener listener){
		mntmBeersFilteredByTrappistYes.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByIsTrappistNo(ActionListener listener){
		mntmBeersFiltedredByTrappistNo.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByFermentationHigh(ActionListener listener){
		mntmBeersFilteredByFermentationHigh.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByFermentationLow(ActionListener listener){
		mntmBeersFilteredByFermentationLow.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByFermentationSpontaneous(ActionListener listener){
		mntmBeersFilteredByFermentationSpontaneous.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByStyleProvenience(ActionListener listener){
		mntmBeersFiltedredByStyleProvenience.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByBreweryCountry(ActionListener listener){
		mntmBeersFilteredByBreweryCountry.addActionListener(listener);
	}
	
	public void addActionMenuBeersFilteredByPlaceTried(ActionListener listener){
		mntmBeersFilteredByPlaceTried.addActionListener(listener);
	}
	
	
	
	public void addActionMenuStylesSortedByFermentationThenCountry(ActionListener listener){
		mntmStylesSortedByFermentationThenCountry.addActionListener(listener);
	}
	
	public void addActionMenuStylesSortedByCountryThenFermentation(ActionListener listener){
		mntmStylesSortedByCountryThenFermentation.addActionListener(listener);
	}
	
	public void addActionMenuStylesSortedByFermentationCategorySubcategory(ActionListener listener){
		mntmStylesSortedByFermentationCategorySubcategory.addActionListener(listener);
	}
	
	
	
	public void addActionMenuBreweriesSortedByCountryThenName(ActionListener listener){
		mntmBreweriesSortedByCountryThenName.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesSortedByCountryThenAverageAscending(ActionListener listener){
		mntmBreweriesSortedByCountryThenAverageAscending.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesSortedByAverageAscending(ActionListener listener){
		mntmBreweriesSortedByAverageAscending.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesSortedByCountryThenAverageDescending(ActionListener listener){
		mntmBreweriesSortedByCountryThenAverageDescending.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesSortedByAverageDescending(ActionListener listener){
		mntmBreweriesSortedByAverageDescending.addActionListener(listener);
	}
	
	public void addActionMenuBreweriesSortedByName(ActionListener listener){
		mntmBreweriesSortedByName.addActionListener(listener);
	}

	
	
	
	public void addActionMenuBeersSortedByCountryOfBreweryStyle(ActionListener listener){
		mntmSortBeersByCountryOfBreweryStyle.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByFermentationCountryOfStyleBrewery(ActionListener listener){
		mntmSortBeersByFermentationCountryOfStyleBrewery.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByFermentationStyleCountryOfBrewery(ActionListener listener){
		mntmSortBeersByFermentationStyleCountryOfBrewery.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByMarkStarAscending(ActionListener listener){
		mntmBeersSortedByMarkStarAscending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByMarkStarDescending(ActionListener listener){
		mntmBeersSortedByMarkStarDescending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByStarMarkAscending(ActionListener listener){
		mntmBeersSortedByStarMarkAscending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByStarMarkDescending(ActionListener listener){
		mntmBeersSortedByStarMarkDescending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByABVAscending(ActionListener listener){
		mntmBeersSortedByABVAscending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByABVDescending(ActionListener listener){
		mntmBeersSortedByABVDescending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByPriceAscending(ActionListener listener){
		mntmBeersSortedByPriceAscending.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByPriceDescending(ActionListener listener){
		mntmBeersSortedByPriceDescending.addActionListener(listener);
	}
	
	public void addActionMenubeersSortedByName(ActionListener listener){
		mntmBeersSortedByName.addActionListener(listener);
	}
	
	
	public void setTableModel(AbstractTableModel model){
		table.setModel(model);
	}
	
	public void addMouseListener(MouseListener listener){
		table.addMouseListener(listener);
	}
	
	
	public void addActionMenuNewStyleFromFile(ActionListener listener){
		mntmNewStylesFromFile.addActionListener(listener);
	}
	
	public void addActionMenuNewBeerFromFile(ActionListener listener){
		mntmNewBeersFromFile.addActionListener(listener);
	}
	
	public void addActionMenuNewBreweryFromFile(ActionListener listener){
		mntmNewBreweriesFromFile.addActionListener(listener);
	}
	
	public void addActionMenuAddNewStyle(ActionListener listener){
		mntmAddNewStyle.addActionListener(listener);
	}
	
	public void addActionMenuAddNewBeer(ActionListener listener){
		mntmAddNewBeer.addActionListener(listener);
	}
	
	public void addActionMenuAddNewBrewery(ActionListener listener){
		mntmAddNewBrewery.addActionListener(listener);
	}
	
	
	
//	public void addActionMenuExportAsPdf(ActionListener listener){
//		mntmExportAsPdf.addActionListener(listener);
//	}
//	
//	public void addActionMenuExportAsTXT(ActionListener listener){
//		mntmExportAsTxt.addActionListener(listener);
//	}
//	
//	public void addActionMenuExportAsJSON(ActionListener listener){
//		mntmExportAsJSON.addActionListener(listener);
//	}
//	
//	public void addActionMenuExportAsExcelNew(ActionListener listener){
//		mntmAsExcelNew.addActionListener(listener);
//	}
//	
//	public void addActionMenuExportAsExcelOld(ActionListener listener){
//		mntmAsExcelOld.addActionListener(listener);
//	}
	
	
	public void addActionMenuExport(ActionListener listener){
		mntmExport.addActionListener(listener);
	}
	
	
	public void addActionMenuImportBeers(ActionListener listener){
		mntmNewBeersFromFile.addActionListener(listener);
	}
	
	
	public void addActionMenuImportBreweries(ActionListener listener){
		mntmNewBreweriesFromFile.addActionListener(listener);
	}
	
	public void addActionMenuImportStyles(ActionListener listener){
		mntmNewStylesFromFile.addActionListener(listener);
	}
	
	
	public void addTableListSelectionListener(ListSelectionListener listener){
		table.getSelectionModel().addListSelectionListener(listener);
	}
	
	public int getTableSelectedRow(){
		return table.getSelectedRow();
	}
	
	public int[]  getTableSelectedRows(){
		return table.getSelectedRows();
	}
	
	public void noRowSelected(){
		table.clearSelection();
	}
	
	
	public void setQueryEnabledItemsBeer(boolean enabled){
		mnOrderBeer.setEnabled(enabled);
		mnFilterBeer.setEnabled(enabled);
	}
	
	public void setQueryEnabledItemsBrewerie(boolean enabled){
		mnOrderBrewery.setEnabled(enabled);
		mnFilterBrewery.setEnabled(enabled);
	}
	
	public void setQueryEnabledItemsStyle(boolean enabled){
		mnOrderStyle.setEnabled(enabled);
		mnFilterStyle.setEnabled(enabled);
	}
	
	
	public void addActionListenerViewBeer(ActionListener listener){
		mntmViewBeers.addActionListener(listener);
	}
	
	public void addActionListenerViewBrewery(ActionListener listener){
		mntmViewBreweries.addActionListener(listener);
	}
	
	public void addActionListenerViewStyle(ActionListener listener){
		mntmViewStyles.addActionListener(listener);
	}

	private static void setFilters(JFileChooser chooser, FileNameExtensionFilter[] filters){
		for(FileNameExtensionFilter filter: filters){
			chooser.setFileFilter(filter);
		}
	}
	
	public JFileChooser getJFileChooser(){
		return chooser;
	}
	
	//static because I need to call when the gui is not ready
	public static JFileChooser initJFileChooser(FileNameExtensionFilter[] filters, File directory, boolean multiSelectionEnabled){
		JFileChooser chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		setFilters(chooser, filters);
		
		chooser.setCurrentDirectory(directory);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(multiSelectionEnabled);
		return chooser;
	}
	
	
	public void setJFileChooserTitle(String title){
		chooser.setDialogTitle(title);
	}
	
	public void setJFileChooserToolTip(String toolTip){
		chooser.setToolTipText(toolTip);
	}
	
	
	public void setExportEnabled(boolean enabled){
		mntmExport.setEnabled(enabled);
	}
	
	
	public void addActionListenerOnClosing(WindowListener listener){
		addWindowListener(listener);
	}
	
	public void setDoNothingOnClose(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	


	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMainGUI frame = new ViewMainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public ViewMainGUI(Model model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 634);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		
		
		JMenu mnImport = new JMenu("Importa");
		
		mntmSave = new JMenuItem("Salva");
		mnFile.add(mntmSave);
		JMenu mnNew = new JMenu("Nuovo");
		
		mnFile.add(mnNew);	
		menuBar.add(mnFile);
		mnFile.add(mnImport);
		
		
		/*JMenuItem*/ mntmNewStylesFromFile = new JMenuItem("Stile");
		/*JMenuItem*/ mntmNewBeersFromFile = new JMenuItem("Birra");	
		/*JMenuItem*/ mntmNewBreweriesFromFile = new JMenuItem("Birrificio");
		
		mnImport.add(mntmNewBeersFromFile);
		mnImport.add(mntmNewStylesFromFile);
		mnImport.add(mntmNewBreweriesFromFile);
		
		
			
		/*JMenuItem */mntmAddNewBeer = new JMenuItem("Birra");
		/*JMenuItem */mntmAddNewBrewery = new JMenuItem("Birrificio");
		/*JMenuItem */mntmAddNewStyle = new JMenuItem("Stile");
		
		
		mnNew.add(mntmAddNewBeer);
		mnNew.add(mntmAddNewBrewery);
		mnNew.add(mntmAddNewStyle);
		
		mntmExport = new JMenuItem("Esporta");
		mnFile.add(mntmExport);
		
		btnSave = new JButton("Salva");
		menuBar.add(btnSave);
		
		btnReferesh = new JButton("Referesh");
		menuBar.add(btnReferesh);
		
		
		
		
		JMenu mnQuery = new JMenu("Vedi");
		menuBar.add(mnQuery);
		
		mntmViewBeers = new JMenuItem("Birre");
		mnQuery.add(mntmViewBeers);
		
		mntmViewBreweries = new JMenuItem("Birrifici");
		mnQuery.add(mntmViewBreweries);
		
		mntmViewStyles = new JMenuItem("Stili");
		mnQuery.add(mntmViewStyles);
		
		JMenu mnOrder = new JMenu("Ordina");
		menuBar.add(mnOrder);
		
		mnOrderBeer = new JMenu("Birra");
		mnOrder.add(mnOrderBeer);
		
		mntmBeersSortedByName = new JMenuItem("Nome");
		mnOrderBeer.add(mntmBeersSortedByName);
		
		mntmSortBeersByCountryOfBreweryStyle = new JMenuItem("Nazione e nome birrificio, stile");
		mnOrderBeer.add(mntmSortBeersByCountryOfBreweryStyle);
		
		mntmSortBeersByFermentationCountryOfStyleBrewery = new JMenuItem("Nazione origine stile e stile, birrificio");
		mnOrderBeer.add(mntmSortBeersByFermentationCountryOfStyleBrewery);
		
		mntmSortBeersByFermentationStyleCountryOfBrewery = new JMenuItem("Stile, nazione e nome birrifcio");
		mnOrderBeer.add(mntmSortBeersByFermentationStyleCountryOfBrewery);
		
		mnMarkStar = new JMenu("Voto, stelle");
		mnOrderBeer.add(mnMarkStar);
		
		mntmBeersSortedByMarkStarDescending = new JMenuItem("Discendente");
		mnMarkStar.add(mntmBeersSortedByMarkStarDescending);
		
		mntmBeersSortedByMarkStarAscending = new JMenuItem("Ascendente");
		mnMarkStar.add(mntmBeersSortedByMarkStarAscending);
		
		mnStarMark = new JMenu("Stelle, voto");
		mnOrderBeer.add(mnStarMark);
		
		mntmBeersSortedByStarMarkDescending = new JMenuItem("Discendente");
		mnStarMark.add(mntmBeersSortedByStarMarkDescending);
		
		mntmBeersSortedByStarMarkAscending = new JMenuItem("Ascendente");
		mnStarMark.add(mntmBeersSortedByStarMarkAscending);
		
		mnBeersSortedByABV = new JMenu ("ABV%");
		mntmBeersSortedByABVAscending = new JMenuItem("Ascendente");
		mntmBeersSortedByABVDescending = new JMenuItem("Discendente");
		mnBeersSortedByABV.add(mntmBeersSortedByABVAscending);
		mnBeersSortedByABV.add(mntmBeersSortedByABVDescending);
		
		mnBeersSortedByPrice = new JMenu ("Prezzo");
		mntmBeersSortedByPriceAscending = new JMenuItem("Ascendente");
		mntmBeersSortedByPriceDescending = new JMenuItem("Discendente");
		mnBeersSortedByPrice.add(mntmBeersSortedByPriceDescending);
		mnBeersSortedByPrice.add(mntmBeersSortedByPriceAscending);
		
		mnOrderBeer.add(mnBeersSortedByABV);
		mnOrderBeer.add(mnBeersSortedByPrice);
		
		mnOrderBrewery = new JMenu("Birrificio");
		mnOrder.add(mnOrderBrewery);
		
		mntmBreweriesSortedByCountryThenName = new JMenuItem("Nazione, nome");
		mnOrderBrewery.add(mntmBreweriesSortedByCountryThenName);
		
		mntmBreweriesSortedByName = new JMenuItem("Nome");
		mnOrderBrewery.add(mntmBreweriesSortedByName);
		
		mnBreweriesSortedByAverage = new JMenu("Voto medio");
		mnOrderBrewery.add(mnBreweriesSortedByAverage);
		
		mntmBreweriesSortedByAverageDescending = new JMenuItem("Discendente");
		mnBreweriesSortedByAverage.add(mntmBreweriesSortedByAverageDescending);
		
		mntmBreweriesSortedByAverageAscending = new JMenuItem("Ascendente");
		mnBreweriesSortedByAverage.add(mntmBreweriesSortedByAverageAscending);
		
		mnBreweriesSortedByCountryThenAverage = new JMenu("Nazione, voto medio");
		mnOrderBrewery.add(mnBreweriesSortedByCountryThenAverage);
		
		mntmBreweriesSortedByCountryThenAverageDescending = new JMenuItem("Discendente");
		mnBreweriesSortedByCountryThenAverage.add(mntmBreweriesSortedByCountryThenAverageDescending);
		
		mntmBreweriesSortedByCountryThenAverageAscending = new JMenuItem("Ascendente");
		mnBreweriesSortedByCountryThenAverage.add(mntmBreweriesSortedByCountryThenAverageAscending);
		
		mnOrderStyle = new JMenu("Stile");
		mnOrder.add(mnOrderStyle);
		
		mntmStylesSortedByFermentationThenCountry = new JMenuItem("Fermentazione, nazione origine stile");
		mnOrderStyle.add(mntmStylesSortedByFermentationThenCountry);
		
		mntmStylesSortedByCountryThenFermentation = new JMenuItem("Nazione origine stile, fermentazione");
		mnOrderStyle.add(mntmStylesSortedByCountryThenFermentation);
		
		mntmStylesSortedByFermentationCategorySubcategory = new JMenuItem("Fermentazione, stile, sottostile");
		mnOrderStyle.add(mntmStylesSortedByFermentationCategorySubcategory);
		
		JMenu mnFilter = new JMenu("Filtra");
		menuBar.add(mnFilter);
		
		mnFilterBeer = new JMenu("Birre");
		mnFilter.add(mnFilterBeer);
		
		mntmBeersFilteredByStyle = new JMenuItem("Stile e sottostile");
		mnFilterBeer.add(mntmBeersFilteredByStyle);
		
		mntmBeersFilteredByMainStyle = new JMenuItem("Solo stile principale");
		mnFilterBeer.add(mntmBeersFilteredByMainStyle);
		
		mntmBeersFilteredByBrewery = new JMenuItem("Birrifici");
		mnFilterBeer.add(mntmBeersFilteredByBrewery);
		
		mnProvata = new JMenu("Provata");
		mnFilterBeer.add(mnProvata);
		
		mntmBeersFilteredByIsTriedYes = new JMenuItem("S\u00EC");
		mnProvata.add(mntmBeersFilteredByIsTriedYes);
		
		mntmBeersFilteredByIsTriedNo = new JMenuItem("No");
		mnProvata.add(mntmBeersFilteredByIsTriedNo);
		
		mnVoto = new JMenu("Voto");
		mnFilterBeer.add(mnVoto);
		
		mntmBeersFilteredByMiminumMark = new JMenuItem("Minimo");
		mnVoto.add(mntmBeersFilteredByMiminumMark);
		
		mntmBeersFilteredByExactMark = new JMenuItem("Esatto");
		mnVoto.add(mntmBeersFilteredByExactMark);
		
		mnStelle = new JMenu("Stelle");
		mnFilterBeer.add(mnStelle);
		
		mntmBeersFilteredByMinimumNumberOfStars = new JMenuItem("Minime");
		mnStelle.add(mntmBeersFilteredByMinimumNumberOfStars);
		
		mntBeersFilteredByExactNumberOfStars = new JMenuItem("Esatte");
		mnStelle.add(mntBeersFilteredByExactNumberOfStars);
		
		mnABV = new JMenu("ABV%");
		mnFilterBeer.add(mnABV);
		
		mntmBeersFilteredByMinimumAlcool = new JMenuItem("Minimo");
		mnABV.add(mntmBeersFilteredByMinimumAlcool);
		
		mntmBeersFilteredByExactAlcool = new JMenuItem("Esatto");
		mnABV.add(mntmBeersFilteredByExactAlcool);
		
		mnTrappista = new JMenu("Trappista");
		mnFilterBeer.add(mnTrappista);
		
		mntmBeersFilteredByTrappistYes = new JMenuItem("S\u00EC");
		mnTrappista.add(mntmBeersFilteredByTrappistYes);
		
		mntmBeersFiltedredByTrappistNo = new JMenuItem("No");
		mnTrappista.add(mntmBeersFiltedredByTrappistNo);
		
		mnFermentazione = new JMenu("Fermentazione");
		mnFilterBeer.add(mnFermentazione);
		
		mntmBeersFilteredByFermentationHigh = new JMenuItem("Alta");
		mnFermentazione.add(mntmBeersFilteredByFermentationHigh);
		
		mntmBeersFilteredByFermentationLow = new JMenuItem("Bassa");
		mnFermentazione.add(mntmBeersFilteredByFermentationLow);
		
		mntmBeersFilteredByFermentationSpontaneous = new JMenuItem("Spontanea");
		mnFermentazione.add(mntmBeersFilteredByFermentationSpontaneous);
		
		mntmBeersFilteredByBreweryCountry = new JMenuItem("Nazione");
		mnFilterBeer.add(mntmBeersFilteredByBreweryCountry);
		
		mntmBeersFiltedredByStyleProvenience = new JMenuItem("Nazione origine stile");
		mnFilterBeer.add(mntmBeersFiltedredByStyleProvenience);
		
		mntmBeersFilteredByPlaceTried = new JMenuItem("Luogo di bevuta");
		mnFilterBeer.add(mntmBeersFilteredByPlaceTried);
		
		mnFilterBrewery = new JMenu("Birricio");
		mnFilter.add(mnFilterBrewery);
		
		mnTrappista_1 = new JMenu("Trappista");
		mnFilterBrewery.add(mnTrappista_1);
		
		mntmBreweriesFilteredByTrappistYes = new JMenuItem("S\u00EC");
		mnTrappista_1.add(mntmBreweriesFilteredByTrappistYes);
		
		mntmBreweriesFilteredByTrappistNo = new JMenuItem("No");
		mnTrappista_1.add(mntmBreweriesFilteredByTrappistNo);
		
		mntmNazione = new JMenuItem("Nazione");
		mnFilterBrewery.add(mntmNazione);
		
		mnFilterStyle = new JMenu("Stili");
		mnFilter.add(mnFilterStyle);
		
		mnFermentazione_1 = new JMenu("Fermentazione");
		mnFilterStyle.add(mnFermentazione_1);
		
		mntmStylesFilteredByFermentationHigh = new JMenuItem("Alta");
		mnFermentazione_1.add(mntmStylesFilteredByFermentationHigh);
		
		mntmStylesFilteredByFermentationLow = new JMenuItem("Bassa");
		mnFermentazione_1.add(mntmStylesFilteredByFermentationLow);
		
		mntmStylesFilteredByFermentationSpontaneous = new JMenuItem("Spontanea");
		mnFermentazione_1.add(mntmStylesFilteredByFermentationSpontaneous);
		
		mntmStylesFilteredByMainStyle = new JMenuItem("Stile principale");
		mnFilterStyle.add(mntmStylesFilteredByMainStyle);
		
		mntmStylesFilteredByCountryOrigin = new JMenuItem("Nazione origine");
		mnFilterStyle.add(mntmStylesFilteredByCountryOrigin);
		
		mnSearch = new JMenu("Search");
//		menuBar.add(mnSearch);	maybe in future.
		
		mntmSearchBeer = new JMenuItem("Birra");
		mnSearch.add(mntmSearchBeer);
		
		mntmSearchBrewery = new JMenuItem("Birrificio");
		mnSearch.add(mntmSearchBrewery);
		
		mntmSearchStyle = new JMenuItem("Stile");
		mnSearch.add(mntmSearchStyle);
		
		JMenu mnOptions = new JMenu("Opzioni");
		menuBar.add(mnOptions);
		
		mntmPreferences = new JMenuItem("Preferenze");
		mnOptions.add(mntmPreferences);
		
		mntmAbout = new JMenuItem("A proposito");
		mnOptions.add(mntmAbout);
		
		
		
		
		//mnFile.add(mntmExport);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model.getTableModel());
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
//		contentPane.add(table, gbc_table);
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
//		GridBagConstraints gbc_popupMenu = new GridBagConstraints();
//		gbc_popupMenu.gridx = 0;
//		gbc_popupMenu.gridy = 1;
//		table.add(popupMenu, gbc_popupMenu);
		
		
		
		mntmViewThingsTable = new JMenuItem("Vedi");
		popupMenu.add(mntmViewThingsTable);
		
		//table.get
		mntmModifyThingsTable = new JMenuItem("Modifica");
		popupMenu.add(mntmModifyThingsTable);
		
		mntmDeleteThingsTable = new JMenuItem("Elimina");
		popupMenu.add(mntmDeleteThingsTable);
		
		table.setComponentPopupMenu(popupMenu);
		
		table.getTableHeader().setResizingAllowed(true);

		
		
	}

}
