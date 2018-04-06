package dkeep.logic;

import dkeep.cli.Game;
/**
 * Hero.java - a class for Hero
 * @see Character
 */
public class Hero extends Character{
	private char rep ='H'; 
	/**
	 * Method to check if an element is close to other element
	 * @param coord position of a element
	 * @param board current board
	 * @param element current element
	 * @return A boolean data type
	 */
	public static boolean isAdjacent(Coordinates coord, char[][] board, char element) {
		if(board[coord.getX()-1][coord.getY()] == element || board[coord.getX()+1][coord.getY()] == element || board[coord.getX()][coord.getY()-1] == element || board[coord.getX()][coord.getY()+1] == element)
			return true;
		else return false;
	}
	
	/**
	 * Method to pull the lever or catch the key 
	 * @param input key to be press
	 * @param coord coordinates
	 * @param board current board
	 */
	public void getAction(char input, Coordinates coord, char[][]board) {
		if(input == 'T' || input == 't') {
			if(isAdjacent(coord, board, 'k')){
				board[5][0]='S';
				board[6][0]='S';
				Game.flag=1; 
			}
		}
		if(input == 'C' || input == 'c') {
			if(isAdjacent(coord, board, 'k')){
				this.rep='K';
				board[1][7]=' ';
				board[coord.getX()][coord.getY()]='K';
			}
		}
	}
		
}
