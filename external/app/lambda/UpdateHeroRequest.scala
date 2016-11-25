package lambda

import scala.beans.BeanProperty

class UpdateHeroRequest(
    @BeanProperty var id: String,
    @BeanProperty var name: String
) {

  def this() = this(
    id = "", name = ""
  )

}
