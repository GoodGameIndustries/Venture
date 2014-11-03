/**
 * 
 */
package com.GGI.Venture;

import java.awt.Point;
import java.util.ArrayList;

import com.GGI.GameOBJ.Enemy;
import com.GGI.GameOBJ.Player;
import com.GGI.GameOBJ.ShipStat;
import com.GGI.Map.Map;
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
	public ArrayList<Texture> bases = new ArrayList<Texture>();
	public TextureRegion[][] regions;
	public Venture g;
	
	public Texture black;
	public Texture concrete;
	public Texture toolbarBG;
	public Texture title;
	
	public TextureRegion scoutGun;
	public TextureRegion snipeGun;
	public TextureRegion tankGun;
	public TextureRegion scoutShield;
	public TextureRegion snipeShield;
	public TextureRegion tankShield;
	public TextureRegion scoutThrust;
	public TextureRegion snipeThrust;
	public TextureRegion tankThrust;
	
	public ShipStat[] stats;
	
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
		//base pictures
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
		//end base pictures
		
		//base stats
		stats = new ShipStat[bases.size()];
		stats[0] =new ShipStat(new Vector2[]{new Vector2(.003f,.034f),new Vector2(.058f,.034f),new Vector2(.030f,.084f),new Vector2(.030f,-.02f)},100,.4f,.04f);
		stats[9] =new ShipStat(new Vector2[]{new Vector2(.2f,.3f)},100,.4f,.09f);
		//end base stats
		
		//attatchments
		scoutGun = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/SMG.png")));
		snipeGun = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/SNIPE.png")));
		tankGun = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/TANK.png")));
		
		scoutShield = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/ScoutShield.png")));
		snipeShield = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/SnipeShield.png")));
		tankShield = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/TankShield.png")));
		
		scoutThrust = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/STEER.png")));
		snipeThrust = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/SPEED.png")));
		tankThrust = new TextureRegion(new Texture(Gdx.files.internal("Attatchments/ACCEL.png")));
		//end attatchments
		
		//UI
		black = new Texture(Gdx.files.internal("UI/black.png"));
		concrete = new Texture(Gdx.files.internal("UI/Concrete.png"));
		toolbarBG = new Texture(Gdx.files.internal("UI/ToolbarBG.png"));
		title = new Texture(Gdx.files.internal("UI/Title.png"));
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
