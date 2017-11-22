package net.studikode.effplayground.effects

import org.atnos.eff._
import org.atnos.eff.EvalEffect._
import java.io.PrintStream
import cats.Eval

import net.studikode.effplayground.effects.LogEffect.{LogEffect, Info, Error}

object LogInterpreter {

  type HasEval[R] = MemberIn[Eval, R]

  // This needs eval and returns nothing
  private def logMessageTo[R: HasEval](
    printStream: PrintStream,
    message: String
  ): Eff[R, Unit] =
    for {
      // `now` is an eval effect
      _ <- now(printStream.println(s"log - ${message}"))
    } yield()

  // It strips off an effect from a set of effects
  def runLog[
    R,
    U : Member.Aux[LogEffect, R, ?]
      : HasEval,
    A
  ](eff: Eff[R, A]): Eff[U, A] = interpret.translate(eff) {
    new Translate[LogEffect, U] {
      def apply[X](action: LogEffect[X]): Eff[U, X] = action match {
        case Info(message) => logMessageTo[U](System.out, message)
        case Error(message) => logMessageTo[U](System.err, message)
      }
    }
  }

  implicit class LogInterpreterOps[R,A](eff: Eff[R,A]) {
    def runLog[U: Member.Aux[LogEffect, R, ?] : HasEval](): Eff[U,A] =
      LogInterpreter.runLog(eff)
  }
}
