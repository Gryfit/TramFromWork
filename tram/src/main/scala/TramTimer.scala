import dispatch._
import scala.util.{Success, Failure}
import scala.concurrent.Future
import play.api.libs.json._
import cats.implicits._
import cats.instances.list
import cats.instances.tuple._

import scala.concurrent.ExecutionContext.Implicits.global

object TramTimer extends App{

  override def main(args: Array[String]): Unit = {
    val svc = url("http://www.ttss.krakow.pl/internetservice/services/passageInfo/stopPassages/stop?stop=77")
    val response : Future[String] = Http.default(svc OK as.String)
    response onComplete {
      case Success(content) => {
        val resultOpt = for{
          mpkStopData <- Json.parse(content).validate[MpkStop].asOpt
          actual = mpkStopData.actual
          tupleList <- actual.traverse[Option,(Int,String)](tram => (tram.actualRelativeTime,tram.direction).bisequence)
          (result , _) = tupleList.filter{
            case (time,dir) => dir == "Czerwone Maki P+R" && time > 0
          }.head
        } yield result
        println(resultOpt.getOrElse("Something went wrong! :<"))
      }
      case Failure(t) => {
        println("An error has occurred: " + t.getMessage)
      }
    }
  }


}
