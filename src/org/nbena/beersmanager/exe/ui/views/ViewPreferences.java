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



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ViewPreferences extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewPreferences dialog = new ViewPreferences();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private JComboBox<String> comboBoxSortingBeer;
	private JComboBox<String> comboBoxSortingBrewery;
	private JComboBox<String> comboBoxSortingStyle;
	
	private JComboBox<String> comboBoxFilteringBeer;
	private JComboBox<String> comboBoxFilteringBrewery;
	private JComboBox<String> comboBoxFilteringStyle;
	
	private JComboBox<String> comboBoxDefaultView;
	
	private JButton okButton;
	private JButton cancelButton;
	
//	private JButton btnDefaultSorting;
//	private JButton btnDefaultFiltering;
	
	private JButton btnDefault;
	
	
	public void fillComboBoxDefaultView(String[] values){
		for(String s: values){
			comboBoxDefaultView.addItem(s);
		}
	}
	
	public void fillComboBoxSortingBeer(String[]  values){
		for(String s: values){
			comboBoxSortingBeer.addItem(s);
		}
	}
	
	public void fillComboBoxSortingBrewery(String[] values){
		for(String s: values){
			comboBoxSortingBrewery.addItem(s);
		}
	}
	
	public void fillComboBoxSortingStyle(String[] values){
		for(String s: values){
			comboBoxSortingStyle.addItem(s);
		}
	}
	
	public void setComboBoxSortingBeerSelectedItem(String t){
		comboBoxSortingBeer.setSelectedItem(t);
	}
	
	public void setComboBoxSortingBrewerySelectedItem(String t){
		comboBoxSortingBrewery.setSelectedItem(t);
	}
	
	public void setComboBoxSortingStyleSelectedItem(String t){
		comboBoxSortingStyle.setSelectedItem(t);
	}
	
	public String getComboBoxSortingBeerSelectedItem(){
		return (String)comboBoxSortingBeer.getSelectedItem();
	}
	
	public String getComboBoxSortingBrewerySelectedItem(){
		return (String)comboBoxSortingBrewery.getSelectedItem();
	}
	
	public String getComboBoxSortingStyleSelectedItem(){
		return (String)comboBoxSortingStyle.getSelectedItem();
	}
	
	public String getComboBoxDeafultViewSelectedItem(){
		return (String)comboBoxDefaultView.getSelectedItem();
	}
	
	
//	public void fillComboBoxFilteringBeer(String[]  values){
//		for(String s: values){
//			comboBoxFilteringBeer.addItem(s);
//		}
//	}
//	
//	public void fillComboBoxFilteringBrewery(String[] values){
//		for(String s: values){
//			comboBoxFilteringBrewery.addItem(s);
//		}
//	}
//	
//	public void fillComboBoxFilteringStyle(String[] values){
//		for(String s: values){
//			comboBoxFilteringStyle.addItem(s);
//		}
//	}
	
//	public void setComboBoxFilteringBeerSelectedItem(String t){
//		comboBoxFilteringBeer.setSelectedItem(t);
//	}
//	
//	public void setComboBoxFilteringBrewerySelectedItem(String t){
//		comboBoxFilteringBrewery.setSelectedItem(t);
//	}
//	
//	public void setComboBoxFilteringStyleSelectedItem(String t){
//		comboBoxFilteringStyle.setSelectedItem(t);
//	}
	
	public void setComboBoxDefaultViewSelectedItem(String t){
		comboBoxDefaultView.setSelectedItem(t);
	}
	
	public String getComboBoxFilteringBeerSelectedItem(){
		return (String)comboBoxFilteringBeer.getSelectedItem();
	}
	
	public String getComboBoxFilteringBrewerySelectedItem(){
		return (String)comboBoxFilteringBrewery.getSelectedItem();
	}
	
	public String getComboBoxFilteringStyleSelectedItem(){
		return (String)comboBoxFilteringStyle.getSelectedItem();
	}
	
//	public void setFilteringBeer(String t){
//		textFieldFilteringBeer.setText(t);
//	}
//	
//	public void setFilteringBrewery(String t){
//		textFieldFilteringStyle.setText(t);
//	}
//	
//	public void setFilteringStyle(String t){
//		textFieldFilteringStyle.setText(t);
//	}
	
	
	
	public String getBeerFilteringValue(){
		return null;
	}
	
	public String getBreweryFilteringValue(){
		return null;
	}
	
	public String getStyleFilteringValue(){
		return null;
	}
	
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public void addActionListenerCancelButton(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	public void addActionListenerDefaultButton(ActionListener listener){
		btnDefault.addActionListener(listener);
	}
	
//	public void addActionListenerDefaultSortingButton(ActionListener listener){
//		btnDefaultSorting.addActionListener(listener);
//	}
//	
//	public void addActionListenerDefaultFilteringButton(ActionListener listener){
//		btnDefaultFiltering.addActionListener(listener);
//	}
//	

	/**
	 * Create the dialog.
	 */
	public ViewPreferences() {

		setBounds(100, 100, 461, 306);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			{
				JLabel lblImpostazioniPerLordinamento = new JLabel("Impostazioni");
				GridBagConstraints gbc_lblImpostazioniPerLordinamento = new GridBagConstraints();
				gbc_lblImpostazioniPerLordinamento.insets = new Insets(0, 0, 5, 5);
				gbc_lblImpostazioniPerLordinamento.gridx = 0;
				gbc_lblImpostazioniPerLordinamento.gridy = 0;
				contentPanel.add(lblImpostazioniPerLordinamento, gbc_lblImpostazioniPerLordinamento);
			}
			{
				JLabel lblBirre = new JLabel("Birre:");
				GridBagConstraints gbc_lblBirre = new GridBagConstraints();
				gbc_lblBirre.insets = new Insets(0, 0, 5, 5);
				gbc_lblBirre.gridx = 0;
				gbc_lblBirre.gridy = 2;
				contentPanel.add(lblBirre, gbc_lblBirre);
			}
			{
				comboBoxSortingBeer = new JComboBox<String>();
				GridBagConstraints gbc_comboBoxSortingBeer = new GridBagConstraints();
				gbc_comboBoxSortingBeer.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxSortingBeer.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxSortingBeer.gridx = 2;
				gbc_comboBoxSortingBeer.gridy = 2;
				contentPanel.add(comboBoxSortingBeer, gbc_comboBoxSortingBeer);
			}
			{
				JLabel lblBirrifici = new JLabel("Birrifici:");
				GridBagConstraints gbc_lblBirrifici = new GridBagConstraints();
				gbc_lblBirrifici.insets = new Insets(0, 0, 5, 5);
				gbc_lblBirrifici.gridx = 0;
				gbc_lblBirrifici.gridy = 4;
				contentPanel.add(lblBirrifici, gbc_lblBirrifici);
			}
			{
				comboBoxSortingBrewery = new JComboBox<String>();
				GridBagConstraints gbc_comboBoxSortingBrewery = new GridBagConstraints();
				gbc_comboBoxSortingBrewery.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxSortingBrewery.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxSortingBrewery.gridx = 2;
				gbc_comboBoxSortingBrewery.gridy = 4;
				contentPanel.add(comboBoxSortingBrewery, gbc_comboBoxSortingBrewery);
			}
			{
				JLabel lblStili = new JLabel("Stili:");
				GridBagConstraints gbc_lblStili = new GridBagConstraints();
				gbc_lblStili.insets = new Insets(0, 0, 5, 5);
				gbc_lblStili.gridx = 0;
				gbc_lblStili.gridy = 6;
				contentPanel.add(lblStili, gbc_lblStili);
			}
			{
				comboBoxSortingStyle = new JComboBox<String>();
				GridBagConstraints gbc_comboBoxSortingStyle = new GridBagConstraints();
				gbc_comboBoxSortingStyle.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxSortingStyle.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxSortingStyle.gridx = 2;
				gbc_comboBoxSortingStyle.gridy = 6;
				contentPanel.add(comboBoxSortingStyle, gbc_comboBoxSortingStyle);
			}
			
			{
				JLabel lblDiDefaultMostra = new JLabel("Di default mostra:");
				GridBagConstraints gbc_lblDiDefaultMostra = new GridBagConstraints();
				gbc_lblDiDefaultMostra.insets = new Insets(0, 0, 5, 5);
				gbc_lblDiDefaultMostra.gridx = 0;
				gbc_lblDiDefaultMostra.gridy = 8;
				contentPanel.add(lblDiDefaultMostra, gbc_lblDiDefaultMostra);
			}
			{
				comboBoxDefaultView = new JComboBox<String>();
				GridBagConstraints gbc_comboBoxDefaultView = new GridBagConstraints();
				gbc_comboBoxDefaultView.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxDefaultView.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxDefaultView.gridx = 2;
				gbc_comboBoxDefaultView.gridy = 8;
				contentPanel.add(comboBoxDefaultView, gbc_comboBoxDefaultView);
			}
			
//			{
//				btnDefaultSorting = new JButton("Default");
//				GridBagConstraints gbc_btnDefaultSorting = new GridBagConstraints();
//				gbc_btnDefaultSorting.insets = new Insets(0, 0, 0, 5);
//				gbc_btnDefaultSorting.gridx = 0;
//				gbc_btnDefaultSorting.gridy = 10;
//				contentPanel.add(btnDefaultSorting, gbc_btnDefaultSorting);
//			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			buttonPane.setLayout(gbl_buttonPane);
			{
				okButton = new JButton("Ok");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				gbc_okButton.weightx = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnDefault = new JButton("Deafult");
				GridBagConstraints gbc_btnDefault = new GridBagConstraints();
				gbc_btnDefault.gridx = 2;
				gbc_btnDefault.gridy = 0;
				gbc_btnDefault.weightx = 0;
				buttonPane.add(btnDefault, gbc_btnDefault);
			}
			{
				cancelButton = new JButton("Annulla");
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
