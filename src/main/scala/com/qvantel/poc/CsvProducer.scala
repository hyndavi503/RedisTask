package com.qvantel.poc

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class CsvProducer {

  var properties = new Properties()

  properties.put("bootstrap.servers", "localhost:9092")
  properties.put("client.id", "ProducerClass")
  properties.put("acks", "all")
  properties.put("retries", "0")
  properties.put("batch.size", "16384")
  properties.put("linger.ms", "1")
  properties.put("buffer.memory", "33554432")
  properties.put("key.serializer",
                 "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer",
                 "org.apache.kafka.common.serialization.StringSerializer")

  val producer: KafkaProducer[Nothing, String] =
    new KafkaProducer[Nothing, String](properties)

  val topic = "csv-processor"
  println(s"Sending Records to the Topic [$topic]")
  // invoke FileUtils.readFile when required

  val producerThread = new Runnable {
    override def run(): Unit =
      while (true) {
        val lines = FileUtils.readFile(
          filename =
            "C:/Home/workspace/KafkaExample/src/main/resources/MOCK_DATA.csv"
        )
        for (i <- lines) {
          val record: ProducerRecord[Nothing, String] =
            new ProducerRecord(topic, i.toString)
          println(s"$record")
          producer.send(record)
        }
        Thread.sleep(20000)
      }
    producer.close()
  }
}
