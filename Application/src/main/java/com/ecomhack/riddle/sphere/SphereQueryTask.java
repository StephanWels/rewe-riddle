package com.ecomhack.riddle.sphere;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Type;
import com.ecomhack.riddle.sphere.models.AuthResponse;
import com.ecomhack.riddle.sphere.models.QueryResult;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public abstract class SphereQueryTask<T> extends AsyncTask<AuthResponse, Integer, QueryResult<T>> {
    private static final String SPHERE_API_URL = "https://api.sphere.io/rewe-riddle-2/";

    abstract String tag();

    abstract String endpoint();

    abstract Type type();

    @Override
    protected QueryResult<T> doInBackground(AuthResponse... authResponses) {
        Log.v(tag(), "Starting request with " + authResponses[0]);
        String url = SPHERE_API_URL + endpoint();
        HttpEntity<String> requestEntity = requestEntity(authResponses[0]);
        ResponseEntity<String> result = restTemplate().exchange(url, HttpMethod.GET, requestEntity, String.class);
        Log.v(tag(), result.getBody());
        return new Gson().fromJson(result.getBody(), type());
    }

    private HttpEntity<String> requestEntity(AuthResponse authResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String authHeader = "Bearer " + authResponse.getAccessToken();
        headers.add("Authorization", authHeader);
        return new HttpEntity<>(null, headers);
    }

    private RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.v(tag(), progress[0].toString());
    }

    protected void onPostExecute(QueryResult<T> result) {
        Log.v(tag(), result.toString());
    }
}