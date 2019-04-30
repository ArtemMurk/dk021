package com.murk.dk021.core.controller;

import com.murk.dk021.core.service.ClassificatorService;
import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.UpdateInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dk021")
public class ClassificatorController {

    private ClassificatorService service;

    @Autowired
    public ClassificatorController(ClassificatorService service)
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    ResponseEntity<UpdateInfoTO> update(@RequestBody String path)
    {
        UpdateInfoTO info = service.update(path);

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

}

