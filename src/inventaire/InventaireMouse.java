package inventaire;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InventaireMouse implements MouseListener {
	
	public static boolean isLeftClicked;
	public static boolean isRightClicked;
	private Inventaire inventaire;
	
	/*
	 * Classe qui gère les clics sur l'inventaire
	 */
	
	public InventaireMouse(Inventaire inventaire){
		this.inventaire = inventaire;
	}
	
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){ //Clic gauche
			isLeftClicked = true;
			isRightClicked = false;
		}
		
		if(e.getButton() == MouseEvent.BUTTON3){ //Clic droit
			isLeftClicked = false;
			isRightClicked = true;
		}
		Point mousePoint = e.getPoint();
		for(int i = 0; i < inventaire.getCapacite(); i++){
			Slot slotTemp = inventaire.getSlots().get(i);
			
			/*
			 * Vérifier que la souris a cliqué dans le slot 
			 * et que l'inventaire est visible.
			 * Si oui, le clic gauche utilise et le clic
			 * droit détruit.
			 */
			if(slotTemp.contains(mousePoint) && inventaire.getIsVisible()){
				if(isLeftClicked){
					inventaire.useItem(i);
				}
				else if(isRightClicked){
					inventaire.delItem(i);
				}
			}
		}
	
		
	}

	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){ //Clic gauche
			isLeftClicked = false;
		
		}
		
		if(e.getButton() == MouseEvent.BUTTON3){ //Clic droit
			isRightClicked = false;
		}
		
	}
		
	

	
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	

}
