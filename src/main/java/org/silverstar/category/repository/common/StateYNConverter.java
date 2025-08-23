package org.silverstar.category.repository.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.silverstar.category.common.contant.YNType;

@Converter
public class StateYNConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return YNType.fromBoolean(attribute);
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return YNType.toBoolean(dbData);
    }
}
