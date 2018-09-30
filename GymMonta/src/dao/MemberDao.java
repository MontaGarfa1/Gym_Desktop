package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Member;

public class MemberDao {

	private ObjectOutputStream sortieC;
	private ObjectInputStream lectureC;
	// private ArrayList<Staff> listC = new ArrayList<Staff>();

	public void AjoutFichierC(ArrayList<Member> listC) throws FileNotFoundException, IOException {
		sortieC = new ObjectOutputStream(new FileOutputStream("Member.txt"));
		for (int i = 0; i < listC.size(); i++) {
			sortieC.writeObject(listC.get(i));
		}
	}

	public void lectureFichierC(ArrayList<Member> listC) throws FileNotFoundException, IOException {
		boolean test = false;
		lectureC = new ObjectInputStream(new FileInputStream("Member.txt"));
		while (!test) {
			try {
				Member C = (Member) lectureC.readObject();
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

	public ArrayList<Member> lectureFichier() throws FileNotFoundException, IOException {
		boolean test = false;
		ArrayList<Member> listC = new ArrayList<Member>();
		lectureC = new ObjectInputStream(new FileInputStream("Member.txt"));
		while (!test) {
			try {
				Member C = (Member) lectureC.readObject();
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
