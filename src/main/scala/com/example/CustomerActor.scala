package com.example

import akka.actor.{Actor, ActorLogging, Props}

class CustomerActor extends Actor with ActorLogging {
  import CustomerActor._
  val doormanActor = context.actorOf(DoormanActor.props, "doormanActor")

  def receive = {
    case Call => {
      println("start")
      doormanActor ! CallDoorman("Please Open The Door!")
    }
    case DoormanActor.CalledResponse(res_msg) => {
      println("stop")
      context.system.shutdown()
    }
  }
}

object CustomerActor {
  val props = Props[CustomerActor]
  case object Call
  case class CallDoorman(msg: String)
}
