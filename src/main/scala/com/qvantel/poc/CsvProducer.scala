package com.qvantel.poc

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}

class CsvProducer extends Runnable {

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

  val producer: KafkaProducer[String, String] =
    new KafkaProducer[String, String](properties)

  val topic = "csv-processor"
  println(s"Sending Records to the Topic [$topic]")

  override def run(): Unit = {
    val result = FileUtils.readFile(filename = "MOCK_DATA.csv")
    while (true) {
      for (i <- result) {
        val record: ProducerRecord[String, String] =
          new ProducerRecord(topic, i)
        producer.send(
          record,
          (metadata: RecordMetadata, exception: Exception) => {
            if (Option(exception).isDefined) {
              exception.printStackTrace()
            } else {
              println(s"Producer => Offset : ${metadata.offset()}, Record : $i")
            }
          }
        )
      }
      Thread.sleep(10000)
    }
  }

  def terminateProducer(): Unit = producer.close()
}
