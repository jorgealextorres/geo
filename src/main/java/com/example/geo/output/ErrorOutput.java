package com.example.geo.output;

public class ErrorOutput {

    private String originalSearch;
    private String error;

    public ErrorOutput() {
    }

    public ErrorOutput(String originalSearch, String error) {
        this.originalSearch = originalSearch;
        this.error = error;
    }

    public String getOriginalSearch() {
        return originalSearch;
    }

    public void setOriginalSearch(String originalSearch) {
        this.originalSearch = originalSearch;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
