package com.qvantel.poc

import scala.io.Source

object FileUtils {

  def readFile(filename: String): List[String] = {
    val gettingFile = io.Source.fromFile(
      "C:/Home/workspace/KafkaExample/src/main/resources/MOCK_DATA.csv"
    )
    val lines = gettingFile.getLines().toList.tail
    gettingFile.close()
    lines
  }

}
