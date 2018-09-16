package repositories.impl

import javax.inject.{Inject, Singleton}

import collections.UserCollection
import models.User
import play.api.libs.json.Json
import reactivemongo.api.commands.WriteResult
import play.modules.reactivemongo.json._
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.BSONObjectID
import repositories.UserRepository

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryImpl @Inject() (userCollection: UserCollection) (implicit ec: ExecutionContext) extends UserRepository{

  private val collection = userCollection.collection
  import reactivemongo.play.json._

  override def findUserById(id: Option[BSONObjectID]): Future[Option[User]] = {
    val s = Json.obj("userID" -> id)
    collection.flatMap(_.find(s).one[User](ReadPreference.primary))
  }

  override def deleteUser(id: Option[BSONObjectID]): Future[WriteResult] = {
    val s = Json.obj("userID" -> id)
    for{
      d <- collection.flatMap(_.remove(s))
    } yield {
      if (d.ok){
        d
      }
      else d
    }
  }

  override def updateUserDetails(user: User): Future[Option[User]] = {
    val s = Json.obj("userID" -> user.userID)
    val currentUser = user
    for{
      _ <- collection
        .flatMap(_.update(s, currentUser))
      r <- findUserById(currentUser.userID)
    } yield r
  }

  override def registerUser(user: User): Future[Option[User]] = {
    val id = user.userID.orElse(Some(BSONObjectID.generate()))
    val r = user.copy(
      userID = id
    )
    for{
      _ <- collection.flatMap(_.insert[User](r))
      r <- findUserById(id)
    } yield r
  }
}
