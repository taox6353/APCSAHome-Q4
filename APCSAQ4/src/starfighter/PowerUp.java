package starfighter;

import java.awt.Graphics;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


import javax.imageio.ImageIO;

public class PowerUp extends MovingThing {
	
	static int rX = (int)(Math.random()*700);
	static int rY = (int)(Math.random()*200+300);
	Image image;
	
	public PowerUp(){
		super(rX,rY);
		try
		{
			image = ImageIO.read(new File("src/starfighter/pu.jpg"));
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println("File could not be found or read. ");
		}
	}

	public void setSpeed(int s) {
		// TODO Auto-generated method stub
	}

	public int getSpeed() {
		return 0;
	}

	public void draw(Graphics window) {
		window.drawImage(image,getX(),getY(),40,40,null);
		
	}
}
