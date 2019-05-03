package com.murk.dk021.core.utils.reader;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.murk.dk021.core.utils.ClassificatorsDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
@Slf4j
public class ClassificatorReaderImpl implements ClassificatorReader {
    private  ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("classificatorDeser", Version.unknownVersion());
        module.addDeserializer(Map.class, new ClassificatorsDeserializer(null));
        mapper.registerModule(module);
    }

    public  Map<String,String> read(String path)
    {
        Map<String,String> classificators = null;
        try {
            URL jsonUrl = new URL(path);
            classificators = (Map<String,String>)  mapper.readValue(jsonUrl,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (classificators != null)
        {
            log.warn("END read classifictors from path = {}, classificators length = {}",path, classificators.size());
        } else
        {
            log.error("Fail get data from path = {}",path);
        }

        return classificators;
    }
}
