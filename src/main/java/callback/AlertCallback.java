package callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class AlertCallback implements Callback {
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            System.err.printf("error: %s\n", exception.getMessage());
        } else {
            System.out.printf("offset = %d, topic = %s, timestamp = %d\n",
                    metadata.offset(), metadata.topic(), metadata.timestamp());
        }
    }
}

