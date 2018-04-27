package starfighter;

import java.lang.*;

public class Aliens {
	
	Alien[][] a;
	
	public Aliens(){
		int n = (int)(Math.random()*2+2);
		a = new Alien[n][n];
		int constant = (int)(Math.random()*10+80);
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
