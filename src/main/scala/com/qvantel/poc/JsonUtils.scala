package com.qvantel.poc

import play.api.libs.json.Json

object JsonUtils {

  def convertCsvDataToJson(csvData: CsvData): String = {
    implicit val formats = Json.format[CsvData]

    val sample = CsvData(
      1,
      "Samara",
      "Yates",
      "syates0@unblog.fr",
      "Female",
      "201.168.104.0",
      "Bosnia and Herzegovina"
    )
    val result = Json.stringify(Json.toJson[CsvData](sample))
    result
  }
}
