
package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.cli.Game;
/**
 * Ogre.java - classe for ogre
 * @see Character
 */
public class Ogre extends Character {
	public static ArrayList<Ogre>ogres = new ArrayList();

	/**
	 * ogreMovement - Method to move an ogre
	 * @param board2 current board
	 */
	public static void ogreMovement(char[][]board2) {
		char move= randomDirection();		
		Coordinates pos= getPos(board2, 'O', 9);
		checkDirection(move, pos, board2, 'O');
		pos= getPos(board2, 'O', 9);
		if(Game.hero!='K')
			board2[1][7]='k';
		Club.clubSwing(pos, board2);
	}

	/**
	 * insertOgre - Method to place an ogre at board
	 * @param x position xx
	 * @param y posistion yy
	 * @param board2 current board
	 */
	public static void insertOgre(int x, int y, char[][]board2) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board2[x][y]='O';
			}
		}
	}
	/**
	 * Method to place an club at board
	 * @param x position xx
	 * @param y posistion yy
	 * @param board2 current board
	 */
	public static void insertClub(int x, int y, char[][] board2) {
		if(board2[x+1][y] == ' ')
			board2[x+1][y]='*';
		else if(board2[x-1][y] == ' ')
			board2[x-1][y]='*';
		else if(board2[x][y+1] == ' ')
			board2[x][y+1]='*';
		else if(board2[x][y-1] == ' ')
			board2[x][y-1]='*';
	}
	/**
	 * Method to place multiple ogres at board
	 * @param board2 current board
	 * @param number number of ogres
	 */
	public static void insertOgres(char[][]board2, int number) {
		while(number > 0) {
			Random r = new Random();
			int line = 2;
			int col = 8;
			int x = r.nextInt(col-line) + line;
			r = new Random();
			int y = r.nextInt(col-line) + line;

			Ogre ogre = new Ogre();
			ogres.add(ogre);
			insertOgre(x,y, board2);
			insertClub(x,y, board2);
			number--;
		}
	}
}
