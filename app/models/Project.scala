package models

import java.util.Date

import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.bson.BSONObjectID

case class Project (projectID: Option[BSONObjectID],
                    projectCode: String,
                    projectDescription: String,
                    createDate: Date,
                    updateDate: Date,
                    totalMinutes: Int,
                    totalCost: Double,
                    implementedUntil: Date)

object Project {

  implicit val objectIdRead: Reads[Option[BSONObjectID]] =
    (__ \ "$oid").read[String].map { oid =>
      Option(BSONObjectID(oid))
    }

  implicit val objectIdWrite: Writes[BSONObjectID] = new Writes[BSONObjectID] {
    def writes(objectId: BSONObjectID): JsValue = Json.obj(
      "$oid" -> objectId.stringify
    )
  }
  implicit val projectRead: Reads[Project] = (
    (JsPath \ "projectID").read[Option[BSONObjectID]] and
      (JsPath \ "projectCode").read[String] and
      (JsPath \ "projectDescription").read[String] and
      (JsPath \ "createDate").read[Date] and
      (JsPath \ "updateDate").read[Date] and
      (JsPath \ "totalMinutes").read[Int] and
      (JsPath \ "totalCost").read[Double] and
      (JsPath \ "implementedUntil").read[Date]
    )(Project.apply _)

  implicit val projectWrites: OWrites[Project] = (
    (JsPath \ "projectID").write[Option[BSONObjectID]] and
      (JsPath \ "projectCode").write[String] and
      (JsPath \ "projectDescription").write[String] and
      (JsPath \ "createDate").write[Date] and
      (JsPath \ "updateDate").write[Date] and
      (JsPath \ "totalMinutes").write[Int] and
      (JsPath \ "totalCost").write[Double] and
      (JsPath \ "implementedUntil").write[Date]
    )(projectJson => (projectJson.projectID,
    projectJson.projectCode,
    projectJson.projectDescription,
    projectJson.createDate,
    projectJson.updateDate,
    projectJson.totalMinutes,
    projectJson.totalCost,
    projectJson.implementedUntil))

  implicit val projectFormat: Format[Project] = Json.format[Project]
}
