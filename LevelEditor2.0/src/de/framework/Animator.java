package de.framework;

import de.visuals.EditorView;

public class Animator implements Runnable{
	
	private EditorView editor;
	private boolean running;
	private Thread t;
	
	public Animator(EditorView editor){
		this.editor = editor;
		t = new Thread(this);
	}

	public void start(){
		running = true;
		t.start();
	}
	
	public void stop(){
		running = false;
	}
	
	@Override
	public void run() {
		byte fps = 60;
		
		long lastTime = System.nanoTime();
		long timePerFrame = 1000000000/fps;
		
		while(running){
			
			if(System.nanoTime() - lastTime >= timePerFrame){
				editor.repaint();
				lastTime += timePerFrame;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
