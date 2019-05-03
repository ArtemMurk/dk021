package com.murk.dk021.core.utils.converter;

import com.murk.dk021.core.model.Classificator;
import com.murk.dk021.core.to.ClassificatorTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static mocks.model.MockModels.*;
import static mocks.to.MockClassificatorTO.*;
import static org.assertj.core.api.Assertions.assertThat;

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
        Map<Integer,Classificator> actual = classificatorConverter.convert(ALL_CLASSIFICATORS_TO);

        assertThat(ALL_CLASSIFICATORS_MODELS).containsAllEntriesOf((actual));
        assertThat(ALL_CLASSIFICATORS_MODELS).hasSameSizeAs((actual));
    }
}