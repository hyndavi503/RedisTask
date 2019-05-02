package com.qvantel.poc

import java.util.{Collections, Properties}

import DemoProducer.KafkaScalaConsumer.consumer
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

class CsvConsumer {
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

  val topic = "csv-processor-2"

  val consumer: KafkaConsumer[Nothing, String] =
    new KafkaConsumer[Nothing, String](props)

  consumer.subscribe(Collections.singletonList(topic))

  val consumerThread = new Runnable {
    override def run(): Unit =
      while (true) {
        val records: ConsumerRecords[Nothing, String] = consumer.poll(100)
        for (record <- records.asScala.toList) {
          println("C: " + record)
          val csvData = CsvLineToCaseClassConverter.convert(record.value())
          val json = JsonUtils.convertCsvDataToJson(csvData)
          RedisClient.save(csvData.id.toString, json)
        }
        Thread.sleep(20000)
      }
  }

}
