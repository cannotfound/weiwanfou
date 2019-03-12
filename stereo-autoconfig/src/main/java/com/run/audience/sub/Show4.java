package com.run.audience.sub;

import org.springframework.stereotype.Component;

import com.run.audience.Performance;
import com.run.audience.myShow;

@Component
@myShow
public class Show4 implements Performance {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("this is show 4.");
		

		
	}

}
