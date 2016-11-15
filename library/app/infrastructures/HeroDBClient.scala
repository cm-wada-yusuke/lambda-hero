package infrastructures

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.document.{ DynamoDB, Item }
import domains.{ Hero, Heroes }
import infrastructures.HeroDBClient.{ AttributeName, HeroConverter }

import scala.collection.JavaConversions._


/**
 * Hero DB の Client
 */
class HeroDBClient {

  private val db = new DynamoDB(Regions.AP_NORTHEAST_1)
  private val table = db.getTable("hero")

  private val ProjectionExpr = List(
    AttributeName.Id, AttributeName.Name
  ).mkString("", ",", "")

  def findAll: Heroes =
    Heroes(table.scan().toList.map(HeroConverter.toHero))
}

object HeroDBClient {

  object AttributeName {
    val Id = "id"
    val Name = "name"
  }

  object HeroConverter {
    def toHero(item: Item): Hero =
      Hero(id = item.getInt(AttributeName.Id),
        name = item.getString(AttributeName.Name)
      )
  }

}