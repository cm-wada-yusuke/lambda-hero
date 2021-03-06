package domains

import scala.beans.BeanProperty

case class Hero(
    @BeanProperty var id: Long,
    @BeanProperty var name: String
) {
  def this() = this(
    id = 0,
    name = ""
  )
}
