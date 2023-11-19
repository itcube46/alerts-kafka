package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AuditProducer {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties kafkaProperties = getKafkaProperties();
        try (Producer<String, String> producer = new KafkaProducer<>(kafkaProperties)) {
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>("audit", null, "audit event");

            RecordMetadata result = producer.send(producerRecord).get();
            System.out.printf("offset = %d, topic = %s, timestamp = %d\n",
                    result.offset(), result.topic(), result.timestamp());
        }
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("acks", "all");
        kafkaProperties.put("retries", "3");
        kafkaProperties.put("max.in.flight.requests.per.connection", "1");
        return kafkaProperties;
    }
}
