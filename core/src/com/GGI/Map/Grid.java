/**
 * 
 */
package com.GGI.Map;

import java.util.ArrayList;

import com.GGI.GameOBJ.Enemy;
import com.GGI.GameOBJ.Player;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class Grid {

	public int state;
	private Map m;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Rectangle bounds = new Rectangle();
	public int max;
	
	public Grid(Map m, int state){
		this.m=m;
		this.state = state;
		position.x = -2f;
		position.y = -2f;
		bounds.width = 4f;
		bounds.height = 4f;
		max = m.distanceFromHome(this);
		System.out.println("State: "+state);
		if(state==0){
			genEnemy(max);
			System.out.println("max: "+max);
			System.out.println("enemies: "+enemies.size());
		}
	}
	
	public void genEnemy(int m){
		
		if(m==0){
			
		}
		else{
			int rand=0;
			if(m<10){
			 rand = (int) (Math.random()*(m-1)+1);
			}
			else{
			 rand = (int) (Math.random()*(9)+1);
			}
			enemies.add(new Enemy(""+rand,this.m.g));
			genEnemy(m-rand);
		}
	}
	
	public void boundsCheck(){
		if(position.x>0){position.x=0;}
		if(position.y>0){position.y=0;}
		if(position.x+bounds.width<1){position.x=-bounds.width+1;}
		if(position.y+bounds.height<1){position.y=-bounds.height+1;}
	}

	public Enemy topRight(Player p) {
		Enemy result = null;
		for(int i = 0 ; i < enemies.size();i++){
			if(enemies.get(i).position.x>=p.position.x&&enemies.get(i).position.y>=p.position.y){
				result = enemies.get(i);
				break;
			}
		}
		return result;
	}

	public Enemy bottomRight(Player p) {
		Enemy result = null;
		for(int i = 0 ; i < enemies.size();i++){
			if(enemies.get(i).position.x>=p.position.x&&enemies.get(i).position.y<p.position.y){
				result = enemies.get(i);
				break;
			}
		}
		return result;
	}

	public Enemy topLeft(Player p) {
		Enemy result = null;
		for(int i = 0 ; i < enemies.size();i++){
			if(enemies.get(i).position.x<p.position.x&&enemies.get(i).position.y>=p.position.y){
				result = enemies.get(i);
				break;
			}
		}
		return result;
	}

	public Enemy bottomLeft(Player p) {
		Enemy result = null;
		for(int i = 0 ; i < enemies.size();i++){
			if(enemies.get(i).position.x<p.position.x&&enemies.get(i).position.y<p.position.y){
				result = enemies.get(i);
				break;
			}
		}
		return result;
	}
}
