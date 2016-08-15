package org.nbena.beersmanager.exe.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

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
	
	private JButton okButton;
	private JButton cancelButton;
	
	private JButton btnDefaultSorting;
	private JButton btnDefaultFiltering;
	
	private JButton btnDefault;
	private JTextField textFieldFilteringBeer;
	private JTextField textFieldFilteringBrewery;
	private JTextField textFieldFilteringStyle;
	
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
	
	
	
	public void fillComboBoxFilteringBeer(String[]  values){
		for(String s: values){
			comboBoxFilteringBeer.addItem(s);
		}
	}
	
	public void fillComboBoxFilteringBrewery(String[] values){
		for(String s: values){
			comboBoxFilteringBrewery.addItem(s);
		}
	}
	
	public void fillComboBoxFilteringStyle(String[] values){
		for(String s: values){
			comboBoxFilteringStyle.addItem(s);
		}
	}
	
	public void setComboBoxFilteringBeerSelectedItem(String t){
		comboBoxFilteringBeer.setSelectedItem(t);
	}
	
	public void setComboBoxFilteringBrewerySelectedItem(String t){
		comboBoxFilteringBrewery.setSelectedItem(t);
	}
	
	public void setComboBoxFilteringStyleSelectedItem(String t){
		comboBoxFilteringStyle.setSelectedItem(t);
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
	
	public void setFilteringBeer(String t){
		textFieldFilteringBeer.setText(t);
	}
	
	public void setFilteringBrewery(String t){
		textFieldFilteringStyle.setText(t);
	}
	
	public void setFilteringStyle(String t){
		textFieldFilteringStyle.setText(t);
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
	
	public void addActionListenerDefaultSortingButton(ActionListener listener){
		btnDefaultSorting.addActionListener(listener);
	}
	
	public void addActionListenerDefaultFilteringButton(ActionListener listener){
		btnDefaultFiltering.addActionListener(listener);
	}

	/**
	 * Create the dialog.
	 */
	public ViewPreferences() {
		setBounds(100, 100, 484, 477);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{458, 0};
		gbl_contentPanel.rowHeights = new int[]{395, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.gridx = 0;
			gbc_tabbedPane.gridy = 0;
			contentPanel.add(tabbedPane, gbc_tabbedPane);
			{
				JPanel panelSorting = new JPanel();
				tabbedPane.addTab("New tab", null, panelSorting, null);
				GridBagLayout gbl_panelSorting = new GridBagLayout();
				gbl_panelSorting.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panelSorting.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panelSorting.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_panelSorting.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelSorting.setLayout(gbl_panelSorting);
				{
					JLabel lblImpostazioniPerLordinamento = new JLabel("Impostazioni per l'ordinamento dei dati");
					GridBagConstraints gbc_lblImpostazioniPerLordinamento = new GridBagConstraints();
					gbc_lblImpostazioniPerLordinamento.insets = new Insets(0, 0, 5, 5);
					gbc_lblImpostazioniPerLordinamento.gridx = 0;
					gbc_lblImpostazioniPerLordinamento.gridy = 1;
					panelSorting.add(lblImpostazioniPerLordinamento, gbc_lblImpostazioniPerLordinamento);
				}
				{
					JLabel lblBirre = new JLabel("Birre:");
					GridBagConstraints gbc_lblBirre = new GridBagConstraints();
					gbc_lblBirre.insets = new Insets(0, 0, 5, 5);
					gbc_lblBirre.gridx = 0;
					gbc_lblBirre.gridy = 3;
					panelSorting.add(lblBirre, gbc_lblBirre);
				}
				{
					comboBoxSortingBeer = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxSortingBeer = new GridBagConstraints();
					gbc_comboBoxSortingBeer.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxSortingBeer.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxSortingBeer.gridx = 2;
					gbc_comboBoxSortingBeer.gridy = 3;
					panelSorting.add(comboBoxSortingBeer, gbc_comboBoxSortingBeer);
				}
				{
					JLabel lblBirrifici = new JLabel("Birrifici:");
					GridBagConstraints gbc_lblBirrifici = new GridBagConstraints();
					gbc_lblBirrifici.insets = new Insets(0, 0, 5, 5);
					gbc_lblBirrifici.gridx = 0;
					gbc_lblBirrifici.gridy = 5;
					panelSorting.add(lblBirrifici, gbc_lblBirrifici);
				}
				{
					comboBoxSortingBrewery = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxSortingBrewery = new GridBagConstraints();
					gbc_comboBoxSortingBrewery.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxSortingBrewery.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxSortingBrewery.gridx = 2;
					gbc_comboBoxSortingBrewery.gridy = 5;
					panelSorting.add(comboBoxSortingBrewery, gbc_comboBoxSortingBrewery);
				}
				{
					JLabel lblStili = new JLabel("Stili:");
					GridBagConstraints gbc_lblStili = new GridBagConstraints();
					gbc_lblStili.insets = new Insets(0, 0, 5, 5);
					gbc_lblStili.gridx = 0;
					gbc_lblStili.gridy = 7;
					panelSorting.add(lblStili, gbc_lblStili);
				}
				{
					comboBoxSortingStyle = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxSortingStyle = new GridBagConstraints();
					gbc_comboBoxSortingStyle.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxSortingStyle.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxSortingStyle.gridx = 2;
					gbc_comboBoxSortingStyle.gridy = 7;
					panelSorting.add(comboBoxSortingStyle, gbc_comboBoxSortingStyle);
				}
				{
					btnDefaultSorting = new JButton("Default");
					GridBagConstraints gbc_btnDefaultSorting = new GridBagConstraints();
					gbc_btnDefaultSorting.insets = new Insets(0, 0, 5, 5);
					gbc_btnDefaultSorting.gridx = 0;
					gbc_btnDefaultSorting.gridy = 9;
					panelSorting.add(btnDefaultSorting, gbc_btnDefaultSorting);
				}
			}
			{
				JPanel panelFiltering = new JPanel();
				tabbedPane.addTab("New tab", null, panelFiltering, null);
				GridBagLayout gbl_panelFiltering = new GridBagLayout();
				gbl_panelFiltering.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panelFiltering.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panelFiltering.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_panelFiltering.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelFiltering.setLayout(gbl_panelFiltering);
				{
					JLabel lblImpostazioniPerIl = new JLabel("Impostazioni per il filtraggio dei dati");
					GridBagConstraints gbc_lblImpostazioniPerIl = new GridBagConstraints();
					gbc_lblImpostazioniPerIl.insets = new Insets(0, 0, 5, 5);
					gbc_lblImpostazioniPerIl.gridx = 0;
					gbc_lblImpostazioniPerIl.gridy = 0;
					panelFiltering.add(lblImpostazioniPerIl, gbc_lblImpostazioniPerIl);
				}
				{
					JLabel lblBirre_1 = new JLabel("Birre:");
					GridBagConstraints gbc_lblBirre_1 = new GridBagConstraints();
					gbc_lblBirre_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblBirre_1.gridx = 0;
					gbc_lblBirre_1.gridy = 2;
					panelFiltering.add(lblBirre_1, gbc_lblBirre_1);
				}
				{
					comboBoxFilteringBeer = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxFilteringBeer = new GridBagConstraints();
					gbc_comboBoxFilteringBeer.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxFilteringBeer.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxFilteringBeer.gridx = 2;
					gbc_comboBoxFilteringBeer.gridy = 2;
					panelFiltering.add(comboBoxFilteringBeer, gbc_comboBoxFilteringBeer);
				}
				{
					JLabel lblValoreDiFiltraggio = new JLabel("Valore di filtraggio:");
					GridBagConstraints gbc_lblValoreDiFiltraggio = new GridBagConstraints();
					gbc_lblValoreDiFiltraggio.insets = new Insets(0, 0, 5, 5);
					gbc_lblValoreDiFiltraggio.gridx = 0;
					gbc_lblValoreDiFiltraggio.gridy = 3;
					panelFiltering.add(lblValoreDiFiltraggio, gbc_lblValoreDiFiltraggio);
				}
				{
					textFieldFilteringBeer = new JTextField();
					GridBagConstraints gbc_textFieldFilteringBeer = new GridBagConstraints();
					gbc_textFieldFilteringBeer.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldFilteringBeer.anchor = GridBagConstraints.WEST;
					gbc_textFieldFilteringBeer.fill = GridBagConstraints.VERTICAL;
					gbc_textFieldFilteringBeer.gridx = 2;
					gbc_textFieldFilteringBeer.gridy = 3;
					panelFiltering.add(textFieldFilteringBeer, gbc_textFieldFilteringBeer);
					textFieldFilteringBeer.setColumns(10);
				}
				{
					JLabel lblBirrifici_1 = new JLabel("Birrifici:");
					GridBagConstraints gbc_lblBirrifici_1 = new GridBagConstraints();
					gbc_lblBirrifici_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblBirrifici_1.gridx = 0;
					gbc_lblBirrifici_1.gridy = 5;
					panelFiltering.add(lblBirrifici_1, gbc_lblBirrifici_1);
				}
				{
					comboBoxFilteringBrewery = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxFilteringBrewery = new GridBagConstraints();
					gbc_comboBoxFilteringBrewery.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxFilteringBrewery.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxFilteringBrewery.gridx = 2;
					gbc_comboBoxFilteringBrewery.gridy = 5;
					panelFiltering.add(comboBoxFilteringBrewery, gbc_comboBoxFilteringBrewery);
				}
				{
					JLabel lblValoreDiFiltraggio_1 = new JLabel("Valore di filtraggio:");
					GridBagConstraints gbc_lblValoreDiFiltraggio_1 = new GridBagConstraints();
					gbc_lblValoreDiFiltraggio_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblValoreDiFiltraggio_1.gridx = 0;
					gbc_lblValoreDiFiltraggio_1.gridy = 6;
					panelFiltering.add(lblValoreDiFiltraggio_1, gbc_lblValoreDiFiltraggio_1);
				}
				{
					textFieldFilteringBrewery = new JTextField();
					GridBagConstraints gbc_textFieldFilteringBrewery = new GridBagConstraints();
					gbc_textFieldFilteringBrewery.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldFilteringBrewery.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldFilteringBrewery.gridx = 2;
					gbc_textFieldFilteringBrewery.gridy = 6;
					panelFiltering.add(textFieldFilteringBrewery, gbc_textFieldFilteringBrewery);
					textFieldFilteringBrewery.setColumns(10);
				}
				{
					JLabel lblStili_1 = new JLabel("Stili:");
					GridBagConstraints gbc_lblStili_1 = new GridBagConstraints();
					gbc_lblStili_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblStili_1.gridx = 0;
					gbc_lblStili_1.gridy = 8;
					panelFiltering.add(lblStili_1, gbc_lblStili_1);
				}
				{
					comboBoxFilteringStyle = new JComboBox<String>();
					GridBagConstraints gbc_comboBoxFilteringStyle = new GridBagConstraints();
					gbc_comboBoxFilteringStyle.insets = new Insets(0, 0, 5, 0);
					gbc_comboBoxFilteringStyle.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxFilteringStyle.gridx = 2;
					gbc_comboBoxFilteringStyle.gridy = 8;
					panelFiltering.add(comboBoxFilteringStyle, gbc_comboBoxFilteringStyle);
				}
				{
					JLabel lblValoreDiFiltraggio_2 = new JLabel("Valore di filtraggio:");
					GridBagConstraints gbc_lblValoreDiFiltraggio_2 = new GridBagConstraints();
					gbc_lblValoreDiFiltraggio_2.insets = new Insets(0, 0, 5, 5);
					gbc_lblValoreDiFiltraggio_2.gridx = 0;
					gbc_lblValoreDiFiltraggio_2.gridy = 9;
					panelFiltering.add(lblValoreDiFiltraggio_2, gbc_lblValoreDiFiltraggio_2);
				}
				{
					textFieldFilteringStyle = new JTextField();
					GridBagConstraints gbc_textFieldFilteringStyle = new GridBagConstraints();
					gbc_textFieldFilteringStyle.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldFilteringStyle.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldFilteringStyle.gridx = 2;
					gbc_textFieldFilteringStyle.gridy = 9;
					panelFiltering.add(textFieldFilteringStyle, gbc_textFieldFilteringStyle);
					textFieldFilteringStyle.setColumns(10);
				}
				{
					btnDefaultFiltering = new JButton("Default");
					GridBagConstraints gbc_btnDefault_1 = new GridBagConstraints();
					gbc_btnDefault_1.insets = new Insets(0, 0, 0, 5);
					gbc_btnDefault_1.gridx = 0;
					gbc_btnDefault_1.gridy = 11;
					panelFiltering.add(btnDefaultFiltering, gbc_btnDefault_1);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{346, 47, 65, 0};
//			gbl_buttonPane.rowHeights = new int[]{23, 0};
//			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
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
			
			{
				okButton = new JButton("OK");
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
