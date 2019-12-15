package swagger

import javax.ws.rs.Path
import akka.http.scaladsl.server.Route
import io.swagger.annotations._


@Path("/ping")
@Api(value = "ping service")
@SwaggerDefinition(tags = Array(
  new Tag(name = "hello", description = "operations useful for debugging"),
  new Tag(name = "Log Generator", description = "Generate random logs"),
  new Tag(name = "Hostname", description = "Gets hostname"),
  new Tag(name = "Count Words", description = "Count words in the given website")
))
trait APIDefinition {
  @ApiOperation(value = "ping service (value in ApiOperation)", tags = Array("ping service"), httpMethod = "GET",
    notes = "This route will return a output pong")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK"),
    new ApiResponse(code = 500, message = "There was an internal server error.")
  ))
  def pingSwagger: Option[Route] = None
}

@Path("/generateLogs")
@Api(value = "Generate Log")
trait LogGeneratorTrait {
  @ApiOperation(value = "Inside generate log", tags = Array("Log Generator"), httpMethod = "GET",
    notes = "Generate random logs for ELK.")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK"),
    new ApiResponse(code = 400, message = "Bad Request"),
    new ApiResponse(code = 500, message = "Internal server error.")
  ))
  def GenerateLogsSwagger: Option[Route] = None
}

@Path("/hostname")
@Api(value = "Hostname")
trait HostnameTrait{
  @ApiOperation(value = "gets the host name", tags = Array("Hostname"), httpMethod = "GET",
    notes = "gets the host name of local machine.")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK"),
    new ApiResponse(code = 500, message = "There was an internal server error.")
  ))
  def HostnameSwagger: Option[Route] = None
}

@Path("/countWords")
@Api(value = "Count words")
trait CountWordsTrait{
  @ApiOperation(value = "Count words in the given book", tags = Array("Count Words"), httpMethod = "POST",
    notes = "Read the data from Book and counts the word frequency of it")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "book", required = true, dataType = "string", paramType = "body", value = "input string")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK", response = classOf[Map[String, Int]]),
    new ApiResponse(code = 500, message = "There was an internal server error.")
  ))
  def CountWordsMethod: Option[Route] = None
}