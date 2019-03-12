package com.run.audience.sub;

import org.springframework.stereotype.Component;

import com.run.audience.Performance;

@Component
public class Show3 implements Performance {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("this is show 3.");
	}

}
