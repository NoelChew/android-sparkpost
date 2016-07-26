package com.noelchew.sparkpostutil.library;

/**
 * Created by noelchew on 9/1/15.
 */
public class SparkPostSender {
    private String name;
    private String email;
    public SparkPostSender(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
