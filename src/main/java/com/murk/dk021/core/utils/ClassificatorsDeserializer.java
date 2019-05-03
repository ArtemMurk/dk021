package com.murk.dk021.core.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ClassificatorsDeserializer extends StdDeserializer<Map> {

    public ClassificatorsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        Map<String,String> classificators = new HashMap<>();

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<Map.Entry<String,JsonNode>> iteratorCS = node.fields();

        while (iteratorCS.hasNext())
        {
            Map.Entry<String,JsonNode> classificatorJson = iteratorCS.next();

            String code = classificatorJson.getKey();
            String name = classificatorJson.getValue().asText();

            classificators.put(code,name);
        }
            return classificators;
    }
}
