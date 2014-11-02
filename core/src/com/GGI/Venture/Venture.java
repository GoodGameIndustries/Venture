package com.GGI.Venture;

import com.GGI.Screens.MainMenuScreen;
import com.GGI.Screens.OpeningScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Venture extends Game {
	public Assets assets;
	
	@Override
	public void create () {
		setScreen(new OpeningScreen(this));	
		}

	
}
