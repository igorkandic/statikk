package Serial
import com.fazecast.jSerialComm._

import java.util.Scanner
import scala.io.Source.stdin
class Zapper {
  private var serialport: SerialPort = null


  def init(): Unit = {
    val ports = SerialPort.getCommPorts
    for(port <- ports){
      if(port.getDescriptivePortName.contains("USB-SERIAL CH340")) {
        serialport = port
        serialport.setBaudRate( 9600)
        val opened = serialport.openPort()
        println(s"Port is opened: ${opened}")
      }
    }
  }
  def check(): Boolean = {

    serialport.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 1000)
    try{
      val writeBuffer = "LOPinit".getBytes()
      val numWrite = serialport.writeBytes(writeBuffer, writeBuffer.length)
      serialport.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2000, 0)
      val readBuffer = new Array[Byte](1024)
      val numRead = serialport.readBytes(readBuffer, readBuffer.length)
      if(numRead > 0)
        return true
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    false
  }
  def zap(): Unit = {
    serialport.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 1000)
    try{
      val writeBuffer = "shock".getBytes()
      val numWrite = serialport.writeBytes(writeBuffer, writeBuffer.length)
      println("zapped")
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}
