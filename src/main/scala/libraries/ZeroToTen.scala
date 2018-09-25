package net.studikode.effplayground.libraries

import scala.util.Random

//Somehow you need a case class here, instead of using the default Exception class
case class LibraryException(message: String) extends Exception(message)

object ZeroToTen {
  @throws(classOf[Exception])
  def generate: Int = {
    val x = Random.nextInt(10)
    if (x == 4) throw LibraryException("It is number 4, I am going to die")
    else
    	x
  }
}
