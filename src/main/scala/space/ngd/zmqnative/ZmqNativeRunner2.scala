package space.ngd.zmqnative

import org.zeromq._
import akka.util.ByteString
import javafx.scene.SubScene

object ZmqNativeRunner2 extends App {
  val topic = "ngd.blueshift"
  val bindAddress = "tcp://127.0.0.1:21231"
  val connectAddress = bindAddress
  val context = ZMQ.context(1)
  val pubSocket = context.socket(ZMQ.PUB)
  val subSocket = context.socket(ZMQ.SUB)
  
  pubSocket.bind(bindAddress)
  
  val subThread = new Thread {
    override def run {
      println("Starting the Sub thread.")
      subSocket.connect(connectAddress)
      subSocket.subscribe(topic.getBytes())
      
      while(!this.isInterrupted()) {
        val envelope = subSocket.recvStr()
        val contents = subSocket.recvStr()
        println("Notification: " + contents)
      }
    }
  }
  
  subThread.start()
  Thread.sleep(100)
  
  for(x <- 1 to 10) {
    println("Sending Message")
    pubSocket.sendMore(topic)
    pubSocket.send("Hey, Brosephonie")
  }
  
  subThread.interrupt()
  pubSocket.close()
  subSocket.close()
  context.term()
}