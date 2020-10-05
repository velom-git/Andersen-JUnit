import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.json.JSONUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class SendAndRecvTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void localhost() {
        ConnectionFactory factory = FactrFactr.getFactory();
        String host = factory.getHost();
        assertEquals(host, "localhost");
    }

    @Test
    public void getFactoryRecv() {
        String name = FactrFactr.getFactory().getClass().getName();
        assertEquals(name, "com.rabbitmq.client.ConnectionFactory");
    }

    @Test
    public void getConnection() throws IOException, TimeoutException {
        String name = FactrFactr.getConnection().getClass().getName();
        assertEquals(name, "com.rabbitmq.client.impl.recovery.AutorecoveringConnection");
    }

    @Test
    public void getChannel() throws IOException {
        String name = FactrFactr.getChannel().getClass().getName();
        System.out.println(name);
        assertEquals(name, "com.rabbitmq.client.impl.recovery.AutorecoveringChannel");
    }

    @Test
    public void messages() throws IOException, TimeoutException, InterruptedException {
        Send send = new Send();
        Recv recv = new Recv();
        List<String> senList = new ArrayList() {{
            add("Raz");
            add("Dva");
        }};
        send.send(senList);
        List<String> recList = recv.recv();
        assertEquals(senList, recList);
    }

}



