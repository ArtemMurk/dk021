package com.murk.dk021.core.controller;


import com.murk.dk021.core.exception.ExceptionHelper;
import com.murk.dk021.core.service.ClassificatorServiceImpl;
import com.murk.dk021.core.to.STATUS;
import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static mocks.MockRequest.*;
import static mocks.exception.MockException.NOT_FOUND_CLASSIFICATOR_EXCEPTION;
import static mocks.exception.MockException.NOT_VALID_CODE_EXCEPTION;
import static mocks.to.MockClassificatorTO.*;
import static mocks.to.MockUpdateInfoTO.UPDATE_INFO_FAIL;
import static mocks.to.MockUpdateInfoTO.UPDATE_INFO_SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClassificatorControllerTest {

    private MockMvc mockMvc;

    private ClassificatorServiceImpl service;
    private ClassificatorController controller;

    @Before
    public void setUp(){
        service = mock(ClassificatorServiceImpl.class);
        controller = new ClassificatorController (service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void contextLoad(){
        assertThat(service).isNotNull();
        assertThat(mockMvc).isNotNull();
        assertThat(controller).isNotNull();
    }

    @Test
    public void getClassificatorSuccess() throws Exception {

        when(service.get(CODE_SUCCESS_1)).thenReturn(CLASSIFICATOR_1);

        mockMvc.perform(get(GET_CLASSIFICATOR_SUCCESS_URI_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", is(STATUS.SUCCESS.name())))
                .andExpect(jsonPath("code", is(CLASSIFICATOR_1.getCode())))
                .andExpect(jsonPath("name", is(CLASSIFICATOR_1.getName())));

        verify(service, times(1)).get(CODE_SUCCESS_1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getClassificatorNodesSuccess() throws Exception {
        when(service.getNodes(CODE_SUCCESS_1)).thenReturn(NODES_FOR_CLASSIFICATOR_1);

        mockMvc.perform(get(GET_CLASSIFICATOR_NODES_SUCCESS_URI_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is(STATUS.SUCCESS.name())))
                .andExpect(jsonPath("$[0].code", is(CLASSIFICATOR_2.getCode())))
                .andExpect(jsonPath("$[0].name", is(CLASSIFICATOR_2.getName())))
                .andExpect(jsonPath("$[1].status", is(STATUS.SUCCESS.name())))
                .andExpect(jsonPath("$[1].code", is(CLASSIFICATOR_3.getCode())))
                .andExpect(jsonPath("$[1].name", is(CLASSIFICATOR_3.getName())));

        verify(service, times(1)).getNodes(CODE_SUCCESS_1);
        verifyNoMoreInteractions(service);

    }

    @Test
    public void notValidClassificatorCode() throws Exception {
        when(service.get(CODE_NOT_VALID)).thenThrow(NOT_VALID_CODE_EXCEPTION);

        mockMvc.perform(get(GET_CLASSIFICATOR_FAIL_URI))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status", is(STATUS.FAIL.name())))
                .andExpect(jsonPath("cause", is(String.format(ExceptionHelper.NOT_VALID_CODE_MESSAGE,CODE_NOT_VALID))));

        verify(service, times(1)).get(CODE_NOT_VALID);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void notValidClassificatorCodeNodes() throws Exception {

        when(service.getNodes(CODE_NOT_VALID)).thenThrow(NOT_VALID_CODE_EXCEPTION);

        mockMvc.perform(get(GET_CLASSIFICATOR_NODES_FAIL_URI))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status", is(STATUS.FAIL.name())))
                .andExpect(jsonPath("cause", is(String.format(ExceptionHelper.NOT_VALID_CODE_MESSAGE,CODE_NOT_VALID))));

        verify(service, times(1)).getNodes(CODE_NOT_VALID);
        verifyNoMoreInteractions(service);
    }


    @Test
    public void notFoundClassificator() throws Exception {
        when(service.get(CODE_NOT_FOUND)).thenThrow(NOT_FOUND_CLASSIFICATOR_EXCEPTION);

        mockMvc.perform(get(GET_CLASSIFICATOR_NOT_FOUND_URI))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status", is(STATUS.FAIL.name())))
                .andExpect(jsonPath("cause", is(String.format(ExceptionHelper.NOT_FOUND_CLASSIFICATOR,CODE_NOT_FOUND))));

        verify(service, times(1)).get(CODE_NOT_FOUND);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void notFoundClassificatorNodes() throws Exception {

        when(service.getNodes(CODE_NOT_FOUND)).thenThrow(NOT_FOUND_CLASSIFICATOR_EXCEPTION);

        mockMvc.perform(get(GET_CLASSIFICATOR_NODES_NOT_FOUND_URI))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status", is(STATUS.FAIL.name())))
                .andExpect(jsonPath("cause", is(String.format(ExceptionHelper.NOT_FOUND_CLASSIFICATOR,CODE_NOT_FOUND))));

        verify(service, times(1)).getNodes(CODE_NOT_FOUND);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void updateSuccess() throws Exception {
        when(service.update()).thenReturn(UPDATE_INFO_SUCCESS);

        mockMvc.perform(post(UPDATE_INFO_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", is(UPDATE_INFO_SUCCESS.getStatus().name())))
                .andExpect(jsonPath("message", is(UPDATE_INFO_SUCCESS.getMessage())));

        verify(service, times(1)).update();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void updateFail() throws Exception {
        when(service.update()).thenReturn(UPDATE_INFO_FAIL);

        mockMvc.perform(post(UPDATE_INFO_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", is(UPDATE_INFO_FAIL.getStatus().name())))
                .andExpect(jsonPath("message", is(UPDATE_INFO_FAIL.getMessage())));

        verify(service, times(1)).update();
        verifyNoMoreInteractions(service);
    }
}