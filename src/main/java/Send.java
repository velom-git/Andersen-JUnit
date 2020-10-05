import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = FactrFactr.QUEUE_NAME;
    private static Channel channel;

    public void send(List<String> list) throws IOException, TimeoutException {
        ConnectionFactory factory = FactrFactr.getFactory();
        Connection connection = FactrFactr.getConnection();
        channel = FactrFactr.getChannel();
        sendList(list);
    }

    public void sendList(List<String> list) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            String message = list.get(i);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Отправил '" + message + "'");
        }
    }


}
