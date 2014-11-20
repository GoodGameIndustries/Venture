/**
 * 
 */
package com.GGI.Screens;

import java.util.HashMap;
import java.util.Map;

import com.GGI.GameOBJ.Bullet;
import com.GGI.GameOBJ.Enemy;
import com.GGI.GameOBJ.Player;
import com.GGI.Map.Grid;
import com.GGI.UI.Button;
import com.GGI.UI.Toolbar;
import com.GGI.Venture.Venture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	public int x,y;
	private Toolbar toolbar;
	private BitmapFont fnt = new BitmapFont();
	private Button pause;
	
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
		toolbar=new Toolbar(g.assets,this);
		pause = new Button("UI/PauseUp.png","UI/PauseDown.png");
	}

	@Override
	public void render(float delta) {
		if(player.currentHealth<player.maxHealth){
			player.currentHealth+=(.01)*player.base;
			}
		Gdx.gl.glClearColor(0f, 0f, 0f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		player.move(delta);
		
	if(player.currentHealth<=0){
		g.assets.money-=.05f*g.assets.money;
		currentGrid=g.assets.map.home;
		g.assets.map.x=g.assets.map.hX;
		g.assets.map.y=g.assets.map.hY;
		g.assets.bullets.clear();
		player.currentHealth=player.maxHealth;
		player.play();
		g.assets.save();
	}
		
		
	
		if(player.reload>0){
			player.reload--;
			
		}
		if(player.reload<0){player.reload=0;}
		currentGrid = g.assets.map.getCurrent();
		x=g.assets.map.x;
		y=g.assets.map.y;
		
		if((player.position.x*w)<0){g.assets.map.move(x-1, y);player.position.x=currentGrid.bounds.width-.2f;}
		else if((player.position.x+player.bounds.width)*w>currentGrid.bounds.width*w){g.assets.map.move(x+1, y);player.position.x=.2f;}
		else if(player.position.y*h<0){g.assets.map.move(x, y-1);player.position.y=currentGrid.bounds.height-.2f;}
		else if((player.position.y*h)+(player.bounds.height*w)>currentGrid.bounds.height*h){g.assets.map.move(x, y+1);player.position.y=.2f;}
		pic.begin();

		pic.draw(g.assets.stars,(float)(currentGrid.position.x*w),(float)(currentGrid.position.y*h),(float)(currentGrid.bounds.width*w),(float)(currentGrid.bounds.height*h));
		
		pic.draw(g.assets.grid,(float)(currentGrid.position.x*w),(float)(currentGrid.position.y*h),(float)(currentGrid.bounds.width*w),(float)(currentGrid.bounds.height*h));
		
		
		currentGrid.position.x = -(player.position.x-.5f);
		currentGrid.position.y = -(player.position.y-.5f);
		currentGrid.boundsCheck();
		
		if(pre/*g.assets.move.inBounds(screenX,screenY)*/){
			g.assets.move.move(touches.get(moveP).touchX, touches.get(moveP).touchY);
		}
		if(prea/*g.assets.aim.inBounds(screenX,screenY)*/){
			g.assets.aim.move(touches.get(aimP).touchX, touches.get(aimP).touchY);
		}
		
		
		
		//render bullets
		
				for(Bullet temp:g.assets.bullets){
					//Bullet temp = g.assets.bullets.get(i);
				
					if(temp.team==0){pic.draw(new TextureRegion(g.assets.blueLaser), (temp.position.x+currentGrid.position.x)*w, (temp.position.y+currentGrid.position.y)*h, (temp.bounds.width/2)*w, (temp.bounds.height/2)*w, temp.bounds.width*w, temp.bounds.height*w, 1, 1, temp.rotation);}
					else if(temp.team==1){pic.draw(new TextureRegion(g.assets.redLaser), (temp.position.x+currentGrid.position.x)*w, (temp.position.y+currentGrid.position.y)*h, (temp.bounds.width/2)*w, (temp.bounds.height/2)*w, temp.bounds.width*w, temp.bounds.height*w, 1, 1, temp.rotation);}
					temp.move(delta);
				}
				
				//end render bullets
				
				if(currentGrid.state==2){
					pic.draw(g.assets.motherShip,(1.5f+currentGrid.position.x)*w,((2*h)-(w/2))+(currentGrid.position.y*h),1*w,1*w);
					pic.draw(g.assets.wrench, (1.95f+currentGrid.position.x)*w,(float) (((2.3*h)-((.1f*w)/2))+(currentGrid.position.y*h)+(.7*h)),.1f*w,.1f*w);
				}
				else if(currentGrid.state==1){
					//do nothing
				}
				else if(currentGrid.state==0){
					//gen enemies
					//render enemies
					
					for(int i = 0; i < currentGrid.enemies.size();i++){
						Enemy e = currentGrid.enemies.get(i);
						pic.draw(new TextureRegion(e.getText()),(float)((e.position.x+currentGrid.position.x)*w),(float)((e.position.y+currentGrid.position.y)*h),(float)((e.bounds.width/2)*w),(float)((e.bounds.height/2)*w),(float)(e.bounds.width*w),(float)(e.bounds.height*w),1f,1f,e.rotation);
						e.move(delta);
						e.reload--;
						if(e.currentHealth<=0){
							g.assets.currentXP+=(int)((Math.random()*e.base)*2)+1;
							g.assets.money+=(int)((Math.random()*e.base)*2)+1;
							if(g.assets.currentXP>=g.assets.neededXP){g.assets.lv++;g.assets.currentXP-=g.assets.neededXP;
							g.assets.neededXP+=(int)(.2*g.assets.neededXP);g.assets.save();
							}
							currentGrid.enemies.remove(e);}
						//System.out.println("Enemy Rendered");
						if((e.position.x*w)<0){e.position.x=currentGrid.bounds.width-.1f;}
						else if((e.position.x+e.bounds.width)*w>currentGrid.bounds.width*w){e.position.x=.1f;}
						else if(e.position.y*h<0){e.position.y=currentGrid.bounds.height-.1f;}
						else if((e.position.y*h)+(e.bounds.height*w)>currentGrid.bounds.height*h){e.position.y=.1f;}
					}
					
					//bullet hit detection
					for(int i = 0; i < g.assets.bullets.size();i++){
						Bullet bullet = g.assets.bullets.get(i);
						if(bullet.team==0){
							for(int j = 0; j <currentGrid.enemies.size();j++){
								Enemy e = currentGrid.enemies.get(j);
								float bX=bullet.position.x,bXW=bullet.position.x+bullet.bounds.width,bY=bullet.position.y,bYH=bullet.position.y+bullet.bounds.height,eX=e.position.x,eXW=e.position.x+e.bounds.width,eY=e.position.y,eYH=e.position.y+e.bounds.height;
								
								if((bX>eX&&bX<eXW)&&(bY>eY&&bY<eYH)){e.currentHealth--;g.assets.bullets.remove(bullet);}
								if((bXW>eX&&bXW<eXW)&&(bYH>eY&&bYH<eYH)){e.currentHealth--;g.assets.bullets.remove(bullet);}
								
							}
						}
						else{
							float bX=bullet.position.x,bXW=bullet.position.x+bullet.bounds.width,bY=bullet.position.y,bYH=bullet.position.y+bullet.bounds.height,eX=player.position.x,eXW=player.position.x+player.bounds.width,eY=player.position.y,eYH=player.position.y+player.bounds.height;
							
							if((bX>eX&&bX<eXW)&&(bY>eY&&bY<eYH)){player.currentHealth--;g.assets.bullets.remove(bullet);}
							if((bXW>eX&&bXW<eXW)&&(bYH>eY&&bYH<eYH)){player.currentHealth--;g.assets.bullets.remove(bullet);}
						}
					}
					//end bullet hit detection
					
					if(currentGrid.enemies.size()==0){
						currentGrid.state=1;
						g.assets.save();
					}
				}
		
		//render Player
		
		//pic.draw(player.getText(),(float)(player.position.x*w),(float)(player.position.y*h),(float)(player.bounds.width*w),(float)(player.bounds.height*w));
		pic.draw(new TextureRegion(player.getText()),(float)((player.position.x+currentGrid.position.x)*w),(float)((player.position.y+currentGrid.position.y)*h),(float)((player.bounds.width/2)*w),(float)((player.bounds.height/2)*w),(float)(player.bounds.width*w),(float)(player.bounds.height*w),1f,1f,player.rotation);
		
		//end render Player

		//render toolbars
		
		pic.draw(g.assets.black, 0*w, .9f*h,w,.1f*h);
		String stats =  "Lv: "+g.assets.lv+" XP: "+g.assets.currentXP + "/" + g.assets.neededXP+"("+(int)((g.assets.currentXP/g.assets.neededXP)*100)+"%)" +" $: " + g.assets.money;
		fnt.draw(pic,stats, (float)(.05*w), (float)(.96*h));
		pic.draw(pause.getState(),.78f*w,.92f*h,.2f*w,.06f*h);
		
		pic.draw(g.assets.black, 0f*w, 0f*h, w,.25f*h);
		pic.draw(g.assets.health, .25f*w,.15f*h,.5f*w*(player.currentHealth/player.maxHealth),.05f*h);
		pic.draw(g.assets.reload,.25f*w,.05f*h,.5f*w*(1-(player.reload/player.maxReload)),.05f*h); 
		
		//end render toolbars
		
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
		
		if(screenX>.78f*w&&screenX<(.78f+.2f)*w){
			if(screenY>.92f*h&&screenY<(.92f+.06f)*h){
				pause.press();
			}
		}
		
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
		pause.release();
		
		if(screenX>.78f*w&&screenX<(.78f+.2f)*w){
			if(screenY>.92f*h&&screenY<(.92f+.06f)*h){
				g.setScreen(new PauseScreen(g,this));
			}
		}
		
		if(screenX>(1.95f+currentGrid.position.x)*w&&screenX<((1.95f+currentGrid.position.x)*w)+(.1*w)){
			if(screenY>(float) (((2.3*h)-((.1f*w)/2))+(currentGrid.position.y*h)+(.7*h))&&screenY<(float) (((2.3*h)-((.1f*w)/2))+(currentGrid.position.y*h)+(.7*h))+(.1*w)){
				g.setScreen(new UpgradeScreen(g,this));
			}
		}
		
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
