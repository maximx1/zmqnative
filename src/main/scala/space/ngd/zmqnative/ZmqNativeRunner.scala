package space.ngd.zmqnative

import zeromq._
import akka.util.ByteString

object ZmqNativeRunner extends App {
  val bindAddress = "tcp://127.0.0.1:21231"
  val connectAddress = bindAddress
  val pushSocket = ZeroMQ.socket(SocketType.Push)
  val pullSocket = ZeroMQ.socket(SocketType.Pull)
  
  pushSocket.bind(bindAddress)
  pullSocket.connect(connectAddress)
  
  pullSocket.recvAll { message: Message => 
    println("recieved: " + message.map(_.utf8String).mkString(" "))
  }
  
  for(x <- 1 to 10) {
    pushSocket.send(Message(ByteString("Hey, Brosephonie")))
  }
}