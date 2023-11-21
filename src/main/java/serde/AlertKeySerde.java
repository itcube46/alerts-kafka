package serde;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Alert;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AlertKeySerde implements Serde<Alert>, Serializer<Alert>, Deserializer<Alert> {
    public byte[] serialize(String topic, Alert key) {
        if (key == null) {
            return null;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(key);
        return json.getBytes(StandardCharsets.UTF_8);
    }

    public Alert deserialize(String topic, byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String json = new String(byteArray, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        return gson.fromJson(json, Alert.class);
    }

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

    @Override
    public Serializer<Alert> serializer() {
        return this;
    }

    @Override
    public Deserializer<Alert> deserializer() {
        return this;
    }
}
