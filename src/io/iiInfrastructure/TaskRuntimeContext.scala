package io.iiInfrastructure

trait TaskRuntimeContext {
  def asMap: Map[String, _]
}
