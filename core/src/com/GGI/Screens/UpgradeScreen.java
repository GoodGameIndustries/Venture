/**
 * 
 */
package com.GGI.Screens;

import com.GGI.Map.Map;
import com.GGI.UI.Button;
import com.GGI.Venture.Venture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Emmett Deen
 *
 */
public class UpgradeScreen implements Screen, InputProcessor{

	private SpriteBatch pic = new SpriteBatch();
	private Venture g;
	private GameScreen gs;
	private BitmapFont fnt = new BitmapFont();
	private float w=Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	private Map m;
	private Button resume = new Button("UI/ResumeUp.png","UI/ResumeDown.png");
	
	public UpgradeScreen(Venture g, GameScreen gs){
		this.g=g;
		this.gs=gs;
		m = g.assets.map;
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		pic.begin();
		pic.draw(g.assets.black, 0*w, .9f*h,w,.1f*h);
		String stats =  "Lv: "+g.assets.lv+" XP: "+g.assets.currentXP + "/" + g.assets.neededXP+"("+(int)((g.assets.currentXP/g.assets.neededXP)*100)+"%)" +" $: " + g.assets.money;
		fnt.draw(pic,stats, (float)(.05*w), (float)(.96*h));
		pic.draw(resume.getState(),.78f*w,.92f*h,.2f*w,.06f*h);
		
		
		pic.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		if(screenX>.78f*w&&screenX<(.78f+.2f)*w){
			if(screenY>.92f*h&&screenY<(.92f+.06f)*h){
				resume.press();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		resume.release();
		if(screenX>.78f*w&&screenX<(.78f+.2f)*w){
			if(screenY>.92f*h&&screenY<(.92f+.06f)*h){
				g.setScreen(gs);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
