package de.framework;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4833057609456148306L;
	protected transient Area hitBox;
	protected Polygon poly;

	protected int x, y;

	public boolean collision(GameObject obj) {
		Area area = new Area(hitBox);
		area.intersect(obj.hitBox);
		if (area.equals(new Area()))
			return false;
		return true;
	}

	public GameObject collision(double x, double y, Class<?> _class, ArrayList<GameObject> list) {

		@SuppressWarnings("unchecked")
		ArrayList<GameObject> listCopy = (ArrayList<GameObject>) list.clone();

		for (GameObject go : listCopy) {
			if ((go.getClass() == _class || go.getClass().getSuperclass() == _class) && go != this) {
				Polygon tempPoly = new Polygon(poly.xpoints, poly.ypoints, poly.npoints);
				tempPoly.translate((int) x - this.x, (int) y - this.y);
				Area tempArea = new Area(tempPoly);
				tempArea.intersect(go.getHitBox());
				if (!tempArea.isEmpty()) {
					return go;
				}
			}
		}

		return null;
	}

	public GameObject collisionPoint(double x, double y) {
		if (hitBox.contains(x, y))
			return this;
		else
			return null;
	}

	public void createHitBox() {
		hitBox = new Area(poly);
	}

	public abstract void draw(Graphics g);

	public Area getHitBox() {
		return hitBox;
	}
}
