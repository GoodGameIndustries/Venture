/**
 * 
 */
package com.GGI.Map;

import java.util.ArrayList;

import com.GGI.GameOBJ.Enemy;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class Grid {

	public int state;
	private Map m;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Rectangle bounds = new Rectangle();
	
	public Grid(Map m, int state){
		this.m=m;
		this.state = state;
		position.x = -2f;
		position.y = -2f;
		bounds.width = 4f;
		bounds.height = 4f;
	}
	
	public void genEnemy(){
		
	}
	
	public void boundsCheck(){
		if(position.x>0){position.x=0;}
		if(position.y>0){position.y=0;}
		if(position.x+bounds.width<1){position.x=-bounds.width+1;}
		if(position.y+bounds.height<1){position.y=-bounds.height+1;}
	}
}
