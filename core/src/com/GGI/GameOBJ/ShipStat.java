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
	public int health;
	public float scale;
	public float bounds;
	
	public ShipStat(Vector2[] points,int health,float scale,float bounds){
		this.points = points;
		this.health = health;
		this.scale = scale;
		this.bounds = bounds;
	}
	
}
