package cn.az.boot.kafka.consumer;

import cn.az.boot.kafka.entity.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * UserDeSerializer
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see cn.az.boot.kafka.consumer
 * @since 2020-03-12
 */
public class UserDeSerializer implements Deserializer<User> {
    /**
     * Configure this class.
     *
     * @param configs configs in key/value pairs
     * @param isKey   whether is for key or value
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    /**
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic topic associated with the data
     * @param data  serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public User deserialize(String topic, byte[] data) {
        User o;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            o = (User) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.printf("topic [%s], object [%s]", topic, o);
        return o;
    }

    /**
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic   topic associated with the data
     * @param headers headers associated with the record; may be empty.
     * @param data    serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public User deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    /**
     * Close this deserializer.
     * <p>
     * This method must be idempotent as it may be called multiple times.
     */
    @Override
    public void close() {

    }
}
