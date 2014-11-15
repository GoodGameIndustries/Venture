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
	private Button buy = new Button("UI/BuyUp.png","UI/BuyDown.png");
	boolean lock = false;//////Change this to true!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public UpgradeScreen(Venture g, GameScreen gs){
		this.g=g;
		this.gs=gs;
		m = g.assets.map;
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		if(g.assets.lv/5>g.assets.player.base){lock=false;}
		
		pic.begin();
		pic.draw(g.assets.black, 0*w, .9f*h,w,.1f*h);
		String stats =  "Lv: "+g.assets.lv+" XP: "+g.assets.currentXP + "/" + g.assets.neededXP+"("+(int)((g.assets.currentXP/g.assets.neededXP)*100)+"%)" +" $: " + g.assets.money;
		fnt.draw(pic,stats, (float)(.05*w), (float)(.96*h));
		pic.draw(resume.getState(),.78f*w,.92f*h,.2f*w,.06f*h);
		
		pic.draw(g.assets.ranks.get((int) (g.assets.lv/5)),.1f*w,(.5f*h)-(.2f*w),.4f*w,.4f*w);
		pic.draw(g.assets.nextShip,.6f*w,.6f*h,.4f*w,.3f*h);
		
		
		if(g.assets.player.base<10&&!lock){
			pic.draw(buy.getState(),.64f*w,.6f*h,.2f*w,.1f*h);
		fnt.draw(pic, "Cost: " + Math.pow(2,g.assets.player.base+1), .7f*w, .7f*h);
		fnt.draw(pic, "Base Lv: " + (g.assets.player.base+1), .6f*w, .5f*h);
		fnt.draw(pic, "Health: " + (g.assets.player.base+1)*5, .6f*w, .4f*h);
		fnt.draw(pic, "Speed: " + (g.assets.player.base+1)*2, .6f*w, .3f*h);
		fnt.draw(pic, "Reload Time: " +(100/(g.assets.player.base+1)), .6f*w, .2f*h);
		}
		
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
		if(screenX>.64f*w&&screenX<(.64f+.2f)*w){
			if(screenY>.6f*h&&screenY<(.6f+.1f)*h){
				buy.press();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		resume.release();
		buy.release();
		if(screenX>.78f*w&&screenX<(.78f+.2f)*w){
			if(screenY>.92f*h&&screenY<(.92f+.06f)*h){
				g.setScreen(gs);
			}
		}
		if(screenX>.64f*w&&screenX<(.64f+.2f)*w){
			if(screenY>.6f*h&&screenY<(.6f+.1f)*h){
				if(g.assets.money>Math.pow(2,g.assets.player.base+1)){
					g.assets.money-=Math.pow(2,g.assets.player.base+1);
					g.assets.player.base+=1;
					g.assets.player.baseText = g.assets.bases.get(g.assets.player.base-1);
					g.assets.save();
				}
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
