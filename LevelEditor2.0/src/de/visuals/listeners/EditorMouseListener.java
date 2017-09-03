package de.visuals.listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import de.framework.Options;
import de.framework.Room;
import de.framework.Tile;
import de.visuals.EditorView;
import de.visuals.TileSelector;

public class EditorMouseListener implements MouseInputListener {

	EditorView editor;
	TileSelector tileSelector;

	private Integer x, y;
	private boolean drag = false;
	private boolean place = false;
	private boolean remove = false;

	public EditorMouseListener(EditorView editor, TileSelector tileSelector) {
		this.editor = editor;
		this.tileSelector = tileSelector;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// double y1 = arg0.getY()/editor.getScale() - editor.getYOffset();
		// double x1 = arg0.getX()/editor.getScale() - editor.getXOffset();

		// System.out.println(x1 + " " + y1);

		if (arg0.getButton() == MouseEvent.BUTTON2) {
			drag = true;
			editor.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		switch (editor.getPlaceState()) {
		case 1:
			break;
		case 2:
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				placeTile(arg0);
				place = true;
				remove = false;
				if (Options.animation == false)
					editor.repaint();
			}
			if (arg0.getButton() == MouseEvent.BUTTON3) {
				removeTile(arg0);
				remove = true;
				place = false;
				if (Options.animation == false)
					editor.repaint();
			}
			break;
		case 3:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == MouseEvent.BUTTON2) {
			editor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			drag = false;
			x = null;
			y = null;
		}

		switch (editor.getPlaceState()) {
		case 1:
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				double y1 = arg0.getY() / editor.getScale() - editor.getYOffset();
				double x1 = arg0.getX() / editor.getScale() - editor.getXOffset();

				Room r = editor.selectRoom(x1, y1);
				if (r != null)
					r.OnMouseClick();

				if (Options.animation == false)
					editor.repaint();
			}
			break;
		case 2:
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				place = false;
			}
			if (arg0.getButton() == MouseEvent.BUTTON3) {
				remove = false;
			}
			break;
		case 3:
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (drag) {
			if (x == null)
				x = arg0.getX();
			if (y == null)
				y = arg0.getY();

			editor.addOffset((arg0.getX() - x) / editor.getScale(), (arg0.getY() - y) / editor.getScale());
			x = arg0.getX();
			y = arg0.getY();
			if (Options.animation == false)
				editor.repaint();
		}
		switch (editor.getPlaceState()) {
		case 1:
			// room
		case 2:
			// tile
			if (place) {
				placeTile(arg0);
				if (Options.animation == false)
					editor.repaint();
			}
			if (remove) {
				removeTile(arg0);
				if (Options.animation == false)
					editor.repaint();
			}
			break;
		case 3:
			// object

			break;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	private void placeTile(MouseEvent arg0) {
		double y1 = arg0.getY() / editor.getScale() - editor.getYOffset();
		double x1 = arg0.getX() / editor.getScale() - editor.getXOffset();

		editor.addTile(new Tile(x1, y1, tileSelector.getSelection(), true));
	}

	private void removeTile(MouseEvent arg0) {
		double y1 = arg0.getY() / editor.getScale() - editor.getYOffset();
		double x1 = arg0.getX() / editor.getScale() - editor.getXOffset();

		editor.removeTile(x1, y1);
	}
}
