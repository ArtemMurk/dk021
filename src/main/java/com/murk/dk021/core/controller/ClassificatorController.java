package com.murk.dk021.core.controller;

import com.murk.dk021.core.exception.NotFoundClassificatorException;
import com.murk.dk021.core.exception.NotValidCodeException;
import com.murk.dk021.core.service.ClassificatorServiceImpl;
import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.ExceptionTO;
import com.murk.dk021.core.to.UpdateInfoTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/dk021")
public class ClassificatorController {

    private ClassificatorServiceImpl service;

    @Autowired
    public ClassificatorController(ClassificatorServiceImpl service)
    {
        this.service = service;
    }

    public ClassificatorController() {
    }

    @RequestMapping(value = "/classificator/{code}", method = RequestMethod.GET)
    ResponseEntity<ClassificatorTO> get(@PathVariable String code)
    {
        ClassificatorTO classificator = service.get(code);
        return new ResponseEntity<>(classificator, HttpStatus.OK);
    }

    @RequestMapping(value = "/classificator/nodes/{code}", method = RequestMethod.GET)
    ResponseEntity<Set<ClassificatorTO>> getNodes(@PathVariable String code)
    {
        Set<ClassificatorTO> nodes = service.getNodes(code);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    ResponseEntity<UpdateInfoTO> update()
    {
        UpdateInfoTO info = service.update();

        return new ResponseEntity<>(info, HttpStatus.OK);
    }


    @ExceptionHandler(value= { NotValidCodeException.class})
    public ResponseEntity<ExceptionTO> notValidCode(RuntimeException ex, WebRequest request)
    {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ExceptionTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= { NotFoundClassificatorException.class})
    public ResponseEntity<ExceptionTO> notFoundClassificator(RuntimeException ex, WebRequest request)
    {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ExceptionTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}

