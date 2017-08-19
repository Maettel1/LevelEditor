package de.visuals;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
		
		//splitpane
		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(splitpane,BorderLayout.CENTER);
		splitpane.setPreferredSize(getSize());
		splitpane.setResizeWeight(1);
		
		//left component
		EditorView editor = new EditorView();
		splitpane.setLeftComponent(editor);
		
		//right component
		TileSelector tiles = new TileSelector();
		
		JScrollPane scrollPane = new JScrollPane(tiles);
		scrollPane.setPreferredSize(tiles.getPreferredSize());
		scrollPane.setMinimumSize(tiles.getMinimumSize());
		splitpane.setRightComponent(scrollPane);
		
		setJMenuBar(new EditorMenuBar());
		
		setMinimumSize(new Dimension(300,200));

		setVisible(true);
		
	}
}
