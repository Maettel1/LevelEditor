package de.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;

import static de.framework.Options.*;

public class Room extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268873046380546182L;
	private static transient SpriteSheet UI = new SpriteSheet(20, 20, 
			"/de/visuals/menuImages/RoomUI.png");
	private ArrayList<Tile> tileList;
	private Dimension dimension;
	private transient boolean selected;  

	public Room(double x, double y) {
		tileList = new ArrayList<Tile>();

		this.x = Math.floorDiv((int) x, roomWidth * tileSize) * roomWidth * tileSize;
		this.y = Math.floorDiv((int) y, roomHeight * tileSize) * roomHeight * tileSize;

		dimension = new Dimension(roomWidth * tileSize, roomHeight * tileSize);
		createHitBox();
	}
	
	public void createHitBox(){
		poly = new Polygon();
		poly.addPoint(0, 0);
		poly.addPoint(0, dimension.height);
		poly.addPoint(dimension.width, dimension.height);
		poly.addPoint(dimension.width, 0);

		poly.translate(this.x, this.y);
		super.createHitBox();
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

	public void update() {
		createHitBox();
		for (Tile t : tileList) {
			t.createHitBox();
		}
		for (Tile t : tileList) {
			t.update();
		}
	}

	public ArrayList<Tile> getTileList() {
		return tileList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void draw(Graphics g) {

		for (Tile t : (ArrayList<Tile>) tileList.clone())
			t.draw(g);

		if (selected) {
			drawSelectedBorder(g);
			drawUI(g);
		}
		else
			drawBorder(g);
	}

	private void drawBorder(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x + 1, y + 1, dimension.width - 2, dimension.height - 2);
		g.setColor(Color.white);
		g.drawRect(x, y, dimension.width, dimension.height);
		g.setColor(Color.gray);
		g.drawRect(x - 1, y - 1, dimension.width + 2, dimension.height + 2);
	}

	private void drawSelectedBorder(Graphics g) {
		g.setColor(new Color(255,215,0));
		g.drawRect(x + 1, y + 1, dimension.width - 2, dimension.height - 2);
		g.setColor(new Color(218,165,32));
		g.drawRect(x, y, dimension.width, dimension.height);
		g.setColor(new Color(255,215,0));
		g.drawRect(x - 1, y - 1, dimension.width + 2, dimension.height + 2);

	}

	private void drawUI(Graphics g) {
		//enlarges
		g.drawImage(UI.getSprite(8), x+(int) dimension.getWidth() / 2-10, y, null);
		g.drawImage(UI.getSprite(0), x+(int) dimension.getWidth() / 2-10, y, null);

		g.drawImage(UI.getSprite(8), x+(int) dimension.getWidth() / 2-10, y+(int) dimension.getHeight()-20, null);
		g.drawImage(UI.getSprite(1), x+(int) dimension.getWidth() / 2-10, y+(int) dimension.getHeight()-20, null);

		g.drawImage(UI.getSprite(8), x, y+(int) dimension.getHeight()/2-10, null);
		g.drawImage(UI.getSprite(2), x, y+(int) dimension.getHeight()/2-10, null);

		g.drawImage(UI.getSprite(8), x+(int) dimension.getWidth()-20, y+(int) dimension.getHeight()/2-10, null);
		g.drawImage(UI.getSprite(3), x+(int) dimension.getWidth()-20, y+(int) dimension.getHeight()/2-10, null);
		
	}

	public void OnMouseClick() {
		/*if(selected)
			enlargeTop();*/
		selected = true;
	}

	public void OnMouseUnclick() {
		selected = false;
	}
	
	//#################
	//#         enlarge
	//#################
	
	public void enlargeTop(){
		dimension.setSize(dimension.getWidth(), dimension.getHeight()+roomHeight * tileSize);
		y -= roomHeight * tileSize;
		createHitBox();
	}
	
	public void enlargeBottom(){
		dimension.setSize(dimension.getWidth(), dimension.getHeight()+roomHeight * tileSize);
		createHitBox();
	}

	public void enlargeLeft(){
		dimension.setSize(dimension.getWidth()+roomWidth * tileSize, dimension.getHeight());
		x -= roomWidth * tileSize;
		createHitBox();
	}
	
	public void enlargeRight(){
		dimension.setSize(dimension.getWidth()+roomWidth * tileSize, dimension.getHeight());
		createHitBox();
	}

	//#################
	//#          shrink
	//#################
	
	public void shrinkTop(){
		dimension.setSize(dimension.getWidth(), dimension.getHeight()-roomHeight * tileSize);
		y += roomHeight * tileSize;
		createHitBox();
	}
	
	public void shrinkBottom(){
		dimension.setSize(dimension.getWidth(), dimension.getHeight()-roomHeight * tileSize);
		createHitBox();
	}

	public void shrinkLeft(){
		dimension.setSize(dimension.getWidth()-roomWidth * tileSize, dimension.getHeight());
		x += roomWidth * tileSize;
		createHitBox();
	}
	
	public void shrinkRight(){
		dimension.setSize(dimension.getWidth()-roomWidth * tileSize, dimension.getHeight());
		createHitBox();
	}
	
	
	public boolean isSelected() {
		return selected;
	}

	public void OnLoad() {
		createHitBox();
		for (Tile t : tileList) {
			t.createHitBox();
		}
		for (Tile t : tileList) {
			t.OnLoad();
		}

	}

}
