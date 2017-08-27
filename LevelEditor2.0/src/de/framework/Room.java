package de.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;

import static de.framework.Options.*;

public class Room extends GameObject {

	private ArrayList<Tile> tileList;
	private Dimension dimension;

	public Room(double x, double y) {
		tileList = new ArrayList<Tile>();

		this.x = Math.floorDiv((int) x, roomWidth * tileSize) * roomWidth * tileSize;
		this.y = Math.floorDiv((int) y, roomHeight * tileSize) * roomHeight * tileSize;

		dimension = new Dimension(roomWidth * tileSize, roomHeight * tileSize);
		hitBox = new Area(new Rectangle(this.x, this.y, dimension.width, dimension.height));
	}

	public int getTileAmount() {
		return tileList.size();
	}

	public void addTile(Tile tile) {
		for (Tile i : tileList) {
			if (i.collision(tile)) {
				return;
			}
		}
		tileList.add(tile);
		tile.setRoom(this);
	}

	public void removeTile(double x, double y) {
		Tile tile = null;
		for (Tile i : tileList) {
			tile = (Tile) i.collisionPoint(x, y);
			if (tile != null)
				break;
		}
		tileList.remove(tile);
		if (tile != null)
			tile.update();
	}

	public void updateDimension() {
		x = Math.floorDiv(x, roomWidth * tileSize) * roomWidth * tileSize;
		y = Math.floorDiv(y, roomHeight * tileSize) * roomHeight * tileSize;

		dimension = new Dimension(roomWidth * tileSize, roomHeight * tileSize);
		hitBox = new Area(new Rectangle(this.x, this.y, dimension.width, dimension.height));
	}

	public ArrayList<Tile> getTileList() {
		return tileList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x + 1, y + 1, dimension.width - 2, dimension.height - 2);
		g.setColor(Color.white);
		g.drawRect(x, y, dimension.width, dimension.height);
		g.setColor(Color.gray);
		g.drawRect(x - 1, y - 1, dimension.width + 2, dimension.height + 2);

		for (Tile t : (ArrayList<Tile>) tileList.clone())
			t.draw(g);
	}
}
