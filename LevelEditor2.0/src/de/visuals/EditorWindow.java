package de.visuals;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

public class EditorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833927369553211675L;

	public EditorWindow(int width, int height) {
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

		// splitpane
		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(splitpane, BorderLayout.CENTER);
		splitpane.setPreferredSize(getSize());
		splitpane.setResizeWeight(1);

		// right component
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

		// left component
		EditorView editor = new EditorView(tiles);
		splitpane.setLeftComponent(editor);

		// Toolbar
		addToolBar(editor);

		// statusbar
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

		JLabel statusLabel = new JLabel();

		JLabel tileAmount = new JLabel();

		statusPanel.add(statusLabel);
		statusPanel.add(tileAmount);

		new Thread(new Runnable() {

			@Override
			public void run() {
				Runtime runtime = Runtime.getRuntime();
				NumberFormat format = NumberFormat.getInstance();

				while (true) {
					long maxMemory = runtime.maxMemory();
					long allocatedMemory = runtime.totalMemory();
					long freeMemory = runtime.freeMemory();

					statusLabel.setText("free memory: " + format.format(freeMemory / 1024) + "KB  ");
					statusLabel.setToolTipText("<html><p width=\"200\">" + "free memory: " + format.format(freeMemory / 1024)
					+ "KB<br>" + "allocated memory: " + format.format(allocatedMemory / 1024) + "KB<br>"
					+ "max memory: " + format.format(maxMemory / 1024) + "KB<br>" + "total free memory: "
					+ format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "KB"
					+ "</p></html>");

					tileAmount.setText("Tiles: " + editor.getTileSize());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();

		setJMenuBar(new EditorMenuBar());

		setMinimumSize(new Dimension(300, 200));

		setVisible(true);
	}

	private void addToolBar(EditorView component) {
		JToolBar tb = new JToolBar();
		tb.setFloatable(false);
		tb.setRollover(false);

		JToggleButton roomButton, tileButton, objectButton;
		ButtonGroup bg = new ButtonGroup();

		roomButton = new JToggleButton("room");
		tileButton = new JToggleButton("tile");
		objectButton = new JToggleButton("object");

		roomButton.setFocusable(false);
		tileButton.setFocusable(false);
		objectButton.setFocusable(false);

		roomButton.setSelected(true);

		roomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				component.setPlaceState(1);
			}
		});
		tileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				component.setPlaceState(2);
			}
		});
		objectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				component.setPlaceState(3);
			}
		});

		bg.add(roomButton);
		bg.add(tileButton);
		bg.add(objectButton);

		tb.add(roomButton);
		tb.add(tileButton);
		tb.add(objectButton);

		tb.addSeparator();

		add(tb, BorderLayout.PAGE_START);
	}
}
