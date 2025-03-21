package org.example.plus.aop;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.plus.domain.common.annotation.Auth;
import org.example.plus.domain.common.dto.AuthUser;
import org.example.plus.domain.log.service.LogService;
import org.example.plus.domain.manager.dto.request.ManagerSaveRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SaveManagerLoggingAspect {
    private final HttpServletRequest request;
    private final LogService logService;

     @Pointcut("execution(* org.example.plus.domain.manager.service.ManagerService.saveManager(..)) && args(authUser, todoId, managerSaveRequest)")
    //@Pointcut("execution(* org.example.plus.domain.manager.service.ManagerService.saveManager(..))")
    public void managerServiceMethods(AuthUser authUser, long todoId, ManagerSaveRequest managerSaveRequest) {}
    //public void managerServiceMethods() {}
    @Around("managerServiceMethods(authUser, todoId, managerSaveRequest)")
    public Object logManagerService(ProceedingJoinPoint joinPoint, AuthUser authUser, long todoId, ManagerSaveRequest managerSaveRequest) throws Throwable{
        //AuthUser authUser = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpServletRequest request = getRequest();
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        Long managerId = managerSaveRequest.getManagerUserId();

        log.error("[REQUEST] 요청 URL: {} | HTTP Method: {} | Manager ID: {}", requestURI, requestMethod, managerId);

        // 요청 로그 저장
        logService.createLog(requestURI, requestMethod, managerId);

        Object proceed = null;

        try {
             proceed = joinPoint.proceed();
             logService.successLog(requestURI,requestMethod, managerId, HttpStatus.OK.name());
        } catch (Exception e) {
            logService.failedLog(requestURI,requestMethod, managerId, e.getMessage(), HttpStatus.BAD_REQUEST.name());
        }

        return proceed;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
