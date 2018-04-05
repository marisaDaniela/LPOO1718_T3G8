package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;
import dkeep.logic.Character;

public class Game {

	public static int flag=0;
	public static char hero='H';
	public static char guard='G';
	public static int game_flag=1;
	public static int flag_move=0;
	public static boolean insertOgre=false;
	public static Level level1 = new Level1();
	public static Level level2 = new Level2();


	public static void main(String[] args) {

		Scanner key = new Scanner(System.in);
		char key2 = '1';

		int count = 0;

		char[][] board = level1.getMap();
		char[][] board2 = level2.getMap();

		GameState.printBoard(board, 10);

		//Guard.personality=Guard.randomPersonality();

		Guard.personality="Drunken";

		System.out.println("Guard Personality: " + Guard.personality + "\n");


		Coordinates coord = new Coordinates();

		while (true) 
		{
			if(game_flag==1) {
				key2 = key.next().charAt(0);

				if(key2 == 'q' || key2 == 'Q') 
					break;

				coord = Character.getPos(board, hero, 10);
				Character.checkDirection(key2, coord, board, hero);

				coord = Character.getPos(board, hero, 10);
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
								count++;
							//System.out.println("count: " + count + "\n");
						}

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
							count++;
					}
				}
				if(Hero.isAdjacent(coord, board, 'G'))
				{
					GameOver(key, board);
					return;
				}
				if(game_flag!=2) {
					GameState.printBoard(board, 10);
				}else {
					if(!insertOgre) {
						int number =  ((int) (Math.random() * (3 - 1))) + 1;
						Ogre.insertOgres(board2, number);
						insertOgre = true;
					}

					for(int i = 0; i < Ogre.ogres.size(); i++) {
						Ogre.ogres.get(i).ogreMovement(board2);
					}
					GameState.printBoard(board2, 9);
				}
			}else if(game_flag==2) {
				key2 = key.next().charAt(0); 

				if(key2 == 'q' || key2 == 'Q') 
					break;
				coord = Character.getPos(board2, hero, 9);
				Character.checkDirection(key2, coord, board2, hero);
				coord = Character.getPos(board2, hero, 9);
				//Ogre.ogreMovement(board2);
				if(Hero.isAdjacent(coord, board2, 'O')  || Hero.isAdjacent(coord, board2, '*'))
				{
					GameState.printBoard(board2, 9);
					System.out.println("GAME OVER!");
					key.close();
					return;
				}

				if(flag_move==1)//if flg_move is 1 it means it's the end of the game
				{	
					break;
				}
				GameState.printBoard(board2, 9);

			}
		}
		key.close();
	}


	/**
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
