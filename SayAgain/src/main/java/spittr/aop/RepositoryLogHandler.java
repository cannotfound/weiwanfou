package spittr.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class RepositoryLogHandler {


	public void logAfterRepositoryExecute(JoinPoint jp) {
		
		MethodSignature ms = (MethodSignature) jp.getSignature();
		System.out.println("------logAfterRepositoryExecute----" + ms);
		
		
	}
}
