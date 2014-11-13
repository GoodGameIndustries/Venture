/**
 * 
 */
package com.GGI.Screens;

import java.util.HashMap;
import java.util.Map;

import com.GGI.GameOBJ.Bullet;
import com.GGI.GameOBJ.Player;
import com.GGI.Map.Grid;
import com.GGI.Venture.Venture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	private boolean pre;
	private boolean prea;
	private int moveP;
	private int aimP;
	
	class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
    
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
	
	
	public GameScreen(Venture g) {
		this.g=g;
		this.player = g.assets.player;
		for(int i = 0; i < 5; i++){
            touches.put(i, new TouchInfo());
        }
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		player.move(delta);
		
		if(player.reload>0){
			player.reload--;
			System.out.println(player.reload);
		}
		
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
		
		currentGrid.position.x = -(player.position.x-.5f);
		currentGrid.position.y = -(player.position.y-.5f);
		currentGrid.boundsCheck();
		
		if(pre/*g.assets.move.inBounds(screenX,screenY)*/){
			g.assets.move.move(touches.get(moveP).touchX, touches.get(moveP).touchY);
		}
		if(prea/*g.assets.aim.inBounds(screenX,screenY)*/){
			g.assets.aim.move(touches.get(aimP).touchX, touches.get(aimP).touchY);
		}
		
		pic.begin();
		
		//render bullets
		
				for(Bullet temp:g.assets.bullets){
					//Bullet temp = g.assets.bullets.get(i);
				
					if(temp.team==0){pic.draw(new TextureRegion(g.assets.blueLaser), (temp.position.x+currentGrid.position.x)*w, (temp.position.y+currentGrid.position.y)*h, (temp.bounds.width/2)*w, (temp.bounds.height/2)*w, temp.bounds.width*w, temp.bounds.height*w, 1, 1, temp.rotation);}
					temp.move(delta);
				}
				
				//end render bullets
		
		//render Player
		pic.draw(g.assets.grid,(float)(currentGrid.position.x*w),(float)(currentGrid.position.y*h),(float)(currentGrid.bounds.width*w),(float)(currentGrid.bounds.height*h));
		//pic.draw(player.getText(),(float)(player.position.x*w),(float)(player.position.y*h),(float)(player.bounds.width*w),(float)(player.bounds.height*w));
		pic.draw(new TextureRegion(player.getText()),(float)((player.position.x+currentGrid.position.x)*w),(float)((player.position.y+currentGrid.position.y)*h),(float)((player.bounds.width/2)*w),(float)((player.bounds.height/2)*w),(float)(player.bounds.width*w),(float)(player.bounds.height*w),1f,1f,player.rotation);
		
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
		
		if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
           // System.out.println("Touched");
        }
		
		if(g.assets.move.inBounds(screenX,screenY)){
			//g.assets.move.move(screenX, screenY);
			pre=true;
			moveP=pointer;
		}
		if(g.assets.aim.inBounds(screenX,screenY)){
			//g.assets.aim.move(screenX, screenY);
			prea=true;
			aimP=pointer;
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = h-screenY;
		if(pointer < 5){
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
          //  System.out.println("Released");
        }
		
		g.assets.move.reset();
		g.assets.aim.reset();
		if(pointer==moveP){
		pre=false;
		}
		if(pointer==aimP){
		prea=false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenY = h-screenY;
		if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
          //  System.out.println("Dragged");
        }
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
