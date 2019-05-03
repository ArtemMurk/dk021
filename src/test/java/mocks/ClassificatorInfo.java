package mocks;

import static mocks.MockRequest.*;

public class ClassificatorInfo {
    public static final String CLASSIFICATOR_1_NAME = "classificator 1";
    public static final int CLASSIFICATOR_1_ID = Integer.parseInt(CODE_SUCCESS_1.split("-")[0]);
    public static final short CLASSIFICATOR_1_NUM = Short.parseShort(CODE_SUCCESS_1.split("-")[1]);
    public static final Integer CLASSIFICATOR_1_PARENTID = null;


    public static final String CLASSIFICATOR_2_NAME = "classificator 2";
    public static final int CLASSIFICATOR_2_ID = Integer.parseInt(CODE_SUCCESS_2.split("-")[0]);
    public static final short CLASSIFICATOR_2_NUM = Short.parseShort(CODE_SUCCESS_2.split("-")[1]);
    public static final Integer CLASSIFICATOR_2_PARENTID = CLASSIFICATOR_1_ID;

    public static final String CLASSIFICATOR_3_NAME = "classificator 3";
    public static final int CLASSIFICATOR_3_ID = Integer.parseInt(CODE_SUCCESS_3.split("-")[0]);
    public static final short CLASSIFICATOR_3_NUM = Short.parseShort(CODE_SUCCESS_3.split("-")[1]);
    public static final Integer CLASSIFICATOR_3_PARENTID = CLASSIFICATOR_1_ID;

    public static final int CLASSIFICATOR_NOT_FOUND_ID = Integer.parseInt(CODE_NOT_FOUND.split("-")[0]);

}
