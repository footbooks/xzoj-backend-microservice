package com.zengjing.xzojbackendjudgeservice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitRabbitMq {
    public static void doInit(){
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchagne";
            //创建交换机
            channel.exchangeDeclare(EXCHANGE_NAME,"direct");
            //创建队列
            String QUEUE_NAME = "code_queue";
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //绑定队列和交换机
            channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"my_routingKey");
            log.info("消息队列启动成功");
        }catch (Exception e){
            log.error("项目启动失败");
        }
    }

    public static void main(String[] args) {
        doInit();
    }
}
