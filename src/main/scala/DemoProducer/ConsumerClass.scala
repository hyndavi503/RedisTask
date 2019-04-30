package DemoProducer

import play.api.libs.json.Json
import java.util.{Collections, Properties}

import DemoProducer.ConvertingData.sample

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

object KafkaScalaConsumer extends App {

  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer")

  props.put("value.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer")

  props.put("group.id", "consumer-group-1")

  props.put("enable.auto.commit", "true")

  props.put("auto.commit.interval.ms", "1000")

  props.put("auto.offset.reset", "earliest")

  props.put("session.timeout.ms", "30000")

  val topic = "csv-processor"

  val consumer: KafkaConsumer[Nothing, String] =
    new KafkaConsumer[Nothing, String](props)

  consumer.subscribe(Collections.singletonList(topic))

  while (true) {

    val records: ConsumerRecords[Nothing, String] = consumer.poll(100)
    for (record <- records.asScala) {
      println(record)
      val arr = record.value().split(",")

      val convert =
        Convert(arr(0).toInt, arr(1), arr(2), arr(3), arr(4), arr(5), arr(6))
      convert.id

    }
  }

}
