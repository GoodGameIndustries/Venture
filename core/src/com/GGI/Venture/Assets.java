/**
 * 
 */
package com.GGI.Venture;

import java.util.ArrayList;

import com.GGI.GameOBJ.Enemy;
import com.GGI.GameOBJ.Player;
import com.GGI.Map.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Emmett Deen
 *
 */
public class Assets {

	public int lv,currentXP,neededXP,money;
	public Player player;
	public Enemy[] enemies;
	public Map map;
	public ArrayList<Texture> bases = new ArrayList<Texture>();
	public TextureRegion[][] regions;
	public Venture g;
	
	public Texture black;
	public Texture concrete;
	public Texture toolbarBG;
	public Texture title;
	
	public Assets(Venture g){
		this.g=g;
		setup();
		load();
	}

	private void setup() {
		if(!Gdx.files.local("Map.txt").exists()){
			FileHandle from = Gdx.files.internal("Map.txt");
			from.copyTo(Gdx.files.local("Map.txt"));
		}
		
		if(!Gdx.files.local("Ship.txt").exists()){
			FileHandle from = Gdx.files.internal("Ship.txt");
			from.copyTo(Gdx.files.local("Ship.txt"));
		}
		
		if(!Gdx.files.local("Stats.txt").exists()){
			FileHandle from = Gdx.files.internal("Stats.txt");
			from.copyTo(Gdx.files.local("Stats.txt"));
		}
		
	}

	private void load() {
		bases.add(new Texture(Gdx.files.internal("Bases/Starter.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Scout1.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Scout2.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Scout3.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Snipe1.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Snipe2.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Snipe3.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Tank1.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Tank2.png")));
		bases.add(new Texture(Gdx.files.internal("Bases/Tank3.png")));
		
		black = new Texture(Gdx.files.internal("UI/black.png"));
		concrete = new Texture(Gdx.files.internal("UI/Concrete.png"));
		toolbarBG = new Texture(Gdx.files.internal("UI/ToolbarBG.png"));
		title = new Texture(Gdx.files.internal("UI/Title.png"));
		
		
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
		player = new Player(shipS,this);
		
		FileHandle statsFile = Gdx.files.local("Stats.txt");
		String[] stats = statsFile.readString().split(",");
		lv=Integer.parseInt(stats[0]);
		currentXP=Integer.parseInt(stats[1]);
		neededXP=Integer.parseInt(stats[2]);
		money=Integer.parseInt(stats[3]);
		
		
		
		
	}
	
	public void save(){
		
	}
	
}
