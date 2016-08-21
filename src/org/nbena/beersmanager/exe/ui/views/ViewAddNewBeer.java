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
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class ViewAddNewBeer extends JDialog implements BeerDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldABV;
	private JTextField textFieldBeerName;
	private JTextField textFieldPrice;
	private JTextField textFieldStars;
	private JTextField textFieldMark;
	private JTextField textFieldPlace;
	
	private JButton okButton;
	private JButton cancelButton;

	
	private JTextArea textAreaDescription;
	private JComboBox<String> comboBoxBrewery;
	private JComboBox<String> comboBoxStyle;
	private JRadioButton rdbtnTriedYes;
	private JRadioButton rdbtnTriedNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JScrollPane scrollPane;
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public void addActionListenerCancelButton(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	
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
	
//	public void setTried(String t){
//		textFieldTried.setText(t);
//	}

	
	public void setTriedButtonDefault(boolean tried){
		setTried(tried);
	}
	
	public void setTried(boolean tried){
		if(tried){
			rdbtnTriedYes.setSelected(true);
		}
		else{
			rdbtnTriedNo.setSelected(true);
		}
	}
	
	public boolean isTried(){
		return rdbtnTriedYes.isSelected();
	}
	
	public void setDescription(String t){
		textAreaDescription.setText(t);
	}
	
	public void setPlace(String t){
		textFieldPlace.setText(t);
	}
	
	public String getBrewery(){
		return (String)comboBoxBrewery.getSelectedItem();
	}
	
	public String getStyle(){
		return (String)comboBoxStyle.getSelectedItem();
	}
	
	public String getBeerName(){
		return textFieldBeerName.getText();
	}
	
	public String getABV(){
		return textFieldABV.getText();
	}
	
	public String getStars(){
		return textFieldStars.getText();
	}
	
	public String getMark(){
		return textFieldMark.getText();
	}
	
//	public String getTried(){
//		return textFieldTried.getText();
//	}
	
	public String getDescription(){
		return textAreaDescription.getText();
	}
	
	public String getPrice(){
		return textFieldPrice.getText();
	}
	
	public String getPlace(){
		return textFieldPlace.getText();
	}
	
//	public void addActionListenerTriedYesRadioButton(ActionListener listener){
//		rdbtnTriedYes.addActionListener(listener);
//	}
//	
//	public void addActionListenerTriedNoRadioButton(ActionListener listener){
//		rdbtnTriedNo.addActionListener(listener);
//	}
	
	public void addActionListenerTriedYesRadioButton(MouseAdapter listener){
		rdbtnTriedYes.addMouseListener(listener);
	}
	
	public void addActionListenerTriedNoRadioButton(MouseAdapter listener){
		rdbtnTriedNo.addMouseListener(listener);
	}
	
	public void setTextFieldPriceEditable(boolean enabled){
		textFieldPrice.setEditable(enabled);
	}
	
	public void setTextFieldMarkEditable(boolean enabled){
		textFieldMark.setEditable(enabled);
	}
	
	public void setPrice(String t){
		textFieldPrice.setText(t);
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
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			gbc_textFieldABV.fill = GridBagConstraints.HORIZONTAL;
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
			gbc_textFieldStars.fill = GridBagConstraints.HORIZONTAL;
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
			gbc_textFieldMark.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldMark.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldMark.gridx = 3;
			gbc_textFieldMark.gridy = 10;
			contentPanel.add(textFieldMark, gbc_textFieldMark);
			textFieldMark.setColumns(10);
		}
		
		{
			textFieldMark.setColumns(10);
		}
		
		{
			JLabel lblProvata = new JLabel("Provata:");
			GridBagConstraints gbc_lblProvata = new GridBagConstraints();
			gbc_lblProvata.insets = new Insets(0, 0, 5, 5);
//			gbc_lblProvata.gridx = 4;
			gbc_lblProvata.gridx = 5;
			gbc_lblProvata.gridy = 10;
			contentPanel.add(lblProvata, gbc_lblProvata);
		}	
		{
			rdbtnTriedYes = new JRadioButton("S\u00EC");
			buttonGroup.add(rdbtnTriedYes);
			GridBagConstraints gbc_rdbtnTriedYes = new GridBagConstraints();
			gbc_rdbtnTriedYes.insets = new Insets(0, 0, 5, 5);
//			gbc_rdbtnTriedYes.gridx = 5;
			gbc_rdbtnTriedYes.gridx = 6;
			gbc_rdbtnTriedYes.gridy = 10;
			contentPanel.add(rdbtnTriedYes, gbc_rdbtnTriedYes);
		}
		{
			rdbtnTriedNo = new JRadioButton("No");
			buttonGroup.add(rdbtnTriedNo);
			GridBagConstraints gbc_rdbtnTriedNo = new GridBagConstraints();
			gbc_rdbtnTriedNo.insets = new Insets(0, 0, 5, 0);
//			gbc_rdbtnTriedNo.gridx = 6;
			gbc_rdbtnTriedNo.gridx = 7;
			gbc_rdbtnTriedNo.gridy = 10;
			contentPanel.add(rdbtnTriedNo, gbc_rdbtnTriedNo);
		}
		
		{
			JLabel lblPrezzo = new JLabel("Prezzo:");
			GridBagConstraints gbc_lblPrezzo = new GridBagConstraints();
			gbc_lblPrezzo.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrezzo.gridx = 1;
			gbc_lblPrezzo.gridy = 12;
			contentPanel.add(lblPrezzo, gbc_lblPrezzo);
		}
		{
			textFieldPrice = new JTextField();
			GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
			gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPrice.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPrice.gridx = 3;
			gbc_textFieldPrice.gridy = 12;
			contentPanel.add(textFieldPrice, gbc_textFieldPrice);
			textFieldPrice.setColumns(10);
		}
		{
			JLabel lblLuogo = new JLabel("Luogo:");
			GridBagConstraints gbc_lblLuogo = new GridBagConstraints();
			gbc_lblLuogo.insets = new Insets(0, 0, 5, 5);
			gbc_lblLuogo.gridx = 5;
			gbc_lblLuogo.gridy = 12;
			contentPanel.add(lblLuogo, gbc_lblLuogo);
		}
		{
			textFieldPlace = new JTextField();
			GridBagConstraints gbc_textFieldPlace = new GridBagConstraints();
			gbc_textFieldPlace.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPlace.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPlace.gridx = 7;
			gbc_textFieldPlace.gridy = 12;
			contentPanel.add(textFieldPlace, gbc_textFieldPlace);
			textFieldPlace.setColumns(10);
		}
		{
			JLabel lblDescrizione = new JLabel("Descrizione:");
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.insets = new Insets(0, 0, 0, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 14;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 5;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 14;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		{
			textAreaDescription = new JTextArea();
			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
			gbc_textAreaDescription.gridwidth = 5;
			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
			gbc_textAreaDescription.gridx = 3;
			gbc_textAreaDescription.gridy = 14;
//			contentPanel.add(textAreaDescription, gbc_textAreaDescription);
			textAreaDescription.setLineWrap(true);
			scrollPane.setViewportView(textAreaDescription);
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
