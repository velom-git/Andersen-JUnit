import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FactrFactr {
    public final static String QUEUE_NAME = "mychannel";
    public static ConnectionFactory factory;
    public static Connection connection;
    public static Channel channel;
    public static DeliverCallback deliverCallback;





    public static ConnectionFactory getFactory() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        return factory;
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        connection = factory.newConnection();
        return connection;
    }

    public static Channel getChannel() throws IOException {
        channel = connection.createChannel();
        channel.queueDeclare("mychannel", false, false, false, null);
        return channel;
    }
}
