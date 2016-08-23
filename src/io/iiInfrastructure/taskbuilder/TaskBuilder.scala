package io.iiInfrastructure.taskbuilder

import io.iiInfrastructure.{Task, TaskRuntimeContext}

import scala.concurrent.{ExecutionContext, Future}

trait TaskBuilder {
  def append(task: Task, context: TaskRuntimeContext): TaskBuilder
  def build(): PipeLine
}











