package consumer;

import model.Alert;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import serde.AlertKeySerde;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AlertConsumer {
    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kafkaProperties = getKafkaProperties();
        AlertConsumer consumer = new AlertConsumer();
        consumer.consume(kafkaProperties);

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("enable.auto.commit", "false");
        kafkaProperties.put("group.id", "alert_group");
        kafkaProperties.put("key.deserializer", AlertKeySerde.class.getName());
        kafkaProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        return kafkaProperties;
    }

    private void consume(final Properties kafkaProperties) {
        KafkaConsumer<Alert, String> consumer = new KafkaConsumer<>(kafkaProperties);
        TopicPartition partitionZero = new TopicPartition("alert", 0);
        consumer.assign(List.of(partitionZero));

        while (keepConsuming) {
            ConsumerRecords<Alert, String> records = consumer.poll(Duration.ofMillis(250));
            for (ConsumerRecord<Alert, String> record : records) {
                System.out.printf("offset = %d, key = %s\n", record.offset(), record.key().getStageId());
                commitOffset(record.offset(), record.partition(), "alert", consumer);
            }
        }
    }

    public static void commitOffset(long offset, int part, String topic, KafkaConsumer<Alert, String> consumer) {
        OffsetAndMetadata offsetMeta = new OffsetAndMetadata(++offset, "");

        Map<TopicPartition, OffsetAndMetadata> offsetMap = new HashMap<>();
        offsetMap.put(new TopicPartition(topic, part), offsetMeta);

        consumer.commitAsync(offsetMap, AlertConsumer::onComplete);
    }

    private static void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
        if (e != null) {
            for (TopicPartition key : map.keySet()) {
                System.out.printf("error topic %s, partition %d, offset %d\n",
                        key.topic(),
                        key.partition(),
                        map.get(key).offset());
            }
        } else {
            for (TopicPartition key : map.keySet()) {
                System.out.printf("info topic %s, partition %d, offset %d\n",
                        key.topic(),
                        key.partition(),
                        map.get(key).offset());
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }
}
