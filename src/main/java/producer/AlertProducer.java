package producer;

import callback.AlertCallback;
import model.Alert;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import partitioner.AlertLevelPartitioner;
import serde.AlertKeySerde;

import java.util.Properties;

public class AlertProducer {
    public static void main(String[] args) {
        Properties kafkaProperties = getKafkaProperties();
        try (Producer<Alert, String> producer = new KafkaProducer<>(kafkaProperties)) {
            Alert alert =
                    new Alert(0, "Stage 0", "CRITICAL", "Stage 0 stopped");
            ProducerRecord<Alert, String> producerRecord =
                    new ProducerRecord<>("alert", alert, alert.getAlertMessage());
            producer.send(producerRecord, new AlertCallback());
        }
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kafkaProperties.put("key.serializer", AlertKeySerde.class.getName());
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("partitioner.class", AlertLevelPartitioner.class.getName());
        return kafkaProperties;
        //kafkaProperties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        //kafkaProperties.put("schema.registry.url", "http://localhost:8081");
        // Запуск schema registry: confluent local services connect start
    }
}
