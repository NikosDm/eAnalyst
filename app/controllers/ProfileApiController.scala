package controllers

import java.util.Date
import javax.inject._

import models.User
import services.UserService
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ProfileApiController @Inject()
(userService: UserService)(implicit ec: ExecutionContext) extends Controller {

  def registerUser = Action { implicit request =>
    val userData = request.body.asFormUrlEncoded
    val newUser = new User( None,
      userData.get("Username").head,
      userData.get("Password").head,
      userData.get("First Name").head,
      userData.get("Last Name").head,
      userData.get("Email").head,
      new Date())
    val result = userService.registerUser(newUser)
    Redirect(routes.Application.index())
  }

  def loginUser = Action { implicit request =>
    val userData = request.body.asFormUrlEncoded
    Redirect(routes.Application.index())
  }
}
