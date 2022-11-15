/*
 *Author: Brad Davidson
 *Date: 11/15/2022
 *Purpose: Program will determine the greatest common divisor using recursion
 */
import java.util.Scanner;

public class GCD_Recursion {
	
	int m = 0;
	int n = 0;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		//Enter the first number
		System.out.print("Enter the first number: ");;
		int m = input.nextInt();
		
		//Enter the second number
		System.out.print("Enter the second number: ");
		int n = input.nextInt();
		
		//Display results by invoking the gcd() method
		System.out.println("The GCD of " + m + " and " + n + " is " + gcd(m,n));
		
	}
	
	public static int gcd(int m, int n) {
		
		//check to see if the remainder of m divided by n is 0
		if(m % n == 0)
			return n;
		else
			return gcd(n, m % n);
		}
}
