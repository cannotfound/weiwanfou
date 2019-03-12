package audience;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.run.audience.AudienceConfig;
import com.run.audience.Performance;
import com.run.audience.Say;
import com.run.audience.TopShow;
import com.run.audience.myShow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AudienceConfig.class)
public class AudienceTest {

	@Autowired
	private Performance performance1;
	
	@Autowired
	@myShow
	private Performance performance2;
	
	@Autowired
	@Qualifier("show3")
	private Performance performance3;
	
	@Autowired
	@Qualifier("topShow")
	private Performance performance4;
	
	@Autowired
	private Say say;
	
	@Test
	public void test() {
		
	
		//performance1.perform();
		//performance2.perform();
		//performance3.perform();
		//performance4.perform();
		
		
		say.run(1);
		
		
		
	}

}
