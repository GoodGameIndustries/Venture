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
	
	public void move(int x,int y){
		if(x>map.length||x<0){
			addX(x);
		}
		if(y>map[0].length||y<0){
			addY(y);
		}
		this.x=x;
		this.y=y;
		
	}

	private void addY(int y2) {
	Grid[][] newMap = null;
		if(y2<0){
			newMap = new Grid[map.length][map[0].length+Math.abs(y2)];
			for(int i = 0; i<newMap.length;i++){
				for(int j = y2; j<newMap.length;j++){
					newMap[i][j]=map[i][j-Math.abs(y2)];
				}
			}
		}
		
		
		//fill nulls
		for(int i =0; i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				if(newMap[i][j]==null){newMap[i][j]=new Grid(this,0);}
			}
		}
		//end fill nulls
	}

	private void addX(int x2) {
		// TODO Auto-generated method stub
		
	}
}
