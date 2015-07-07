
import slick.driver.PostgresDriver.api._
import java.sql.Timestamp

object Default {

}

object Tables {

  class User(tag: Tag) extends Table[(Int, String, String, String, Option[String], String, Timestamp)](tag, "User") {

    def id = column[Int]("user_id", O.PrimaryKey, O.AutoInc)

    def email = column[String]("user_email")

    def password = column[String]("user_password")

    def firstname = column[String]("user_firstname")

    def secondname = column[Option[String]]("user_midname")

    def lastname = column[String]("user_lastname")

    def signupDate = column[Timestamp]("user_signup_date", O.Default(new Timestamp(System.currentTimeMillis())))

    def unique_email = index("unique_user_email", email, unique = true)

    def * = (id, email, password, firstname, secondname, lastname, signupDate)
  }

  lazy val User = new TableQuery(tag => new User(tag))

  class ObjectType(tag: Tag) extends Table[(String)](tag, "ObjectType") {
    def objectType = column[String]("object_type", O.PrimaryKey)

    def * = objectType
  }

  lazy val ObjectType = new TableQuery(tag => new ObjectType(tag))

  class Object(tag: Tag) extends Table[(Int, String, String)](tag, "Object") {
    def id = column[Int]("object_id", O.PrimaryKey, O.AutoInc)

    def objectType = column[String]("object_type")

    def description = column[String]("object_description")

    def typeFK = {
      foreignKey("object_object_type_fk", objectType, ObjectType) (
                  (p: ObjectType) => p.objectType,
                  ForeignKeyAction.NoAction,
                  ForeignKeyAction.NoAction
                 )
    }

    def * = (id, objectType, description)

  }

  lazy val Object = new TableQuery(tag => new Object(tag))

  class ObjectHistory(tag: Tag) extends Table[(Int, Int, String, Timestamp)](tag, "ObjectHistory") {
    def id = column[Int]("object_id")

    def version = column[Int]("object_version", O.AutoInc)

    def properties = column[String]("object_properties", O.Length(32768, varying = false))

    def creationDate = column[Timestamp]("object_creation_date", O.Default(new Timestamp(System.currentTimeMillis())))

    def idFK = {
      foreignKey("object_history_object_fk", id, Object) (
                  (p: Object) => p.id,
                  ForeignKeyAction.NoAction,
                  ForeignKeyAction.NoAction
                )
    }

    def primary_key = primaryKey("object_history_pk", (id, version))

    def * = (id, version, properties, creationDate)
  }

  lazy val ObjectHistory = new TableQuery(tag => new ObjectHistory(tag))

}