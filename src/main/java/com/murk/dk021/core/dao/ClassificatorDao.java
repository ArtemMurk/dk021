package com.murk.dk021.core.dao;

import com.murk.dk021.core.model.Classificator;

import java.util.Map;
import java.util.Set;

public interface ClassificatorDao {
    Classificator get(int id, short num);
    Set<Classificator> getNodes(int id, short num);
    Set<Classificator> getRootNodes();

    void update(Map<Integer, Classificator> classificators);


}
