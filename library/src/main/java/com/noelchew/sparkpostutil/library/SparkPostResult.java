package com.noelchew.sparkpostutil.library;

/**
 * Created by noelchew on 4/27/16.
 */
public class SparkPostResult {
    private int total_rejected_recipients;
    private int total_accepted_recipients;
    private String id;

    public SparkPostResult(String id, int total_accepted_recipients, int total_rejected_recipients) {
        this.id = id;
        this.total_accepted_recipients = total_accepted_recipients;
        this.total_rejected_recipients = total_rejected_recipients;
    }

    public String getId() {
        return id;
    }

    public int getTotal_accepted_recipients() {
        return total_accepted_recipients;
    }

    public int getTotal_rejected_recipients() {
        return total_rejected_recipients;
    }
}
