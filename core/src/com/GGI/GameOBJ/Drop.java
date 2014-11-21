/**
 * 
 */
package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class Drop {
public Vector2 position = new Vector2();
public Rectangle bounds = new Rectangle();
public boolean isDone = false;
public Assets a;

public int state;

public Drop(Assets a,int state){
	this.state = state;
	bounds.width = .1f;
	bounds.height = .1f;
	this.a=a;
}

public void move(){
	if(Math.abs(position.x-a.player.position.x)<.05f&&Math.abs(position.y-a.player.position.y)<.05f){
		isDone=true;
	}
	else{
		position.x+=(position.x-a.player.position.x)/2;
		position.y+=(position.y-a.player.position.y)/2;
	}
}
}
