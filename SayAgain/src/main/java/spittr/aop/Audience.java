package spittr.aop;

import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import spittr.pojo.Spitter;
import spittr.pojo.SpitterUser;
import spittr.repository.SpitterRepositoryImpl;
import spittr.service.RabbitMQService;

@Aspect
public class Audience {


	@Autowired
	private SpitterRepositoryImpl spitterRepositoryImpl;
	
	//@Pointcut("execution(* spittr.repository.SpitterRepositoryImpl.findByUsername(..))")
	public void newSpitterBeforeFind() {}
	
	
	//@Before("newSpitterBeforeFind()")
	public void saveASpitter() {
		
		System.out.println("save a spitter before findByusername()-----------");
		
		Spitter cell = new Spitter();
		cell.setEmail("09@qq.com");
		cell.setFirstName("Ju");
		cell.setLastName("fc");
		cell.setUsername("ran_" + new Random().nextInt(10000));
		
		//SpitterRepositoryImpl spitterRepositoryImpl = new SpitterRepositoryImpl();
		
		this.spitterRepositoryImpl.save(cell);
	}
	
	@Pointcut("execution(* spittr.web.HomeController.login2(..))")
	public void afterLoginPC() {}
	
	@Autowired
	private RabbitMQService rabbitMQService;

	@AfterReturning(returning="kvt", pointcut="afterLoginPC()")
	public void afterLogin(JoinPoint point, Object kvt) {
		
		
		String ekvt = "redirect:homepage";
		Object[] args = point.getArgs();

		System.out.println(point.getSignature() + " 返回 了： " + kvt + "; args.length=" + args.length);
		String username = "";
		if(args.length >= 4) {
			
			SpitterUser user = (SpitterUser) args[2];
			username = user.getUsername();
		}
		if(ekvt.equals(kvt)) {
			
			this.rabbitMQService.login(username);
			
			
		}
		
	}
}
