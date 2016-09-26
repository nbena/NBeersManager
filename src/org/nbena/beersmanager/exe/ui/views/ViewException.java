
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class ViewException extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldErrorType;
	private JTextField textFieldErrorMessage;
	
	private JButton okButton;
	private JButton btnSaveException;
	private JEditorPane editorPaneException;
	

	
	public void setErrorType(String t){
		textFieldErrorType.setText(t);
	}
	
	public void setErrorMessage(String t){
		textFieldErrorMessage.setText(t);
	}
	
	public void setStackTrace(String start, String text, String end){
		editorPaneException.setText(start+text+end);
	}
	
	public void init(){
		editorPaneException.setContentType("text/html");
		editorPaneException.setCaretPosition(0);
		editorPaneException.setEditable(false);
		textFieldErrorMessage.setEditable(false);
		textFieldErrorType.setEditable(false);
	}
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public void addActionListenerSaveButton(ActionListener listener){
		btnSaveException.addActionListener(listener);
	}
	
	public String getStackTrace(){
		return editorPaneException.getText();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewException dialog = new ViewException();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewException() {
		setBounds(100, 100, 560, 442);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("C'\u00E8 stato un errore");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textFieldErrorType = new JTextField();
			GridBagConstraints gbc_textFieldErrorType = new GridBagConstraints();
			gbc_textFieldErrorType.gridwidth = 3;
			gbc_textFieldErrorType.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldErrorType.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldErrorType.gridx = 0;
			gbc_textFieldErrorType.gridy = 2;
			contentPanel.add(textFieldErrorType, gbc_textFieldErrorType);
			textFieldErrorType.setColumns(10);
//		}
//		{
			JScrollBar scrollBarErrorType = new JScrollBar();
			scrollBarErrorType.setOrientation(JScrollBar.HORIZONTAL);
			GridBagConstraints gbc_scrollBarErrorType = new GridBagConstraints();
			gbc_scrollBarErrorType.gridwidth = 2;
			gbc_scrollBarErrorType.insets = new Insets(0, 0, 5, 0);
			gbc_scrollBarErrorType.gridx = 1;
			gbc_scrollBarErrorType.gridy = 2;

//			scrollBarErrorType.add(textFieldErrorType);
			scrollBarErrorType.setModel(textFieldErrorType.getHorizontalVisibility());
			
//			contentPanel.add(textFieldErrorType, gbc_textFieldErrorType);
//			contentPanel.add(scrollBarErrorType, gbc_scrollBarErrorType);

		}
		{
			JLabel lblErrore = new JLabel("Errore:");
			GridBagConstraints gbc_lblErrore = new GridBagConstraints();
			gbc_lblErrore.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrore.gridx = 0;
			gbc_lblErrore.gridy = 1;
			contentPanel.add(lblErrore, gbc_lblErrore);
		}
		{
			textFieldErrorMessage = new JTextField();
			GridBagConstraints gbc_textFieldErrorMessage = new GridBagConstraints();
			gbc_textFieldErrorMessage.gridwidth = 3;
			gbc_textFieldErrorMessage.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldErrorMessage.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldErrorMessage.gridx = 0;
			gbc_textFieldErrorMessage.gridy = 4;
			contentPanel.add(textFieldErrorMessage, gbc_textFieldErrorMessage);
			textFieldErrorMessage.setColumns(10);
//		}
//		{
			JScrollBar scrollBarErrorMessage = new JScrollBar();
			scrollBarErrorMessage.setOrientation(JScrollBar.HORIZONTAL);
			GridBagConstraints gbc_scrollBarErrorMessage = new GridBagConstraints();
			gbc_scrollBarErrorMessage.gridwidth = 2;
			gbc_scrollBarErrorMessage.insets = new Insets(0, 0, 5, 5);
			gbc_scrollBarErrorMessage.gridx = 1;
			gbc_scrollBarErrorMessage.gridy = 4;

//			scrollBarErrorMessage.add(textFieldErrorMessage);
			scrollBarErrorMessage.setModel(textFieldErrorMessage.getHorizontalVisibility());
			
//			contentPanel.add(textFieldErrorMessage,gbc_textFieldErrorMessage);
//			contentPanel.add(scrollBarErrorMessage, gbc_scrollBarErrorMessage);
		}
		{
			JLabel lblMessaggio = new JLabel("Messaggio:");
			GridBagConstraints gbc_lblMessaggio = new GridBagConstraints();
			gbc_lblMessaggio.insets = new Insets(0, 0, 5, 5);
			gbc_lblMessaggio.gridx = 0;
			gbc_lblMessaggio.gridy = 3;
			contentPanel.add(lblMessaggio, gbc_lblMessaggio);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 6;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				editorPaneException = new JEditorPane();
				scrollPane.setViewportView(editorPaneException);
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			    okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setEnabled(true);
			}
			{
				btnSaveException = new JButton("Salva errore");
				buttonPane.add(btnSaveException);
			}
		}
	}

}
