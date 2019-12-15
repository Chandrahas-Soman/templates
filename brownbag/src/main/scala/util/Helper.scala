package util

import org.slf4j.{Logger, LoggerFactory}
import scala.io.Source
import scala.util.Random

object Helper {
  val logger: Logger = LoggerFactory.getLogger(getClass)
  val r = new Random(42)

  val microServices = List("Server", "Simulator", "Trainer", "Injector", "Search", "config", "ML", "crawler")
  val applicationIds = List("SIMULATOR001","SIMULATOR002","SIMULATOR003","PRODUCTION001","SANDBOX001")
  val debugMessages = List(s"Restarting ${microServices(r.nextInt(8))}", "creating Inbound", "creating Outbound",
    "adding training phases", s"Loaded applicationId ${applicationIds(r.nextInt(5))}", "Adding suggestions",
    "fallback response received", "process new intent", "query DialogFlow")
  val infoMessages = List("Update Intent", "Assess Intent", s"Application ${applicationIds(r.nextInt(5))} started",
     s"Application ${applicationIds(r.nextInt(5))} stopped", "Delete Intent", "Create Intent", "sent direct response" +
      " to sender")
  val warnMessages = List("Incorrect signature", "Cannot access config path", "generate detection failure",
    "Version not up to date")
  val errorMessages = List("Create Outbound error", "Cannot open resource", "file exception", "DbObject load exception",
    "Update Intent exception", "Assess Intent exception", "Delete Intent exception", "Create Intent exception",
    "sentList exception", "Invalid shared secret", "Query Actor Handle inbound exception")


  def createLogStatement(): Unit = {
    // manually creating a distribution
    val p = r.nextInt(100)

    p match {
      case p if p < 5 => logger.trace(s"You are in ${microServices(r.nextInt(microServices.length))}") // 5% of logs will be trace messages
      case p if p < 45 => logger.debug(debugMessages(r.nextInt(debugMessages.length))) // 40% of logs will be debug messages
      case p if p < 75 => logger.info(infoMessages(r.nextInt(infoMessages.length))) // 30% of logs will be info messages
      case p if p < 85 => logger.warn(warnMessages(r.nextInt(warnMessages.length))) // 10% of logs will be warn messages
      case p if p < 100 => logger.error(errorMessages(r.nextInt(errorMessages.length))) // 15% of logs will be error messages
    }
  }

  def generateRandomLogs(): Unit = {
    val maxBurst = 100
    val maxTimeDifferenceBetweenBurst = 20
    var i = 0
    while (i < 25) {
      println(s"while iteration $i")
      for {
        _ <- 1 to r.nextInt(maxBurst)
      } yield {
        createLogStatement()
      }
      Thread.sleep(r.nextInt(maxTimeDifferenceBetweenBurst) * 1000) // sleep arg is in milliseconds
      i += 1
    }
  }

  def countWords(book: String): List[(String, Int)] = {
    Control.using(Source.fromFile("./Books/" + book))(source => {
        source.getLines.mkString.split(" ")
          .groupBy(identity).mapValues(_.size).toList
            .sortBy(_._2)(Ordering[Int].reverse).slice(0,10)
      }
    )
  }
}
