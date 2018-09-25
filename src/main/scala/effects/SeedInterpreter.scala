package net.studikode.effplayground.effects

import org.atnos.eff._
import org.atnos.eff.all._  // from Either is from here

import net.studikode.effplayground.effects.Seed._
import net.studikode.effplayground.libraries.ZeroToTen
import net.studikode.effplayground.AppError
import net.studikode.effplayground.AppError.HasError
import net.studikode.effplayground.libraries.LibraryException
import net.studikode.effplayground.effects.LogEffect._

object SeedInterpreter {

  // It strips off SeedEffect from a set of effects
  // But it may throw error, hence we need to put HasError
  def runSeed[
    R,
    U : Member.Aux[SeedEffect, R, ?]
      : HasError
      : HasLog
    ,
    A
  ](eff: Eff[R, A]): Eff[U, A] = interpret.translate(eff) {
    new Translate[SeedEffect, U] {
      def apply[X](action: SeedEffect[X]): Eff[U, X] = action match {
        // pattern matching on case object
        case Randomise => randomise[U]()
      }
    }
  }

  private def randomise[R : HasError : HasLog](): Eff[R, Int] = {
    //Cannot straight call an object that generate int
    //For some reason it has to be wrapped inside a monad
    //e.g. Either Monad
    LogEffect.info("Inside randomise");
    val result = for {
      number <- fromEither(generateSeed)
    } yield(number)

    LogEffect.info(s"Randomise result: ${result}" )
    result
  }

  // The name of implicit class doesn't matter
  implicit class SeedInterpreterOps[R,A](eff: Eff[R,A]) {
    def runSeed[U: Member.Aux[SeedEffect, R, ?] : HasError : HasLog](): Eff[U,A] =
      SeedInterpreter.runSeed(eff)
  }

  // Run third party library and lift the result into a monad
  def generateSeed: Either[AppError, Int] = {
    try {
      Right(ZeroToTen.generate)
    } catch {
      case LibraryException(msg) => Left(AppError(msg))
    }    
  }
}
