package spittr.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitTemplate template;
	
	public void login(String user) {

		String body = user + "登陆了！!!";
		MessageProperties properties = new MessageProperties();
		properties.setContentEncoding("utf-8");
		properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		properties.setPriority(1);
		properties.setHeader("login", "真的吗？");
		Message message = new Message(body.getBytes(), properties);
		
		this.template.convertAndSend("spittr.exchange", "spittr.login", message);
		this.template.convertAndSend("spittr.exchange", "spittr.login2", message);
		

	}
	
	@RabbitListener(queues = "spittr.login2")
	public void receive2(Message msg) {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null != msg)
			System.out.println("收到消息2：" + new String(msg.getBody()) + ";    " + msg.getMessageProperties());	
		
	}
	
	@RabbitListener(queues = "spittr.login")
	public void receive(Message msg) {
		
		if(null != msg)
			System.out.println("收到消息：" + new String(msg.getBody()) + ";    " + msg.getMessageProperties());
			
		
		/*Message message = this.template.receive("spittr.login");
		
		Message message2 = this.template.receive("spittr.login2");
		
		if(null != message)
		System.out.println("收到消息1：" + message.getBody() + ";    " + message.getMessageProperties());
		
		if(null != message2)
		System.out.println("收到消息2：" + message2.getBody() + ";  " + message2.getMessageProperties());
		*/
		
		
	}
}
