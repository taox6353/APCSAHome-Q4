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

public class Ammo extends MovingThing
{
	private int speed;

	public Ammo()
	{
		super(0,0);
		speed = 4;
	}

	public Ammo(int x, int y)
	{
		//add code
		super(0,0);
		speed = 4;
	}

	public Ammo(int x, int y, int s)
	{
		//add code
		super(x,y);
		speed = s;
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
		//add code to draw the ammo
		window.setColor(Color.YELLOW);
		window.fillRect(getX(), getY(), 5, 5);
		while(getY()<-50){
			window.setColor(Color.BLACK);
			window.fillRect(getX(), getY(), 5, 5);
			window.setColor(Color.YELLOW);
			window.fillRect(getX(), getY()+speed, 5, 5);
		}
		
	}

	public String toString()
	{
		return "";
	}
}
