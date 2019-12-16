package com.example.geo.output;

public class SimpleSearchOutput {
    private String originalSearch;
    private GeoLocation result;

    public SimpleSearchOutput() {
    }

    public SimpleSearchOutput(String originalSearch, GeoLocation result) {
        this.originalSearch = originalSearch;
        this.result = result;
    }

    public String getOriginalSearch() {
        return originalSearch;
    }

    public void setOriginalSearch(String originalSearch) {
        this.originalSearch = originalSearch;
    }

    public GeoLocation getResult() {
        return result;
    }

    public void setResult(GeoLocation result) {
        this.result = result;
    }
}
