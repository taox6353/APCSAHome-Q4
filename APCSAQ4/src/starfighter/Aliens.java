package starfighter;

import java.lang.*;

public class Aliens {
	
	Alien[][] a = new Alien[2][2];
	
	public Aliens(){
		int constant = (int)(Math.random()*150+100);
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				a[i][j] = new Alien(20+constant*i,constant*j,3);
			}
		}
	}
	public Alien[][] getList(){
		return a;
	}
	
}
