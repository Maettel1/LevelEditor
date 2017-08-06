package de.visuals;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EditorWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833927369553211675L;

	public EditorWindow(int width, int height){
		this.setLayout(new BorderLayout());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EditorToolBar tb = new EditorToolBar();
		add(tb, BorderLayout.PAGE_START);
		
		EditorView editor = new EditorView(getSize());
		add(editor, BorderLayout.CENTER);
		
		setJMenuBar(new EditorMenuBar());
		
		setMinimumSize(new Dimension(500,300));

		setVisible(true);
		
		
	}
}
