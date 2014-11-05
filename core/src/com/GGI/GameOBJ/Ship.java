/**
 * 
 */
package com.GGI.GameOBJ;

import java.util.ArrayList;

import com.GGI.Venture.Assets;
import com.GGI.Venture.Venture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class Ship {

	public int base;
	public ArrayList<Gun> guns = new ArrayList<Gun>();
	public ArrayList<Shield> shields = new ArrayList<Shield>();
	public ArrayList<Thruster> thrusters = new ArrayList<Thruster>();
	private Texture baseText;
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Rectangle bounds = new Rectangle();
	private int w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private float maxVelocity;
	private float maxSteer;
	public float rotation;
	public ShipStat stats;
	private String[] breakDown;
	private Assets a;
	
	public Ship(String s,Assets a){
		
		
		this.a=a;
		//base,guns,(each gun),shields,(each shield),thrusters,(each thruster)
		breakDown = s.split(",");
		base = Integer.parseInt(breakDown[0]);
	
		//System.out.println(base);
		//base = 9;
		
		position.x=.5f;
		position.y=.5f;
		velocity.x= 0f;
		velocity.y= 0f;
		bounds.width= a.stats[base].bounds;
		bounds.height=a.stats[base].bounds;
		
		baseText = a.bases.get(base);
		
	}
	
	
	public void move(float delta){
		position.x=position.x+(velocity.x*delta);
		position.y=position.y+(velocity.y*delta);
		
	}
	
	public Texture getText(){
		return baseText;
	}
	
	public void genGuns(){
		int numGuns = Integer.parseInt(breakDown[1]);
		int index = 2;
		for(int i = index; i < index+numGuns;i++){
			guns.add(new Gun(breakDown[i],a));
		}
		int numShield = Integer.parseInt(breakDown[index+numGuns]);
		index = index+numGuns+1;
		for(int i = index; i < index+numShield;i++){
			shields.add(new Shield(breakDown[i],a));
		}
		//int numThrust = Integer.parseInt(breakDown[index+numShield]);
		index = index+numShield+1;
		for(int i = index; i < breakDown.length; i++){
			thrusters.add(new Thruster(breakDown[i],a));
		}
	}
	
}
