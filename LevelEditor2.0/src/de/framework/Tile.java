package de.framework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Tile extends GameObject{

	
	private Image image;
	private TileSelection tile;
	private Room room;
	
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
		
		image = tile.getImage(43);
	}
	
	public void setRoom(Room room){
		this.room = room;
		getImageNumber();
		update();
	}
	
	public void update(){
		@SuppressWarnings("unchecked")
		ArrayList<GameObject> list = (ArrayList<GameObject>) room.getTileList().clone();
		
		Tile temp; 
		temp = (Tile) collision(x - 32, y, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x - 32, y - 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x, y - 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x + 32, y - 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x + 32, y, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x + 32, y + 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x, y + 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
		temp = (Tile) collision(x - 32, y + 32, Tile.class, list);
		if (temp != null)
			temp.getImageNumber();
	}
	
	private void getImageNumber(){
		int tile = 43;
		
		boolean left, up, right, down;
		boolean leftup, leftdown, rightup, rightdown;
		
		@SuppressWarnings("unchecked")
		ArrayList<GameObject> list = (ArrayList<GameObject>) room.getTileList().clone();

		if (collision(x - Options.tileSize, y, Tile.class, list) == null)
			left = false;
		else
			left = true;
		if (collision(x - Options.tileSize, y - Options.tileSize, Tile.class, list) == null)
			leftup = false;
		else
			leftup = true;
		if (collision(x, y - Options.tileSize, Tile.class, list) == null)
			up = false;
		else
			up = true;
		if (collision(x + Options.tileSize, y - Options.tileSize, Tile.class, list) == null)
			rightup = false;
		else
			rightup = true;
		if (collision(x + Options.tileSize, y, Tile.class, list) == null)
			right = false;
		else
			right = true;
		if (collision(x + Options.tileSize, y + Options.tileSize, Tile.class, list) == null)
			rightdown = false;
		else
			rightdown = true;
		if (collision(x, y + Options.tileSize, Tile.class, list) == null)
			down = false;
		else
			down = true;
		if (collision(x - Options.tileSize, y + Options.tileSize, Tile.class, list) == null)
			leftdown = false;
		else
			leftdown = true;

		tile = 44;

		if (up) {
			tile = 0;
			if (right) {
				tile = 4;
				if (down) {
					tile = 12;
					if (left) {
						tile = 28;
						if (rightup) {
							tile = 29;
							if (rightdown) {
								tile = 33;
								if (leftdown) {
									tile = 39;
									if (leftup)
										tile = 43;
								} else if (leftup)
									tile = 40;
							} else if (leftdown) {
								tile = 37;
								if (leftup)
									tile = 41;
							} else if (leftup)
								tile = 36;
						} else if (rightdown) {
							tile = 30;
							if (leftdown) {
								tile = 34;
								if (leftup)
									tile = 42;
							} else if (leftup)
								tile = 38;
						} else if (leftdown) {
							tile = 31;
							if (leftup)
								tile = 35;
						} else if (leftup)
							tile = 32;
					} else if (rightup) {
						tile = 16;
						if (rightdown)
							tile = 18;
					} else if (rightdown)
						tile = 17;
				} else if (left) {
					tile = 15;
					if (rightup) {
						tile = 25;
						if (leftup)
							tile = 27;
					} else if (leftup)
						tile = 26;
				} else if (rightup)
					tile = 8;
			} else if (down) {
				tile = 45;
				if (left) {
					tile = 14;
					if (leftdown) {
						tile = 22;
						if (leftup)
							tile = 24;
					} else if (leftup)
						tile = 23;
				}
			} else if (left) {
				tile = 7;
				if (leftup)
					tile = 11;
			}
		} else if (right) {
			tile = 1;
			if (down) {
				tile = 5;
				if (left) {
					tile = 13;
					if (rightdown) {
						tile = 19;
						if (leftdown)
							tile = 21;
					} else if (leftdown)
						tile = 20;
				} else if (rightdown)
					tile = 9;
			} else if (left) {
				tile = 46;
			}
		} else if (down) {
			tile = 2;
			if (left) {
				tile = 6;
				if (leftdown)
					tile = 10;
			}
		} else if (left) {
			tile = 3;
		}
		
		image = this.tile.getImage(tile);
	}
	
	private void createHitBox(){
		hitBox = new Area(poly);
	}
	
	@Override
	public void draw(Graphics g){
		g.drawImage(tile.getThumbnail(), x, y, null);
		g.drawImage(image, x, y, null);
	}
}
