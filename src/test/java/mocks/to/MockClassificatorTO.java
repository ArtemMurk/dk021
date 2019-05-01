package mocks.to;

import com.murk.dk021.core.to.ClassificatorTO;

import java.util.*;

import static mocks.MockRequest.CODE_SUCCESS_1;
import static mocks.MockRequest.CODE_SUCCESS_2;
import static mocks.MockRequest.CODE_SUCCESS_3;

public class MockClassificatorTO {
    public static final ClassificatorTO CLASSIFICATOR_1 = new ClassificatorTO(CODE_SUCCESS_1,"classificator 1");
    public static final ClassificatorTO CLASSIFICATOR_2 = new ClassificatorTO(CODE_SUCCESS_2,"classificator 2");
    public static final ClassificatorTO CLASSIFICATOR_3 = new ClassificatorTO(CODE_SUCCESS_3,"classificator 3");

    public static final Set<ClassificatorTO> NODES_FOR_CLASSIFICATOR_1 = new LinkedHashSet<>(Arrays.asList(CLASSIFICATOR_2,CLASSIFICATOR_3));

}
