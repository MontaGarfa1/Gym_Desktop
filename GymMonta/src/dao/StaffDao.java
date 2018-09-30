package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Staff;

public class StaffDao {

	private ObjectOutputStream sortieC;
	private ObjectInputStream lectureC;
	// private ArrayList<Staff> listC = new ArrayList<Staff>();

	public void AjoutFichierC(ArrayList<Staff> listC) throws FileNotFoundException, IOException {
		sortieC = new ObjectOutputStream(new FileOutputStream("Staff.txt"));
		for (int i = 0; i < listC.size(); i++) {
			sortieC.writeObject(listC.get(i));
		}
	}

	public void lectureFichierC(ArrayList<Staff> listC) throws FileNotFoundException, IOException {
		boolean test = false;
		lectureC = new ObjectInputStream(new FileInputStream("Staff.txt"));
		while (!test) {
			try {
				Staff C = (Staff) lectureC.readObject();
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

	public ArrayList<Staff> lectureFichierC() throws FileNotFoundException, IOException {
		boolean test = false;
		ArrayList<Staff> listC = new ArrayList<Staff>();
		lectureC = new ObjectInputStream(new FileInputStream("Staff.txt"));
		while (!test) {
			try {
				Staff C = (Staff) lectureC.readObject();
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
