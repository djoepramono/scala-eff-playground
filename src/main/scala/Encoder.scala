package net.studikode.effplayground

object Encoder {
  def encode(input: String, secret: String): Option[String] = {
    secret.toLowerCase match {
      case "me" => Some(s"${input}and${secret}")
      case _ => None
    }
  }
}
