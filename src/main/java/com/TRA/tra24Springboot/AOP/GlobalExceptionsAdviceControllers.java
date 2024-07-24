package com.TRA.tra24Springboot.AOP;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionsAdviceControllers {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionsAdviceControllers.class);

    @Pointcut("execution(* com.TRA.tra24Springboot.Controllers.*.*(..))")
    public void controllerLayerPointcut() {
    }

    @AfterThrowing(pointcut = "controllerLayerPointcut()", throwing = "ex")
    public void handleGlobalException(Exception ex) {
        logger.error("Exception caught in GlobalExceptionHandlingAspect: ", ex);

        if (ex instanceof NullPointerException) {
            handleNullPointerException((NullPointerException) ex);
        } else if (ex instanceof IllegalArgumentException) {
            handleIllegalArgumentException((IllegalArgumentException) ex);
        } else {
            handleGenericException(ex);
        }
    }

    private void handleNullPointerException(NullPointerException ex) {
        logger.error("NullPointerException occurred: ", ex);
    }

    private void handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException occurred: ", ex);
    }

    private void handleGenericException(Exception ex) {

        logger.error("An unexpected exception occurred: ", ex);
    }
}
