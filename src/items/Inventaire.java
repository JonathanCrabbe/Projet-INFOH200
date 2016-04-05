package items;

import java.util.ArrayList;

import acteurs.Personnage;
import main.Game;

public class Inventaire {
	private Game game;
	private Personnage porteur; //Personnage qui porte cet inventaire
	private int capacite;  
	private ArrayList<Item> items = new ArrayList<Item>(); //Liste d'items dans l'inventaire
	
	public Inventaire(Game game, Personnage porteur, int capacite){
		this.game = game;
		this.porteur = porteur;
		this.capacite = capacite;
	}
	
	//Ajoute item à l'inventaire
	public void add(Item item){
		
		//Il ne faut pas que la taille maximale soit surpassée!
		
		items.add(item);
		if(this.items.size() > capacite){
			items.remove(item);
		}
	}
	
	public void useItem(int i){
		if(items.size() != 0){
			Item item = this.items.get(i);
			item.useOn(this.porteur);
			this.items.remove(item);
		}
	}

}
