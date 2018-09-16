package repositories

import models.User
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONObjectID

import scala.concurrent.Future

trait UserRepository {

  def findUserById(id: Option[BSONObjectID]): Future[Option[User]]

  def registerUser(user: User): Future[Option[User]]

  def deleteUser(id: Option[BSONObjectID]): Future[WriteResult]

  def updateUserDetails(user: User): Future[Option[User]]
}
