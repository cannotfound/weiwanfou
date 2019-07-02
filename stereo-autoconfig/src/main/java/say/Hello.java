package say;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.run.audience.Performance;

@Component("hello_nc")
public class Hello implements ISay, Performance {

	@Override
	public void run() {
		
		System.out.print("say hello.");

	}
	
	public void byebye() {
		System.out.println("byebye.");
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		
	}

}
