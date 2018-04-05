package dkeep.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dkeep.logic.Coordinates;
import dkeep.logic.GameState;
import dkeep.logic.Level;

@SuppressWarnings("serial")
public class GameGraphs extends JPanel implements KeyListener {

	private static final int HEIGHT = 660;
	private static final int WIDTH = 640;
	private static final int LAST_Y = 640;
	private static final int LAST_X = 620;
	private static final int INIT_CORD = 20;
	
	private final int boardSizeInFrame = 600;
	
	Level game;
	private JFrame gameKeep;
	private String gameString;
	private JTextArea gameArea;
	
	private int sizeToDraw;
	private Coordinates cor = new Coordinates(); 

	/**
	 * Create the application.
	 */
	public GameGraphs(Level game,JTextArea gameArea) {
		
		setBounds(INIT_CORD, INIT_CORD, LAST_X, LAST_Y);
		
		this.gameArea = gameArea;
		this.game = game;
		sizeToDraw = (int) ((double)boardSizeInFrame)/game.getMap().length;
		addKeyListener(this);//
		
		gameKeep = new JFrame("Maze Game");
		gameKeep.setResizable(false);
		gameKeep.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		gameKeep.setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		gameKeep.getContentPane().setLayout(null);
		gameKeep.getContentPane().add(this);
        gameKeep.pack();
		gameKeep.setVisible(true);
		
		setLayout(null);
		requestFocus();
		repaint();
	}
	
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		
		GameState g1 = new GameState();
		
		
		g1.printBoard(game.getMap(), game.getMap().length);
		
		for (int i = 0; i < gameString.length(); i++) {
			if (gameString.charAt(i) == '\n') {
				cor.setX(0);
				cor.setY(cor.getY()+sizeToDraw) ;
			} else {
				cor.setX(cor.getX()+sizeToDraw) ;
			}
		}
		cor.setX(0);
		cor.setY(0);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	}
	
	public void buttonPressed() {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
