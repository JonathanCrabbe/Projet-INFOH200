package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import GUI.TopBar;
import acteurs.*;
import fenetreOptions.OptionDialog;
import inventaire.Inventaire;
import inventaire.InventaireMouse;
import plateau.Case;
import plateau.Plateau;


public class Game extends Canvas implements Runnable, Serializable{

	private static final long serialVersionUID = 314;
	
	private transient Thread thread;
	private boolean running = false;
	private FenetrePrincipale fenetreJeu;
	
	public static final int WIDTH = Case.dim * (2*Joueur.FOV+1);
	public static final int HEIGHT = Case.dim * (2*Joueur.FOV+1);
	
	
	private Population population;
	private Plateau plateau;
	private TopBar topbar;
	
	private  int taillePlateau = 100;
	private final int nombreMonstres = 20;
	
	private String classeJoueur;
	private String nomJoueur;
	
	private int score = 0;

	
	
	public Game(){
		
		
			
	}
	
	public void newGame(){
		plateau = new Plateau(taillePlateau, this);
		population = new Population(nombreMonstres,this);
		topbar = new TopBar(this);
		
		this.addKeyListener(new KeyInput(this, population));
		fenetreJeu = new FenetrePrincipale(WIDTH, HEIGHT, "Rogue Heritage", this);
	}
	
	public void loadGame(){
		
		/*
		 * Listeners:
		 */
		this.addKeyListener(new KeyInput(this, population));
		for(Personnage persoTemp:population.getPersonnages()){
			Inventaire inventaireTemp = persoTemp.getInventaire();
			this.addMouseListener(new InventaireMouse(inventaireTemp));
		}
		for(Case caseTemp:plateau.getDalles()){
			Inventaire inventaireTemp = caseTemp.getButin();
			if(inventaireTemp != null) this.addMouseListener(new InventaireMouse(inventaireTemp));
		}
		fenetreJeu = new FenetrePrincipale(WIDTH, HEIGHT, "Rogue Heritage", this);
	}

	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop(){
		
		try{
			
			
			running = false;
			
			this.saveScore("Scores.csv");
			String bestScore = this.readBestScore("Scores.csv");
			
			System.out.println("Coucou");
			BufferStrategy bs = this.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.BOLD, 30));
			g.drawString("Game Over", WIDTH/3, HEIGHT/3);
			g.setFont(new Font("Courier", Font.BOLD, 20));
			g.drawString("Votre score: " + score, WIDTH/3, HEIGHT/2);
			g.drawString("Record: " + bestScore, WIDTH/3, 2*HEIGHT/3);
			g.dispose();
			bs.show();
			thread.join();
		
				
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
		double ns = 1000000000/ amountOfTicks; //Période (ns)
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
	
	private synchronized  void tick(){
		//Actualisation population et plateau et GUI:
		population.tick();
		topbar.tick();
	}
	
	private synchronized void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		Personnage player = population.getJoueur();
		plateau.renderLocal(g, player.getX(), player.getY());
		topbar.render(g);
		population.render(g);	
		player.getInventaire().render(g);
		
		//Affichage du contenu des cases loot:
		for(Case caseTemp:plateau.getDalles()){
			Inventaire butin = caseTemp.getButin();
			if(butin != null && player.getCase() == caseTemp){
				butin.setVisible(true);
				butin.render(g);	
			}
			else if(butin != null && butin.getIsVisible()){
				//Si le joueur n'est pas sur la case:	
				butin.setVisible(false);
			}
		}
		
		
		g.dispose();
		if(running) bs.show();  //Latence entre l'appel de render et actualisation de running
	}
	
	
	public void relaunch(){
		/*
		 * Recréer une map et des ennemis
		 */
		thread.interrupt();
		plateau = new Plateau(taillePlateau, this);		
		this.population.relaunch();
			
	}
	
	
	
	/*
	 * Getters
	 */
	
	public Population getPopulation(){
		return this.population;
	}	
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getClasseJoueur(){
		return this.classeJoueur;
	}
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	/*
	 * Setters:
	 */
	
	public void setTaillePlateau(int taillePlateau){
		if(taillePlateau >= 20){
			this.taillePlateau = taillePlateau;
		}
	}
	
	public void setScore(int score){
		if(score > this.score){
			this.score = score;
		}
	}
	
	public void setClasseJoueur(String classeJoueur){
		this.classeJoueur = classeJoueur;
		
	}
	
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	
	/*
	 * Persistance:
	 */
	
	public void save(Game game, String fileName){
		try
	      {
	         FileOutputStream fileOut =  new FileOutputStream(fileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(game);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public Game load(String fileName){
		Game game = new Game();
		 try
	      {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         game = (Game) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();  
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Game class not found");
	         c.printStackTrace();      
	      }
		 return game;
	}
	
	public void saveScore(String fileName){
		
		try {
			
			BufferedWriter file = new BufferedWriter(
					new FileWriter(fileName, true));
			String data = nomJoueur + ": ";
			data += String.valueOf(score) + "\n";
			file.append(data);
			file.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readBestScore(String fileName){
		
		String ans = new String();
		try {
			
			BufferedReader file = new BufferedReader(
					new FileReader(fileName));
			
			String line = file.readLine();
			String dat = line.replace("\n", "");
			ans = dat;
			while(line != null){
				
				//Vérification de la supériorité de l'entrée testée:
				dat = line.replace("\n", "");
				String[] values = dat.split(": ");
				if(Integer.valueOf(ans.split(": ")[1]) < 
						Integer.valueOf(values[1])){
					ans = dat;
				}
				line = file.readLine();
			}
			
			file.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ans;
	}

	

	
	
}