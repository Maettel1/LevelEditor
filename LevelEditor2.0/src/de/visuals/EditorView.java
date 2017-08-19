package de.visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.NumberFormat;

import javax.swing.JPanel;

import de.framework.Animator;
import de.framework.Options;
import de.visuals.listeners.EditorKeyListener;
import de.visuals.listeners.EditorMouseListener;
import de.visuals.listeners.EditorMouseWheelListener;

public class EditorView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8534499882922429063L;
	
	private double scale;
	private double offsetx, offsety;
	private EditorCamera cam;
	private Animator ani;

	public EditorView(){
		setFocusable(true);
		
		cam = new EditorCamera();
		scale = 1;
		offsetx = 0;
		offsety = 0;
		
		addKeyListener(new EditorKeyListener(this));
		addMouseWheelListener(new EditorMouseWheelListener(this));
		EditorMouseListener mouseInput = new EditorMouseListener(this);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		System.out.println(toString());
		
		if(Options.animation == true){
			ani = new Animator(this);
			ani.start();
		}
		
		this.setDoubleBuffered(true);
	}
	
	protected void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getSize().width, getSize().height);

		cam.scale(g2d, scale);
		cam.translate(g2d, offsetx, offsety);
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.red);
		g2d.fillOval(10, 10, 100, 100);
		
		paintChildren(g2d);
		
		drawHUD(g2d);
		
		g.dispose();
		g2d.dispose();
	}
	
	private void drawHUD(Graphics2D g2d){

		Runtime runtime = Runtime.getRuntime();
		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("free memory: " + format.format(freeMemory / 1024) + "KB\n");
		sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "KB\n");
		sb.append("max memory: " + format.format(maxMemory / 1024) + "KB\n");
		sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "KB\n");
		
		g2d.setTransform(new AffineTransform());
		
		g2d.setColor(Color.white);
		drawString(g2d,sb.toString(),10,10);
	}

	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
	
	public void addOffset(double x, double y){
		offsetx += x;
		offsety += y;
	}
	
	public void addScale(double scale){
		this.scale += scale;
		if(this.scale <= 0.1) 
			this.scale = .1;
	}
	
	public void setScale(double scale){
		this.scale = scale;
	}
	
	public String toString(){
		return new String("Scale: " + scale + " Offset: " + offsetx + " " + offsety);
		
	}

	public double getXOffset(){
		return offsetx;
	}
	
	public double getYOffset(){
		return offsety;
	}
	
	public double getScale(){
		return scale;
	}
	
	public void setOffset(double x, double y){
		offsetx = x;
		offsety = y;
	}
}

class EditorCamera {

	public void scale(Graphics2D g2d, double scale){
		g2d.scale(scale, scale);
	}
	
	public void translate(Graphics2D g2d, double offsetx, double offsety){
		g2d.translate(offsetx, offsety);
	}
}

