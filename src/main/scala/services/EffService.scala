package net.studikode.effplayground.services

import org.atnos.eff.Eff
import org.atnos.eff.MemberIn

import net.studikode.effplayground.effects.LogEffect
import net.studikode.effplayground.effects.LogEffect.HasLog
import net.studikode.effplayground.effects.EncodeEffect
import net.studikode.effplayground.effects.EncodeEffect.HasEncode

object EffService {

  type HasOption[R] = MemberIn[Option, R]

  def execute[R: HasLog : HasEncode]: Eff[R, Unit] = for {
    _ <- LogEffect.info("Start")
    //Encoded here is a string
    encoded <- EncodeEffect.encode("hello")
    // encoded <- fromOption(encodedOption)
    _ <- LogEffect.info(s"The output is ${encoded}")
    _ <- LogEffect.info("End")
  } yield()

}
