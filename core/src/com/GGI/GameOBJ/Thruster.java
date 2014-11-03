package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Thruster {

	public int lv;
	public int type;
	public TextureRegion texture;
	
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
	}

}
