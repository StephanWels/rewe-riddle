package com.ecomhack.riddle.sphere;


import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

public class SphereTask extends AsyncTask<URL, Integer, Long> {

    private static final String TAG = "SphereActivity";

    protected Long doInBackground(URL... urls) {

            // The connection URL
            String url = "https://ajax.googleapis.com/ajax/" +
                    "services/search/web?v=1.0&q={query}";

// Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

// Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(url, String.class, "Android");

        Log.v(TAG, "testing" + result);

return 0L;

    }

    protected void onProgressUpdate(Integer... progress) {
        Log.v(TAG, "onProgressUpdate" );
    }

    protected void onPostExecute(Long result) {
        Log.v(TAG, "onPostExecute" );
    }
}