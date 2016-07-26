package com.noelchew.sparkpostutil.library;

/**
 * Created by noelchew on 7/22/16.
 */
public class SparkPostError {
    private String message;
    private String description;
    private String code;

    public SparkPostError(String message, String description, String code) {
        this.message = message;
        this.description = description;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
