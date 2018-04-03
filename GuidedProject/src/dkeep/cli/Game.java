package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;
import dkeep.logic.Character;

public class Game {
	
	public static int flag=0;
	public static char hero='H';
	public static int game_flag=1;
	public static int flag_move=0;
	public static Level level1 = new Level1();
	public static Level level2 = new Level2();

	
	public static void main(String[] args) {
	
		Scanner key = new Scanner(System.in);
		char key2 = '1';
		
		int count = 0;
		
		char[][] board = level1.getMap();
		char[][] board2 = level2.getMap();

		GameState.printBoard(board, 10);
		
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
				Guard.movement(board, count);

				if(Hero.isAdjacent(coord, board, 'G'))
				{
					GameState.printBoard(board, 10);
					System.out.println("GAME OVER!");
					key.close();
					return;
				}
				
				if(count==23) {
					count=0;
				}else
					count++;
				
				if(game_flag!=2) {
					GameState.printBoard(board, 10);
				}else {
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
			
			
			
				//break;
				
			}
					
		key.close();

	}




}
