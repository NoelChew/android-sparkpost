package com.noelchew.sparkpostutil.library;

import java.util.ArrayList;

/**
 * Created by noelchew on 9/1/15.
 */
public class SparkPostEmailMessage {
    private ArrayList<SparkPostRecipient> recipients;
    private SparkPostContent content;

    public SparkPostEmailMessage(ArrayList<SparkPostRecipient> recipients, SparkPostContent content) {
        this.recipients = recipients;
        this.content = content;
    }
}
