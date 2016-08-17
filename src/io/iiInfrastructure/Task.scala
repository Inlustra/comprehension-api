package io.iiInfrastructure

trait Task[C, O] {
  def start(context: C): O
}