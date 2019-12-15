
import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import org.slf4j.LoggerFactory
import util.Helper

case class RouteHandler(system: ActorSystem) {
  val logger = LoggerFactory.getLogger(getClass)
  val routes = path("ping") {
    get {
      logger.debug("User requested ping!")
      complete(HttpResponse(OK, entity = "pong"))
    }
  } ~ path("hostname"){
    get{
      val hostname: String = java.net.InetAddress.getLocalHost.getHostName
      complete(HttpResponse(OK, entity = "You have been served by " + hostname ))
    }
  } ~ path("generateLogs"){
    get{
      logger.debug("User requested generate logs.")
      Helper.generateRandomLogs()
      complete(HttpResponse(OK, entity = "Random logs are generated!"))
    }
  } ~ path("countWords"){
    post{
      entity(as[String]) { book =>
        logger.info(s"Called countWords method for URL ${book}")
        val wordCount = Helper.countWords(book)
        val resultingEntity: String = wordCount.map(_.productIterator.mkString(":")).mkString("\n")
        complete(HttpResponse(OK, entity = resultingEntity))
      }
    }
  }
}