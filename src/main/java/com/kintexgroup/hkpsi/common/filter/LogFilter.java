package com.kintexgroup.hkpsi.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 日志自定义过滤器
 *
 * @author gradylo
 * @since 2020/10/10 5:07 下午
 */
public class LogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        // 设置过滤条件
        boolean infoBool = "INFO".equals(event.getLevel().toString());
        boolean errorBool = "ERROR".equals(event.getLevel().toString());
        boolean warnBool = "WARN".equals(event.getLevel().toString());
        if (errorBool) {
            //允许输入
            return FilterReply.ACCEPT;
        }
        boolean msgBool = (event.getMessage().contains("==") && !event.getMessage()
            .contains("CONDITIONS EVALUATION REPORT"));
        if (infoBool || msgBool || warnBool) {
            //允许输入
            return FilterReply.ACCEPT;
        }
        //不允许输出
        return FilterReply.DENY;
    }

}
