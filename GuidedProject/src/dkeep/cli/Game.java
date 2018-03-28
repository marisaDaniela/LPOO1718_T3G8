package dkeep.cli;
import java.util.Random;
import java.util.Scanner;
import dkeep.logic.*;
import dkeep.logic.Character;

public class Game {
	
	static int flag=0;
	static char hero='H';
	static int game_flag=1;
	static int flag_move=0;

	
	public static void main(String[] args) {
		Level level1 = new Level1();
		Level level2 = new Level2();
		Hero hero = null;
		Guard guard = null;
		GameState game = null;
		Scanner key = new Scanner(System.in);
		char key2 = '1';
		
		int count=0;
		
		char[][] board = level1.getMap();
		char[][] board2 = level2.getMap();

		GameState.printBoard(board, 10);
		
		while (true) 
		{
			key2 = key.next().charAt(0);

			if(key2 == 'q' || key2 == 'Q') 
				break;
			
			Coordinates coord = Character.getPos(board, 'H', 10);
			Character.checkDirection(key2, coord, board, 'H');
			
			coord = Character.getPos(board, 'H', 10);
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
			
			GameState.printBoard(board, 10);
			
			if(((coord.getX()==5 && coord.getY()==1) || (coord.getX()==6 && coord.getY()==1)) && flag==1) {
				while(true) {
					game_flag=2;
					
					if(flag_move==1)
					{	
						break;
					}
					
					key2 = key.next().charAt(0); 
					
					if(key2 == 'q' || key2 == 'Q') 
						break;
					
					Ogre.ogreMovement(board2); 
					
					coord = Character.getPos(board, 'H', 9);
					
					Character.checkDirection(key2, coord, board2, 'H');
					
					
					if(Hero.isAdjacent(coord, board2, 'O')  || Hero.isAdjacent(coord, board2, '*'))
					{
						GameState.printBoard(board2, 9);
						System.out.println("GAME OVER!");
						key.close();
						return;
					}
					
					GameState.printBoard(board2, 9);
					
					
				}
				break;
				
			}
					

		}
		key.close();

	}




}
