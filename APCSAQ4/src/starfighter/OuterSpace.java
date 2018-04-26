package starfighter;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private int score;
	private boolean lose;

	/* uncomment once you are ready for this part
	 */
	private ArrayList<Alien> aliens;
	private ArrayList<Ammo> shots;
//	private Alien[][] alienz;

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other stuff
		
		ship = new Ship(100,450,2);
		alienOne = new Alien(20,0,3);
		alienTwo = new Alien(680,0,3);
		shots = new ArrayList<Ammo>();
		score = 0;
		lose = false;
		
//		alienz = new Alien[2][2];
		aliens = new ArrayList<Alien>();
		
		
		Aliens a = new Aliens();
//		alienz = a.getList();
		
		for(int i=0;i<a.getList().length;i++){
			for(int j=0;j<a.getList()[0].length;j++){
				aliens.add(a.getList()[i][j]);
			}
		}
		
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		
		graphToBack.setColor(Color.CYAN);
		graphToBack.drawString("Score: " + score, 500, 500);
		ship.draw(graphToBack);
		
		for(int i=0;i<aliens.size();i++){
			aliens.get(i).draw(graphToBack);
		}
		
		alienOne.draw(graphToBack);
		alienTwo.draw(graphToBack);
		
		
		

		if(keys[0] == true)
		{
			ship.move("LEFT");
		}
		
		//add code to move stuff
		if(keys[1] == true)
		{
			ship.move("RIGHT");
		}
		if(keys[2] == true)
		{
			ship.move("UP");
		}
		if(keys[3] == true)
		{
			ship.move("DOWN");
		}
		if(keys[4] == true)
		{
			int col = (int)(Math.random()*99+1);
			if(col>90)
				shots.add(new Ammo(ship.getX()+36,ship.getY(),3));
			else
				shots.add(new Ammo(ship.getX()+36,ship.getY(),2));
			keys[4] = false;
		}
		
		for(int i=0;i<shots.size();i++){
			shots.get(i).draw(graphToBack);
			if((alienOne.getX()+80>=shots.get(i).getX()&&alienOne.getX()<=shots.get(i).getX())&&
					(alienOne.getY()+80>=shots.get(i).getY()&&alienOne.getY()<=shots.get(i).getY())){
				if(shots.get(i).getSpeed()==6){
					alienOne.setPos(1200, 1200);
					graphToBack.setColor(Color.BLACK);
					graphToBack.drawString("Score: " + score, 500, 500);
					score += 24;
					graphToBack.setColor(Color.CYAN);
					graphToBack.drawString("Score: " + score, 500, 500);
					shots.get(i).setPos(5000,0);
				}
			}
			if((alienTwo.getX()+80>=shots.get(i).getX()&&alienTwo.getX()<=shots.get(i).getX())&&
					(alienTwo.getY()+80>=shots.get(i).getY()&&alienTwo.getY()<=shots.get(i).getY())){
				if(shots.get(i).getSpeed()==6){
					alienTwo.setPos(1200, 1200);
					graphToBack.setColor(Color.BLACK);
					graphToBack.drawString("Score: " + score, 500, 500);
					score += 24;
					graphToBack.setColor(Color.CYAN);
					graphToBack.drawString("Score: " + score, 500, 500);
					shots.get(i).setPos(5000,0);
				}
			}
			for(int a=0;a<aliens.size();a++){
				if((aliens.get(a).getX()+80>=shots.get(i).getX()&&aliens.get(a).getX()<=shots.get(i).getX())&&
						(aliens.get(a).getY()+80>=shots.get(i).getY()&&aliens.get(a).getY()<=shots.get(i).getY())){
					aliens.get(a).setPos(1200, 1200);
					aliens.remove(aliens.get(a));
					graphToBack.setColor(Color.BLACK);
					graphToBack.drawString("Score: " + score, 500, 500);
					score += 12;
					graphToBack.setColor(Color.CYAN);
					graphToBack.drawString("Score: " + score, 500, 500);
					shots.get(i).setPos(5000,0);
					
				}
			}
		}
		
		if(ship.getX()>750||ship.getX()<-50||ship.getY()>500||ship.getY()<-50){
			graphToBack.setColor(Color.CYAN);
			graphToBack.drawString("You are lost in space! Return to the battle zone!", 200, 550);;
		}
		if(ship.getX()<750&&ship.getY()<500&&ship.getX()>-50&&ship.getY()>-50){
			graphToBack.setColor(Color.BLACK);
			graphToBack.drawString("You are lost in space! Return to the battle zone!", 200, 550);;
		}
			
		
		
		
		if(alienOne.getX()<10||alienOne.getX()>700){
			alienOne.setSpeed((alienOne.getSpeed())*-1);
		}
		if(alienTwo.getX()<10||alienTwo.getX()>700){
			alienTwo.setSpeed((alienTwo.getSpeed())*-1);
		}
		
		//add in collision detection
		for(int i=0;i<aliens.size();i++){
			if(aliens.get(i).getX()<10||aliens.get(i).getX()>700){
				aliens.get(i).setSpeed((aliens.get(i).getSpeed())*-1);
			}
		}
		
		if(alienOne.getX()<10||alienOne.getX()>700){
			alienOne.setSpeed((alienOne.getSpeed())*-1);
		}
		if(alienTwo.getX()<10||alienTwo.getX()>700){
			alienTwo.setSpeed((alienTwo.getSpeed())*-1);
		}
		for(int a=0;a<aliens.size();a++){
				if((aliens.get(a).getX()+80>=ship.getX()&&aliens.get(a).getX()<=ship.getX())&&
						(aliens.get(a).getY()+80>=ship.getY()&&aliens.get(a).getY()<=ship.getY())){
					for(int b=0;b<aliens.size();b++){
						lose = true;
					}
					
				}
		}
		
		if(!lose&&aliens.size()==0){
			graphToBack.setColor(Color.CYAN);
			graphToBack.drawString("YOU WON WITH A SCORE OF "+score+"! ", 250, 250);
		}
		
		if(lose){
			for(int b=0;b<aliens.size();b++){
				aliens.remove(b);
			}	
			graphToBack.setColor(Color.CYAN);
			graphToBack.drawString("YOU LOST!", 250, 250);
			ship.setPos(8000, 8000);
		}
		

		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

