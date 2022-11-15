/*
 * Author: Brad Davidson
 * Date: 11/05/2022
 * Purpose: Data input and output in binary form
 */

import java.io.*;

public class File_Size_and_Count {

	public static void main(String[] args) {
		
		//declare the data input
		DataInputStream dis = null;
		DataOutputStream output = null;
		
		int count = 0;
		
		try {
			
			//create data input stream
			dis = new DataInputStream(new FileInputStream("Ch17Lab2.dat"));
			int total = 0;
			while (dis.available() > 0) {
				int temp = dis.readInt();
				total += temp;
				count ++;
				System.out.print(temp + " ");
			}
			System.out.println("\nCount is " + count);
			System.out.println("\nTotal is " + total + " bytes");
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			try {
				//close files
				if (dis != null) dis.close();
			}
			catch (IOException ex) {
				System.out.println(ex);
			}
		}
		
	}

}
