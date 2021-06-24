package example

import zio.test.{DefaultRunnableSpec, assertCompletesM}

object MacroCodeCoverageReproducerSpec extends DefaultRunnableSpec {
  def spec = suite("Test")(
    testM("Object with macro usage"){
      TestServiceWithMacro.doSomething *> assertCompletesM
    },
    testM("Object with without macro usage"){
      TestServiceWithoutMacro.doSomething *> assertCompletesM
    }
  )
}
