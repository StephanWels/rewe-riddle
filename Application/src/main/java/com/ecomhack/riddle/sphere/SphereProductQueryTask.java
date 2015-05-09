package com.ecomhack.riddle.sphere;

import com.ecomhack.riddle.sphere.models.AuthResponse;
import com.ecomhack.riddle.sphere.models.Product;
import com.ecomhack.riddle.sphere.models.QueryResult;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SphereProductQueryTask extends SphereQueryTask<QueryResult<Product>> {
    private static final String TAG = SphereProductQueryTask.class.getSimpleName();
    private final String challengeId;

    public SphereProductQueryTask(AuthResponse authResponse, String challengeId) {
        super(authResponse);
        this.challengeId = challengeId;
    }

    @Override
    String tag() {
        return TAG;
    }

    @Override
    String endpoint() {
        return "product-projections/search?staged=true&sort=variants.attributes.order asc&filter.query=categories.id:\"" + challengeId + "\"";
    }

    @Override
    Type type() {
        return new TypeToken<QueryResult<Product>>() {}.getType();
    }

}