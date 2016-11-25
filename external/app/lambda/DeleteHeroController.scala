package lambda

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import infrastructures.HeroDBClient

trait DeleteHeroComponent extends RequestHandler[DeleteHeroRequest, Unit] {
  val client: HeroDBClient

  override def handleRequest(input: DeleteHeroRequest, context: Context): Unit =
    client.delete(input.id.toInt)

}

class DeleteHeroController extends DeleteHeroComponent {
  override val client: HeroDBClient = new HeroDBClient
}
