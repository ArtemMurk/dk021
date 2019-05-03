package com.murk.dk021.core.utils.converter;

import com.murk.dk021.core.model.Classificator;
import com.murk.dk021.core.to.ClassificatorTO;

import java.util.Map;

public interface ClassificatorConverter {
    ClassificatorTO convert(Classificator classificator);
    Map<Integer,Classificator> convert(Map<String,String> classificatorsTo);
}
