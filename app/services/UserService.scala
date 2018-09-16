package services

import javax.inject.{Inject, Singleton}

import models.User
import reactivemongo.bson.BSONObjectID
import services.UserService
import repositories.UserRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserService @Inject() (userRepository: UserRepository) (implicit ex: ExecutionContext) {

  def registerUser(user: User) = {
    userRepository.registerUser(user)
  }

  def deleteUser(id: Int) = {
    userRepository.deleteUser(id)
  }

  def updateUser(user: User) = {
    userRepository.updateUserDetails(user)
  }

  def findUserByID(userID: Int): Future[Option[User]] = {
    userRepository.findUserById(userID)
  }
}
