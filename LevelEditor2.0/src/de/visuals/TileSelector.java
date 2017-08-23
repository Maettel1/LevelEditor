package de.visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.framework.Options;
import de.framework.Tile;

public class TileSelector extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224953310597476006L;

	private ArrayList<Tile> tiles;
	
	public TileSelector(){
		
		this.setPreferredSize(new Dimension(Options.tileSize*4,Options.tileSize*10));
		this.setMaximumSize(new Dimension(Options.tileSize*4,Options.tileSize*10));
		this.setMinimumSize(new Dimension(Options.tileSize*4+2,Options.tileSize*10));
		tiles = new ArrayList<Tile>();
		
		Tile testwall = new Tile(Options.tileSize, Options.tileSize, "SpritesheetWall.png");
		tiles.add(testwall);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.drawImage(tiles.get(0).getThumbnail(), 0, 0, null);
		g.setColor(Color.white);
		
		//columns
		for(int i = 1; i <= 4; i++){
			g.drawLine(i*32, 0, i*32, getHeight());
		}
		//rows
		for(int i = 1; i <= 10; i++){
			g.drawLine(0, i*32, getWidth(), 32*i);
		}
		
	}
	
}
