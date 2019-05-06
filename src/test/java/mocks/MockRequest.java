package mocks;

public class MockRequest {
    public static final String CODE_SUCCESS_1 = "35000000-4";
    public static final String CODE_SUCCESS_2 = "35100000-5";
    public static final String CODE_SUCCESS_3 = "35200000-6";

    public static final String CODE_NOT_VALID = "12-2";
    public static final String CODE_NOT_FOUND = "11111111-2";

    public static final String GET_CLASSIFICATOR_SUCCESS_URI_1 = "/dk021/classificator/"+CODE_SUCCESS_1;
    public static final String GET_CLASSIFICATOR_FAIL_URI = "/dk021/classificator/"+ CODE_NOT_VALID;
    public static final String GET_CLASSIFICATOR_NOT_FOUND_URI = "/dk021/classificator/"+ CODE_NOT_FOUND;
    public static final String GET_CLASSIFICATOR_NODES_SUCCESS_URI_1 = "/dk021/classificator/nodes/"+CODE_SUCCESS_1;
    public static final String GET_CLASSIFICATOR_NODES_ROOT_SUCCESS_URI = "/dk021/classificator/nodes/";
    public static final String GET_CLASSIFICATOR_NODES_FAIL_URI = "/dk021/classificator/nodes/"+ CODE_NOT_VALID;
    public static final String GET_CLASSIFICATOR_NODES_NOT_FOUND_URI = "/dk021/classificator/nodes/"+ CODE_NOT_FOUND;
    public static final String UPDATE_INFO_URI = "/dk021/update";

}
