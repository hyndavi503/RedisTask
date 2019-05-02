package com.qvantel.poc

import scala.util.Try

object CsvLineToCaseClassConverter {

  def convert(record: String): CsvData = {
    val arr = record.split(',')

    Try {
      CsvData(arr(0).toInt, arr(1), arr(2), arr(3), arr(4), arr(5), arr(6))
    } recover {
      case e: Exception =>
        println(record)
        throw e
    } get
  }
}
