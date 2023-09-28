
import Serial.Zapper
import LeagueConnect.InGameClient
import gui.guiPresenter
import play.api.libs.json
import play.api.libs.json.Json
import scalafx.Includes._
import scalafx.application.ConditionalFeature.FXML
import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextArea}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.event.{ActionEvent, EventHandler}
import scalafx.stage.WindowEvent
import scalafxml.core.{DependenciesByType, FXMLView, NoDependencyResolver}

import java.io.IOException
import scala.reflect.runtime.universe.typeOf
object Main extends JFXApp3 {

  val zapper = new Zapper()
  val LeagueClient = new InGameClient()
  var lastDeaths = 0
  override def start(): Unit = {
    val resource = getClass.getResource("gui.fxml")
    if(resource == null){
      throw new IOException("Cannot load: gui.fxml")
    }
    var controller = new guiPresenter(null)
    val root = FXMLView(resource, new DependenciesByType(Map(
      typeOf[gui.guiPresenter] -> controller
    )))

    stage = new JFXApp3.PrimaryStage() {
      title = "statikk"
      scene = new Scene(root)
    }
//    stage = new JFXApp3.PrimaryStage {
//      title = "Statikk"
//      width = 600
//      height = 500
//      scene = new Scene {
//          content = new VBox {
//            padding = Insets(50)
//            children = Seq(
//              new HBox{
//                children = Seq(
//                  new VBox {
//                    padding = Insets(50)
//                    children = Seq(
//                      new Text {
//                        text = "Start/Stop"
//                      },
//                      new Button {
//                        text = "Start"
//                        onAction = (ae: ActionEvent) => {
//                          zapper.init()
//                          try {
//                            println(f"Arduino is ready: ${zapper.check()}")
//                          } catch {
//                            case e: Exception =>
//                              e.printStackTrace()
//                              println(f"Arduino is not plugged in")
//                              System.exit(-1)
//                          }
//                          LeagueClient.InGameClient()
//                          var lastDeaths = 0
//                          LeagueClient.Subscribe("/liveclientdata/allgamedata", 2000, (data) => {
//                            val parsedData = Json.parse(data)
//                            var deaths = 0
//                            val summoner = (parsedData \ "activePlayer" \ "summonerName").get
//                            val summoners = (parsedData \ "allPlayers").get
//                            for (player <- summoners.as[List[json.JsObject]]) {
//                              if ((player \ "summonerName").get.as[String].equals(summoner.as[String])) {
//                                deaths = (player \ "scores" \ "deaths").as[Int]
//                              }
//                            }
//                            println(deaths)
//                            if (deaths == lastDeaths + 1) {
//                              println("You Died!!")
//                              zapper.zap()
//                            }
//                            lastDeaths = deaths
//                          })
//                          println("LeagueOfPain")
//                        }
//
//                      }
//                    )
//                  },
//                  new VBox {
//                    padding = Insets(50)
//                    children = Seq(
//                      new Text {
//                        text = "Test shocker"
//                      },
//                      new Button {
//                        text = "Test"
//
//                      }
//                    )
//                  },
//                  new VBox {
//                    padding = Insets(50)
//                    children = Seq(
//                      new Text {
//                        text = "Check arduino"
//                      },
//                      new Button {
//                        text = "Check"
//
//                      }
//                    )
//                  }
//                )
//              },
//              new VBox{
//                children = Seq(
//                  new Label{
//                    text = "Log:"
//                  },
//                  new TextArea{
//                    text = ""
//                    editable = false
//                  },
//                )
//              }
//            )
//          }
//          onCloseRequest = (we : WindowEvent) => {
//            System.exit(0)
//          }
//      }
//    }
  }


}
