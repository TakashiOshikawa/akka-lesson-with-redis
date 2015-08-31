package com.example

import akka.actor.{Actor, Props}

class AuthenticActor extends Actor {
  import AuthenticActor._
  val redisConnectActor = context.actorOf(RedisConnectActor.props, "redisConnectActor")

  def receive = {
    case Auth => {
      println("start")
      redisConnectActor ! RequestValue("okamoto")
    }
    case RedisConnectActor.GetValueResponse(value) => {
      println("Response : " + value)
      sender() ! RequestSetValue("akka", "Akka使ってます！")
    }
    case RedisConnectActor.SetValueResponse(value: String) => {
      println("Set Response : " + value)
      println("stop")
      context.system.shutdown()
    }
  }

}

object AuthenticActor {
  val props = Props[AuthenticActor]
  case object Auth
  case class RequestValue(key: String)
  case class RequestSetValue(ket: String, value: String)
}
