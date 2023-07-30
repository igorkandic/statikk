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
        var deaths = 0
        val summoner = (parsedData \ "activePlayer" \ "summonerName").get
        val summoners = (parsedData \ "allPlayers").get
        for(player <-summoners.as[List[json.JsObject]]){
          if((player \ "summonerName").get.as[String].equals(summoner.as[String])){
            deaths = (player \ "scores" \ "deaths").as[Int]
          }
        }
        println(deaths)
        if(deaths == lastDeaths + 1){
          println("You Died!!")
          zapper.zap()
        }
        lastDeaths = deaths
      })
    println("LeagueOfPain")

  }
}
