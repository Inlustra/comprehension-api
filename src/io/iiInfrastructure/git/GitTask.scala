package io.iiInfrastructure.git

import io.iiInfrastructure.{TaskRuntimeContext, Task}

/**
  * Created by jackliddiard on 15/08/16.
  */
class GitTask extends Task {
  def start(context: TaskRuntimeContext): Unit = println(s"Started a ${context.asMap.get("command")} against ${context.asMap.get("source")}")
}