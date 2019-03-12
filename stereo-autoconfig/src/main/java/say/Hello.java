package say;

import org.springframework.stereotype.Component;

@Component
public class Hello implements ISay {

	@Override
	public void run() {
		
		System.out.print("say hello.");

	}

}
