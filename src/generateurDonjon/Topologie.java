package generateurDonjon;

import java.util.ArrayList;
import java.util.Random;

public class Topologie {
	
	private int tailleDonjon;
	private int nombreSalles;
	private static int tailleSalleMin =3;
	private ArrayList<Salle> salles = new ArrayList<Salle>(); //Toutes les salles sont stock�es dans cette liste
	
	public Topologie(int tailleDonjon){	
		
		this.tailleDonjon = tailleDonjon;
		this.nombreSalles = Math.round(tailleDonjon/5);
		int tailleSalleMax = Math.round(tailleDonjon/5);
	    
		//Chaque Salle a des attributs choisis al�atoirement, utilisation de la classe Random de Java
		
		Random rd = new Random(); //Car la m�thode souhait�e n'est pas statique
		
		
	    for (int i = 0; i < nombreSalles; i++) { 
	    	//On execute cette boucle jusqu'� avoir le nombre de salles souhait�
	        int w = tailleSalleMin + rd.nextInt(tailleSalleMax - tailleSalleMin +1);  
	        int h = tailleSalleMin + rd.nextInt(tailleSalleMax - tailleSalleMin +1);
	        int x = 1 + rd.nextInt(tailleDonjon - w +1);
	        int y = 1 + rd.nextInt(tailleDonjon - h +1);
	        
	        Salle newSalle = new Salle(x, y, w, h); //On cr�er une salle avec ces attributs al�atoires
	 
	        boolean failed = false; //Bool�en qui vaut false si la salle convient
	        
	        for (Salle autreSalle:salles) {
	            
	        	//Si la nouvelle salle en rencontre une autre, c'est rat�! On recommence!
	        	if (newSalle.intersects(autreSalle)) {
	                failed = true;
	                break;
	            }
	        }
	        if (!failed) {
	            salles.add(newSalle);
	        }
	        
	        /*
	         * 
	         * On va maintenant cr�er des couloirs pour connecter les salles entre elles.
	         * On prend une topologie simple: on connecte la salle i � la salle i+1 en cr�ant
	         * une salle verticale et une salle horizontale qui, mises bout � bout, connecte
	         * le centre de la salle i au centre de la salle i+1
	         * 
	         */
	        	        
	        if(salles.size() != 1){
	        	
	        	// On r�cup�re le centre de la nouvelle salle
		        int xc2 = newSalle.xc;
		        int yc2 = newSalle.yc;
		        
		        //On r�cup�re le centre de la salle d'avant:
		        
		        int j = 0;
		        //Si on a d�j� cr�� 2 couloirs:		        
		        if(salles.size() >= 4) j = salles.size() - 4;
		        int xc1 = salles.get(j).xc;
		        int yc1 = salles.get(j).yc;
		        
		        //On cr�e les attributs d'un couloir horizontal qui connecte xc1 � xc2:
		        int xh = 0;
		        int yh = 0;
	            if(xc1 < xc2){
	            	 xh = xc1;
	            	 yh = yc1;
	            }
	            else{
	            	xh = xc2;
	            	yh = yc2;
	            }
		        int wh = Math.abs(xc1 - xc2);
		        
		        //On cr�e une salle couloir horizontal et on l'ajoute � la liste:
		        Salle couloirHorizontal = new Salle(xh, yh, wh, 1);
		        salles.add(couloirHorizontal);
		        
		      //On cr�e les attributs d'un couloir vertical qui connecte yc1 � yc2:
		        
		      
	            int xv = xh+wh-1;
	            int yv = Math.min(yc1, yc2) ;	           
		        int hv = Math.abs(yc1 - yc2);
		        
		        //On cr�e une salle couloir horizontal et on l'ajoute � la liste:
		        Salle couloirVertical = new Salle(xv, yv, 1, hv);
		        salles.add(couloirVertical);
	        }
	        	        
	        
	    }
	    
	    
		
	}
	
	
	public boolean estSalle(int x, int y){
		
		//Cette fonction renvoie True si le point (x,y) est dans une des salles
		
		boolean ans = false;
		
		for(Salle salleTemp:salles){
			int x1 = salleTemp.x1;
			int x2 = salleTemp.x2;
			int y1 = salleTemp.y1;
			int y2 = salleTemp.y2;
			
			if(x1 <= x && x <= x2 && y1 <= y && y <= y2 ) ans = true;
			
		}
		
		return ans;
		
	}

}
