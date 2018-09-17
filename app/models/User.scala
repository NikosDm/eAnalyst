package models

import java.util.Date

import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._

case class User(userID: Option[BSONObjectID] = None,
                username: String,
                password: String,
                firstName: String,
                lastName: String,
                email: String,
                createDate: Date )

object User {

  implicit def optionUserFormat[BSONObjectID: Format]: Format[Option[BSONObjectID]] = new Format[Option[BSONObjectID]]{
    override def reads(json: JsValue): JsResult[Option[BSONObjectID]] = json.validateOpt[BSONObjectID]

    override def writes(o: Option[BSONObjectID]): JsValue = o match {
      case Some(t) ⇒ implicitly[Writes[BSONObjectID]].writes(t)
      case None ⇒ JsNull
    }
  }

  implicit val userRead: Reads[User] = (
    (JsPath \ "userID").read[Option[BSONObjectID]] and
      (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "createDate").read[Date]
    )(User.apply _)

  implicit val userWrites: OWrites[User] = (
    (JsPath \ "userID").write[Option[BSONObjectID]] and
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

  implicit val userFormat: Format[User] = Json.format[User]
}
