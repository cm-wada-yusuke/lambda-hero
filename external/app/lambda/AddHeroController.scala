package lambda

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import domains.Hero
import infrastructures.{ HeroDBClient, SequenceDBClient }

trait AddHeroComponent extends RequestHandler[AddHeroRequest, Hero] {

  val sequenceClient: SequenceDBClient
  val heroClient: HeroDBClient

  override def handleRequest(input: AddHeroRequest, context: Context): Hero = {
    val seq = sequenceClient.sequence("heroId")
    val newHero = Hero(seq, input.name)
    heroClient.create(newHero)
    newHero
  }

}

class AddHeroController extends AddHeroComponent {
  override val sequenceClient: SequenceDBClient = new SequenceDBClient()
  override val heroClient: HeroDBClient = new HeroDBClient()
}
