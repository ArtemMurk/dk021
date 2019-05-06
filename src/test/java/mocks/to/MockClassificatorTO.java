package mocks.to;

import com.murk.dk021.core.to.ClassificatorTO;

import java.util.*;

import static mocks.ClassificatorInfo.*;
import static mocks.MockRequest.*;

public class MockClassificatorTO {

    public static final ClassificatorTO CLASSIFICATOR_TO_1 = new ClassificatorTO(CODE_SUCCESS_1,CLASSIFICATOR_1_NAME);
    public static final ClassificatorTO CLASSIFICATOR_TO_2 = new ClassificatorTO(CODE_SUCCESS_2,CLASSIFICATOR_2_NAME);
    public static final ClassificatorTO CLASSIFICATOR_TO_3 = new ClassificatorTO(CODE_SUCCESS_3,CLASSIFICATOR_3_NAME);

    public static final Set<ClassificatorTO> NODES_FOR_CLASSIFICATOR_TO_1 = new LinkedHashSet<>(Arrays.asList(CLASSIFICATOR_TO_2, CLASSIFICATOR_TO_3));

    public static final Set<ClassificatorTO> NODES_FOR_ROOT = new LinkedHashSet<>(Collections.singletonList(CLASSIFICATOR_TO_1));

    public static final Map<String,String> ALL_CLASSIFICATORS_TO = new HashMap<>();

    static {
        ALL_CLASSIFICATORS_TO.put(CLASSIFICATOR_TO_1.getCode(),CLASSIFICATOR_TO_1.getName());
        ALL_CLASSIFICATORS_TO.put(CLASSIFICATOR_TO_2.getCode(),CLASSIFICATOR_TO_2.getName());
        ALL_CLASSIFICATORS_TO.put(CLASSIFICATOR_TO_3.getCode(),CLASSIFICATOR_TO_3.getName());
    }


}
