package org.nbena.beersmanager.exe.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;

public class ViewAddNewBeer extends JDialog implements BeerDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldABV;
	private JTextField textFieldBeerName;
	private JTextField textFieldPrice;
	private JTextField textFieldStars;
	private JTextField textFieldMark;
	private JTextField textFieldTried;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnModify;
	private JButton btnDelete;
	
	private JTextArea textAreaDescription;
	private JComboBox<String> comboBoxBrewery;
	private JComboBox<String> comboBoxStyle;
	
	
	public void fillThings(List<String> breweries, List<String> styles){
		fillBreweries(breweries);
		fillStyles(styles);
	}
	
	private void fillBreweries(List<String> breweries){
		for(String s: breweries){
			comboBoxBrewery.addItem(s);
		}
	}
	
	private void fillStyles(List<String> styles){
		for(String s: styles){
			comboBoxStyle.addItem(s);
		}
	}
	
	public void setBeerName(String t){
		textFieldBeerName.setText(t);
	}
	
	public void setBreweryName(String t){
		comboBoxBrewery.setSelectedItem(t);
	}
	
	public void setStyle(String t){
		comboBoxStyle.setSelectedItem(t);
	}
	
	public void setABV(String t){
		textFieldABV.setText(t);
	}
	
	public void setStars(String t){
		textFieldStars.setText(t);
	}
	
	public void setMark(String t){
		textFieldMark.setText(t);
	}
	
	public void setTried(String t){
		textFieldTried.setText(t);
	}
	
	public void setDescription(String t){
		textAreaDescription.setText(t);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewAddNewBeer dialog = new ViewAddNewBeer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewAddNewBeer() {
		setBounds(100, 100, 509, 599);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitle = new JLabel("");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		{
			JLabel lblNome = new JLabel("Nome:");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.gridx = 1;
			gbc_lblNome.gridy = 2;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			textFieldBeerName = new JTextField();
			GridBagConstraints gbc_textFieldBeerName = new GridBagConstraints();
			gbc_textFieldBeerName.gridwidth = 5;
			gbc_textFieldBeerName.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_textFieldBeerName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldBeerName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldBeerName.gridx = 3;
			gbc_textFieldBeerName.gridy = 2;
			contentPanel.add(textFieldBeerName, gbc_textFieldBeerName);
			textFieldBeerName.setColumns(10);
		}
		{
			JLabel lblBirrificio = new JLabel("Birrificio:");
			GridBagConstraints gbc_lblBirrificio = new GridBagConstraints();
			gbc_lblBirrificio.insets = new Insets(0, 0, 5, 5);
			gbc_lblBirrificio.gridx = 1;
			gbc_lblBirrificio.gridy = 4;
			contentPanel.add(lblBirrificio, gbc_lblBirrificio);
		}
		{
			comboBoxBrewery = new JComboBox();
			GridBagConstraints gbc_comboBoxBrewery = new GridBagConstraints();
			gbc_comboBoxBrewery.gridwidth = 5;
			gbc_comboBoxBrewery.insets = new Insets(0, 0, 5, 0);
			gbc_comboBoxBrewery.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxBrewery.gridx = 3;
			gbc_comboBoxBrewery.gridy = 4;
			contentPanel.add(comboBoxBrewery, gbc_comboBoxBrewery);
		}
		{
			JLabel lblStile = new JLabel("Stile:");
			GridBagConstraints gbc_lblStile = new GridBagConstraints();
			gbc_lblStile.insets = new Insets(0, 0, 5, 5);
			gbc_lblStile.gridx = 1;
			gbc_lblStile.gridy = 6;
			contentPanel.add(lblStile, gbc_lblStile);
		}
		{
			comboBoxStyle = new JComboBox();
			GridBagConstraints gbc_comboBoxStyle = new GridBagConstraints();
			gbc_comboBoxStyle.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxStyle.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxStyle.gridx = 3;
			gbc_comboBoxStyle.gridy = 6;
			contentPanel.add(comboBoxStyle, gbc_comboBoxStyle);
		}
		{
			JLabel lblAbv = new JLabel("ABV%:");
			GridBagConstraints gbc_lblAbv = new GridBagConstraints();
			gbc_lblAbv.insets = new Insets(0, 0, 5, 5);
			gbc_lblAbv.gridx = 1;
			gbc_lblAbv.gridy = 8;
			contentPanel.add(lblAbv, gbc_lblAbv);
		}
		{
			textFieldABV = new JTextField();
			GridBagConstraints gbc_textFieldABV = new GridBagConstraints();
			gbc_textFieldABV.anchor = GridBagConstraints.WEST;
			gbc_textFieldABV.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldABV.gridx = 3;
			gbc_textFieldABV.gridy = 8;
			contentPanel.add(textFieldABV, gbc_textFieldABV);
			textFieldABV.setColumns(10);
		}
		
		{
			JLabel lblStars = new JLabel("Stelle:");
			GridBagConstraints gbc_lblStars = new GridBagConstraints();
			gbc_lblStars.insets = new Insets(0, 0, 5, 5);
			gbc_lblStars.gridx = 5;
			gbc_lblStars.gridy = 8;
			contentPanel.add(lblStars, gbc_lblStars);
		}
		
		{
			textFieldStars = new JTextField();
			GridBagConstraints gbc_textFieldStars = new GridBagConstraints();
			gbc_textFieldStars.anchor = GridBagConstraints.WEST;
			gbc_textFieldStars.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldStars.gridx = 7;
			gbc_textFieldStars.gridy = 8;
			contentPanel.add(textFieldStars, gbc_textFieldStars);
			textFieldStars.setColumns(10);
		}
		
		
		
		{
			JLabel lblVoto = new JLabel("Voto:");
			GridBagConstraints gbc_lblVoto = new GridBagConstraints();
			gbc_lblVoto.insets = new Insets(0, 0, 5, 5);
			gbc_lblVoto.gridx = 1;
			gbc_lblVoto.gridy = 10;
			contentPanel.add(lblVoto, gbc_lblVoto);
		}
		
		{
			textFieldMark = new JTextField();
			GridBagConstraints gbc_textFieldMark = new GridBagConstraints();
			gbc_textFieldMark.anchor = GridBagConstraints.WEST;
			gbc_textFieldMark.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldMark.gridx = 3;
			gbc_textFieldMark.gridy = 10;
			contentPanel.add(textFieldMark, gbc_textFieldMark);
			textFieldMark.setColumns(10);
		}
		
		{
			JLabel lblProvata = new JLabel("Provata:");
			GridBagConstraints gbc_lblProvata = new GridBagConstraints();
			gbc_lblProvata.insets = new Insets(0, 0, 5, 5);
			gbc_lblProvata.gridx = 5;
			gbc_lblProvata.gridy = 10;
			contentPanel.add(lblProvata, gbc_lblProvata);
		}	
		
		{
			textFieldTried = new JTextField();
			GridBagConstraints gbc_textFieldTried = new GridBagConstraints();
			gbc_textFieldTried.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTried.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldTried.gridx = 7;
			gbc_textFieldTried.gridy = 10;
			contentPanel.add(textFieldTried, gbc_textFieldTried);
			textFieldMark.setColumns(10);
		}
		
		{
			JLabel lblPrezzo = new JLabel("Prezzo:");
			GridBagConstraints gbc_lblPrezzo = new GridBagConstraints();
			gbc_lblPrezzo.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrezzo.gridx = 1;
			gbc_lblPrezzo.gridy = 14;
			contentPanel.add(lblPrezzo, gbc_lblPrezzo);
		}
		{
			textFieldPrice = new JTextField();
			GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
			gbc_textFieldPrice.anchor = GridBagConstraints.WEST;
			gbc_textFieldPrice.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPrice.gridx = 3;
			gbc_textFieldPrice.gridy = 14;
			contentPanel.add(textFieldPrice, gbc_textFieldPrice);
			textFieldPrice.setColumns(10);
		}
		{
			JLabel lblDescrizione = new JLabel("Descrizione:");
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.insets = new Insets(0, 0, 0, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 16;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			textAreaDescription = new JTextArea();
			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
			gbc_textAreaDescription.gridwidth = 5;
			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
			gbc_textAreaDescription.gridx = 3;
			gbc_textAreaDescription.gridy = 16;
			contentPanel.add(textAreaDescription, gbc_textAreaDescription);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{376, 47, 65, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);

			
			
			
			
			
			
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.gridx = 2;
				gbc_okButton.gridy = 0;
				gbc_okButton.weightx = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.gridx = 3;
				gbc_cancelButton.gridy = 0;
				gbc_cancelButton.weightx = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
		
		
	}

}
