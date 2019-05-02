package com.qvantel.poc

object MainApp {

  def main(args: Array[String]): Unit = {

    new CsvConsumer().consumerThread.run()
    new CsvProducer().producerThread.run()

  }
}
