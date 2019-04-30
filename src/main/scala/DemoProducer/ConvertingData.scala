package DemoProducer

import play.api.libs.json.Json

case class Convert(
    id: Int,
    first_name: String,
    last_name: String,
    email: String,
    gender: String,
    ip_address: String,
    country: String)

object ConvertingData extends App {

  implicit val formats = Json.format[Convert]

  val sample = Convert(
    1,
    "Samara",
    "Yates",
    "syates0@unblog.fr",
    "Female",
    "201.168.104.0",
    "Bosnia and Herzegovina"
  )
  println(Json.stringify(Json.toJson[Convert](sample)))

}
