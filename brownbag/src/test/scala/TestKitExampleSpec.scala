import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._


class ExampleTest extends WordSpec with Matchers with ScalatestRouteTest {
  private val testExample = RouteHandler(system)

  "RouteHandler" should {
    "get pong back" in {
      Get("/ping") ~> testExample.routes ~> check {
        responseAs[String] should include("pong")
      }
    }
  }
}