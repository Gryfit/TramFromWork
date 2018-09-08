import play.api.libs.json.{JsValue, __}
import play.api.libs.functional.syntax._

case class MpkStop (
  actual: List[Tram],
  directions: List[String],
  firstPassageTime: Long,
  generalAlerts: List[String],
  lastPassageTime: Long,
  old: List[JsValue],
  routes: List[JsValue],
  stopName: String,
  stopShortName: String
                   )

object MpkStop {
  implicit def mpkStopReads = (
    (__ \   "actual").read[List[Tram]] and
      (__ \ "directions").read[List[String]] and
      (__ \ "firstPassageTime").read[Long] and
      (__ \ "generalAlerts").read[List[String]] and
      (__ \ "lastPassageTime").read[Long] and
      (__ \ "old").read[List[JsValue]] and
      (__ \ "routes").read[List[JsValue]] and
      (__ \ "stopName").read[String] and
      (__ \ "stopShortName").read[String]
    )(MpkStop.apply _)
}
