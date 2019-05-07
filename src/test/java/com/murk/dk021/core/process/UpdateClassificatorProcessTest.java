package com.murk.dk021.core.process;

import com.murk.dk021.core.dao.ClassificatorDao;
import com.murk.dk021.core.utils.converter.ClassificatorConverter;
import com.murk.dk021.core.utils.reader.ClassificatorReader;
import com.murk.dk021.core.utils.reader.PropertiesReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static mocks.MockRequest.CODE_NOT_VALID;
import static mocks.model.MockModels.ALL_CLASSIFICATORS_MODELS;
import static mocks.to.MockClassificatorTO.ALL_CLASSIFICATORS_TO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class UpdateClassificatorProcessTest {

    @Mock
    private ClassificatorReader reader;

    @Mock
    private ClassificatorDao dao;

    @Mock
    private ClassificatorConverter converter;

    private UpdateClassificatorProcess process;

    private static String path;

    @BeforeClass
    public static void init()
    {
        URL pathToProperties = UpdateClassificatorProcessTest .class.getClassLoader().getResource("classificator.properties");
        path =  PropertiesReader.readProperty(pathToProperties.getPath(),"path");
    }

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        process = new UpdateClassificatorProcess(reader,dao,converter);
    }


    @Test
    public void runSuccess()
    {

        when(reader.read(path)).thenReturn(ALL_CLASSIFICATORS_TO);
        when(converter.convert(ALL_CLASSIFICATORS_TO)).thenReturn(ALL_CLASSIFICATORS_MODELS);

        process.run();

        assertTrue(process.isFinish());
        Mockito.verify(reader, times(1)).read(path);
        Mockito.verify(converter, times(1)).convert(ALL_CLASSIFICATORS_TO);
        Mockito.verify(dao, times(1)).update(ALL_CLASSIFICATORS_MODELS);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(reader);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void runNotValid()
    {
        Map<String,String> notValidClassificators = new HashMap<>();
        notValidClassificators.put(CODE_NOT_VALID,"not valid");

        when(reader.read(path)).thenReturn(notValidClassificators);
        process.run();

        assertTrue(process.isFinish());

        Mockito.verify(reader, times(1)).read(path);
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(reader);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void noClassificatorsForPath()
    {


        when(reader.read(path)).thenReturn(null);
        process.run();

        assertTrue(process.isFinish());

        Mockito.verify(reader, times(1)).read(path);
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(reader);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void emptyClassificatorsForPath()
    {

        when(reader.read(path)).thenReturn(new HashMap<>());
        process.run();

        assertTrue(process.isFinish());

        Mockito.verify(reader, times(1)).read(path);
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(reader);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void notConvertableClassificators()
    {

        when(reader.read(path)).thenReturn(ALL_CLASSIFICATORS_TO);
        when(converter.convert(ALL_CLASSIFICATORS_TO)).thenReturn(null);

        process.run();

        assertTrue(process.isFinish());

        Mockito.verify(reader, times(1)).read(path);
        Mockito.verify(converter, times(1)).convert(ALL_CLASSIFICATORS_TO);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(reader);
        verifyNoMoreInteractions(converter);
    }
}