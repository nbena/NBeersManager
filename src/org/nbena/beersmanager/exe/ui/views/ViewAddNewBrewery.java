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


import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import org.nbena.beersmanager.coreclasses.Brewery;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ViewAddNewBrewery extends JDialog implements BreweryDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldTown;
	private JTextField textFieldWebsite;
	private JTextField textFieldAverage;
	
	private JEditorPane editorDescription;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private JRadioButton rdbtnTrappistYes;
	private JRadioButton rdbtnTrappistNo;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JComboBox<String> comboBoxCountry;
	
	private JScrollPane scrollPane;
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public void addActionListenerCancelButton(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	public void fillThings(List<String> countries){
		for(String s: countries){
			comboBoxCountry.addItem(s);
		}
	}
	
	
	
	public String getBreweryName(){
		return textFieldName.getText();
	}
	
	public String getBreweryTown(){
		return textFieldTown.getText();
	}
	
	public String getBreweryCountry(){
		return (String)comboBoxCountry.getSelectedItem();
	}
	
	public String getBreweryWebsite(){
		return textFieldWebsite.getText();
	}
	
	public String getDescription(){
		return editorDescription.getText();
	}
	
	public boolean isTrappist(){
		return rdbtnTrappistYes.isSelected();
	}
	
	
	public void setBreweryName(String text){
		textFieldName.setText(text);
	}
	
	public void setBreweryTown(String text){
		textFieldTown.setText(text);
	}
	
	public void setBreweryCountry(String text){
		comboBoxCountry.setSelectedItem(text);
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
	
	public void setBreweryAverage(String t) {
		textFieldAverage.setText(t);
	}
	
	public void setBreweryTrappistDefault(boolean trappist){
		setBreweryTrappist(trappist);
	}
	
	public void setBreweryTrappist(boolean trappist){
		if(trappist){
			rdbtnTrappistYes.setSelected(true);
		}
		else{
			rdbtnTrappistNo.setSelected(false);
		}
	}
	
	public void setOkButtonEnabled(boolean enabled){
		okButton.setEnabled(enabled);
	}

	
	/**
	public void setEditable(boolean editable){
		textFieldName.setEditable(editable);
		textFieldTown.setEditable(editable);
		//textFieldCountry.setEditable(editable);
		textFieldWebsite.setEditable(editable);
		textAreaDescription.setEditable(editable);
	}
	*/
	@Deprecated
	public void setBrewery(Brewery b){
		textFieldName.setText(b.getBreweryName());
		textFieldTown.setText(b.getTown());
		comboBoxCountry.setSelectedItem(b.getCountry());
		textFieldWebsite.setText(b.getWebsite());
		editorDescription.setText(b.getBreweryDescription());
	}
	
	public void addDocumentListenerName(DocumentListener listener){
		textFieldName.getDocument().addDocumentListener(listener);
	}
	
	public void addDocumentListenerTown(DocumentListener listener){
		textFieldTown.getDocument().addDocumentListener(listener);
	}
	
	public void addDocumentListenerWebsite(DocumentListener listener){
		textFieldWebsite.getDocument().addDocumentListener(listener);
	}
	
	
	public void setBreweryNameToolTip(String text){
		textFieldName.setToolTipText(text);
	}
	
	public void setBreweryTownToolTip(String text){
		textFieldTown.setToolTipText(text);
//		ToolTipManager.sharedInstance().unregisterComponent(textFieldTown);
	}
	
	public void removeToolTipName(){
		setBreweryNameToolTip(null);
	}
	
	public void removeToolTipTown(){
		setBreweryTownToolTip(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewAddNewBrewery dialog = new ViewAddNewBrewery();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewAddNewBrewery() {
		setBounds(100, 100, 509, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
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
			gbc_textFieldTown.insets = new Insets(0, 0, 5, 5);
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
			comboBoxCountry = new JComboBox<String>();
			GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
			gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxCountry.gridx = 3;
			gbc_comboBoxCountry.gridy = 6;
			contentPanel.add(comboBoxCountry, gbc_comboBoxCountry);
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
			gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldWebsite.gridx = 3;
			gbc_textFieldWebsite.gridy = 8;
			contentPanel.add(textFieldWebsite, gbc_textFieldWebsite);
			textFieldWebsite.setColumns(10);
		}
		{
			JLabel lblDescrizione = new JLabel("Descrizione:");
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 9;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			editorDescription = new JEditorPane();
//			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
//			gbc_textAreaDescription.gridwidth = 3;
//			gbc_textAreaDescription.gridheight = 2;
//			gbc_textAreaDescription.insets = new Insets(0, 0, 5, 5);
//			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
//			gbc_textAreaDescription.gridx = 3;
//			gbc_textAreaDescription.gridy = 9;
//			contentPanel.add(textAreaDescription, gbc_textAreaDescription);
//			textAreaDescription.setLineWrap(true);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 9;
			contentPanel.add(scrollPane, gbc_scrollPane);
			
			scrollPane.setViewportView(editorDescription);
		}
		
		
		{
			JLabel lblTrappist = new JLabel("Birrificio trappista:");
			GridBagConstraints gbc_lblTrappist = new GridBagConstraints();
			gbc_lblTrappist.insets = new Insets(0, 0, 5, 5);
			gbc_lblTrappist.gridx = 1;
			gbc_lblTrappist.gridy = 11;
			contentPanel.add(lblTrappist, gbc_lblTrappist);
		}		
		{
			rdbtnTrappistYes = new JRadioButton("S\u00EC");
			GridBagConstraints gbc_rdbtnTrappistYes = new GridBagConstraints();
			gbc_rdbtnTrappistYes.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnTrappistYes.gridx = 3;
			gbc_rdbtnTrappistYes.gridy = 11;
			buttonGroup.add(rdbtnTrappistYes);
			contentPanel.add(rdbtnTrappistYes, gbc_rdbtnTrappistYes);
		}
		
		{
			rdbtnTrappistNo = new JRadioButton("No");
			GridBagConstraints gbc_rdbtnTrappistNo = new GridBagConstraints();
			gbc_rdbtnTrappistNo.insets = new Insets(0, 0, 5, 0);
			gbc_rdbtnTrappistNo.gridx = 5;
			gbc_rdbtnTrappistNo.gridy = 11;
			buttonGroup.add(rdbtnTrappistNo);
			contentPanel.add(rdbtnTrappistNo, gbc_rdbtnTrappistNo);
			rdbtnTrappistNo.setSelected(true);
		}
		
		{
			JLabel lblAverage = new JLabel("Media voti birre:");
			GridBagConstraints gbc_lblAverage = new GridBagConstraints();
			gbc_lblAverage.insets = new Insets(0, 0, 0, 5);
			gbc_lblAverage.gridx = 1;
			gbc_lblAverage.gridy = 13;
			contentPanel.add(lblAverage, gbc_lblAverage);
		}
		{
			textFieldAverage = new JTextField();
			GridBagConstraints gbc_textFieldAverage = new GridBagConstraints();
			gbc_textFieldAverage.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldAverage.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldAverage.gridx = 3;
			gbc_textFieldAverage.gridy = 13;
			contentPanel.add(textFieldAverage, gbc_textFieldAverage);
			textFieldAverage.setColumns(7);
			textFieldAverage.setEditable(false);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			    okButton = new JButton("Ok");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setEnabled(false);
			}
			{
				cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
