package de.framework;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	@SuppressWarnings("unused")
	private int width;
	@SuppressWarnings("unused")
	private int height;
	private BufferedImage sprites[];
	
	public SpriteSheet(int width, int height, String ressource){
		this.width = width;
		this.height = height;
		BufferedImage sheet = null;
		
		try{
			URL url = this.getClass().getResource(ressource);
			sheet = ImageIO.read(url);
		}catch(Exception e){
			System.out.println("Spritesheet can't be opened!");
			e.printStackTrace();
		}
		
		if(sheet == null)
			System.out.println("nope");
		
		int x = sheet.getWidth()/width;
		int y = sheet.getHeight()/height;
		
		sprites = new BufferedImage[x*y];
		
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				sprites[i*x+j] = sheet.getSubimage(j*width, i*height, width, height);
			}
		}
	}
	
	public BufferedImage getSprite(int i){
		return sprites[i];
	}
	
}
