package consumer;

import avro.alert.Alert;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AlertConsumer {
    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kafkaProperties.put("group.id", "test_consumer");
        kafkaProperties.put("enable.auto.commit", "true");
        kafkaProperties.put("auto.commit.interval.ms", "1000");
        kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        kafkaProperties.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        kafkaProperties.put("schema.registry.url", "http://localhost:8081");

        AlertConsumer consumer = new AlertConsumer();
        consumer.consume(kafkaProperties);

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
    }

    private void consume(Properties properties) {
        try (KafkaConsumer<Long, Alert> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(List.of("alert_test"));

            while (keepConsuming) {
                ConsumerRecords<Long, Alert> records = consumer.poll(Duration.ofMillis(250));
                for (ConsumerRecord<Long, Alert> record : records) {
                    System.out.printf("offset = %d, value = %s\n", record.offset(), record.value());
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }
}
