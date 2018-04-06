package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.cli.Utils;
import dkeep.logic.Coordinates;
import dkeep.logic.Level;
import dkeep.logic.Level1;

public class StartGameGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	Level game;
	char key = '1';

	JFrame Game;
	private JTextField numberOgres;
	private boolean manualMaze = false;
	private char[][] manualMap = null;
	private JTextArea gameArea;
	JButton btnLeft, btnRight, btnUp,btnDown;

	Coordinates hero = new Coordinates();
	ArrayList<Coordinates> ogres;

	/**
	 * Launch the application.
	 */
	public StartGameGUI() {
		initialize();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGameGUI mainMenu = new StartGameGUI();
					mainMenu.Game.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartGameGUI(boolean type, char[][] map,ArrayList<Coordinates> ogres, Coordinates hero) {
		manualMaze = type;
		manualMap = map;
		this.ogres = ogres;
		this.hero = hero;
		initialize();
	}


	public void close() {
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);	

	}

	/**
	 * Create the application.
	 */
	public void initialize() {
		Game = new JFrame();
		gameInit();
		numberOgres();
		labels();
		exitBut();
		@SuppressWarnings("unused")
		JComboBox<String> guardPersonality = guardPersonalities();	

		gameArea = new JTextArea();
		gameArea.setWrapStyleWord(true);
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.setBounds(30, 113, 200, 170);
		Game.getContentPane().add(gameArea);

		JLabel gameStatus = new JLabel("");
		gameStatus.setBounds(16, 295, 419, 16);
		gameStatus.setForeground(Color.BLUE);
		Game.getContentPane().add(gameStatus);

		gameButtonLeft(gameArea, gameStatus);
		gameButtonDown(gameArea, gameStatus);
		gameButtonUp(gameArea, gameStatus);
		gameButtonRight(gameArea, gameStatus);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manualMaze == true)
					setManualMaze();
				else if ((new Utils()).isNumeric(numberOgres.getText())) {
					int numb = Integer.parseInt(numberOgres.getText());
					if (numb <= 0)
						System.out.println("Number Of Ogres minor or equal to 0!");
					game = new Level1();
					initGame(gameArea);
				} else
					System.out.println("Number Of Ogres is not a number!");	
			}});
		btnNewGame.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnNewGame.setBounds(289, 107, 117, 29);
		Game.getContentPane().add(btnNewGame);	
	}

	public JComboBox<String> guardPersonalities() {
		String[] guardPersonalities = {"Rookie", "Drunken", "Suspicious"};

		JComboBox<String> guardPersonality = new JComboBox<String>(guardPersonalities);
		guardPersonality.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		guardPersonality.setBounds(141, 57, 169, 27);
		Game.getContentPane().add(guardPersonality);
		return guardPersonality;
	}

	public void labels() {
		JLabel labelNumberOgres = new JLabel("Number of Ogres");
		labelNumberOgres.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		labelNumberOgres.setBounds(16, 30, 114, 16);
		Game.getContentPane().add(labelNumberOgres);

		JLabel labelGuardPers = new JLabel("Guard Personality");
		labelGuardPers.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		labelGuardPers.setBounds(16, 62, 114, 16);
		Game.getContentPane().add(labelGuardPers);
	}

	public void numberOgres() {
		numberOgres = new JTextField();
		numberOgres.setBounds(141, 24, 70, 26);
		numberOgres.setHorizontalAlignment(SwingConstants.LEFT);
		Game.getContentPane().add(numberOgres);
		numberOgres.setColumns(1);
	}

	public void initGame(JTextArea gameArea) {
		(new Utils()).printGameArea(gameArea,game);
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
	}

	private void setManualMaze() {
		game = new Level1();			
		cleanManualMap();
		game.getMap();
		initGame(gameArea);
	}

	public void exitBut() {
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.setVisible(false);
			}
		});
		btnExit.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnExit.setBounds(289, 265, 117, 29);
		Game.getContentPane().add(btnExit);
	}

	public void cleanManualMap() {
		for (int i = 0; i < manualMap.length; i++) {
			for (int j = 0; j < manualMap.length; j++) {
				if (manualMap[i][j] == 'H' || manualMap[i][j] == 'O')
					manualMap[i][j] = ' ';
			}
		}
	}

	public void gameButtonRight(JTextArea gameArea, JLabel gameStatus) {//
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnRight.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnRight.setBounds(351, 192, 70, 29);
		Game.getContentPane().add(btnRight);
	}

	public void gameButtonUp(JTextArea gameArea, JLabel gameStatus) {
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUp.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnUp.setBounds(311, 161, 70, 29);
		Game.getContentPane().add(btnUp);
	}

	public void gameButtonDown(JTextArea gameArea, JLabel gameStatus) {
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDown.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnDown.setBounds(311, 224, 70, 29);
		Game.getContentPane().add(btnDown);
	}

	public void gameButtonLeft(JTextArea gameArea, JLabel gameStatus) {
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLeft.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnLeft.setBounds(266, 192, 70, 29);
		Game.getContentPane().add(btnLeft);
	}

	public void gameInit() {
		Game.setResizable(false);
		Game.setBounds(4, 100, 458, 346);
		Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.getContentPane().setLayout(null);

		Game.setBackground(Color.LIGHT_GRAY);

		try {
			Game.setContentPane(new JLabel((Icon) new ImageIcon(ImageIO.read(new File("images/mazeRunner.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}