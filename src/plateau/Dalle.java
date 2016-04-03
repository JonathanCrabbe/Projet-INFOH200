package plateau;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.VisualGameObject;

public class Dalle extends Case implements VisualGameObject {

	public Dalle(int x, int y) {
		super(x, y, 0);
		try {
			this.image = ImageIO.read(new File("Images/dalle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
