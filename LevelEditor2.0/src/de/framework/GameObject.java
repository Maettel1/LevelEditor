package de.framework;

import java.awt.Polygon;
import java.awt.geom.Area;

public abstract class GameObject {
	
	protected Area hitBox;
	protected Polygon poly;
	
	protected int x, y;
	
	public boolean collision(GameObject obj){
		Area area = new Area(hitBox);
		area.intersect(obj.hitBox);
		if(area.equals(new Area()))
			return false;
		return true;
	}
	
	public GameObject collisionPoint(double x, double y){
		if(hitBox.contains(x, y))
			return this;
		else return null;
	}
	
}
