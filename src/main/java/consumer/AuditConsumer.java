package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AuditConsumer {
    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kafkaProperties = getKafkaProperties();
        final AuditConsumer auditConsumer = new AuditConsumer();
        auditConsumer.consume(kafkaProperties);
        Runtime.getRuntime().addShutdownHook(new Thread(auditConsumer::shutdown));
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("enable.auto.commit", "false");
        kafkaProperties.put("group.id", "audit_group");
        kafkaProperties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        return kafkaProperties;
    }

    private void consume(final Properties kafkaProperties) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties)) {

            consumer.subscribe(List.of("audit"));

            while (keepConsuming) {
                var records = consumer.poll(Duration.ofMillis(250));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, value = %s\n", record.offset(), record.value());

                    OffsetAndMetadata offsetMeta = new OffsetAndMetadata(record.offset() + 1, "");

                    Map<TopicPartition, OffsetAndMetadata> offsetMap = new HashMap<>();
                    offsetMap.put(new TopicPartition("audit", record.partition()), offsetMeta);

                    consumer.commitSync(offsetMap);
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }
}
