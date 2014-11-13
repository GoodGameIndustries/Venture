package com.GGI.GameOBJ;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {

	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Rectangle bounds = new Rectangle();
	public int team;
	public float rotation;
	
	
	public Bullet(int team, float positionX,float positionY,float velocityX,float velocityY,float rotation){
		
		this.velocity.x =velocityX;
		this.velocity.y = velocityY;
		this.team=team;
		this.rotation=rotation;
		bounds.width=.02f;
		bounds.height=.01f;
		this.position.x=positionX-bounds.width/2;
		this.position.y=positionY-bounds.height/2;
	}
	
	public void move(float delta){
		
		position.x=position.x+(velocity.x*delta*50);
		position.y=position.y+(velocity.y*delta*50);
	}
}
