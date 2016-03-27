package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import acteurs.Personnage;



public class Window extends Canvas {

	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		
		// Taille de la fen�tre:
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// Actions sur celle-ci:
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// Disposition de la fen�tre: 
		
		
		frame.add(game);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
