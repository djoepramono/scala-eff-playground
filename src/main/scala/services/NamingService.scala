package net.studikode.effplayground.services

import org.atnos.eff.Eff
import org.atnos.eff.MemberIn

import net.studikode.effplayground.effects.LogEffect
import net.studikode.effplayground.effects.LogEffect.HasLog
import net.studikode.effplayground.effects.Seed
import net.studikode.effplayground.effects.Seed.HasSeed
// import net.studikode.effplayground.effects.NickName
// import net.studikode.effplayground.effects.NickName.HasNickName

object NamingService {

  type HasOption[R] = MemberIn[Option, R]

  def execute[R: HasLog : HasSeed]: Eff[R, Unit] = for {
    _ <- LogEffect.info("Start")
    seed <- Seed.randomise
    // encoded <- NickName.generate(seed)
    // encoded <- fromOption(encodedOption)
    _ <- LogEffect.info("Randomised")
    _ <- LogEffect.info(s"Your name is ${seed}")
    _ <- LogEffect.info("End")
  } yield()

}
