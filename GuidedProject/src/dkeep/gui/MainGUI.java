package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainGUI {
	
	JFrame Menu;
	
	public MainGUI() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI mainMenu = new MainGUI();
					mainMenu.Menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize() {

		
		Menu = new JFrame("MAZE RUNNER");
		Menu.setResizable(false);
		Menu.setBounds(180, 180, 550, 400);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu.getContentPane().setLayout(null);
		Menu.setBackground(Color.LIGHT_GRAY);
		
		try {
            Menu.setContentPane(new JLabel((Icon) new ImageIcon(ImageIO.read(new File("images/mazeRunner.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		exitBut();
		
		
		JButton btnStartGame = new JButton("New Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnStartGame.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnStartGame.setBounds(400, 77, 130, 29);
		Menu.getContentPane().add(btnStartGame);	
		
		JButton btnCreateNewMap = new JButton("Create New Maze");
		btnCreateNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ConstructGameGUI newGame = new ConstructGameGUI();
					newGame.setVisible(true);
			}
		});
		btnCreateNewMap.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnCreateNewMap.setBounds(400, 129, 130, 29);
		Menu.getContentPane().add(btnCreateNewMap);	
		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: complete this
			}
		});
		btnSaveGame.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnSaveGame.setBounds(400, 182, 130, 29);
		Menu.getContentPane().add(btnSaveGame);	
	}

	public void exitBut() {
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnExit.setBounds(400, 235, 130, 29);
		Menu.getContentPane().add(btnExit);
	}

	
}
