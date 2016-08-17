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

public class ViewException extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldErrorType;
	private JTextField textFieldErrorMessage;
	
	private JButton okButton;
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
	
	public void initEditorPane(){
		editorPaneException.setContentType("text/html");
		editorPaneException.setCaretPosition(0);
		editorPaneException.setEditable(false);
	}
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
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
		setBounds(100, 100, 450, 442);
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
			JLabel lblErrore = new JLabel("Errore:");
			GridBagConstraints gbc_lblErrore = new GridBagConstraints();
			gbc_lblErrore.insets = new Insets(0, 0, 5, 5);
			gbc_lblErrore.gridx = 0;
			gbc_lblErrore.gridy = 2;
			contentPanel.add(lblErrore, gbc_lblErrore);
		}
		{
			textFieldErrorType = new JTextField();
			GridBagConstraints gbc_textFieldErrorType = new GridBagConstraints();
			gbc_textFieldErrorType.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldErrorType.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldErrorType.gridx = 2;
			gbc_textFieldErrorType.gridy = 2;
			contentPanel.add(textFieldErrorType, gbc_textFieldErrorType);
			textFieldErrorType.setColumns(10);
		}
		{
			JLabel lblMessaggio = new JLabel("Messaggio:");
			GridBagConstraints gbc_lblMessaggio = new GridBagConstraints();
			gbc_lblMessaggio.insets = new Insets(0, 0, 5, 5);
			gbc_lblMessaggio.gridx = 0;
			gbc_lblMessaggio.gridy = 4;
			contentPanel.add(lblMessaggio, gbc_lblMessaggio);
		}
		{
			textFieldErrorMessage = new JTextField();
			GridBagConstraints gbc_textFieldErrorMessage = new GridBagConstraints();
			gbc_textFieldErrorMessage.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldErrorMessage.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldErrorMessage.gridx = 2;
			gbc_textFieldErrorMessage.gridy = 4;
			contentPanel.add(textFieldErrorMessage, gbc_textFieldErrorMessage);
			textFieldErrorMessage.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{312, 47, 65, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
