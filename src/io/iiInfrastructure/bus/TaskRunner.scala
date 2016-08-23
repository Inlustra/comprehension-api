package io.iiInfrastructure.bus

import scala.collection.mutable._
import scala.concurrent.{ExecutionContext, Future}


class TaskRunner extends Subscriber[TaskEvent, Publisher[TaskEvent]] {
  def notify(pub: Publisher[TaskEvent], event: TaskEvent): Unit = {
    event.task.start(event.context)
  }
}