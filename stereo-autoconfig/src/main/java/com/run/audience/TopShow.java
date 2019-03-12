package com.run.audience;

import org.springframework.stereotype.Component;

@Component("topShow")
public class TopShow implements Performance {

	@Override
	@myPerform
	public void perform() {
		// TODO Auto-generated method stub
		System.out.println("this is a topshow.");
	}

}
