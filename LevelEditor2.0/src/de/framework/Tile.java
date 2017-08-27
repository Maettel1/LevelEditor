package de.framework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Area;

public class Tile extends GameObject{

	
	private Image image;
	private TileSelection tile;
	
	public Tile(double x, double y, TileSelection tile, boolean square){
		poly = new Polygon();
		
		poly.addPoint(0, 0);
		poly.addPoint(0, Options.tileSize);
		poly.addPoint(Options.tileSize, Options.tileSize);
		poly.addPoint(Options.tileSize, 0);
		
		int deltaX, deltaY;
		deltaX = (int) (Math.floor(x/Options.tileSize)*Options.tileSize);
		deltaY = (int) (Math.floor(y/Options.tileSize)*Options.tileSize);
		
		this.x = deltaX;
		this.y = deltaY;
		
		poly.translate(deltaX, deltaY);
		createHitBox();
		
		this.tile = tile;
		
		image = getImageNumber();
	}
	
	private Image getImageNumber(){
		//TODO after collision get the real number
		return tile.getImage(44);
	}
	
	private void createHitBox(){
		hitBox = new Area(poly);
	}
	
	public Area getHitBox(){
		return hitBox;
	}
	
	@Override
	public void draw(Graphics g){
		g.drawImage(tile.getThumbnail(), x, y, null);
		g.drawImage(image, x, y, null);
	}
}
