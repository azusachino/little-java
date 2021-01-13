package cn.az.java.cloud.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author az
 * @since 2020-04-17
 */
public class ObjectSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String s, Object o) {

        System.out.println("topic : " + s + " , object : " + o);
        byte[] dataArray;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);

            dataArray = outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return dataArray;
    }
}
