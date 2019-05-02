package com.qvantel.poc

import redis.clients.jedis.Jedis

object RedisClient {
  val host = "localhost"
  val jedis = new Jedis(host)
  jedis.select(1)
  def save(id: String, json: String): Unit = {
    println(s"Key: $id, Value: $json")

    jedis.set(id, "JsonUtils")
  }
}
