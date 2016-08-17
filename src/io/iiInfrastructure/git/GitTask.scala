package io.iiInfrastructure.git

import io.iiInfrastructure.Task

/**
  * Created by jackliddiard on 15/08/16.
  */
class GitTask extends Task[GitContext] {
  def start(context: GitContext): Unit = println(s"Started a ${context.command} against ${context.source}")
}