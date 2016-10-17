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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.nbena.beersmanager.coreclasses.Style;
import javax.swing.JScrollPane;

//public class ViewViewStyle extends JDialog {
@SuppressWarnings("serial")
public class ViewViewStyle extends ViewAbstractDialog implements StyleDialog{
//public class ViewViewStyle extends JDialog implements ViewAbstractDialog{

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textFieldStyleMainCategory;
	private JTextField textFieldStyleSubcategory;
	private JTextField textFieldStyleCountry;
	private JTextField textFieldFermentation;
	
	private JEditorPane editorDescription;
	
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnViewBeers;
	private JScrollPane scrollPane;
	
	public void addActionListenerViewBeersButton(ActionListener listener){
		btnViewBeers.addActionListener(listener);
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
	
	
	public void setStyle(Style style){
		textFieldStyleMainCategory.setText(style.getStyleMainName());
		textFieldStyleSubcategory.setText(style.getStyleSubCategory());
		textFieldStyleCountry.setText(style.getStyleCountryOrigin());
		textFieldFermentation.setText(style.getFermentation().toFirstUpperCase());
		editorDescription.setText(style.getDescription());
	}
	
	private void setEditable(){
		textFieldStyleMainCategory.setEditable(false);
		textFieldStyleSubcategory.setEditable(false);
		textFieldStyleCountry.setEditable(false);
		textFieldFermentation.setEditable(false);
		editorDescription.setEditable(false);
	}
	
	public void setStyleMainName(String t){
		textFieldStyleMainCategory.setText(t);
	}
	
	public void setStyleSubcategory(String t){
		textFieldStyleSubcategory.setText(t);
	}
	
	public void setStyleCountry(String t){
		textFieldStyleCountry.setText(t);
	}
	
	public void setFermentation(String t){
		textFieldFermentation.setText(t);
	}
	
	public void setDescription(String t){
		editorDescription.setText(t);
		editorDescription.setCaretPosition(0);
	}
	
	public void setContentType(String type){
		editorDescription.setContentType(type);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewViewStyle dialog = new ViewViewStyle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewViewStyle() {
		setBounds(100, 100, 509, 446);
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
			JLabel lblStyleMain = new JLabel("Stile principale:");
			GridBagConstraints gbc_lblStyleMain = new GridBagConstraints();
			gbc_lblStyleMain.insets = new Insets(0, 0, 5, 5);
			gbc_lblStyleMain.gridx = 1;
			gbc_lblStyleMain.gridy = 2;
			contentPanel.add(lblStyleMain, gbc_lblStyleMain);
		}
		{
			textFieldStyleMainCategory = new JTextField();
			GridBagConstraints gbc_textFieldStyleMainCategory = new GridBagConstraints();
			gbc_textFieldStyleMainCategory.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldStyleMainCategory.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldStyleMainCategory.gridx = 3;
			gbc_textFieldStyleMainCategory.gridy = 2;
			contentPanel.add(textFieldStyleMainCategory, gbc_textFieldStyleMainCategory);
			textFieldStyleMainCategory.setColumns(10);
		}
		{
			JLabel lblSottostile = new JLabel("Sottostile:");
			GridBagConstraints gbc_lblSottostile = new GridBagConstraints();
			gbc_lblSottostile.insets = new Insets(0, 0, 5, 5);
			gbc_lblSottostile.gridx = 1;
			gbc_lblSottostile.gridy = 4;
			contentPanel.add(lblSottostile, gbc_lblSottostile);
		}
		{
			textFieldStyleSubcategory = new JTextField();
			GridBagConstraints gbc_textFieldStyleSubcategory = new GridBagConstraints();
			gbc_textFieldStyleSubcategory.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldStyleSubcategory.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldStyleSubcategory.gridx = 3;
			gbc_textFieldStyleSubcategory.gridy = 4;
			contentPanel.add(textFieldStyleSubcategory, gbc_textFieldStyleSubcategory);
			textFieldStyleSubcategory.setColumns(10);
		}
		
//		{
//			textFieldStyleSubcategory = new JTextField();
//			GridBagConstraints gbc_textFieldStyleSubcategory = new GridBagConstraints();
//			gbc_textFieldStyleSubcategory.insets = new Insets(0, 0, 5, 0);
//			gbc_textFieldStyleSubcategory.fill = GridBagConstraints.HORIZONTAL;
//			gbc_textFieldStyleSubcategory.gridx = 3;
//			gbc_textFieldStyleSubcategory.gridy = 4;
//			contentPanel.add(textFieldStyleSubcategory, gbc_textFieldStyleSubcategory);
//			textFieldStyleSubcategory.setColumns(10);
//		}
		
		{
			JLabel lblFermentation = new JLabel("Fermentazione:");
			GridBagConstraints gbc_lblFermentation = new GridBagConstraints();
			gbc_lblFermentation.insets = new Insets(0, 0, 5, 5);
			gbc_lblFermentation.gridx = 1;
			gbc_lblFermentation.gridy = 6;
			contentPanel.add(lblFermentation, gbc_lblFermentation);
		}
		{
			textFieldFermentation = new JTextField();
			GridBagConstraints gbc_textFieldFermentation = new GridBagConstraints();
			gbc_textFieldFermentation.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldFermentation.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldFermentation.gridx = 3;
			gbc_textFieldFermentation.gridy = 6;
			contentPanel.add(textFieldFermentation, gbc_textFieldFermentation);
			textFieldFermentation.setColumns(10);
		}
		{
			JLabel lblOriginCountry = new JLabel("Paese d'origine:");
			GridBagConstraints gbc_lblOriginCountry = new GridBagConstraints();
			gbc_lblOriginCountry.insets = new Insets(0, 0, 5, 5);
			gbc_lblOriginCountry.gridx = 1;
			gbc_lblOriginCountry.gridy = 8;
			contentPanel.add(lblOriginCountry, gbc_lblOriginCountry);
		}
		{
			textFieldStyleCountry = new JTextField();
			GridBagConstraints gbc_textFieldStyleCountry = new GridBagConstraints();
			gbc_textFieldStyleCountry.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldStyleCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldStyleCountry.gridx = 3;
			gbc_textFieldStyleCountry.gridy = 8;
			contentPanel.add(textFieldStyleCountry, gbc_textFieldStyleCountry);
			textFieldStyleCountry.setColumns(10);
		}
		{
			JLabel lblDescrizione = new JLabel("Descrizione:");
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.insets = new Insets(0, 0, 0, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 10;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 10;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		{
			editorDescription = new JEditorPane();
//			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
//			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
//			gbc_textAreaDescription.gridx = 3;
//			gbc_textAreaDescription.gridy = 10;
//			contentPanel.add(editorDescription, gbc_textAreaDescription);
//			textAreaDescription.setLineWrap(true);
			scrollPane.setViewportView(editorDescription);
		}
//		{
//			JPanel buttonPane = new JPanel();
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{371, 47, 65, 0};
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
//				okButton = new JButton("OK");
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
//				cancelButton = new JButton("Cancel");
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
				gbc_okButton.gridx = 3;
//				gbc_okButton.gridx = 2;
				gbc_okButton.gridy = 0;
				gbc_okButton.weightx = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.gridx = 4;
//				gbc_cancelButton.gridx = 3;
				gbc_cancelButton.gridy = 0;
				gbc_cancelButton.weightx = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
		
		setEditable();
	}

}
