package io.iiInfrastructure.git

import io.iiInfrastructure.TaskRuntimeContext

case class GitContext(source: String, command: String) extends TaskRuntimeContext {
  def asMap: Map[String, _] = Map("source" -> source, "command" -> command)
}