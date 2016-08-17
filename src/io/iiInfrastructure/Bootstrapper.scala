package io.iiInfrastructure

import java.io.File
import java.net.URL

import io.iiInfrastructure.git.{GitContext, GitTask}
import io.iiInfrastructure.sbt.{SbtContext, SbtTask}

import scala.concurrent.ExecutionContext.Implicits.global

object Bootstrapper extends App {

  val taskBuilder = AsyncTaskBuilder.append(new GitTask(), GitContext(new URL(""), "checkout"))
  .append(SbtTask(sbtContext = SbtContext("test", new File("/home/jackliddiard/Projects/iiInfrastructure"))))
  .append(new GitTask)

  taskBuilder.build().start()

  Thread.sleep(10000)

}