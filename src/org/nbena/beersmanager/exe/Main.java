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
package org.nbena.beersmanager.exe;


import java.io.FileNotFoundException;



import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONException;
import org.nbena.beersmanager.conf.ConfigurationFactory;
import org.nbena.beersmanager.conf.ConfigurationNew;
import org.nbena.beersmanager.exceptions.RecomposingException;
import org.nbena.beersmanager.exe.ui.Controller;
import org.nbena.beersmanager.exe.ui.models.Model;
import org.nbena.beersmanager.exe.ui.views.ViewMainGUI;

/**
 * The main class. It creates instance of View, Model and Controller.
 * @author nbena
 *
 */
public class Main {
	
	private static void tryConfiguration(Model model, ConfigurationNew conf){
		//here try to read configuration.
		try {
			conf = ConfigurationFactory.readConfiguration(ConfigurationFactory.getDefaultConfigurationPath());
			conf = ConfigurationFactory.setupPath(conf);
			model.setConfiguration(conf);
		} catch (JSONException | FileNotFoundException e) {
			
			//if fails, tell to user 
			Controller.showExceptionDialog(e);
			Controller.tellToUserDefaultOptionWillBeUsed();
			
			conf = ConfigurationFactory.getDefaultConfiguration();
			model.setConfiguration(conf);
			//try to save the new-default configuration
			try {
				model.saveConfiguration();
			} catch (FileNotFoundException e1) {
				
				Controller.tellUserErrorSaveDefaultConfig();
				//it happens only if we are very unlucky!!
			}
		}
	}
	
	private static void trySetData(Model model, Controller controller){
		controller.setShowBreweriesAverages(true);
		
		try {
			model.readThings();
		} catch (FileNotFoundException | JSONException | RecomposingException e) {
			
			Controller.showExceptionDialog(e);
			System.exit(1);
		}
		
		model.setAverages();
		controller.showDefault();
	}

	private static void tryCountries(Model model){
		try {
			model.setCountries(Utils.getCountries(model.getConfiguration().getCountriesFilePath()));
		} catch (JSONException | FileNotFoundException e) {
			
			Controller.showExceptionDialog(e);
			Controller.tellUserErrorNoCountry();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
				
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				
				Controller.showExceptionDialog(e);
				System.exit(1);
			}
			
			Model model =new Model();
			ConfigurationNew conf = null;
			
			tryConfiguration(model, conf);
			
			tryCountries(model);
			
			
			ViewMainGUI gui = new ViewMainGUI(model);
			Controller controller=new Controller(gui, model);
			
			
			trySetData(model, controller);


	}

}
