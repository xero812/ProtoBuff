import org.junit.Test;
import protobuf.Message.MyMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

public class Tester {

    public static final String FILE_NAME = "message.bin";

    public Main driver = getInstance();

    @Test
    public void testForPersistence() {
        driver = getInstance();
        boolean isFileCreated = driver.persistData(FILE_NAME, getMyMessage());
        assertTrue(isFileCreated);
    }

    @Test
    public void testForRetrieval() {
        Optional<MyMessage> message = driver.retrieveData(FILE_NAME);
        assertTrue(message.isPresent());
        assertEquals(getMyMessage(), message.get());
    }

    private MyMessage getMyMessage() {
        MyMessage.Builder builder = MyMessage.newBuilder();
        builder.setId(100)
                .setName("Arya Stark")
                .setIsCool(Boolean.TRUE);
        return builder.build();
    }

    private Main getInstance() {
        return new Main();
    }
}
