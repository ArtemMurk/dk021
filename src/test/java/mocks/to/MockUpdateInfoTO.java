package mocks.to;

import com.murk.dk021.core.to.STATUS;
import com.murk.dk021.core.to.UpdateInfoTO;

import static com.murk.dk021.core.service.UpdateMessageHelper.*;

public class MockUpdateInfoTO {
    public static final UpdateInfoTO UPDATE_INFO_SUCCESS = new UpdateInfoTO(STATUS.SUCCESS,START_UPDATE_MESSAGE);

    public static final UpdateInfoTO UPDATE_INFO_FAIL = new UpdateInfoTO(STATUS.FAIL,FAIL_UPDATE_ANOTHER_PROCESS_MESSAGE);
}
