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
public class Enemy extends Ship{
	public Assets a;
	public double w =Gdx.graphics.getWidth(), h =Gdx.graphics.getHeight();
	public float cx,cy;
	public float pX,pY;
	public float reload = 50;
	public float randX=1,randY=1;
	public float newR = 0;
	
	public Enemy(String s,Assets a){
		super(s,a);
		this.a=a;
		//stats = a.stats[this.base];
		reload/=base;
		baseText = a.enemiesT.get(base-1);
		position.x=(float) (Math.random()*4-(bounds.width/2));
		position.y=(float) (Math.random()*4-(bounds.height/2));
		pX=.5f-(bounds.width/2);
		pY=.5f-(bounds.height/2);
		cx=position.x+(bounds.width/2);
		cy=position.y+(bounds.height/2);
		//System.out.println(position.x+","+position.y);
	}

	
	
	public void move(float delta){
		genRands();
		Grid currentGrid = a.map.getCurrent();
		
		maxVelocity=base*2;
		if(maxVelocity>10){maxVelocity=10;}
		
			velocity.x=randX*maxVelocity;
			velocity.y=randY*maxVelocity;
			super.move(delta);
		
			if(randX!=0&&randY!=0){
		rotation = (float) Math.toDegrees(Math.atan2(randY, randX));
		//System.out.println(rotation);
			}
			//if(a.aim.diff.x!=0&&a.aim.diff.y!=0){
				rotation = (float) Math.toDegrees(Math.atan2(a.player.position.y-position.y, a.player.position.x-position.x));
				fire();
				//	}
			cx=position.x+(bounds.width/2);
			cy=position.y+(bounds.height/2);
	}

	public void genRands() {
		if(newR<=0){
		if(Math.random()>.5){randX=(float) Math.random()/10;}
		else{randX=(float) Math.random()/2;}
		
		if(Math.random()>.5){randY=(float) Math.random()/10;}
		else{randY=(float) Math.random()/2;}
		
		while(Math.abs(randX)>.1f){randX/=2;}
		while(Math.abs(randY)>.1f){randY/=2;}
		newR=(float) (Math.random()*500);
		
		if(Math.random()>.5){
			randX*=-1;
		}
		if(Math.random()>.5){
			randY*=-1;
		}
		
		}
		else{
			newR--;
		}
		
		
	}

	private void fire() {
		if(reload<=0){
			reload=100/base;
		a.bullets.add(new Bullet(1,position.x+(bounds.width/2),position.y+(bounds.height/2),(a.player.position.x-position.x)/150,(a.player.position.y-position.y)/150,rotation));
		}
	}
	
}
