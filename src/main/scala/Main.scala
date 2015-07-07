/**
 * Created by Sergey on 01.07.15.
 */

import java.lang.Exception

import akka.actor.ActorSystem

import scala.concurrent.Await
import scala.concurrent.duration._
import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import Tables._

object Main extends App {
  val db = Database.forURL("jdbc:postgresql://evalon-ds.ru:5432/step1", "it_user2_Sergey", "0")

  def create_schema = {
    val actions = DBIO.seq (
      User.schema.create,
      ObjectType.schema.create,
      Object.schema.create,
      ObjectHistory.schema.create
    )
    Await.result(db.run(actions), 5 seconds)
  }

}
