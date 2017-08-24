package de.framework;

import java.awt.Image;

public class Tile {

	private Image thumbnail;
	private SpriteSheet sheet;
	
	public Tile(int width, int height, String ressource){
		sheet = new SpriteSheet(width, height, ressource);
		
		thumbnail = sheet.getSprite(43);
	}
	
	public Image getThumbnail(){
		return thumbnail;
	}
}
