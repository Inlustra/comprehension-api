package io.iiInfrastructure.taskbuilder.async

import io.iiInfrastructure.taskbuilder.PipeLine
import io.iiInfrastructure.{Task, TaskRuntimeContext}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by jackliddiard on 17/08/16.
  */
private case class AsyncPipeLine(future: Future[TaskWrapper]) extends PipeLine {
  def start(): Unit = true
}

private object AsyncPipeLine {
  def apply(task: Task, context: TaskRuntimeContext)(implicit ec: ExecutionContext): AsyncPipeLine = {
    new AsyncPipeLine(Future {
      TaskWrapper(task, context)
    })
  }
}