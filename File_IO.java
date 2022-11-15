/*
 * Author: Brad Davidson
 * Date: 11/5/2022
 * Purpose: Demonstrate the Formatter, FileInputStream, File, FileOutputStream, StringBuffer classes
 */


import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class File_IO {

	public static void main(String[] args) {
		
		//the file class links to the name of the file to be read
		File file = new File("Ch17Lab1File.txt");
		
		int ch;
		
		//the StringBuffer class allows you to append to a String of characters
		StringBuffer strContent = new StringBuffer("");
		
		//The FileInputStream class reads the input supplied by the File class
		FileInputStream fin = null;
		
		try {
			//construct a Formatter object that uses the FilOutputStream class to link to the text file to be downloaded to
			Formatter output = new Formatter(new FileOutputStream("Ch17Lab1File.txt"));
			
			//generate 100 random numbers
			for (int i = 0; i < 100; i++) {
				output.format("%d",  (int)(Math.random() * 100000)); //The %d formats to an integer
				
				//always close the stream
			}
				
			output.close();
		}
		
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error creating file");
		}
			
			//notify the user that the download is complete
		JOptionPane.showConfirmDialog(null, "Output Complete");
		
		try {
			fin = new FileInputStream(file);
			
			//while there is anything to read through the FileInputStream append to the StringBuffer
			while((ch = fin.read()) != -1) {
				strContent.append((char)ch);
			}
			
			//close the stream
			fin.close();
		}
		
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found. Check the name of the file.");
		}
		
		catch(IOException e) {
			System.out.println("Error reading the file");
		}
		
		System.out.println(strContent);		
	}
}
