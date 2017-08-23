package de.visuals;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
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
		
		//Toolbar
		JToolBar tb = new JToolBar();
		tb.setFloatable(false);
		tb.setRollover(false);
		
		
		tb.add(new JButton("TileMode"));
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
		JPanel rightPanel = new JPanel();
		FlowLayout bl = (FlowLayout) rightPanel.getLayout();
		bl.setHgap(0);
		bl.setVgap(0);
		
		TileSelector tiles = new TileSelector();
		DnDPanel dndtiles = new DnDPanel(tiles);
		
		rightPanel.add(dndtiles);
		
		JScrollPane scrollPane = new JScrollPane(rightPanel);
		scrollPane.setPreferredSize(tiles.getPreferredSize());
		
		splitpane.setRightComponent(scrollPane);
		
		setJMenuBar(new EditorMenuBar());
		
		setMinimumSize(new Dimension(300,200));

		setVisible(true);
		
	}
}
