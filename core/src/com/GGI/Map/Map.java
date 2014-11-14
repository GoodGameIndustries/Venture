/**
 * 
 */
package com.GGI.Map;

import com.GGI.Venture.Assets;

/**
 * @author Emmett Deen
 *
 */
public class Map {

	public Grid[][] map;
	public int x,y;
	private int col=0;
	private int hX,hY;
	private Grid home;
	public Assets g;
	
	public Map(String mapS,Assets g) {
		this.g=g;
		String[] breakDown = mapS.split(",");//sizex,sizey,xPos,yPos
		map = new Grid[Integer.parseInt(breakDown[0])][Integer.parseInt(breakDown[1])];
		x=Integer.parseInt(breakDown[2]);
		y=Integer.parseInt(breakDown[3]);
		
		for(int i = 4; i < breakDown.length;i++){
			if((i-4)>Integer.parseInt(breakDown[0])){col++;}
			map[i-4][col]=new Grid(this,Integer.parseInt(breakDown[i]));
			if(Integer.parseInt(breakDown[i])==2){hX=i-4;hY=col;}
		}
	}
	
	public Grid getCurrent(){
		//System.out.println("xLength: "+map.length+ " x: " +x);
		//System.out.println("yLength: "+map[0].length+ " y: " +y);
		return map[x][y];
	}
	
	public void move(int x,int y){
		if(x>map.length-1||x<0||y>map[0].length-1||y<0){
		//	System.out.println("addX");
			
			
			
			if(this.x!=x){
			this.x=x;
			addX(x);
			}
			if(this.y!=y){
			this.y=y;
			addY(y);}
			
		}
		
		
		else{
			this.x=x;
			this.y=y;
		}
		g.bullets.clear();
	}

	private void addY(int y2) {
	Grid[][] newMap = null;
		if(y2<0){
			y=0;
			newMap = new Grid[map.length][map[0].length+Math.abs(y2)];
			for(int i = 0; i<newMap.length;i++){
				for(int j = Math.abs(y2); j<newMap[0].length;j++){
					newMap[i][j]=map[i][j-Math.abs(y2)];
				}
			}
		}
		else{
			//y+=1;
			newMap = new Grid[map.length][map[0].length+1];
			for(int i = 0; i<map.length;i++){
				for(int j = 0; j<map[0].length;j++){
					newMap[i][j]=map[i][j];
				}
			}
		}
		
		//fill nulls
		for(int i =0; i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				
				if(newMap[i][j]==null){newMap[i][j]=new Grid(this,0);System.out.println("Null filled");}
			}
		}
		//end fill nulls
		map = newMap;
		
	}

	private void addX(int x2) {
		Grid[][] newMap = null;
		if(x2<0){
			x=0;
			newMap = new Grid[map.length+Math.abs(x2)][map[0].length];
			//System.out.println(map[0].length);
			for(int i = Math.abs(x2); i<newMap.length;i++){
				for(int j = 0; j<newMap[0].length;j++){
					//System.out.println(i);
					//System.out.println(j);
					//System.out.println(i-Math.abs(x2));
					newMap[i][j]=map[i-Math.abs(x2)][j];
				}
			}
		}
		else{
			//x+=1;
			newMap = new Grid[map.length+1][map[0].length];
			for(int i = 0; i<map.length;i++){
				for(int j = 0; j<map[0].length;j++){
					newMap[i][j]=map[i][j];
				}
			}
		}
		
		//fill nulls
		for(int i =0; i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				
				if(newMap[i][j]==null){newMap[i][j]=new Grid(this,0);System.out.println("Null filled");}
			}
		}
		//end fill nulls
		
		map=newMap;
		//x=x2;
	}

	public int distanceFromHome(Grid g) {
		// TODO Auto-generated method stub
		home=findHome();
		//int result =Math.abs(findX(home)-findX(g))+Math.abs(findY(home)-findY(g));
		int result =(int) (Math.abs(Math.round((double)(map.length/2)-x))+Math.abs(Math.round((double)(map[0].length/2)-y)));
		System.out.println("DFH: "+result);
		if(result==0){
			return 1;
		}
		return result;
	}

	private Grid findHome() {
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length;j++){
				if(map[i][j]!=null&&map[i][j].state==2){
					return map[i][j];
				}
			}
		}
		return null;
	}

	private int findX(Grid g) {
		for(int i = 0; i < map.length;i++){
			for(int j = 0;j<map[0].length;j++){
				if(map[i][j]==g){
					System.out.println(i);
					return i;
				}
			}
		}
		return -1;
	}
	private int findY(Grid g) {
		for(int i = 0; i < map.length;i++){
			for(int j = 0;j<map[0].length;j++){
				if(map[i][j]==g){
					System.out.println(j);
					return j;
				}
			}
		}
		return -1;
	}
}
