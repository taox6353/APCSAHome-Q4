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

	public void aliendraw(Graphics window){
		window.setColor(Color.RED);
		window.fillRect(getX(), getY(), 2, 2);
		move("DOWN");
	}
	
	public void draw( Graphics window )
	{
		//add code to draw the ammo
		if(speed==3){
			window.setColor(Color.WHITE);
			window.fillRect(getX(), getY(), 5, 5);
			if(getY()>-5)
				move("UP");
		}
		else if(speed==2){
			int r = (int)(Math.random()*256);
			int g = (int)(Math.random()*256);
			int b = (int)(Math.random()*256);
			
			Color col = new Color(r,g,b);
			
			window.setColor(col);
			window.fillRect(getX(), getY(), 5, 5);
			int leftright = (int)(Math.random()*2);
			if(getY()>-5){
				if(leftright==0){
					move("LEFT");
					move("UP");
					move("LEFT");
					move("UP");
					move("LEFT");
					move("DOWN");
				}
				else if(leftright==1){
					move("RIGHT");
					move("UP");
					move("RIGHT");
					move("UP");
					move("RIGHT");
					move("DOWN");
				}
			}
		}
	}

	public String toString()
	{
		return "";
	}
}
