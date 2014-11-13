/**
 * 
 */
package com.GGI.Venture;

import java.util.ArrayList;

import com.GGI.GameOBJ.Bullet;
import com.GGI.GameOBJ.Enemy;
import com.GGI.GameOBJ.Player;
import com.GGI.GameOBJ.ShipStat;
import com.GGI.Map.Map;
import com.GGI.UI.Joystick;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class Assets {

	public int lv,currentXP,neededXP,money;
	public Player player;
	public Enemy[] enemies;
	public Map map;
	public ArrayList<TextureRegion> bases = new ArrayList<TextureRegion>();
	public ArrayList<Texture> ranks = new ArrayList<Texture>();
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Venture g;
	
	public Texture black;
	public Texture concrete;
	public Texture toolbarBG;
	public Texture title;
	public Texture jBase;
	public Texture jTop;
	public Texture grid;
	public Texture star;
	public Texture blueLaser;
	public Texture redLaser;
	
	
	
	public Joystick move =new Joystick(new Vector2(.1f,.05f));
	public Joystick aim =new Joystick(new Vector2(.8f,.05f));
	
	public ShipStat[] stats;
	
	public Assets(Venture g){
		this.g=g;
		setup();
		load();
	}

	private void setup() {
		if(!Gdx.files.local("Map.txt").exists()){
			FileHandle fromM = Gdx.files.internal("Map.txt");
			fromM.copyTo(Gdx.files.local("Map.txt"));
		}
		
		if(!Gdx.files.local("Ship.txt").exists()){
			FileHandle fromS = Gdx.files.internal("Ship.txt");
			fromS.copyTo(Gdx.files.local("Ship.txt"));
		}
		
		if(!Gdx.files.local("Stats.txt").exists()){
			FileHandle fromSt = Gdx.files.internal("Stats.txt");
			fromSt.copyTo(Gdx.files.local("Stats.txt"));
		}
		
	}

	private void load() {
		//base pictures
		
		for(int i =1; i <= 15; i++){
			bases.add(new TextureRegion(new Texture(Gdx.files.internal("Ships/Ship" + i +".png"))));
		}
		blueLaser=new Texture(Gdx.files.internal("Ships/BlueLaser.png"));
		redLaser=new Texture(Gdx.files.internal("Ships/RedLaser.png"));
		//end base pictures
		//rank pictures
		for(int i = 0; i < 15;i++){
			ranks.add(new Texture(Gdx.files.internal("Ranks/Rank" + i +".png")));
		}
		//end rank pictures
		
		star = new Texture(Gdx.files.internal("Ranks/Star.png"));
		
		
		//UI
		black = new Texture(Gdx.files.internal("UI/black.png"));
		concrete = new Texture(Gdx.files.internal("UI/Concrete.png"));
		toolbarBG = new Texture(Gdx.files.internal("UI/ToolbarBG.png"));
		title = new Texture(Gdx.files.internal("UI/Title.png"));
		jBase = new Texture(Gdx.files.internal("UI/JoystickBase.png"));
		jTop = new Texture(Gdx.files.internal("UI/JoystickTop.png"));
		grid = new Texture(Gdx.files.internal("UI/GridOutline.png"));
		//end UI
		
		FileHandle mapFile = Gdx.files.local("Map.txt");
		String mapS = mapFile.readString();
		map = new Map(mapS);
		
		/*
		FileHandle enemyFile = Gdx.files.local("Enemies.txt");
		String[] enemyS = enemyFile.readString().split(":");
		enemies = new Enemy[enemyS.length];
		for(int i = 0; i < enemyS.length; i++){
			enemies[i]=new Enemy(enemyS[i]);
		}
		*/
		
		FileHandle shipFile = Gdx.files.local("Ship.txt");
		String shipS = shipFile.readString();
		//TESTING
		shipS = ""+(60/5);
		//END TESTING
		player = new Player(shipS,this);
		
		
		FileHandle statsFile = Gdx.files.local("Stats.txt");
		String[] stats = statsFile.readString().split(",");
		lv=Integer.parseInt(stats[0]);
		currentXP=Integer.parseInt(stats[1]);
		neededXP=Integer.parseInt(stats[2]);
		money=Integer.parseInt(stats[3]);
		
		lv=60;
		
		
	}
	
	public void save(){
		
	}
	
}
