package com.GGI.GameOBJ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Star {

	
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public float acceleration = 1.00000001f;
	
	public Star(){
		gen();
	}
	
	private void gen() {
		position.x=(float) .3;
		position.y=(float) .5;
		velocity.x=(float) ((float) 1-Math.random());
		velocity.y=(float) 1-velocity.x;
		
		if(Math.random()>.5){
			velocity.x=-velocity.x;
		}
		if(Math.random()>.5){
			velocity.y=-velocity.y;
		}
	}

	public void move(float delta){
		position.x+=velocity.x*delta;
		position.y+=velocity.y*delta;
		velocity.x*=acceleration;
		velocity.y*=acceleration;
		
		if(position.x>1||position.x<0){gen();}
	}
	
}
