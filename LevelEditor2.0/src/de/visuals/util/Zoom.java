package de.visuals.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.framework.Options;
import de.visuals.EditorView;

public class Zoom {
	private static Lock zoomLock = new ReentrantLock();
	
	public Zoom(){
	}
	
	public void zoomIn(EditorView editor, int zoomSteps, int sleepTime, int x, int y){
		zoomLock.lock();
		
		for (int i = 0; i < zoomSteps; i++) {
			double s1 = editor.getScale();
			double y1 = editor.getHeight() / s1;
			double x1 = editor.getWidth() / s1;
			double xm1 = x;
			double ym1 = y;
			
			editor.addScale((-.1)/zoomSteps);
			double y2, x2, ym2, xm2;

			y2 = editor.getHeight() / editor.getScale();
			x2 = editor.getWidth() / editor.getScale();
			ym2 = ym1 / editor.getHeight();
			xm2 = xm1 / editor.getWidth();

			editor.addOffset(-((x1 - x2) * xm2), -((y1 - y2) * ym2));	

			if(Options.animation == false)
				editor.repaint();
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		zoomLock.unlock();
	}
	
	public void zoomOut(EditorView editor, int zoomSteps, int sleepTime, int x, int y){
		zoomLock.lock();
		
		for (int i = 0; i < zoomSteps; i++) {
			double s1 = editor.getScale();
			double y1 = editor.getHeight() / s1;
			double x1 = editor.getWidth() / s1;
			double xm1 = x;
			double ym1 = y;
			
			editor.addScale((.1)/zoomSteps);
			double y2, x2, ym2, xm2;

			y2 = editor.getHeight() / editor.getScale();
			x2 = editor.getWidth() / editor.getScale();
			ym2 = ym1 / editor.getHeight();
			xm2 = xm1 / editor.getWidth();

			editor.addOffset(-((x1 - x2) * xm2), -((y1 - y2) * ym2));

			if(Options.animation == false)
				editor.repaint();
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		zoomLock.unlock();
	}
}
