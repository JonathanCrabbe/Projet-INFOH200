package generateurDonjon;

public class Salle {
	
	//Extremités de la salle (rectangulaire) 1 = supérieur gauche et 2 = inférieur droit:
	
	protected int x1;  
	protected int y1;
	protected int x2;
	protected int y2;
	
	//Dimensions de la salle:
	
	protected int w; 
	protected int h;
	
	//Centre de la pièce (utile pour les couloirs):
	
	protected int xc, yc;
	
	//Constructeur
	public Salle(int x, int y, int w, int h) {        
        this.x1 = x;
        this.x2 = x + w;
        this.y1 = y;
        this.y2 = y + h;
        this.w = w;
        this.h = h;
        this.xc = Math.round((x1 + x2) / 2);
        this.yc = Math.round((y1 + y2) / 2);
    }
	
	//Renvoie True si cette Salle en rencontre une autre
	public boolean intersects(Salle autreSalle){
        
		return (x1 <= autreSalle.x2 && x2 >= autreSalle.x1 &&
            y1 <= autreSalle.y2 && y2 >= autreSalle.y1);
    }
}


