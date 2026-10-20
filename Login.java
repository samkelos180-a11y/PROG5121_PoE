/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121_poe;

/**
 *
 * @author Samkelo
 */
public class Login {
    // Store user details 
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;
    
    //Check if username contains an underscore and is no more tha 5 characters long
    
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;

    }
    //Password complexity
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }
    //Checks international code number and is less than 12 characters
        public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) {
            return false;
        }
        // Regex: starts with +27, followed by 7 to 9 digits
        String regex = "^\\+27[0-9]{7,9}$";
        return cellNumber.matches(regex);
        }
    //Registers the user and returns the appropriate message based on validation
    public String registerUser(String username, String password, String cellNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
         if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Store the details for later login
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }
    //Verifies login details match the stored registration details.
    public boolean loginUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }
    //Returns login message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters and setters for first name and last name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStoredUsername() {
        return storedUsername;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public String getStoredCellNumber() {
        return storedCellNumber;
    }
}