/*
 * Author: Brad Davidson
 * Date: 11/15/2022
 * Purpose: Program will search directory recursively for a specific string
 */

import java.io.*;
import java.util.Scanner;

public class DirectorySearch {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: first word will be directory name to search in and second word will be the word to search ");
			System.exit(0);
		}
		
		findInFile(new File(args[0]), args[1]);
	}
	
	public static long findInFile(File file, String word) {
		
		long numberOfFiles = 0;
		
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(int i = 0; i < files.length; i++) {
				findInFile(files[i], word);
			}
		}
		else {
			findWord(file, word);
			numberOfFiles++;
			
		}
		return numberOfFiles;
	}
	
	public static void findWord(File file, String word) {
		try {
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				String line = input.nextLine();
				if(line.indexOf(word) > 1) {
					System.out.println(file + ": " + line);
				}
			}
			input.close();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
