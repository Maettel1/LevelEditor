package de.visuals.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.framework.Room;

public class Filework {
	static JFileChooser chooser = new JFileChooser();

	public static void init() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Level files", "lvl");
		chooser.setFileFilter(filter);
	}

	public static void save(ArrayList<Room> roomList) {

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println(chooser.getSelectedFile());

			ObjectOutputStream out = null;

			try {
				String FileString = chooser.getSelectedFile().toString();
				if (!FileString.contains("."))
					FileString += ".lvl";
				out = new ObjectOutputStream(new FileOutputStream(FileString));

				/*
				 * for(Room r : roomList){ //roomdata //out.write(r.read());
				 * //System.out.println(r.read()); }
				 */
				out.writeObject(roomList);

				out.close();
				System.out.println("written");
			} catch (IOException e) {
				System.out.println("Oops, something went wrong with saving!");
				e.printStackTrace();
			} finally {
				if (out != null)
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Room> load() {
		ArrayList<Room> roomList = null;

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println(chooser.getSelectedFile());

			ObjectInputStream in = null;
			
			try {
				String FileString = chooser.getSelectedFile().toString();

				if (!FileString.endsWith(".lvl")) {
					System.out.println("Unsupported format!");
					throw null;
				}
				 in = new ObjectInputStream(new FileInputStream(FileString));
				 
				 try {
					roomList = (ArrayList<Room>) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(in != null)
						in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return roomList;
	}

}
