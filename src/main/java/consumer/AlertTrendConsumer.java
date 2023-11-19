package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import serde.AlertKeySerde;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AlertTrendConsumer {
    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kafkaProperties = getKafkaProperties();
        AlertTrendConsumer consumer = new AlertTrendConsumer();
        consumer.consume(kafkaProperties);

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("enable.auto.commit", "true");
        kafkaProperties.put("group.id", "alert_trend_group");
        kafkaProperties.put("key.deserializer", AlertKeySerde.class.getName());
        kafkaProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        return kafkaProperties;
    }

    private void consume(final Properties kafkaProperties) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties)) {
            consumer.subscribe(List.of("alert_trend"));

            while (keepConsuming) {
                var records = consumer.poll(Duration.ofMillis(250));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, value = %s\n", record.offset(), record.value());
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }
}
