/**
 * @author Brad Davidson
 * Date:04/28/2022
 * Purpose: to test SaddleHorse,DraftHorse, and Horse classes
 */
import java.util.Scanner;

public class Horse_Abstraction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//declarations
		Scanner keyboard = new Scanner(System.in);
		
		SaddleHorse horse1 = new SaddleHorse();
		DraftHorse horse2 = new DraftHorse();
		
		int idNo, age, height;
		String color, saddleType, breed, classification;
		
		System.out.printf("%100s%n%n", "Let's enter a Saddle Horse");
		
		System.out.println("Enter the horse's ID number (whole numbers only)");
		idNo=keyboard.nextInt();
		
		System.out.println("Enter the horse's age(whole numbers only");
		age=keyboard.nextInt();
		
		System.out.println("Enter the horse's coat color e.g. Roan");
		color=keyboard.next();
		
		System.out.println("Enter height of horse hands (whole numbers only");
		height=keyboard.nextInt();
		
		System.out.println("Enter the saddle type for this horse (English/Western)");
		saddleType=keyboard.next();
		
		keyboard.nextLine();
		
		System.out.println("Enter the breed of this horse e.g. Thoroughbred");
		breed=keyboard.nextLine();
		
		horse1.setIdNo(idNo);
		horse1.setCoatColor(color);
		horse1.setAge(age);
		horse1.setHeight(height);
		horse1.setSaddleType(saddleType);
		horse1.setSaddleBreed(breed);
		
		System.out.printf("%100s%n%n", "Let's enter a Draft Horse");
		
		System.out.println("Enter the horse's ID number (whole numbers only)");
		idNo=keyboard.nextInt();
		
		System.out.println("Enter the horse's age(whole numbers only");
		age=keyboard.nextInt();
		
		System.out.println("Enter the horse's coat color e.g. Roan");
		color=keyboard.next();
		
		System.out.println("Enter height of horse hands (whole numbers only");
		height=keyboard.nextInt();
		
		System.out.println("Enter the classification for this horse (Harness/Saddle)");
		classification=keyboard.next();
		
		keyboard.nextLine();
		
		System.out.println("Enter the breed of this horse e.g. Percheron");
		breed=keyboard.nextLine();
		
		horse2.setIdNo(idNo);
		horse2.setCoatColor(color);
		horse2.setAge(age);
		horse2.setHeight(height);
		horse2.setClassification(classification);
		horse2.setDraftBreed(breed);
		
		System.out.println("\nLet's display the information on the Saddle Horse");
		System.out.println(horse1.tellAboutHorse());
		
		System.out.println("\nLet's display the information on the Draft Horse");
		System.out.println(horse2.tellAboutHorse());
	}
}
