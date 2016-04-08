package inventaire;

import java.awt.Graphics;
import java.util.ArrayList;

import acteurs.Joueur;
import acteurs.Personnage;
import items.Item;
import main.Game;
import main.VisualGameObject;

public class Inventaire implements VisualGameObject {
	private Game game;
	private Personnage porteur; //Personnage qui porte cet inventaire
	private int capacite;  
	private ArrayList<Slot> slots; //Liste d'items dans l'inventaire
	
	private final int dim = Joueur.FOV;
	private boolean isVisible;
	
	public Inventaire(Game game, Personnage porteur, int capacite){
		this.game = game;
		this.porteur = porteur;
		this.capacite = capacite;
		this.isVisible = false;
		slots = new ArrayList<Slot>();
		for(int i = 0; i < capacite; i++){
			
			int x = Math.floorMod(i, dim);
			int y = Math.floorDiv(i, dim);
			slots.add(new Slot(x,y, game ));
		}
		
	}
	
	
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {
		if(isVisible){
			for(Slot slotTemp:slots){
				slotTemp.render(g);
			}
		}
		
		
	}
	
	
	
	//Ajoute item à l'inventaire
	public void add(Item item){
		
		//Il ne faut pas que la taille maximale soit surpassée!
		
		boolean itemAdded = false;
		for(Slot slotTemp:slots){
			if(slotTemp.isEmpty()){
				slotTemp.setItem(item);
				itemAdded = true;
				break;
			}
		}
		
	}
	
	public void useItem(int i){
		
		Item item = this.slots.get(i).getItem();
		if(item != null){
			item.useOn(this.porteur);
			delItem(i);
		}
	}
	
	public void delItem(int i){
		this.slots.get(i).setItem(null);
	}
	
	
	/*
	 * Getters:
	 */
	
	public ArrayList<Slot> getSlots(){
		return this.slots;
	}
	
	public boolean getIsVisible(){
		return this.isVisible;
	}
	
	public int getCapacite(){
		return this.capacite;
	}
	
	

	/*
	 * Setters:
	 */
	
	public void changeVisible(){
		this.isVisible = !isVisible;
	}

	

}
