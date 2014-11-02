/**
 * 
 */
package com.GGI.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author Emmett Deen
 *
 */
public class Button {
	private Texture up;
	private Texture down;
	private Texture current;
	
	public Button(String f1, String f2){
		up = new Texture(Gdx.files.internal(f1));
		down = new Texture(Gdx.files.internal(f2));
		current = up;
	}
	
	public void press(){
		current = down;
	}
	
	public void release(){
		current = up;
	}
	
	public Texture getState(){
		return current;
	}
}
