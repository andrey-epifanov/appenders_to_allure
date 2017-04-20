package ru.yandex.qatools.allure.logback.appender;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.StepFailureEvent;
import ru.yandex.qatools.allure.events.StepFinishedEvent;

/**
 * @author Andrey Epifanov
 * andrey.a.epifanov@gmail.com
 *         Date: 2017.03.17
 */
@SuppressWarnings("unused")
@Aspect
public class LogBackAllureStepsAspectsCTW {
    private static final Logger logger = LoggerFactory.getLogger(LogBackAllureStepsAspectsCTW.class);

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void withTestAnnotation() {
        //pointcut body, should be empty
    }

    @AfterThrowing(pointcut = "anyMethod() && withTestAnnotation()", throwing = "e")
    public void onTestFailure(JoinPoint joinPoint, Throwable e) {
        logger.error("Listener - test has failure.");
        logger.info("");
        Allure.LIFECYCLE.fire(new StepFailureEvent().withThrowable(e));
        Allure.LIFECYCLE.fire(new StepFinishedEvent());
    }


    @Pointcut("execution(* ru.yandex.qatools.allure.aspects.AllureStepsAspects.stepStart(..))")
    public void stepsStartMethod() {
        //pointcut body, should be empty
    }

    @Before("stepsStartMethod()")
    public void aroundStepsStart(ProceedingJoinPoint pjp) throws Throwable {
        LogbackToAllureAppender.flush();
    }

    @Pointcut("execution(* ru.yandex.qatools.allure.aspects.AllureStepsAspects.stepFailed(..))")
    public void stepsFailedMethod() {
        //pointcut body, should be empty
    }

    @AfterReturning("stepsFailedMethod()")
    public void afterStepFailed(ProceedingJoinPoint pjp) throws Throwable {
        LogbackToAllureAppender.flush();
    }

    @Pointcut("execution(* ru.yandex.qatools.allure.aspects.AllureStepsAspects.stepStop(..))")
    public void stepsStopMethod() {
        //pointcut body, should be empty
    }

    @AfterReturning("stepsStopMethod()")
    public void afterStepStop(ProceedingJoinPoint pjp) throws Throwable {
        LogbackToAllureAppender.flush();
    }
}
