package com.kintexgroup.hkpsi.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/30 11:25 上午
 */
public class ContextHolderUtil {

    private ContextHolderUtil() {
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        var obj = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (obj == null) {
            throw new RuntimeException("ServletRequestAttributes none bound!");
        }
        return obj
            .getRequest();
    }

    /**
     * 获取httpSession
     *
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取request中的值
     *
     * @param key request的key
     * @return
     */
    public static Object getRequestAttribute(String key) {
        return getRequest().getAttribute(key);
    }

    /**
     * 获取Token中的用户id
     *
     * @return
     */
    public static int getAuthedUserId() {
        return (int) getRequestAttribute("id");
    }
    
    @SuppressWarnings("unchecked")
    public static List<Integer> getAuthedUserAccess() {
        return (ArrayList<Integer>)getRequestAttribute("access");
    }

}
