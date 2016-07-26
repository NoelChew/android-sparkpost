package com.noelchew.sparkpostutil.library;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by noelchew on 4/27/16.
 */
public class SparkPostResultWrapper {
    private ArrayList<SparkPostError> errors;
    private SparkPostResult results;

    public SparkPostResultWrapper(ArrayList<SparkPostError> errors, SparkPostResult results) {
        this.errors = errors;
        this.results = results;
    }

    public ArrayList<SparkPostError> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<SparkPostError> errors) {
        this.errors = errors;
    }

    public SparkPostResult getResults() {
        return results;
    }

    public void setResults(SparkPostResult results) {
        this.results = results;
    }

    public static SparkPostResultWrapper fromJson(String json) {
        return new Gson().fromJson(json, SparkPostResultWrapper.class);
    }
}
