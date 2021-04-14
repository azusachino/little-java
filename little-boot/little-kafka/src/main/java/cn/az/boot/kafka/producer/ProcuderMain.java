package cn.az.boot.kafka.producer;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Producer Main
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see org.apache.kafka.clients.producer.KafkaProducer
 * @since 2020-03-12
 */
public class ProcuderMain {

    @SneakyThrows
    public static void main(String[] args) {
        main1();
    }

    @SneakyThrows
    public static void main1() {
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("sf", 1, "message", "Bonjour");

        Future<RecordMetadata> metadataFuture = kafkaProducer.send(producerRecord);

        RecordMetadata recordMetadata = metadataFuture.get();

        System.out.println(recordMetadata);
    }

}
