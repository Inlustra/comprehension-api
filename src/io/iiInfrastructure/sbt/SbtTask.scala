package io.iiInfrastructure.sbt

import java.io.File

import io.iiInfrastructure.{TaskRuntimeContext, Task}

import scala.sys.process.{Process, ProcessLogger}

/**
  * Created by jackliddiard on 12/08/16.
  */
case class SbtTask(sbtContext: SbtContext) extends Task {

  val logger = ProcessLogger(System.out.println, System.err.println)

  val process = Process(s"sbt ${sbtContext.argument}", sbtContext.workingDirectory)

  def start(context: TaskRuntimeContext) = {
    println("About to execute SbtTask")
    process.run(logger).exitValue()
  }

}

case class SbtContext(argument: String, workingDirectory: File)