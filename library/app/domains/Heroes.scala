package domains

import scala.beans.BeanProperty

import scala.collection.JavaConverters._

case class Heroes(
    @BeanProperty var heroes: java.util.List[Hero]
) {
  def this() = this(
    heroes = new java.util.ArrayList[Hero]()
  )

  def sort: Heroes = Heroes(this.heroes.asScala.sortWith(_.id < _.id).asJava)
}
