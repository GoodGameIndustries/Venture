/**
 * 
 */
package com.GGI.Map;

/**
 * @author Emmett Deen
 *
 */
public class Map {

	public Grid[][] map;
	public int x,y;
	private int col=0;
	
	public Map(String mapS) {
		String[] breakDown = mapS.split(",");//sizex,sizey,xPos,yPos
		map = new Grid[Integer.parseInt(breakDown[0])][Integer.parseInt(breakDown[1])];
		x=Integer.parseInt(breakDown[2]);
		y=Integer.parseInt(breakDown[3]);
		
		for(int i = 4; i < breakDown.length;i++){
			if((i-4)>Integer.parseInt(breakDown[0])){col++;}
			map[i-4][col]=new Grid(this,Integer.parseInt(breakDown[i]));
		}
	}
	
	public Grid getCurrent(){
		return map[x][y];
	}
}
