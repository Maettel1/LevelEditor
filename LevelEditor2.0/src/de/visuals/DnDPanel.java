package de.visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DnDPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7988357983703771287L;

	private DragPanel dropLabel;
	private JComponent component;
	private JScrollPane scrollPane;
	private GridBagConstraints c;
	
	//Constructors
	public DnDPanel(JComponent component){
		create();
		setComponent(component);
	}
	
	public DnDPanel(){
		create();
	}
	
	private void create(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout gl = new GridBagLayout();
		setLayout(gl);
		
		c = new GridBagConstraints();
		
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		
		dropLabel = new DragPanel();
		
		this.add(dropLabel, c);
	}
	
	//set middle component
	public void setComponent(JComponent component){
		if(this.component != null){
			remove(component);
			remove(scrollPane);
		}
		this.component = component;
		scrollPane = new JScrollPane(component);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0;
		c.weightx = 0;
		c.fill = GridBagConstraints.BOTH;
		
		add(scrollPane,c);
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(component.getPreferredSize());
		
	}
}

class DragPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2617012665870387441L;
	
	@Override
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
