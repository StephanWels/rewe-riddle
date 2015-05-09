package com.ecomhack.riddle.sphere.models;

public class Configuration {
    private final String projectKey;
    private final String clientId;
    private final String clientSecret;

    public Configuration(String projectKey, String clientId, String clientSecret) {
        this.projectKey = projectKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
