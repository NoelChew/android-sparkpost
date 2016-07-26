package com.noelchew.sparkpostutil.library;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SparkPostEmailJsonRequest {
    public static final String API_BASE_URL = "https://api.sparkpost.com/api/v1/";
    public static final String EMAIL_API_PATH = "transmissions?num_rcpt_errors=3";

    private ArrayList<SparkPostRecipient> recipients;
    private SparkPostContent content;

    public SparkPostEmailJsonRequest(String subject, String message, ArrayList<SparkPostRecipient> recipients, SparkPostSender sender) {
        this.recipients = recipients;
        this.content = new SparkPostContent(sender, subject, message);
    }

    public SparkPostEmailJsonRequest(String subject, String message, String recipientEmail, String senderEmail, String senderName) {
        SparkPostRecipient recipient = new SparkPostRecipient(recipientEmail);
        this.recipients = new ArrayList<>();
        this.recipients.add(recipient);

        SparkPostSender sender = new SparkPostSender("feedback@sparkpostbox.com", senderName);
        this.content = new SparkPostContent(sender, subject, message);
    }

    public String toString() {
        return new Gson().toJson(this);
    }


}
