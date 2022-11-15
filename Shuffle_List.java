/**
 * /*
 *  Name: Brad Davidson
 * Date: 09/19/2022
 * Purpose: array list shuffle
 */
 
import java.util.*;


public class Shuffle_List {

	public static void main(String[] args) {
		ArrayList<Number>list = new ArrayList<Number>();
		list.add(14);
		list.add(24);
		list.add(4);
		list.add(42);
		list.add(5);
		shuffle(list);
		
		for(int i = 0;i < list.size();i++)
			System.out.print(list.get(i)+ " ");

	}
	
	public static void shuffle(ArrayList<Number>list) {
		Collections.shuffle(list);
	}
}


