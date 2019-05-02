package Demo

import scala.io.Source

object FileReading extends App {

  for (line <- Source
         .fromFile(
           "C:/Home/workspace/KafkaExample/src/main/resources/MOCK_DATA.csv"
         )
         .getLines()) {
    println(line.trim.toUpperCase)
  }

}
