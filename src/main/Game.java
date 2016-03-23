package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import acteurs.*;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -6112428091888191314L;
	
	private Thread thread;
	private boolean running = false;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH/12*9;
	
	private Population population;

	
	
	public Game(){
		
		population = new Population();
		this.addKeyListener(new KeyInput(population));
		
		new Window(WIDTH, HEIGHT, "Rogue Heritage", this);
		
		//Construction d'une population:		
		population.addPersonnage(new Joueur(100,100, 20));
		population.addPersonnage(new Monstre(200,200, 0));
		population.addPersonnage(new Allié(300,300, 0));
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
			Récupérée sur: https://www.youtube.com/watch?v=1gir2R7G9ws 
		
		
		*/
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis()- timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		population.tick();
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		population.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String args[]){
			new Game();
		
	}
}