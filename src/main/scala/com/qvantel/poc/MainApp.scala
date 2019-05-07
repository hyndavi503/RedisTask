package com.qvantel.poc

object MainApp {

  def main(args: Array[String]): Unit = {

    val consumer = new CsvConsumer
    val consumerThread = new Thread(consumer)

    val producer = new CsvProducer
    val producerThread = new Thread(producer)

    consumerThread.start()
    producerThread.start()

    while (true) {}

    sys.addShutdownHook {
      producer.terminateProducer()
      Thread.sleep(100)
      consumer.terminateConsumer()
      Thread.sleep(100)
      producerThread.interrupt()
      consumerThread.interrupt()
    }
  }
}
