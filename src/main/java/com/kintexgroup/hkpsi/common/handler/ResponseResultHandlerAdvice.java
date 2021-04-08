package com.kintexgroup.hkpsi.common.handler;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import com.kintexgroup.hkpsi.common.dto.ResponseDTO;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.common.util.JsonMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author gradylo
 * @since 2020/8/23 18:35
 */
@ControllerAdvice(annotations = ResponseWrapper.class)
@Slf4j
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.info("returnType:" + returnType);
        log.info("converterType:" + converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 判断响应的Content-Type为JSON格式的body
        if (MediaType.APPLICATION_JSON.equals(selectedContentType)) {
            // 如果响应返回的对象为统一响应体，则直接返回body
            if (body instanceof ResponseDTO) {
                return body;
            } else {
                // 只有正常返回的结果才会进入这个判断流程，所以返回正常成功的状态码
                return new ResponseDTO(ResponseCode.SUCCESS.getCode(),
                    ResponseCode.SUCCESS.getMessage(),
                    body);
            }
        }
        ResponseDTO responseDTO = new ResponseDTO(ResponseCode.SUCCESS.getCode(),
            ResponseCode.SUCCESS.getMessage(),
            body);
        return JsonMapper.nonEmptyMapper()
            .toJson(responseDTO);
    }

}
