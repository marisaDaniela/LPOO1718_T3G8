package dungeon;
import java.io.IOException;
import java.util.Scanner;

import dungeon.Hero;

public class Game {
	
	public static void printBoard(char[][] board) {
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(board[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}
		
	}
	
	public static char read() {
		Scanner reader = new Scanner(System.in);  
		System.out.println("Direction: ");
		Scanner s = new Scanner(System.in);
		char x = s.next().charAt(0);
		reader.close(); 
		return x;
	}
	
	public static int[] getPos(char[][] board){
		int[] pos = new int[2];
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(board[i][j]=='H')
				{
					pos[0] = i; 
					pos[1] = j;
				}
			}
		}
		return pos;
	}
	
	public static void checkDirection(char input, int[] pos, char[][]board)
	{
		int x= pos[0];
		int y= pos[1];
		System.out.println(pos[0]);
		System.out.println(pos[1]);
		if(input == 'A')
		{
			if(board[x][y-1]==' '){
				board[x][y-1]='H';
				board[x][y]=' ';
			}
				
		}
		if(input == 'W')
		{
			if(board[x+1][y]==' '){
				board[x+1][y]='H';
				board[x][y]=' ';
			}
				
		}
		if(input == 'S')
		{
			if(board[x-1][y]==' '){
				board[x-1][y]='H';
				board[x][y]=' ';
			}
				
		}
		if(input == 'D')
		{
			if(board[x][y+1]==' '){
				board[x][y+1]='H';
				board[x][y]=' ';
			}
				
		}

	}
	public static void main(String[] args) {
		
		char[][] board = new char [][]{
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
			
		};
		printBoard(board);
		char input = read();
		int pos[] = getPos(board);
		checkDirection(input, pos, board);
		printBoard(board);
		
	}

}
