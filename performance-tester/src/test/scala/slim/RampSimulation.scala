package slim

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RampSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8762")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")


	val scn = scenario("RampSimulation")
		.exec(http("request_0")
			.get("/first-business-service/getProductByCategoryNoDb/Legume"))

	setUp(scn.inject(rampUsers(1500) over(50 seconds))).protocols(httpProtocol)
}