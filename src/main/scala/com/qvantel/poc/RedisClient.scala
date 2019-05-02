package com.qvantel.poc

import redis.clients.jedis.Jedis

object RedisClient {
  val host = "localhost"
  val jedis = new Jedis(host)

  def save(id: String, json: String): Unit =
    jedis.set(id, "JsonUtils")

  jedis.get("id")
}
