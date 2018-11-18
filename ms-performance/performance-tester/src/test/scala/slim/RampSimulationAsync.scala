package slim

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

//Run command : mvn gatling:test -Dgatling.simulationClass=slim.RampSimulationAsync
class RampSimulationAsync extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8091")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")


	val scn = scenario("RampSimulationAsync")
		.exec(http("request_0")
			.get("/waitTimeAsync/100"))

	setUp(scn.inject(constantUsersPerSec(1000) during (120 seconds))).protocols(httpProtocol)
}