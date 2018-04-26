package painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Pixel {
	public Pixel(){
		
	}
	public Pixel(int x,int y){
		window.setColor(Color.BLACK);
		window.fillRect(getX(), getY(), 5, 5);
	}
}
