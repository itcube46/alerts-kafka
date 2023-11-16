# alerts-kafka

## Пример проекта на основе Kafka

Учебный проект направления ["Основы программирования на языке Java"](https://vk.com/jkursk).

## Запуск Kafka

_В проекте используется Kafka 2.7.2_

### Запуск сервера ZooKeeper

```bash
cd ~/kafka_2.13-2.7.2
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Запуск брокеров Kafka

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-server-start.sh config/server0.properties
```

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-server-start.sh config/server1.properties
```

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-server-start.sh config/server2.properties
```

### Запуск всего

```bash
cd ~/kafka_2.13-2.7.2
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
sleep 20
bin/kafka-server-start.sh -daemon config/server0.properties
bin/kafka-server-start.sh -daemon config/server1.properties
bin/kafka-server-start.sh -daemon config/server2.properties
```

### Остановка всего

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-server-stop.sh
bin/zookeeper-server-stop.sh
```

### Процессы Java

```bash
jps
```
