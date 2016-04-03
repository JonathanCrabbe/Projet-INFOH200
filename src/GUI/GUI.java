package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.Game;
import main.VisualGameObject;

public class GUI extends Canvas{
	
	private Game game;
	
	
	public GUI(Game game){
		this.game = game;
	}

	
}
