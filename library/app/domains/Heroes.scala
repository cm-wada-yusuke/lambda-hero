package domains

import scala.beans.BeanProperty

/**
 * Heroes の VO.
 */
case class Heroes(
    @BeanProperty var heroes: java.util.List[Hero]
) {
  def this() = this(
    heroes = new java.util.ArrayList[Hero]()
  )
}
