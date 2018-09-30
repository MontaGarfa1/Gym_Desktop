package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Section;

public class SectionDao {

	private ObjectOutputStream sortieC;
	private ObjectInputStream lectureC;
	// private ArrayList<Staff> listC = new ArrayList<Staff>();

	public void AjoutFichierC(ArrayList<Section> listC) throws FileNotFoundException, IOException {
		sortieC = new ObjectOutputStream(new FileOutputStream("Section.txt"));
		for (int i = 0; i < listC.size(); i++) {
			sortieC.writeObject(listC.get(i));
		}
	}

	public void lectureFichierC(ArrayList<Section> listC) throws FileNotFoundException, IOException {
		boolean test = false;
		lectureC = new ObjectInputStream(new FileInputStream("Section.txt"));
		while (!test) {
			try {
				Section C = (Section) lectureC.readObject();
				listC.add(C);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException ex) {
				test = true;
				lectureC.close();
			}
		}

	}

	public ArrayList<Section> lectureFichierC() throws FileNotFoundException, IOException {
		boolean test = false;
		ArrayList<Section> listC = new ArrayList<Section>();
		lectureC = new ObjectInputStream(new FileInputStream("Section.txt"));
		while (!test) {
			try {
				Section C = (Section) lectureC.readObject();
				listC.add(C);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException ex) {
				test = true;
				lectureC.close();
			}
		}
		return listC;

	}
}
