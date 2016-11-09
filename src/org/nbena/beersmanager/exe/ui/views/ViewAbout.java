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
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class ViewAbout extends JDialog /*not implements super abstract because it does not implement the cancel button*/ {

	private final JPanel contentPanel = new JPanel();
	private JEditorPane editorPaneGPL;
	private JEditorPane editorPaneOther;
	private JButton okButton;


	
	public void setGPL(String license, String type){
		editorPaneGPL.setContentType(type);
		editorPaneGPL.setText(license);
		editorPaneGPL.setEditable(false);
		editorPaneGPL.setCaretPosition(0);
	}
	
	public void setOther(String others, String type){
		editorPaneOther.setContentType(type);
		editorPaneOther.setText(others);
		editorPaneOther.setEditable(false);
	}
	
	public void addActionListenerOkButton(ActionListener listener){
		okButton.addActionListener(listener);
	}

	/**
	 * Create the dialog.
	 */
	public ViewAbout() {
		setBounds(100, 100, 496, 483);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNbeersmanagerLicenziatoSotto = new JLabel("NBeersManager: licenziato sotto la GPL 3.");
			GridBagConstraints gbc_lblNbeersmanagerLicenziatoSotto = new GridBagConstraints();
			gbc_lblNbeersmanagerLicenziatoSotto.insets = new Insets(0, 0, 5, 0);
			gbc_lblNbeersmanagerLicenziatoSotto.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNbeersmanagerLicenziatoSotto.gridx = 0;
			gbc_lblNbeersmanagerLicenziatoSotto.gridy = 0;
			contentPanel.add(lblNbeersmanagerLicenziatoSotto, gbc_lblNbeersmanagerLicenziatoSotto);
		}
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.gridx = 0;
			gbc_tabbedPane.gridy = 1;
			contentPanel.add(tabbedPane, gbc_tabbedPane);
			{
				JPanel panelGPL = new JPanel();
				tabbedPane.addTab("Licenza GPL", null, panelGPL, null);
				GridBagLayout gbl_panelGPL = new GridBagLayout();
				gbl_panelGPL.columnWidths = new int[]{0, 0};
				gbl_panelGPL.rowHeights = new int[]{0, 0};
				gbl_panelGPL.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panelGPL.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panelGPL.setLayout(gbl_panelGPL);
				{
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 0;
					panelGPL.add(scrollPane, gbc_scrollPane);
					{
						editorPaneGPL = new JEditorPane();
						scrollPane.setViewportView(editorPaneGPL);
					}
				}
			}
			{
				JPanel panelOther = new JPanel();
				tabbedPane.addTab("Licenze terze parti", null, panelOther, null);
				GridBagLayout gbl_panelOther = new GridBagLayout();
				gbl_panelOther.columnWidths = new int[]{0, 0};
				gbl_panelOther.rowHeights = new int[]{0, 0, 0};
				gbl_panelOther.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panelOther.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				panelOther.setLayout(gbl_panelOther);
				{
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 0;
					panelOther.add(scrollPane, gbc_scrollPane);

					editorPaneOther = new JEditorPane();
//					GridBagConstraints gbc_editorPaneOther = new GridBagConstraints();
//					gbc_editorPaneOther.anchor = GridBagConstraints.NORTHEAST;
//					gbc_editorPaneOther.gridx = 0;
//					gbc_editorPaneOther.gridy = 1;
					scrollPane.setViewportView(editorPaneOther);
					
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
//			gbl_buttonPane.columnWidths = new int[]{312, 47, 65, 0};
//			gbl_buttonPane.rowHeights = new int[]{23, 0};
//			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.CENTER;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
//				gbc_okButton.gridx = 1;
//				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
//				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
