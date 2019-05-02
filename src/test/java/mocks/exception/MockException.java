package mocks.exception;

import com.murk.dk021.core.exception.ExceptionHelper;
import com.murk.dk021.core.exception.NotFoundClassificatorException;
import com.murk.dk021.core.exception.NotValidCodeException;

import static mocks.MockRequest.*;

public class MockException {

    public static final NotValidCodeException NOT_VALID_CODE_EXCEPTION = new NotValidCodeException(String.format(ExceptionHelper.NOT_VALID_CODE_MESSAGE, CODE_NOT_VALID));

    public static final NotFoundClassificatorException NOT_FOUND_CLASSIFICATOR_EXCEPTION = new NotFoundClassificatorException(String.format(ExceptionHelper.NOT_FOUND_CLASSIFICATOR_MESSAGE,CODE_NOT_FOUND));
}
