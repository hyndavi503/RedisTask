package com.qvantel.poc

object MainApp {

  def main(args: Array[String]): Unit = {

    val csvProducer = new CsvProducer().producerThread.run()
    val csvConsumer = new CsvConsumer().consumerThread.run()

  }
}
