package lambda.hero

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import domains.Heroes
import infrastructures.HeroDBClient

/**
 * Hero Component
 */
trait HeroesComponent extends RequestHandler[java.lang.Object, Heroes] {

  val client: HeroDBClient

  override def handleRequest(input: Object, context: Context): Heroes = client.findAll

}

class HeroesController extends HeroesComponent {
  override val client: HeroDBClient = new HeroDBClient()
}
