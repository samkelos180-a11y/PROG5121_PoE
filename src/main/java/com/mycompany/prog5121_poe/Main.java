package com.mycompany.prog5121_poe;

import java.util.Scanner;
/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author Samkelo
 */
public class Main {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== QuickChat Registration ===");

        // --- Register ---
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter SA cell phone number (e.g. +27838968976): ");
        String cellNumber = scanner.nextLine();

        // Set names for welcome message
        login.setFirstName(firstName);
        login.setLastName(lastName);

        // Call registerUser and print the result
        String registrationMessage = login.registerUser(username, password, cellNumber);
        System.out.println("\n" + registrationMessage);

        // If registration failed, stop the program
        if (!registrationMessage.contains("successfully")) {
            System.out.println("Registration failed. Please restart and try again.");
            scanner.close();
            return;
        }

        // --- Login ---
        System.out.println("\n=== QuickChat Login ===");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        String loginMessage = login.returnLoginStatus(loginUsername, loginPassword);
        System.out.println("\n" + loginMessage);

        scanner.close();
    }
}