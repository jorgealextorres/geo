package com.example.geo.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RemoteLocatorConfig {
    @Value("${LocatorREST.url}")
    private String url;

    @Value("${LocatorREST.searchField}")
    private String searchField;

    public RemoteLocatorConfig() {
    }

    public RemoteLocatorConfig(String url, String searchField) {
        this.url = url;
        this.searchField = searchField;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
}
