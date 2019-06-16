package com.example.mq;

import com.example.object.User;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SendMessage {

    @Autowired
    private RabbitTemplate amqpTemplate;

    public String sendToRPC(String name) {
        User user = new User();
        user.setName(name == null ? "default" : name);
        user.setVerification(false);

        try {
            MessageProperties msgProp = new MessageProperties();
            Message msg = new Message(SerializationUtils.serialize(user), msgProp);
            Object response = amqpTemplate.convertSendAndReceive("RPC", msg);
            User userReply = SerializationUtils.deserialize((byte[]) response);
            System.out.println("userReply:" + userReply);
            return userReply.toString();
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    public String sendToRouting(String message) {
        try {
            message = (message == null) ? "default" : message;
            amqpTemplate.convertAndSend("notice-exchange", "Notice", message);
            return "Send Message: '" + message + "' , Success!";
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
}
