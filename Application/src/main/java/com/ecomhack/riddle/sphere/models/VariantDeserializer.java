package com.ecomhack.riddle.sphere.models;

import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonArray;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonDeserializationContext;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonDeserializer;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonElement;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonObject;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonParseException;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class VariantDeserializer implements JsonDeserializer<Variant> {

    @Override
    public Variant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        List<Image> images = images(context, object);
        JsonArray attributes = object.get("attributes").getAsJsonArray();
        LocalizedStrings riddle = attribute(context, attributes, "riddle", LocalizedStrings.class);
        String beacon = attribute(context, attributes, "beacon", String.class);
        return new Variant(images, riddle, beacon);
    }

    private List<Image> images(JsonDeserializationContext context, JsonObject object) {
        Type imageType = new TypeToken<List<Image>>() {}.getType();
        return context.deserialize(object.get("images"), imageType);
    }

    private <T> T attribute(JsonDeserializationContext context, JsonArray attributes, String name, Class<T> clazz) {
        Iterator<JsonElement> iterator = attributes.iterator();
        while (iterator.hasNext()) {
            JsonObject current = iterator.next().getAsJsonObject();
            if (name.equals(current.get("name").getAsString())) {
                return context.deserialize(current.get("value"), clazz);
            }
        }
        return null;
    }
}
