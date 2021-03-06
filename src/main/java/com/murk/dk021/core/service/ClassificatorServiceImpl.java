package com.murk.dk021.core.service;

import com.murk.dk021.core.dao.ClassificatorDao;
import com.murk.dk021.core.exception.NotFoundClassificatorException;
import com.murk.dk021.core.model.Classificator;
import com.murk.dk021.core.process.UpdateClassificatorProcess;
import com.murk.dk021.core.utils.UpdateManager;
import com.murk.dk021.core.utils.reader.ClassificatorReader;
import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.STATUS;
import com.murk.dk021.core.to.UpdateInfoTO;
import com.murk.dk021.core.utils.converter.ClassificatorConverter;
import com.murk.dk021.core.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PreDestroy;
import java.util.Set;
import java.util.stream.Collectors;

import static com.murk.dk021.core.exception.ExceptionHelper.NOT_FOUND_CLASSIFICATOR_MESSAGE;
import static com.murk.dk021.core.service.UpdateMessageHelper.FAIL_UPDATE_ANOTHER_PROCESS_MESSAGE;
import static com.murk.dk021.core.service.UpdateMessageHelper.START_UPDATE_MESSAGE;

@Service
@Slf4j
public class ClassificatorServiceImpl implements ClassificatorService {

    private ClassificatorDao dao;
    private ClassificatorReader classificatorReader;
    private UpdateManager updatePool;
    private ClassificatorConverter converter;

    @Autowired
    public ClassificatorServiceImpl(ClassificatorDao dao, ClassificatorReader classificatorReader, UpdateManager updatePool, ClassificatorConverter converter) {
        this.dao = dao;
        this.classificatorReader = classificatorReader;
        this.updatePool = updatePool;
        this.converter = converter;
    }

    @Override
    public ClassificatorTO get(String code) {
        ClassificatorTO classificatorTO;
        ValidationUtil.validateCode(code);
        log.info("Get classificator, code = {}",code);

        int id = getIdFromCode(code);
        short num = getNumFromCode(code);

        Classificator classificator = dao.get(id,num);
        if (classificator != null)
        {
            classificatorTO = converter.convert(classificator);
        } else
            {
                throw new NotFoundClassificatorException(String.format(NOT_FOUND_CLASSIFICATOR_MESSAGE,code));

            }

        return classificatorTO;
    }



    @Override
    public Set<ClassificatorTO> getNodes(String code) {
        Set<ClassificatorTO> classificatorsTo;

        ValidationUtil.validateCode(code);
        int id = getIdFromCode(code);
        short num = getNumFromCode(code);

        log.info("Get classificator nodes, code = {}",code);

        Set<Classificator> classificators= dao.getNodes(id,num);
        if (classificators != null && classificators.size()>0)
        {
            classificatorsTo = classificators
                    .stream()
                    .map(converter::convert)
                    .collect(Collectors.toSet());
        } else
        {
            throw new NotFoundClassificatorException(String.format(NOT_FOUND_CLASSIFICATOR_MESSAGE,code));
        }

        return classificatorsTo;
    }

    public Set<ClassificatorTO> getRootNodes() {
        Set<ClassificatorTO> classificatorsTo;

        Set<Classificator> classificators= dao.getRootNodes();
        if (classificators != null && classificators.size()>0)
        {
            classificatorsTo = classificators
                    .stream()
                    .map(converter::convert)
                    .collect(Collectors.toSet());
        } else
        {
            throw new NotFoundClassificatorException(String.format(NOT_FOUND_CLASSIFICATOR_MESSAGE,"for root nodes"));
        }

        return classificatorsTo;
    }

    private short getNumFromCode(String code) {
        return Short.parseShort(code.split("-")[1]);
    }

    private int getIdFromCode(String code) {
        return Integer.parseInt(code.split("-")[0]);
    }

    public synchronized UpdateInfoTO update()
    {

        UpdateInfoTO responseUpdate;
        UpdateClassificatorProcess updateThread = new UpdateClassificatorProcess(classificatorReader,dao,converter);
        boolean success = updatePool.executeIfFree(updateThread);
        if (success)
        {
            log.warn(START_UPDATE_MESSAGE);
            responseUpdate = new UpdateInfoTO(STATUS.SUCCESS,START_UPDATE_MESSAGE);

        }
        else
        {
            log.warn(FAIL_UPDATE_ANOTHER_PROCESS_MESSAGE);
            responseUpdate =  new UpdateInfoTO(STATUS.FAIL,FAIL_UPDATE_ANOTHER_PROCESS_MESSAGE);
        }
            return responseUpdate;
    }

    @PreDestroy
    private void closeUpdatePool()
    {
            updatePool.shutdown();
    }


}
