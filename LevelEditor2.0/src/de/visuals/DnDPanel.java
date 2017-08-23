package de.visuals;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class DnDPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7988357983703771287L;

	private DragPanel dropLabel;
	private JComponent component;
	
	//Constructors
	public DnDPanel(JComponent component){
		create();
		setComponent(component);
	}
	
	public DnDPanel(){
		create();
	}
	
	private void create(){
		BorderLayout bl = new BorderLayout();
		bl.setVgap(1);
		setLayout(bl);
		
		dropLabel = new DragPanel();
		
		this.add(dropLabel, BorderLayout.NORTH);
	}
	
	//set middle component
	public void setComponent(JComponent component){
		if(this.component != null)
			remove(this.component);
		this.component = component;
		add(component);
		
	}
}

class DragPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2617012665870387441L;
	
	public void paintComponent(Graphics g){
		int middle = getWidth()/2-16;
		
		URL url = this.getClass().getClassLoader().getResource("de/visuals/menuImages/Dot.png");
		Image i = null;
		
		try {
			i = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(i, middle, 1, null);
		g.drawImage(i, middle+11, 1, null);
		g.drawImage(i, middle+22, 1, null);
	}
}
