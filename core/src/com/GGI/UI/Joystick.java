package com.GGI.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Joystick {

	float w=Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	public Vector2 pos;
	public Vector2 top = new Vector2();
	public Vector2 diff = new Vector2();
	public Rectangle bounds = new Rectangle();
	public Rectangle topBounds = new Rectangle();
	
	public Joystick(Vector2 pos){
		this.pos=pos;
		
		bounds.width = .1f;
		bounds.height = .1f;
		topBounds.width=bounds.width;
		topBounds.height=bounds.height;
		top.x=pos.x;
		top.y=pos.y;
	}
	
	public void move(int x, int y){
		top.x =x/w;
		top.y=y/h;
		
		diff.x = top.x-pos.x;
		diff.y = top.y-pos.y;
	}
	
}
