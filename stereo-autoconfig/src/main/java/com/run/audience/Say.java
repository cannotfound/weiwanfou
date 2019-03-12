package com.run.audience;

import retry.NeedRetry;

public interface Say {
	
	@NeedRetry(times=6, waitTime=2000)
	public void run(int msg);
}
