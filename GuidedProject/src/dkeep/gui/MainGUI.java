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
		
		
		JButton btnStartGame = new JButton("PLAY");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGameGUI newGame = new StartGameGUI();
				newGame.Game.setVisible(true);
	
			}
		});
		btnStartGame.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnStartGame.setForeground(Color.BLUE);
		btnStartGame.setBounds(400, 100, 130, 50);
		Menu.getContentPane().add(btnStartGame);	
		
		JButton btnCreateNewMap = new JButton("CREATE MAZE");
		btnCreateNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ConstructGameGUI newGame = new ConstructGameGUI();
					newGame.setVisible(true);
			}
		});
		btnCreateNewMap.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnCreateNewMap.setBounds(400, 152, 130, 50);
		btnCreateNewMap.setForeground(Color.BLUE);
		Menu.getContentPane().add(btnCreateNewMap);	
	}

	public void exitBut() {
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		btnExit.setBounds(400, 204, 130, 50);
		btnExit.setForeground(Color.BLUE);
		Menu.getContentPane().add(btnExit);
	}

	
}
