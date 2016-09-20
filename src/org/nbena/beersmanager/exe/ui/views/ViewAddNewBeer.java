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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import org.nbena.beersmanager.exe.Utils;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSlider;

public class ViewAddNewBeer extends JDialog implements BeerDialog{

	private final JPanel contentPanel = new JPanel();
//	private JTextField textFieldABV;
	private JTextField textFieldBeerName;
	private JTextField textFieldPrice;
//	private JTextField textFieldStars;
//	private JTextField textFieldMark;
	private JTextField textFieldPlace;
	
	private JButton okButton;
	private JButton cancelButton;

	
	private JEditorPane editorDescription;
	private JComboBox<String> comboBoxBrewery;
	private JComboBox<String> comboBoxStyle;
	private JRadioButton rdbtnTriedYes;
	private JRadioButton rdbtnTriedNo;
	
	private JSlider starSlider;
	private JSpinner markSpinner;
	private JSpinner priceSpinner;
	private JSpinner abvSpinner;
	
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
	
//	public void setABV(String t){
//		textFieldABV.setText(t);
//	}
	
	public void setABV(double abv){
		abvSpinner.setValue(abv);
	}
	
//	public void setStars(String t){
//		textFieldStars.setText(t);
//	}
	
	public void setStars(int star){
		starSlider.setValue(star);
	}
	
//	public void setMark(String t){
//		textFieldMark.setText(t);
//	}
	
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
		editorDescription.setText(t);
		editorDescription.setCaretPosition(0);
	}
	
	public void setContentType(String type){
		editorDescription.setContentType(type);
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
	
//	public String getABV(){
//		return textFieldABV.getText();
//	}
	
	public double getABV(){
//		Object ret = null;
//		try{
//			ret= (Double)abvSpinner.getValue();
//		}catch(ClassCastException e){
//			int r = (Integer)abvSpinner.getValue();
//			double t = new Double(r);
//			ret = t;
//		}
//		return (Double)ret;
//		ABVSpinnerNumberModel model = (ABVSpinnerNumberModel)abvSpinner.getModel();
//		return model.getValue();
		double a = ((SpinnerNumberModel)abvSpinner.getModel()).getNumber().doubleValue();
		return Utils.truncateDouble(a);
	}
	
	public int getStars(){
//		return textFieldStars.getText();
		return (Integer)starSlider.getValue();
	}
	
	public int getMark(){
//		return textFieldMark.getText();
//		return ((Integer)markSpinner.getValue())==null ? 0 : (Integer)markSpinner.getValue();
//		return (Integer)markSpinner.getValue();
//		MarkSpinnerModel model = (MarkSpinnerModel)markSpinner.getModel();
//		return model.getIntValue();
//		MarkSpinnerNumberModel model = (MarkSpinnerNumberModel)markSpinner.getModel();
//		return model.getValue();
		return ((SpinnerNumberModel)markSpinner.getModel()).getNumber().intValue();
	}
	
//	public String getTried(){
//		return textFieldTried.getText();
//	}
	
	public String getDescription(){
		return editorDescription.getText();
	}
	
//	public String getPrice(){
//		return textFieldPrice.getText();
//	}
	
	public double getPrice(){
//		Object ret = null;
//		try{
//			ret= (Double)priceSpinner.getValue();
//		}catch(ClassCastException e){
//			int r = (Integer)priceSpinner.getValue();
//			double t = new Double(r);
//			ret = t;
//		}
//		return (Double)ret;
//		PriceSpinnerNumberModel model = (PriceSpinnerNumberModel)priceSpinner.getModel();
//		return model.getValue();
		double a = ((SpinnerNumberModel)priceSpinner.getModel()).getNumber().doubleValue();
		return Utils.truncateDouble(a);
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
	
//	public void setTextFieldPriceEditable(boolean enabled){
//		textFieldPrice.setEditable(enabled);
//	}
//	
//	public void setTextFieldMarkEditable(boolean enabled){
//		textFieldMark.setEditable(enabled);
//	}
	
//	public void setPrice(String t){
//		textFieldPrice.setText(t);
//	}
	
	public void setPrice(double price){
		priceSpinner.setValue(price);
	}
	
	
	public void addDocumentListenerPrice(DocumentListener listener){
		textFieldPrice.getDocument().addDocumentListener(listener);
	}
	
	public void addDocumentListenerName(DocumentListener listener){
		textFieldBeerName.getDocument().addDocumentListener(listener);
	}
	
	
//	public void addDocumentListenerABV(DocumentListener listener){
//		textFieldABV.getDocument().addDocumentListener(listener);
//	}
//	
	
	
//	public void setStarSliderLableTable(Hashtable<Integer, JLabel> table){
//		starSlider.setLabelTable(table);
//	}
	
	
//	public void setSpinnerModel(MarkSpinnerModel model){
//		markSpinner.setModel(model);
//	}
	
	public static enum SpinnerType{
		ABV,
		MARK,
		PRICE
	}
	
//	private void setMarkSpinnerModel(SpinnerNumberModel model){
//		markSpinner.setModel(model);
//	}
//	
//	private void setMarkSpinnerChangeEvent(ChangeListener listener){
//		markSpinner.addChangeListener(listener);
//	}
//	
//	
//	private void setMarkSpinnerTextFieldValue(int value){
//		((JFormattedTextField)markSpinner.getValue()).setValue(value);
//	}
//	
//	private void markSpinnerCommitEdit() throws ParseException{
//		markSpinner.commitEdit();
//	}
	
	public void setMarkSpinnerEnable(boolean enabled){
		markSpinner.setEnabled(enabled);
		if(!enabled){
			markSpinner.setValue(0);
		}
		
	}
	
	
//	private void setABVSpinnerModel(SpinnerNumberModel model){
//		abvSpinner.setModel(model);
//	}
//	
//	private void setABVSpinnerChangeEvent(ChangeListener listener){
//		abvSpinner.addChangeListener(listener);
//	}
//	
//	
//	private void setABVSpinnerTextFieldValue(int value){
//		((JFormattedTextField)abvSpinner.getValue()).setValue(value);
//	}
//	
//	private void ABVSpinnerCommitEdit() throws ParseException{
//		abvSpinner.commitEdit();
//	}
//	
//
//	private void setPriceSpinnerModel(SpinnerNumberModel model){
//		priceSpinner.setModel(model);
//	}
//	
//	private void setPriceSpinnerChangeEvent(ChangeListener listener){
//		priceSpinner.addChangeListener(listener);
//	}
//	
//
//	
//	private void setPriceSpinnerTextFieldValue(int value){
//		((JFormattedTextField)priceSpinner.getValue()).setValue(value);
//	}
//	
//	private void priceSpinnerCommitEdit() throws ParseException{
//		priceSpinner.commitEdit();
//	}
	
//	public void setSpinnerModel(SpinnerType type, SpinnerNumberModel model){
//		switch(type){
//		case ABV:
//			abvSpinner.setModel(model);
//			System.out.println("ABV STEP: "+model.getStepSize());
//			break;
//		case MARK:
//			markSpinner.setModel(model);
//			System.out.println("MARK STEP "+model.getStepSize());
//			break;
//		case PRICE:
//			priceSpinner.setModel(model);
//			System.out.println("PRICE STEP "+model.getStepSize());
//			break;		
//		}
//		
//	}
	
//	public void setSpinnerChangeEvent(SpinnerType type, ChangeListener listener){
//		switch(type){
//		case ABV:
//			abvSpinner.addChangeListener(listener);
//			break;
//		case MARK:
//			markSpinner.addChangeListener(listener);
//			break;
//		case PRICE:
//			System.out.println("Called the change event on price");
//			priceSpinner.addChangeListener(listener);
//			break;		
//		}
//		
//	}
	
	
	
//	public JFormattedTextField getSpinnerTextField(SpinnerType type){
//		return null;
//	}
//	
//	public void addChangeEventToSpinnerTextField(SpinnerType type, PropertyChangeListener listener){
//		JFormattedTextField text = null;
//		switch(type){
//		case ABV:
//			text =(JFormattedTextField) abvSpinner.getEditor();
//			break;
//		case MARK:
//			text =(JFormattedTextField) markSpinner.getEditor();
//			break;
//		case PRICE:
//			text =(JFormattedTextField) priceSpinner.getEditor();
//			break;
//		
//		}
//		
//		text.addPropertyChangeListener(listener);
//	}
	
//	public Object getValue(SpinnerType type) throws ClassCastException{
//		Object ret = null;
//		switch(type){
//		case ABV:
//			ret = getABV();
//			break;
//		case MARK:
//			ret = getMark();
//			break;
//		case PRICE:
//			ret = getPrice();
//			break;
//		
//		}
//		return ret;
//	}
	
//	public void setDefaultValue(SpinnerType type){
//		switch(type){
//		case ABV:
//			abvSpinner.setValue(Utils.Constants.ABV_SPINNER_DEF_VALUE);
//			break;
//		case MARK:
//			markSpinner.setValue(Utils.Constants.MARK_SPINNER_DEF_VALUE);
//			break;
//		case PRICE:
//			priceSpinner.setValue(Utils.Constants.PRICE_SPINNER_DEF_VALUE);
//			break;
//			
//		}
//	}
	

	
//	public JComponent getMarkSpinnerEditor(){
//		return markSpinner.getEditor();
//	}
	
//	public JSpinner getSpinner(SpinnerType type){
//		switch(type){
//		case ABV:
//			break;
//		case MARK:
//			break;
//		case PRICE:
//			break;		
//		}
//		return markSpinner;
//	}
	
	
//	public void spinnerCommitEdit(SpinnerType type) throws ParseException {
//		switch(type){
//		case ABV:
//			abvSpinner.commitEdit();
//			break;
//		case MARK:
//			markSpinner.commitEdit();
//			break;
//		case PRICE:
//			priceSpinner.commitEdit();
//			break;		
//		}
//		
//	}
//	
//	public Object getSpinnerValue(SpinnerType type){
//		Object o = null;
//		switch(type){
//		case ABV:
//			o = abvSpinner.getValue();
//			break;
//		case MARK:
//			o = markSpinner.getValue();
//			break;
//		case PRICE:
//			o = priceSpinner.getValue();
//			break;		
//		}
//		return o;
//	}
	
	private JFormattedTextField getTextFieldABV(){
		JComponent editor = abvSpinner.getEditor();
		JFormattedTextField text = ((JSpinner.NumberEditor)editor).getTextField();
		return text;
	}
	
	private JFormattedTextField getTextFieldMark(){
		JComponent editor = markSpinner.getEditor();
		JFormattedTextField text = ((JSpinner.NumberEditor)editor).getTextField();
		return text;
	}
	
	private JFormattedTextField getTextFieldPrice(){
		JComponent editor = priceSpinner.getEditor();
		JFormattedTextField text = ((JSpinner.NumberEditor)editor).getTextField();
		return text;
	}
	
	private void resizeTextFieldABV(){
		getTextFieldABV().setColumns(5);
	}
	
	private void resizeTextFieldMark(){
		getTextFieldMark().setColumns(5);
//		System.out.println("Resized text field");
	}
	
	private void resizeTextFieldPrice(){
		getTextFieldPrice().setColumns(5);
	}
	
//	private JFormattedTextField getFormattedTextField(SpinnerType type){
//		JFormattedTextField text = null;
//		switch(type){
//		case ABV:
//			text = getTextFieldABV();
//			break;
//		case MARK:
//			text = getTextFieldMark();
//			break;
//		case PRICE:
//			text = getTextFieldPrice();
//			break;
//		
//		}
//		return text;
//	}
	
	
//	private void resizeSpinnerEditors(SpinnerType type){
//		JFormattedTextField text = getFormattedTextField(type);
//		text.setColumns(5);
//	}
	

	public void setOkButtonEnabled(boolean enabled){
		okButton.setEnabled(enabled);
	}
	
	public void setMark(int mark){
//		System.out.println("Set the value of mark: "+mark);
		markSpinner.setValue(mark);
//		markSpinner.getModel().setValue(mark);
//		System.out.println("Settings the current mark: "+mark+"\n the value is: "+(Integer)markSpinner.getValue());
//		getTextFieldMark().setValue(mark);
		
//		getTextFieldMark().setEditable(false);
		
//		getTextFieldMark().setText(Integer.toString(mark));
	}
	
//	private void initResize(){
//		for(SpinnerType type: SpinnerType.values()){
//			resizeSpinnerEditors(type);
//		}
//	}
	
	/**
	 * Initializes every spinner. It adds the model, the editor, and then it resizes it to 5 columns.
	 * No matter if, while you are digit, you digit a letter or something wrong in the spinner, when you loose
	 * focus on it, or click ok, the value is set to default.
	 * @param models the model to initializes them. Array must be <code><ol>
	 * 																	<li> {@link CustomSpinnerModel#MarkSpinnerModel()()}</li>
	 * 																	<li> {@link CustomSpinnerModel#ABVSpinnerModel()()}</li>
	 * 																	<li> {@link CustomSpinnerModel#PriceSpinnerModel()()}</li>
	 * 																	</ol></code>
	 * @throws <code> IllegalArgumentException </code> if array is not in that order.
	 */
	public void initSpinners(SpinnerNumberModel[] models){
		
//		if(!checkArgs(models)){
//			throw new IllegalArgumentException("models not in proper order");  //should never happen
//		}
		
		initMarkSpinner(models[0]);
		initABVSpinner(models[1]);
		initPriceSpinner(models[2]);
	}
	
	
	private void initMarkSpinner(SpinnerNumberModel model){
//		System.out.println("Instance of model mark: "+model.getClass().getName());
		markSpinner.setModel(model/*new CustomSpinnerModel.MarkSpinnerNumberModel()*/);	//must set model before!!!!!!!!!!!!!!!!!!!!!!!!!!!11
		markSpinner.setEditor(new JSpinner.NumberEditor(markSpinner/*, "000"*//*,"###"*//*, "000"*/, "#"));
		resizeTextFieldMark();
//		
//		markSpinner.setModel(new SpinnerNumberModel(Utils.Constants.MARK_SPINNER_DEF_VALUE, Utils.Constants.MARK_SPINNER_MIN_VALUE,
//				Utils.Constants.MARK_SPINNER_MAX_VALUE, Utils.Constants.MARK_SPINNER_STEP_VALUE));
////		markSpinner.setEditor(new JSpinner.NumberEditor(markSpinner, "#"));
//		markSpinner.setValue(new Integer(50));
//		System.out.println("set the fuck value to 50");
	}
	
	
	private void initABVSpinner(SpinnerNumberModel model){
		abvSpinner.setModel(model);
		abvSpinner.setEditor(new JSpinner.NumberEditor(abvSpinner, "00.00"));
		resizeTextFieldABV();
	}
	
	private void initPriceSpinner(SpinnerNumberModel model){
		priceSpinner.setModel(model);
		priceSpinner.setEditor(new JSpinner.NumberEditor(priceSpinner, "00.00"));
		resizeTextFieldPrice();
	}
	
	
//	private boolean checkArgs(SpinnerNumberModel [] models){
//		return models[0] instanceof MarkSpinnerNumberModel &&
//				models[1] instanceof ABVSpinnerNumberModel &&
//				models[2] instanceof PriceSpinnerNumberModel;
//	}
	
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
			comboBoxBrewery = new JComboBox<String>();
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
			comboBoxStyle = new JComboBox<String>();
			GridBagConstraints gbc_comboBoxStyle = new GridBagConstraints();
			gbc_comboBoxStyle.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxStyle.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxStyle.gridx = 3;
			gbc_comboBoxStyle.gridy = 6;
			contentPanel.add(comboBoxStyle, gbc_comboBoxStyle);
		}
//		{
//			textFieldABV = new JTextField();
//			GridBagConstraints gbc_textFieldABV = new GridBagConstraints();
//			gbc_textFieldABV.fill = GridBagConstraints.HORIZONTAL;
//			gbc_textFieldABV.insets = new Insets(0, 0, 5, 5);
//			gbc_textFieldABV.gridx = 3;
//			gbc_textFieldABV.gridy = 8;
////			contentPanel.add(textFieldABV, gbc_textFieldABV);
//			textFieldABV.setColumns(10);
//		}
//		
//		{
//			textFieldStars = new JTextField();
//			GridBagConstraints gbc_textFieldStars = new GridBagConstraints();
//			gbc_textFieldStars.fill = GridBagConstraints.HORIZONTAL;
//			gbc_textFieldStars.insets = new Insets(0, 0, 5, 0);
//			gbc_textFieldStars.gridx = 7;
//			gbc_textFieldStars.gridy = 8;
////			contentPanel.add(textFieldStars, gbc_textFieldStars);
//			textFieldStars.setColumns(10);
//		}
//		
//		{
//			textFieldMark = new JTextField();
//			GridBagConstraints gbc_textFieldMark = new GridBagConstraints();
//			gbc_textFieldMark.fill = GridBagConstraints.HORIZONTAL;
//			gbc_textFieldMark.insets = new Insets(0, 0, 5, 5);
//			gbc_textFieldMark.gridx = 3;
//			gbc_textFieldMark.gridy = 10;
////			contentPanel.add(textFieldMark, gbc_textFieldMark);
//			textFieldMark.setColumns(10);
//		}
		

		
		
		
		{
			JLabel lblVoto = new JLabel("Voto:");
			GridBagConstraints gbc_lblVoto = new GridBagConstraints();
			gbc_lblVoto.insets = new Insets(0, 0, 5, 5);
			gbc_lblVoto.gridx = 1;
			gbc_lblVoto.gridy = 8;
			contentPanel.add(lblVoto, gbc_lblVoto);
		}
		{
			markSpinner = new JSpinner();
//			markSpinner.setEditor(new JSpinner.NumberEditor(markSpinner));
			GridBagConstraints gbc_markSpinner = new GridBagConstraints();
			gbc_markSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_markSpinner.gridx = 3;
			gbc_markSpinner.gridy = 8;
			contentPanel.add(markSpinner, gbc_markSpinner);
		}
		{
			JLabel lblAbv = new JLabel("ABV%:");
			GridBagConstraints gbc_lblAbv = new GridBagConstraints();
			gbc_lblAbv.insets = new Insets(0, 0, 5, 5);
			gbc_lblAbv.gridx = 5;
			gbc_lblAbv.gridy = 8;
			contentPanel.add(lblAbv, gbc_lblAbv);
		}
		{
			abvSpinner = new JSpinner();
//			abvSpinner.setEditor(new JSpinner.NumberEditor(abvSpinner, "00.00"));
			GridBagConstraints gbc_abvSpinner = new GridBagConstraints();
			gbc_abvSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_abvSpinner.insets = new Insets(0, 0, 5, 0);
			gbc_abvSpinner.gridx = 7;
			gbc_abvSpinner.gridy = 8;
			contentPanel.add(abvSpinner, gbc_abvSpinner);
		}
		
		{
			JLabel lblStars = new JLabel("Stelle:");
			GridBagConstraints gbc_lblStars = new GridBagConstraints();
			gbc_lblStars.insets = new Insets(0, 0, 5, 5);
			gbc_lblStars.gridx = 1;
			gbc_lblStars.gridy = 10;
			contentPanel.add(lblStars, gbc_lblStars);
		}
		{
			starSlider = new JSlider();
			starSlider.setValue(1);
			starSlider.setMaximum(5);
			starSlider.setMinimum(1);
			starSlider.setMajorTickSpacing(1);
			starSlider.setPaintLabels(true);
			starSlider.setMinorTickSpacing(1);
			GridBagConstraints gbc_starSlider = new GridBagConstraints();
			gbc_starSlider.fill = GridBagConstraints.HORIZONTAL;
			gbc_starSlider.insets = new Insets(0, 0, 5, 5);
			gbc_starSlider.gridx = 3;
			gbc_starSlider.gridy = 10;
			contentPanel.add(starSlider, gbc_starSlider);
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
			JLabel lblLuogo = new JLabel("Luogo:");
			GridBagConstraints gbc_lblLuogo = new GridBagConstraints();
			gbc_lblLuogo.insets = new Insets(0, 0, 5, 5);
			gbc_lblLuogo.gridx = 1;
			gbc_lblLuogo.gridy = 12;
			contentPanel.add(lblLuogo, gbc_lblLuogo);
		}
		{
			textFieldPlace = new JTextField();
			GridBagConstraints gbc_textFieldPlace = new GridBagConstraints();
			gbc_textFieldPlace.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPlace.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPlace.gridx = 3;
			gbc_textFieldPlace.gridy = 12;
			contentPanel.add(textFieldPlace, gbc_textFieldPlace);
			textFieldPlace.setColumns(10);
		}
		
		{
			JLabel lblPrezzo = new JLabel("Prezzo:");
			GridBagConstraints gbc_lblPrezzo = new GridBagConstraints();
			gbc_lblPrezzo.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrezzo.gridx = 5;
			gbc_lblPrezzo.gridy = 12;
			contentPanel.add(lblPrezzo, gbc_lblPrezzo);
		}
		{
			textFieldPrice = new JTextField();
			GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
			gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPrice.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPrice.gridx = 7;
			gbc_textFieldPrice.gridy = 12;
//			contentPanel.add(textFieldPrice, gbc_textFieldPrice);
			textFieldPrice.setColumns(10);
		}
		{
			priceSpinner = new JSpinner();
//			priceSpinner.setEditor(new JSpinner.NumberEditor(priceSpinner, "00.00"));
			GridBagConstraints gbc_priceSpinner = new GridBagConstraints();
			gbc_priceSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_priceSpinner.insets = new Insets(0, 0, 5, 0);
			gbc_priceSpinner.gridx = 7;
			gbc_priceSpinner.gridy = 12;
			contentPanel.add(priceSpinner, gbc_priceSpinner);
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
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 14;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		{
			editorDescription = new JEditorPane();
//			GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
//			gbc_textAreaDescription.gridwidth = 5;
//			gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
//			gbc_textAreaDescription.gridx = 3;
//			gbc_textAreaDescription.gridy = 14;
//			contentPanel.add(textAreaDescription, gbc_textAreaDescription);
//			textAreaDescription.setLineWrap(true);
			scrollPane.setViewportView(editorDescription);
		}
//		{
//			JPanel buttonPane = new JPanel();
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{376, 47, 65, 0};
//			gbl_buttonPane.rowHeights = new int[]{23, 0};
//			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//			buttonPane.setLayout(gbl_buttonPane);
//
//			
//			
//			
//			
//			
//			
//			{
//				okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				GridBagConstraints gbc_okButton = new GridBagConstraints();
//				gbc_okButton.gridx = 2;
//				gbc_okButton.gridy = 0;
//				gbc_okButton.weightx = 0;
//				buttonPane.add(okButton, gbc_okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
//				gbc_cancelButton.gridx = 3;
//				gbc_cancelButton.gridy = 0;
//				gbc_cancelButton.weightx = 0;
//				buttonPane.add(cancelButton, gbc_cancelButton);
//			}
//		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			    okButton = new JButton("OK");
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
