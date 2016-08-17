package io.iiInfrastructure

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by jackliddiard on 15/08/16.
  */
trait TaskBuilder[C] {
  def append[O](task: Task[C, O]): TaskBuilder[O]

  def build(): PipeLine
}

trait PipeLine {
  def start()
}

private case class TaskWrapper[C, O](task: Task[C, O], context: C) {
  val result = task.start(context)
}

private case class AsyncPipeLine[C, O](future: Future[TaskWrapper[C, O]]) extends PipeLine {
  def start(): Unit = true
}

object AsyncTaskBuilder {
  def append[C, O](next: Task[C, O], context: C)(implicit ec: ExecutionContext): TaskBuilder[C] =
}

private class AsyncTaskBuilder[C](asyncPipeLine: AsyncPipeLine[C, _])(implicit ec: ExecutionContext) extends TaskBuilder[C] {
  def append[O](next: Task[C, O]): TaskBuilder[O] = {
    new AsyncTaskBuilder[O](new AsyncPipeLine[C, O](asyncPipeLine.future.map()))
  }

  def build(): Task[C] = new Task[C] {
    def start(): Unit = asyncPipeLine.start
  }
}