import com.sun.org.apache.regexp.internal.RE;
import protobuf.Message;
import protobuf.Message.MyMessage;

import java.io.*;
import java.util.Optional;

public class Main {

    public static final String RESOURCE_PATH = ".\\resources\\";

    public static void main(String[] args) {
    //    System.out.println(new Main().createFile("aaa.bin"));
    }

    public boolean persistData(String fileName, MyMessage message) {
        FileOutputStream outputStream;
        String filePath = RESOURCE_PATH + fileName;
        try {
            outputStream = new FileOutputStream(filePath);
            message.writeTo(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        return file.exists();
    }

    public Optional<MyMessage> retrieveData(String fileName) {
        String filePath = RESOURCE_PATH + fileName;
        MyMessage message = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            message = MyMessage.parseFrom(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<MyMessage> result = Optional.ofNullable(message);
        return result;
    }

    private MyMessage getMyMessage() {
        MyMessage.Builder builder = MyMessage.newBuilder();
        builder.setId(100)
                .setName("Arya Stark")
                .setIsCool(Boolean.TRUE);
        return builder.build();
    }

}
