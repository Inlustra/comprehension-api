package io.iiInfrastructure

import java.io.File

import io.iiInfrastructure.git.{GitContext, GitTask}
import io.iiInfrastructure.sbt.{SbtContext, SbtTask}
import io.iiInfrastructure.taskbuilder.queue.QueuedTaskBuilder

import scala.concurrent.ExecutionContext.Implicits.global

object Bootstrapper extends App {

//  val taskBuilder = AsyncTaskBuilder.append(new GitTask(), GitContext("http://google.com/", "checkout"))
//  .append(SbtTask(sbtContext = SbtContext("test", new File("/home/jackliddiard/Projects/iiInfrastructure"))), new TaskRuntimeContext {
//    override def asMap: Map[String, _] = Map.empty
//  })
//  .append(new GitTask, GitContext("google.com", "checkout"))
//
//  taskBuilder.build().start()
//
//  Thread.sleep(4000)

  println("About to start queued task builder")

  val newTaskbuilder = QueuedTaskBuilder.append(new GitTask(), GitContext("http://google.com/", "checkout"))
    .append(SbtTask(sbtContext = SbtContext("test", new File("/home/jackliddiard/Projects/iiInfrastructure"))), new TaskRuntimeContext {
      override def asMap: Map[String, _] = Map.empty
    })
    .append(new GitTask, GitContext("google.com", "checkout"))

  newTaskbuilder.build().start()

}