/**
 * 
 */
package com.GGI.Screens;

import com.GGI.GameOBJ.Gun;
import com.GGI.GameOBJ.Player;
import com.GGI.GameOBJ.Shield;
import com.GGI.GameOBJ.Thruster;
import com.GGI.Map.Grid;
import com.GGI.Venture.Venture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Emmett Deen
 *
 */
public class GameScreen implements Screen,InputProcessor{

	private SpriteBatch pic = new SpriteBatch();
	private Venture g;
	private Grid currentGrid;
	private Player player;
	private int w =Gdx.graphics.getWidth(), h =Gdx.graphics.getHeight();
	
	
	public GameScreen(Venture g) {
		this.g=g;
		this.player = g.assets.player;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		currentGrid = g.assets.map.getCurrent();
		if(currentGrid.state==2){
			//render MotherShip
		}
		else if(currentGrid.state==1){
			//do nothing
		}
		else if(currentGrid.state==0){
			//gen enemies
			//render enemies
			
			if(currentGrid.enemies.size()==0){
				currentGrid.state=1;
				g.assets.save();
			}
		}
		
		pic.begin();
		//render Player
		pic.draw(player.getText(),(float)(player.position.x*w),(float)(player.position.y*h),(float)(player.bounds.width*w),(float)(player.bounds.height*w));
		
		/*
		for(int i = 0; i <player.guns.size(); i++){
			Gun temp = player.guns.get(i);
			if(temp.texture!=null){
				pic.draw(temp.texture,(float) ((player.position.x+(g.assets.stats[player.base].points[i].x*(g.assets.stats[player.base].scale)))*w), (float)((player.position.y+(g.assets.stats[player.base].points[i].y*(g.assets.stats[player.base].scale)))*h)-2, (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((player.bounds.width*g.assets.stats[player.base].scale)*w),(float) ((player.bounds.height*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
				}
		}
		
		for(int i = player.guns.size(); i <player.guns.size()+player.shields.size(); i++){
			Shield temp = player.shields.get(i-player.guns.size());
			if(temp.texture!=null){
				pic.draw(temp.texture,(float) ((player.position.x+(g.assets.stats[player.base].points[i].x*(g.assets.stats[player.base].scale)))*w), (float)((player.position.y+(g.assets.stats[player.base].points[i].y*(g.assets.stats[player.base].scale)))*h)-2, (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((player.bounds.width*g.assets.stats[player.base].scale)*w),(float) ((player.bounds.height*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
			}
		}
		
		for(int i = (player.guns.size()+player.shields.size()); i <(player.guns.size()+player.shields.size())+player.thrusters.size(); i++){
			Thruster temp = player.thrusters.get(i-(player.guns.size()+player.shields.size()));
			if(temp.texture!=null){
				pic.draw(temp.texture,(float) ((player.position.x+(g.assets.stats[player.base].points[i].x*(g.assets.stats[player.base].scale)))*w), (float)((player.position.y+(g.assets.stats[player.base].points[i].y*(g.assets.stats[player.base].scale)))*h)-2, (float)(((player.bounds.width*g.assets.stats[player.base].scale)*w)/2), (float)(((player.bounds.height*g.assets.stats[player.base].scale)*w)/2), (float)((player.bounds.width*g.assets.stats[player.base].scale)*w),(float) ((player.bounds.height*g.assets.stats[player.base].scale)*w), 1f, 1f, player.rotation);
			}
		}
		*/
		for(int i = 0; i < player.guns.size();i++){
			Gun temp = player.guns.get(i);
			if(temp.texture!=null){
				pic.draw(temp.texture,(float)(temp.position.x*w),(float)(temp.position.y*h),(float)((temp.bounds.width/2)*w),(float)((temp.bounds.height/2)*w),(float)(temp.bounds.width*w),(float)(temp.bounds.height*w),1f,1f,player.rotation);
			}
		}
		for(int i = player.guns.size(); i <player.guns.size()+player.shields.size(); i++){
			Shield temp = player.shields.get(i-player.guns.size());
			if(temp.texture!=null){
				pic.draw(temp.texture,(float)(temp.position.x*w),(float)(temp.position.y*h),(float)((temp.bounds.width/2)*w),(float)((temp.bounds.height/2)*w),(float)(temp.bounds.width*w),(float)(temp.bounds.height*w),1f,1f,player.rotation);
			}
		}
		
		for(int i = (player.guns.size()+player.shields.size()); i <(player.guns.size()+player.shields.size())+player.thrusters.size(); i++){
			Thruster temp = player.thrusters.get(i-(player.guns.size()+player.shields.size()));
			if(temp.texture!=null){
				pic.draw(temp.texture,(float)(temp.position.x*w),(float)(temp.position.y*h),(float)((temp.bounds.width/2)*w),(float)((temp.bounds.height/2)*w),(float)(temp.bounds.width*w),(float)(temp.bounds.height*w),1f,1f,player.rotation);
			}
		}
		//end render Player
		
		//render joysticks
		pic.draw(g.assets.jBase,(float)(g.assets.move.pos.x*w),(float)(g.assets.move.pos.y*h),(float)(g.assets.move.bounds.width*w),(float)(g.assets.move.bounds.height*w));
		pic.draw(g.assets.jTop,(float)(g.assets.move.top.x*w),(float)(g.assets.move.top.y*h),(float)(g.assets.move.topBounds.width*w),(float)(g.assets.move.topBounds.height*w));
		
		
		pic.draw(g.assets.jBase,(float)(g.assets.aim.pos.x*w),(float)(g.assets.aim.pos.y*h),(float)(g.assets.aim.bounds.width*w),(float)(g.assets.aim.bounds.height*w));
		pic.draw(g.assets.jTop,(float)(g.assets.aim.top.x*w),(float)(g.assets.aim.top.y*h),(float)(g.assets.aim.topBounds.width*w),(float)(g.assets.aim.topBounds.height*w));

		//end render joysticks
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
		screenY = h-screenY;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = h-screenY;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenY = h-screenY;
		return true;
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
