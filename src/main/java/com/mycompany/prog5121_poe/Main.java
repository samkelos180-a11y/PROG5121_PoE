package com.mycompany.prog5121_poe;
// Part 2 & Part 3 Development
import java.util.Scanner;

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

        login.setFirstName(firstName);
        login.setLastName(lastName);

        String registrationMessage = login.registerUser(username, password, cellNumber);
        System.out.println("\n" + registrationMessage);

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

        // Check if login was successful before showing the menu
        if (!login.loginUser(loginUsername, loginPassword)) {
            System.out.println("Login failed. Exiting.");
            scanner.close();
            return;
        }

        // --- QuickChat Menu (Part 2 & Part 3) ---
        System.out.println("\nWelcome to QuickChat.");
        
        // Initialize Part 3 Manager and load the test data
        MessageManager manager = new MessageManager();
        manager.populateTestData();
        
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMenu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages (Part 3 Features)");
            System.out.println("4) Quit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.print("How many messages do you wish to enter? ");
                    int numMessages = Integer.parseInt(scanner.nextLine());
                    
                    for (int i = 0; i < numMessages; i++) {
                        // Ask for recipient
                        String recipient = "";
                        while (true) {
                            System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                            recipient = scanner.nextLine();
                            if (!recipient.matches("^\\+[0-9]{9,10}$")) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            } else {
                                break;
                            }
                        }

                        // Ask for message text
                        String messageText = "";
                        while (true) {
                            System.out.print("Enter your message (max 250 characters): ");
                            messageText = scanner.nextLine();
                            if (messageText.length() > 250) {
                                System.out.println("Message exceeds 250 characters by " + (messageText.length() - 250) + "; please reduce the size.");
                            } else {
                                System.out.println("Message ready to send.");
                                break;
                            }
                        }

                        // Create the message
                        Message msg = new Message(i + 1, recipient, messageText);
                        System.out.println("Message ID generated: " + msg.getMessageID());
                        
                        // Action menu
                        System.out.println("\nOptions:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Choose: ");
                        int action = Integer.parseInt(scanner.nextLine());
                        
                        String result = msg.SentMessage(action);
                        System.out.println(result);
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;
                    
                case 3:
                    // --- PART 3 MENU ---
                    boolean backToMain = false;
                    while (!backToMain) {
                        System.out.println("\n--- Stored Messages Menu ---");
                        System.out.println("a. Display sender and recipient of all stored messages");
                        System.out.println("b. Display the longest stored message");
                        System.out.println("c. Search for a message ID");
                        System.out.println("d. Search for messages by recipient");
                        System.out.println("e. Delete a message using the message hash");
                        System.out.println("f. Display a report of all stored messages");
                        System.out.println("g. Back to Main Menu");
                        System.out.print("Choose an option: ");
                        String part3Choice = scanner.nextLine().toLowerCase();

                        switch (part3Choice) {
                            case "a":
                                System.out.println(manager.displayStoredSenderRecipient());
                                break;
                            case "b":
                                System.out.println("Longest message: " + manager.getLongestStoredMessage());
                                break;
                            case "c":
                                System.out.print("Enter Message ID to search: ");
                                System.out.println("Result: " + manager.searchByMessageID(scanner.nextLine()));
                                break;
                            case "d":
                                System.out.print("Enter Recipient number to search: ");
                                System.out.println("Result:\n" + manager.searchByRecipient(scanner.nextLine()));
                                break;
                            case "e":
                                System.out.print("Enter Message Hash to delete: ");
                                System.out.println(manager.deleteMessageByHash(scanner.nextLine()));
                                break;
                            case "f":
                                System.out.println(manager.displayReport());
                                break;
                            case "g":
                                backToMain = true;
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 4:
                    quit = true;
                    System.out.println("Total messages sent: " + Message.totalMessagesSent);
                    if (Message.totalMessagesSent > 0) {
                        System.out.println("\n--- Sent Messages ---");
                        System.out.println(Message.sentMessages.get(0).printMessages()); 
                    }
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }
}