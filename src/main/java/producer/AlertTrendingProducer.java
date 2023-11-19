package producer;

import model.Alert;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import serde.AlertKeySerde;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AlertTrendingProducer {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties kafkaProperties = getKafkaProperties();
        try (Producer<Alert, String> producer = new KafkaProducer<>(kafkaProperties)) {
            Alert alert =
                    new Alert(0, "Stage 0", "CRITICAL", "Stage 0 stopped");
            ProducerRecord<Alert, String> producerRecord =
                    new ProducerRecord<>("alert_trend", alert, alert.getAlertMessage());

            RecordMetadata result = producer.send(producerRecord).get();
            System.out.printf("offset = %d, topic = %s, timestamp = %d\n",
                    result.offset(), result.topic(), result.timestamp());
        }
    }

    private static Properties getKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kafkaProperties.put("key.serializer", AlertKeySerde.class.getName());
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kafkaProperties;
    }
}
