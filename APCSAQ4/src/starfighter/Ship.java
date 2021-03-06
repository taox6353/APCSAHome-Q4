package starfighter;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;
	private Image puimage;

	public Ship()
	{
		this(0,0,2);
	}

	public Ship(int x, int y)
	{
		this(x,y,2);
	}

	public Ship(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		try
		{
			image = ImageIO.read(new File("src/starfighter/ship.jpg"));
			puimage = ImageIO.read(new File("src/starfighter/shieldship.jpg"));
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println("File could not be found or read. ");
		}
	}
//	public Ship(int x, int y, int s)
//	{
//		super(x, y);
//		speed=s;
//		try
//		{
//			image = ImageIO.read(new File("src/starfighter/ship.jpg"));
//		}
//		catch(Exception e)
//		{
//			//feel free to do something here
//			System.out.println("File could not be found or read. ");
//		}
//	}


	public void setSpeed(int s)
	{
	   //add more code
		speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),80,80,null);
	}
	public void drawPU(Graphics window)
	{
		window.drawImage(puimage,getX(),getY(),80,80,null);
	}

	public String toString()
	{
		return "Ship" + " " + getX() + " " + getY() + " " + getSpeed();
	}
}
