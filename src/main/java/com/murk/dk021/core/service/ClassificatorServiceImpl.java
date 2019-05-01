package com.murk.dk021.core.service;

import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.STATUS;
import com.murk.dk021.core.to.UpdateInfoTO;
import com.murk.dk021.core.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ClassificatorServiceImpl implements ClassificatorService {

    @Override
    public ClassificatorTO get(String code) {
        ValidationUtil.validateCode(code);
        log.info("Get classificator, code = {}",code);
        return new ClassificatorTO("code1","name1");
    }

    @Override
    public Set<ClassificatorTO> getNodes(String code) {
        ValidationUtil.validateCode(code);

        log.info("Get classificator nodes, code = {}",code);

        Set<ClassificatorTO> classificators = new HashSet<>();
        ClassificatorTO classificator1 = new ClassificatorTO("code1","name1");
        ClassificatorTO classificator2 = new ClassificatorTO("code2","name2");
        classificators.add(classificator1);
        classificators.add(classificator2);
        return classificators;
    }

    public UpdateInfoTO update()
    {
        log.warn("Start update classificators");

        return new UpdateInfoTO(STATUS.SUCCESS,"success update");
    }
}
