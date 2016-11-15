package domains

import scala.beans.BeanProperty

/**
 * HeroのVO.
 */
case class Hero(
    @BeanProperty var id: Int,
    @BeanProperty var name: String
) {
  def this() = this(
    id = 0,
    name = ""
  )
}
