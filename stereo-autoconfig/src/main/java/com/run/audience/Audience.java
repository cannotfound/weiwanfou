package com.run.audience;

import java.lang.reflect.Method;

import javax.management.relation.InvalidRelationTypeException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import retry.NeedRetry;

@Aspect
public class Audience {

	@Pointcut("execution(* com.run.audience.Say.run(int))")
	public void aroundPointCut() {
	}

	/**
	 * --@annotation 指到方法
	 */
	@Pointcut("execution(* com.run.audience.Performance.perform(..)) && @annotation(com.run.audience.myPerform)")
	public void whenPerform() {

	}

	@Pointcut("execution(* com.run.audience.Performance.perform(..)) && @within(com.run.audience.myPerform)")
	public void whenPerform2() {

	}

	/**
	 * within 指定到类
	 */
	@Pointcut("execution(* com.run.audience.Performance.perform(..)) && within(com.run.audience.sub.*)")
	public void onlySub() {

	}

	@Around("aroundPointCut()")
	public Object aroundSay(ProceedingJoinPoint jp) throws InterruptedException {

		System.out.println("----start-------");

		MethodSignature ms = (MethodSignature) jp.getSignature();
		Object arg0 = jp.getArgs()[0];
		Method method = ms.getMethod();

		NeedRetry annotationRetry = method.getAnnotation(NeedRetry.class);

		String methodName = method.getName();

		int times = annotationRetry.times();
		long waitTime = annotationRetry.waitTime();

		for (; times >= 0; times--) {

			try {

				if(times == 1) {
					jp.proceed(new Object[] {2});
				}else {
					jp.proceed();
				}
				
				System.out.println("-----执行成功---------");
				
				return 1;

			} catch (Exception  e) {

				
				System.out.printf("%s执行出错(参数=%s)，正在重试，重试机会还有%s 次.\n", ms, arg0, times);

				if (waitTime > 0) {
					Thread.sleep(waitTime);
				}

			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		System.out.printf("%s执行失败.\n", ms);
		

		return 0;

	}

	@Before("onlySub()")
	public void silenceCellPhones2() {
		System.out.println("----------Silencing cell phones--------");
	}

	@Before("whenPerform()")
	public void silenceCellPhones() {
		System.out.println("Silencing cell phones...");
	}

	@Before("whenPerform2()")
	public void silenceCellPhones3() {
		System.out.println("+++++++++++=Silencing cell phones++++++++++");
	}

	@AfterReturning("whenPerform()")
	public void afterperform() {
		System.out.println("after perform()... ");
	}

}
