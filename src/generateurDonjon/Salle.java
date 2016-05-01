package generateurDonjon;

public class Salle {

	private int x1, y1, x2, y2;  //Extremités de la salle
	private int w, h; //Dimensions de la salle
	private int xc, yc; //Centre de la pièce (utile pour les couloirs):
	
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
	
	
	public boolean intersects(Salle autreSalle){ //Renvoie True si cette Salle en rencontre une autre
        
		return (x1 <= autreSalle.x2 && x2 >= autreSalle.x1 &&
            y1 <= autreSalle.y2 && y2 >= autreSalle.y1);
    }
	
	public int getX1(){
		return this.x1;
	}
	
	public int getX2(){
		return this.x2;
	}
	
	public int getY1(){
		return this.y1;
	}
	
	public int getY2(){
		return this.y2;
	}
	
	public int getXC(){
		return this.xc;
	}
	
	public int getYC(){
		return this.yc;
	}
}


