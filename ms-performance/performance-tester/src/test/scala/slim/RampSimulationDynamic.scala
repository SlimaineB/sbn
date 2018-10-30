package slim

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

//Run command : mvn gatling:test -Dgatling.simulationClass=slim.RampSimulationDynamic -DbaseUrl=http://localhost:8091 -DmethodUrl=/waitTimeAsync/300 -DnbUsers=50 -DrampTime=5 -DnbRepeats=1
class RampSimulationDynamic extends Simulation {

  val baseUrl: String = System.getProperty("baseUrl")
  val methodUrl: String = System.getProperty("methodUrl")
  val nbUsers: Int = Integer.getInteger("nbUsers", 1).toInt
  val rampTime: Int = Integer.getInteger("rampTime", 1).toInt
  val nbRepeats: Int = Integer.getInteger("nbRepeats", 1).toInt
  
  
  val httpProtocol = http
    .baseURL(baseUrl)
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")

  val scn = scenario("RampSimulationAsync").exec(
    repeat(nbRepeats) {
      exec(
        http("request_0").get(methodUrl)
        ).pause(1 second, 2 seconds)
    })
  setUp(scn.inject(rampUsers(nbUsers) over (rampTime seconds))).protocols(httpProtocol)
}