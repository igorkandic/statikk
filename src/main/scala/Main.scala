import Serial.Zapper
import LeagueConnect.InGameClient
import play.api.libs.json.Json
object Main {
  def main(args: Array[String]): Unit = {
      val zapper = new Zapper()
      zapper.init()
      println(f"Arduino is ready: ${zapper.check()}")

      val LeagueClient = new InGameClient()
      LeagueClient.InGameClient()
      var lastDeaths = 0
      LeagueClient.Subscribe("/liveclientdata/allgamedata", 2000, (data) => {
        val parsedData = Json.parse(data)
        val deaths = (parsedData \ "allPlayers" \ 0 \ "scores" \ "deaths").get
        println(deaths)
        if(deaths.as[Int] == lastDeaths + 1){
          lastDeaths = deaths.as[Int]
          println("You Died!!")
          zapper.zap()
        }else{
          lastDeaths = deaths.as[Int]
        }
      })
    println("LeagueOfPain")

  }
}
