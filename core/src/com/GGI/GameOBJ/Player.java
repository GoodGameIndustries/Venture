/**
 * 
 */
package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.GGI.Venture.Venture;

/**
 * @author Emmett Deen
 *
 */
public class Player extends Ship{
	public Assets a;
	
	public Player(String s,Assets a){
		super(s,a);
		this.a=a;
		stats = a.stats[this.base];
		bounds.width = .5f;
		bounds.height = .5f;
		position.x = .05f;
		position.y = .04f;
	}

	public void play(){
		stats.clear();
		guns.clear();
		shields.clear();
		thrusters.clear();
		position.x=.5f;
		position.y=.5f;
		bounds.width= a.stats[base].bounds;
		bounds.height=a.stats[base].bounds;
		genGuns();
		
	}
	
}
