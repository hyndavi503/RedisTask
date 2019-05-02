package com.qvantel.poc

import play.api.libs.json.Json

object JsonUtils {

  def convertCsvDataToJson(csvData: CsvData): String = {
    implicit val formats = Json.format[CsvData]
    val result = Json.stringify(Json.toJson[CsvData](csvData))
    result
  }
}
