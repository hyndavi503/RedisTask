package com.qvantel.poc

import scala.io.Source

object FileUtils {

  def readFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList.tail
    source.close()
    lines
  }

}
