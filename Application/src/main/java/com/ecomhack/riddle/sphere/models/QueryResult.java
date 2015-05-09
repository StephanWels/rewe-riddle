package com.ecomhack.riddle.sphere.models;

import java.util.List;

public class QueryResult<T> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "results=" + results +
                '}';
    }
}
