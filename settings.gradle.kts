pluginManagement {
    repositories {
        maven("https://vela.tozydev.id.vn/snapshots")
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "collib"

subproject("core")
subproject("database-core")
subproject("database-exposed")
subproject("versions-catalog")

include(":examples:test-plugin")

fun subproject(
    module: String,
    dir: String = module,
    prefix: String = "${rootProject.name}-",
) {
    val name = "$prefix$module"
    include(name)
    project(":$name").projectDir = file(dir)
}
