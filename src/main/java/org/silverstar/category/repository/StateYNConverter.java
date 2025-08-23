package org.silverstar.category.repository;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StateYNConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) return StateYNType.N.name();  // null 처리
        return attribute ? StateYNType.Y.name() : StateYNType.N.name();
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) return Boolean.FALSE;
        return StateYNType.Y.name().equals(dbData);
    }
}
