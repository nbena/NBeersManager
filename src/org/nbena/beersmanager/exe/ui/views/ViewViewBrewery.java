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

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.nbena.beersmanager.coreclasses.Brewery;
import org.nbena.beersmanager.exe.Utils;
import javax.swing.JScrollPane;

//public class ViewViewBrewery extends JDialog {
@SuppressWarnings("serial")
public class ViewViewBrewery extends ViewAbstractDialog implements BreweryDialog{
	
//	public class ViewViewBrewery extends JDialog implements ViewAbstractDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textFieldName;
	private JTextField textFieldTown;
	private JTextField textFieldCountry;
	private JTextField textFieldWebsite;
	private JEditorPane editorDescription;
	private JTextField textFieldAverage;
	private JTextField textFieldTrappist;
	
	private JButton okButton;
	private JButton cancelButton; 
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnViewBeers;
	private JScrollPane scrollPane;
	
	
//	public String getBreweryName(){
//		return textFieldName.getText();
//	}
//	
//	public String getBreweryTown(){
//		return textFieldTown.getText();
//	}
//	
//	public String getBreweryCountry(){
//		//return textFieldCountry.getText();
//		return null;
//	}
//	
//	public String getBreweryWebsite(){
//		return textFieldWebsite.getText();
//	}
//	
//	public String getDescription(){
//		return textAreaDescription.getText();
//	}
	
	
	public void setBreweryName(String text){
		textFieldName.setText(text);
	}
	
	public void setBreweryTown(String text){
		textFieldTown.setText(text);
	}
	
	public void setBreweryCountry(String text){
		textFieldCountry.setText(text);
	}
	
	public void setBreweryWebsite(String text){
		textFieldWebsite.setText(text);
	}
	
	public void setBreweryDescription(String text){
		editorDescription.setText(text);
		editorDescription.setCaretPosition(0);
	}
	
	public void setContentType(String type){
		editorDescription.setContentType(type);
	}
	
	public void setBreweryAverage(String text){
		textFieldAverage.setText(text);
	}
	
	public void setBreweryTrappist(boolean trappist){
		textFieldTrappist.setText(Utils.getBooleanItalian(trappist));
	}
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public void addActionListenerCancelButton(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	public void addActionListenerModifyButton(ActionListener listener){
		btnModify.addActionListener(listener);
	}
	
	@Override
	public void addActionListenerDeleteButton(ActionListener listener){
		btnDelete.addActionListener(listener);
	}
	
	public void addActionListenerViewBeersButton(ActionListener listener){
		btnViewBeers.addActionListener(listener);
	}
	
	public void addLinkListener(MouseListener listener){
//		textFieldWebsite.addH
		textFieldWebsite.addMouseListener(listener);
	}
	
	public void setWebsiteCursorHand(){
		textFieldWebsite.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void setWebsiteCursorDefault(){
		textFieldWebsite.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	
	
	
//	public void setEditable(boolean editable){
//		textFieldName.setEditable(editable);
//		textFieldTown.setEditable(editable);
//		//textFieldCountry.setEditable(editable);
//		textFieldWebsite.setEditable(editable);
//		textAreaDescription.setEditable(editable);
//	}
	
	private void setEditable(){
		textFieldName.setEditable(false);
		textFieldTown.setEditable(false);
		textFieldCountry.setEditable(false);
		textFieldWebsite.setEditable(false);
		editorDescription.setEditable(false);
		textFieldAverage.setEditable(false);
		textFieldTrappist.setEditable(false);
	}
	
	@Deprecated
	public void setBrewery(Brewery b){
		textFieldName.setText(b.getBreweryName());
		textFieldTown.setText(b.getTown());
		textFieldCountry.setText(b.getCountry());
		textFieldWebsite.setText(b.getWebsite());
		editorDescription.setText(b.getBreweryDescription());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewViewBrewery dialog = new ViewViewBrewery();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewViewBrewery() {
		setBounds(100, 100, 509, 525);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitleLabel = new JLabel("");
			GridBagConstraints gbc_lblTitleLabel = new GridBagConstraints();
			gbc_lblTitleLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitleLabel.gridx = 0;
			gbc_lblTitleLabel.gridy = 0;
			contentPanel.add(lblTitleLabel, gbc_lblTitleLabel);
		}
		{
			JLabel lblNane = new JLabel("Name:");
			GridBagConstraints gbc_lblNane = new GridBagConstraints();
			gbc_lblNane.insets = new Insets(0, 0, 5, 5);
			gbc_lblNane.gridx = 1;
			gbc_lblNane.gridy = 2;
			contentPanel.add(lblNane, gbc_lblNane);
		}
		{
			textFieldName = new JTextField();
			GridBagConstraints gbc_textFieldName = new GridBagConstraints();
			gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldName.gridx = 3;
			gbc_textFieldName.gridy = 2;
			contentPanel.add(textFieldName, gbc_textFieldName);
			textFieldName.setColumns(10);
		}
		{
			JLabel lblTown = new JLabel("Citt\u00E0:");
			GridBagConstraints gbc_lblTown = new GridBagConstraints();
			gbc_lblTown.insets = new Insets(0, 0, 5, 5);
			gbc_lblTown.gridx = 1;
			gbc_lblTown.gridy = 4;
			contentPanel.add(lblTown, gbc_lblTown);
		}
		{
			textFieldTown = new JTextField();
			GridBagConstraints gbc_textFieldTown = new GridBagConstraints();
			gbc_textFieldTown.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldTown.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTown.gridx = 3;
			gbc_textFieldTown.gridy = 4;
			contentPanel.add(textFieldTown, gbc_textFieldTown);
			textFieldTown.setColumns(10);
		}
		{
			JLabel lblNazione = new JLabel("Nazione:");
			GridBagConstraints gbc_lblNazione = new GridBagConstraints();
			gbc_lblNazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazione.gridx = 1;
			gbc_lblNazione.gridy = 6;
			contentPanel.add(lblNazione, gbc_lblNazione);
		}
		{
			textFieldCountry = new JTextField();
			GridBagConstraints gbc_textFieldCountry = new GridBagConstraints();
			gbc_textFieldCountry.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCountry.gridx = 3;
			gbc_textFieldCountry.gridy = 6;
			contentPanel.add(textFieldCountry, gbc_textFieldCountry);
			textFieldCountry.setColumns(10);
		}
		{
			JLabel lblWeb = new JLabel("Web:");
			GridBagConstraints gbc_lblWeb = new GridBagConstraints();
			gbc_lblWeb.insets = new Insets(0, 0, 5, 5);
			gbc_lblWeb.gridx = 1;
			gbc_lblWeb.gridy = 8;
			contentPanel.add(lblWeb, gbc_lblWeb);
		}
		{
			textFieldWebsite = new JTextField();
			GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
			gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldWebsite.gridx = 3;
			gbc_textFieldWebsite.gridy = 8;
			contentPanel.add(textFieldWebsite, gbc_textFieldWebsite);
			textFieldWebsite.setColumns(10);
		}
		{
			JLabel lblDescrizione = new JLabel("Descrizione:");
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.fill = GridBagConstraints.VERTICAL;
			gbc_lblDescrizione.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 10;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			editorDescription = new JEditorPane();
//			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
//			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
//			gbc_textAreaDescription.gridx = 3;
//			gbc_textAreaDescription.gridy = 10;
//			contentPanel.add(textAreaDescription, gbc_textAreaDescription);
//			textAreaDescription.setLineWrap(true);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 10;
			contentPanel.add(scrollPane, gbc_scrollPane);
			
			scrollPane.setViewportView(editorDescription);
		}
		
		
		
		{
			JLabel lblTrappist = new JLabel("Birrificio trappista:");
			GridBagConstraints gbc_lblTrappist = new GridBagConstraints();
			gbc_lblTrappist.insets = new Insets(0, 0, 5, 5);
			gbc_lblTrappist.gridx = 1;
			gbc_lblTrappist.gridy = 12;
			contentPanel.add(lblTrappist, gbc_lblTrappist);
		}
		{
			textFieldTrappist = new JTextField();
			GridBagConstraints gbc_textFieldTrappist = new GridBagConstraints();
			gbc_textFieldTrappist.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTrappist.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldTrappist.gridx = 3;
			gbc_textFieldTrappist.gridy = 12;
			contentPanel.add(textFieldTrappist, gbc_textFieldTrappist);
			textFieldTrappist.setColumns(7);
		}
		
		{
			JLabel lblAverage = new JLabel("Media voti birre:");
			GridBagConstraints gbc_lblAverage = new GridBagConstraints();
			gbc_lblAverage.insets = new Insets(0, 0, 0, 5);
			gbc_lblAverage.gridx = 1;
			gbc_lblAverage.gridy = 14;
			contentPanel.add(lblAverage, gbc_lblAverage);
		}
		{
			textFieldAverage = new JTextField();
			GridBagConstraints gbc_textFieldAverage = new GridBagConstraints();
			gbc_textFieldAverage.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldAverage.gridx = 3;
			gbc_textFieldAverage.gridy = 14;
			contentPanel.add(textFieldAverage, gbc_textFieldAverage);
			textFieldAverage.setColumns(7);
		}
		
		
		
//		{
//			JPanel buttonPane = new JPanel();
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{369, 47, 67, 0};
//			gbl_buttonPane.rowHeights = new int[]{23, 0};
//			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//			buttonPane.setLayout(gbl_buttonPane);
//			{
//				btnModify = new JButton("Modifica");
//				GridBagConstraints gbc_btnModify = new GridBagConstraints();
//				gbc_btnModify.anchor = GridBagConstraints.WEST;
//				gbc_btnModify.insets = new Insets(0, 0, 0, 5);
//				gbc_btnModify.gridx = 0;
//				gbc_btnModify.gridy = 0;
//				buttonPane.add(btnModify, gbc_btnModify);
//			}
//			{
//			    okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				GridBagConstraints gbc_okButton = new GridBagConstraints();
//				gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
//				gbc_okButton.insets = new Insets(0, 0, 0, 5);
//				gbc_okButton.gridx = 1;
//				gbc_okButton.gridy = 0;
//				buttonPane.add(okButton, gbc_okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				cancelButton = new JButton("Annulla");
//				cancelButton.setActionCommand("Cancel");
//				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
//				gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
//				gbc_cancelButton.gridx = 2;
//				gbc_cancelButton.gridy = 0;
//				buttonPane.add(cancelButton, gbc_cancelButton);
//			}
//		}
		
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
				btnViewBeers=new JButton("Vedi birre");
				GridBagConstraints gbc_btnViewBeers = new GridBagConstraints();
				gbc_btnViewBeers.gridx = 2;
				gbc_btnViewBeers.gridy = 0;
				gbc_btnViewBeers.weightx = 0;
				buttonPane.add(btnViewBeers, gbc_btnViewBeers);
			}
			
			
			
			
			{
				okButton = new JButton("Ok");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
//				gbc_okButton.gridx = 2;
				gbc_okButton.gridx = 3;
				gbc_okButton.gridy = 0;
				gbc_okButton.weightx = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
//				gbc_cancelButton.gridx = 3;
				gbc_cancelButton.gridx = 4;
				gbc_cancelButton.gridy = 0;
				gbc_cancelButton.weightx = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
		
		setEditable();
	}

}
