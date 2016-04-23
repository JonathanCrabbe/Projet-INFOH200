package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import GUI.TopBar;
import acteurs.*;
import fenetreOptions.OptionDialog;
import fenetreOptions.OptionsDemarrage;
import inventaire.Inventaire;
import inventaire.InventaireMouse;
import plateau.Case;
import plateau.Plateau;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -6112428091888191314L;
	
	private Thread thread;
	private boolean running = false;
	
	public static final int WIDTH = Case.dim * (2*Joueur.FOV+1);
	public static final int HEIGHT = Case.dim * (2*Joueur.FOV+1);
	
	private Population population;
	private Plateau plateau;
	private TopBar gui;
	
	private  int taillePlateau = 100;
	private final int nombreMonstres = 20;

	
	
	public Game(){
		
		
		OptionDialog fenetreOptions = new OptionDialog(this,null, "Options de jeu", true);
		
		
		plateau = new Plateau(taillePlateau, this);
		population = new Population(nombreMonstres,this);
		gui = new TopBar(this);
		
		
		this.addKeyListener(new KeyInput(population));
		
		//Tous les inventaires doivent pouvoir �tre �cout�s:
		for(Personnage persoTemp:population.getPersonnages()){
			Inventaire inventaireTemp = persoTemp.getInventaire();
			this.addMouseListener(new InventaireMouse(inventaireTemp));
		}
		
		
		
		new FenetrePrincipale(WIDTH, HEIGHT, "Rogue Heritage", this);	
		
	}

	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop(){
		try{		
		thread.join();
		running = false;
				
		}catch(Exception e){
			e.printStackTrace();  //Affiche erreur dans la console
		}
		
	}
	
	public void run(){
		
		/* 
			
			Game loop pour r�guler l'ex�cution des t�ches.
			Inspir�e de: http://www.java-gaming.org/index.php?topic=24220.0
		
		
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
	
	private synchronized void tick(){
		//Actualisation population et plateau et GUI:
		population.tick();
		plateau.tick();	
		gui.tick();
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
		
		Personnage player = population.getPerso(0);
		plateau.renderLocal(g, player.getX(), player.getY());
		population.render(g);
		gui.render(g);
		player.getInventaire().render(g);
		
		//Affichage du contenu des cases loot:
		for(Case caseTemp:plateau.getDalles()){
			Inventaire butin = caseTemp.getButin();
			if(butin != null && player.getCase() == caseTemp){
				butin.render(g);
			}
		}
		
		
		g.dispose();
		bs.show();
	}
	
	//Getters
	
	public Population getPopulation(){
		return this.population;
	}	
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	
	/*
	 * Setters:
	 */
	
	public void setTaillePlateau(int taillePlateau){
		if(taillePlateau > 50){
			this.taillePlateau = taillePlateau;
		}
	}
	
	
}