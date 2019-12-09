package spittr.config;

import java.util.Collection;
import java.util.Map;

import javax.persistence.metamodel.Bindable.BindableType;

import org.apache.commons.collections.Factory;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

@EnableRabbit
public class RabbitMQConfig {

	@Bean
	public ConnectionFactory getConnectionFactory() {

		// ConnectionFactory factory = new ConnectionFactory();
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setPort(5672);
		factory.setHost("127.0.0.1");

		return factory;
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {

		RabbitAdmin admin = new RabbitAdmin(factory);
		admin.isAutoStartup();

		Queue queue = new Queue("spittr.login");
		Queue queue2 = new Queue("spittr.login2");
		admin.declareQueue(queue);
		admin.declareQueue(queue2);

		Exchange exchange = new DirectExchange("spittr.exchange");
		admin.declareExchange(exchange);

		Binding binding = new Binding("spittr.login", Binding.DestinationType.QUEUE, "spittr.exchange", "spittr.login",
				null);

		Binding binding2 = new Binding("spittr.login2", Binding.DestinationType.QUEUE, "spittr.exchange",
				"spittr.login2", null);

		admin.declareBinding(binding);
		admin.declareBinding(binding2);

		admin.afterPropertiesSet();

		return admin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {

		RabbitTemplate template = new RabbitTemplate(factory);
		template.setExchange("spittr.exchange");
		template.setQueue("spittr.login");
		template.afterPropertiesSet();

		return template;

	}

	@Bean
	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		// SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}
