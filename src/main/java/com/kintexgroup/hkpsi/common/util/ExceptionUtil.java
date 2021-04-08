package com.kintexgroup.hkpsi.common.util;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * 处理错误工具类
 *
 * @author lmao
 * @since 2020/8/28 18:38
 */
public final class ExceptionUtil {

    private ExceptionUtil() {
    }

    /**
     * 处理数据重复异常,选择异常条件抛出相应异常
     *
     * @param exception boolean = false 时报的异常
     * @return 错误码
     */
    private static ResponseCode switchIllegalStateException(IllegalStateException exception) {

        if (exception.getMessage().equals(ResponseCode.DB_INSERT_DUPLICATE_ERROR.getMessage())) {
            return ResponseCode.DB_INSERT_DUPLICATE_ERROR;
        }
        if (exception.getMessage().equals(ResponseCode.DB_UPDATE_DUPLICATE_ERROR.getMessage())) {
            return ResponseCode.DB_UPDATE_DUPLICATE_ERROR;
        }
        return ResponseCode.DB_DUPLICATE_ERROR;
    }


    /**
     * 判断插入是否成功
     *
     * @param dao 映射中返回的布尔值
     */
    public static boolean isInsertSuccess(boolean dao) {
        if (!dao) {
            throw new BusinessException(ResponseCode.DB_INSERT_ERROR);
        }
        return true;
    }

    /**
     * 判断插入是否成功
     *
     * @param dao 映射中返回的布尔值
     */
    public static boolean isInsertSuccess(int... dao) {

        if (Arrays.stream(dao).iterator().nextInt() == 0) {
            throw new BusinessException(ResponseCode.DB_INSERT_ERROR);
        }
        return true;
    }

    /**
     * 判断更新是否成功
     *
     * @param dao 映射中返回的布尔值
     */
    public static boolean isUpdateSuccess(boolean dao) {
        if (!dao) {
            throw new BusinessException(ResponseCode.DB_UPDATE_ERROR);
        }
        return true;
    }

    /**
     * 判断更新是否成功
     *
     * @param dao 映射中返回的int
     */
    public static boolean isUpdateSuccess(int dao) {
        if (dao == 0) {
            throw new BusinessException(ResponseCode.DB_UPDATE_ERROR);
        }
        return true;
    }

    /**
     * 判断删除是否成功
     *
     * @param dao 映射中返回的int
     */
    public static boolean isDeleteSuccess(int dao) {
        if (dao == 0) {
            throw new BusinessException(ResponseCode.DB_DELETE_ERROR);
        }
        return true;
    }

    /**
     * 判断删除是否成功
     *
     * @param dao 映射中返回的布尔值
     */
    public static boolean isDeleteSuccess(boolean dao) {
        if (!dao) {
            throw new BusinessException(ResponseCode.DB_DELETE_ERROR);
        }
        return true;
    }

    /**
     * 判断传入参数是否为空,选择异常条件抛出相映异常
     *
     * @param reference    T
     * @param errorMessage 错误信息
     * @param <T>          所传参数
     */
    public static <T> void checkNotNull(T reference, @Nullable String errorMessage) {
        if (reference == null) {
            if (ResponseCode.CRUD_INPUT_ID_NOT_EXIST.getMessage().equals(errorMessage)) {
                throw new BusinessException(ResponseCode.CRUD_INPUT_ID_NOT_EXIST);
            }
            if (ResponseCode.CRUD_INPUT_VALUE_NOT_EXIST.getMessage().equals(errorMessage)) {
                throw new BusinessException(ResponseCode.CRUD_INPUT_VALUE_NOT_EXIST);
            }
            if (ResponseCode.CRUD_NAME_NOT_EXIST.getMessage().equals(errorMessage)) {
                throw new BusinessException(ResponseCode.CRUD_NAME_NOT_EXIST);
            }
            throw new BusinessException(ResponseCode.CRUD_PARAM_NOT_EXIST);
        }
        if (errorMessage == null) {
            throw new BusinessException(ResponseCode.DB_PARAM_NOT_EXIST);
        }
        throw new BusinessException(ResponseCode.FAIL);
    }

    /**
     * 判断表达式是否为 false
     *
     * @param expression   boolean 表达式
     * @param errorMessage 错误信息
     */
    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new BusinessException(switchIllegalStateException(
                new IllegalStateException(String.valueOf(errorMessage))));
        }
    }
}
