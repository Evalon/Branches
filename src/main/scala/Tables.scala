/**
 * Created by Sergey on 01.07.15.
 */

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(Account.schema, AllowedTypePermission.schema, DisallowedObjectPermission.schema, Object.schema, ObjectData.schema, ObjectHistory.schema, ObjectType.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Account
    *  @param accountId Database column account_id SqlType(serial), AutoInc, PrimaryKey
    *  @param accountUsername Database column account_username SqlType(text)
    *  @param accountPassword Database column account_password SqlType(text)
    *  @param accountEmail Database column account_email SqlType(text)
    *  @param accountCreationTime Database column account_creation_time SqlType(timestamp) */
  case class AccountRow(accountId: Int, accountUsername: String, accountPassword: String, accountEmail: String, accountCreationTime: java.sql.Timestamp)
  /** GetResult implicit for fetching AccountRow objects using plain SQL queries */
  implicit def GetResultAccountRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[AccountRow] = GR{
    prs => import prs._
      AccountRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table account. Objects of this class serve as prototypes for rows in queries. */
  class Account(_tableTag: Tag) extends Table[AccountRow](_tableTag, "account") {
    def * = (accountId, accountUsername, accountPassword, accountEmail, accountCreationTime) <> (AccountRow.tupled, AccountRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId), Rep.Some(accountUsername), Rep.Some(accountPassword), Rep.Some(accountEmail), Rep.Some(accountCreationTime)).shaped.<>({r=>import r._; _1.map(_=> AccountRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column account_id SqlType(serial), AutoInc, PrimaryKey */
    val accountId: Rep[Int] = column[Int]("account_id", O.AutoInc, O.PrimaryKey)
    /** Database column account_username SqlType(text) */
    val accountUsername: Rep[String] = column[String]("account_username")
    /** Database column account_password SqlType(text) */
    val accountPassword: Rep[String] = column[String]("account_password")
    /** Database column account_email SqlType(text) */
    val accountEmail: Rep[String] = column[String]("account_email")
    /** Database column account_creation_time SqlType(timestamp) */
    val accountCreationTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("account_creation_time")

    /** Uniqueness Index over (accountEmail) (database name unique_account_email) */
    val index1 = index("unique_account_email", accountEmail, unique=true)
    /** Uniqueness Index over (accountUsername) (database name unique_account_username) */
    val index2 = index("unique_account_username", accountUsername, unique=true)
  }
  /** Collection-like TableQuery object for table Account */
  lazy val Account = new TableQuery(tag => new Account(tag))

  /** Entity class storing rows of table AllowedTypePermission
    *  @param accountId Database column account_id SqlType(int4)
    *  @param objectType Database column object_type SqlType(text) */
  case class AllowedTypePermissionRow(accountId: Int, objectType: String)
  /** GetResult implicit for fetching AllowedTypePermissionRow objects using plain SQL queries */
  implicit def GetResultAllowedTypePermissionRow(implicit e0: GR[Int], e1: GR[String]): GR[AllowedTypePermissionRow] = GR{
    prs => import prs._
      AllowedTypePermissionRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table allowed_type_permission. Objects of this class serve as prototypes for rows in queries. */
  class AllowedTypePermission(_tableTag: Tag) extends Table[AllowedTypePermissionRow](_tableTag, "allowed_type_permission") {
    def * = (accountId, objectType) <> (AllowedTypePermissionRow.tupled, AllowedTypePermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId), Rep.Some(objectType)).shaped.<>({r=>import r._; _1.map(_=> AllowedTypePermissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column account_id SqlType(int4) */
    val accountId: Rep[Int] = column[Int]("account_id")
    /** Database column object_type SqlType(text) */
    val objectType: Rep[String] = column[String]("object_type")

    /** Primary key of AllowedTypePermission (database name allowed_type_permission_pkey) */
    val pk = primaryKey("allowed_type_permission_pkey", (accountId, objectType))

    /** Foreign key referencing Account (database name allowed_type_permission_account_id_fkey) */
    lazy val accountFk = foreignKey("allowed_type_permission_account_id_fkey", accountId, Account)(r => r.accountId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing ObjectType (database name allowed_type_permission_object_type_fkey) */
    lazy val objectTypeFk = foreignKey("allowed_type_permission_object_type_fkey", objectType, ObjectType)(r => r.objectType, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table AllowedTypePermission */
  lazy val AllowedTypePermission = new TableQuery(tag => new AllowedTypePermission(tag))

  /** Entity class storing rows of table DisallowedObjectPermission
    *  @param accountId Database column account_id SqlType(int4)
    *  @param objectId Database column object_id SqlType(uuid) */
  case class DisallowedObjectPermissionRow(accountId: Int, objectId: java.util.UUID)
  /** GetResult implicit for fetching DisallowedObjectPermissionRow objects using plain SQL queries */
  implicit def GetResultDisallowedObjectPermissionRow(implicit e0: GR[Int], e1: GR[java.util.UUID]): GR[DisallowedObjectPermissionRow] = GR{
    prs => import prs._
      DisallowedObjectPermissionRow.tupled((<<[Int], <<[java.util.UUID]))
  }
  /** Table description of table disallowed_object_permission. Objects of this class serve as prototypes for rows in queries. */
  class DisallowedObjectPermission(_tableTag: Tag) extends Table[DisallowedObjectPermissionRow](_tableTag, "disallowed_object_permission") {
    def * = (accountId, objectId) <> (DisallowedObjectPermissionRow.tupled, DisallowedObjectPermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId), Rep.Some(objectId)).shaped.<>({r=>import r._; _1.map(_=> DisallowedObjectPermissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column account_id SqlType(int4) */
    val accountId: Rep[Int] = column[Int]("account_id")
    /** Database column object_id SqlType(uuid) */
    val objectId: Rep[java.util.UUID] = column[java.util.UUID]("object_id")

    /** Primary key of DisallowedObjectPermission (database name disallowed_object_permission_pkey) */
    val pk = primaryKey("disallowed_object_permission_pkey", (accountId, objectId))

    /** Foreign key referencing Account (database name disallowed_object_permission_account_id_fkey) */
    lazy val accountFk = foreignKey("disallowed_object_permission_account_id_fkey", accountId, Account)(r => r.accountId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Object (database name disallowed_object_permission_object_id_fkey) */
    lazy val objectFk = foreignKey("disallowed_object_permission_object_id_fkey", objectId, Object)(r => r.objectId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table DisallowedObjectPermission */
  lazy val DisallowedObjectPermission = new TableQuery(tag => new DisallowedObjectPermission(tag))

  /** Entity class storing rows of table Object
    *  @param objectId Database column object_id SqlType(uuid), PrimaryKey
    *  @param objectDescription Database column object_description SqlType(text)
    *  @param objectType Database column object_type SqlType(text) */
  case class ObjectRow(objectId: java.util.UUID, objectDescription: String, objectType: String)
  /** GetResult implicit for fetching ObjectRow objects using plain SQL queries */
  implicit def GetResultObjectRow(implicit e0: GR[java.util.UUID], e1: GR[String]): GR[ObjectRow] = GR{
    prs => import prs._
      ObjectRow.tupled((<<[java.util.UUID], <<[String], <<[String]))
  }
  /** Table description of table object. Objects of this class serve as prototypes for rows in queries. */
  class Object(_tableTag: Tag) extends Table[ObjectRow](_tableTag, "object") {
    def * = (objectId, objectDescription, objectType) <> (ObjectRow.tupled, ObjectRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(objectId), Rep.Some(objectDescription), Rep.Some(objectType)).shaped.<>({r=>import r._; _1.map(_=> ObjectRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column object_id SqlType(uuid), PrimaryKey */
    val objectId: Rep[java.util.UUID] = column[java.util.UUID]("object_id", O.PrimaryKey)
    /** Database column object_description SqlType(text) */
    val objectDescription: Rep[String] = column[String]("object_description")
    /** Database column object_type SqlType(text) */
    val objectType: Rep[String] = column[String]("object_type")

    /** Foreign key referencing ObjectType (database name object_object_type_fkey) */
    lazy val objectTypeFk = foreignKey("object_object_type_fkey", objectType, ObjectType)(r => r.objectType, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Object */
  lazy val Object = new TableQuery(tag => new Object(tag))

  /** Entity class storing rows of table ObjectData
    *  @param objectId Database column object_id SqlType(uuid)
    *  @param objectVersion Database column object_version SqlType(int4)
    *  @param objectProperties Database column object_properties SqlType(json), Length(2147483647,false) */
  case class ObjectDataRow(objectId: java.util.UUID, objectVersion: Int, objectProperties: String)
  /** GetResult implicit for fetching ObjectDataRow objects using plain SQL queries */
  implicit def GetResultObjectDataRow(implicit e0: GR[java.util.UUID], e1: GR[Int], e2: GR[String]): GR[ObjectDataRow] = GR{
    prs => import prs._
      ObjectDataRow.tupled((<<[java.util.UUID], <<[Int], <<[String]))
  }
  /** Table description of table object_data. Objects of this class serve as prototypes for rows in queries. */
  class ObjectData(_tableTag: Tag) extends Table[ObjectDataRow](_tableTag, "object_data") {
    def * = (objectId, objectVersion, objectProperties) <> (ObjectDataRow.tupled, ObjectDataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(objectId), Rep.Some(objectVersion), Rep.Some(objectProperties)).shaped.<>({r=>import r._; _1.map(_=> ObjectDataRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column object_id SqlType(uuid) */
    val objectId: Rep[java.util.UUID] = column[java.util.UUID]("object_id")
    /** Database column object_version SqlType(int4) */
    val objectVersion: Rep[Int] = column[Int]("object_version")
    /** Database column object_properties SqlType(json), Length(2147483647,false) */
    val objectProperties: Rep[String] = column[String]("object_properties", O.Length(2147483647,varying=false))

    /** Primary key of ObjectData (database name object_data_pkey) */
    val pk = primaryKey("object_data_pkey", (objectId, objectVersion))

    /** Foreign key referencing ObjectHistory (database name object_data_object_id_fkey) */
    lazy val objectHistoryFk = foreignKey("object_data_object_id_fkey", (objectId, objectVersion), ObjectHistory)(r => (r.objectId, r.objectVersion), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ObjectData */
  lazy val ObjectData = new TableQuery(tag => new ObjectData(tag))

  /** Entity class storing rows of table ObjectHistory
    *  @param objectId Database column object_id SqlType(uuid)
    *  @param objectVersion Database column object_version SqlType(int4)
    *  @param objectCreationDate Database column object_creation_date SqlType(timestamp) */
  case class ObjectHistoryRow(objectId: java.util.UUID, objectVersion: Int, objectCreationDate: java.sql.Timestamp)
  /** GetResult implicit for fetching ObjectHistoryRow objects using plain SQL queries */
  implicit def GetResultObjectHistoryRow(implicit e0: GR[java.util.UUID], e1: GR[Int], e2: GR[java.sql.Timestamp]): GR[ObjectHistoryRow] = GR{
    prs => import prs._
      ObjectHistoryRow.tupled((<<[java.util.UUID], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table object_history. Objects of this class serve as prototypes for rows in queries. */
  class ObjectHistory(_tableTag: Tag) extends Table[ObjectHistoryRow](_tableTag, "object_history") {
    def * = (objectId, objectVersion, objectCreationDate) <> (ObjectHistoryRow.tupled, ObjectHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(objectId), Rep.Some(objectVersion), Rep.Some(objectCreationDate)).shaped.<>({r=>import r._; _1.map(_=> ObjectHistoryRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column object_id SqlType(uuid) */
    val objectId: Rep[java.util.UUID] = column[java.util.UUID]("object_id")
    /** Database column object_version SqlType(int4) */
    val objectVersion: Rep[Int] = column[Int]("object_version")
    /** Database column object_creation_date SqlType(timestamp) */
    val objectCreationDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("object_creation_date")

    /** Primary key of ObjectHistory (database name object_history_pkey) */
    val pk = primaryKey("object_history_pkey", (objectId, objectVersion))

    /** Uniqueness Index over (objectCreationDate) (database name unique_object_creation_date) */
    val index1 = index("unique_object_creation_date", objectCreationDate, unique=true)
  }
  /** Collection-like TableQuery object for table ObjectHistory */
  lazy val ObjectHistory = new TableQuery(tag => new ObjectHistory(tag))

  /** Entity class storing rows of table ObjectType
    *  @param objectType Database column object_type SqlType(text), PrimaryKey */
  case class ObjectTypeRow(objectType: String)
  /** GetResult implicit for fetching ObjectTypeRow objects using plain SQL queries */
  implicit def GetResultObjectTypeRow(implicit e0: GR[String]): GR[ObjectTypeRow] = GR{
    prs => import prs._
      ObjectTypeRow(<<[String])
  }
  /** Table description of table object_type. Objects of this class serve as prototypes for rows in queries. */
  class ObjectType(_tableTag: Tag) extends Table[ObjectTypeRow](_tableTag, "object_type") {
    def * = objectType <> (ObjectTypeRow, ObjectTypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = Rep.Some(objectType).shaped.<>(r => r.map(_=> ObjectTypeRow(r.get)), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column object_type SqlType(text), PrimaryKey */
    val objectType: Rep[String] = column[String]("object_type", O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table ObjectType */
  lazy val ObjectType = new TableQuery(tag => new ObjectType(tag))
}
