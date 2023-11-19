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
sleep 10
bin/zookeeper-server-stop.sh
```

## Создание топиков

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic alert --partitions 3 --replication-factor 3
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic alert_trend --partitions 3 --replication-factor 3
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic audit --partitions 3 --replication-factor 3
```

## Запуск Connect

```bash
cd ~/kafka_2.13-2.7.2
bin/connect-standalone.sh config/connect-standalone.properties config/alerts-source.properties config/alerts-sink.properties
```

## Запуск Console Consumer

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-console-consumer.sh --bootstrap-server localhost:9094 --topic alerts_connect --from-beginning
```

## Полезные команды

Список топиков:

```bash
cd ~/kafka_2.13-2.7.2
bin/zookeeper-shell.sh localhost:2181
ls /brokers/topics
```

Другой способ:

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-topics.sh --list --bootstrap-server localhost:9094
```

Вывод информации о контроллере:

```bash
cd ~/kafka_2.13-2.7.2
bin/zookeeper-shell.sh localhost:2181
get /controller
```

Процессы Java:

```bash
jps
```
