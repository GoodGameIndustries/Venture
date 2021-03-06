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
	
	public void move(float touchX, float touchY){
		if(touchX<(pos.x*w)){touchX=(int) (pos.x*w);}
		if(touchY<(pos.y*h)){touchY=(int) (pos.y*h);}
		if(touchX>((pos.x+(bounds.width/1.5))*w)){touchX=(int) ((pos.x+(bounds.width/1.5))*w);}
		if(touchY>((pos.y*h)+((bounds.height/1.5)*w))){touchY=(int) ((pos.y*h)+((bounds.height/1.5)*w));}
		touchX=(int) (touchX-((topBounds.width/3)*w));
		touchY=(int) (touchY-((topBounds.height/3)*w));
		top.x =touchX/w;
		top.y=touchY/h;
		
		diff.x = top.x-pos.x;
		diff.y = top.y-pos.y;
		
	}
	
	public void reset(){
		top.x=pos.x;
		top.y=pos.y;
		diff.x = top.x-pos.x;
		diff.y = top.y-pos.y;
	}
	
	public boolean inBounds(int x, int y){
		if(x>pos.x*w && x<(pos.x+bounds.width)*w && y>pos.y*h && y<(pos.y*h)+(bounds.height*w)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
