package org.nbena.beersmanager.exe.ui.views;



import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import org.nbena.beersmanager.exe.ui.models.Model;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTable;
import java.awt.GridBagConstraints;

public class ViewMainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6973734778443148904L;
	private JPanel contentPane;
	
	
	private JMenuItem mntmNewStyleFromFile;
	private JMenuItem mntmNewBeerFromFile;
	private JMenuItem mntmNewBreweryFromFile;

	
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
	
	private JMenuItem [] mntmMainStyles;
	private JMenuItem [] mntmStyles;
	private JMenuItem [] mntmCountries;
	private JMenuItem [] mntmBreweries;
	
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
	
	
	public void addActionMenuSearchBeer(ActionListener listener){
		mntmSearchBeer.addActionListener(listener);
	}
	
	public void addActionMenuSearchBrewery(ActionListener listener){
		mntmSearchBrewery.addActionListener(listener);
	}
	
	public void addActionMenuSearchStyle(ActionListener listener){
		mntmSearchStyle.addActionListener(listener);
	}
	
	
	public void addActionMenuBeersFilteredByCountryOfBreweryStyle(ActionListener listener){
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
	
	
	
	public void addActionMenuStylesSortedByFermentationThenCountry(ActionListener listener){
		mntmStylesSortedByFermentationThenCountry.addActionListener(listener);
	}
	
	public void addActionMenuStylesSortedByCountryThenFermentation(ActionListener listener){
		mntmStylesSortedByCountryThenFermentation.addActionListener(listener);
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
	
	
	
	public void setTableModel(AbstractTableModel model){
		table.setModel(model);
	}
	
	
	public void addActionMenuNewStyleFromFile(ActionListener listener){
		mntmNewStyleFromFile.addActionListener(listener);
	}
	
	public void addActionMenuNewBeerFromFile(ActionListener listener){
		mntmNewBeerFromFile.addActionListener(listener);
	}
	
	public void addActionMenuNewBreweryFromFile(ActionListener listener){
		mntmNewBreweryFromFile.addActionListener(listener);
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
	
	
	public void addTableListSelectionListener(ListSelectionListener listener){
		table.getSelectionModel().addListSelectionListener(listener);
	}
	
	public int getTableSelectedRow(){
		return table.getSelectedRow();
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

	private void setFilters(JFileChooser chooser, FileNameExtensionFilter[] filters){
		for(FileNameExtensionFilter filter: filters){
			chooser.setFileFilter(filter);
		}
	}
	
	public JFileChooser getJFileChooser(){
		return chooser;
	}
	
	public void initJFileChooser(FileNameExtensionFilter[] filters, File directory){
		chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		setFilters(chooser, filters);
		
		chooser.setCurrentDirectory(directory);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
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
		
		
		JMenu mnImport = new JMenu("Import");
		JMenu mnNew = new JMenu("New");
		
		mnFile.add(mnNew);	
		menuBar.add(mnFile);
		mnFile.add(mnImport);
		
		
		/*JMenuItem*/ mntmNewStyleFromFile = new JMenuItem("Beer Styles");
		/*JMenuItem*/ mntmNewBeerFromFile = new JMenuItem("Beers");	
		/*JMenuItem*/ mntmNewBreweryFromFile = new JMenuItem("Breweries");
		
		mnImport.add(mntmNewBeerFromFile);
		mnImport.add(mntmNewStyleFromFile);
		mnImport.add(mntmNewBreweryFromFile);
		
		
			
		/*JMenuItem */mntmAddNewBeer = new JMenuItem("Beer");
		/*JMenuItem */mntmAddNewBrewery = new JMenuItem("Brewery");
		/*JMenuItem */mntmAddNewStyle = new JMenuItem("Beer Style");
		
		
		mnNew.add(mntmAddNewBeer);
		mnNew.add(mntmAddNewBrewery);
		mnNew.add(mntmAddNewStyle);
		
		mntmExport = new JMenuItem("Export");
		mnFile.add(mntmExport);
		
		
		
		
		JMenu mnQuery = new JMenu("View");
		menuBar.add(mnQuery);
		
		mntmViewBeers = new JMenuItem("Beers");
		mnQuery.add(mntmViewBeers);
		
		mntmViewBreweries = new JMenuItem("Breweries");
		mnQuery.add(mntmViewBreweries);
		
		mntmViewStyles = new JMenuItem("Styles");
		mnQuery.add(mntmViewStyles);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnOptions.add(mntmPreferences);
		
		JMenu mnOrder = new JMenu("Order");
		menuBar.add(mnOrder);
		
		mnOrderBeer = new JMenu("Beer");
		mnOrder.add(mnOrderBeer);
		
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
		
		mnOrderBrewery = new JMenu("Brewery");
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
		
		mnOrderStyle = new JMenu("Style");
		mnOrder.add(mnOrderStyle);
		
		mntmStylesSortedByFermentationThenCountry = new JMenuItem("Fermentazione, nazione origine stile");
		mnOrderStyle.add(mntmStylesSortedByFermentationThenCountry);
		
		mntmStylesSortedByCountryThenFermentation = new JMenuItem("Nazione origine stile, fermentazione");
		mnOrderStyle.add(mntmStylesSortedByCountryThenFermentation);
		
		JMenu mnFilter = new JMenu("Filter");
		menuBar.add(mnFilter);
		
		mnFilterBeer = new JMenu("Beer");
		mnFilter.add(mnFilterBeer);
		
		mntmBeersFilteredByStyle = new JMenuItem("Stile e sottostile");
		mnFilterBeer.add(mntmBeersFilteredByStyle);
		
		mntmBeersFilteredByMainStyle = new JMenuItem("Solo stile principale");
		mnFilterBeer.add(mntmBeersFilteredByMainStyle);
		
		mntmBeersFilteredByBrewery = new JMenuItem("Birrificio");
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
		
		mnFilterBrewery = new JMenu("Brewery");
		mnFilter.add(mnFilterBrewery);
		
		mnFilterStyle = new JMenu("Style");
		mnFilter.add(mnFilterStyle);
		
		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		mntmSearchBeer = new JMenuItem("Birra");
		mnSearch.add(mntmSearchBeer);
		
		mntmSearchBrewery = new JMenuItem("Birrificio");
		mnSearch.add(mntmSearchBrewery);
		
		mntmSearchStyle = new JMenuItem("Stile");
		mnSearch.add(mntmSearchStyle);
		
		
		
		
		//mnFile.add(mntmExport);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		table = new JTable(model.getTableModel());
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		contentPane.add(table, gbc_table);
		
		//table.get
		
		
	}

}
