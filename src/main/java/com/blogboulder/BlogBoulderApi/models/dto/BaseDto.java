package com.blogboulder.BlogBoulderApi.models.dto;

import ma.glasnost.orika.MapperFacade;

public class BaseDto {

    public <T> T toModel(Class<T> clazz, MapperFacade mapper) throws RuntimeException {
        try {
            return mapper.map(this, clazz);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Error converting to class %s, message %s",
                            clazz.getTypeName(),
                            e.getLocalizedMessage()));
        }
    }
}
