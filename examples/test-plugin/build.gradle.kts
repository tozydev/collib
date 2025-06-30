plugins {
    alias(libs.plugins.paper.infra.kotlin)
    alias(libs.plugins.paper.infra.paper)
}

repositories {
    maven {
        name = "VelaSnapshots"
        url = uri("https://vela.tozydev.id.vn/snapshots")
    }
}

dependencies {
    library(projects.collibCore)
    library(projects.collibDatabaseExposed)
}

paperInfra {
    paper {
        plugin {
            apiVersion = "1.21.6"
            main = "vn.id.tozydev.collib.testplugin.TestPlugin"
        }

        runServer {
            acceptEula = true
        }
    }
}
