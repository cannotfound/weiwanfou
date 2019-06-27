package spittr.aop;

import java.util.Random;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import spittr.pojo.Spitter;
import spittr.repository.SpitterRepositoryImpl;

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
}
