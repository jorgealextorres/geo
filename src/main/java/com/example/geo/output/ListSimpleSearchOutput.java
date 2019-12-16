package com.example.geo.output;

import java.util.List;

public class ListSimpleSearchOutput {
    List<SimpleSearchOutput> locations;
    List<ErrorOutput> errors;

    public ListSimpleSearchOutput(List<SimpleSearchOutput> locations, List<ErrorOutput> errors) {
        this.locations = locations;
        this.errors = errors;
    }
}
