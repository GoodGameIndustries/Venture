/**
 * 
 */
package com.GGI.GameOBJ;

import com.GGI.Venture.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Emmett Deen
 *
 */
public class Gun {

	public int lv;
	public int type;
	public TextureRegion texture;
	
	public Gun(String str,Assets a) {
		type =Integer.parseInt(str.substring(0,1));
		lv =Integer.parseInt(str.substring(0,2));
		switch(type){
		case 1:texture = a.scoutGun;
		break;
		case 2:texture = a.snipeGun;
		break;
		case 3:texture = a.tankGun;
		break;
		}
	}

}
