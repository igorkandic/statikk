package gui

import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, TextArea}
import scalafxml.core.macros.sfxml

@sfxml
class guiPresenter(private val startButton: Button,
                   private val testButton: Button,
                   private val arduinoButton: Button,
                   private val logText: TextArea){

  def startAction(event: ActionEvent): Unit = {
    logText.text= logText.getText+f"test\n"
  }

}
