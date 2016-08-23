package io.iiInfrastructure

trait TaskRuntimeContext {
  def asMap: Map[String, _]
}

object EmptyTaskRuntimeContext extends TaskRuntimeContext {
  override def asMap: Map[String, _] = Map.empty
}