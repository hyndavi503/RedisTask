package DemoProducer

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import play.api.libs.json.Json

import scala.io.Source

object ProducerClass extends App {

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
  val lines = Source
    .fromFile("C:/Home/workspace/KafkaExample/src/main/resources/MOCK_DATA.csv")
    .getLines
    .toList
  Thread.sleep(1000)
  /* val jsonStr = Source.fromResource("MOCK_DATA.csv").mkString
  val result = Json.parse("MOCK_DATA.csv")
  println(jsonStr)
  println(result)*/

  for (i <- lines) {
    val record: ProducerRecord[Nothing, String] =
      new ProducerRecord(topic, i.toString)
    println(s"$record")
    producer.send(record)
  }

  producer.close()

}
