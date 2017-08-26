package de.visuals.listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import de.framework.Options;
import de.framework.Tile;
import de.visuals.EditorView;
import de.visuals.TileSelector;

public class EditorMouseListener implements MouseInputListener{
	
	EditorView editor;
	TileSelector tileSelector;
	
	private Integer x, y;
	private boolean drag = false;
	private boolean place = false;
	private boolean remove = false;
	
	public EditorMouseListener(EditorView editor, TileSelector tileSelector){
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
		//double y1 = arg0.getY()/editor.getScale() - editor.getYOffset();
		//double x1 = arg0.getX()/editor.getScale() - editor.getXOffset();	
		
		//System.out.println(x1 + " " + y1);
		
		if(arg0.getButton() == MouseEvent.BUTTON2){
			drag = true;
			editor.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		if(arg0.getButton() == MouseEvent.BUTTON1){
			placeTile(arg0);
			place = true;
			remove = false;
			if(Options.animation == false)
				editor.repaint();
		}
		if(arg0.getButton() == MouseEvent.BUTTON3){
			removeTile(arg0);
			remove = true;
			place = false;
			if(Options.animation == false)
				editor.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton() == MouseEvent.BUTTON2){
			editor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			drag = false;
			x = null;
			y = null;
		}
		if(arg0.getButton() == MouseEvent.BUTTON1){
			place = false;
		}
		if(arg0.getButton() == MouseEvent.BUTTON3){
			remove = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(drag){
			if(x == null) x = arg0.getX();
			if(y == null) y = arg0.getY();
			
			editor.addOffset((arg0.getX()-x)/editor.getScale(), (arg0.getY()-y)/editor.getScale());
			x = arg0.getX();
			y = arg0.getY();
			if(Options.animation == false)
				editor.repaint();
		}
		if(place){
			placeTile(arg0);
			if(Options.animation == false)
				editor.repaint();
		}
		if(remove){
			removeTile(arg0);
			if(Options.animation == false)
				editor.repaint();
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	private void placeTile(MouseEvent arg0){
		if(editor.getPlaceState() != 2)
			return;
		double y1 = arg0.getY()/editor.getScale() - editor.getYOffset();
		double x1 = arg0.getX()/editor.getScale() - editor.getXOffset();
		
		editor.addTile(new Tile(x1,y1,tileSelector.getSelected(),true));
	}
	
	private void removeTile(MouseEvent arg0){
		if(editor.getPlaceState() != 2)
			return;
		double y1 = arg0.getY()/editor.getScale() - editor.getYOffset();
		double x1 = arg0.getX()/editor.getScale() - editor.getXOffset();
					
		editor.removeTile(x1, y1);
	}
}
