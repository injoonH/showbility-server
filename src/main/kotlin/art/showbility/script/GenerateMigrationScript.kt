package art.showbility.script

import art.showbility.user.adapter.persistence.UserTable
import org.jetbrains.exposed.v1.core.ExperimentalDatabaseMigrationApi
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.migration.MigrationUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val MIGRATION_DIRECTORY = "src/main/resources/db/migration"

val DB_HOST = System.getenv("DB_HOST")
val DB_PORT = System.getenv("DB_PORT")
val DB_NAME = System.getenv("DB_NAME")
val DB_USER = System.getenv("DB_USER")
val DB_PASSWORD = System.getenv("DB_PASSWORD")

val database = Database.connect(
    driver = "org.postgresql.Driver",
    url = "jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME",
    user = DB_USER,
    password = DB_PASSWORD,
)

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

fun getScriptName(description: String): String {
    val timestamp = LocalDateTime.now().format(formatter)
    return "V${timestamp}__$description"
}

@OptIn(ExperimentalDatabaseMigrationApi::class)
fun main(args : Array<String>) {
    val description = args.first().lowercase().replace(" ", "_")

    transaction(database) {
        MigrationUtils.generateMigrationScript(
            UserTable,
            scriptDirectory = MIGRATION_DIRECTORY,
            scriptName = getScriptName(description),
        )
    }
}
