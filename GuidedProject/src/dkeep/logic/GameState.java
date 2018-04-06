package dkeep.logic;

/**
 * GameState.java - a class for game states 
 */
public class GameState {

	/**
	 * printBoard - a method to print the board to game
	 * @param board current board
	 * @param size size of board
	 */
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
	
}
