package com.luciana.challenge_factorIT.utils;

import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ModelMapper getModelMapper() {
        return MODEL_MAPPER;
    }
    public static LocalDateTime parseToLocalDateTime(String dateString) {
        return LocalDateTime.parse(dateString, FORMATTER);
    }


}
