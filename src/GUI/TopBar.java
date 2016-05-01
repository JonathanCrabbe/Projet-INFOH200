package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

import main.Game;
import main.VisualGameObject;

public class TopBar extends Canvas implements VisualGameObject, Serializable {
	
	private Game game;
	private int playerHP;
	private int playerHPMax;
	private int mobsAlive;
	
	
	public TopBar(Game game){
		this.game = game;
		this.playerHPMax = game.getPopulation().getJoueur().getHPMax();
	}
	
	public void tick(){
		this.playerHP = this.game.getPopulation().getJoueur().getHP();
		this.mobsAlive = this.game.getPopulation().getSize() -1 ;
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT/20);
		Font font = new Font("Courier", Font.BOLD, 15); //Police
		g.setFont(font);
		
		//HP du joueur:
		g.setColor(Color.red);
		g.drawString("HP: " + String.valueOf(playerHP) + " / " + String.valueOf(playerHPMax) , 15, 20); 
		
		//Compteur de monstres:
		g.setColor(Color.blue);
		g.drawString("Monstres: " + String.valueOf(mobsAlive) , 5*Game.WIDTH / 10, 20);  
		
		//Score:
		g.setColor(Color.green);
		g.drawString("Score: " + String.valueOf(game.getScore()) , 8*Game.WIDTH / 10, 20);  
	}

	
}
