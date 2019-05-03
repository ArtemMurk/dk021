package com.murk.dk021.core.process;

import com.murk.dk021.core.dao.ClassificatorDao;
import com.murk.dk021.core.model.Classificator;
import com.murk.dk021.core.utils.converter.ClassificatorConverter;
import com.murk.dk021.core.utils.reader.ClassificatorReader;
import com.murk.dk021.core.utils.reader.PropertiesReader;
import com.murk.dk021.core.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Map;


@Slf4j
public class UpdateClassificatorProcess implements Runnable{

    private ClassificatorReader classificatorReader;
    private ClassificatorDao dao;
    private ClassificatorConverter converter;

    private volatile boolean finish;

    public UpdateClassificatorProcess(ClassificatorReader classificatorReader, ClassificatorDao dao,ClassificatorConverter converter) {
        this.classificatorReader = classificatorReader;
        this.dao =dao;
        this.converter = converter;
    }

    @Override
    public void run() {
        try {
            URL classificatorPropertiesRes = getClass().getClassLoader().getResource("classificator.properties");

            String classificatorURIPath = PropertiesReader.readProperty(classificatorPropertiesRes.getPath(),"path");

            Map<String,String> classificatorsTo = classificatorReader.read(classificatorURIPath);

            if (classificatorsTo == null || classificatorsTo.size()>0)
            {
                ValidationUtil.validateCode(classificatorsTo.keySet());

                Map<Integer, Classificator> classificators = converter.convert(classificatorsTo);

                dao.update(classificators);
            }
            else
            {
                log.error("Zero classificators from path = ",classificatorURIPath);
            }
        } catch (IllegalArgumentException ex)
        {
            log.error(ex.getMessage());
        } finally {
            finish();
        }
    }


    public synchronized boolean isFinish() {
        return finish;
    }

    private void finish()
    {
        finish = true;
    }

}
