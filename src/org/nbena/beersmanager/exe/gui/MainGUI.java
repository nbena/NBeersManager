package org.nbena.beersmanager.exe.gui;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6973734778443148904L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 634);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnImport = new JMenu("Import");
		mnFile.add(mnImport);
		
		JMenu mnFromFile = new JMenu("From File");
		mnImport.add(mnFromFile);
		
		JMenu mnFromScratch = new JMenu("From scratch");
		mnImport.add(mnFromScratch);
		
		JMenuItem mntmNewStyleFromFile = new JMenuItem("New Style");
		mnFromFile.add(mntmNewStyleFromFile);
		
		JMenuItem mntmNewBeerFromFile = new JMenuItem("New Beer");
		mnFromFile.add(mntmNewBeerFromFile);
		
		
		
		JMenuItem mntmNewBreweryFromFile = new JMenuItem("New Brewery");
		mnFromFile.add(mntmNewBreweryFromFile);
		
		JMenuItem mntmNewStyleFromScratch = new JMenuItem("New Style");
		mnFromScratch.add(mntmNewStyleFromScratch);
		
		JMenuItem mntmNewBeerFromScratch = new JMenuItem("New Beer");
		mnFromScratch.add(mntmNewBeerFromScratch);
		
		JMenuItem mntmNewBreweryFromScratch = new JMenuItem("New Brewery");
		mnFromScratch.add(mntmNewBreweryFromScratch);
		
		
		
		JMenu mnExport = new JMenu("Export");
		mnFile.add(mnExport);
		
		JMenuItem mntmAsPdf = new JMenuItem("As PDF");
		mnExport.add(mntmAsPdf);
		
		JMenu mnAsMsExcel = new JMenu("As MS Excel");
		mnExport.add(mnAsMsExcel);
		
		JMenuItem mntmFileVersion = new JMenuItem("2010-2016 file version");
		mnAsMsExcel.add(mntmFileVersion);
		
		JMenuItem mntmFileVersion_1 = new JMenuItem("2003-2007 file version");
		mnAsMsExcel.add(mntmFileVersion_1);
		
		JMenuItem mntmAsXML = new JMenuItem("As XML");
		mnExport.add(mntmAsXML);
		
		JMenuItem mntmAsJson = new JMenuItem("As JSON");
		mnExport.add(mntmAsJson);
		
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
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}

}
