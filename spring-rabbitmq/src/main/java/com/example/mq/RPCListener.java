package com.example.mq;

import com.example.object.User;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RPCListener implements ChannelAwareMessageListener {

    @Autowired
    private MessagePropertiesConverter messagePropertiesConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            User user = SerializationUtils.deserialize(message.getBody());
            System.out.println("The message content is: " + user);

            MessageProperties messageProperties = message.getMessageProperties();
            AMQP.BasicProperties rabbitMQProperties = messagePropertiesConverter.fromMessageProperties(messageProperties, "UTF-8");
            System.out.println("The message's correlationId is:" + rabbitMQProperties.getCorrelationId());

            User replyUser = new User();
            replyUser.setName(user.getName() + "_reply");
            replyUser.setVerification(!user.getVerification() ? true : user.getVerification());

            channel.basicPublish("", rabbitMQProperties.getReplyTo(), rabbitMQProperties, SerializationUtils.serialize(replyUser));
            channel.basicAck(messageProperties.getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
