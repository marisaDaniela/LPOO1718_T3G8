package dkeep.cli;

import javax.swing.JTextArea;

import dkeep.logic.GameState;
import dkeep.logic.Level;

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
		char[][] map = game.getMap();
		for(int i = 0; i < map.length; i++) {
			gameArea.append("\n");
			for(int j = 0; j < map.length; j++) {
				gameArea.setText(GameState.printBoard(map,  map.length));
			}	
		}
	}
}
