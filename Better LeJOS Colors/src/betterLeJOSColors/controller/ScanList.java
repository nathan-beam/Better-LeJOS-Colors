package betterLeJOSColors.controller;

import java.util.ArrayList;

import betterLeJOSColors.model.Color;
/**
 * ScanList class to hold scanned in colors
 * 
 * @author Nathan Beam 
 * @version Fall 2016
 */
public class ScanList {

	private ArrayList<Color> scanList;

	/**
	 * Constructor for the ScanList
	 */
	public ScanList() {
		this.scanList = new ArrayList<Color>();
	}

	/**
	 * Adds a Color object to the ScanList
	 *
	 * @precondition none
	 * @postcondition scanlist has one more object stored within
	 * @param color
	 *            the Color to be added
	 */
	public void addColor(Color color) {
		this.scanList.add(color);
	}

	/**
	 * returns the size of the ScanList
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the size of the ScanList
	 */
	public int size() {
		return this.scanList.size();
	}

	/**
	 * Gets all the possible Color names, without duplicates.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return all possible color names
	 */
	public ArrayList<String> getAllColorNames() {
		ArrayList<String> colorNames = new ArrayList<String>();
		for (Color color : this.scanList) {
			String colorName = color.getColorName();
			if (!colorNames.contains(colorName)) {
				colorNames.add(colorName);
			}
		}
		return colorNames;
	}

	/**
	 * Returns the ArrayList itself
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the contained ArrayList
	 */
	public ArrayList<Color> getScanList() {
		return this.scanList;
	}

	/**
	 * Sets the contained ArrayList to the provided ArrayList
	 *
	 * @precondition scanList != null
	 * @postcondition this.scanList = scanList
	 * @param scanList
	 *            the new ArrayList to use
	 */
	public void setScanList(ArrayList<Color> scanList) {
		this.scanList = scanList;
	}

}
