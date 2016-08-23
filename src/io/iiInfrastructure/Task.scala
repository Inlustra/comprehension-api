package io.iiInfrastructure

trait Task {
  def start(context: TaskRuntimeContext)
}