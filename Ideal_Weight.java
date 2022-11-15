/**
 * @author Brad Davidson
 * Date: 04/15/2022
 * File Name: Chapter8Lab2.java
 * Purpose:to determine whether a person is within the desired weight based on their height
 */
import java.util.Scanner;

public class Ideal_Weight {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[]firstName,lastName,gender,result;
		int size;
		int[]feet,inches,weight;
		int[]totalHeight;
	
		
		System.out.println("");
		System.out.println("This program will determine whether you are within your ideal weight, based on your height.");
		System.out.println("\n\n");
		System.out.println("How many people will you be entering?");
		size=sc.nextInt();

		firstName = new String[size];
		lastName = new String[size];
		gender = new String[size];
		result = new String[size];
		feet = new int[size];
		inches = new int[size];
		totalHeight = new int[size];
		weight = new int[size];
		
		for(int i=0;i<size;i++) {
			System.out.println("\nEnter the first name of Person # " + (i+1));
			firstName[i]=sc.next();
			
			System.out.println("\nEnter the last name of Person # " + (i+1));
			lastName[i]=sc.next();
			
			System.out.println("\nEnter the gender(Male or Female) of Person # " + (i+1));
			gender[i]=sc.next();
			
			System.out.println("\nEnter Person # " + (i+1)+ " height in feet: ");
			feet[i]=sc.nextInt();
			
			System.out.println("\nEnter Person # " + (i+1)+ "'s additional inches in their height(for example: if a person is 5ft 10in, you would enter 10 here):");
			inches[i]=sc.nextInt();
			
			totalHeight[i]=(feet[i]*12)+inches[i];
			
			System.out.println("\nEnter Person # " + (i+1)+ " wight to the nearest whole number: ");
			weight[i]=sc.nextInt();
			
			if(gender[i].equalsIgnoreCase("female"))
				gender[i]="Her";
			else
				gender[i]="His";
			
			switch(totalHeight[i]) {
			
			case 58:
				if(weight[i]>=91 && weight[i]<=115)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 91 and 115 pounds";
				break;
				
			case 59:
				if(weight[i]>=94 && weight[i]<=119)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 94 and 119 pounds";
				break;
				
			case 60:
				if(weight[i]>=97 && weight[i]<=123)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 97 and 123 pounds";
				break;
				
			case 61:
				if(weight[i]>=100 && weight[i]<=127)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 100 and 127 pounds";
				break;
				
			case 62:
				if(weight[i]>=104 && weight[i]<=131)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 104 and 131 pounds";
				break;
				
			case 63:
				if(weight[i]>=107 && weight[i]<=135)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 107 and 135 pounds";
				break;
				
			case 64:
				if(weight[i]>=110 && weight[i]<=140)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 110 and 140 pounds";
				break;
				
			case 65:
				if(weight[i]>=114 && weight[i]<=144)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 114 and 144 pounds";
				break;
				
			case 66:
				if(weight[i]>=118 && weight[i]<=148)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 118 and 148 pounds";
				break;
				
			case 67:
				if(weight[i]>=121 && weight[i]<=153)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 121 and 153 pounds";
				break;
				
			case 68:
				if(weight[i]>=125 && weight[i]<=158)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 125 and 158 pounds";
				break;
				
			case 69:
				if(weight[i]>=128 && weight[i]<=162)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 128 and 162 pounds";
				break;
				
			case 70:
				if(weight[i]>=132 && weight[i]<=167)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 132 and 167 pounds";
				break;
				
			case 71:
				if(weight[i]>=136 && weight[i]<=172)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 136 and 172 pounds";
				break;
				
			case 72:
				if(weight[i]>=140 && weight[i]<=177)
					result[i]=" is ideal.";
				else
					result[i] = " is not ideal. " + gender[i] + " weight should be between 140 and 177 pounds";
				break;
				
			default:
				result[i]="Cannot be calculated whether weight is ideal or not because " + gender[i] + " height is " + feet[i] + "ft" + inches[i] + "in. See doctor for ideal weight.";
				break;
			}
		}
		
	for(int i=0;i<size;i++) {
		System.out.println("");
		System.out.println(firstName[i]+" "+lastName[i]+"'s weight " + result[i]);
		}
	}

}
