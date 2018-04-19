package pixlab;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    System.out.println("The count is: "+Integer.toString(count));
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color
   * 
   * This method was edited as part of Activity 9 Exercise 1.
   * 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row+1][col];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /** Method to keep only blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate the picture */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(pixelObj.getRed()-255);
        pixelObj.setGreen(pixelObj.getGreen()-255);
        pixelObj.setBlue(pixelObj.getBlue()-255);
      }
    }
  }
  
  /** Method to grayscale the picture */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
    	int avg = (pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  
  /** Method to modify pixel colors to make fish easier to see */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(pixelObj.getGreen()-100);
        pixelObj.setBlue(pixelObj.getBlue()-100);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
   * vertical mirror in the center of the picture
   * from right to left */
 public void mirrorVerticalRightToLeft()
 {
   Pixel[][] pixels = this.getPixels2D();
   Pixel leftPixel = null;
   Pixel rightPixel = null;
   int width = pixels[0].length;
   for (int row = 0; row < pixels.length; row++)
   {
     for (int col = 0; col < width / 2; col++)
     {
       leftPixel = pixels[row][col];
       rightPixel = pixels[row][width - 1 - col];
       leftPixel.setColor(rightPixel.getColor());
     }
   } 
 }
  
 /** Method that mirrors the picture around a 
  * horizontal mirror in the center of the picture
  * from top to bottom */
public void mirrorHorizontal()
{
  Pixel[][] pixels = this.getPixels2D();
  Pixel topPixel = null;
  Pixel bottomPixel = null;
  int height = pixels.length;
  for (int col = 0; col < pixels[0].length; col++)
  {
    for (int row = 0; row < height / 2; row++)
    {
      topPixel = pixels[row][col];
      bottomPixel = pixels[height - 1 - row][col];
      bottomPixel.setColor(topPixel.getColor());
    }
  } 
} 
 
/** Method that mirrors the picture around a 
 * horizontal mirror in the center of the picture
 * from bottom to top */
public void mirrorHorizontalBotToTop()
{
 Pixel[][] pixels = this.getPixels2D();
 Pixel topPixel = null;
 Pixel bottomPixel = null;
 int height = pixels.length;
 for (int col = 0; col < pixels[0].length; col++)
 {
   for (int row = 0; row < height / 2; row++)
   {
     topPixel = pixels[row][col];
     bottomPixel = pixels[height - 1 - row][col];
     topPixel.setColor(bottomPixel.getColor());
   }
 } 
} 

/** Method that mirrors the picture along a diagonal
 * from the bottom left to the top right */
public void mirrorDiagonal()
{
 Pixel[][] pixels = this.getPixels2D();
 Pixel OPixel = null;//OPixel for "Original Pixel" (from bottom left)
 Pixel CPixel = null;//CPixel for "Copied Pixel" (to top right)
 
 for (int row = 0; row < pixels.length; row++)
	 {
	   for (int col = 0; col < pixels.length ; col++)
	   {
		   if(row!=col&&row>col){
			   OPixel = pixels[row][col];
			   CPixel = pixels[col][row];
			   CPixel.setColor(OPixel.getColor());
		   }
	   }
	 } 
} 

/** Mirror just part of a picture of a snowman */
public void mirrorArms()
{
  int mirrorPoint = 192;
  Pixel OPixel = null;
  Pixel CPixel = null;
  Pixel[][] pixels = this.getPixels2D();
  int absdist=0;
  for (int row = 156; row < 192; row++)
  {
	  absdist= mirrorPoint-row;
    for (int col = 102; col < 172; col++)
    {
      OPixel = pixels[row][col];      
      CPixel = pixels[mirrorPoint + absdist][col];
      CPixel.setColor(OPixel.getColor());
    }
  }
  mirrorPoint = 196;
  for (int row = 168; row < 196; row++)
  {
	  absdist= mirrorPoint-row;
    for (int col = 236; col < 292; col++)
    {
      OPixel = pixels[row][col];      
      CPixel = pixels[mirrorPoint + absdist][col];
      CPixel.setColor(OPixel.getColor());
    }
  }
}
  
/** Method that mirrors a seagull to the right */
public void mirrorGull()
{
 int mirrorPoint = 348;
 Pixel[][] pixels = this.getPixels2D();
 Pixel OPixel = null;
 Pixel CPixel = null;
 int absdist = 0;
 
 for (int row = 233; row < 326; row++)
	 {
	   for (int col = 231; col < 348 ; col++)
	   {
		   absdist = 348-col;
		   OPixel = pixels[row][col];      
		   CPixel = pixels[row][mirrorPoint+absdist];
		   CPixel.setColor(OPixel.getColor());
	   }
	 } 
} 

/** Copy a part of the passed fromPic to the
 * specified startRow and startCol in the
 * current picture
 * @param fromPic the picture to copy from
 * @param cutStartRow the start row to copy from
 * @param cutEndRow the end row to copy from
 * @param cutStartCol the start col to copy from
 * @param cutEndCol the end col to copy from
 * @param pasteRow the row to copy to
 * @param pasteCol the col to copy to
 * 
 */
public void copy(Picture fromPic, int cutStartRow, int cutEndRow, int cutStartCol, int cutEndCol, int pasteRow, int pasteCol)
{
 Pixel fromPixel = null;
 Pixel toPixel = null;
 Pixel[][] toPixels = this.getPixels2D();
 Pixel[][] fromPixels = fromPic.getPixels2D();
 
 int pasteEndRow = pasteRow + (cutEndRow-cutStartRow);
 int pasteEndCol = pasteCol + (cutEndCol-cutStartCol);
 
 for (int fromRow = cutStartRow, toRow = pasteRow; fromRow < cutEndRow && toRow < pasteEndRow; fromRow++, toRow++)
 {
   for (int fromCol = cutStartCol, toCol = pasteCol; fromCol < cutEndCol && toCol < pasteEndCol; fromCol++, toCol++)
   {
     fromPixel = fromPixels[fromRow][fromCol];
     toPixel = toPixels[toRow][toCol];
     toPixel.setColor(fromPixel.getColor());
   }
 }   
}

public void myCollage(Picture fromPic)
{
	Pixel fromPixel = null;
	Pixel toPixel = null;
	Pixel[][] toPixels = this.getPixels2D();
	Pixel[][] fromPixels = fromPic.getPixels2D();

	//First paste
	int pasteEndRow = 280 + (326-233);
	int pasteEndCol = 495 + (348-231);
	for (int fromRow = 233, toRow = 280; fromRow < 326 && toRow < pasteEndRow; fromRow++, toRow++)
	 {
	   for (int fromCol = 231, toCol = 495; fromCol < 348 && toCol < pasteEndCol; fromCol++, toCol++)
	   {
	     fromPixel = fromPixels[fromRow][fromCol];
	     fromPixel.setRed(fromPixel.getRed()-100);
	     toPixel = toPixels[toRow][toCol];
	     toPixel.setColor(fromPixel.getColor());
	   }
	 }  
	//Second paste
	pasteEndRow = 245 + (326-233);
	pasteEndCol = 201 + (348-231);
	for (int fromRow = 233, toRow = 245; fromRow < 326 && toRow < pasteEndRow; fromRow++, toRow++)
	 {
	   for (int fromCol = 231, toCol = 201; fromCol < 348 && toCol < pasteEndCol; fromCol++, toCol++)
	   {
	     fromPixel = fromPixels[fromRow][fromCol];
	     fromPixel.setRed(fromPixel.getGreen());
	     fromPixel.setGreen(fromPixel.getBlue());
	     fromPixel.setBlue(fromPixel.getRed());
	     toPixel = toPixels[toRow][toCol];
	     toPixel.setColor(fromPixel.getColor());
	   }
	 }
	//Third paste
	int mirrorPoint = 348;
	 int absdist = 0;
	 
	 for (int row = 233; row < 326; row++)
		 {
		   for (int col = 231; col < 348 ; col++)
		   {
			   absdist = 348-col;
			   fromPixel = fromPixels[row][col];
			   fromPixel.setRed(fromPixel.getBlue());
			   fromPixel.setGreen(fromPixel.getRed()+60);
			   fromPixel.setBlue(fromPixel.getGreen()-60);
			   toPixel = toPixels[row][mirrorPoint+absdist];
			   toPixel.setColor(fromPixel.getColor());
		   }
		 } 
}






  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
