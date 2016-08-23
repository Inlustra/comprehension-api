package io.iiInfrastructure.taskbuilder.queue

import io.iiInfrastructure.{Task, TaskRuntimeContext}
import io.iiInfrastructure.bus.{BlockingTaskEventQueue, TaskEvent}
import io.iiInfrastructure.taskbuilder.PipeLine

/**
  * Created by jackliddiard on 17/08/16.
  */
private class QueuedPipeline(task: Task, taskRuntimeContext: TaskRuntimeContext, queue: BlockingTaskEventQueue) extends PipeLine {
  def start(): Unit = queue.push(new TaskEvent(task, taskRuntimeContext))
}
