package com.run.audience;

import org.springframework.stereotype.Component;

import retry.NeedRetry;

@Component
public class Hello implements Say {

	@Override
	public void run(int msg) {
		
		
		System.out.println("this is say hello.");
		
		if(msg == 1) {
			int a = 0;
			int b = 1;
			int c = 0;
			
			c = b / a;
		}
	}

}
