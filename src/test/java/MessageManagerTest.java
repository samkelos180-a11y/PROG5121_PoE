package com.mycompany.prog5121_poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {
    
    private MessageManager manager;

    @BeforeEach
    public void setUp() {
        manager = new MessageManager();
        manager.populateTestData();
    }

    @Test
    public void testSentMessagesCorrectlyPopulated() {
        // Test Data: Developer entry for Test data for message 1-4
        String expected = "Did you get the cake?\nIt is dinner time!";
        StringBuilder actual = new StringBuilder();
        for (Message msg : manager.sentMessages) {
            actual.append(msg.getMessageText()).append("\n");
        }
        assertEquals(expected, actual.toString().trim());
    }

    @Test
    public void testLongestMessage() {
        // Test Data: message 1-4
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchForMessageID() {
        // Test Data: message 4. 
        // We must grab the dynamic ID since it's randomly generated
        String actualIdToSearch = manager.storedMessages.get(0).getMessageID(); 
        String expected = manager.storedMessages.get(0).getMessageText();
        assertEquals(expected, manager.searchByMessageID(actualIdToSearch));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        // Test Data: +27838884567
        String expected = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.";
        assertEquals(expected, manager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageByHash() {
        // Test Data: Test Message 2. 
        // We grab the dynamic hash from the array
        String actualHashToDelete = manager.storedMessages.get(0).getMessageHash();
        String expectedMessageText = manager.storedMessages.get(0).getMessageText();
        
        String expectedResult = "Message: \"" + expectedMessageText + "\" successfully deleted.";
        assertEquals(expectedResult, manager.deleteMessageByHash(actualHashToDelete));
        
        // Verify it was actually removed
        assertEquals(1, manager.storedMessages.size()); 
    }

    @Test
    public void testDisplayReport() {
        String report = manager.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
}