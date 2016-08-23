package io.iiInfrastructure.bus

import io.iiInfrastructure.{TaskRuntimeContext, Task}

case class TaskEvent(task: Task, context: TaskRuntimeContext)
