package org.nbena.beersmanager.exe.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.nbena.beersmanager.coreclasses.Beer;
import org.nbena.beersmanager.exe.Utils;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ViewViewBeer extends ViewAbstractDialog implements BeerDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldABV;
	private JTextField textFieldBeerName;
	private JTextField textFieldBreweryName;
	private JTextField textFieldStyle;
	private JTextField textFieldPrice;
	private JTextField textFieldStars;
	private JTextField textFieldMark;
	private JTextField textFieldTried;
	private JTextField textFieldPlace;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnModify;
	private JButton btnDelete;
	
	private JTextArea textAreaDescription;
	private JButton btnViewStyle;
	private JButton btnViewBrewery;
	private JLabel lblLuogo;
	private JScrollPane scrollPane;

	
	
	@Override
	public void addActionListenerOkButton(ActionListener listener) {
		okButton.addActionListener(listener);
		
	}

	@Override
	public void addActionListenerCancelButton(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	@Override
	public void addActionListenerModifyButton(ActionListener listener) {
		btnModify.addActionListener(listener);
	}
	
	@Override
	public void addActionListenerDeleteButton(ActionListener listener){
		btnDelete.addActionListener(listener);
	}
	
	public void addActionListenerViewBreweryButton(ActionListener listener){
		btnViewBrewery.addActionListener(listener);
	}
	
	public void addActionListenerViewStyleButton(ActionListener listener){
		btnViewStyle.addActionListener(listener);
	}
	
	private void setEditable(){
		textFieldBeerName.setEditable(false);
		textFieldBreweryName.setEditable(false);
		textFieldStyle.setEditable(false);
		textFieldABV.setEditable(false);
		textFieldStars.setEditable(false);
		textFieldMark.setEditable(false);
		textFieldPrice.setEditable(false);
		textFieldTried.setEditable(false);
		textAreaDescription.setEditable(false);
	}
	
	public void setBeerName(String t){
		textFieldBeerName.setText(t);
	}
	
	public void setBreweryName(String t){
		textFieldBreweryName.setText(t);
	}
	
	public void setStyle(String t){
		textFieldStyle.setText(t);
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
	
	public void setTried(boolean tried){
		textFieldTried.setText(Utils.getBooleanItalian(tried));
	}
	
	public void setDescription(String t){
		textAreaDescription.setText(t);
	}
	
	public void setPlace(String t){
		textFieldPlace.setText(t);
	}
	
	public void setPrice(String t){
		textFieldPrice.setText(t);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewViewBeer dialog = new ViewViewBeer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewViewBeer() {
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
			textFieldBreweryName = new JTextField();
			GridBagConstraints gbc_textFieldBreweryName = new GridBagConstraints();
			gbc_textFieldBreweryName.gridwidth = 4;
			gbc_textFieldBreweryName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldBreweryName.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_textFieldBreweryName.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldBreweryName.gridx = 3;
			gbc_textFieldBreweryName.gridy = 4;
			contentPanel.add(textFieldBreweryName, gbc_textFieldBreweryName);
			textFieldBreweryName.setColumns(10);
		}
		{
			btnViewBrewery = new JButton("Vedi");
			GridBagConstraints gbc_btnViewBrewery = new GridBagConstraints();
			gbc_btnViewBrewery.insets = new Insets(0, 0, 5, 0);
			gbc_btnViewBrewery.gridx = 7;
			gbc_btnViewBrewery.gridy = 4;
			contentPanel.add(btnViewBrewery, gbc_btnViewBrewery);
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
			textFieldStyle = new JTextField();
			GridBagConstraints gbc_textFieldStyle = new GridBagConstraints();
			gbc_textFieldStyle.gridwidth = 4;
			gbc_textFieldStyle.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldStyle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldStyle.gridx = 3;
			gbc_textFieldStyle.gridy = 6;
			contentPanel.add(textFieldStyle, gbc_textFieldStyle);
			textFieldStyle.setColumns(10);
		}
		{
			btnViewStyle = new JButton("Vedi");
			GridBagConstraints gbc_btnViewStyle = new GridBagConstraints();
			gbc_btnViewStyle.insets = new Insets(0, 0, 5, 0);
			gbc_btnViewStyle.gridx = 7;
			gbc_btnViewStyle.gridy = 6;
			contentPanel.add(btnViewStyle, gbc_btnViewStyle);
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
		textFieldMark.setColumns(10);
		
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
			gbc_textFieldPrice.anchor = GridBagConstraints.WEST;
			gbc_textFieldPrice.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPrice.gridx = 3;
			gbc_textFieldPrice.gridy = 12;
			contentPanel.add(textFieldPrice, gbc_textFieldPrice);
			textFieldPrice.setColumns(10);
		}
		{
			lblLuogo = new JLabel("Luogo:");
			GridBagConstraints gbc_lblLuogo = new GridBagConstraints();
			gbc_lblLuogo.insets = new Insets(0, 0, 5, 5);
			gbc_lblLuogo.gridx = 5;
			gbc_lblLuogo.gridy = 12;
			contentPanel.add(lblLuogo, gbc_lblLuogo);
		}
		
		{
			textFieldPlace = new JTextField();
			GridBagConstraints gbc_textFieldPlace = new GridBagConstraints();
			gbc_textFieldPlace.anchor = GridBagConstraints.WEST;
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
			scrollPane.setViewportView(textAreaDescription);
			textAreaDescription.setLineWrap(true);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{376, 47, 65, 0};
//			gbl_buttonPane.rowHeights = new int[]{23, 0};
//			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//			buttonPane.setLayout(gbl_buttonPane);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			buttonPane.setLayout(gbl_buttonPane);
			
			GridBagConstraints constraint = new GridBagConstraints();
			constraint.fill = GridBagConstraints.HORIZONTAL;
			constraint.weightx = 0;
			constraint.gridx = 0;
			constraint.gridy = 0;
			
			{
				btnModify = new JButton("Modifica");
				GridBagConstraints gbc_btnModify = new GridBagConstraints();
				gbc_btnModify.gridx = 0;
				gbc_btnModify.gridy = 0;
				gbc_btnModify.weightx = 0;
				buttonPane.add(btnModify, gbc_btnModify);
			}
			
			{
				btnDelete = new JButton("Elimina");
				GridBagConstraints gbc_btnDelete = new GridBagConstraints();
				gbc_btnDelete.gridx = 1;
				gbc_btnDelete.gridy = 0;
				gbc_btnDelete.weightx = 0;
				buttonPane.add(btnDelete, gbc_btnDelete);
			}
			
			
			
			
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
		
		setEditable();
	}


}
