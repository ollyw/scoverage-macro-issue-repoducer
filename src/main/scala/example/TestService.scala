package example

import zio.{Has, RIO, Task, ZIO}
import zio.macros.accessible

// The accessible macro generates an accessor method called someServiceMethod in the TestService object
// and preserve other methods. i.e. it is additive. It doesn't affect existing methods in the body
// see https://zio.dev/docs/howto/use-zio-macros#description
@accessible
object TestServiceWithMacro {
  trait Service {
    def someServiceMethod(): Task[Unit]
  }

  val doSomething = zio.console.putStrLn("This line is executed")
}

object TestServiceWithoutMacro {
  trait Service {
    def someServiceMethod(): Task[Unit]
  }

  // This is the equivalent of what the macro would make
  def someServiceMethod(): RIO[Has[Service], Unit] = ZIO.accessM(_.get.someServiceMethod())

  val doSomething = zio.console.putStrLn("This line is executed")
}
