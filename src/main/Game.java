package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import acteurs.*;
import plateau.Plateau;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -6112428091888191314L;
	
	private Thread thread;
	private boolean running = false;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH/12*9;
	
	private Population population;
	private Plateau plateau;

	
	
	public Game(){
		
		population = new Population();
		plateau = new Plateau(40);
		this.addKeyListener(new KeyInput(population));
		
		new Window(WIDTH, HEIGHT, "Rogue Heritage", this);
		
		//Construction d'une population:		
		population.addPersonnage(new Joueur(100,100, 20));
		population.addPersonnage(new Monstre(200,200, 0));
		population.addPersonnage(new Allié(300,300, 0));
		
		//Construction de la map:
	}

	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop(){
		try{
			// Tue le thread
			thread.join();
			running = false;
			
		}catch(Exception e){
			e.printStackTrace();  //Affiche erreur dans la console
		}
		
	}
	
	public void run(){
		
		/* 
			
			Game loop pour réguler l'exécution des tâches.
			Inspirée de: http://www.java-gaming.org/index.php?topic=24220.0
		
		
		*/
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 5.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			if(running){
				render();
				frames++;
			}
			
			
			if(System.currentTimeMillis()- timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		population.tick();
		plateau.tick();
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		plateau.render(g);
		population.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	public Population getPopulation(){
		return this.population;
	}
	
	public static void main(String args[]){
			new Game();
		
	}
}