package com.zerobase.user.common.response;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.BasicErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    /**
     * http status : 500
     * result : fail
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public CommonResponse onException(final Exception e) {

        log.error(e.getMessage(), e);

        return CommonResponse.fail(BasicErrorCode.COMMON_SYSTEM_ERROR);
    }

    /**
     * http status : 403
     * result : fail
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public CommonResponse onAuthorizationDeniedException(final AuthorizationDeniedException e) {
        return CommonResponse.fail(BasicErrorCode.FORBIDDEN);
    }

    /**
     * http status : 200
     * result : fail
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BaseException.class)
    public CommonResponse onBaseException(final BaseException e) {

        log.error(e.getMessage(), e);

        return CommonResponse.fail(e.getErrorCode());
    }
}
