package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shield {

	public int lv;
	public int type;
	public TextureRegion texture;
	
	public Shield(String str,Assets a) {
		type =Integer.parseInt(str.substring(0,1));
		lv =Integer.parseInt(str.substring(0,2));
		switch(type){
		case 1:texture = a.scoutShield;
		break;
		case 2:texture = a.snipeShield;
		break;
		case 3:texture = a.tankShield;
		break;
		}
	}
}
