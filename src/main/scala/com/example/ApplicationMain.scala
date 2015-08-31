package com.example

import akka.actor.ActorSystem

object ApplicationMain extends App {
  val system = ActorSystem("MyActorSystem")
  val authenticActor = system.actorOf(AuthenticActor.props, "authenticActor")
  authenticActor ! AuthenticActor.Auth
  system.awaitTermination()
}
