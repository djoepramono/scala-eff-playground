package net.studikode.effplayground

object NickNameGenerator {

  // val key = getEnv("APP_KEY")

  def generate(input: String, secret: String): Option[String] = {
    secret.toLowerCase match {
      case "me" => Some(s"${input}and${secret}")
      case _ => None
    }
  }
}
