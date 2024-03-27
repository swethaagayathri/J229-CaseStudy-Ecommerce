package com.hexaware.main;

import com.hexaware.controller.EcomController;
import com.hexaware.controller.EcomInterface;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.util.Scanner;

/**
 * Main class for the E-commerce application. Allows users to interact with the
 * system by performing various operations.
 * 
 * @author Swethaa Gayathri K
 * @version 1.0
 */
public class EcomApp {
	public static void main(String[] args) {
		EcomInterface ecomController = new EcomController();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Ecom App!");
		String choice;
		do {
			System.out.println("E-commerce Operations Menu:");
			System.out.println("1. Register Customer");
			System.out.println("2. Create Product");
			System.out.println("3. Delete Product");
			System.out.println("4. Delete Customer");
			System.out.println("5. Add to Cart");
			System.out.println("6. Remove from Cart");
			System.out.println("7. View Cart of a Specific Customer");
			System.out.println("8. Place Order");
			System.out.println("9. View a Specific Customer Order");
			System.out.println("Enter your choice:");

			int option = sc.nextInt();
			switch (option) {
			case 1:
				ecomController.createCustomer();
				break;
			case 2:
				ecomController.createProduct();
				break;
			case 3:
				try {
					ecomController.deleteProduct();
				} catch (ProductNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					ecomController.deleteCustomer();
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				ecomController.addToCart();
				break;
			case 6:
				try {
					ecomController.removeFromCart();
				} catch (ProductNotFoundException | CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				try {
					ecomController.getAllFromCart();
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				ecomController.placeOrder();
				break;
			case 9:
				try {
					ecomController.getOrdersByCustomer();
				} catch (OrderNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Invalid choice! Please enter a number between 1 and 9.");
			}
			System.out.println("Do you want to continue (Y/N)?");
			choice = sc.next();
		} while (choice.equalsIgnoreCase("Y"));
		System.out.println("Thank you for using the ecom app!");
	}
}
