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

	private int base;
	private ArrayList<Gun> guns = new ArrayList<Gun>();
	private ArrayList<Shield> shields = new ArrayList<Shield>();
	private ArrayList<Thruster> thrusters = new ArrayList<Thruster>();
	private Texture baseText;
	private Venture g;
	private Vector2 position;
	private Vector2 velocity;
	private Rectangle bounds;
	private int w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private float maxVelocity;
	private float maxSteer;
	private float rotation;
	
	public Ship(String s,Assets a){
		this.g=g;
		//base,guns,(each gun),shields,(each shield),thrusters,(each thruster)
		String[] breakDown = s.split(",");
		base = Integer.parseInt(breakDown[0]);
		int numGuns = Integer.parseInt(breakDown[1]);
		int index = 2;
		for(int i = index; i < index+numGuns;i++){
			guns.add(new Gun(breakDown[i]));
		}
		int numShield = Integer.parseInt(breakDown[index+numGuns]);
		index = index+numGuns+1;
		for(int i = index; i < index+numShield;i++){
			shields.add(new Shield(breakDown[i]));
		}
		//int numThrust = Integer.parseInt(breakDown[index+numShield]);
		index = index+numShield+1;
		for(int i = index; i < breakDown.length; i++){
			thrusters.add(new Thruster(breakDown[i]));
		}
		//System.out.println(base);
		//base = 8;
		baseText = a.bases.get(base);
		
	}
	
	public void move(float delta){
		position.x=position.x+(velocity.x*delta);
		position.y=position.y+(velocity.y*delta);
		
	}
	
	public Texture getText(){
		return baseText;
	}
	
}
