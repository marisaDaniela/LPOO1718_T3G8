package dungeon;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	static int flag=0;

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


	public static int[] getPos(char[][] board, char mover){
		int[] pos = new int[2];
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
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
			}

		}
		if(input == 'S' || input == 's')
		{
			if(board[x+1][y]==' '){
				board[x+1][y]=mover;
				board[x][y]=' ';
			}
		
		}
		if(input == 'W' || input == 'w')
		{
			if(board[x-1][y]==' '){
				board[x-1][y]=mover;
				board[x][y]=' ';
			}

		}
		if(input == 'D' || input == 'd')
		{
			if(board[x][y+1]==' '){
				board[x][y+1]=mover;
				board[x][y]=' ';
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
		// TODO: lever esta a desaparecer, why? 
		// movimentos do guarda
		
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

			if(key2 == 'q' || key2 == 'Q') // Para sair TODO: ver porque esta a dar erro ao pressionar esta tecla
				break;


			int pos[] = getPos(board, 'H');
			
			checkDirection(key2, pos, board, 'H');
			
			pos = getPos(board, 'H');
			
			guardMovement(board, count);
			
			if(adjacentGuard(pos[0], pos[1], board))
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
					ogreMovement(board2); 
					printBoard(board2, 9);
				}
				
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

	public static boolean adjacentGuard(int x, int y, char[][]board)
	{
		if(board[x][y] == 'H' && board[x+1][y] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x-1][y] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x][y+1] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x][y-1] == 'G')
			return true;
		else
			return false;
	}
	
	public static void guardMovement(char[][]board, int count) {
		int pos[]= getPos(board, 'G');
		char moves[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
		checkDirection(moves[count], pos, board, 'G');
	}
	
	public static void ogreMovement(char[][]board2) {
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
		
		int pos[]= getPos(board2, 'O');
		
		checkDirection(move, pos, board2, 'O');
		
	}



}
