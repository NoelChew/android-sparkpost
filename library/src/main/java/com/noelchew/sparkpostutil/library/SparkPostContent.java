package com.noelchew.sparkpostutil.library;

/**
 * Created by noelchew on 9/1/15.
 */
public class SparkPostContent {
    private SparkPostSender from;
    private String subject;
    private String text;

    public SparkPostContent(SparkPostSender from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    public SparkPostSender getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
