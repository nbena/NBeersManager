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
package org.nbena.beersmanager.sclasses;

/**
 * The class version of {@link #org.nbena.beersmanager.coreclasses.Fermentation}, that contains the enumerator values as string,
 * because my JSON exporter does not known enums.
 * @author nbena
 * @see org.nbena.beersmanager.coreclasses.Fermentation
 *
 */
public class FermentationJSONSpecialClass {
	
	public static final String HIGH = "High";
	public static final String LOW = "Low";
	public static final String SPONTANEOUS = "Spontaneous";
	
	

}
