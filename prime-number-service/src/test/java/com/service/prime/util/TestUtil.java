package com.service.prime.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.prime.model.ServiceResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TestUtil {

    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = TestUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static ServiceResponse jsonStringToServiceResponse(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, ServiceResponse.class);
        } catch (IOException ex) {
            return new ServiceResponse();
        }
    }

}
