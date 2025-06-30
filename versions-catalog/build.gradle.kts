plugins {
    `version-catalog`
    alias(libs.plugins.maven.publish)
}

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
        version("collib", version.toString())
        sequenceOf(projects.collibCore, projects.collibDatabaseCore, projects.collibDatabaseExposed).forEach {
            library(it.name, it.group!!, it.name).versionRef("collib")
        }
    }
}
