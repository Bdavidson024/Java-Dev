/**
 * @author Brad Davidson
 * Date: 05/02/2022
 * Purpose: vehicle class motorized vehicle
 *
 */
import java.util.Scanner;

public class Vehicle_Abstraction {

	public static void main(String[] args) {
		Bicycle bicycle = new Bicycle("Bobby",2);
		MotorizedVehicle MotorizedVehicle = new MotorizedVehicle("Brandon", 4, 25, 100);
		String owner = null;
		int wheels = 0;
		int litersDisplaced = 0;
		int horsepower = 0;
		
		System.out.println("The bicycle: ");
		System.out.println("Owned by: " + owner); bicycle.owner();
		System.out.println("The bicycle has " + wheels + " wheels."); bicycle.wheels();
		
		System.out.println("The motorized vehicle: ");
		System.out.println("The motorized vehicle is owned by: " + owner); MotorizedVehicle.owner();
		System.out.println("The motorized vehicle has " + wheels + " wheels."); MotorizedVehicle.wheels();
		System.out.println("The motorized vehicle displaces " + litersDisplaced + " liters."); MotorizedVehicle.getLitersDisplaced();
		System.out.println("The motorized vehicle has " + horsepower + " horsepower."); MotorizedVehicle.getHowMuchHorsepower();

	}

}
