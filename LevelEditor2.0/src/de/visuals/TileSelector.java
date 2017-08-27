package de.visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.framework.Options;
import de.framework.TileSelection;

public class TileSelector extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224953310597476006L;

	private ArrayList<TileSelection> tiles;
	private int selection = 0;
	
	
	public TileSelector(){
		
		setPreferredSize(new Dimension(Options.tileSize*4,Options.tileSize*10));
		setMaximumSize(new Dimension(Options.tileSize*4,Options.tileSize*10));
		tiles = new ArrayList<TileSelection>();
		
		//TODO remove and make more flexible
		TileSelection testwall = new TileSelection(Options.tileSize, Options.tileSize, "SpritesheetWall.png");
		tiles.add(testwall);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, Options.tileSize*4, Options.tileSize*10);
		
		g2d.drawImage(tiles.get(0).getThumbnail(), 0, 0, null);
		g.setColor(Color.white);
		
		//columns
		for(int i = 0; i <= 4; i++){
			g.drawLine(i*Options.tileSize, 0, i*Options.tileSize, Options.tileSize*10);
		}
		//rows
		for(int i = 0; i <= 10; i++){
			g.drawLine(0, i*Options.tileSize, Options.tileSize*4, Options.tileSize*i);
		}
		g.dispose();
	}
	
	public TileSelection getSelected(){
		return tiles.get(selection);
	}
}
