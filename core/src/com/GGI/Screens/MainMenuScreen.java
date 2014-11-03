/**
 * 
 */
package com.GGI.Screens;

import com.GGI.GameOBJ.Gun;
import com.GGI.GameOBJ.Player;
import com.GGI.GameOBJ.Shield;
import com.GGI.GameOBJ.Thruster;
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
public class MainMenuScreen implements Screen, InputProcessor {

	private SpriteBatch pic = new SpriteBatch();
	private Venture g;
	private int w=Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	private Button play = new Button("UI/PlayUp.png","UI/PlayDown.png");
	private Button help = new Button("UI/HelpUp.png","UI/HelpDown.png");
	private BitmapFont fnt = new BitmapFont();
	private Player player;
	
	public MainMenuScreen(Venture g) {
		this.g=g;
		fnt.scale(w/1000);
		player = g.assets.player;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		pic.begin();
		//bg
		pic.draw(g.assets.concrete,0,0,w,w);
		pic.draw(g.assets.black, (int)(w-(.4*w)),0,(int)(.35*w),h);
		pic.draw(g.assets.player.getText(),(int)(.05*w),(int)(.04*w),(int)(.5*w),(int)(.5*w));
		
		for(int i = 0; i <player.guns.size(); i++){
			Gun temp = player.guns.get(i);
			if(temp.texture!=null){
			pic.draw(temp.texture,(float) ((.05f+(g.assets.stats[player.base].points[i].x/(g.assets.stats[player.base].scale*0.5)))*w), (float)((.04f+(g.assets.stats[player.base].points[i].y/(g.assets.stats[player.base].scale*0.5)))*h), (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((.5f*g.assets.stats[player.base].scale)*w),(float) ((.5f*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
			}
		}
		for(int i = player.guns.size(); i <player.guns.size()+player.shields.size(); i++){
			Shield temp = player.shields.get(i-player.guns.size());
			if(temp.texture!=null){
			pic.draw(temp.texture,(float) ((.05f+(g.assets.stats[player.base].points[i].x/(g.assets.stats[player.base].scale*0.5)))*w), (float)((.04f+(g.assets.stats[player.base].points[i].y/(g.assets.stats[player.base].scale*0.5)))*h), (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((.5f*g.assets.stats[player.base].scale)*w),(float) ((.5f*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
			}
		}
		for(int i = (player.guns.size()+player.shields.size()); i <(player.guns.size()+player.shields.size())+player.thrusters.size(); i++){
			Thruster temp = player.thrusters.get(i-(player.guns.size()+player.shields.size()));
			if(temp.texture!=null){
			pic.draw(temp.texture,(float) ((.05f+(g.assets.stats[player.base].points[i].x/(g.assets.stats[player.base].scale*0.5)))*w), (float)((.04f+(g.assets.stats[player.base].points[i].y/(g.assets.stats[player.base].scale*0.5)))*h), (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((.5f*g.assets.stats[player.base].scale)*w),(float) ((.5f*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
			}
		}
		//end bg
		
		//buttons and title
		pic.draw(help.getState(),(int)(w-(.35*w)),(int)(.05*h),(int)(.25*w),(int)(.15*h));
		pic.draw(play.getState(),(int)(w-(.35*w)),(int)(.2*h),(int)(.25*w),(int)(.15*h));
		pic.draw(g.assets.title,(int)(w-(.39*w)),(int)(.8*h),(int)(.33*w),(int)(.15*h));
		//end buttons and title
		
		//stats
		fnt.draw(pic, "LV: " + g.assets.lv, (int)(w-(.31*w)), (int)(.65*h));
		fnt.draw(pic, "XP: " + g.assets.currentXP + "/" + g.assets.neededXP + "(" + ((int)((g.assets.currentXP/g.assets.neededXP)*100)) + "%)", (int)(w-(.31*w)), (int)(.57*h));
		fnt.draw(pic, "$: " + g.assets.money, (int)(w-(.31*w)), (int)(.5*h));
		//ends stats
		pic.end();
	
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
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
		screenY = h-screenY;
		//System.out.println(screenX+":"+(int)(w-(.35*w))+":"+(int)(w-(.1*w)));
		if(screenX>(int)(w-(.35*w)) && screenX<(int)(w-(.1*w))){
			//System.out.println("x");
			if(screenY>(int)(.05*h)&&screenY<(int)((.05*h)+(.15*h))){
				help.press();
			}
			else if(screenY>(int)(.2*h)&&screenY<(int)((.2*h)+(.15*h))){
				play.press();
			}
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = h-screenY;
		play.release();
		help.release();
		
		if(screenX>(int)(w-(.35*w)) && screenX<(int)(w-(.1*w))){
			//System.out.println("x");
			if(screenY>(int)(.05*h)&&screenY<(int)((.05*h)+(.15*h))){
				//help.press();
			}
			else if(screenY>(int)(.2*h)&&screenY<(int)((.2*h)+(.15*h))){
				g.setScreen(new GameScreen(g));
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
