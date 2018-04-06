package dkeep.logic;

import java.util.Random;

import dkeep.cli.Game;
/**
 * Guard.java - a class for Guard
 * @see Character
 */
public class Guard extends Character{
	
	public static String personality= new String();
	
	public static boolean  asleep=false;
	public static int  num_laps=0;
	public static boolean  reverse=false;
	
	/**
	 * movement Method for guard movement
	 * @param board current board
	 * @param count counter
	 */
	public static void movement(char[][]board, int count) {
		Coordinates pos= getPos(board, Game.guard, 10);
		char moves[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
		if(personality == "Rookie") {
			checkDirection(moves[count], pos, board, 'G');
		}
		else if(personality == "Drunken") {
				drunken(board, count, pos, moves);
		} 
		else if(personality == "Suspicious") {	
			suspicious();
			if(reverse==true) {
				if(count==0)
					count=23;
				else
					count--;
				char newDirection=invertDirection(moves[count]);
				checkDirection(newDirection, pos, board, 'G');
			}else if(reverse==false)
			checkDirection(moves[count], pos, board, 'G');
		}
	}

	/**
	 * Method for suspicious movement
	 */
	public static void suspicious() {
		if(num_laps==0) {
			int choice=randomChoice();
			if(choice==1) {
				reverse=false;
				num_laps=randomNumberPlays();
			}else if(choice==2) {
				reverse=true;
				num_laps=randomNumberPlays();
			}
		}
	}

	/**
	 * Method to Drunken movement
	 * @param board current board
	 * @param count counter
	 * @param pos coordinates
	 * @param moves possible movements 
	 */
	public static void drunken(char[][] board, int count, Coordinates pos, char[] moves) {
		int moving = randomChoice();
		if(moving==1) {
			if(asleep==true) {
				asleep=false;
				System.out.println("G pos: " + pos.getX() + " " + pos.getY() + "\n");
				Game.guard='G';
			}else
			if(reverse==true) {
				char newDirection=invertDirection(moves[count]);
				checkDirection(newDirection, pos, board, 'G');
			}else
				System.out.println("count: " + count + ", move: " + moves[count] + "\n");
				checkDirection(moves[count], pos, board, 'G');
		}else if(moving==2 && asleep!=true) {
			System.out.println("g pos: " + pos.getX() + ", " + pos.getY() + "\n");
			board[pos.getX()][pos.getY()]='g';
			Game.guard='g';
			asleep=true;
			num_laps=randomNumberPlays();
			int choice=randomChoice();
			if(choice==1) {
				reverse=false;
			}else if(choice==2) {
				reverse=true;}
		}else if(moving==2 && asleep==true) {
			movement(board, count);
		}
	}
	/**
	 * Method for randomize guard personality
	 * @return A string data type
	 */
	public static String randomPersonality() {
		Random rand = new Random();
		String personality = new String();	
		int i=rand.nextInt(3) + 1;		
		if(i==1) {
			personality="Rookie";
		}else if(i==2) {
			personality="Drunken";
		}else if(i==3) {
			personality="Suspicious";
		}	
		return personality;
	}
	/**
	 * Method to invert guard direction
	 * @param direction char to choose direction
	 * @return A char data type
	 */
	public static char invertDirection(char direction) {
		char newDirection =' ';	
		if(direction=='A')
			newDirection='D';
		if(direction=='D')
			newDirection='A';
		if(direction=='W')
			newDirection='S';
		if(direction=='S')
			newDirection='W';	
		return newDirection;	
	}
}
