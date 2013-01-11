package com.rudilucas.ideas.controller;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {
    private static final Logger log = LoggerFactory.getLogger(JSONUtils.class);

    private JSONUtils() {
    }
    public static String toJSON(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        String returnString = "ERROR";
        try {
            returnString = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            log.error("toJSON method throws JSON Generation Exception: ", e.getStackTrace());
        } catch (JsonMappingException e) {
            log.error("toJSON method throws JSON Mapping Exception: ", e.getStackTrace());
        } catch (IOException e) {
            log.error("toJSON method throws IO Exception: ", e.getStackTrace());
        }

        return returnString;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> fromJSON(String json) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> objectJson = new HashMap<String, Object>();
        try {
            objectJson = mapper.readValue(json, HashMap.class);
        } catch (JsonParseException e) {
            log.error("fromJSON method throws JSON Parse Exception: ", e.getStackTrace());
        } catch (JsonMappingException e) {
            log.error("fromJSON method throws JSON Mapping Exception: ", e.getStackTrace());
        } catch (IOException e) {
            log.error("fromJSON method throws IO Exception: ", e.getStackTrace());
        }

        return objectJson;

    }
}
