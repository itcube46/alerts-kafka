# alerts-kafka

## Пример проекта на основе Kafka

Учебный проект направления ["Основы программирования на языке Java"](https://vk.com/jkursk).

### Литература

1. [Беджек Билл. Kafka Streams в действии, 2019.](https://github.com/bbejeck/kafka-streams-in-action)
2. [Гвен Шапира, Тодд Палино, Раджини Сиварам, Крит Петти. Apache Kafka. Потоковая обработка и анализ данных, 2023.](https://github.com/gwenshap/kafka-examples)
3. [Дилан Скотт, Виктор Гамов, Дейв Клейн. Kafka в действии, 2022.](https://github.com/Kafka-In-Action-Book)
4. [Сеймур Митч. Kafka Streams и ksqlDB: данные в реальном времени, 2023.](https://github.com/mitch-seymour/mastering-kafka-streams-and-ksqldb)
5. [Фишер Джош, Ван Нин. Грокаем стриминг, 2023.](https://github.com/nwangtw/GrokkingStreamingSystems)

## Запуск Kafka

_В проекте используется Kafka версии 2.7.2._

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

## Создание топиков

```bash
cd ~/kafka_2.13-2.7.2
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic alert --partitions 3 --replication-factor 3
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic alert_trend --partitions 3 --replication-factor 3
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic audit --partitions 3 --replication-factor 3
```

## Connect

### Запуск Connect

```bash
cd ~/kafka_2.13-2.7.2
bin/connect-standalone.sh config/connect-standalone.properties config/alerts-source.properties config/alerts-sink.properties
```

### Запуск Console Consumer

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

Список топиков (второй способ):

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
