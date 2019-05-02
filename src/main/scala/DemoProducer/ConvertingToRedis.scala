/*
package DemoProducer

import org.apache.zookeeper.server.quorum.QuorumCnxManager.Message
import org.slf4j.LoggerFactory

object ConveringToRedis {

  private val logger = LoggerFactory.getLogger(this.getClass)
  private val host = "localhost"
  private val pool = new jedispool(new jedispoolconfig(), pool)
  private val subscribers = Map[String, (Thread, jedis, JedisPubSub)]

  def publish(message: Message, channel: Channel): Unit =
    withClient { jedis =>
      jedis.publish(channel.name, message.data)
    }

  def subscribe(channel: Channel, messageReceiver: MessageReceiver): String = {
    val jedis = new Jedis(host)
    val pubsub = createPubSub(messageReceiver)
    val thread = new Thread(() => {
      logger.debug(s"start subscribe to ${channel.name}")
      jedis.subscribe(pubsub, channel.name)
    })
    subscribers.put(thread.getName(), (thread, jedis, pubsub))
    thread.start()
    thread.getName()
  }

  private def withClient[T](f: Jedis => T): T = {
    val jedis = new Jedis(host)
    try {
      f(jedis)
    } finally {
      jedis.quit()
    }
  }

}
 */
