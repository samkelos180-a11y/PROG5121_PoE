package com.mycompany.prog5121_poe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Login class.
 * @author Samkelo
 
 */
public class LoginTest {

    Login login = new Login();

    // TEST 1: Username is correctly formatted ("kyl_1") -> true
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    // TEST 2: Username is incorrectly formatted ("kyle!!!!!!") -> false
    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    // TEST 3: Password meets complexity requirements ("Ch&sec@ke99!") -> true
    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }

    // TEST 4: Password fails complexity ("password") -> false
    @Test
    public void testPasswordFailsComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // TEST 5: Cell phone correct ("+27838968976") -> true
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    // TEST 6: Cell phone incorrect ("08966553") -> false
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // TEST 7: Login successful -> true
    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    // TEST 8: Login failed -> false
    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    // TEST 9: assertEquals - Register success message
    @Test
    public void testRegisterUserSuccessMessage() {
        String result = login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.", result);
    }

    // TEST 10: assertEquals - Username fail message
    @Test
    public void testRegisterUserUsernameFailMessage() {
        String result = login.registerUser("kyle!!!!!!", "Ch&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    // TEST 11: assertEquals - Password fail message
    @Test
    public void testRegisterUserPasswordFailMessage() {
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    // TEST 12: assertEquals - Cell fail message
    @Test
    public void testRegisterUserCellFailMessage() {
        String result = login.registerUser("kyl_1", "Ch&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }
}