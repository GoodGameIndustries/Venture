/**
 * 
 */
package com.GGI.UI;

import com.GGI.Screens.GameScreen;
import com.GGI.Screens.PauseScreen;
import com.GGI.Venture.Assets;
import com.badlogic.gdx.Screen;

/**
 * @author Emmett Deen
 *
 */
public class Toolbar {

	public Assets a;
	int buttonState=0;
	
	public Toolbar(Assets a,Screen s){
		this.a=a;
		if(s instanceof GameScreen){
			buttonState=0;
		}
		else if(s instanceof PauseScreen){
			buttonState=1;
		}
		
	}
	
}
