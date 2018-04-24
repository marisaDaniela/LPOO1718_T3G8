package dkeep.cli;
import java.util.Scanner;

import dkeep.elements.Guard;
import dkeep.elements.Hero;
import dkeep.elements.Ogre;
import dkeep.logic.*;
import dkeep.logic.Character;
/**
 * Game.java - main menu of game in the console
 */
public class Game {
	public static int flag=0;
	public static char hero='H';
	public static char guard='G';
	public static char ogre='O';
	public static int game_flag=1;
	public static int flag_move=0;
	public static boolean insertOgre=false;
	public static Level level1 = new Level1();
	public static Level level2 = new Level2();
	public static boolean WIN = false;

	public static void main(String[] args) { 
		Scanner key = new Scanner(System.in);
		int count = 0;
		char[][] board = level1.getMap();
		char[][] board2 = level2.getMap();
		Scanner input = new Scanner(System.in);
		menuInicial(input);
		GameLoop(key, count, board, board2);
	}


	/**
	 * menuInicial - for an user choose a guard personality
	 * @param input - key for user choose
	 */
	public static void menuInicial(Scanner input) {
		int selection;
		System.out.print("+----------------------------+\n");
		System.out.println("|  Choose guard personality: |");
		System.out.print("+----------------------------+\n");
		System.out.println("|   1 - Rookie               |");
		System.out.println("|   2 - Suspicious           |");
		System.out.println("|   3 - Drunken              |");
		System.out.println("|   4 - Quit                 |");
		System.out.println("+----------------------------+\n");

		selection = input.nextInt();
		switch(selection) {
		case 1:
			Guard.personality="Rookie";
			break;
		case 2: 
			Guard.personality="Suspicious";
			break;
		case 3: 
			Guard.personality="Drunken";
			break;
		case 4: 
			System.exit(0);
		default:
			Guard.personality="Rookie";
			break;
		}
	}


	/** GameLoop - heart of the game. Here we have all the game proccess.
	 * @param key - key that an user will press
	 * @param count - counter
	 * @param board - map of level 1
	 * @param board2 - map of level 2
	 */
	public static void GameLoop(Scanner key, int count, char[][] board, char[][] board2) {
		char key2;
		GameState.printBoard(board, 10);
		Coordinates coord = new Coordinates();

		while (true) {
			if(game_flag==1) {
				key2 = key.next().charAt(0);
				if(key2 == 'q' || key2 == 'Q') 
					break;
				coord = Character.getPos(board, hero, 10);
				Character.checkDirection(key2, coord, board, hero);
				coord = Character.getPos(board, hero, 10);
				count = guardPersonalities(count, board);
				if(Hero.isAdjacent(coord, board, guard)){
					GameOver(key, board);
					return;}
				if(game_flag!=2) {
					GameState.printBoard(board, 10);
				}else {
					if(!insertOgre) {
						int number =  ((int) (Math.random() * (3 - 1))) + 1;
						//Ogre.insertOgres(board2, number);
						insertOgre = true;}
					for(int i = 0; i < Ogre.ogres.size(); i++) {
						Ogre.ogres.get(i);
						Ogre.ogreMovement(board2);}
					GameState.printBoard(board2, 9);}
			}else if(game_flag==2) {
				key2 = key.next().charAt(0); 
				if(key2 == 'q' || key2 == 'Q') 
					break;
				coord = Character.getPos(board2, hero, 9);
				Character.checkDirection(key2, coord, board2, hero);
				coord = Character.getPos(board2, hero, 9);
				for(int i = 0; i < Ogre.ogres.size(); i++) {
					Ogre.ogres.get(i);
					Ogre.ogreMovement(board2);}
				if(Hero.isAdjacent(coord, board2, ogre)  || Hero.isAdjacent(coord, board2, '*')){
					if(hero == 'A') {
						ogre = '8';
					} else {
					GameState.printBoard(board2, 9);
					System.out.println("GAME OVER!");
					key.close();
					return;}}
				if(flag_move==1) { //if flg_move is 1 it means it's the end of the game 
					break;
				}
				GameState.printBoard(board2, 9);
			}
		}
		key.close();
	}


	/**
	 * guardPersonalities - method that deals with guard personalities
	 * @param count - counter
	 * @param board - map do level 1
	 * @return
	 */
	public static int guardPersonalities(int count, char[][] board) {
		if(Guard.personality=="Rookie") {
			Guard.movement(board, count);
			if(count==23) {
				count=0;
			}else
				count++;
		}else if(Guard.personality=="Drunken") {
			if(Guard.num_laps==0) {
				Guard.movement(board, count);
				if(Guard.reverse==true) {
					System.out.println("reversing \n");
					if(count==0) {
						count=23;
					}else count--;
				}else {
					System.out.println("not reversing \n");
					if(count==23) {
						count=0;
					}else if(Guard.asleep!=true)
						count++;}
			}else {
				System.out.println("num laps: " + Guard.num_laps + "\n");
				Guard.num_laps--;
			}
		}else if(Guard.personality == "Suspicious") {
			Guard.movement(board, count);
			Guard.num_laps--;
			if(Guard.reverse==true) {
				if(count==0) {
					count=23;
				}else count--;
			}else {
				if(count==23) {
					count=0;
				}else
					count++;}}
		return count;
	}

 
	/**
	 * GameOver - end of game one
	 * @param key 
	 * @param board
	 */
	public static void GameOver(Scanner key, char[][] board) {
		GameState.printBoard(board, 10);
		System.out.println("GAME OVER!");
		key.close();
		return;
	}
}
