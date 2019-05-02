package com.qvantel.poc

object CsvLineToCaseClassConverter {

  def convert(record: String): CsvData = {
    val arr = record.split("")

    CsvData(arr(0).toInt, arr(1), arr(2), arr(3), arr(4), arr(5), arr(6))
  }
}
