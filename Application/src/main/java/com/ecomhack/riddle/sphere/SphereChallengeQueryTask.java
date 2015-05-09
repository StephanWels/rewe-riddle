package com.ecomhack.riddle.sphere;

import java.lang.reflect.Type;

import com.ecomhack.riddle.sphere.models.AuthResponse;
import com.ecomhack.riddle.sphere.models.Challenge;
import com.ecomhack.riddle.sphere.models.QueryResult;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.reflect.TypeToken;

public class SphereChallengeQueryTask extends SphereQueryTask<QueryResult<Challenge>> {
    private static final String TAG = SphereChallengeQueryTask.class.getSimpleName();

    public SphereChallengeQueryTask(AuthResponse authResponse) {
        super(authResponse);
    }

    @Override
    String tag() {
        return TAG;
    }

    @Override
    String endpoint() {
        return "categories";
    }

    @Override
    Type type() {
        return new TypeToken<QueryResult<Challenge>>() {}.getType();
    }

}