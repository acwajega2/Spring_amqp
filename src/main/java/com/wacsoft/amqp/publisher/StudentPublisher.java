package com.wacsoft.amqp.publisher;


import com.wacsoft.amqp.config.MessagingConfig;
import com.wacsoft.amqp.dto.Student;
import com.wacsoft.amqp.dto.StudentResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentPublisher {
    @Autowired
    private RabbitTemplate template;


    @PostMapping(value = "/saveStudent",produces= MediaType.APPLICATION_JSON_VALUE)
    public String saveStudent(@RequestBody Student student){


        StudentResponse response = new StudentResponse();
        response.setResponseCode(200);
        response.setStudent(student);
        response.setMessage("STUDENT SAVED SUCCESSFULLY");

        template.convertAndSend(MessagingConfig.TEST_EXCHANGE_01,MessagingConfig.ROUTING_KEY_01,response);

        return "Success";
    }

}
