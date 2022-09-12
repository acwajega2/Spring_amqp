package com.wacsoft.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String TEST_QUE_01 = "test_que_01";
    public static final String TEST_EXCHANGE_01 = "test_exchange_01";
    public static final String ROUTING_KEY_01 = "routing_key_01";

    //-------------> We create a que
    @Bean
    public Queue queue(){
        return new Queue(TEST_QUE_01);
    }

    //---------------> We create an Exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(TEST_EXCHANGE_01);
    }


    //--------------> We then bind que to exchange
    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY_01);
    }


    //----> for converting messages --> String to Object
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //-----> Adding Rabbit MQ Template
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }



}
