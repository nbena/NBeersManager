package org.nbena.beersmanager.exe.views;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.nbena.beersmanager.exe.models.Model;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
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
		
		
		
		
		JMenu mnQuery = new JMenu("Query");
		menuBar.add(mnQuery);
		
		JMenu mnBeers = new JMenu("Beers");
		mnQuery.add(mnBeers);
		
		JMenuItem mntmSpecificQueryComposerBeers = new JMenuItem("Specific Query Composer");
		mnBeers.add(mntmSpecificQueryComposerBeers);
		
		JMenu mnBreweries = new JMenu("Breweries");
		mnQuery.add(mnBreweries);
		
		JMenuItem mntmSpecificQueryComposerBreweries = new JMenuItem("Specific Query Composer");
		mnBreweries.add(mntmSpecificQueryComposerBreweries);
		
		JMenu mnStyles = new JMenu("Styles");
		mnQuery.add(mnStyles);
		
		JMenuItem mntmSpecificQueryComposerStyles = new JMenuItem("Specific Query Composer");
		mnStyles.add(mntmSpecificQueryComposerStyles);
		
		JMenu mnViewAll = new JMenu("View all");
		mnQuery.add(mnViewAll);
		
		JMenuItem mntmGropuByCountry = new JMenuItem("Gropu by country, brewery, fermentation, style");
		mnViewAll.add(mntmGropuByCountry);
		
		JMenuItem mntmGroupByFermentaion = new JMenuItem("Group by fermentaion, style, country, brewery");
		mnViewAll.add(mntmGroupByFermentaion);
		
		JMenuItem mntmStupidAlphabeticalOrder = new JMenuItem("Stupid alphabetical order");
		mnViewAll.add(mntmStupidAlphabeticalOrder);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnOptions.add(mntmPreferences);
		
		
		
		
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
		
		
	}

}
