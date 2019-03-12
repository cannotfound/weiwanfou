package com.run.audience;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Show2 implements Performance {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("this is show2.");
	}

}
