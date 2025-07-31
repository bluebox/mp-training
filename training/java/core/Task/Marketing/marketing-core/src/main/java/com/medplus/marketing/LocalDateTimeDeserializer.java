package com.medplus.marketing;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        long epochTime = json.getAsJsonPrimitive().getAsLong();
        Instant instant = Instant.ofEpochMilli(epochTime);
        return instant.atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime();
    }
}
