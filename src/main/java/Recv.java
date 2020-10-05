import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class Recv {
    private final static String QUEUE_NAME = FactrFactr.QUEUE_NAME;
    private static DeliverCallback deliverCallback;

    public List<String> recv() throws IOException, TimeoutException, InterruptedException {
        Channel channel = FactrFactr.getChannel();
        System.out.println("Проверяем сообщения...");
        List<String> list = getMessages();
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {  });
        Thread.sleep(500);
        return list;
    }

    public List<String> getMessages() {
        ArrayList<String> list = new ArrayList<>();
        deliverCallback = (consumerTag, delivery) -> { // оповещает о пришедшем сообщении, использует лямбду через Consumer
            String message = new String(delivery.getBody(), "UTF-8");
            list.add(message);
            System.out.println("Сохранено: '" + message + "'");
        };
        return list;
    }

}