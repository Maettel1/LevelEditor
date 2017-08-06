package de.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.visuals.EditorView;

public class EditorMouseWheelListener implements MouseWheelListener {

	protected EditorView editor;
	private Lock lock;

	public EditorMouseWheelListener(EditorView editor) {
		this.editor = editor;
		lock = new ReentrantLock();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

		Thread t = new Thread(new ZoomThread(arg0, lock, editor));
		t.start();

	}

}

class ZoomThread implements Runnable {
	private MouseWheelEvent e;
	private Lock lock;
	private EditorView editor;

	public ZoomThread(MouseWheelEvent e, Lock lock, EditorView editor) {
		this.e = e;
		this.lock = lock;
		this.editor = editor;
	}

	@Override
	public void run() {
		lock.lock();

		int zoomSteps = 5;
		int sleepTime = 5;
		
		for (int i = 0; i < zoomSteps; i++) {
			double s1 = editor.getScale();
			double y1 = editor.getHeight() / s1;
			double x1 = editor.getWidth() / s1;
			double xm1 = e.getX();
			double ym1 = e.getY();

			if (e.getWheelRotation() > 0) {
				editor.addScale((-.1)/zoomSteps);
				double y2, x2, ym2, xm2;

				y2 = editor.getHeight() / editor.getScale();
				x2 = editor.getWidth() / editor.getScale();
				ym2 = ym1 / editor.getHeight();
				xm2 = xm1 / editor.getWidth();

				editor.addOffset(-((x1 - x2) * xm2), -((y1 - y2) * ym2));
			} else {
				editor.addScale(.1/zoomSteps);
				double y2, x2, ym2, xm2;

				y2 = editor.getHeight() / editor.getScale();
				x2 = editor.getWidth() / editor.getScale();
				ym2 = ym1 / editor.getHeight();
				xm2 = xm1 / editor.getWidth();

				editor.addOffset(-((x1 - x2) * xm2), -((y1 - y2) * ym2));
			}
			//editor.repaint();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		lock.unlock();
		
	}

}
