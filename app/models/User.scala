package models

import java.util.Date

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class User(userID: Int,
                username: String,
                password: String,
                firstName: String,
                lastName: String,
                email: String,
                createDate: Date )

object User {

  implicit val userRead: Reads[User] = (
    (JsPath \ "userID").read[Int] and
      (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "createDate").read[Date]
    )(User.apply _)

  implicit val userWrites: OWrites[User] = (
    (JsPath \ "userID").write[Int] and
      (JsPath \ "username").write[String] and
      (JsPath \ "password").write[String] and
      (JsPath \ "firstName").write[String] and
      (JsPath \ "lastName").write[String] and
      (JsPath \ "email").write[String] and
      (JsPath \ "createDate").write[Date]
    )(userJson => (userJson.userID,
    userJson.username,
    userJson.password,
    userJson.firstName,
    userJson.lastName,
    userJson.email,
    userJson.createDate))

  implicit val userFormat = Json.format[User]
}
