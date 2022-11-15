import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

/*
 * Author: Brad Davidson
 * Date: 11/07/2022
 * Purpose: the program will store objects and arrays in a file. working with binary input output
 */
public class StoreObjectsAndArraysInAFile {
	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
	
		int[] intArray = {1, 2, 3, 4, 5};

		try(ObjectOutputStream output = new	ObjectOutputStream(new FileOutputStream("Exercise17_05.dat",true));) {
			
			output.writeObject(intArray);
			output.writeDouble(5.5);
			output.writeObject(new java.util.Date());
			
			output.close();
		}
		
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Exercise17_05.dat"));) {
				
			int[] newArray = (int[]) (input.readObject());
			double number = input.readDouble();
			java.util.Date date = (java.util.Date) (input.readObject());
			
			
			for(int i = 0; i < newArray.length; i++) {
				System.out.println(newArray[i] + " ");
			}			
			System.out.println("\n" + number + "\n\n" + date);
			
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}
}
