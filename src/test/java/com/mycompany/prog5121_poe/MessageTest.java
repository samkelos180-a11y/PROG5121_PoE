package com.mycompany.prog5121_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLength() {
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(validMessage.length() <= 250, "Message ready to send.");
        
        String longMessage = "a".repeat(251);
        assertTrue(longMessage.length() > 250, "Message exceeds 250 characters by 1; please reduce the size.");
    }

    @Test
    public void testRecipientFormat() {
        // Using the exact test data from your assignment document
        Message validMsg = new Message(1, "+27718693002", "Test message");
        assertEquals("Cell phone number successfully captured.", validMsg.checkRecipientCell());
        
        Message invalidMsg = new Message(2, "08575975889", "Test message");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", invalidMsg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message testMsg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expectedHash = testMsg.getMessageID().substring(0, 2) + ":0:HITONIGHT";
        assertEquals(expectedHash, testMsg.getMessageHash());
    }

    @Test
    public void testMessageIDCreation() {
        Message testMsg = new Message(1, "+27718693002", "Test message");
        assertNotNull(testMsg.getMessageID());
        assertTrue(testMsg.getMessageID().length() <= 10);
    }

    @Test
    public void testSentMessage() {
        Message testMsg = new Message(1, "+27718693002", "Test message");
        assertEquals("Message successfully sent.", testMsg.SentMessage(1));
    }
}