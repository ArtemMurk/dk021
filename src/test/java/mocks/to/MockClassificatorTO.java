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


}
