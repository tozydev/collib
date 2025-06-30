package vn.id.tozydev.collib.testplugin

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.mainDispatcher
import kotlinx.coroutines.delay
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import vn.id.tozydev.collib.database.core.DatabaseConfig
import vn.id.tozydev.collib.database.exposed.connect

class TestPlugin : SuspendingJavaPlugin() {
    private lateinit var database: Database

    override suspend fun onEnableAsync() {
        launch(mainDispatcher) {
            componentLogger.info("This sleep will not block main thread!")
            delay(1000L)
            componentLogger.info("This is a test plugin for ColLib!")
        }
        componentLogger.info("Sleep for 1 second...")
        delay(1000L)
        componentLogger.info("Sleep 1 second done!")
        componentLogger.info("Sleep for another 2 second...")
        delay(2000L)
        componentLogger.info("Sleep 2 seconds done!")
        database =
            Database.connect(
                DatabaseConfig(
                    url = "jdbc:sqlite:./test.db",
                ),
            )

        transaction(database) {
            SchemaUtils.create(TestTable)
            TestTable.insert {
                it[name] = "Test Name"
            }
        }

        transaction(database) {
            val testName = TestTable.selectAll().firstOrNull()?.get(TestTable.name)
            if (testName != null) {
                componentLogger.info("Inserted name: $testName")
            } else {
                componentLogger.warn("No name found in the database!")
            }
        }
    }

    override suspend fun onDisableAsync() {
        componentLogger.info("TestPlugin disabled successfully!")
        transaction(database) {
            SchemaUtils.drop(TestTable)
        }
        TransactionManager.closeAndUnregister(database)
    }
}
