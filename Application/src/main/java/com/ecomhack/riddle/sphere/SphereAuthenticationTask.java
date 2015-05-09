package com.ecomhack.riddle.sphere;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ecomhack.riddle.models.AuthResponse;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

public class SphereAuthenticationTask extends AsyncTask<URL, Integer, AuthResponse> {
    private static final String TAG = SphereAuthenticationTask.class.getSimpleName();

    private static final String SPHERE_USER = "il1AWMhTtKeBoTsldA-r31GX";
    private static final String SPHERE_PASSWORD = "96bG47J4diYfudRj0JBf52qZopBswBcv";
    private static final String SPHERE_USER_PASSWORD = SPHERE_USER + ":" + SPHERE_PASSWORD;

    private static final String SPHERE_AUTH_URL = "https://auth.sphere.io/oauth/token";
    private static final String SPHERE_AUTH_SCOPE = "grant_type=client_credentials&scope=manage_project:rewe-riddle-2";

    protected AuthResponse doInBackground(URL... urls) {
        Log.v(TAG, "Starting auth request");
        ResponseEntity<String> result = restTemplate().exchange(SPHERE_AUTH_URL, HttpMethod.POST, httpEntityAuth(), String.class);
        return new Gson().fromJson(result.getBody(), AuthResponse.class);
    }

    private HttpEntity<String> httpEntityAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String authHeader = "Basic " + Base64.encodeToString(SPHERE_USER_PASSWORD.getBytes(), Base64.NO_WRAP);
        headers.add("Authorization", authHeader);
        return new HttpEntity<>(SPHERE_AUTH_SCOPE, headers);
    }

    private RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.v(TAG, progress[0].toString());
    }

    protected void onPostExecute(AuthResponse result) {
        Log.v(TAG, result.toString());
    }
}