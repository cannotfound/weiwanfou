package com.run.audience;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 通过注解切面，向performance注入新的接口实现，
 * @author only_TG
 *
 */
@Aspect
public class AddISayToIPerformance {

	
	@DeclareParents(value="com.run.audience.Performance+", defaultImpl=com.run.audience.Hello.class)
	public static Say say;
}
