package de.visuals.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.visuals.EditorView;

public class EditorKeyListener implements KeyListener {

	private EditorView editor;

	public EditorKeyListener(EditorView editor) {
		this.editor = editor;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			editor.addOffset(-2, 0);
			break;
		case KeyEvent.VK_RIGHT:
			editor.addOffset(2, 0);
			break;
		case KeyEvent.VK_UP:
			editor.addOffset(0, -2);
			break;
		case KeyEvent.VK_DOWN:
			editor.addOffset(0, 2);
			break;
		}
		editor.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
