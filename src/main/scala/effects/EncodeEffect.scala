package net.studikode.effplayground.effects

import org.atnos.eff.Eff.send
import org.atnos.eff.{Eff, MemberIn}

object EncodeEffect {

  type HasEncode[R] = MemberIn[EncodeEffect, R]

  sealed trait EncodeEffect[A]
  //remember this
  case class Encode(message: String) extends EncodeEffect[String]

  def encode[R : HasEncode](message: String): Eff[R, String] = {
    //third type parameter is String because Encode.encode returns string
    send[EncodeEffect, R, String](Encode(message))
  }
}
