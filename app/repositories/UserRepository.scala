package repositories

import models.User
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future

trait UserRepository {

  def findUserById(id: Int): Future[Option[User]]

  def registerUser(user: User): Future[Option[User]]

  def deleteUser(id: Int): Future[WriteResult]

  def updateUserDetails(user: User): Future[Option[User]]
}
