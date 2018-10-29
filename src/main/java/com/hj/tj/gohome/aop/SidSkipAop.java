package com.hj.tj.gohome.aop;

import com.hj.tj.gohome.annotation.SidSkip;
import com.hj.tj.gohome.cosntants.TextConstants;
import com.hj.tj.gohome.entity.PortalUser;
import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.exception.JwtException;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.service.PortalUserService;
import com.hj.tj.gohome.utils.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 8:54
 */
@Aspect
@Order(value = 1)
@Component
public class SidSkipAop {

    private static Logger log = LoggerFactory.getLogger(SidSkipAop.class);

    @Resource
    private PortalUserService portalUserService;

    @Pointcut("execution(* com.hj.tj.gohome.controller.*Controller.*(..))")
    public void methodPointCut() {
    }

    @Around("methodPointCut()")
    public Object sidSkip(ProceedingJoinPoint joinPoint) {
        try {
            log.info("[action = `sidSkip`, sid check start.]");
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            Method method = methodSignature.getMethod();
            SidSkip methodSidSkipAnnotation = method.getAnnotation(SidSkip.class);
            SidSkip classSidAnnotation = method.getDeclaringClass().getAnnotation(SidSkip.class);

            if (Objects.nonNull(methodSidSkipAnnotation) || Objects.nonNull(classSidAnnotation)) {
                Object obj = joinPoint.proceed();
                return obj;
            }

            // 进行验证
            Object[] args = joinPoint.getArgs();

            String sid = "";
            if (Objects.nonNull(args) && args.length > 0 && Objects.nonNull(args[0])) {
                if (args[0] instanceof ApiRequest) {
                    ApiRequest apiRequest = (ApiRequest) args[0];
                    sid = apiRequest.getSid();
                } else if (args[0] instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) args[0];
                    sid = request.getParameter("sid");
                }
            }

            if (StringUtils.isEmpty(sid) || null == JwtUtil.decode(sid)) {
                return JsonResponse.newError(TextConstants.SID_NOT_EXIST);
            }

            PortalUser portalUser = portalUserService.getPortalUserBySid(sid);

            if (Objects.isNull(portalUser)) {
                return JsonResponse.newError(TextConstants.USER_NOT_EXIST);
            }

            if (Objects.isNull(portalUser.getSidExpire())) {
                return JsonResponse.newError(TextConstants.SID_NOT_EXIST);
            }

            if (portalUser.getSidExpire().compareTo(new Date()) < 0) {
                return JsonResponse.newError(TextConstants.SID_IS_EXPIRE);
            }

            return joinPoint.proceed();
        } catch (CustomException customException) {
            ErrorMsgEnum errorMsgEnum = customException.getErrorMsgEnum();
            return JsonResponse.newError(errorMsgEnum.getCode(), errorMsgEnum.getUserMsg());
        } catch (JwtException e) {
            log.debug("jwt 验证异常", e);
            return JsonResponse.newError(TextConstants.SID_NOT_EXIST);
        } catch (Throwable e) {
            log.error("[sys_error]", e);
            return JsonResponse.newError(ErrorMsgEnum.SYS_ERR);
        }
    }
}
