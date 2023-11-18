package producer;

import avro.alert.Alert;
import avro.alert.AlertStatus;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Instant;
import java.util.Properties;

public class AlertProducer {
    public static void main(String[] args) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        kafkaProperties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        kafkaProperties.put("schema.registry.url", "http://localhost:8081");
        // Запуск schema registry: confluent local services connect start

        try (Producer<Long, Alert> producer = new KafkaProducer<>(kafkaProperties)) {
            Alert alert = new Alert(12345L, Instant.now().toEpochMilli(), AlertStatus.Critical);
            System.out.println(alert);
            ProducerRecord<Long, Alert> producerRecord =
                    new ProducerRecord<>("alert_test", alert.getSensorId(), alert);
            producer.send(producerRecord);
        }
    }
}
