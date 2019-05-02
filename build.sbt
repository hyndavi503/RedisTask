name := "KafkaExample"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies += "org.apache.kafka" %% "kafka" % "2.2.0"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.3"

// https://mvnrepository.com/artifact/redis.clients/jedis
libraryDependencies += "redis.clients" % "jedis" % "3.0.0"
