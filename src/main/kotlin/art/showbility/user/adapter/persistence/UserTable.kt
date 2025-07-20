package art.showbility.user.adapter.persistence

import org.jetbrains.exposed.v1.core.dao.id.ULongIdTable
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime

object UserTable : ULongIdTable("user") {
    val handle = varchar("handle", 32).uniqueIndex()
    val nickname = varchar("nickname", 32)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
