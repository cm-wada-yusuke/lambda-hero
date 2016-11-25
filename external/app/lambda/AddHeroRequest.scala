package lambda

import scala.beans.BeanProperty

class AddHeroRequest(
    @BeanProperty var name: String
) {
  def this() = this("")
}
