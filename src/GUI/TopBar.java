package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.Game;
import main.VisualGameObject;

public class TopBar extends Canvas implements VisualGameObject {
	
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
		Font font = new Font("Courier", Font.BOLD, 20); //Police
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("HP: " + String.valueOf(playerHP) + " / " + String.valueOf(playerHPMax) , 15, 20);
		g.setColor(Color.blue);
		g.drawString("Monstres: " + String.valueOf(mobsAlive) , Game.WIDTH / 3, 20);
	}

	
}
