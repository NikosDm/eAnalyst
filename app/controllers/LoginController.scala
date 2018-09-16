package controllers

import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

class LoginController extends Controller {

  val loginUserForm = Form(
    tuple (
      "Username" -> nonEmptyText,
      "Password" -> nonEmptyText
    )
  )

  val registerUserForm = Form(
    tuple(
      "Username" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "First Name" -> nonEmptyText,
      "Last Name" -> nonEmptyText,
      "Email" -> nonEmptyText
    )
  )

  def login = Action {
    Ok(views.html.login(loginUserForm))
  }

  def register = Action {
    Ok(views.html.register(registerUserForm))
  }
}
