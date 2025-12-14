package com.example.movies.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class GenreConverter implements AttributeConverter<Genre, String> {

    @Override
    public String convertToDatabaseColumn(Genre attribute) {
        if (attribute == null) return null;
        return attribute.name();
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        String normalized = dbData.trim().toUpperCase()
                .replace(' ', '_')
                .replace('-', '_');
        if ("SCI_FI".equals(normalized)) {
            normalized = "SCIFI";
        }
        return Genre.valueOf(normalized);
    }
}
