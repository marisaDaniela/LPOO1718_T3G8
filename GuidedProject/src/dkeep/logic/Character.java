package dkeep.logic;

import java.util.Random;
import dkeep.cli.Game;

/**  
* Character.java - a class for Character
*/ 
public class Character {

	/**
	 * getPos - finds a position of an character in the board
	 * @param board current board
	 * @param mover current character
	 * @param size size of current board
	 * @return A Coordinates object
	 */
	public static Coordinates getPos(char[][] board, char mover, int size){
		Coordinates pos= new Coordinates();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j]==mover){
					pos.setX(i); 
					pos.setY(j);}}}
		return pos;}
	
	/**
	 * atLever - method to see if an character is close to the lever
	 * @param x coordinate x of a character
	 * @param y coordinate y of a character
	 * @param board current board
	 * @param sym symbol of a character
	 * @return A boolean data type
	 */
	public static boolean atLever(int x, int y, char[][]board, char sym){
		if(board[x-1][y] == sym || board[x+1][y] == sym || board[x][y-1] == sym || board[x][y+1] == sym)
			return true;
		return false;
	}

	/**
	 * checnkDirection - Method to check if an character can move and moves it
	 * @param input key that an user presses
	 * @param pos Coordinates
	 * @param board current board
	 * @param mover current character
	 */
	public static void checkDirection(char input, Coordinates pos, char[][]board, char mover) {
		int x= pos.getX();
		int y= pos.getY();
		moveLeft(input, board, mover, x, y);
		moveDown(input, board, mover, x, y);
		moveUP(input, board, mover, x, y);
		moveRight(input, board, mover, x, y);
		pullLever(input, board, x, y);
		catchArm(input, board, x, y);
		catchKey(input, board, x, y);
	}

	/**
	 * catchKey - Method to hero catch the key
	 * @param input key that user press
	 * @param board current board
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public static void catchKey(char input, char[][] board, int x, int y) {
		if(input == 'C' || input == 'c') {
			if(atLever(x,y, board, 'k')) {
				Game.hero='K';
				for (int i = 0; i < board[0].length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == 'k')
							board[i][j] = ' ';}}
				board[x][y]='K';}
			else
				System.out.println("Nothing to do, try again!!");}}
	
	/**
	 * catchArm - Method to hero catch the arm
	 * @param input key that user press
	 * @param board current board
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void catchArm(char input, char[][] board, int x, int y) {
		if(input == 'F' || input == 'f') {
			if(atLever(x,y, board, 'a')) {
				Game.hero='A';
				for (int i = 0; i < board[0].length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == 'a')
							board[i][j] = ' ';}}
				board[x][y]='A';}
			else
				System.out.println("Nothing to do, try again!!");}}

	/**
	 * pullLever - Method to hero catch the arm
	 * @param input key that user press
	 * @param board current board
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void pullLever(char input, char[][] board, int x, int y) {
		if(input == 'T' || input == 't') {
			if(atLever(x,y, board, 'k')){
				for (int i = 0; i < board[0].length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == 'I')
							board[i][j] = 'S';}}}
			else System.out.println("Nothing to do, try again!!");}}

	/**
	 * moveRight - Method for character moves right
	 * @param input key that user press
	 * @param board current board
	 * @param mover current mover
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void moveRight(char input, char[][] board, char mover, int x, int y) {
		if(input == 'D' || input == 'd'){
			if(board[x][y+1]==' '){
				board[x][y+1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y+1]=='k') {
				board[x][y+1]='$';
				board[x][y+1]=' ';}}
	}

	/**
	 * moveUp - Method for character moves up
	 * @param input key that user press
	 * @param board current board
	 * @param mover current mover
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void moveUP(char input, char[][] board, char mover, int x, int y) {
		if(input == 'W' || input == 'w'){
			if(board[x-1][y]==' '){
				board[x-1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x-1][y]=='k') {
				board[x-1][y]='$';
				board[x-1][y]=' ';
				}
			}
		}

	/**
	 * moveDown - Method for character moves down
	 * @param input key that user press
	 * @param board current board
	 * @param mover current mover
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void moveDown(char input, char[][] board, char mover, int x, int y) {
		if(input == 'S' || input == 's'){
			if(board[x+1][y]==' '){
				board[x+1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x+1][y]=='k') {
				board[x+1][y]='$';
				board[x][y]=' ';
			}
		}
	}

	/**
	 * moveLeft - Method for character moves left
	 * @param input key that user press
	 * @param board current board
	 * @param mover current mover
	 * @param x Coordenate x
	 * @param y Coordenate y
	 */
	public static void moveLeft(char input, char[][] board, char mover, int x, int y) {
		if(input == 'A' || input == 'a'){	
			if(board[x][y-1]==' '){
				board[x][y-1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y-1]=='k') {
				board[x][y-1]='$';
				board[x][y]=' ';
			}else if(mover==Game.hero && board[x][y-1]=='S' && Game.game_flag==1) {
				Game.game_flag=2;
			}else if(mover==Game.hero && board[x][y-1]=='I' && Game.game_flag==2) {
				board[x][y-1]='S';
			}else if(mover==Game.hero && board[x][y-1]=='S' && Game.game_flag==2) {
				System.out.println("END OF GAME\nYOU WIN!!!");
				Game.flag_move=1;
				Game.WIN = true;
			}
		}
	}

	/**
	 * Method to choose a random movement
	 * @return
	 */
	public static char randomDirection() {
		Random rand = new Random();
		char move=' ';
		int num_move=rand.nextInt(4) + 1;
		switch(num_move) {
		case 1:
			move='A';
			break;
		case 2:
			move='S'; 
			break;
		case 3:
			move='W';
			break;
		case 4:
			move='D';
			break;
		}
		return move;
	}

	/**
	 * Method for a randomChoice
	 * @return
	 */
	public static int randomChoice() {
		Random rand = new Random();
		int choice=rand.nextInt(2) + 1;
		return choice;
	}

	/**
	 * Method for a randomNumberPlays;
	 * @return
	 */
	public static int randomNumberPlays() {
		Random rand = new Random();
		int num=rand.nextInt(6) + 2;
		return num;
	}
}
