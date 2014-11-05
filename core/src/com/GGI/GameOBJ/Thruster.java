package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Thruster {

	public int lv;
	public int type;
	public TextureRegion texture;
	public Vector2 position = new Vector2();
	public Rectangle bounds = new Rectangle();
	double w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	
	public Thruster(String str,Assets a) {
		type =Integer.parseInt(str.substring(0,1));
		lv =Integer.parseInt(str.substring(0,2));
		switch(type){
		case 1:texture = a.scoutThrust;
		break;
		case 2:texture = a.snipeThrust;
		break;
		case 3:texture = a.tankThrust;
		break;
		}
		
		int ne = a.player.stats.getNextEmpty();
		bounds.width = a.player.bounds.width*a.player.stats.scale;
		bounds.height = (float) (a.player.bounds.height*a.player.stats.scale);
		position.x = a.player.position.x + (a.player.stats.points[ne].x*a.player.bounds.width);
		position.y = (float) (a.player.position.y + (a.player.stats.points[ne].y*a.player.bounds.height*(w/h)));
		
		System.out.println(position.x + "," + position.y + "," + bounds.width + "," + bounds.height);
	
	}

}
