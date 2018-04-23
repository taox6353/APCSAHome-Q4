package starfighter;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
	private int speed;
	private Image image;

	public Alien()
	{
		this(0,0,2);
	}

	public Alien(int x, int y)
	{
		this(x,y,2);
	}

	public Alien(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		try
		{
			image = ImageIO.read(new File("src/starfighter/alien.jpg"));
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println("Image not found. ");
		}
	}

	public void setSpeed(int s)
	{
	   //add code
		speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),80,80,null);
		
		if(speed>0&&getX()<700&&getX()>10){
			setSpeed(3);
			move("RIGHT");
		}
		else if(speed<0&&getX()<10){
			setSpeed(3);
			move("RIGHT");
			move("RIGHT");
		}
		else{
			setSpeed(-3);
			move("RIGHT");
			setSpeed(-3);
		}

	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
