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
	
	protected TextureRegion baseText;
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Rectangle bounds = new Rectangle();
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	protected float maxVelocity;
	private float maxSteer;
	public float rotation = 0f;
	public ShipStat stats;
	private String[] breakDown;
	private Assets a;
	
	public Ship(String s,Assets a){
		
		
		this.a=a;
		//base,guns,(each gun),shields,(each shield),thrusters,(each thruster)
		//breakDown = s.split(",");
		base = Integer.parseInt(s);
	
		//System.out.println(base);
		//base = 9;
		
		position.x=.5f;
		position.y=.5f;
		velocity.x= 0f;
		velocity.y= 0f;
	
		
		baseText = a.bases.get(base-1);
		
		bounds.width=(float)(baseText.getRegionWidth())*((w/1000)/w);
		bounds.height=(float)(baseText.getRegionHeight())*((w/1000)/w);
		
	}
	
	
	public void move(float delta){
		
		
		position.x=position.x+(velocity.x*delta);
		position.y=position.y+(velocity.y*delta);
		
	}
	
	public TextureRegion getText(){
		return baseText;
	}
	
	
	
}
