package com.ecomhack.riddle.sphere;


import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ecomhack.riddle.authentication.AuthResponse;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;
import com.ecomhack.riddle.R;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

public class SphereTask extends AsyncTask<URL, Integer, Long> {

    private static final String SPHERE_USER = "il1AWMhTtKeBoTsldA-r31GX";
    private static final String SPHERE_PASSWORD = "96bG47J4diYfudRj0JBf52qZopBswBcv";
    private static final String SPHERE_USER_PASSWORD = SPHERE_USER + ":" + SPHERE_PASSWORD;
    private static final String SPHERE_AUTH_SCOPE = "grant_type=client_credentials&scope=manage_project:rewe-riddle-2";

    private static final String TAG = "SphereActivity";

    protected Long doInBackground(URL... urls) {

        // The connection URL
        String url = "https://auth.sphere.io/oauth/token";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


        // Make the HTTP GET request, marshaling the response to a String
        final String basicAuth = "Basic " + Base64.encodeToString(SPHERE_USER_PASSWORD.getBytes(), Base64.NO_WRAP);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", basicAuth);
        HttpEntity<String> entity = new HttpEntity<String>(SPHERE_AUTH_SCOPE, headers);

        String result = restTemplate.postForObject(url, entity, String.class);

        new Gson().fromJson(result, AuthResponse.class);

        Log.v(TAG, "testing" + result);

        return 0L;

    }

    protected void onProgressUpdate(Integer... progress) {
        Log.v(TAG, "onProgressUpdate");
    }

    protected void onPostExecute(Long result) {
        Log.v(TAG, "onPostExecute");
    }
}