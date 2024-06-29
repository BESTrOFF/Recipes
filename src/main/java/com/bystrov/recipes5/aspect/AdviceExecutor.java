package com.bystrov.recipes5.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class AdviceExecutor {

    @Before("PointCutSearcher.watchRecipe())")
    public void somebodyWatchRecipe(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String recipeName = args[0].toString();

        System.out.println("SOMEBODY TRIED TO WATCH RECIPE " + recipeName);
    }
}
