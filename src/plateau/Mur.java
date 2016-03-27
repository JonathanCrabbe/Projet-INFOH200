package plateau;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mur extends Case {

	public Mur(int x, int y) {
		super(x, y, 1);
		try {
			this.image = ImageIO.read(new File("Images/Mur.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}