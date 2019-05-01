package com.murk.dk021.core.utils;


import com.murk.dk021.core.exception.ExceptionHelper;
import com.murk.dk021.core.exception.NotValidCodeException;

public class ValidationUtil {
    private static final String CODE_REGEXP = "^([0-9]){8}-[0-9]$";

    public static void validateCode(String code)
    {
        if (!code.matches(CODE_REGEXP))
        {
            throw new NotValidCodeException(String.format(ExceptionHelper.NOT_VALID_CODE_MESSAGE,code));
        }
    }

}
