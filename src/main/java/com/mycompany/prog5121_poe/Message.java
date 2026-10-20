package com.mycompany.prog5121_poe;
/* @author Samkelo*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static int totalMessagesSent = 0;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        // Checks if it starts with '+' and the total length is between 10 and 12 characters
        // (e.g., +27718693002 is valid, 08575975889 is invalid)
        if (this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() >= 10 && this.recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) return "";
        String idPart = this.messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase().replaceAll("[^A-Z]", "");
        String lastWord = words[words.length - 1].toUpperCase().replaceAll("[^A-Z]", "");
        return idPart + ":" + this.messageNumber + ":" + firstWord + lastWord;
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public void storeMessage() {
        try (FileWriter file = new FileWriter("messages.json", true)) {
            String json = "{\n" +
                    "  \"messageID\": \"" + this.messageID + "\",\n" +
                    "  \"messageHash\": \"" + this.messageHash + "\",\n" +
                    "  \"recipient\": \"" + this.recipient + "\",\n" +
                    "  \"message\": \"" + this.messageText + "\"\n" +
                    "},\n";
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append("Message ID: ").append(m.messageID)
              .append("\nMessage Hash: ").append(m.messageHash)
              .append("\nRecipient: ").append(m.recipient)
              .append("\nMessage: ").append(m.messageText).append("\n\n");
        }
        return sb.toString();
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    private String generateID() {
        Random rand = new Random();
        long id = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
}