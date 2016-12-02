package domains

import org.scalatest.{ FlatSpec, MustMatchers }

import scala.collection.JavaConverters._

class HeroesSpec extends FlatSpec with MustMatchers {

  "ヒーローのソート" should "IDの昇順になる" in {
    val heroes = Heroes(
      List(
        Hero(5, "5"),
        Hero(3, "3"),
        Hero(1, "2"),
        Hero(2, "1"),
        Hero(4, "4")
      ).asJava
    )
    heroes.sort.heroes.asScala.map(_.id) must contain theSameElementsAs Iterable(1, 2, 3, 4, 5)
  }
}
