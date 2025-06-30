plugins {
    alias(libs.plugins.paper.infra.kotlin)
    alias(libs.plugins.maven.publish)
}

dependencies {
    api(projects.collibDatabaseCore)

    api(platform(libs.exposed.bom))
    api(libs.bundles.exposed)
}
