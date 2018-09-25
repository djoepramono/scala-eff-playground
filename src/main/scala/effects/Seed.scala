package net.studikode.effplayground.effects

import org.atnos.eff.Eff.send
import org.atnos.eff.{Eff, MemberIn}

object Seed {

  sealed trait SeedEffect[A]
  // Why case object? bacause there is no parameter
  // Why int? Because the interpretation of Randomise would need to return int
  // as you can see from `def randomise` below
  case object Randomise extends SeedEffect[Int]

  type HasSeed[R] = MemberIn[SeedEffect, R]

  def randomise[R : HasSeed](): Eff[R, Int] = {
    /* create an Eff[R, A] value from an effectful value of type T[V]
    provided that T is one of the effects of R */
    send[SeedEffect, R, Int](Randomise)
  }

}
