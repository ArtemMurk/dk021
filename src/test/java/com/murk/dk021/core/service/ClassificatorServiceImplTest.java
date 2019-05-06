package com.murk.dk021.core.service;

import com.murk.dk021.core.dao.ClassificatorDao;
import com.murk.dk021.core.exception.NotFoundClassificatorException;
import com.murk.dk021.core.exception.NotValidCodeException;
import com.murk.dk021.core.process.UpdateClassificatorProcess;
import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.UpdateInfoTO;
import com.murk.dk021.core.utils.UpdateManager;
import com.murk.dk021.core.utils.converter.ClassificatorConverter;
import com.murk.dk021.core.utils.reader.ClassificatorReader;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static mocks.ClassificatorInfo.*;
import static mocks.MockRequest.*;
import static mocks.model.MockModels.*;
import static mocks.to.MockClassificatorTO.*;
import static mocks.to.MockUpdateInfoTO.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class ClassificatorServiceImplTest {

    @Spy
    private ClassificatorDao dao;

    @Spy
    private ClassificatorReader reader;

    @Mock
    private UpdateManager updatePool;

    @Spy
    private ClassificatorConverter converter;

    @InjectMocks
    @Autowired
    private ClassificatorService service;


    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void contextLoad(){
        assertThat(service).isNotNull();
        assertThat(dao).isNotNull();
        assertThat(reader).isNotNull();
        assertThat(updatePool).isNotNull();
        assertThat(converter).isNotNull();
    }

    @Test
    public void getSuccess()
    {

        when(dao.get(CLASSIFICATOR_1_ID,CLASSIFICATOR_1_NUM)).thenReturn(CLASSIFICATOR_MODEL_1);
        when(converter.convert(CLASSIFICATOR_MODEL_1)).thenReturn(CLASSIFICATOR_TO_1);

        ClassificatorTO classificatorTOActual = service.get(CODE_SUCCESS_1);

        assertThat(CLASSIFICATOR_TO_1).isEqualToComparingFieldByField(classificatorTOActual);

        verify(dao, times(1)).get(CLASSIFICATOR_1_ID,CLASSIFICATOR_1_NUM);
        verify(converter, times(1)).convert(CLASSIFICATOR_MODEL_1);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void getNodesSuccess()
    {

        when(dao.getNodes(CLASSIFICATOR_1_ID,CLASSIFICATOR_1_NUM)).thenReturn(NODES_FOR_CLASSIFICATOR_MODEL_1);
        when(converter.convert(CLASSIFICATOR_MODEL_2)).thenReturn(CLASSIFICATOR_TO_2);
        when(converter.convert(CLASSIFICATOR_MODEL_3)).thenReturn(CLASSIFICATOR_TO_3);

        Set<ClassificatorTO> classificatorsTOActual = service.getNodes(CODE_SUCCESS_1);

        assertThat(NODES_FOR_CLASSIFICATOR_TO_1).isEqualTo(classificatorsTOActual);

        verify(dao, times(1)).getNodes(CLASSIFICATOR_1_ID,CLASSIFICATOR_1_NUM);
        verify(converter, times(1)).convert(CLASSIFICATOR_MODEL_2);
        verify(converter, times(1)).convert(CLASSIFICATOR_MODEL_3);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }


    @Test
    public void getRootNodesSuccess()
    {

        when(dao.getRootNodes()).thenReturn(ROOT_NODES);
        when(converter.convert(CLASSIFICATOR_MODEL_1)).thenReturn(CLASSIFICATOR_TO_1);


        Set<ClassificatorTO> classificatorsTOActual = service.getRootNodes();

        assertThat(NODES_FOR_ROOT).isEqualTo(classificatorsTOActual);

        verify(dao, times(1)).getRootNodes();
        verify(converter, times(1)).convert(CLASSIFICATOR_MODEL_1);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }

    @Test(expected = NotValidCodeException.class)
    public void getNotValid()
    {

        service.get(CODE_NOT_VALID);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }

    @Test(expected = NotValidCodeException.class)
    public void getNodesNotValid()
    {
        service.getNodes(CODE_NOT_VALID);

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }


    @Test(expected = NotFoundClassificatorException.class)
    public void getNotFoundClassificator()
    {
        when(dao.get(CLASSIFICATOR_NOT_FOUND_ID,CLASSIFICATOR_NOT_FOUND_NUM )).thenReturn(null);

        service.get(CODE_NOT_FOUND);


        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }

    @Test(expected = NotFoundClassificatorException.class)
    public void getNotFoundNodesIsEmpty()
    {
        when(dao.getNodes(CLASSIFICATOR_NOT_FOUND_ID,CLASSIFICATOR_NOT_FOUND_NUM )).thenReturn(new HashSet<>());

        service.getNodes(CODE_NOT_FOUND);


        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }


    @Test(expected = NotFoundClassificatorException.class)
    public void getNotFoundNodes()
    {
        when(dao.getNodes(CLASSIFICATOR_NOT_FOUND_ID,CLASSIFICATOR_NOT_FOUND_NUM )).thenReturn(null);

        service.getNodes(CODE_NOT_FOUND);


        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void updateSuccess()
    {
        when(updatePool.executeIfFree(Matchers.<UpdateClassificatorProcess>any())).thenReturn(true);
       UpdateInfoTO updateSuccess=  service.update();
       assertThat(UPDATE_INFO_SUCCESS).isEqualToComparingFieldByField(updateSuccess);


    }

    @Test
    public void updateFailAnotherInstanceUpdateIsRunning()
    {
        when(updatePool.executeIfFree(Matchers.<UpdateClassificatorProcess>any())).thenReturn(false);

        UpdateInfoTO updateFail=  service.update();

        assertThat(UPDATE_INFO_FAIL).isEqualToComparingFieldByField(updateFail);

    }
}