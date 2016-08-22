package io.iiInfrastructure.taskbuilder.queue

import io.iiInfrastructure.bus.{BlockingTaskEventQueue, TaskRunner}
import io.iiInfrastructure.taskbuilder.{PipeLine, TaskBuilder}
import io.iiInfrastructure.{Task, TaskRuntimeContext}

import scala.concurrent.ExecutionContext

object QueuedTaskBuilder {
  val queue = new BlockingTaskEventQueue
  queue.subscribe(new TaskRunner)

  def append(task: Task, context: TaskRuntimeContext)(implicit executionContext: ExecutionContext): TaskBuilder = {
    new QueuedTaskBuilder(new QueuedPipeline(task, context, queue))
  }

  private class QueuedTaskBuilder(pipeline: PipeLine)(implicit executionContext: ExecutionContext) extends TaskBuilder {
    def append(task: Task, context: TaskRuntimeContext): TaskBuilder = {
      new QueuedTaskBuilder(new PipeLine {
        def start(): Unit = {
          pipeline.start()
          new QueuedPipeline(task, context, queue).start()
        }
      })
    }

    def build(): PipeLine = pipeline
  }

}