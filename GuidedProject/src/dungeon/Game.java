package dungeon;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	static int flag=0;
	static char hero='H';
	static int game_flag=1;
	static int flag_move=0;

	public static void printBoard(char[][] board, int size) {
		System.out.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(board[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}

	}


	public static int[] getPos(char[][] board, char mover, int size){
		int[] pos = new int[2];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(board[i][j]==mover)
				{
					pos[0] = i; 
					pos[1] = j;
				}
			}
		}
		return pos;
	}


	public static void checkDirection(char input, int[] pos, char[][]board, char mover)
	{
		int x= pos[0];
		int y= pos[1];


		if(input == 'A' || input == 'a')
		{	
			if(board[x][y-1]==' '){
				board[x][y-1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y-1]=='k') {
				board[x][y-1]='$';
				board[x][y]=' ';
			}else if(mover==hero && board[x][y-1]=='I' && game_flag==2) {
				if(flag_move==0) {
					flag_move=1;
					board[x][y-1]='S';}
				else {
					System.out.println("END OF GAME\n");
				}
					
			}

		}
		if(input == 'S' || input == 's')
		{
			if(board[x+1][y]==' '){
				board[x+1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x+1][y]=='k') {
				board[x+1][y]='$';
				board[x][y]=' ';
			}
		
		}
		if(input == 'W' || input == 'w')
		{
			if(board[x-1][y]==' '){
				board[x-1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x-1][y]=='k') {
				board[x-1][y]='$';
				board[x-1][y]=' ';
			}

		}
		if(input == 'D' || input == 'd')
		{
			if(board[x][y+1]==' '){
				board[x][y+1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y+1]=='k') {
				board[x][y+1]='$';
				board[x][y+1]=' ';
			}
	
		}
		if(input == 'T' || input == 't') {
			if(atLever(x,y, board))
			{
				board[5][0]='S';
				board[6][0]='S';
				flag=1;
			}
			else
				System.out.println("Nothing to do, try again!!");
		}
		if(input == 'C' || input == 'c') {
			if(atLever(x,y, board))
			{
				hero='K';
				board[1][7]=' ';
				board[pos[0]][pos[1]]='K';
			}
			else
				System.out.println("Nothing to do, try again!!");
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

		
		char[][] board2 = new char [][]{
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', 'X'},
			{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}

		};

		Scanner key = new Scanner(System.in);
		char key2 = '1';
		
		int count=0;

		printBoard(board, 10);
		
		while (true) 
		{
			key2 = key.next().charAt(0);

			if(key2 == 'q' || key2 == 'Q') 
				break;


			int pos[] = getPos(board, hero, 10);
			
			checkDirection(key2, pos, board, hero);
			
			pos = getPos(board, hero, 10);
			
			guardMovement(board, count);
			
			if(adjacentEnemy(pos[0], pos[1], board, 'G'))
			{
				printBoard(board, 10);
				System.out.println("GAME OVER!");
				key.close();
				return;
			}
			
			if(count==23) {
				count=0;
			}else
				count++;
			
			printBoard(board, 10);
			
			if(((pos[0]==5 && pos[1]==1) || (pos[0]==6 && pos[1]==1)) && flag==1) {
				while(true) {
					game_flag=2;
					
					if(flag_move==1)
					{	
						break;
					}
					
					key2 = key.next().charAt(0); 
					
					if(key2 == 'q' || key2 == 'Q') 
						break;
					
					ogreMovement(board2); 
					
					pos = getPos(board2, hero, 9);
					
					checkDirection(key2, pos, board2, hero);
					
					
					if(adjacentEnemy(pos[0], pos[1], board2, 'O') || adjacentEnemy(pos[0], pos[1], board2, '*'))
					{
						printBoard(board2, 9);
						System.out.println("GAME OVER!");
						key.close();
						return;
					}
					
					printBoard(board2, 9);
					
					
				}
				break;
				
			}
					

		}
		key.close();

	}



	public static boolean atLever(int x, int y, char[][]board)
	{
		if(board[x-1][y] == 'k' || board[x+1][y] == 'k' || board[x][y-1] == 'k' || board[x][y+1] == 'k')
			return true;
		else
			return false;
	}

	public static boolean adjacentEnemy(int x, int y, char[][]board, char enemy)
	{
		if(board[x][y] == hero && board[x+1][y] == enemy)
			return true;
		else if(board[x][y] == hero && board[x-1][y] == enemy)
			return true;
		else if(board[x][y] == hero && board[x][y+1] == enemy)
			return true;
		else if(board[x][y] == hero && board[x][y-1] == enemy)
			return true;
		else
			return false;
	}
	
	public static void guardMovement(char[][]board, int count) {
		int pos[]= getPos(board, 'G', 10);
		char moves[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
		checkDirection(moves[count], pos, board, 'G');
	}
	
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
	
	public static void ogreMovement(char[][]board2) {
		
		char move= randomDirection();		
		
		int pos[]= getPos(board2, 'O', 9);
		
		checkDirection(move, pos, board2, 'O');
		
		pos= getPos(board2, 'O', 9);
		
		if(hero!='K')
			board2[1][7]='k';
		
		clubSwing(pos, board2);
		
	}
	
	public static void clubSwing(int pos[], char[][]board2) {
		char move= randomDirection();
		int position[]= getPos(board2, '*', 9);
		
		switch(move) {
		
		case 'W':
			if(board2[pos[0]-1][pos[1]]==' ') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]-1][pos[1]]='*';
			}else if(board2[pos[0]-1][pos[1]]=='X' || board2[pos[0]-1][pos[1]]=='I') {
				clubSwing(pos, board2);
			}
			
			else if(board2[pos[0]-1][pos[1]]=='k') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]-1][pos[1]]='$';
			}
			break;
			
		case 'A':
			if(board2[pos[0]][pos[1]-1]==' ') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]][pos[1]-1]='*';
			}else if(board2[pos[0]][pos[1]-1]=='X' || board2[pos[0]][pos[1]-1]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos[0]][pos[1]-1]=='k') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]][pos[1]-1]='$';
			}
			break;
			
		case 'S':
			if(board2[pos[0]+1][pos[1]]==' ') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]+1][pos[1]]='*';
			}else if(board2[pos[0]+1][pos[1]]=='X' || board2[pos[0]+1][pos[1]]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos[0]+1][pos[1]]=='k') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]+1][pos[1]]='$';

			}
			break;
			
		case 'D':
			if(board2[pos[0]][pos[1]+1]==' ') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]][pos[1]+1]='*';
			}else if(board2[pos[0]][pos[1]+1]=='X' || board2[pos[0]][pos[1]+1]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos[0]][pos[1]+1]=='k') {
				board2[position[0]][position[1]]=' ';
				board2[pos[0]][pos[1]+1]='$';
			}
			break;
		}
	}



}
