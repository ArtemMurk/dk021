package com.murk.dk021.core.dao;

import com.murk.dk021.core.model.Classificator;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
public class ClassificatorDaoImpl implements ClassificatorDao {

    @Override
    public Classificator get(int id) {
        // TODO: 03.05.2019
        return null;
    }

    @Override
    public Set<Classificator> getNodes(int id) {
        // TODO: 03.05.2019
        return null;
    }

    @Override
    public void update(Map<Integer, Classificator> classificators) {
        // TODO: 03.05.2019
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
