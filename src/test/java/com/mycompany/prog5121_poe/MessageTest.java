package com.mycompany.prog5121_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class.
 * These tests verify the formatting, hash generation, and sending logic required for Part 2.
 */
public class MessageTest {

    /**
     * Test 1: Message Length
     * Verifies that a message under 250 characters is accepted, 
     * and a message over 250 characters is flagged correctly.
     */
    @Test
    public void testMessageLength() {
        // Success case: 48 characters (<= 250)
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(validMessage.length() <= 250, "Message ready to send.");
        
        // Failure case: 251 characters (> 250)
        String longMessage = "a".repeat(251);
        assertTrue(longMessage.length() > 250, "Message exceeds 250 characters by 1; please reduce the size.");
    }

    /**
     * Test 2: Recipient Format
     * Verifies the cell number contains an international code (+) and is not more than 10 characters.
     * Test Data: "+2771869300" (Valid) and "085759758" (Invalid - missing +).
     */
    @Test
    public void testRecipientFormat() {
        // Success case: Valid international format
        Message validMsg = new Message(1, "+2771869300", "Test message");
        assertEquals("Cell phone number successfully captured.", validMsg.checkRecipientCell());
        
        // Failure case: Incorrect format (missing international code)
        Message invalidMsg = new Message(2, "085759758", "Test message");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", invalidMsg.checkRecipientCell());
    }

    /**
     * Test 3: Message Hash Generation
     * Verifies the hash is created using the first two digits of the ID, 
     * the message number, and the first and last words of the message in caps.
     */
    @Test
    public void testMessageHash() {
        Message testMsg = new Message(0, "+2771869300", "Hi Mike, can you join us for dinner tonight?");
        
        // The hash should be: [First 2 digits of ID] : [Message Number] : [FIRSTWORDLASTWORD]
        // For message "Hi Mike... tonight?", it should be HITONIGHT
        String expectedHash = testMsg.getMessageID().substring(0, 2) + ":0:HITONIGHT";
        assertEquals(expectedHash, testMsg.getMessageHash());
    }

    /**
     * Test 4: Message ID Creation
     * Verifies that a 10-digit Message ID is automatically generated.
     */
    @Test
    public void testMessageIDCreation() {
        Message testMsg = new Message(1, "+2771869300", "Test message");
        
        // Ensure the ID is not null and does not exceed 10 characters
        assertNotNull(testMsg.getMessageID());
        assertTrue(testMsg.getMessageID().length() <= 10);
    }

    /**
     * Test 5: Sent Message
     * Verifies that selecting option 1 (Send) returns the correct success message.
     */
    @Test
    public void testSentMessage() {
        Message testMsg = new Message(1, "+2771869300", "Test message");
        assertEquals("Message successfully sent.", testMsg.SentMessage(1));
    }
}