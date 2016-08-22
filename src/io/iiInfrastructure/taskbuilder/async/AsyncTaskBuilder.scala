package io.iiInfrastructure.taskbuilder.async

import io.iiInfrastructure.taskbuilder.{PipeLine, TaskBuilder}
import io.iiInfrastructure.{Task, TaskRuntimeContext}

import scala.concurrent.ExecutionContext

/**
  * Created by jackliddiard on 17/08/16.
  */
object AsyncTaskBuilder {
  def append(next: Task, context: TaskRuntimeContext)(implicit ec: ExecutionContext): TaskBuilder = new AsyncTaskBuilder(AsyncPipeLine(next, context))
}

private class AsyncTaskBuilder(asyncPipeLine: AsyncPipeLine)(implicit ec: ExecutionContext) extends TaskBuilder {

  def append(next: Task, context: TaskRuntimeContext): TaskBuilder = {
    new AsyncTaskBuilder(AsyncPipeLine(asyncPipeLine.future.map(x => TaskWrapper(next, context))))
  }

  def build(): PipeLine = asyncPipeLine
}

private case class TaskWrapper(task: Task, context: TaskRuntimeContext) {
  task.start(context)
  def result: Int = 0
}
