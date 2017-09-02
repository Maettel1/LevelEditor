package de.visuals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.framework.Room;
import de.visuals.util.Filework;

public class EditorMenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5959123130812976802L;

	JMenu file;
	JMenu edit;
	JMenuItem new_, open, save, saveas, exit;
	JMenuItem undo, redo, cut, copy, paste;

	public EditorMenuBar() {
		file = new JMenu("File");
		edit = new JMenu("Edit");

		add(file);
		add(edit);

		addFileItems();
		addEditItems();

	}

	private void addFileItems() {

		// New
		new_ = new JMenuItem("New");
		new_.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/New.png")));
		new_.addActionListener(this);
		file.add(new_);

		// Open
		open = new JMenuItem("Open...");
		open.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Open.png")));
		open.addActionListener(this);
		file.add(open);

		file.addSeparator();

		// Save
		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		// save.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Save.png")));
		save.addActionListener(this);
		file.add(save);

		// SaveAs
		saveas = new JMenuItem("Save As...");
		// saveas.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/SaveAs.png")));
		saveas.addActionListener(this);
		file.add(saveas);

		file.addSeparator();

		// Exit
		exit = new JMenuItem("Exit");
		// exit.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Exit.png")));
		exit.addActionListener(this);
		file.add(exit);

	}

	private void addEditItems() {

		// Undo
		undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		// undo.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Undo.png")));
		undo.addActionListener(this);
		edit.add(undo);

		// Redo
		redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		// redo.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Redo.png")));
		redo.addActionListener(this);
		edit.add(redo);

		edit.addSeparator();

		// Cut
		cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		// cut.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Cut.png")));
		cut.addActionListener(this);
		edit.add(cut);

		// Copy
		copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		// copy.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Copy.png")));
		copy.addActionListener(this);
		edit.add(copy);

		// Paste
		paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		// paste.setIcon(new
		// ImageIcon(this.getClass().getClassLoader().getResource("de/visuals/menuImages/Paste.png")));
		paste.addActionListener(this);
		edit.add(paste);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(((JMenuItem) arg0.getSource()).getText());
		switch(((JMenuItem) arg0.getSource()).getText()){
		case "Save":
			Filework.save(EditorWindow.editor.getRoomList());
			break;
		case "Open...":
			ArrayList<Room> roomList= Filework.load();
			if(roomList != null)
				EditorWindow.editor.setRoomList(roomList);
			break;
		}
	}

}
