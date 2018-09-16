import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class Module extends AbstractModule {
  override def configure() = {
    bind(classOf[repositories.UserRepository]).to(classOf[repositories.impl.UserRepositoryImpl])
  }
}
