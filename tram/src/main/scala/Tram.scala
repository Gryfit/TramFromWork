import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class Tram (
                  actualRelativeTime: Option[Int]    = None,
                  actualTime:         Option[String]    = None,
                  direction:          Option[String]    = None,
                  mixedTime:          Option[String]    = None,
                  passageid:          Option[String]    = None,
                  patternText:        Option[String]    = None,
                  plannedTime:        Option[String]    = None,
                  routeId:            Option[String]    = None,
                  status:             Option[String]    = None,
                  tripId:             Option[String]    = None,
                  vehicleId:          Option[String]    = None
                )

object Tram {
  implicit def tramReads = (
    (__ \ "actualRelativeTime").readNullable[Int] and
      (__ \ "actualTime").readNullable[String] and
      (__ \ "direction").readNullable[String] and
      (__ \ "mixedTime").readNullable[String] and
      (__ \ "passageid").readNullable[String] and
      (__ \ "patternText").readNullable[String] and
      (__ \ "plannedTime").readNullable[String] and
      (__ \ "routeId").readNullable[String] and
      (__ \ "status").readNullable[String] and
      (__ \ "tripId").readNullable[String] and
      (__ \ "vehicleId").readNullable[String]
    )(Tram.apply _)
}


