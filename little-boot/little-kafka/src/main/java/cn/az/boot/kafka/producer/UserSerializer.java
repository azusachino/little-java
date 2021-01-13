package cn.az.boot.kafka.producer;

import cn.az.boot.kafka.entity.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * UserSerializer
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see cn.az.boot.kafka.producer
 * @since 2020-03-12
 */
public class UserSerializer implements Serializer<User> {
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
     * Convert {@code data} into a byte array.
     *
     * @param topic topic associated with data
     * @param data  typed data
     * @return serialized bytes
     */
    @Override
    public byte[] serialize(String topic, User data) {
        return new byte[0];
    }

    /**
     * Convert {@code data} into a byte array.
     *
     * @param topic   topic associated with data
     * @param headers headers associated with the record
     * @param data    typed data
     * @return serialized bytes
     */
    @Override
    public byte[] serialize(String topic, Headers headers, User data) {
        return new byte[0];
    }

    /**
     * Close this serializer.
     * <p>
     * This method must be idempotent as it may be called multiple times.
     */
    @Override
    public void close() {

    }
}
