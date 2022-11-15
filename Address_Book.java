/*
 * Author: Bradley Davidson
 * Date: 11/11/2022
 * Purpose: Program will take user input of name and address fields and save that information to a file
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Address_Book extends Application {
	
	int name = 32;
	int street = 32;
	int city = 20;
	int state = 2;
	int zip = 5;
	int count = 0;
	AddressBookPane pane = new AddressBookPane();
	
	@Override
	public void start(Stage primaryStage) {
		
		pane.btnAdd.setOnAction(e -> add());
		pane.btnFirst.setOnAction(e -> first());
		pane.btnNext.setOnAction(e -> next());
		pane.btnPrevious.setOnAction(e -> previous());
		pane.btnLast.setOnAction(e -> last());
		pane.btnUpdate.setOnAction(e -> update());
		
		Scene scene = new Scene(pane, 380, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chapter 17 Programming Assignment 2");
		primaryStage.show();
	}
	
	private void add() {
	
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
			raf.seek(raf.length());
			write(raf);
		}
		
		catch (FileNotFoundException ex) {}
		catch (IOException ex) {}
		catch (IndexOutOfBoundsException ex) {}
		
	}
	
	private void first() {
		
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
			
			if(raf.length() > 0) {
				raf.seek(0);
				read(raf);
				count = 1;
				System.out.println("Address #1");
			}
			else {
				System.out.println("Address is empty");
			}
		}
		catch (IOException ex) {}
		
	}
	
	private void next() {
		
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
			if (count * 91 < raf.length()) {
				raf.seek(count * 91);
				read(raf);
				count++;
				System.out.println("Reading address number " + count);
			}
			else {
				System.out.println("End of file");
			}
		}
		catch (IOException ex) {}		
	}
	
	private void previous() {
		
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
			
			if(count > 1)
				count--;
			else
				count = 1;
			raf.seek((count *91) - 91);
			read(raf);
			System.out.println("Reading address number " + count);
		}
		catch (IOException ex) {}
	}
	
	private void last() {
		
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
			
			count = ((int)raf.length()) / 91;
			raf.seek((count *91) - 91);
			read(raf);
			System.out.println("Reading address number " + count);
		}
		catch (IOException ex) {}
	}
	
	private void update() {
		
		try(RandomAccessFile raf = new RandomAccessFile("Names_and_Addresses.dat", "rw");) {
		
			raf.seek(count * 91 - 91);
			read(raf);
		}
		
		catch (FileNotFoundException ex) {}
		catch (IOException ex) {}		
	}
	
	private void read(RandomAccessFile raf) throws IOException{
		int pos;
		byte[] NAME = new byte[name];
		pos = raf.read(NAME);
		pane.tfName.setText(new String(NAME));

		byte[] STREET = new byte[street];
		pos = raf.read(STREET);
		pane.tfStreet.setText(new String(STREET));

		byte[] CITY = new byte[city];
		pos = raf.read(CITY);
		pane.tfCity.setText(new String(CITY));

		byte[] STATE = new byte[state];
		pos = raf.read(STATE);
		pane.tfState.setText(new String(STATE));
		
		byte[] ZIP = new byte[zip];
		pos = raf.read(ZIP);
		pane.tfZip.setText(new String(ZIP));
		
		
	}

	private void write(RandomAccessFile raf) throws IOException {
		raf.write(fixedLength(pane.tfName.getText().getBytes(), name));
		raf.write(fixedLength(pane.tfStreet.getText().getBytes(), street));
		raf.write(fixedLength(pane.tfCity.getText().getBytes(), city));
		raf.write(fixedLength(pane.tfState.getText().getBytes(), state));
		raf.write(fixedLength(pane.tfZip.getText().getBytes(), zip));
		System.out.println("Address number " + (count +1) + " has been saved to file");
	}
	
	private byte[]fixedLength(byte[] x, int n) {
		byte[]b = new byte[n];
		for(int i = 0; i <x.length; i ++)
			b[i] = x[i];
		return b;
	}

	public static void main(String[] args) throws IOException,ClassNotFoundException {
		launch(args);
	}
}

