package serde;

import model.Alert;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AlertKeySerde implements Serializer<Alert>, Deserializer<Alert> {
    public byte[] serialize(String topic, Alert key) {
        if (key == null) {
            return null;
        }
        return key.getStageId().getBytes(StandardCharsets.UTF_8);
    }

    public Alert deserialize(String topic, byte[] value) {
        // Заглушка десериализации
        return new Alert(0, "Stage 0", "CRITICAL", "Stage 0 stopped");
    }

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
