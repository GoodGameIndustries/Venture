/**
 * 
 */
package com.GGI.GameOBJ;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class ShipStat {

	public Vector2[] points;
	public boolean[] attatched;
	public int health;
	public float scale;
	public float bounds;
	private int nextEmpty = -1;
	
	public ShipStat(Vector2[] points,int health,float scale,float bounds){
		this.points = points;
		this.health = health;
		this.scale = scale;
		this.bounds = bounds;
		attatched = new boolean[points.length];
		for(int i = 0; i < attatched.length; i++){
			attatched[i]=false;
		}
	}

	public int getNextEmpty() {
		nextEmpty++;
		return nextEmpty;
		
	}

	public void clear() {
		nextEmpty = -1;
		
	}
	
}
