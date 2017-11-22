package net.studikode.effplayground.effects

import org.atnos.eff.Eff.send
import org.atnos.eff.{Eff, MemberIn}

object LogEffect {

  type HasLog[R] = MemberIn[LogEffect, R]

  sealed trait LogEffect[A]
  case class Info(message: String) extends LogEffect[Unit]
  case class Error(message: String) extends LogEffect[Unit]

  def info[R : HasLog](message: String): Eff[R, Unit] = {
    /* create an Eff[R, A] value from an effectful value of type T[V]
    provided that T is one of the effects of R */
    send[LogEffect, R, Unit](Info(message))
  }

  def error[R : HasLog](message: String): Eff[R, Unit] = {
    send[LogEffect, R, Unit](Error(message))
  }
}
