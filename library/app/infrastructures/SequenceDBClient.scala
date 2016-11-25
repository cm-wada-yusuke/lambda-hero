package infrastructures

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.NameMap
import com.amazonaws.services.dynamodbv2.document.{ DynamoDB, Table }
import com.amazonaws.services.dynamodbv2.model.ReturnValue

import scala.collection.JavaConverters._


class SequenceDBClient {

  import SequenceDBClient.AttributeName._

  private val db: DynamoDB = new DynamoDB(Regions.AP_NORTHEAST_1)
  private val table: Table = db.getTable("sequence")


  def sequence(keyName: String): Long = {
    val updateItemSpec: UpdateItemSpec = new UpdateItemSpec()
        .withPrimaryKey(SequenceName, keyName)
        .withReturnValues(ReturnValue.UPDATED_NEW)
        .withUpdateExpression("SET #count = if_not_exists(#count, :zero) + :i")
        .withNameMap(new NameMap().`with`("#count", "count"))
        .withValueMap(Map[String, AnyRef](":i" -> int2Integer(1), ":zero" -> int2Integer(0)).asJava)

    table.updateItem(updateItemSpec).getItem.getLong("count")
  }

}

object SequenceDBClient {

  object AttributeName {
    val SequenceName = "key"
  }

}
