package de.visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.framework.Animator;
import de.framework.Options;
import de.framework.Tile;
import de.visuals.listeners.EditorKeyListener;
import de.visuals.listeners.EditorMouseListener;
import de.visuals.listeners.EditorMouseWheelListener;

public class EditorView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8534499882922429063L;
	
	private double scale;
	private double offsetx, offsety;
	private EditorCamera cam;
	private Animator ani;
	private int placeState = 1;
	private ArrayList<Tile> tileList;

	public int getPlaceState() {
		return placeState;
	}

	public void setPlaceState(int i) {
		this.placeState = i;
	}

	public EditorView(TileSelector tileSelector){
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
		
		System.out.println(toString());
		
		if(Options.animation == true){
			ani = new Animator(this);
			ani.start();
		}
		
		this.setDoubleBuffered(true);
		
		tileList = new ArrayList<Tile>();
	}
	
	protected void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getSize().width, getSize().height);

		cam.scale(g2d, scale);
		cam.translate(g2d, offsetx, offsety);
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.red);
		g2d.fillOval(10, 10, 100, 100);
		
		@SuppressWarnings("unchecked")
		ArrayList<Tile> tileListCopy = (ArrayList<Tile>) tileList.clone();
		for(Tile i : tileListCopy)
			i.draw(g);
		
		paintChildren(g2d);
		
		g.dispose();
		g2d.dispose();
	}
	
	public void addOffset(double x, double y){
		offsetx += x;
		offsety += y;
	}
	
	public void addScale(double scale){
		this.scale += scale;
		if(this.scale <= 0.1) 
			this.scale = .1;
	}
	
	public void setScale(double scale){
		this.scale = scale;
	}
	
	public String toString(){
		return new String("Scale: " + scale + " Offset: " + offsetx + " " + offsety);
		
	}
	
	public int getTileSize(){
		return tileList.size();
	}
	
	public void addTile(Tile tile){
		for(Tile i : tileList){
			if(i.collision(tile)){
				return;
			}
		}
		tileList.add(tile);
	}
	
	public void removeTile(double x, double y){
		Tile tile = null;
		for(Tile i : tileList){
			tile = (Tile) i.collisionPoint(x, y);
			if(tile != null)
				break;
		}
		tileList.remove(tile);
	}

	public double getXOffset(){
		return offsetx;
	}
	
	public double getYOffset(){
		return offsety;
	}
	
	public double getScale(){
		return scale;
	}
	
	public void setOffset(double x, double y){
		offsetx = x;
		offsety = y;
	}
}

class EditorCamera {

	public void scale(Graphics2D g2d, double scale){
		g2d.scale(scale, scale);
	}
	
	public void translate(Graphics2D g2d, double offsetx, double offsety){
		g2d.translate(offsetx, offsety);
	}
}

