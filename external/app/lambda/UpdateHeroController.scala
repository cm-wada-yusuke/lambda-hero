package lambda

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import domains.Hero
import infrastructures.HeroDBClient


trait UpdateHeroComponent extends RequestHandler[UpdateHeroRequest, Unit] {

  val client: HeroDBClient

  override def handleRequest(input: UpdateHeroRequest, context: Context): Unit =
    client.update(Hero(input.id.toLong, input.name))
}

class UpdateHeroController extends UpdateHeroComponent {
  override val client: HeroDBClient = new HeroDBClient()
}
