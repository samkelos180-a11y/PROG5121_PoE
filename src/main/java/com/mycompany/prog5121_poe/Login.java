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
}
