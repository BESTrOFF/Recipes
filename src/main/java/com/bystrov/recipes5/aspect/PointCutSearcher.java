package com.bystrov.recipes5.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class PointCutSearcher {

    @Pointcut("execution(public * com..RecipeController.recipe(..))")
    public void watchRecipe() {
    }
}
