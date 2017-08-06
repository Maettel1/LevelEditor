package de.listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import de.visuals.EditorView;

public class EditorMouseListener implements MouseInputListener{
	
	EditorView editor;
	
	private Integer x, y;
	private boolean drag = false;
	
	public EditorMouseListener(EditorView editor){
		this.editor = editor;
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
		double y1 = arg0.getY()/editor.getScale() - editor.getYOffset();
		double x1 = arg0.getX()/editor.getScale() - editor.getXOffset();	
		
		System.out.println(x1 + " " + y1);
		
		if(arg0.getButton() == MouseEvent.BUTTON2){
			drag = true;
			editor.setCursor(new Cursor(Cursor.MOVE_CURSOR));
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
			//editor.repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
