package de.framework;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;

import static de.framework.Options.*;

public class Room {

	private ArrayList<Tile> tiles;
	private Area hitBox;
	private Dimension dimension;
	public int x,y;
	
	public Room(double x, double y){
		tiles = new ArrayList<Tile>();
		
		this.x = Math.floorDiv((int) x, roomWidth*tileSize)*roomWidth*tileSize;
		this.y = Math.floorDiv((int) y, roomHeight*tileSize)*roomHeight*tileSize;
		
		dimension = new Dimension(roomWidth*tileSize,roomHeight*tileSize);
		hitBox = new Area(new Rectangle(this.x, this.y, dimension.width, dimension.height));
	}
	
	public void addTile(Tile tile){
		tiles.add(tile);
	}
	
	public void removeTile(Tile tile){
		tiles.remove(tile);
	}
	
	public Area getHitBox(){
		return hitBox;
	}
	
	public void updateDimension(){
		x = Math.floorDiv((int) x, roomWidth*tileSize)*roomWidth*tileSize;
		y = Math.floorDiv((int) y, roomHeight*tileSize)*roomHeight*tileSize;
		
		dimension = new Dimension(roomWidth*tileSize,roomHeight*tileSize);
		hitBox = new Area(new Rectangle(this.x, this.y, dimension.width, dimension.height));
	}
}
