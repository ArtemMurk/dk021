package mocks.model;

import com.murk.dk021.core.model.Classificator;

import java.util.*;
import java.util.stream.Collectors;

import static mocks.ClassificatorInfo.*;

public class MockModels {

    public static final Classificator CLASSIFICATOR_MODEL_1 = new Classificator(CLASSIFICATOR_1_ID, CLASSIFICATOR_1_NUM,CLASSIFICATOR_1_PARENTID,CLASSIFICATOR_1_NAME);

    public static final Classificator CLASSIFICATOR_MODEL_2 = new Classificator(CLASSIFICATOR_2_ID, CLASSIFICATOR_2_NUM,CLASSIFICATOR_2_PARENTID,CLASSIFICATOR_2_NAME);

    public static final Classificator CLASSIFICATOR_MODEL_3 = new Classificator(CLASSIFICATOR_3_ID, CLASSIFICATOR_3_NUM,CLASSIFICATOR_3_PARENTID,CLASSIFICATOR_3_NAME);

    public static final Set<Classificator> NODES_FOR_CLASSIFICATOR_MODEL_1 = new LinkedHashSet<>(Arrays.asList(CLASSIFICATOR_MODEL_2, CLASSIFICATOR_MODEL_3));

    public static final Map<Integer,Classificator> ALL_CLASSIFICATORS_MODELS = new HashMap<>();

    static {
        ALL_CLASSIFICATORS_MODELS.putAll(NODES_FOR_CLASSIFICATOR_MODEL_1.stream().collect(Collectors.toMap(Classificator::getId, e -> e)));
        ALL_CLASSIFICATORS_MODELS.put(CLASSIFICATOR_MODEL_1.getId(),CLASSIFICATOR_MODEL_1);

    }


}
