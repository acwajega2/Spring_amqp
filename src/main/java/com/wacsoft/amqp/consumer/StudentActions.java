package com.wacsoft.amqp.consumer;

import com.wacsoft.amqp.config.MessagingConfig;
import com.wacsoft.amqp.dto.StudentResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StudentActions
{


    @RabbitListener(queues = MessagingConfig.TEST_QUE_01)
    public void consumeMessageFromQue(StudentResponse studentResponse){
        System.out.println("Message recieved from que"+studentResponse);
    }
}
