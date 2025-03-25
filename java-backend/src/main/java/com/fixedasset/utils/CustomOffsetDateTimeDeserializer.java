package com.fixedasset.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;  // support ISO 8601 (2025-03-25T08:48:28Z)
    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // support yyyy-MM-dd HH:mm:ss

    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        try {
            // Try to use ISO 8601 for parse
            return OffsetDateTime.parse(date, ISO_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                // If fail case will try to use yyyy-MM-dd HH:mm:ss for parse（Assume it is UTC format）
                return OffsetDateTime.parse(date + " +00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss X"));
            } catch (DateTimeParseException ex) {
                throw new IOException("Unkown date: " + date);
            }
        }
    }
}