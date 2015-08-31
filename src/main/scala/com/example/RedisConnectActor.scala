package com.example

import akka.actor.{Actor, Props}

class RedisConnectActor extends Actor {
  import RedisConnectActor._

  def receive = {
    case AuthenticActor.RequestValue(key) => {
      val value = Redis.get(key)
      println(value)
      sender() ! GetValueResponse(value)
    }
    case AuthenticActor.RequestSetValue(key: String, value: String) => {
      val isSet = Redis.set(key, value)
      println(isSet)
      val setValue = Redis.get(key)
      sender() ! SetValueResponse(setValue)
    }
  }

}


object RedisConnectActor {
  val props = Props[RedisConnectActor]
  case class GetValueResponse(value: String)
  case class SetValueResponse(value: String)
}


object Redis {
  import com.redis._

  val red = new RedisClient("127.0.0.1", 6379)
  def set(key: String, value: String): Boolean = red.set(key,value)
  def get(key: String): String = red.get(key).getOrElse("")
}
