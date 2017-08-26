package de.framework;

import java.awt.Image;

public class TileSelection {

	private Image thumbnail;
	private SpriteSheet sheet;
	
	public TileSelection(int width, int height, String ressource){
		sheet = new SpriteSheet(width, height, ressource);
		
		thumbnail = sheet.getSprite(43);
	}
	
	public Image getThumbnail(){
		return thumbnail;
	}
	
	public Image getImage(int i){
		return sheet.getSprite(i);
	}
}
