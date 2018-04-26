package painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.*;

public class Painter extends Canvas implements MouseListener, MouseMotionListener, Runnable/*, KeyListener*/
{
	
	//instance vars
	private BufferedImage back;
	private boolean mouseDown = false;
	private Color selectedcolor = Color.BLACK;
	private int locX = 0;
	private int locY = 0;
	
	public Painter(){
		setBackground(Color.WHITE);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();

		setVisible(true);
	}
	public void update(Graphics window)
	{
		   paint(window);
	}
	public void paint( Graphics window )
	{
		Graphics2D twoDGraph = (Graphics2D)window;
		if(back==null)
			   back = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics canvas = back.createGraphics();
		
		canvas.drawString("Cursor: "+MouseInfo.getPointerInfo().getLocation().getX()+", "+MouseInfo.getPointerInfo().getLocation().getY(), 20, 9995);
		
		if(mouseDown == true){
			
		}
		
		
	}
	
	public void mouseClicked(MouseEvent event){
		
	}
	public void mousePressed(MouseEvent event){
		mouseDown = true;
	}
	public void mouseReleased(MouseEvent event){
		mouseDown = false;
	}
	public void mouseEntered(MouseEvent event){
		
	}
	public void mouseExited(MouseEvent event){
		
	}
	public void mouseDragged(MouseEvent event){
		mouseDown = true;
	}
	public void mouseMoved(MouseEvent event){
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	public void run()
	   {
	   	try
	   	{
	   		while(true)
	   		{
	   		   Thread.currentThread().sleep(3);
	            repaint();
	         }
	      }catch(Exception e)
	      {
	      }
	  	}
	
}
