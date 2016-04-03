package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import GUI.GUI;
import acteurs.Personnage;



public class FenetrePrincipale extends Canvas {

	private static final long serialVersionUID = -8255319694373975038L;
	
	public FenetrePrincipale(int width, int height, String title, Game game){
		
		JFrame frame = new JFrame(title);
		
		// Taille de la fen�tre:
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// Actions sur celle-ci:
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// Cr�ation du GUI:
		
		GUI topBar = new GUI(game);
		
		
		
		frame.add(topBar);
		frame.add(game);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
