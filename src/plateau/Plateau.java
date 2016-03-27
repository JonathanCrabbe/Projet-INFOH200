package plateau;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Image;

import javax.swing.JPanel;

import acteurs.Personnage;
import generateurDonjon.Topologie;

public class Plateau extends JPanel{
	
	private int size;
	private ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
	
	public Plateau(int size){
		this.size = size;
		this.setFocusable(true);
		this.requestFocusInWindow();
		Topologie topologie = new Topologie(size);
		for(int i = 0; i < size ; i++){
			grille.add(new ArrayList<Case>());
			for(int j = 0; j < size; j++){
				if(topologie.estSalle(i, j)){			
					addCase(new Dalle(i,j), i, j);
				}
				else{
					addCase(new Mur(i,j),i,j);
				}
			}
		}		
	}
	
	public void tick(){
		for(int i = 0; i < size ; i++){
			for(int j = 0; j < size; j++){
				Case caseTemp = grille.get(i).get(j);
				caseTemp.tick();
			}
		}			
	}
		
	
	
	public void render(Graphics g) { 
		if(grille == null){
		}else{
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					Case caseTemp = grille.get(i).get(j);
					int dim = Case.dim;
					g.drawImage(caseTemp.image, dim*i, dim*j, 20, 20, null);
				}
				
			}
		}		
     }
	
	public void addCase(Case c, int x, int y){
		this.grille.get(x).add(y, c);
	}

}
	
