package de.visuals.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import de.visuals.EditorView;
import de.visuals.util.Zoom;

public class EditorMouseWheelListener implements MouseWheelListener {

	protected EditorView editor;

	public EditorMouseWheelListener(EditorView editor) {
		this.editor = editor;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

		Thread t = new Thread(new ZoomThread(arg0, editor));
		t.start();

	}

}

class ZoomThread implements Runnable {
	private MouseWheelEvent e;
	private EditorView editor;

	public ZoomThread(MouseWheelEvent e, EditorView editor) {
		this.e = e;
		this.editor = editor;
	}

	@Override
	public void run() {

		int zoomSteps = 10;
		int sleepTime = 5;
		
		
		Zoom zoom = new Zoom();
		
		if (e.getWheelRotation() > 0) {
			zoom.zoomIn(editor, zoomSteps, sleepTime, e.getX(), e.getY());
		} else {
			zoom.zoomOut(editor, zoomSteps, sleepTime, e.getX(), e.getY());
		}
	}

}
