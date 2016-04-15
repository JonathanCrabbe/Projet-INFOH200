package inventaire;

import java.awt.Graphics;
import java.util.ArrayList;

import acteurs.Joueur;
import acteurs.Personnage;
import items.Item;
import main.Game;
import main.VisualGameObject;

public  class Inventaire implements VisualGameObject {
	protected Game game;
	protected Personnage porteur; //Personnage qui porte cet inventaire
	private int capacite;  
	protected ArrayList<Slot> slots; //Liste d'items dans l'inventaire
	
	private final int dim = Joueur.FOV;
	private boolean isVisible;
	
	public Inventaire(Game game, Personnage porteur, int capacite){
		this.game = game;
		this.porteur = porteur;
		this.capacite = capacite;
		this.isVisible = false;
		slots = new ArrayList<Slot>();
		for(int i = 0; i < capacite; i++){
			
			//Position dans une grille dim*dim:
			int x = Math.floorMod(i, dim);
			int y = Math.floorDiv(i, dim);
			
			//Différencier un joueur d'un monstre:
			
			if(porteur.getEstJoueur()){
				slots.add(new SlotJoueur(x,y, game, this));
			}
			else{
				slots.add(new SlotButin(x,y,game,this));
			}
			
		}
		
	}
	
	
	
	public void tick() {
		
		
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
		Item itemTemp = this.slots.get(i).getItem();
		if(itemTemp != null){
			itemTemp.useOn(porteur);
		}
	}
	
	//Surcharge de méthode:
	public void useItem(int x, int y){
		Item itemTemp = getSlot(x, y).getItem();
		if(itemTemp != null){
			itemTemp.useOn(porteur);
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
	
	public Slot getSlot(int x, int y){
		Slot slot = null;
		ArrayList<Slot> slots = getSlots();
		for(Slot slotTemp : slots){
			if(slotTemp.getXGrid() == x && slotTemp.getYGrid() == y){
				slot = slotTemp;
			}
		}
		return slot;
	}
	
	

	/*
	 * Setters:
	 */
	
	public void changeVisible(){
		
		this.isVisible = !isVisible;
	}
	
	public void setPorteur(Personnage porteur){
		this.porteur = porteur;
	}

	

}
