package dkeep.logic;

import javax.swing.JTextArea;

/**
 * Utils.java - class to util methods
 */
public class Utils {
	/**
	 * Default constructor for class 'Utilities'
	 */
	public Utils() {}

	/*
	 * Method to verify if is numeric
	 * @param text String
	 * @return A boolean data type
	 */
	public boolean isNumeric(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Method to print game in gameArea
	 * @param gameArea
	 * @param game
	 */
	public void printGameArea(JTextArea gameArea, Level game) {
		
	}
}
