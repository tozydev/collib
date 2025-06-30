package vn.id.tozydev.collib.testplugin

import org.jetbrains.exposed.v1.core.Table

object TestTable : Table() {
    val name = varchar("name", 50)
}
