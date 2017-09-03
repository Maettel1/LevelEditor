package de.visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.framework.Animator;
import de.framework.Options;
import de.framework.Room;
import de.framework.Tile;
import de.visuals.listeners.EditorKeyListener;
import de.visuals.listeners.EditorMouseListener;
import de.visuals.listeners.EditorMouseWheelListener;

public class EditorView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8534499882922429063L;

	private double scale;
	private double offsetx, offsety;
	private EditorCamera cam;
	private Animator ani;
	private int placeState = 1;
	private ArrayList<Room> roomList;

	public int getPlaceState() {
		return placeState;
	}

	public void setPlaceState(int i) {
		this.placeState = i;
	}

	public EditorView(TileSelector tileSelector) {
		setFocusable(true);

		cam = new EditorCamera();
		scale = 1;
		offsetx = 0;
		offsety = 0;

		addKeyListener(new EditorKeyListener(this));
		addMouseWheelListener(new EditorMouseWheelListener(this));
		EditorMouseListener mouseInput = new EditorMouseListener(this, tileSelector);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);

		if (Options.animation == true) {
			ani = new Animator(this);
			ani.start();
		}

		this.setDoubleBuffered(true);

		roomList = new ArrayList<Room>();

		addRoom(new Room(0, 0));
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getSize().width, getSize().height);

		cam.scale(g2d, scale);
		cam.translate(g2d, offsetx, offsety);

		@SuppressWarnings("unchecked")
		ArrayList<Room> roomListCopy = (ArrayList<Room>) roomList.clone();
		for (Room i : roomListCopy)
			i.draw(g);

		paintChildren(g2d);

		g.dispose();
		g2d.dispose();
	}

	public void addOffset(double x, double y) {
		offsetx += x;
		offsety += y;
	}

	public void addScale(double scale) {
		this.scale += scale;
		if (this.scale < 0.1)
			this.scale = .1;

		if (this.scale > 2)
			this.scale = 2;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		return new String("Scale: " + scale + " Offset: " + offsetx + " " + offsety);

	}

	public int getRoomAmount() {
		return roomList.size();
	}

	public void addRoom(Room room) {
		for (Room i : roomList) {
			if (i.collision(room))
				return;
		}
		roomList.add(room);
	}
	
	public Room selectRoom(double x, double y){
		Room r = null;
		for(Room r1 : roomList){
			if(r1.collisionPoint(x, y) != null)
				r = r1;
		}
		for(Room r1 : roomList){
			if(r1 != r)
				r1.OnMouseUnclick();
		}
		return r;
	}

	public void removeRoom(Room room) {
		roomList.remove(room);
	}

	public int getTileAmount() {
		int i = 0;
		for (Room r : roomList)
			i += r.getTileAmount();
		return i;
	}

	public void addTile(Tile tile) {
		for (Room r : roomList) {
			if (r.collision(tile)) {
				r.addTile(tile);
				break;
			}
		}
	}

	public void removeTile(double x, double y) {
		Room room = null;
		for (Room r : roomList) {
			room = (Room) r.collisionPoint(x, y);
			if (room != null)
				room.removeTile(x, y);
			break;
		}
	}

	public double getXOffset() {
		return offsetx;
	}

	public double getYOffset() {
		return offsety;
	}

	public double getScale() {
		return scale;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
		for (Room r : roomList) {
			r.createHitBox();
		}
		for (Room r : roomList) {
			r.OnLoad();
		}
		repaint();
	}

	public void setOffset(double x, double y) {
		offsetx = x;
		offsety = y;
	}
}

class EditorCamera {

	public void scale(Graphics2D g2d, double scale) {
		g2d.scale(scale, scale);
	}

	public void translate(Graphics2D g2d, double offsetx, double offsety) {
		g2d.translate(offsetx, offsety);
	}
}
