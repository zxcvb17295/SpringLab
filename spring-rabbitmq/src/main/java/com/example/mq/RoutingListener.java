package com.example.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RoutingListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println("[Notice]: " + messageBody);
    }
}
