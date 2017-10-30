package com.lan.common.exception;

import com.lan.common.util.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * package com.lan.common.exception
 * 统一处理Controller中出现的Exception
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
@RestControllerAdvice
public class IChatExceptionHandler {

    @ExceptionHandler(IChatException.class)
    public BaseResult handleIChatException(IChatException e) {
        return new BaseResult(e.getCode(), e.getMsg());
    }
}
