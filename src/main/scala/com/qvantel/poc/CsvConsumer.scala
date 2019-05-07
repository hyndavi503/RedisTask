package com.qvantel.poc

import java.util.{Collections, Properties}

import DemoProducer.KafkaScalaConsumer.consumer
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

class CsvConsumer extends Runnable {

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

  val consumer: KafkaConsumer[String, String] =
    new KafkaConsumer[String, String](props)

  consumer.subscribe(Collections.singletonList(topic))
  override def run(): Unit =
    while (true) {
      val records: ConsumerRecords[String, String] = consumer.poll(100)
      for (record <- records.asScala) {
        println(s"Consumer => ${record.value()}")
        val csvData = CsvLineToCaseClassConverter.convert(record.value())
        val json = JsonUtils.convertCsvDataToJson(csvData)
        RedisClient.save(csvData.id.toString, json)
      }
    }

  def terminateConsumer() = consumer.close()
}
