package lambda

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import domains.Heroes
import infrastructures.HeroDBClient

/**
 * Hero Component
 */
trait GetHeroesComponent extends RequestHandler[java.lang.Object, Heroes] {

  val client: HeroDBClient

  override def handleRequest(input: Object, context: Context): Heroes = client.findAll

}

class GetHeroesController extends GetHeroesComponent {
  override val client: HeroDBClient = new HeroDBClient()
}
