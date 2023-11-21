import model.Alert;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.integration.utils.EmbeddedKafkaCluster;
import org.apache.kafka.test.TestUtils;
import org.junit.ClassRule;
import org.junit.Test;
import partitioner.AlertLevelPartitioner;
import serde.AlertKeySerde;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmbeddedKafkaClusterTest {
    private static final String TOPIC = "alert";
    private static final int PARTITION_NUMBER = 3;
    private static final int REPLICATION_NUMBER = 3;
    private static final int BROKER_NUMBER = 3;

    @ClassRule
    public static EmbeddedKafkaCluster embeddedKafkaCluster = new EmbeddedKafkaCluster(BROKER_NUMBER);

    @Test
    public void testAlertPartitioner() throws InterruptedException {
        embeddedKafkaCluster.createTopic(TOPIC, PARTITION_NUMBER, REPLICATION_NUMBER);
        Properties kafkaProducerProperties = TestUtils.producerConfig(
                embeddedKafkaCluster.bootstrapServers(),
                AlertKeySerde.class,
                StringSerializer.class);
        Properties kafkaConsumerProperties = TestUtils.consumerConfig(
                embeddedKafkaCluster.bootstrapServers(),
                AlertKeySerde.class,
                StringDeserializer.class);

        AlertProducer alertProducer = new AlertProducer();
        try {
            alertProducer.sendMessage(kafkaProducerProperties);
        } catch (Exception exception) {
            fail("error: " + exception.getMessage());
        }

        AlertConsumer alertConsumer = new AlertConsumer();
        ConsumerRecords<Alert, String> records = alertConsumer.getAlertMessages(kafkaConsumerProperties);
        TopicPartition partition = new TopicPartition(TOPIC, 0);
        List<ConsumerRecord<Alert, String>> results = records.records(partition);
        assertEquals(0, results.get(0).partition());
    }

    static class AlertProducer {
        public void sendMessage(Properties properties) throws InterruptedException, ExecutionException {
            properties.put("partitioner.class", AlertLevelPartitioner.class.getName());
            try (Producer<Alert, String> producer = new KafkaProducer<>(properties)) {
                Alert alert =
                        new Alert(1, "Stage 1", "CRITICAL", "Stage 1 stopped");
                ProducerRecord<Alert, String> producerRecord =
                        new ProducerRecord<>(TOPIC, alert, alert.getAlertMessage());
                producer.send(producerRecord).get();
            }
        }
    }

    static class AlertConsumer {
        public ConsumerRecords<Alert, String> getAlertMessages(Properties properties) {
            KafkaConsumer<Alert, String> consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(List.of(TOPIC));
            return consumer.poll(Duration.ofMillis(2500));
        }
    }
}
