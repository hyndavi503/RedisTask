package com.qvantel.poc

import scala.io.Source

object FileUtils {

  def readFile(filename: String): List[String] = {
    val line = io.Source.fromFile(
      "C:/Home/workspace/KafkaExample/src/main/resources/MOCK_DATA.csv"
    )
    val lines = (for (line <- line.getLines()) yield line.toUpperCase).toList
    line.close
    lines
  }
}
