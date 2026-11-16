package com.mycompany.prog5121_poe;

import java.util.ArrayList;

public class MessageManager {
    
    public ArrayList<Message> sentMessages = new ArrayList<>();
    public ArrayList<Message> disregardedMessages = new ArrayList<>();
    public ArrayList<Message> storedMessages = new ArrayList<>();
    public ArrayList<String> messageHashes = new ArrayList<>();
    public ArrayList<String> messageIDs = new ArrayList<>();

    public void populateTestData() {
        // Test Data 1
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.setFlag("Sent");
        
        // Test Data 2
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.setFlag("Stored");
        
        // Test Data 3
        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        msg3.setFlag("Disregard");
        
        // Test Data 4
        Message msg4 = new Message(4, "0838884567", "It is dinner time!");
        msg4.setFlag("Sent");
        
        // Test Data 5
        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        msg5.setFlag("Stored");

        Message[] allMessages = {msg1, msg2, msg3, msg4, msg5};

        for (Message msg : allMessages) {
            messageIDs.add(msg.getMessageID());
            messageHashes.add(msg.getMessageHash());
            
            if (msg.getFlag().equalsIgnoreCase("Sent")) {
                sentMessages.add(msg);
            } else if (msg.getFlag().equalsIgnoreCase("Stored")) {
                storedMessages.add(msg);
            } else if (msg.getFlag().equalsIgnoreCase("Disregard")) {
                disregardedMessages.add(msg);
            }
        }
    }

    public String displayStoredSenderRecipient() {
        StringBuilder result = new StringBuilder();
        for (Message msg : storedMessages) {
            result.append("Sender: Developer | Recipient: ").append(msg.getRecipient()).append("\n");
        }
        return result.toString();
    }

    public String getLongestStoredMessage() {
        String longestMsg = "";
        for (Message msg : storedMessages) {
            if (msg.getMessageText().length() > longestMsg.length()) {
                longestMsg = msg.getMessageText();
            }
        }
        return longestMsg;
    }

    public String searchByMessageID(String id) {
        for (Message msg : storedMessages) { 
            if (msg.getMessageID().equals(id)) {
                return msg.getMessageText();
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        for (Message msg : storedMessages) { 
            if (msg.getRecipient().equals(recipient)) {
                result.append(msg.getMessageText()).append("\n");
            }
        }
        if (result.length() == 0) return "No messages found for this recipient.";
        return result.toString().trim();
    }

    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                String deletedMsg = storedMessages.get(i).getMessageText();
                storedMessages.remove(i);
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    public String displayReport() {
        StringBuilder report = new StringBuilder();
        for (Message msg : storedMessages) {
            report.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
            report.append("Recipient: ").append(msg.getRecipient()).append("\n");
            report.append("Message: ").append(msg.getMessageText()).append("\n");
            report.append("-------------------------\n");
        }
        return report.toString();
    }
}