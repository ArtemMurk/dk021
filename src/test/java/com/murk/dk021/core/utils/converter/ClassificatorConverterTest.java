package com.murk.dk021.core.utils.converter;

import com.murk.dk021.core.model.Classificator;
import com.murk.dk021.core.to.ClassificatorTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static mocks.model.MockModels.CLASSIFICATOR_MODEL_1;
import static mocks.model.MockModels.NODES_FOR_CLASSIFICATOR_MODEL_1;
import static mocks.to.MockClassificatorTO.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class ClassificatorConverterTest {

    @Autowired
    ClassificatorConverter classificatorConverter;

    @Test
    public void convertClassificatorSuccess()
    {
        ClassificatorTO actual = classificatorConverter.convert(CLASSIFICATOR_MODEL_1);
        assertThat(CLASSIFICATOR_TO_1).isEqualToComparingFieldByField(actual);
    }

    @Test
    public void convertTOsSuccess()
    {
        Map<String,String> classificatorsTOs = new HashMap<>();
        classificatorsTOs.put(CLASSIFICATOR_TO_1.getCode(),CLASSIFICATOR_TO_1.getName());
        classificatorsTOs.put(CLASSIFICATOR_TO_2.getCode(),CLASSIFICATOR_TO_2.getName());
        classificatorsTOs.put(CLASSIFICATOR_TO_3.getCode(),CLASSIFICATOR_TO_3.getName());


        Map<Integer,Classificator> actual = classificatorConverter.convert(classificatorsTOs);

        Map<Integer,Classificator> expected = NODES_FOR_CLASSIFICATOR_MODEL_1.stream().collect(Collectors.toMap(Classificator::getId, e -> e));
        expected.put(CLASSIFICATOR_MODEL_1.getId(),CLASSIFICATOR_MODEL_1);

        assertThat(expected).containsAllEntriesOf((actual));
    }
}