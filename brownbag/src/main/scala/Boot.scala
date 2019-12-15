// import java.io.File

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

// from PRK
//import org.apache.log4j.Logger

import org.slf4j.LoggerFactory

// this is for log4j 1.x
//import org.apache.log4j.Logger

// current version is log4j 2.x
// import org.apache.logging.log4j.Logger

// import org.apache.logging.log4j.LogManager
// import org.apache.logging.log4j.core.LoggerContext

import sun.misc.{Signal, SignalHandler}
import swagger.Swagger

import scala.concurrent.{Await, Promise}
import scala.concurrent.duration.Duration

object Boot{

  val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val APPLICATION_NAME = "brownbag"

    // needed to run the route
    implicit val system = ActorSystem("my-actor-system")
    implicit val materializer = ActorMaterializer()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val routes = RouteHandler(system).routes ~ Swagger(system).routes ~
       getFromResourceDirectory("swagger-ui")

    // our server server
    val bindingFuture = Http().bindAndHandle(routes, "0.0.0.0", 9001)

    /* graceful shutdown function to be called from signal handler and shutdown hook */
    def properShutdown = {
      println(s"Application ${APPLICATION_NAME} properShutdown")
      logger.info(s"Application ${APPLICATION_NAME} properShutdown")
      // if (ss.nonEmpty) ss.get.stop()
      /* trigger unbinding from the port and shutdown the actor system when done */
      bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
      println(s"Application ${APPLICATION_NAME} has Stopped")
      logger.info(s"Application ${APPLICATION_NAME} has Stopped")
    } // properShutdown

    /* add cleanup hook */
    /* If you kill the process with kill -15 (SIGTERM) it will be caught in shutdown hook */
    /* kill -9 (SIGKILL) will not be caught */
    sys.addShutdownHook {
      properShutdown
    } // addShutdownHook

    /* catch SIGUSR2 for graceful shutdown */
    Signal.handle(new Signal("USR2"), new SignalHandler() {
      def handle(sig: Signal) {
        println(s"Received signal ${sig} to shutdown application ${APPLICATION_NAME}")
        logger.info(s"Received signal ${sig} to shutdown application ${APPLICATION_NAME}")
        properShutdown
      } // handle
    }) // Signal

    /* wait for a future that will never complete */
    val waitOnFuture = bindingFuture.map { f => Promise[Done].future }
    Await.ready(waitOnFuture, Duration.Inf)
    println(s"Application ${APPLICATION_NAME} is listening on port 9001")
    logger.info(s"Application ${APPLICATION_NAME} is listening on port 9001")

    /*
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => {println("shutting down!");system.terminate()}) // and shutdown when done
     */
  }

}