package io.iiInfrastructure.sbt

import java.io.File

import io.iiInfrastructure.Task

import scala.sys.process.{Process, ProcessLogger}

/**
  * Created by jackliddiard on 12/08/16.
  */
case class SbtTask(sbtContext: SbtContext) extends Task[SbtContext] {

  val logger = ProcessLogger(System.out.println, System.err.println)

  val process = Process(s"sbt ${sbtContext.argument}", sbtContext.workingDirectory)

  def start() = {
    println("About to execute SbtTask")
    RunningSbtTask(process.run(logger)).start()
  }

}

case class RunningSbtTask(process: Process) extends Task {
  def kill() = process.destroy()

  def start() = process.exitValue()
}

case class SbtContext(argument: String, workingDirectory: File)