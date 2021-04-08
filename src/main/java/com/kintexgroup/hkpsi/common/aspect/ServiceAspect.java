package com.kintexgroup.hkpsi.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * https://zhuanlan.zhihu.com/p/337580365
 *
 * @author : YTY
 * @description : AOP Service层 警告日志监控
 * 监控所有Service，针对不同Service的执行实践进行日志打印
 * @date : 2020-11-30 09:39
 */
@Aspect
@Component
@Slf4j
public class ServiceAspect {
    /*
      AOP通知：
      1、前置通知：在方法调用前执行
      2、后置通知：在方法真长调用之后执行
      3、环绕通知：方法调用前后，都分别可以执行
      4、异常通知：方法调用过程中发生异常，则通知
      5、最终通知：方法调用后执行
     */

    /**
     * 切面表达式：
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表AOP监控的类所在包
     * 第三处 .*. 代表匹配某一个包，因为包的命名都是有一定规范的
     * 第四处 .. 代表该包以及其子包下的所有类方法
     * 第五处 * 代表类名， *代表所有类
     * 第六处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */


    @Around("execution(* com.kintexgroup.hkpsi.*.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("====== 开始执行 {}.{}() =======",
            joinPoint.getTarget().getClass(),
            joinPoint.getSignature().getName());
        var args = joinPoint.getArgs();
        if (args.length > 0) {
            log.info("方法入参：{}", args);
        }
        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 执行目标 Service
        Object result = joinPoint.proceed();
        // 记录开始时间
        long end = System.currentTimeMillis();
        long takeTime = end - begin;

        log.info("====== 执行结束，耗时：{} 毫秒 ======", takeTime);

        return result;

    }


}


