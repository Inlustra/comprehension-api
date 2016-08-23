package io.iiInfrastructure

import java.io.File

import io.iiInfrastructure.git.{GitContext, GitTask}
import io.iiInfrastructure.sbt.{SbtContext, SbtTask}
import io.iiInfrastructure.taskbuilder.queue.QueuedTaskBuilder

import scala.concurrent.ExecutionContext.Implicits.global

object Bootstrapper extends App {

  println("About to start queued task builder")

  val workingdirectory = new File("/home/jackliddiard/Projects/iiInfrastructure");

  val newTaskbuilder = QueuedTaskBuilder.append(new GitTask(), GitContext("http://google.com/", "checkout"))
    .append(SbtTask(sbtContext = SbtContext("test", workingdirectory)), EmptyTaskRuntimeContext)
    .append(SbtTask(sbtContext = SbtContext("package", workingdirectory)), EmptyTaskRuntimeContext)
    .append(new GitTask, GitContext("google.com", "checkout"))

  newTaskbuilder.build().start()

}