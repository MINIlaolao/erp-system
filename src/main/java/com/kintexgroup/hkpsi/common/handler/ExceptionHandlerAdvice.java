package com.kintexgroup.hkpsi.common.handler;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.dto.ResponseDTO;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 异常处理器
 *
 * @author gradylo
 * @author lmao
 * @since 2020/8/23 22:18
 */
@ControllerAdvice(annotations = ResponseWrapper.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseDTO handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage(), null);
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage(), null);
    }

    /**
     * 处理数据库异常BaseException
     *
     * @param e 数据库异常
     * @return 统一响应体
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public ResponseDTO handleMyBatisSystemException(MyBatisSystemException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.DB_SYSTEM_ERROR.getCode(),
            ResponseCode.DB_SYSTEM_ERROR.getMessage(), null);
    }

    /**
     * 处理数据库异常BaseException
     *
     * @param e 数据库异常
     * @return 统一响应体
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseDTO handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.SYSTEM_NULL_POINTER.getCode(),
            ResponseCode.SYSTEM_NULL_POINTER.getMessage(), e.getMessage());
    }

    /**
     * NoSuchElementException
     *
     * @param e NoSuchElementException
     * @return 统一响应体
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseDTO handleMyBatisSystemException(NoSuchElementException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.DB_SELECT_NULL_ERROR.getCode(),
            ResponseCode.DB_SELECT_NULL_ERROR.getMessage(), null);
    }

    /**
     * 未处理的数据重复异常DuplicateKeyException
     *
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseDTO handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.DB_DUPLICATE_ERROR.getCode(),
            ResponseCode.DB_DUPLICATE_ERROR.getMessage(), null);
    }

    /**
     * 处理不能读取HttpMessage
     *
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        return new ResponseDTO(ResponseCode.REQUEST_PARAM_INVALID.getCode(),
            ResponseCode.REQUEST_PARAM_INVALID.getMessage(), null);
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseDTO handleBaseException(BusinessException e) {
        log.error(e.getMessage(), e);
        ResponseCode code = e.getCode();
        return new ResponseDTO(code.getCode(), code.getMessage(), null);
    }


    /**
     * 统一处理参数校验异常
     * <pre>
     * 对象参数接收请求体校验不通过会抛出 MethodArgumentNotValidException
     * 普通参数校验校验不通过会抛出 ConstraintViolationException
     * 必填参数没传校验不通过会抛出 ServletRequestBindingException
     * 请求参数绑定到对象上校验不通过会抛出 BindException
     * </pre>
     *
     * @param e 异常父类
     * @return 统一响应体
     */
    @ExceptionHandler({ConstraintViolationException.class,
        MethodArgumentNotValidException.class,
        ServletRequestBindingException.class,
        BindException.class})
    public ResponseDTO handleValidationException(Exception e) {
        String logMsg = e.getMessage();
        String msg;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else {
            // 其他类型的错误当成未知异常处理
            return handleException(e);
        }
        log.error("参数校验不通过, {}, msg: {}", logMsg, msg);
        ResponseCode code = ResponseCode.INSERT_PARAM_INVALID;
        return new ResponseDTO(code.getCode(), msg, null);
    }


    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(
                Collectors.joining());
    }

}
