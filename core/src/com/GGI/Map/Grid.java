/**
 * 
 */
package com.GGI.Map;

import java.util.ArrayList;

import com.GGI.GameOBJ.Enemy;

/**
 * @author Emmett Deen
 *
 */
public class Grid {

	public int state;
	private Map m;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public Grid(Map m, int state){
		this.m=m;
		this.state = state;
	}
	
	public void genEnemy(){
		
	}
}
