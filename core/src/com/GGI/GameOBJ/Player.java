/**
 * 
 */
package com.GGI.GameOBJ;

import com.GGI.Map.Grid;
import com.GGI.Venture.Assets;
import com.badlogic.gdx.Gdx;

/**
 * @author Emmett Deen
 *
 */
public class Player extends Ship{
	public Assets a;
	public double w =Gdx.graphics.getWidth(), h =Gdx.graphics.getHeight();
	public float cx,cy;
	public float pX,pY;
	public float reload = 50;
	
	public Player(String s,Assets a){
		super(s,a);
		this.a=a;
		//stats = a.stats[this.base];
		reload/=base;
		
		position.x=.3f-(bounds.width/2);
		position.y=.5f-(bounds.height/2);
		System.out.println(position.x+","+position.y);
	}

	public void play(){
		//stats.clear();
		
		//bounds.width= a.stats[base].bounds;
		//bounds.height=a.stats[base].bounds;
		position.x=.5f-(bounds.width/2);
		position.y=.5f-(bounds.height/2);
		pX=.5f-(bounds.width/2);
		pY=.5f-(bounds.height/2);
		cx=position.x+(bounds.width/2);
		cy=position.y+(bounds.height/2);
		
		
	}
	
	public void move(float delta){
		
		Grid currentGrid = a.map.getCurrent();
		
		maxVelocity=base*2;
		if(maxVelocity>15){maxVelocity=15;}
			velocity.x=a.move.diff.x*maxVelocity;
			velocity.y=a.move.diff.y*maxVelocity;
			super.move(delta);
		
			if(a.move.diff.x!=0&&a.move.diff.y!=0){
		rotation = (float) Math.toDegrees(Math.atan2(a.move.diff.y, a.move.diff.x));
		//System.out.println(rotation);
			}
			if(a.aim.diff.x!=0&&a.aim.diff.y!=0){
				rotation = (float) Math.toDegrees(Math.atan2(a.aim.diff.y, a.aim.diff.x));
				fire();
					}
	}

	private void fire() {
		if(reload<=0){
			reload=100/base;
		a.bullets.add(new Bullet(0,position.x+(bounds.width/2),position.y+(bounds.height/2),a.aim.diff.x,a.aim.diff.y,rotation));
		}
	}
	
}
