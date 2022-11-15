/* Date: 08/22/2022
 * Name: Bradley Davidson
 * Purpose: This program will request the size of the sides of the triangle and determine the area and perimeter. it will also ask if the user wants a fill color. 
 * if no fill is designated it will display the triangle will not be solid
 * 
 */

import java.util.Scanner;

public class Triangle_IO {

	public static void main(String[] args) {
		//declare variables
		Scanner input = new Scanner(System.in);
		double[]sides=new double[3];
		String color;
		boolean filled = false;
		String repeat = "yes";
		
		do
		{
			System.out.print("Enter three sides: ");
			for(int i=0;i<sides.length;i++)
			{
				sides[i]=input.nextDouble();
		    }
			System.out.println("Enter the color: ");
			color=input.next();
			
			Triangle triangle = new Triangle(sides[0],sides[1],sides[2],color);
			
			System.out.println("Enter true if you want to fill the triangle with the color: " + color + ". \n1f you do not want to fill the triangle with color please enter false.");
			
			filled = input.nextBoolean();
			
			if(filled==false)
			{
				color = "No color";
				triangle.setColor(color);
			}
			
			triangle.setFilled(filled);
			
			System.out.println();
			System.out.println("The area is " + triangle.getArea());
			System.out.println("The perimeter is " + triangle.getPerimeter());
			System.out.println("The color is " + triangle.getColor());
			System.out.println(triangle);
			System.out.println();
			
			System.out.println("Do you wish to try enter new values? Please enter yes/no");
			repeat = input.next();
			
		}while(repeat.equalsIgnoreCase("yes"));

	}

}
