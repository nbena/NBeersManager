package org.nbena.beersmanager.exe.ui.views;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.nbena.beersmanager.exe.ui.models.Model;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
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
	
	private JMenuItem mntmAsPdf;
	private JMenuItem mntmAsXML;
	private JMenuItem mntmAsJSON;
	
	private JMenuItem mntmAsExcelNew;
	private JMenuItem mntmAsExcelOld;
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
	
	
	public void addActionMenuBeersSortedByCountryOfBreweryStyle(ActionListener listener){
		mntmSortBeersByCountryOfBreweryStyle.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByFermentationCountryOfStyleBrewery(ActionListener listener){
		mntmSortBeersByFermentationCountryOfStyleBrewery.addActionListener(listener);
	}
	
	public void addActionMenuBeersSortedByFermentationStyleCountryOfBrewery(ActionListener listener){
		mntmSortBeersByFermentationStyleCountryOfBrewery.addActionListener(listener);
	}
	
	public void setTableModel(AbstractTableModel model){
		table.setModel(model);
	}
	
	
	public void addActionMenuNewStyleFromFile(ActionListener action){
		mntmNewStyleFromFile.addActionListener(action);
	}
	
	public void addActionMenuNewBeerFromFile(ActionListener action){
		mntmNewBeerFromFile.addActionListener(action);
	}
	
	public void addActionMenuNewBreweryFromFile(ActionListener action){
		mntmNewBreweryFromFile.addActionListener(action);
	}
	
	public void addActionMenuAddNewStyle(ActionListener action){
		mntmAddNewStyle.addActionListener(action);
	}
	
	public void addActionMenuAddNewBeer(ActionListener action){
		mntmAddNewBeer.addActionListener(action);
	}
	
	public void addActionMenuAddNewBrewery(ActionListener action){
		mntmAddNewBrewery.addActionListener(action);
	}
	
	public void addActionExportAsPdf(ActionListener action){
		mntmAsPdf.addActionListener(action);
	}
	
	public void addActionExportAsXML(ActionListener action){
		mntmAsXML.addActionListener(action);
	}
	
	public void addActionExportAsJSON(ActionListener action){
		mntmAsJSON.addActionListener(action);
	}
	
	public void addActionExportAsExcelNew(ActionListener action){
		mntmAsExcelNew.addActionListener(action);
	}
	
	public void addActionExportAsExcelOld(ActionListener action){
		mntmAsExcelOld.addActionListener(action);
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
		JMenu mnExport = new JMenu("Export");
		JMenu mnNew = new JMenu("New");
		
		mnFile.add(mnNew);	
		menuBar.add(mnFile);
		mnFile.add(mnImport);
		mnFile.add(mnExport);
		
		
		/*JMenuItem*/ mntmNewStyleFromFile = new JMenuItem("Beer Styles");
		/*JMenuItem*/ mntmNewBeerFromFile = new JMenuItem("Beers");	
		/*JMenuItem*/ mntmNewBreweryFromFile = new JMenuItem("Breweries");
		
		mnImport.add(mntmNewBeerFromFile);
		mnImport.add(mntmNewStyleFromFile);
		mnImport.add(mntmNewBreweryFromFile);
		
		
		

		
		/*JMenuItem */mntmAsPdf = new JMenuItem("As PDF");		
		JMenu mnAsMsExcel = new JMenu("As MS Excel");	
		/*JMenuItem */mntmAsXML = new JMenuItem("As XML");	
		/*JMenuItem */mntmAsJSON = new JMenuItem("As JSON");
		
		mnExport.add(mntmAsPdf);
		mnExport.add(mnAsMsExcel);
		mnExport.add(mntmAsXML);
		mnExport.add(mntmAsJSON);
		
		
		/*JMenuItem */mntmAsExcelNew=  new JMenuItem("2010-2016 file version");
		/*JMenuItem */mntmAsExcelOld= new JMenuItem("2003-2007 file version");
		mnAsMsExcel.add(mntmAsExcelNew);
		mnAsMsExcel.add(mntmAsExcelOld);
		
		
			
		/*JMenuItem */mntmAddNewBeer = new JMenuItem("Beer");
		/*JMenuItem */mntmAddNewBrewery = new JMenuItem("Brewery");
		/*JMenuItem */mntmAddNewStyle = new JMenuItem("Beer Style");
		
		
		mnNew.add(mntmAddNewBeer);
		mnNew.add(mntmAddNewBrewery);
		mnNew.add(mntmAddNewStyle);
		
		
		
		
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
		
		mnOrderBrewery = new JMenu("Brewery");
		mnOrder.add(mnOrderBrewery);
		
		mnOrderStyle = new JMenu("Style");
		mnOrder.add(mnOrderStyle);
		
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
