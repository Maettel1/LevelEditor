package de.visuals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class EditorToolBar extends JToolBar implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1941705378306676782L;

	private JButton testButton;
	
	public EditorToolBar(){
		setRollover(true);
		testButton = new JButton("TestButton");
		testButton.setFocusable(false);
		add(testButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
