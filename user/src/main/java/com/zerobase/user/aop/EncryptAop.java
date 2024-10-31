package com.zerobase.user.aop;

import com.zerobase.user.annotion.EncryptPassword;
import com.zerobase.user.util.EncryptUtil;
import java.lang.reflect.Field;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncryptAop {

    @Pointcut("execution(* com.zerobase.user.user.controller.UserController.*(..))")
    public void userController() {
    }

    @Around("userController()")
    public Object encryptData(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            for (Field field : arg.getClass().getDeclaredFields()) {
                if (field.getAnnotation(EncryptPassword.class) != null) {

                    field.setAccessible(true);
                    Object encryptField = field.get(arg);
                    field.set(arg, EncryptUtil.sha256Encrypt(encryptField.toString()));
                }
            }
        }
        
        return joinPoint.proceed();
    }
}
