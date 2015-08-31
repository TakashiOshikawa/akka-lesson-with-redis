package com.example

import akka.actor.{Actor, ActorLogging, Props}

class DoormanActor extends Actor with ActorLogging {
  import DoormanActor._

  def receive = {
    case CustomerActor.CallDoorman(msg) => sender() ! CalledResponse("OK!")
  }
}

object DoormanActor {
  val props = Props[DoormanActor]
  case class CalledResponse(msg: String)
}
