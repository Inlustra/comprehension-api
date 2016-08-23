package io.iiInfrastructure.bus

import scala.collection.mutable

class BlockingTaskEventQueue extends mutable.Publisher[TaskEvent] {
  def push(taskEvent: TaskEvent) = publish(taskEvent)
}