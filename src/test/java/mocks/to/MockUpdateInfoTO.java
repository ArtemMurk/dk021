package mocks.to;

import com.murk.dk021.core.to.STATUS;
import com.murk.dk021.core.to.UpdateInfoTO;

public class MockUpdateInfoTO {
    public static final UpdateInfoTO UPDATE_INFO_SUCCESS = new UpdateInfoTO(STATUS.SUCCESS,"Start synchronization classificators");

    public static final UpdateInfoTO UPDATE_INFO_FAIL = new UpdateInfoTO(STATUS.FAIL,"Another instance of synchronization is running");
}
