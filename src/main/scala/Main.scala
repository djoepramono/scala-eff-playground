package net.studikode.effplayground

import org.atnos.eff.Fx
import cats.Eval // For eval
import org.atnos.eff.syntax.all._ // for runEval
// import scala.concurrent.Await
// import scala.concurrent.duration.Duration
// import monix.eval.Task

import net.studikode.effplayground.services.NamingService
import net.studikode.effplayground.effects.LogEffect.LogEffect
import net.studikode.effplayground.effects.Seed.SeedEffect
import net.studikode.effplayground.effects.SeedInterpreter._
import net.studikode.effplayground.effects.LogInterpreter._
import net.studikode.effplayground.AppError.AppErrorOr
// import net.studikode.effplayground.effects.NickName.NickNameEffect
// import net.studikode.effplayground.effects.NickNameInterpreter._ //import the implicit not the function


object Playground {
  def main(args: Array[String]): Unit = {

    type Stack = Fx.fx5[
      SeedEffect,
      // NickNameEffect,
      LogEffect,
      Option,
      AppErrorOr, // Yes, it's the type alias, because really it's a type stack
      Eval,
    ]

    val program = NamingService.execute[Stack]

    // val task = program.runLog().runEval.runAsync
    // Await.result(task.runAsync, Duration.Inf)

    //This is pretty much interpreting (i.e. run) all the way by running .run
    //If you encounter error like org.atnos.eff.Eff[org.atnos.eff.NoFx, ...]
    //that's because you have not put run or runTask
    // val endResult = program.runSeed().runGenerator().runLog().runOption.runEval
    val endResult = program.runSeed().runLog().runEval
    // program.runSeed().runLog().runEval

    // In the end of the day you still need to deal with Option
    // val output = endResult match {
    //   case Some(x) => x.toString()
    //   case None => "Nothing"
    // }

    // Error message: Impure(NoEffect(Vector(())),<function1>,Last(None))
    println(endResult)
  }
}
