package net.studikode.effplayground

import org.atnos.eff.MemberIn

case class AppError(message: String)

object AppError {
  type AppErrorOr[A] = Either[AppError, A]
  type HasError[R] = MemberIn[AppErrorOr, R]
}
